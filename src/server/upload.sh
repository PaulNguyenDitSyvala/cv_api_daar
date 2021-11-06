echo "Compiling Parser..."
javac -Xlint -cp tika-app-2.1.0.jar *.java

echo "Fetching all CVs in server/data/cv/"
files=$(ls ./data/cv | egrep '*.pdf')
echo ${files}

echo "Executing Parser to convert all CVs to .json"
java -cp tika-app-2.1.0.jar:. ParserMain ${files}


echo "Sending all CV(json) to elasticsearch"
cd ./data/cv_json
for file in *.json; do
  name="${file%.json}"
  cd ../..
  echo "sending '$file' to elasticsearch..."
  reply=$(curl -s -X POST "http://localhost:8080/api/cv" -H "Content-Type: application/json" -d @data/cv_json/$file)
  echo "reply : $reply"
  currentPath="./data/cv/"$file
  echo "file path : $currentPath"

  cd ./data/cv_json
  echo "JSON file sent to elasticsearch :"$file
  count=$(curl -s -X GET "http://localhost:8080/api/cv")
  echo "total of cv inside elasticsearch : $count" 
  echo ""
done

