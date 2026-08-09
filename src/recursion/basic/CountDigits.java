package recursion.basic;

import util.Utils;

public class CountDigits {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter number: ");

        int count = recursive(n);

        Utils.println("Number of digits: " + count);
    }

    public static int recursive(int n) {
        if (n < 10) {
            return 1;
        }
        return 1 + recursive(n / 10);
    }
}