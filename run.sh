# script to run the whole project

#running elastic search using Docker
echo "Launching elasticsearch container using Docker :"
echo "- Please enter your password in the newly opened terminal window..."
gnome-terminal --geometry 80x22+1300+0 -- /bin/sh -c 'sudo docker-compose down; sudo docker-compose up elasticsearch'
echo "- Press enter once the elasticsearch container is launched"
read -r a
echo ""

#running springboot API
echo "Launching springboot API : "
gnome-terminal --geometry 80x22+1300+490 -- /bin/sh -c 'java -jar ./spring_boot_api/target/cv-search-0.0.1-SNAPSHOT.jar'
echo "- Press enter once the springboot api is launched"
read -r a
echo ""

#running script to import CVs into elasticsearch
echo "Importing CVs into elasticsearch : "
cd ./src/server
./upload.sh

#running front-end application to search CVs and upload more CVs
cd ../..
pwd
cd vuejs-frontend
pwd
gnome-terminal --geometry 80x22+400+0 -- /bin/sh -c 'npm run serve'
xdg-open "http://localhost:6058" &

#running Kibana
echo "Launching Kibana container using Docker :"
echo "- Please enter your password in the newly opened terminal window..."
gnome-terminal --geometry 80x22+1300+100 -- /bin/sh -c 'sudo docker-compose up kibana'
xdg-open "http://localhost:5601" &

#running Logstash
#gnome-terminal --geometry 80x22+1300+200 -- /bin/sh -c 'sudo docker-compose up logstash'

echo "
