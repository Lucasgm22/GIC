package exception;

import symbols.DataType;

public class IsiIllegalOperationException extends IsiSemanticException {
    public IsiIllegalOperationException(String op, DataType type, int line, int column) {
        super("Operator '" + op +"' can not be applied to type "+ type, line, column);
    }
}
