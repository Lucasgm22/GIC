package ast;

import symbols.Identifier;

public class CmdAttribString extends AbstractCommand{

    private Identifier id;

    private String content;

    public CmdAttribString(Identifier id, String content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public String generateCode() {
        return id.getText() + "=" + content + ";\n";
    }

    @Override
    public void run() {
        id.setValueText(content);
    }
}
