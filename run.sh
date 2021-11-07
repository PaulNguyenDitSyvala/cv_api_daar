#!/bin/bash
# script to run the whole project

#running elastic search using Docker
echo "Launching Docker containers requires sudo privileges." 
echo "Please enter password when prompted"
echo ""
echo "Launching elasticsearch container using Docker :"
sudo gnome-terminal --geometry 80x22+1000+0 -- /bin/sh -c 'sudo docker-compose down; sudo docker volume rm cv_api_daar_data01; sudo docker-compose up elasticsearch'
echo "- Wait for the elasticsearch container to be launched"
echo ""
echo "(The terminal will display a message of this format : "
echo " elasticsearch | {...... \"cluster.uuid\": ... , \"node.id\": ...  })"
echo ""
echo "- Press enter here once the message appears to continue"
read -r a
echo ""

#packaging springboot API
echo "Packaging springboot API :"
echo ""
cd spring_boot_api
mvn package
cd ..

#running springboot API
echo "Launching springboot API : "
gnome-terminal --geometry 80x22+1050+100 -- /bin/sh -c 'java -jar ./spring_boot_api/target/cv-search-0.0.1-SNAPSHOT.jar'
echo "- Wait for the springboot api to be launched"
echo ""
echo "(The terminal will display a message of this format : "
echo " 00:00:00.000 [main] INFO  com.su.cvsearch.CvSearchApplication - Started CvSearchApplication in XX.XXX seconds (JVM running for XX.XXX))"
echo ""
echo "- Press enter here once the message appears to continue"
read -r a
echo ""

#running script to import CVs into elasticsearch
echo "Importing CVs into elasticsearch : "
echo ""
cd ./server
./upload_pdf.sh

#running front-end application to search CVs and upload more CVs
#cd ../..
#cd vuejs-frontend
#gnome-terminal --geometry 80x22+400+0 -- /bin/sh -c 'npm run serve'
#xdg-open "http://localhost:6058" &


#running front-end application to search CVs and upload more CVs using Docker
cd ..
echo "Launching Front-end container using Docker :"
sudo gnome-terminal --geometry 80x22+1100+150 -- /bin/sh -c 'sudo docker-compose up --build vue'
echo "- Wait for the front-end container to be launched"
echo ""
echo "(The terminal will display a message of this format : "
echo " vue              |   -  App running at:"
echo " vue              |   - Local:   http://localhost:6058/ )"
echo ""
echo "- Press enter here once the message appears to continue"
read -r a

echo "Opening http://localhost:6058 on web browser" 
xdg-open "http://localhost:6058" &
echo ""

#cd vuejs-frontend
#gnome-terminal --geometry 80x22+400+0 -- /bin/sh -c 'npm run serve'
#xdg-open "http://localhost:6058" &

#running Kibana
echo "Launching Kibana container using Docker :"
sudo gnome-terminal --geometry 80x22+1150+200 -- /bin/sh -c 'sudo docker-compose up kibana'
echo "- Wait for the Kibana container to be launched"
echo ""
echo "(The terminal will display a message of this format : "
echo " kibana | {\"type\": ... \"message\":\"Kibana is now available... )"
echo ""
echo "- Press enter here once the message appears to continue"
read -r a

echo "Opening http://localhost:5601 on web browser"
xdg-open "http://localhost:5601" &

#running Logstash
#gnome-terminal --geometry 80x22+900+250 -- /bin/sh -c 'sudo docker-compose up logstash'

echo ""
echo "end of script"
echo ""
