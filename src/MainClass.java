import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import parser.IsiLanguageLexer;
import parser.IsiLanguageParser;

public class MainClass {
	public static void main(String[] args) {
		try {
			var lexer = new IsiLanguageLexer(CharStreams.fromFileName("input.isi"));
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			var parser = new IsiLanguageParser(tokenStream);
			parser.init();
			System.out.println("Starting Expression Analysis");
			parser.programa();
			System.out.println("Compilation Successful! Good Job");
			System.out.println("-----------------------------");
			parser.exibirID();
			System.out.println("------- TARGET --------------");
			parser.generateObjectCode();
			parser.runInterpreter();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
