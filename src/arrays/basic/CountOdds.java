package arrays.basic;

import util.Utils;

public class CountOdds {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        int size =  scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);

        int oddCount = 0;
        for (int i = 0; i < size; i++) {
            if (arr[i] % 2 == 1) {
                oddCount++;
            }
        }

        Utils.print("Odd Count: " + oddCount);

    }

}
