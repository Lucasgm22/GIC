package exception;

public class IsiSemanticException extends RuntimeException {
    public IsiSemanticException(String msg, int line, int column) {
        super("SEMANTIC ERROR - " + msg + " at line " + line + " at column " + column + ".");
    }
}
