# cv_api_daar
University project - CV indexation in ElasticSearch using Spring boot rest API

Pour lancer une instance ElasticSearch : 

`sudo docker-compose up`


Pour lancer l'API Spring Boot : 

`java -jar spring_boot_api/target/cv-search-0.0.1-SNAPSHOT.jar`


Pour supprimer les données de l'instance ElasticSearch : 

`sudo docker-compose down`

`sudo docker volume rm cv_api_daar_data01`
