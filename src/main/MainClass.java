package main;

import ast.ProgramMode;
import ast.TargetLang;
import exception.IsiSemanticException;
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
			System.out.println("Starting Expression Analysis");
			parser.init();
			parser.programa();
			System.out.println("Compilation Successful! Good Job");

			if (mode == ProgramMode.C) {
				var target = TargetLang.valueOf(args[2]);
				parser.generateObjectCode(inputName, target);
				parser.getUnassignedIdentifiers().forEach(ui ->
						System.out.println("WARNING - Identifier '" + ui.getText() + "' declared but not assigned."));
			} else if (mode == ProgramMode.I) {
				try {
					parser.runInterpreter();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					parser.stopInterpreter();
				}
			}
		}
		//TODO: NoSuchFileException
		catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Usage: pass the arguments in command Line");
			System.out.println("<INPUT> (<I> | (<C> (<C|JAVA|JS>))");
		}
		catch (IsiSemanticException ex) {
			System.err.println("Compilation Failed!");
			System.err.println(ex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
