# cv_api_daar #
University project - CV indexation in ElasticSearch using Spring boot rest API

# Binôme #

Paul NGUYEN DIT SYVALA
3876651
`paul.nguyen_dit_syvala@etu.sorbonne-universite.fr`

Thibault ROCHE
3677376
`thibault.roche.1@etu.sorbonne-universite.fr`


# Cas d'utilisations #

Par la biais d'une page internet:

- l'utilisateur peut upload un CV aux de formats .pdf et .odt (open document)

- l'utilisateur peut entrer un(/des) mots clefs pour effectuer une recherche sur la base de donnée de CVs


# Architecture conçise du programme #

- le lancement du programme et la connexion entre les différents composants est entièrement géré par un script run.sh et à l'aide de dockers.

- Le frontend est en vue.js, localisé dans ./vuejs-frontend/

- le backend est géré en java Spring boot, localisé dans ./spring_boot_api/

- les CVs uploadés sont stockés en plus d'elastic search, dans ./server/data/ sous format brut dans data/cv/ et sous format .json dans data/cv_json/


# Prérequis d'installation #

### Docker ###
Docker et docker-compose sont requis pour lancer l'instance elasticsearch, la console kibana et le front-end vue-JS permettant d'intéragir avec l'API.
   https://docs.docker.com/engine/install/ubuntu/
   https://docs.docker.com/compose/install/

### JVM ###
la JVM 11 au minimum est requise:
   sudo apt install openjdk-11-jdk

### CURL ###
curl pour peupler la base de données des CV dans elasticsearch
   sudo apt install curl
(vérifier la version avec curl --version)

### MAVEN ###
maven pour compiler l'API springboot
   sudo apt install maven
(vérifier la version avec mvn -version)


# Lancement du projet par script (préférable) #

1) Ouvrir le terminal principal dans le root du projet

2) Entrer `./run.sh`

3) Un 1er terminal annexe s'ouvre:
   - correspond au lancement de l'instance elasticsearch.
   - Attendre que le container soit lancé et stable

4) Retour au terminal principal, appuyer sur `Entrée`:
   - Un 2ème terminal annexe s'ouvre
   - correspond à l'API Spring Boot
   - Attendre que le container soit lancé et stable

5) Retour au terminal principal, appuyer sur `Entrée`:
   - le script effectue l'importation des CVs déjà présents sur le server dans elasticsearch
   - Un 3ème terminal annexe s'ouvre
   - correspond au front-end de l'application
   - Attendre que le container soit lancé et stable
   
6) Retour au terminal principal, appuyer sur `Entrée`:
   - Une fenêtre de navigation internet sur la page localhost:6058 s'ouvre
   - Le programme est prêt à être utilisé 
   - Un 4ème terminal annexe s'ouvre
   - correspond à la console Kibana
   - Attendre que le container soit lancé et stable

7) Retour au terminal principal, appuyer sur `Entrée`:
   - Une fenêtre de navigation internet sur la page localhost:5601 s'ouvre
   - La console kibana est prête et permet d'intéragir avec le contenu d'elasticsearch
   
# Lancement du projet manuellement : module par module (alternative au script) #

### Pour lancer une instance ElasticSearch : ###

`sudo docker-compose up elasticsearch`


### Pour lancer l'API Spring Boot : ###

`cd spring_boot_api`
`mvn package`
`cd ..`
`java -jar spring_boot_api/target/cv-search-0.0.1-SNAPSHOT.jar`


### Pour lancer le front end : ###

`sudo docker-compose up --build vue`

### Pour lancer le front end sans docker (apres installation de nodejs, npm, vue) : ###

`cd vuejs-frontend`
`npm run serve`

### Pour lancer la console Kibana : ###

`sudo docker-compose up kibana`

### Pour supprimer/réinitialiser les données de l'instance ElasticSearch : ###

`sudo docker-compose down`

`sudo docker volume rm cv_api_daar_data01`



# dépendances gérées par maven #

**L'utilisateur ne doit pas prendre cette section en compte.**

### PARSER (pour utilisation stadalone avec programme java)###

Le parser qui récolte les informations des CV à partir des formats .pdf et .odt requiert le package java suivant:

tika-app-2.1.0.jar

Téléchargeable ici : https://www.apache.org/dyn/closer.lua/tika/2.1.0/tika-app-2.1.0.jar

Il faut ensuite placer cette archive .jar dans le dossier contenant le main


