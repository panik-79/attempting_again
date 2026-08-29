package stacks.medium;

import util.Utils;

import java.util.Stack;

// https://takeuforward.org/plus/dsa/problems/next-greater-element
// https://leetcode.com/problems/next-greater-element-i/
public class NextGreaterElement {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int[] result = nextGreaterElement(arr);
        Utils.println(result);
    }


    /*
     * Given an array arr, find the next greater element
     * for every element.
     *
     * The next greater element is the nearest element
     * on the right that is greater than the current element.
     *
     * If no such element exists, return -1.
     *
     */

    /*
     * Traverse from right to left and maintain possible next greater elements.
     *
     * Before processing a number, remove all smaller/equal elements because
     * they can never be the next greater element for this number or future
     * elements to its left.
     *
     * After popping:
     * - Stack empty     -> no greater element exists on the right.
     * - Stack non-empty -> top is the nearest greater element.
     *
     * Time:  O(n)  -> every element is pushed and popped at most once.
     * Space: O(n)
     */
    public static int[] nextGreaterElement(int[] arr) {

        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];

        // Process elements from right to left.
        for (int i = arr.length - 1; i >= 0; i--) {

            int num = arr[i];

            // Remove elements that cannot be the next greater element.
            while (!stack.isEmpty() && stack.peek() <= num) {
                stack.pop();
            }

            // Top of stack is the nearest greater element on the right.
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }

            // Current element may be useful for elements to its left.
            stack.push(num);
        }

        return ans;
    }
}