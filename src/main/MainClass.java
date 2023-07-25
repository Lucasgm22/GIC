package main;

import ast.ProgramMode;
import ast.TargetLang;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.IsiLanguageLexer;
import parser.IsiLanguageParser;

import java.util.Objects;

public class MainClass {
	public static void main(String[] args) {
		try {
			int i = args[0].lastIndexOf(".");
			String[] inputSplitExt =  {args[0].substring(0, i), args[0].substring(i)};
			var inputName = inputSplitExt[0];
			var inputExt = inputSplitExt[1];
			if (!Objects.equals(inputExt, ".isi")) {
				throw new RuntimeException("unsupported extension");
			}
			var mode = ProgramMode.valueOf(args[1]);
			var lexer = new IsiLanguageLexer(CharStreams.fromFileName(inputName + inputExt));
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			var parser = new IsiLanguageParser(tokenStream);
			parser.init();
			parser.programa();
			System.out.println("Starting Expression Analysis");
			System.out.println("Compilation Successful! Good Job");
			System.out.println("-----------------------------");
			parser.exibirID();
			System.out.println("------- TARGET --------------");

			if (mode == ProgramMode.C) {
				var target = TargetLang.valueOf(args[2]);
				parser.generateObjectCode(inputName, target);
			} else if (mode == ProgramMode.I) {
				parser.runInterpreter();

			}
		}
		//TODO: NoSuchFileException
		catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Usage: pass the arguments in command Line");
			System.out.println("<INPUT> <C|I> (<C|JAVA|JS>)?");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
