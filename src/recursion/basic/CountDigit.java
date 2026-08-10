package recursion.basic;

import util.Utils;

// https://leetcode.com/problems/count-digit-appearances/description/
public class CountDigit {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter number: ");
        int digit = scanner.nextInt("Enter digit: ");

        int count = recursive(n, digit);

        Utils.println("Count: " + count);
    }

    public static int recursive(int n, int digit) {

        // Base case: check the final digit.
        if (n < 10) {
            return n == digit ? 1 : 0;
        }

        int lastDigit = n % 10;

        // Count current digit if it matches.
        if (lastDigit == digit) {
            return 1 + recursive(n / 10, digit);
        }

        // Current digit doesn't match.
        return recursive(n / 10, digit);
    }
}