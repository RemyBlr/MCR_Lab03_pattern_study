package game.enemies;

import game.Position;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;

public class Enemy {
    private final Path2D path;
    private Position pos;
    private double distanceTraveled = 0.0;
    private final double speed;
    private int hp;
    private static final int SIZE = 20;

    private int reward = 10;

    public Enemy(Path2D path, double speed, int hp) {
        this.path = (Path2D) path.clone();
        this.speed = speed;
        this.hp = hp;
        this.pos = giveStartingPos();
    }

    private Position giveStartingPos() {
        Point2D startPoint = getPathPoint(0);
        double randomAngle = Math.random() * 2 * Math.PI;

        double radius = 800; // around the castle radius
        double randomX = startPoint.getX() + radius * Math.cos(randomAngle);
        double randomY = startPoint.getY() + radius * Math.sin(randomAngle);
        return new Position(randomX, randomY);
    }

    /***********************************************
     *  Path management (generated for now)
     ***********************************************/
    public class PathPointCalculator {
        private static final int COORDS_ARRAY_SIZE = 6;
        private final Path2D path;
        private Point2D.Double lastPoint;
        private double segmentStart;

        public PathPointCalculator(Path2D path) {
            this.path = path;
            this.segmentStart = 0;
        }

        public Point2D calculatePointAtDistance(double distance) {
            PathIterator pathIterator = path.getPathIterator(null);
            double[] coords = new double[COORDS_ARRAY_SIZE];

            while (!pathIterator.isDone()) {
                int segmentType = pathIterator.currentSegment(coords);

                if (processPathSegment(segmentType, coords, distance)) {
                    return lastPoint;
                }

                pathIterator.next();
            }

            return lastPoint;
        }

        private boolean processPathSegment(int segmentType, double[] coords, double distance) {
            switch (segmentType) {
                case PathIterator.SEG_MOVETO:
                    handleMoveToSegment(coords);
                    break;
                case PathIterator.SEG_LINETO:
                    if (handleLineToSegment(coords, distance)) {
                        return true;
                    }
                    break;
            }
            return false;
        }

        private void handleMoveToSegment(double[] coords) {
            Point2D.Double currentPoint = new Point2D.Double(coords[0], coords[1]);
            if (lastPoint == null) {
                lastPoint = currentPoint;
            }
        }

        private boolean handleLineToSegment(double[] coords, double distance) {
            Point2D.Double nextPoint = new Point2D.Double(coords[0], coords[1]);
            double segmentLength = lastPoint.distance(nextPoint);

            if (isPointInSegment(segmentLength, distance)) {
                lastPoint = calculateIntermediatePoint(nextPoint, segmentLength, distance);
                return true;
            }

            segmentStart += segmentLength;
            lastPoint = nextPoint;
            return false;
        }

        private boolean isPointInSegment(double segmentLength, double distance) {
            return segmentStart + segmentLength >= distance;
        }

        private Point2D.Double calculateIntermediatePoint(Point2D.Double nextPoint,
                                                          double segmentLength,
                                                          double distance) {
            double ratio = (distance - segmentStart) / segmentLength;
            double x = lastPoint.x + (nextPoint.x - lastPoint.x) * ratio;
            double y = lastPoint.y + (nextPoint.y - lastPoint.y) * ratio;
            return new Point2D.Double(x, y);
        }
    }

    public void update() {
        distanceTraveled += speed;
        Point2D point = getPathPoint(distanceTraveled);
        pos = new Position((int) point.getX(), (int) point.getY());
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillOval((int)pos.getX() - SIZE / 2, (int)pos.getY() - SIZE / 2, SIZE, SIZE);
    }

    public Position getPosition() {
        return pos;
    }

    public int getReward() {
        return reward;
    }

    private Point2D getPathPoint(double distance) {
        PathPointCalculator calculator = new PathPointCalculator(path);
        return calculator.calculatePointAtDistance(distance);
    }
}