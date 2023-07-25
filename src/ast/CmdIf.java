package ast;

import expressions.AbstractExpression;
import expressions.BinaryRelationalExpression;
import expressions.ExpressionTree;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class CmdIf extends AbstractCommand {

    private BinaryRelationalExpression bExpression;

    private List<AbstractCommand> listTrue = new ArrayList<>();
    private List<AbstractCommand> listFalse = new ArrayList<>();

    public CmdIf(int indentation,
                    BinaryRelationalExpression bExpression
                 ) {
        super(indentation);
        this.bExpression = bExpression;
    }

    @Override
    public String generateJSCode() {
        var indentation = StringUtil.indentationByTarget(getIndentation(), TargetLang.JS);
        StringBuilder cmdTrue = new StringBuilder();
        StringBuilder cmdFalse = new StringBuilder();
        for (AbstractCommand cmd : listTrue) {
            cmdTrue.append(cmd.generateJSCode());
        }
        for (AbstractCommand cmd : listFalse) {
            cmdFalse.append(cmd.generateJSCode());
        }
        String elseblock = "\n";
        if (!cmdFalse.isEmpty()) {
            elseblock = " else {\n" + cmdFalse + indentation +"}\n";
        }
        return indentation
                + "if (" + bExpression + ") {\n"
                + cmdTrue
                + indentation
                + "}" + elseblock;
    }

    @Override
    public String generateJavaCode() {
        var indentation = StringUtil.indentationByTarget(getIndentation(), TargetLang.JAVA);
        StringBuilder cmdTrue = new StringBuilder();
        StringBuilder cmdFalse = new StringBuilder();
        for (AbstractCommand cmd : listTrue) {
            cmdTrue.append(cmd.generateJavaCode());
        }
        for (AbstractCommand cmd : listFalse) {
            cmdFalse.append(cmd.generateJavaCode());
        }
        String elseblock = "\n";
        if (!cmdFalse.isEmpty()) {
            elseblock = " else {\n" + cmdFalse + indentation +"}\n";
        }
        return indentation
                + "if (" + bExpression + ") {\n"
                + cmdTrue
                + indentation
                + "}" + elseblock;
    }

    @Override
    public String generateCCode() {
        var indentation = StringUtil.indentationByTarget(getIndentation(), TargetLang.C);
        StringBuilder cmdTrue = new StringBuilder();
        StringBuilder cmdFalse = new StringBuilder();
        for (AbstractCommand cmd : listTrue) {
            cmdTrue.append(cmd.generateCCode());
        }
        for (AbstractCommand cmd : listFalse) {
            cmdFalse.append(cmd.generateCCode());
        }
        String elseblock = "\n";
        if (!cmdFalse.isEmpty()) {
            elseblock = " else {\n" + cmdFalse + indentation +"}\n";
        }
        return indentation
                + "if (" + bExpression + ") {\n"
                + cmdTrue
                + indentation
                + "}" + elseblock;
    }

    @Override
    public void run() {
        if (bExpression.eval()) {
            listTrue.forEach(AbstractCommand::run);
        } else {
            listFalse.forEach(AbstractCommand::run);
        }
    }

    public void setListFalse(List<AbstractCommand> listFalse) {
        this.listFalse = listFalse;
    }

    public void setListTrue(List<AbstractCommand> listTrue) {
        this.listTrue = listTrue;
    }
}
