package expressions;

public record OperatorExpression(char operator) implements AbstractExpression {

    @Override
    public double eval() {
        throw new IllegalStateException("cannot eval operator");
    }

    @Override
    public String toString() {
        return String.valueOf(operator);
    }
}
