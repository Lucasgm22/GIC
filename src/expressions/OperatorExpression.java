package expressions;

public class OperatorExpression extends AbstractExpression {

    private final char operator;

    public OperatorExpression(char operator) {
        this.operator = operator;
    }
    @Override
    public double eval() {
        throw new IllegalStateException("cannot eval operator");
    }

    public char getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return String.valueOf(operator);
    }
}
