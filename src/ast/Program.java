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
		this.filename = "output.js";
		this.comandos = new ArrayList<>();
	}

	public void generateTarget() {
		try {
			try (var fw = new FileWriter(filename)) {
				try (var pw = new PrintWriter(fw)) {
					StringBuilder strBuilder = new StringBuilder();
					comandos.forEach(c -> {
						System.out.print(c.generateCode());
						strBuilder.append(c.generateCode());
					});
					pw.println(strBuilder.toString());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
