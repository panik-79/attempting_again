package recursion.basic;

import util.Utils;

public class ReverseNumber {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter number: ");

        int rev = 0;
        int reversed = recursive(n, rev);

        Utils.println("Reversed number: " + reversed);
    }

    public static int recursive(int n, int rev) {

        if (n == 0) {
            return rev;
        }

        int digit = n % 10;
        rev = rev * 10 + digit;

        return recursive(n / 10, rev);
    }
}