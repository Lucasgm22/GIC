package exception;

import symbols.DataType;

public class IsiTypeMismatchException extends IsiSemanticException {
    public IsiTypeMismatchException(DataType left, DataType right, int line, int column) {
        super("Type Mismatch " + left + "-" + right, line, column);
    }
}
