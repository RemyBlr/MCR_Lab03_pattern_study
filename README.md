# Documentation de déploiement et d'utilisation
Ce projet est un petit jeu de Tower Defense entièrement réaslisé en Java et Swing. L'application se lance
via la classe `app.Main`, qui initialise les valeurs par défauts et créer la fenêtre principale `TDWindow`.

## Pré-requis
- JDK 17 ou un version plus récente

## Compilation
Afin de pouvoir compiler manuellement :
```
TODO JSP QUELLE COMMANDE
```

## Exécution
Lancer ensuite :
```
TODO LA AUSSI
java out app.Main [encre] [pv] [or]
```
Les trois paramètres sont facultatifs. S'ils ne sont pas entrés, les valeurs par défaut (200,20,0) seront utilisés.

## Commande de jeu
Les raccourcis clavier sont configurés dans la `TDWindow`.
- `1` : Outil de sélection
- `2` à `6` : Stylos (noir, bleu, vert, rouge, doré)
- `ctrl + 1` à `ctrl + 4` : Achats (recharge d'encre, augmentation encre, ajout Pv, plus grande zone)
- `ctrl + z` : Undo de la dernière commande
- `p` : Mise en pause ou reprise de la partie

Le menu `Option` permet aussi de mettre en pause, réinitialiser la partie ou quitter l'application.
Le menu `Help`contient les mêmes commandes de jeu que ci-dessus.

## Fonctionnement général
Au démarrage, la fenêtre principale affiche
1. Une barre d'outil pour choisir la sélection ou le stylo.
2. Une zone de dessin qui nous permet de dessiner afin de bloquer les ennemis.
3. Une barre d'état qui indique le niveau de l'encre, la vague actuelle et le temps écoulé.
4. Une marge contenant les améliorations s'appliquant à la partie en cours

Les murs qui sont dessinés consomment de l'encre proportionnellement à la longueur du trait. Les améliorations
permettent d'augmenter le conteneur d'encre, la taille de la zone de dessin, d'augmenter les pv max du chateau
ou encore de recharger complètement la barre d'encre.

Les différentes couleurs permettent d'arrêter les différents ennemis qui arrivent au fur et à mesure de la partie.
Plus on avance et plus les ennemis sont variés (couleur, vitesse, trajectoire), il faut adapter la couleur du stylo
à la couleur de l'ennemi afin de le détruire. Un ennemi tué rapport de l'argent pour devenir plus fort.