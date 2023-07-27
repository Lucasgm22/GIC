package exception;

public class IsiUnassignedVariableException extends IsiSemanticException {
    public IsiUnassignedVariableException(String id, int line, int column) {
        super("'" + id + "' does not have a initial value", line, column);
    }
}
