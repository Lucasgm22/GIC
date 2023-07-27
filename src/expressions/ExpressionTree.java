package expressions;


import java.util.ArrayDeque;
import java.util.Deque;

public class ExpressionTree implements AbstractExpression {
    private final Deque<Node> stN = new ArrayDeque<>();
    private final Deque<Node> stOp = new ArrayDeque<>();

    private String expression = "";
    private final int []p = new int[123];

    public ExpressionTree() {
        super();
        p['+'] = p['-'] = 1;
        p['/'] = p['*'] = 2;
        p['('] = 0;
    }


    public void addOperator(OperatorExpression op) {
        expression = expression + op;
        if (op.operator() == '(') {
            var n = new Node(op);
            stOp.push(n);
        } else if (p[op.operator()] > 0) {
            while (
                    !stOp.isEmpty() && p[getPeekOperator()] >= p[op.operator()])
            {
                // Get and remove the top element
                // from the character stack
                assert stOp.peek() != null;
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
        } else if (op.operator() == ')') {
            while (getPeekOperator() != '(')
            {
                assert stOp.peek() != null;
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
            assert stOp.peek() != null;
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
        assert stOp.peek() != null;
        return ((OperatorExpression) stOp.peek().getData()).operator();
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
    Node left;
    Node right;
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

        if (left == null || right == null)
            throw new IllegalStateException(
                    "Expression Tree is not complete, Node with single branch: Left: " + left + " Right: " + right
            );

        double leftEval = left.eval();
        double rightEval = right.eval();

        return switch (((OperatorExpression) data).operator()) {
            case '+' -> leftEval + rightEval;
            case '-' -> leftEval - rightEval;
            case '*' -> leftEval * rightEval;
            case '/' -> leftEval / rightEval;

            default ->
                    throw new IllegalStateException("Unexpected value: " + ((OperatorExpression) data).operator());
        };
    }
}
