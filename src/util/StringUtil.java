package util;

import ast.TargetLang;

public abstract class StringUtil {

    private StringUtil() {
        throw new IllegalStateException("Utility class!");
    }

    private static final String INDENTATION = "    ";

    public static String indentationByTarget(int indentationLvl, TargetLang targetLang) {
        return INDENTATION.repeat(indentationLvl + getDefaultIndentation(targetLang));
    }

    private static int getDefaultIndentation(TargetLang target) {
        return switch (target) {
            case JAVA -> 2;
            case C -> 1;
            case JS -> 0;
        };
    }
}
