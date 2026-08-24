package stacks.easy;

import util.Utils;

import java.util.Stack;

// https://takeuforward.org/plus/dsa/problems/reverse-a-stack
public class ReverseStack {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        int n = scanner.nextInt();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            stack.push(scanner.nextInt());
        }
        reverse(stack);
        Utils.println("Reversed Stack: " + stack);
    }

    public static void reverse(Stack<Integer> stack) {

        // TODO
    }
}