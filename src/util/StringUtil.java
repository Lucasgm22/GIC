package util;

public abstract class StringUtil {

    private static final String INDENTATION = "    ";

    public static String getIndentationLvl(int indentationLvl) {
        String indentation = "";
        for (int i = 0; i < indentationLvl; i++) {
            indentation = indentation.concat(INDENTATION);
        }
        return indentation;
    }
}
