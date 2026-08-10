package recursion.basic;

import util.Utils;

public class PrintNTo1 {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter number: ");

        recursive(n);
    }

    public static void recursive(int n) {

        if (n == 0) return;

        Utils.println(n);
        recursive(n-1);

    }
}