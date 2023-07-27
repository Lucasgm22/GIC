package ast;

import expressions.BinaryRelationalExpression;

import java.util.ArrayList;
import java.util.List;

public class CmdIf extends AbstractCommand {

    private final BinaryRelationalExpression bExpression;

    private List<AbstractCommand> listTrue = new ArrayList<>();
    private List<AbstractCommand> listFalse = new ArrayList<>();

    public CmdIf(int indentation,
                 BinaryRelationalExpression bExpression) {
        super(indentation);
        this.bExpression = bExpression;
    }

    @Override
    public String generateJSCode() {
        var indentation = getIndentationByTarget(TargetLang.JS);
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
        var indentation = getIndentationByTarget(TargetLang.JAVA);
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
        var indentation = getIndentationByTarget(TargetLang.C);
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
    public void run(Program program) {
        if (bExpression.eval()) {
            listTrue.forEach(cmd -> cmd.run(program));
        } else {
            listFalse.forEach(cmd -> cmd.run(program));
        }
    }

    public void setListFalse(List<AbstractCommand> listFalse) {
        this.listFalse = listFalse;
    }

    public void setListTrue(List<AbstractCommand> listTrue) {
        this.listTrue = listTrue;
    }
}
