package recursion.basic;

import util.Utils;

// https://leetcode.com/problems/count-the-digits-that-divide-a-number/
public class CountDigitsThatDivideNumber {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter number: ");

        int count = recursive(n, n);

        Utils.println("Count: " + count);
    }

    public static int recursive(int n, int originalN) {

        if (n < 10) {
            return originalN % n == 0 ? 1 : 0;
        }

        int lastDigit = n % 10;
        if (originalN % lastDigit == 0) {
            return 1 + recursive(n / 10, originalN);
        } else {
            return recursive(n / 10, originalN);
        }

    }
}