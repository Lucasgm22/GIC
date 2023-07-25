package util;

import ast.TargetLang;

public abstract class StringUtil {

    private StringUtil() {
        throw new IllegalStateException("Utility class!");
    }

    private static final String INDENTATION = "    ";

    public static String indentCommand(String command, int indentationLvl, TargetLang targetLang) {
        if (command == null || command.isEmpty()) {
            return "";
        }
        String indentation = "";
        for (int i = 0; i < getIndentationLvlOnTarget(indentationLvl, targetLang); i++) {
            indentation = indentation.concat(INDENTATION);
        }
        return indentation + command;
    }

    private static int getIndentationLvlOnTarget(int indentationLvl, TargetLang target) {
        return switch (target) {
            case JAVA -> indentationLvl + 2;
            case C -> indentationLvl + 1;
            case JS -> indentationLvl;
        };
    }
}
