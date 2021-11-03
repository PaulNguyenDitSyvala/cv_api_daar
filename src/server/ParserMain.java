

public class ParserMain {

	public static void main(String[] args){
		System.out.println(args[0]);
		if (args.length == 0){
			System.out.println("Error: missing arguments in Parser main package."
				+ " Please add filenames from /server/data/cv/");
			return;
		}
		else if (args.length >= 1){
			Parser p = new Parser("");
			for (int i = 0; i < args.length; i++){
				p.setNewFile(args[i]);
				p.makeJSON();
			}
		}
		return;
	}
}