package ast;

import expressions.BinaryRelationalExpression;

import java.util.List;

public class CmdDoWhile extends AbstractCommand{

    private BinaryRelationalExpression bRelational;
    private List<AbstractCommand> listCommands;
    public CmdDoWhile(int indentation, BinaryRelationalExpression bRelational, List<AbstractCommand> commands) {
        super(indentation);
        this.bRelational = bRelational;
        this.listCommands = commands;
    }

    @Override
    public String generateJSCode() {
        var indentation = getIndentationByTarget(TargetLang.JS);
        StringBuilder cmds = new StringBuilder();

        for (AbstractCommand cmd: listCommands) {
            cmds.append(cmd.generateJSCode());
        }

        return indentation + "do {\n"
                + cmds + indentation + "} while ("+  bRelational  +");\n";
    }

    @Override
    public String generateJavaCode() {
        var indentation = getIndentationByTarget(TargetLang.JAVA);
        StringBuilder cmds = new StringBuilder();

        for (AbstractCommand cmd: listCommands) {
            cmds.append(cmd.generateJavaCode());
        }

        return indentation + "do {\n"
                + cmds + indentation + "} while ("+  bRelational  +");\n";
    }

    @Override
    public String generateCCode() {
        var indentation = getIndentationByTarget(TargetLang.C);
        StringBuilder cmds = new StringBuilder();

        for (AbstractCommand cmd: listCommands) {
            cmds.append(cmd.generateCCode());
        }

        return indentation + "do {\n"
                + cmds + indentation + "} while ("+  bRelational  +");\n";
    }

    @Override
    public void run(Program program) {
        do {
            listCommands.forEach(cmd -> cmd.run(program));
        } while (bRelational.eval());
    }
}
