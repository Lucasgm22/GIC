package main;

import ast.ProgramMode;
import ast.TargetLang;
import exception.IsiSemanticException;
import exception.IsiUnsupportedExtensionException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.IsiLanguageLexer;
import parser.IsiLanguageParser;

import java.nio.file.NoSuchFileException;
import java.util.Objects;

public class MainClass {
	public static void main(String[] args) {
		try {
			int i = args[0].lastIndexOf(".");
			String[] inputSplitExt =  {args[0].substring(0, i), args[0].substring(i)};
			var inputName = inputSplitExt[0];
			var inputExt = inputSplitExt[1];
			if (!Objects.equals(inputExt, ".isi")) {
				throw new IsiUnsupportedExtensionException(inputExt);
			}
			var mode = ProgramMode.valueOf(args[1]);
			var lexer = new IsiLanguageLexer(CharStreams.fromFileName(inputName + inputExt));
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			var parser = new IsiLanguageParser(tokenStream);

			parser.init();
			compile(parser);

			if (mode == ProgramMode.C) {
				System.out.println("Compilation Successful! Good Job");
				var target = TargetLang.valueOf(args[2]);
				parser.generateObjectCode(inputName, target);
				parser.getUnassignedIdentifiers().forEach(ui ->
						System.out.println("WARNING - Identifier '" + ui.getText() + "' declared but not assigned.")
				);
			} else if (mode == ProgramMode.I) {
				interpret(parser);
			}
		}
		catch (NoSuchFileException ex) {
			System.err.println("ERROR - file " + ex.getFile() + " not found.");
		}
		catch (ArrayIndexOutOfBoundsException | IllegalArgumentException ex) {
			System.err.println(ex.getMessage());
			System.err.println("Usage: pass the following arguments in command line.");
			System.err.println("<INPUT> (<I> | (<C> (<C|JAVA|JS>)).");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void compile(IsiLanguageParser parser) {
		if (parser == null) {
			throw new IllegalArgumentException("parser not initialized");
		}
		try {
			parser.programa();
		} catch (IsiSemanticException ex) {
			System.err.println("Compilation Failed!");
			System.err.println(ex.getMessage());
		}
	}

	public static void interpret(IsiLanguageParser parser) {
		if (parser == null) {
			throw new IllegalArgumentException("parser not initialized");
		}
		try {
			parser.runInterpreter();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			parser.stopInterpreter();
		}
	}
}
