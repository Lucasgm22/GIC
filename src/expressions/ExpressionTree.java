package expressions;


import java.util.Stack;

public class ExpressionTree  extends AbstractExpression {
    private Stack<Node> stN = new Stack<>();
    private Stack<Node> stOp = new Stack<>();

    public String expression = "";
    private int []p = new int[123];

    public ExpressionTree() {
        super();
        p['+'] = p['-'] = 1;
        p['/'] = p['*'] = 2;
        p['('] = 0;
    }


    public void addOperator(OperatorExpression op) {
        expression = expression + op;
        if (op.getOperator() == '(') {
            var n = new Node(op);
            stOp.push(n);
        } else if (p[op.getOperator()] > 0) {
            while (
                    !stOp.isEmpty() && p[getPeekOperator()] >= p[op.getOperator()])
            {
                // Get and remove the top element
                // from the character stack
                var t = new Node(stOp.peek().getData());
                stOp.pop();

                // Get and remove the top element
                // from the node stack
                var t1 = stN.peek();
                stN.pop();

                // Get and remove the currently top
                // element from the node stack
                var t2 = stN.peek();
                stN.pop();

                // Update the tree
                t.left = t2;
                t.right = t1;

                // Push the node to the node stack
                stN.push(t);

            }
            stOp.push(new Node(op));
        } else if (op.getOperator() == ')') {
            while (getPeekOperator() != '(')
            {
                var t = new Node(stOp.peek().getData());
                stOp.pop();
                var t1 = stN.peek();
                stN.pop();
                var t2 = stN.peek();
                stN.pop();
                t.left = t2;
                t.right = t1;
                stN.push(t);
            }
            stOp.pop();
        }
    }

    private Node buildTree() {
        while (stN.size() > 1) {
            var t = new Node(stOp.peek().getData());
            stOp.pop();
            var t1 = stN.peek();
            stN.pop();
            var t2 = stN.peek();
            stN.pop();
            t.left = t2;
            t.right = t1;
            stN.push(t);
        }
        return stN.peek();
    }

    @Override
    public String toString() {
        return expression;
    }

    private char getPeekOperator() {
        return ((OperatorExpression) stOp.peek().getData()).getOperator();
    }
    public void addOperand(AbstractExpression operand) {
        expression = expression + operand;
        var n = new Node(operand);
        stN.push(n);
    }

    @Override
    public double eval() {
        Node n = buildTree();
        return n.eval();
    }
}

class Node {
    AbstractExpression data;
    Node left,right;
    public Node(AbstractExpression data) {
        this.data = data;
        left = right = null;
    }

    public AbstractExpression getData() {
        return data;
    }

    @Override
    public String toString() {
        String leftSt = left != null ? left.toString() : "";
        String rightSt = right != null ? right.toString() : "";

        return leftSt + data.toString() + rightSt;
    }

    public double eval() {
        // Leaf node i.e, an integer
        if (left == null && right == null)
            return data.eval();

        double leftEval = left.eval();
        double rightEval = right.eval();

        return switch (((OperatorExpression) data).getOperator()) {
            case '+' -> leftEval + rightEval;
            case '-' -> leftEval - rightEval;
            case '*' -> leftEval * rightEval;
            case '/' -> leftEval / rightEval;

            default ->
                    throw new IllegalStateException("Unexpected value: " + ((OperatorExpression) data).getOperator());
        };
    }
}
