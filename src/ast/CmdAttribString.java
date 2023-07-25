package ast;

import symbols.Identifier;
import util.StringUtil;

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
        return StringUtil.indentationByTarget(getIndentation(), TargetLang.JS) +
                id.getText() + " = " + content + ";\n";
    }

    @Override
    public String generateJavaCode() {
        return StringUtil.indentationByTarget(getIndentation(), TargetLang.JAVA) +
                id.getText() + " = " + content + ";\n";
    }

    @Override
    public String generateCCode() {
        return StringUtil.indentationByTarget(getIndentation(), TargetLang.C) +
                "strcpy(" + id.getText() + ", " + content +");\n";
    }

    @Override
    public void run() {
        id.setValueText(content);
    }
}
