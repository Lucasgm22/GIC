package ast;

import expressions.AbstractExpression;
import expressions.ExpressionTree;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class CmdIf extends AbstractCommand {

    private ExpressionTree expressionLeft;
    private ExpressionTree expressionRight;
    private String operator;

    private List<AbstractCommand> listTrue = new ArrayList<>();
    private List<AbstractCommand> listFalse = new ArrayList<>();

    public CmdIf(int indentation,
                    ExpressionTree expressionLeft,
                    ExpressionTree expressionRight,
                    String operator) {
        super(indentation);
        this.expressionLeft = expressionLeft;
        this.expressionRight = expressionRight;
        this.operator = operator;
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
            elseblock = " else {\n" + cmdFalse + indentation +" }\n";
        }
        return indentation
                + "if (" + expressionLeft + operator + expressionRight + ") {\n"
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
            elseblock = " else {\n" + cmdFalse + indentation +" }\n";
        }
        return indentation
                + "if (" + expressionLeft + operator + expressionRight + ") {\n"
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
            elseblock = " else {\n" + cmdFalse + indentation +" }\n";
        }
        return indentation
                + "if (" + expressionLeft + operator + expressionRight + ") {\n"
                + cmdTrue
                + indentation
                + "}" + elseblock;
    }

    @Override
    public void run() {
        var condition = switch (operator) {
            case "<" -> expressionLeft.eval() < expressionRight.eval();
            case "<=" -> expressionLeft.eval() <= expressionRight.eval();
            case ">" -> expressionLeft.eval() > expressionRight.eval();
            case ">=" -> expressionLeft.eval() >= expressionRight.eval();
            case "==" -> expressionLeft.eval() == expressionRight.eval();
            case "<>" -> expressionLeft.eval() != expressionRight.eval();
            default -> throw new RuntimeException("unknown rel operator");
        };
        if (condition) {
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
