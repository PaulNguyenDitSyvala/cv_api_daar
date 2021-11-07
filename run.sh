# script to run the whole project

#running elastic search using Docker
echo "Launching elasticsearch container using Docker :"
echo "- Please enter your password in the newly opened terminal window..."
gnome-terminal --geometry 80x22+700+0 -- /bin/sh -c 'sudo docker-compose down; sudo docker-compose up elasticsearch'
echo "- Press enter once the elasticsearch container is launched"
read -r a
echo ""

#running springboot API
echo "Launching springboot API : "
gnome-terminal --geometry 80x22+750+100 -- /bin/sh -c 'java -jar ./spring_boot_api/target/cv-search-0.0.1-SNAPSHOT.jar'
echo "- Press enter once the springboot api is launched"
read -r a
echo ""

#running script to import CVs into elasticsearch
echo "Importing CVs into elasticsearch : "
cd ./src/server
./upload_pdf.sh

#running front-end application to search CVs and upload more CVs
#cd ../..
#cd vuejs-frontend
#gnome-terminal --geometry 80x22+400+0 -- /bin/sh -c 'npm run serve'
#xdg-open "http://localhost:6058" &


#running front-end application to search CVs and upload more CVs using Docker
cd ../..
echo "Launching Front-end container using Docker :"
echo "- Please enter your password in the newly opened terminal window..."
gnome-terminal --geometry 80x22+800+150 -- /bin/sh -c 'sudo docker-compose up vue'
echo "- Press enter once the container is launched"
read -r a
xdg-open "http://localhost:6058" &
echo ""

#cd vuejs-frontend
#gnome-terminal --geometry 80x22+400+0 -- /bin/sh -c 'npm run serve'
#xdg-open "http://localhost:6058" &

#running Kibana
echo "Launching Kibana container using Docker :"
echo "- Please enter your password in the newly opened terminal window..."
gnome-terminal --geometry 80x22+850+200 -- /bin/sh -c 'sudo docker-compose up kibana'
echo "- Press enter once the container is launched"
read -r a
xdg-open "http://localhost:5601" &

#running Logstash
#gnome-terminal --geometry 80x22+900+250 -- /bin/sh -c 'sudo docker-compose up logstash'

echo ""
