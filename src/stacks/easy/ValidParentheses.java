package stacks.easy;

import util.Utils;

import java.util.Stack;

// https://leetcode.com/problems/valid-parentheses/description/
// https://takeuforward.org/plus/dsa/problems/balanced-paranthesis
public class ValidParentheses {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        String s = scanner.next("Enter string: ");

        boolean result = isValid(s);

        Utils.println("Valid: " + result);
    }

    /*
     * Approach:
     *
     * Opening bracket -> push into stack.
     * Closing bracket -> must match stack.peek().
     *
     * If it matches -> pop.
     * If it doesn't match -> invalid.
     *
     * At the end, stack must be empty.
     *
     * Time:  O(n)
     * Space: O(n)
     */

    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            // Opening bracket
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
                continue;
            }

            // Closing bracket without an opening bracket
            if (stack.isEmpty()) {
                return false;
            }

            char top = stack.peek();

            // Closing bracket doesn't match latest opening bracket
            if ((ch == ')' && top != '(')
                    || (ch == ']' && top != '[')
                    || (ch == '}' && top != '{')) {

                return false;
            }

            // Matching pair
            stack.pop();
        }

        // Every opening bracket must have been closed
        return stack.isEmpty();
    }
}