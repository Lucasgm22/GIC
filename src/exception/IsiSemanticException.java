package exception;

public class IsiSemanticException extends RuntimeException {
    public IsiSemanticException(String msg) {
        super("SEMANTIC ERROR - " + msg);
    }
}
