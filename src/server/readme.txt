Requirements:
tika-app-2.1.0.jar

download at :
https://tika.apache.org/download.html

compilation :
javac -Xlint -cp tika-app-2.1.0.jar *.java

execution :
javac -Xlint -cp tika-app-2.1.0.jar:. ParserMain 
java -cp tika-app-2.1.0.jar:. ParserMain "cv01.pdf" "cv02.pdf" "cv100.pdf" "cv101.pdf"




TODO:
injecter les JSON généré par le parser dans elsticsearch
Les fichiers sont dans cv_api_daar/src/server/data/cv_json/
