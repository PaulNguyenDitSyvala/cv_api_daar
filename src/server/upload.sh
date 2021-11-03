cd ./data/cv_json
ls
echo ""
for file in *.json; do
  #pwd
  name="${file%.json}"
  cd ../..
  echo "sending '$file' to elasticsearch..."
  reply=$(curl -s -X POST "http://localhost:8080/api/cv" -H "Content-Type: application/json" -d @data/cv_json/$file)
  echo "reply : $reply"
  currentPath="./data/cv/"$file
  echo "file path : $currentPath"
  #pwd
  #run command for creating json here
  
  # 
  cd ./data/cv_json
  echo "JSON file sent to elasticsearch :"$file
  count=$(curl -s -X GET "http://localhost:8080/api/cv")
  echo "total of cv inside elasticsearch : $count" 
  echo ""
done

