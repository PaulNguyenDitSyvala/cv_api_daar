

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
import org.apache.commons.lang3.StringUtils;


// Class
public class Parser {

	// PARAMETERS
	private static final boolean verbose = false;


	// VARIABLES
	private final String inputDirectory = "./data/cv/";
	private String inputFile;
	private String inputFullPath;
	private final String outputDirectory = "./data/cv_json/";

	// CONSTRUCTOR
	public Parser(String file){
		this.inputFile = file;
		this.inputFullPath = (this.inputDirectory + this.inputFile);
	}

	// main method from PARSER
	// takes a file and make a JSON with normalized and filtered content
	public void makeJSON(){
		createJSON(normalizeText(parsePDF()));
	}

	// parse the pdf file to Java String
	public String parsePDF(){
		try{
			BodyContentHandler contenthandler
				= new BodyContentHandler();
			if (verbose) System.out.println(inputFullPath);
			
			// create file input stream
			File f = new File(inputFullPath);
			FileInputStream fstream = new FileInputStream(f);

			// Create an object of type Metadata to use
			Metadata data = new Metadata();

			// Create a context parser for the pdf document
			ParseContext context = new ParseContext();

			// PDF document can be parsed using the PDFparser
			PDFParser pdfparser = new PDFParser();
			pdfparser.parse(fstream, contenthandler, data,
							context);
			return contenthandler.toString();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	// normalize and filter the text content from irrelevant data
	public String normalizeText(String content){
		StringBuilder sb = new StringBuilder(100);
		ArrayList<String> words = new ArrayList<String>();
		String regex1 = "^[^a-zA-Z0-9]$";
		String regex2 = "^[0-9]$";
		String regex3 = "^(de|des|et|un|une|en|le|les|j'ai|je)$";

		// normalizing text
		content = content.toLowerCase();
		content = content.replaceAll("[(),:;\"]+", " ");
		content = content.replaceAll("[\\r\\n]+", " ");
		content = StringUtils.normalizeSpace(content);
		
		// filtering text from irrelevant data
		String[] rawWords = content.split(" ");
		for (String rawWord : rawWords){
			if (rawWord.matches(regex1)
				|| rawWord.matches(regex2)
				|| rawWord.matches(regex3)){
				if(verbose) System.out.println("match regex, removing : "+rawWord);
				continue;
			}
			else {
				words.add(rawWord);	
			}
		}
		for (String word : words)
			sb.append(word + " ");
		if (verbose){
			System.out.println("\nNoramlize & filter:");
			System.out.println(sb.toString());
		}
		return sb.toString();
	}


	// create a JSON file with the filename and content
	public void createJSON(String content){
		StringBuilder sb = new StringBuilder(100);

		sb.append("{\n");
		sb.append("\"filename\":\""+inputFile+"\"");
		sb.append(",\n");
		sb.append("\"content\":");
		sb.append("\""+content+"\"");
		sb.append("\n}");
		
		if(verbose) System.out.println("\nCreating this JSON:\n"+sb.toString());

		// write into the outputfile
		try
		{
			FileWriter myWriter = new FileWriter(outputDirectory+inputFile+".json");
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


	// change the targeted inputfile
	public void setNewFile(String file){
		this.inputFile = file;
		this.inputFullPath = (this.inputDirectory + this.inputFile);
	}


}
