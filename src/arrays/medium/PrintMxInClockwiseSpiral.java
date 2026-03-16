package arrays.medium;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

// https://takeuforward.org/plus/dsa/problems/print-the-matrix-in-spiral-manner?subject=dsa
public class PrintMxInClockwiseSpiral {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        Utils.print("Row Size: ");
        int rowSize = scanner.nextInt();
        Utils.print("Column Size: ");
        int columnSize = scanner.nextInt();

        int[][] mx = scanner.nextIntMatrix(rowSize, columnSize);

        Utils.print2D(mx);
        List<Integer> spiral = printClockwiseSpiral(mx);
        Utils.print(spiral);
    }

    public static List<Integer> printClockwiseSpiral(int[][] mx) {

        List<Integer> spiral = new ArrayList<>();

        if (mx == null || mx.length == 0 || mx[0].length == 0) {
            return spiral;
        }

        int top = 0;
        int bottom = mx.length - 1;
        int left = 0;
        int right = mx[0].length - 1;

        while (top <= bottom && left <= right) {

            // left to right
            for (int col = left; col <= right; col++) {
                spiral.add(mx[top][col]);
            }
            top++;

            // top to bottom
            for (int row = top; row <= bottom; row++) {
                spiral.add(mx[row][right]);
            }
            right--;

            // right to left
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    spiral.add(mx[bottom][col]);
                }
                bottom--;
            }

            // bottom to top
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    spiral.add(mx[row][left]);
                }
                left++;
            }
        }


        return spiral;

    }

}
