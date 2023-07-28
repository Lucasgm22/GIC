package exception;

public class IsiSyntacticException extends RuntimeException {
    public IsiSyntacticException(String msg, int line, int column) {
        super("SYNTACTIC ERROR - " + msg + " at line " + line + " at columns " + column + ".");
    }
}
