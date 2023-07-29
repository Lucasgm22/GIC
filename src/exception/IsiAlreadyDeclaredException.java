package exception;

public class IsiAlreadyDeclaredException extends IsiSemanticException {
    public IsiAlreadyDeclaredException(String msg, int line, int column) {
        super(msg, line, column);
    }
}
