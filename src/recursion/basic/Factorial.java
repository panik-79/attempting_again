package recursion.basic;

import util.Utils;

public class Factorial {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter number: ");

        int factorial = recursive(n);

        Utils.println("Factorial: " + factorial);
    }

    public static int recursive(int n) {

        if (n == 0 || n == 1) {
            return 1;
        }

        return n * recursive(n - 1);
    }
}