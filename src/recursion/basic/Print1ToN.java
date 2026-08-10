package recursion.basic;

import util.Utils;

public class Print1ToN {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter number: ");

        recursive(n);
    }

    public static void recursive(int n) {

        if (n == 0) return;

        recursive(n-1);
        Utils.println(n);

    }
}