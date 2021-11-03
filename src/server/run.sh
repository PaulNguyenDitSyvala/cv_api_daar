cd ./data/cv
ls
echo ""
for file in *.pdf; do
  #pwd
  name="${file%.pdf}"
  echo "creating json for file '$name'..."
  currentPath="./data/cv/"$file
  echo "file path : $currentPath"
  cd ../..
  #pwd
  #run command for creating json here
  
  # 
  cd ./data/cv
  echo "JSON file created in ./data/cv_json/"$file".json"
  echo ""
done

for file in *.odt; do
  #pwd
  name="${file%.odt}"
  echo "creating json for file '$name'..."
  currentPath="./data/cv/"$file
  echo "file path : $currentPath"
  cd ../..
  #pwd
  #run command for creating json here
  
  # 
  cd ./data/cv
  echo "JSON file created in ./data/cv_json/"$file".json"
  echo ""
done
