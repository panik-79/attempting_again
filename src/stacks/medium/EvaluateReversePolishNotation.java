package stacks.medium;

import util.Utils;

import java.util.Stack;

// https://leetcode.com/problems/evaluate-reverse-polish-notation/description/
public class EvaluateReversePolishNotation {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        int n = scanner.nextInt();
        String[] tokens = new String[n];
        for (int i = 0; i < n; i++) {
            tokens[i] = scanner.next();
        }
        int result = evalRPN(tokens);
        Utils.println("Result: " + result);
    }

    /*
     * Approach:
     *
     * Number -> push into stack.
     * Operator -> pop right operand, then left operand.
     *            Perform operation and push result back.
     *
     * Example:
     * ["2", "1", "+", "3", "*"]
     *
     * 2  -> [2]
     * 1  -> [2, 1]
     * +  -> [3]
     * 3  -> [3, 3]
     * *  -> [9]
     *
     * Time:  O(n)
     * Space: O(n)
     */

    public static int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {

            // Number
            if (!isOperator(token)) {
                stack.push(Integer.parseInt(token));
                continue;
            }

            // Right operand comes out first
            int right = stack.pop();

            // Left operand comes out second
            int left = stack.pop();

            int result = operate(token, left, right);

            stack.push(result);
        }

        return stack.pop();
    }


    // Performs: left operator right
    private static int operate(String operator, int left, int right) {

        if (operator.equals("+")) return left + right;
        if (operator.equals("-")) return left - right;
        if (operator.equals("*")) return left * right;
        if (operator.equals("/")) return left / right;

        return 0;
    }


    // Checks whether token is an operator
    private static boolean isOperator(String s) {

        return s.equals("+")
                || s.equals("-")
                || s.equals("*")
                || s.equals("/");
    }
}