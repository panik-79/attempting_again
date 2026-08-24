package stacks.easy;

import util.Utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// https://leetcode.com/problems/min-stack/description/
// https://takeuforward.org/plus/dsa/problems/min-stack
public class MinStack {

    /*
     * ============================================================
     * PROBLEM
     * ============================================================
     *
     * Design a stack that supports:
     *
     *     push(val)
     *     pop()
     *     top()
     *     getMin()
     *
     * All operations should ideally be O(1).
     *
     *
     * ============================================================
     * APPROACH 1 — BRUTE FORCE
     * ============================================================
     *
     * Use a normal stack.
     *
     * push() -> O(1)
     * pop()  -> O(1)
     * top()  -> O(1)
     *
     * getMin() -> traverse the entire stack and find minimum.
     *
     * Time:
     *     push()   -> O(1)
     *     pop()    -> O(1)
     *     top()    -> O(1)
     *     getMin() -> O(n)
     *
     * Space: O(n)
     *
     * This does NOT satisfy the O(1) getMin requirement.
     *
     *
     * ============================================================
     * APPROACH 2 — TWO STACKS
     * ============================================================
     *
     * Maintain:
     *
     *     stack     -> actual values
     *     minStack  -> minimum value at every level
     *
     * Example:
     *
     * push(5)
     * push(3)
     * push(7)
     * push(2)
     *
     * stack:
     *     5  3  7  2
     *
     * minStack:
     *     5  3  3  2
     *
     * The top of minStack is always the current minimum.
     *
     * Time:
     *     push()   -> O(1)
     *     pop()    -> O(1)
     *     top()    -> O(1)
     *     getMin() -> O(1)
     *
     * Space: O(n)
     *
     *
     * ============================================================
     * APPROACH 3 — LINKED LIST + minSoFar
     * ============================================================
     *
     * Each node stores:
     *
     *     data      -> actual value
     *     minSoFar  -> minimum from this node downwards
     *     next      -> node below it
     *
     * Example:
     *
     *     top
     *      |
     *      v
     *     [2 | min=2] -> [7 | min=3] -> [3 | min=3]
     *                                      |
     *                                   [5 | min=5]
     *
     * Invariant:
     *
     *     node.minSoFar =
     *         minimum of node.data and everything below it
     *
     * Therefore:
     *
     *     getMin() -> top.minSoFar
     *
     * Time:
     *     push()   -> O(1)
     *     pop()    -> O(1)
     *     top()    -> O(1)
     *     getMin() -> O(1)
     *
     * Space: O(n)
     *
     *
     * ============================================================
     * LEETCODE NOTE
     * ============================================================
     *
     * LeetCode provides the MinStack class itself.
     *
     * Submit only ONE of the approaches below.
     *
     * For learning:
     *     - Keep all approaches here.
     *
     * For submission:
     *     - Copy the approach you want into LeetCode.
     */

    // ============================================================
    // APPROACH 1 — BRUTE FORCE
    // ============================================================

    static class MinStackBruteForce {

        private final Stack<Integer> stack = new Stack<>();

        public void push(int val) {
            stack.push(val);
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        // Time: O(n)
        // Space: O(n)
        public int getMin() {

            int min = Integer.MAX_VALUE;

            for (int value : stack) {
                min = Math.min(min, value);
            }

            return min;
        }
    }


    // ============================================================
    // APPROACH 2 — TWO STACKS
    // ============================================================

    static class MinStackTwoStacks {

        private final Deque<Integer> stack = new ArrayDeque<>();
        private final Deque<Integer> minStack = new ArrayDeque<>();

        /*
         * Time: O(1)
         * Space: O(n)
         */
        public void push(int val) {

            stack.push(val);

            if (minStack.isEmpty()) {
                minStack.push(val);
            } else {
                minStack.push(Math.min(val, minStack.peek()));
            }
        }

        /*
         * Time: O(1)
         * Space: O(n)
         */
        public void pop() {

            stack.pop();
            minStack.pop();
        }

        /*
         * Time: O(1)
         * Space: O(n)
         */
        public int top() {

            return stack.peek();
        }

        /*
         * Time: O(1)
         * Space: O(n)
         */
        public int getMin() {

            return minStack.peek();
        }
    }


    // ============================================================
    // APPROACH 3 — LINKED LIST + minSoFar
    // ============================================================

    static class MinStackLinkedList {

        static class Node {

            int data;
            int minSoFar;
            Node next;

            Node(int data, int minSoFar, Node next) {

                this.data = data;
                this.minSoFar = minSoFar;
                this.next = next;
            }
        }

        private Node top;

        /*
         * Time: O(1)
         * Space: O(n)
         */
        public void push(int val) {

            int minSoFar;

            if (isEmpty()) {
                minSoFar = val;
            } else {
                minSoFar = Math.min(top.minSoFar, val);
            }

            top = new Node(val, minSoFar, top);
        }

        /*
         * Time: O(1)
         * Space: O(n)
         */
        public void pop() {

            if (isEmpty()) {
                return;
            }

            top = top.next;
        }

        /*
         * Time: O(1)
         * Space: O(n)
         */
        public int top() {

            return top.data;
        }

        /*
         * Time: O(1)
         * Space: O(n)
         */
        public int getMin() {

            return top.minSoFar;
        }

        public boolean isEmpty() {

            return top == null;
        }
    }


    // ============================================================
    // TESTING
    // ============================================================

    public static void main(String[] args) {

        MinStackLinkedList stack = new MinStackLinkedList();

        stack.push(5);
        stack.push(3);
        stack.push(7);
        stack.push(2);

        Utils.println("Top: " + stack.top());
        Utils.println("Min: " + stack.getMin());

        stack.pop();

        Utils.println("Top: " + stack.top());
        Utils.println("Min: " + stack.getMin());

        stack.pop();

        Utils.println("Top: " + stack.top());
        Utils.println("Min: " + stack.getMin());
    }
}