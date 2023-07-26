package exception;

public class IsiUndeclaredVariableException extends IsiSemanticException {
    public IsiUndeclaredVariableException(String id, int line, int column) {
        super("Undeclared identifier '" + id + "' at line " + line + " at column " + column + ".");
    }
}
