// Java Program to Extract Content from a PDF

// Importing java input/output classes
import java.io.File;
import java.io.FileInputStream;
// Importing Apache POI classes
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;

import java.lang.StringBuilder;	
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

import java.util.ArrayList;

// Class
public class Parser {

	// Main driver method
	public static void main(String[] args) throws Exception
	{
		// PARAMETERS
		boolean verbose = false;
		String inputDirectory = "../data/cv/";
		String inputFile = "cv02.pdf";
		String inputFullPath = (inputDirectory + inputFile);
		String outputDirectory = "../data/cv_json/";
		String content = "";


		// ** STEP 1 : PARSING THE CV **

		// Create a content handler
		try{
		BodyContentHandler contenthandler
			= new BodyContentHandler();

		// Create a file in local directory
		if (verbose){
			System.out.println(inputFullPath);
		}
		File f = new File(inputFullPath);

		// Create a file input stream
		// on specified path with the created file
		FileInputStream fstream = new FileInputStream(f);

		// Create an object of type Metadata to use
		Metadata data = new Metadata();

		// Create a context parser for the pdf document
		ParseContext context = new ParseContext();

		// PDF document can be parsed using the PDFparser
		// class
		PDFParser pdfparser = new PDFParser();

		// Method parse invoked on PDFParser class
		pdfparser.parse(fstream, contenthandler, data,
						context);

		// filtering the content
		content = contenthandler.toString();
		content = content.toLowerCase();
		String[] rawWords = content.split(" ");
		
		ArrayList<String> words = new ArrayList<String>();
		String regex = "^\\s+$";
		for (String rawWord : rawWords){
			if (rawWord.matches(regex)){
				continue;
			}
			else{
				words.add(rawWord);	
			}
		}
		for (String word : words) System.out.println(word);


		if (verbose){
			System.out.println("Extracting contents :"
						+ content);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}


		// ** STEP 2 : create JSON with the content **

		StringBuilder sb = new StringBuilder(100);

		sb.append("{\n");
		sb.append("\"filename\":\""+inputFile+"\"");
		sb.append(",\n");
		sb.append("\"content\":\""+content+"\"");
		sb.append("\n}");
		

		try
		{
			FileWriter myWriter = new FileWriter(outputDirectory+"["+inputFile+"].txt");
			myWriter.write(sb.toString());
			myWriter.close();
			if (verbose){
				System.out.println("Successfully wrote to the file.");
			}
		}
		catch (IOException e)
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}


		
	}
}
