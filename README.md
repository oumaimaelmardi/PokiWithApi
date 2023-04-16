Projet PokeAPI

Cette application Android utilise l'API PokeAPI pour afficher une liste de Pokémons et permettre à l'utilisateur de cliquer sur un Pokémon pour obtenir des détails 
avec des annimations au niveau de progress bar et la generation d'un couleur pour le background d'image à chaque fois l'utilisateur clique sur un Pokémon
pour afficher ses détails .
 

Fonctionnalités
•	Afficher une liste de Pokémons à partir de l'API PokeAPI
•	Permettre à l'utilisateur de cliquer sur un Pokémon pour afficher ses détails, y compris son nom, son image et ses statistiques(Height ,Weight et Base stats)
•	Animation des progress bars
•	Generation des couleurs dans le background de cardView à chaque l'utilisateur cliquer sur un Pokémon pour afficher ses détails

Technologies utilisées

Android Studio pour le développement de l'application Android
Retrofit pour effectuer des appels réseau à l'API PokeAPI
Gson pour la désérialisation des données JSON renvoyées par l'API PokeAPI
Glide pour le chargement et l'affichage d'images
RecyclerView pour afficher la liste des Pokémons dans une vue de liste déroulante

API PokeAPI

L'API PokeAPI fournit des données sur les Pokémons, y compris leur nom, leur image et leurs statistiques.
Nous avons choisi d'utiliser cette API car elle est bien documentée et facile à utiliser, avec des informations détaillées sur chaque requête et 
une vaste communauté de développeurs qui l'utilisent. En outre, l'API est gratuite et ne nécessite pas d'authentification pour l'utilisation 
de la plupart de ses fonctionnalités.
