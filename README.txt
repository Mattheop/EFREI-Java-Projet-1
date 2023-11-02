Projet réalisé par Matthéo PERELLE et Aristote ROULLOT
Projet EFREI L3-APP-LSI2 2023

Le projet utilise maven pour la gestion des dépendances et la compilation.
Pour se connecter a la base de données, il faut modifier le fichier src/main/resources/database.properties

Un fichier de configuration d'exemple est disponible dans le dossier src/main/resources/database.properties.example
Il suffit de le renommer en database.properties et de modifier les paramètres de connexion à la base de données.

Pour ce qui est du schema de la base de données MySQL, il est disponible dans le dossier sql/1-init.sql
Un jeu de données de test est disponible dans le dossier sql/2-mock.sql

Pour lancer le projet, soit il y a la classe src/Main.java qui permet de lancer le projet en mode console,
soit il y a la classe src/ui/UIMainForm.java qui permet de lancer le projet en mode interface.


Quelques fonctionnalités de l'application supplémentaires :
- La base de donnée est dockerisée, il suffit de lancer la commande `docker-compose up` pour lancer la base de données.
  Elle sera automatiquement configurée avec les données de test.

- L'application comporte un fichier de configuration qui permet de modifier les paramètres de connexion à la base de données.
  Ce fichier est situé dans le dossier src/main/resources/database.properties

- L'application comporte une recherche par nom/prenom en mode CLI

- L'application comporte une interface permettant de visualiser, modifier, supprimer, ajouter des personnes.

- L'application comporte des statistiques : nombre de personnes salaire et bonus moyen, age moyen

- L'application comporte des tests unitaires avec une base de données in-memory et donc indépendante de la base de données réelle.

Pour la gestion de projet, nous avons utilisé Git et communiqué durant les séances de cours et par messages pour se répartir les tâches.