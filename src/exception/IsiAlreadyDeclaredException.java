package exception;

public class IsiAlreadyDeclaredException extends IsiSemanticException {
    public IsiAlreadyDeclaredException(String id, int line0, int line1 ,int line, int column) {
        super("Identifier '" + id + "' already declared at line " + line0 + " at column " + column + ", error", line, column);
    }
}
