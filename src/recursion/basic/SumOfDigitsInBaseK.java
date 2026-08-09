package recursion.basic;

import util.Utils;

// https://leetcode.com/problems/sum-of-digits-in-base-k/description/
public class SumOfDigitsInBaseK {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter number: ");
        int base = scanner.nextInt("Enter base: ");

        int sum = recursive(n, base);

        Utils.println("Sum of digits: " + sum);
    }

    public static int recursive(int n, int base) {

        if (n < base) {
            return n;
        }

        int digit = n % base;
        return digit + recursive(n/base, base);
    }
}