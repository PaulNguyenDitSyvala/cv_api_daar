echo "Sending all CV to elasticsearch"
cd ./data/cv
for file in *.pdf; do
  name="${file%.pdf}"
  cd ../..
  echo "sending '$file' to elasticsearch..."
  reply=$(curl -X POST "http://localhost:8080/api/cv" --form cv=@/data/cv/$file)
  #echo "reply : $reply"
  cd ./data/cv
  echo "PDF file sent to elasticsearch :"$file
  count=$(curl -s -X GET "http://localhost:8080/api/cv")
  echo "total of cv inside elasticsearch : $count" 
  echo ""
done

