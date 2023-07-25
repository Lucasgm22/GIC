package expressions;

public class BinaryRelationalExpression {

    private ExpressionTree expressionLeft;
    private ExpressionTree expressionRight;
    private String operator;


    public BinaryRelationalExpression(ExpressionTree expressionLeft, ExpressionTree expressionRight, String operator) {
        this.expressionLeft = expressionLeft;
        this.expressionRight = expressionRight;
        this.operator = operator;
    }

    public boolean eval() {
        return switch (operator) {
            case "<" -> expressionLeft.eval() < expressionRight.eval();
            case "<=" -> expressionLeft.eval() <= expressionRight.eval();
            case ">" -> expressionLeft.eval() > expressionRight.eval();
            case ">=" -> expressionLeft.eval() >= expressionRight.eval();
            case "==" -> expressionLeft.eval() == expressionRight.eval();
            case "<>" -> expressionLeft.eval() != expressionRight.eval();
            default -> throw new RuntimeException("unknown rel operator");
        };
    }

    @Override
    public String toString() {
        return expressionLeft + operator + expressionRight;
    }
}
