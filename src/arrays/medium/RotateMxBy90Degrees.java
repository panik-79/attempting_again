package arrays.medium;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

// https://takeuforward.org/plus/dsa/problems/rotate-matrix-by-90-degrees?subject=dsa
public class RotateMxBy90Degrees {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();

        Utils.print("Row size: ");
        int rowSize = scanner.nextInt();

        int[][] mx = scanner.nextIntMatrix(rowSize, rowSize);

        Utils.println("Input matrix: ");
        Utils.print2D(mx);

//        List<List<Integer>> rotatedArr = bruteRotate(mx, rowSize);
//
//        Utils.println("Rotated matrix: ");
//        Utils.print2D(Utils.to2DArray(rotatedArr));

        optimalRotate(mx, rowSize);
        Utils.println("Output matrix: ");
        Utils.print2D(mx);
    }

    // TC: O(n^2) , SC: O(n^2)
    public static List<List<Integer>> bruteRotate (int[][] mx, int n) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(mx[n-j-1][i]);
            }
            result.add(row);
        }

        return result;
    }

    public static void optimalRotate(int[][] mx, int n) {

        // transpose
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = mx[i][j];
                mx[i][j] = mx[j][i];
                mx[j][i] = temp;
            }
        }

        // reverse every row
        for (int i = 0; i < n; i++) {
            Utils.reverse(mx[i], 0, mx[i].length - 1);
        }
    }

}
