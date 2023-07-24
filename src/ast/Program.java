package ast;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import symbols.SymbolTable;

public class Program {

	private String filename;
	private List<AbstractCommand> comandos;
	private SymbolTable symbolTable;

	public Program() {
		this.filename = "output";
		this.comandos = new ArrayList<>();
	}

	public void generateTarget(TargetLang target) {
		try {
			var extension = generateExtension(target);
			try (var fw = new FileWriter(filename + extension)) {
				try (var pw = new PrintWriter(fw)) {
					StringBuilder strBuilder = new StringBuilder();
					generateHeader(target, strBuilder);
					comandos.forEach(c -> {
						System.out.print(c.generateCode(target));
						strBuilder.append(c.generateCode(target));
					});
					generateFooter(target, strBuilder);
					pw.println(strBuilder);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private String  generateExtension(TargetLang target) {
		return switch (target) {
			case JAVA -> ".java";
			case JS -> ".js";
			case C -> ".c";
		};
	}

	private void generateFooter(TargetLang target, StringBuilder strBuilder) {
		switch (target) {
			case JAVA -> {
				strBuilder.append("    }\n");
				strBuilder.append("}\n");
			}
			case JS -> {}
			case C -> strBuilder.append("}\n");
		}
	}

	private void generateHeader(TargetLang target, StringBuilder strBuilder) {
		switch (target) {
			case JAVA -> {
				strBuilder.append("import java.util.Scanner;\n\n");
				strBuilder.append("public class Main {\n");
				strBuilder.append("    public static void main(String[] args) {\n");
				strBuilder.append("        Scanner sc = new Scanner(System.in);\n");
			}
			case JS -> {}
			case C -> {
				strBuilder.append("#include <stdio.h>\n");
				strBuilder.append("#include <string.h>\n\n");
				strBuilder.append("int main() {\n");
			}
		}
	}

	public List<AbstractCommand> getComandos() {
		return comandos;
	}

	public void setComandos(List<AbstractCommand> comandos) {
		this.comandos = comandos;
	}

	public SymbolTable getSymbolTable() {
		return symbolTable;
	}

	public void setSymbolTable(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	public void run() {
		RuntimeEntity runtime = new RuntimeEntity();
		runtime.updateContent(symbolTable.getSymbols().values());
		comandos.forEach(c -> {
			c.run();
			runtime.updateContent(symbolTable.getSymbols().values());
		});
		runtime.close();
	}
}
