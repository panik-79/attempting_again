package recursion.basic;

import util.Utils;

public class SumOfNNumbers {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        int n = scanner.nextInt("Enter Number : ");
        int sum = sum(n);
        Utils.println("Sum : " + sum);
    }

    public static int sum (int n) {
        if (n == 1 || n == 0) {
            return n;
        }

        return n + sum(n - 1);
    }

}
