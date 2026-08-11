package recursion.basic;

import util.Utils;

public class DecimalToBinary {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter number: ");

        if (n == 0) {
            Utils.print(0);
        } else {
            recursive(n);
        }
    }

    // Time  : O(log n)
    // Space : O(log n) due to recursion stack
    public static void recursive(int n) {

        // Stop when all higher-order digits have been processed.
        if (n == 0) {
            return;
        }

        // Process higher-order binary digits first.
        recursive(n / 2);

        // Print the current binary digit while unwinding.
        Utils.print(n % 2);
    }
}