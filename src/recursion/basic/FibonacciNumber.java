package recursion.basic;

import util.Utils;

// https://leetcode.com/problems/fibonacci-number/
// https://takeuforward.org/plus/dsa/problems/fibonacci-number
public class FibonacciNumber {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        int n = scanner.nextInt("Enter number : ");

        // 1. Simple Recursion
        int fibonacciRecursive = recursive(n);
        Utils.println("Fibonacci (Recursive) : " + fibonacciRecursive);

        // 2. Recursion + Memoization
        int[] dp = new int[n + 1];
        java.util.Arrays.fill(dp, -1);

        int fibonacciMemo = memoization(n, dp);
        Utils.println("Fibonacci (Memoization) : " + fibonacciMemo);

        // 3. Iterative / Bottom-Up DP
        int fibonacciIterative = iterative(n);
        Utils.println("Fibonacci (Iterative) : " + fibonacciIterative);

        // 4. Space Optimized Iterative
        int fibonacciOptimized = optimized(n);
        Utils.println("Fibonacci (Optimized) : " + fibonacciOptimized);
    }


    /*
     * ============================================================
     * 1. SIMPLE RECURSION
     * ============================================================
     *
     * F(n) = F(n - 1) + F(n - 2)
     *
     * Base cases:
     * F(0) = 0
     * F(1) = 1
     *
     * Time Complexity : O(2^n)
     * Space Complexity: O(n)
     *
     * Space is O(n) because of the recursive call stack.
     */
    public static int recursive(int n) {

        // Base condition
        if (n == 0 || n == 1) {
            return n;
        }

        // Recursive calls
        return recursive(n - 1) + recursive(n - 2);
    }


    /*
     * ============================================================
     * 2. RECURSION + MEMOIZATION
     * ============================================================
     *
     * We store Fibonacci values that we have already calculated.
     *
     * Without memoization:
     *
     *                 F(5)
     *                /    \
     *             F(4)    F(3)
     *             /  \    /  \
     *           F(3) F(2) F(2) F(1)
     *
     * The same values are calculated again and again.
     *
     * With memoization, each F(n) is calculated only once.
     *
     * Time Complexity : O(n)
     * Space Complexity: O(n)
     *
     * O(n) for the dp array
     * O(n) for the recursion stack
     * Overall: O(n)
     */
    public static int memoization(int n, int[] dp) {

        // Base condition
        if (n == 0 || n == 1) {
            return n;
        }

        // If already calculated, return stored value
        if (dp[n] != -1) {
            return dp[n];
        }

        // Calculate and store the result
        dp[n] = memoization(n - 1, dp)
                + memoization(n - 2, dp);

        return dp[n];
    }


    /*
     * ============================================================
     * 3. ITERATIVE / BOTTOM-UP DP
     * ============================================================
     *
     * Instead of going from F(n) -> F(n-1) -> F(n-2),
     * we start from F(0) and build up to F(n).
     *
     * dp[0] = 0
     * dp[1] = 1
     *
     * dp[i] = dp[i - 1] + dp[i - 2]
     *
     * Time Complexity : O(n)
     * Space Complexity: O(n)
     */
    public static int iterative(int n) {

        if (n == 0 || n == 1) {
            return n;
        }

        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }


    /*
     * ============================================================
     * 4. SPACE OPTIMIZED ITERATIVE
     * ============================================================
     *
     * Notice that to calculate F(i), we only need:
     *
     * F(i - 2)
     * F(i - 1)
     *
     * We don't actually need the entire dp array.
     *
     * Time Complexity : O(n)
     * Space Complexity: O(1)
     */
    public static int optimized(int n) {

        if (n == 0 || n == 1) {
            return n;
        }

        int prev2 = 0; // F(0)
        int prev1 = 1; // F(1)

        for (int i = 2; i <= n; i++) {

            int current = prev1 + prev2;

            // Move the two previous values forward
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}