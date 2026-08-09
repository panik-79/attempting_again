package recursion.basic;

import util.Utils;

public class ProductOfDigits {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter number: ");

        int product = recursive(n);

        Utils.println("Product of digits: " + product);
    }

    public static int recursive(int n) {

        if (n < 10) {
            return n;
        }

        return (n % 10) * recursive(n / 10);
    }
}