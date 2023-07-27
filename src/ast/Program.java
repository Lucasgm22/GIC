package ast;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import symbols.Identifier;
import symbols.SymbolTable;

public class Program {

    private List<AbstractCommand> comandos;
    private SymbolTable symbolTable;

    RuntimeEntity runtime;

    public Program() {
        this.comandos = new ArrayList<>();
    }

    public void generateTarget(String filename, TargetLang target) {
        if (target == TargetLang.ALL) {
            throw new IllegalStateException("ALL does not generate code");
        }
        try {
            var extension = generateExtension(target);
            writeOutPutFile(filename, target, extension);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void writeOutPutFile(String filename, TargetLang target, String extension) {
        try (var fw = new FileWriter(filename + extension)) {
            try (var pw = new PrintWriter(fw)) {
                StringBuilder strBuilder = new StringBuilder();
                generateHeader(target, strBuilder);
                comandos.forEach(c -> strBuilder.append(c.generateCode(target)));
                generateFooter(target, strBuilder);
                pw.println(strBuilder);
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
            case ALL -> throw new IllegalStateException("ALL does not generate code");
        };
    }

    private void generateFooter(TargetLang target, StringBuilder strBuilder) {
        if (target == TargetLang.JAVA) {
            strBuilder.append("    }\n");
            strBuilder.append("}\n");
        } else if (target == TargetLang.C) {
            strBuilder.append("}\n");
        }
    }

    private void generateHeader(TargetLang target, StringBuilder strBuilder) {
        if (target == TargetLang.JAVA) {
            strBuilder.append("import java.util.Scanner;\n\n");
            strBuilder.append("public class Main {\n");
            strBuilder.append("    public static void main(String[] args) {\n");
            strBuilder.append("        Scanner sc = new Scanner(System.in);\n");
        } else if (target == TargetLang.C) {
            strBuilder.append("#include <stdio.h>\n");
            strBuilder.append("#include <string.h>\n\n");
            strBuilder.append("int main() {\n");
        }
    }


    public List<AbstractCommand> getComandos() {
        return comandos;
    }

    public void setComandos(List<AbstractCommand> comandos) {
        this.comandos = comandos;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void run() {
        runtime = new RuntimeEntity();
        updateContent();
        comandos.forEach(c -> {
            c.run(this);
            updateContent();
        });
    }

    public void updateContent() {
        runtime.updateContent(symbolTable.getSymbols().values());
    }

    public void stop() {
        runtime.close();
    }

    public List<Identifier> getUnassignedIdentifiers() {
        return symbolTable.getUnassignedIdentifiers();
    }

    public List<Identifier> getAssignedUnusedIdentifiers() {
        return symbolTable.getAssignedUnusedIdentifiers();
    }


}
