import ast.TargetLang;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import parser.IsiLanguageLexer;
import parser.IsiLanguageParser;

import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		try {
			var lexer = new IsiLanguageLexer(CharStreams.fromFileName("input.isi"));
			Scanner sc = new Scanner(System.in);
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			var parser = new IsiLanguageParser(tokenStream);
			parser.init();
			System.out.println("Starting Expression Analysis");
			parser.programa();
			System.out.println("Compilation Successful! Good Job");
			System.out.println("-----------------------------");
			parser.exibirID();
			System.out.println("------- TARGET --------------");
			parser.generateObjectCode(TargetLang.JAVA);
			//parser.runInterpreter();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
