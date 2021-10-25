
public class Main {

	public static void main(String[] args){
		String f1 = "cv01.pdf";
		String f2 = "cv02.pdf";
		Parser p = new Parser(f1);

		p.makeJSON();		// parse f1
		
		p.setNewFile(f2);	// change de cible

		p.makeJSON();		// parse f2
	}
}