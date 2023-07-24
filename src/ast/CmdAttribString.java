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
        return super.generateJSCode() + id.getText() + " = " + content + ";\n";
    }

    @Override
    public String generateJavaCode() {
        return super.generateJavaCode() + id.getText() + " = " + content + ";\n";
    }

    @Override
    public String generateCCode() {
        return super.generateCCode() + "strcpy(" + id.getText() + ", " + content +");\n";
    }

    @Override
    public void run() {
        id.setValueText(content);
    }
}
