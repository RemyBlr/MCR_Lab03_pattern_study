# Paint Tower Defense - Rapport
- Auteurs: Bleuer Rémy, Changanaqui Yoann, Richard Aurélien
- Date: 08.06.25
- Version: 1.0
- Classe: MCR-A

## Contexte
Ce projet est une application Java "Paint Tower Defense". Le joueur dessine des murs dans une zone délimitée
pour défendre un chataeu contre des vagues d'ennemis. L'interface est réalisée avec Swing et l'application 
utilise le pattern Command pour gérer différentes actions.

## Mise en oeuvre du modèle
Le projet est organisé en plusieurs packages :
- `app`: contient la classe principale `Main` qui lance l'application.
- `command`: rassemble les actions exéctables (pattern Command) telles que dessiner un mur ou l'ajout d'encre.
- `game`: gère l'état global (class `Game`, les vagues d'ennemis, chateaux, murs.)
  - `enemies`: regroupe les classes liées aux ennemis.
- `tools`: regroupe les outils de dessin et de gestion des murs.
- `window`: contient les composants Swing pour l'interface utilisateur.

### Fonctionnement du jeu
La classe `Game` est un singleton qui conserve la quantité d'encre qui est disponible, l'or récolté et la
liste des murs construits. Elle met à jour les ennemis et notifie les observeurs à chaque tick de la boucle de jeu.

Les interactions de l'utilisateur passent par le pattern Command. Par exemple, la commande `CreateWallCommand` retire
l'encre nécessaire puis enregistre un nouveau mur dans la partie. Ces commandes peuvent être annulées via le 
`CommandManager` si elles sont étendue de la classe `UndoableCommand`.

`ToolManager` conserve l'outil de dessin sélectionné (couleur, sélection) et informe l'interface lorsque l'outil change.

La fenêtre principale `TDWindow` configure les raccourcis clavier et gère les différents panneaux : zone de dessin,
shop et barre d'état.

Les vagues d'ennemis sont créées par des fabriques `EnemyFactory`. En mode normal, la fabrique génère 25 ennemis choisis
aléatoirement.

### Pattern Commande
// TODO

### Interface
// TODO

## Conslusion
Ce projet utilise plusieurs patterns vu en cours, mais met l'accent sur le pettern Commande qui est le fondement de notre
petit jeu en Java. De ce fait, la logique reste bien séparée de l'affichage, ce qui facilitera les évoltions du jeu par
la suite.
// TODO
