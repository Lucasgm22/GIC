package main;

import ast.ProgramMode;
import ast.TargetLang;
import exception.IsiSemanticException;
import exception.IsiSyntacticException;
import exception.IsiUnsupportedExtensionException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import parser.IsiLanguageLexer;
import parser.IsiLanguageParser;

import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.BitSet;
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
			parser.removeErrorListeners();
			parser.addErrorListener(new BaseErrorListener() {
				@Override
				public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
					throw new IsiSyntacticException(s, i, i1);
				}
			});
			parser.init();
			compile(parser, mode);
			if (mode == ProgramMode.C) {
				System.out.println("Compilation Successful! Good Job");
				var target = TargetLang.valueOf(args[2]);
				if (target == TargetLang.ALL) {
					Arrays.stream(TargetLang.values())
							.filter(t -> t != TargetLang.ALL)
							.forEach(t -> parser.generateObjectCode(inputName, t));
				} else {
					parser.generateObjectCode(inputName, target);
				}
			} else if (mode == ProgramMode.I) {
				interpret(parser);
			}
			parser.getUnassignedIdentifiers().forEach(ui ->
					System.out.println("WARNING - Identifier '" + ui.getText() +
							"' declared but not assigned at line " + ui.getLine() + " at column " + ui.getColumn() + ".")
			);
			parser.getAssignedUnusedIdentifiers().forEach(ui ->
					System.out.println("WARNING - Identifier '" + ui.getText() +
							"' assigned but never used at line " + ui.getLine() + " at column " + ui.getColumn() + ".")
			);
		} catch (NoSuchFileException ex) {
			System.err.println("ERROR - file " + ex.getFile() + " not found.");
		} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | IllegalArgumentException ex) {
			System.err.println(ex.getMessage());
			System.err.println("Usage: pass the following arguments in command line.");
			System.err.println("<PATH_INPUT> (<I> | (<C> (<C|JAVA|JS|ALL>)).");
			System.err.println("Pass a .isi file in <PATH_INPUT>");
		} catch (IsiSemanticException | IsiSyntacticException ex) {
			System.err.println("Compilation Failed!");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void compile(IsiLanguageParser parser, ProgramMode mode) {
		if (parser == null) {
			throw new IllegalArgumentException("parser not initialized");
		}
		try {
			parser.programa();
		} catch (IsiSemanticException ex) {
			System.err.println(ex.getMessage());
			if (mode != ProgramMode.I) throw ex;
		} catch (IsiSyntacticException ex) {
			System.err.println(ex.getMessage());
			if (mode != ProgramMode.I) throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
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
