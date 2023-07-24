package ast;

import symbols.Identifier;

public class CmdDecl extends AbstractCommand {

    private final Identifier id;

    public  CmdDecl(Identifier id, int indentation) {
        super(indentation);
        this.id = id;
    }
    @Override
    public String generateJSCode() {
        return "";
    }

    @Override
    public String generateJavaCode() {
        String content = switch (id.getType()) {
            case TEXT -> "String " + id.getText() + ";\n";
            case REAL -> "double " + id.getText() + ";\n";
            case INTEGER -> "int " + id.getText() + ";\n";
        };
        return super.generateJavaCode().concat(content);
    }

    @Override
    public void run() {}
}
