# cv_api_daar
University project - CV indexation in ElasticSearch using Spring boot rest API

**Binôme**

Paul NGUYEN DIT SYVALA
3876651
paul.nguyen_dit_syvala@etu.sorbonne-universite.fr

Thibault ROCHE
3677376
thibault.roche.1@etu.sorbonne-universite.fr


**Prérequis d'installation**
Le parser qui récolte les informations des CV requiert le package java suivant:
tika-app-2.1.0.jar

Téléchargeable ici : https://tika.apache.org/download.html

Il faut ensuite placer cette archive .jar dans
cv_api_daar/src/

la JVM 11 au minimum est requise:
sudo apt install openjdk-11-jdk

curl pour peupler la base de données des CV dans elasticsearch
 sudo apt install curl
(vérifier la version avec curl --version)

maven pour compiler l'API springboot
sudo apt install maven
(vérifier la version avec mvn -version)

**Lancement de tout le projet par script**

1) ouvrir le terminal principal dans le root du projet `/cv_api_daar/`

2) sudo ./run.sh

3) Une première fenêtre de terminal s'ouvre, correspondant au lancement de l'instance elasticsearch
   Attendre que le container docker soit lancé pour ensuite lier l'API Spring.

4) Une fois elasticsearch lancé, retourner sur le terminal principal, et appuyer sur 'Entrée' pour 
   continuer le lancement du projet.

5) Une seconde fenêtre de terminal s'ouvre, correspondant au lancement de l'API Spring Boot.
   Attendre que le container docker soit lancé pour ensuite peupler la base de donnée de CVs existants.

6) Une fois Spring Boot lancé, retourner sur le terminal principal, et appuyer sur 'Entrée' pour 
   continuer le lancement du projet.

7) Le script va ensuite importer les CV présents dans le dossier


**Pour lancer une instance ElasticSearch :**

`sudo docker-compose up elasticsearch`


**Pour lancer l'API Spring Boot :**

`java -jar spring_boot_api/target/cv-search-0.0.1-SNAPSHOT.jar`


**Pour lancer le front end :**




**Pour supprimer les données de l'instance ElasticSearch :**

`sudo docker-compose down`

`sudo docker volume rm cv_api_daar_data01`



