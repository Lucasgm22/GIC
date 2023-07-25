package ast;

import expressions.BinaryRelationalExpression;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class CmdWhile extends AbstractCommand {

    private BinaryRelationalExpression bRelational;
    private List<AbstractCommand> listCommands = new ArrayList<>();

    public CmdWhile(int indentation, BinaryRelationalExpression bRelational) {
        super(indentation);
        this.bRelational = bRelational;
    }

    @Override
    public String generateJSCode() {
        var indentation = StringUtil.indentationByTarget(getIndentation(), TargetLang.JS);
        StringBuilder cmds = new StringBuilder();

        for (AbstractCommand cmd: listCommands) {
            cmds.append(cmd.generateJSCode());
        }

        return indentation + "while (" + bRelational +") {\n"
                + cmds + indentation + "}\n";
    }

    @Override
    public String generateJavaCode() {
        var indentation = StringUtil.indentationByTarget(getIndentation(), TargetLang.JAVA);
        StringBuilder cmds = new StringBuilder();

        for (AbstractCommand cmd: listCommands) {
            cmds.append(cmd.generateJavaCode());
        }

        return indentation + "while (" + bRelational +") {\n"
                + cmds + indentation + "}\n";
    }

    @Override
    public String generateCCode() {
        var indentation = StringUtil.indentationByTarget(getIndentation(), TargetLang.C);
        StringBuilder cmds = new StringBuilder();

        for (AbstractCommand cmd: listCommands) {
            cmds.append(cmd.generateCCode());
        }

        return indentation + "while (" + bRelational +") {\n"
                + cmds + indentation + "}\n";
    }

    @Override
    public void run() {
        while (bRelational.eval()) {
            listCommands.forEach(AbstractCommand::run);
        }
    }

    public void setListCommands(List<AbstractCommand> listCommands) {
        this.listCommands = listCommands;
    }
}
