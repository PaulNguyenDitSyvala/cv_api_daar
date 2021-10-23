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



**Pour lancer une instance ElasticSearch :**

`sudo docker-compose up`


**Pour lancer l'API Spring Boot :**

`java -jar spring_boot_api/target/cv-search-0.0.1-SNAPSHOT.jar`


**Pour supprimer les données de l'instance ElasticSearch :**

`sudo docker-compose down`

`sudo docker volume rm cv_api_daar_data01`






json-simple-1.1.1.jar
https://code.google.com/archive/p/json-simple/downloads