package recursion.basic;

import util.Utils;

// https://leetcode.com/problems/find-greatest-common-divisor-of-array/description
public class GCD {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int a = scanner.nextInt("Enter first number: ");
        int b = scanner.nextInt("Enter second number: ");

        int gcd = recursive(a, b);

        Utils.println("GCD: " + gcd);
    }

    // Time: O(log(min(a, b))) — Euclidean algorithm reduces the problem quickly.
    // Space: O(log(min(a, b))) — recursive call stack.
    public static int recursive(int a, int b) {

        // When b becomes 0, a is the GCD.
        if (b == 0) {
            return a;
        }

        // Apply Euclidean algorithm: GCD(a, b) = GCD(b, a % b).
        return recursive(b, a % b);
    }
}