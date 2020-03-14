package geekspearls.amz.phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Question Arithmetic Expression Tree (AET) is a specific kind of a binary tree used to represent expressions.
 * Each node of a binary expression tree has zero or two children.
 *
 *          (/)
 *          / \
 *         /   \
 *       (*)   (4)
 *       / \
 *      /   \
 *    (2)   (+)
 *          / \
 *         /   \
 *       (3)   (Avg)
 *             / \  \
 *            /   \  \
 *          (6)  (2) (3)
 *
 *         (/)
 *          / \
 *         /   \
 *       (8)   (4)
 * Design and code a data structure to represent arbitrarily large and complex arithmetic expressions.
 * Consider maintainability and extensibility of your structure.
 * Implement evaluate.
 */
public class ArithmeticExprTree {

    class Node {
        char value;
        Node left;
        Node right;

        Node(char value) {
            this.value = value;
        }
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == 'a';
    }

    int eval(Node root) {
        if (!isOperator(root.value)) {
            return root.value - '0';
        }

        int leftVal = eval(root.left);
        int rightVal = eval(root.right);
        char op = root.value;
        if (op == '+') {
            return leftVal + rightVal;
        } else if (op == '-') {
            return leftVal - rightVal;
        } else if (op == '*') {
            return leftVal * rightVal;
        } else if (op == '/') {
            return leftVal / rightVal;
        } else {
            throw new IllegalArgumentException("Unkown operator");
        }
    }

    /**
     * ab+ef*g*- => a + b - e * f * g
     */
    Node constructTreeFromPostfixExpr(String expr) {
        char[] exprChars = expr.toCharArray();
        Stack<Node> stack = new Stack<>();

        for (int i = 0; i < exprChars.length; i++) {
            char ch = exprChars[i];
            if (!isOperator(ch)) {
                Node operand = new Node(ch);
                stack.push(operand);
            } else {
                Node operator = new Node(ch);
                Node op1 = stack.pop();
                Node op2 = stack.pop();
                operator.right = op1;
                operator.left = op2;
                stack.push(operator);
            }
        }
        return stack.pop();
    }

    ////// n-ary tree expr
    class NAryNode {
        char value;
        List<NAryNode> children;

        NAryNode(char value) {
            this.value = value;
            children = new ArrayList<>();
        }
    }

    NAryNode constructNaryTree(String postfix) {
        char[] exprChars = postfix.toCharArray();
        Stack<NAryNode> stack = new Stack<>();

        for (int i = 0; i < exprChars.length; i++) {
            char ch = exprChars[i];
            if (!isOperator(ch)) {
                NAryNode operand = new NAryNode(ch);
                stack.push(operand);
            } else {
                NAryNode operator = new NAryNode(ch);
                while (!stack.isEmpty()) {
                    NAryNode operand = stack.pop();
                    operator.children.add(operand);
                }
                stack.push(operator);
            }
        }
        return stack.pop();
    }

    double eval(NAryNode root) {
        if (!isOperator(root.value)) {
            return root.value - '0';
        }

        List<Double> operands = new ArrayList<>();
        for (NAryNode oper : root.children) {
            operands.add(eval(oper));
        }
        char op = root.value;
        if (op == '+') {
            if (operands.size() != 2) {
                throw new IllegalArgumentException("wrong number of operands");
            }
            return operands.get(0) + operands.get(1);
        } else if (op == '-') {
            if (operands.size() != 2) {
                throw new IllegalArgumentException("wrong number of operands");
            }
            return operands.get(0) - operands.get(1);
        } else if (op == '*') {
            if (operands.size() != 2) {
                throw new IllegalArgumentException("wrong number of operands");
            }
            return operands.get(0) * operands.get(1);
        } else if (op == '/') {
            if (operands.size() != 2) {
                throw new IllegalArgumentException("wrong number of operands");
            }
            return operands.get(0) / operands.get(1);
        } else if (op == 'a') {
            return avg(operands);
        } else {
            throw new IllegalArgumentException("Unknown operator");
        }
    }

    private double avg(List<Double> nums) {
        int total = 0;
        for (Double n : nums) {
            total += n;
        }
        return total / nums.size();
    }

    public static void main(String[] args) {
        ArithmeticExprTree arithmeticExprTree = new ArithmeticExprTree();
        Node root = arithmeticExprTree.constructTreeFromPostfixExpr("84/3+");
        System.out.println(arithmeticExprTree.eval(root));

        NAryNode nroot = arithmeticExprTree.constructNaryTree("693a3+");
        System.out.println(arithmeticExprTree.eval(nroot));
    }

}
