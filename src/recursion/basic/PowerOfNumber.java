package recursion.basic;

import util.Utils;

// https://leetcode.com/problems/powx-n/description/
public class PowerOfNumber {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        double base = scanner.nextDouble("Enter base: ");
        int exponent = scanner.nextInt("Enter exponent: ");

        double result = recursive(base, exponent);

        Utils.println("Result: " + result);
    }

    /*
     * Calculates base^exponent using fast exponentiation.
     *
     * Instead of calculating:
     *     base^n = base * base * base * ... n times
     *
     * We calculate:
     *     base^n = (base^(n/2))^2
     *
     * This reduces the problem size by half at every recursive call.
     */
    public static double recursive(double base, int exponent) {

        // Base condition:
        // Any number raised to the power 0 is 1.
        if (exponent == 0) {
            return 1;
        }

        // Base condition:
        // Any number raised to the power 1 is itself.
        if (exponent == 1) {
            return base;
        }

        // Base condition:
        // base^(-1) = 1 / base.
        if (exponent == -1) {
            return 1 / base;
        }

        // Calculate base^(exponent / 2).
        // Integer division automatically reduces the exponent by half.
        double halfPow = recursive(base, exponent / 2);

        // If exponent is even:
        // base^n = base^(n/2) * base^(n/2)
        if (exponent % 2 == 0) {
            return halfPow * halfPow;
        }

        // If exponent is odd:
        // base^n = base^(n/2) * base^(n/2) * base       (positive n)
        // base^n = base^(n/2) * base^(n/2) * (1 / base) (negative n)
        return halfPow * halfPow * (exponent > 0 ? base : 1 / base);
    }

    /*
     * Time Complexity: O(log |exponent|)
     *
     * At every recursive call, exponent is divided by 2.
     *
     * Space Complexity: O(log |exponent|)
     *
     * Due to the recursive call stack.
     */
}