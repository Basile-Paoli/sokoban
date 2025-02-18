# Sokoban

Le [Sokoban](https://en.wikipedia.org/wiki/Sokoban) est un jeu de réflexion dans lequel le joueur doit déplacer toutes les caisses sur leur case de stockage.

## Règles
Le joueur peut se déplacer horizontalement et verticalement mais ne peut traverser les murs ou les caisses

Il peut uniquement pousser les caisses. Et, les caisses ne peuvent pas être poussées sur une case contenant une autre caisse ou un mur.

Le nombre de caisses est égal au nombre de cases de stockage.

Un niveau est résolu quand toutes les caisses sont sur une case de stockage.

Un niveau est défini par un rectangle (W par H) de cases.

Chaque case a un état :
- Mur : rien ne peut être déplacé sur cette case
- Sol : une case libre sur laquelle le joueur ou une caisse peut être déplacé
- Joueur : la position actuelle du joueur
- Caisse : une caisse à déplacer vers une case de stockage
- Case de stockage : une case de sol marquée comme la destination pour une caisse
- Caisse Stockée : une caisse se trouvant sur une case de stockage

1. La carte

** Chaque classe de cette partie doit être implémentée dans le package `com.gitlab.sokoban.domain.model`. **

### 1.1. Positions et cases (3 points)

Une position est définie par deux entiers X et Y. Ces coordonnées ne peuvent changer une fois une position créée

Une case est définie par une position et un état :

- Player : le joueur se trouve sur cette case
- Wall : cette case contient un mur
- Empty : cette case est vide (sol)
- Box : une caisse est sur cette case
- Storage : cette case est un lieu de stockage
- BoxStored : cette case est un lieu de stockage et une caisse est dessus

Une case ne peut être modifiée une fois créée

1. Créer un enum `State` pour représenter l'état d'une case
2. Créer une classe `Position` pour modéliser une position et `Tile` pour modéliser une case
3. Deux positions doivent être considérées comme égales si leur Xs et Ys sont égaux. Créer les tests qui valident ce comportement
4. Deux cases doivent être considérées comme égales si leurs positions et leurs états sont égaux. Créer les tests qui valident ce comportement

### 1.2. La carte (3 points)

Une carte contient une liste de cases.

Une carte peut dire :
- si la case à une position donnée est un mur ou non
- si une position donnée est dans la carte ou non. Par exemple, une carte de 4x4, la position 12;34 n'est pas dans la carte. Mais la position 2;2 est dans la carte

1. Créer la classe `Map`. Une carte est créée à partir d'une taille et une liste de cases
2. Créer la méthode `isWall`. Elle retourne True si la position donnée est un mur. Faux, sinon.  Créer les tests qui valident ce comportement
3. Créer la méthode `inside`. Elle retourne True si la position donnée est dans la carte. Faux, sinon. Créer les tests qui valident ce comportement
4. Deux cartes sont égales si leur taille et leurs cases sont égales. Créer les tests qui valident ce comportement

### 1.3. Le générateur de cartes (4 points)

Une carte est générée à partir d'un fichier texte contenant l'état de chaque case. Par exemple :

```
  01234567890123456789
0     #####
1     #   #
2     #$  #
3   ###  $##
4   #  $ $ #
5 ### # ## #   ######
6 #   # ## #####  ..#
7 # $  $          ..#
8 ##### ### #@##  ..#
9     #     #########
0     #######
```

`#` : Mur
`$` : caisse
`.` : Case de stockage
`@` : Joueur
` ` : Sol

Dans un premier temps, nous allons nous concentrer sur la création de la carte (sol et murs)

1. Créer une classe `MapBuilder` qui sera utilisée pour convertir une chaine de caractères (string) en une carte `Map`
2. Créer une méthode statique `toTiles` qui prend une `string` en paramètres et retourne une liste de case `Tile`
3. Créer une méthode statique `build` qui prend une `String` en paramètre et retourne une `Map`

Indice :
La `String` contient plusieurs lignes.
En parcourant la `String` ligne par ligne, on peut en déduire la valeur de Y. Puis caractère par caractère, on peut en déduire la valeur de X de chaque case.

Par exemple, le premier '$' est à la position [5;2] (3ème ligne, 6ème caractère sur la ligne)


## 2. Le jeu

Maintenant que nous avons notre carte, nous pouvons l'afficher

### 2.1. Sokoban (1 point)

Une partie est représentée par un objet de la classe `Sokoban` qui contient, la carte, la position du joueur, les caisses, etc.

1. Ajouter les attributs :
   - `map` qui représente la carte actuelle
   - `storages` qui représente la liste des positions des lieux de stockage
   - ajouter une méthode `getTiles()` qui retourne une liste de `Tile` à partir des informations (map, player, storage, etc.)
     - Attention : il ne faut surtout pas de doublons (i.e. deux `Tile` à la même position)

### 2.2. Construire le Sokoban (2 points)

Nous avons besoin d'accéder la partie en cours pour l'afficher. La partie actuelle peut être sauvegardée dans une base de données, un fichier, directement en mémoire, etc.
Et, le code doit être agnostique de la façon dont on stocke les données.

1. Créer une interface `GameResources` dans le package `domain.features` et ajouter une méthode `get` qui retourne un `Sokoban`
2. Remplir la classe `InMemoryGameResources` en implémentant `GameResources`. La méthode `get` doit retourner un objet `Sokoban` généré à partir de la carte décrite au chapitre 1.2 et de la classe `MapBuilder`.
3. Dans la classe `FrontDispatcher`, retirer les méthodes `index` et `move`. Décommenter l'implémentation. Décommenter également `SokobanDTO`
4. Lancer l'application et ouvrir l'URL `localhost:8080` dans un navigateur. Le jeu doit s'afficher sans erreurs


### 2.3. Règles (4 points)

Maintenant, il faut implémenter les règles dans la classe `Sokoban`

1. Ajouter un attribut `player` qui représente la position actuelle du joueur
2. Ajouter une méthode `Sokoban.move()` qui prend en paramètre une direction (créer un enum pour ça) et déplace le joueur dans la direction donnée
3. Ajouter la règle *le joueur ne peut se déplacer sur un mur*
4. Ajouter un attribut `boxes` qui contient la liste des positions des caisses sur la carte
5. Ajouter la règle : *le joueur peut pousser une caisse*. La caisse doit changer de position
6. Ajouter la règle : *le joueur ne peut pas pousser une caisse qui est contre un mur*


### 2.4. Jouer (4 points)

1. Ajouter un attribut `GameResources` à la classe `Game` qui se trouve dans `com.gitlab.domain.features`
   - Cet attribut nous servira à récupérer la partie courante et la mettre à jour
2. Implémenter la méthode `Game.current()` qui retourne la partie courante stockée dans `GameResources`
3. Ajouter une méthode `Game.execute` qui prend en paramètre une direction et retourne la partie `Sokoban` mise à jour
4. Implémenter la méthode `Game.execute()` comme suit :
- Récupérer le Sokoban dans la source de données `GameResources`
- Mettre à jour le Sokoban avec le déplacement passé en paramètre
- Stocker le Sokoban à jour dans la source de données

Lancer le jeu. Le joueur doit pouvoir se déplacer et pousser les caisses correctement.
