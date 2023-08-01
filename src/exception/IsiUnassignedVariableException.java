package exception;

public class IsiUnassignedVariableException extends IsiSemanticException {
    public IsiUnassignedVariableException(String id, int line, int column) {
        super("Identifier '" + id + "' does not have a initial value", line, column);
    }
}
