

public class Main {

	public static void main(String[] args){
		String f = "cv02.pdf";
		Parser p = new Parser(f);
		p.makeJSON();
	}
}