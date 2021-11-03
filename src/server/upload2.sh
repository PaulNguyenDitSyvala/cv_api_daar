
echo "Compiling Parser..."
javac -Xlint -cp tika-app-2.1.0.jar *.java

echo "Fetching all CVs in server/data/cv/"
files=$(ls ./data/cv | egrep '*.pdf')
echo ${files}

echo "Executing Parser to convert all CVs to .json"
java -cp tika-app-2.1.0.jar:. ParserMain ${files}
