package ast;

import symbols.Identifier;

public class CmdDecl extends AbstractCommand {

    private final Identifier id;

    public  CmdDecl(Identifier id, int indentation, int line, int column) {
        super(indentation);
        this.id = id;
        this.id.setLine(line);
        this.id.setColumn(column);
    }
    @Override
    public String generateJSCode() {
        return "";
    }

    @Override
    public String generateJavaCode() {
        var content = switch (id.getType()) {
            case TEXT -> "String " + id.getText() + ";\n";
            case REAL -> "double " + id.getText() + ";\n";
            case INTEGER -> "int " + id.getText() + ";\n";
        };
        return getIndentationByTarget(TargetLang.JAVA) + content;
    }

    @Override
    public String generateCCode() {
        var content = switch (id.getType()) {
            case TEXT -> "char " + id.getText() + "[100];\n";
            case REAL -> "double " + id.getText() + ";\n";
            case INTEGER -> "int " + id.getText() + ";\n";
        };
        return getIndentationByTarget(TargetLang.C) + content;
    }

    @Override
    public void run(Program program) { /* Identifiers are already in the Symbol table (declared) when runtime is called */ }
}
