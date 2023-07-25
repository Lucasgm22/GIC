package ast;

import symbols.Identifier;

public class CmdAttribString extends AbstractCommand{

    private Identifier id;

    private String content;

    public CmdAttribString(Identifier id, String content, int indentation) {
        super(indentation);
        this.id = id;
        this.content = content;
    }

    @Override
    public String generateJSCode() {
        return id.getText() + " = " + content + ";\n";
    }

    @Override
    public String generateJavaCode() {
        return id.getText() + " = " + content + ";\n";
    }

    @Override
    public String generateCCode() {
        return "strcpy(" + id.getText() + ", " + content +");\n";
    }

    @Override
    public void run() {
        id.setValueText(content);
    }
}
