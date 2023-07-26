package ast;

import expressions.BinaryRelationalExpression;

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
        var indentation = getIndentationByTarget(TargetLang.JS);
        StringBuilder cmds = new StringBuilder();

        for (AbstractCommand cmd: listCommands) {
            cmds.append(cmd.generateJSCode());
        }

        return indentation + "while (" + bRelational +") {\n"
                + cmds + indentation + "}\n";
    }

    @Override
    public String generateJavaCode() {
        var indentation = getIndentationByTarget(TargetLang.JAVA);
        StringBuilder cmds = new StringBuilder();

        for (AbstractCommand cmd: listCommands) {
            cmds.append(cmd.generateJavaCode());
        }

        return indentation + "while (" + bRelational +") {\n"
                + cmds + indentation + "}\n";
    }

    @Override
    public String generateCCode() {
        var indentation = getIndentationByTarget(TargetLang.C);
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
