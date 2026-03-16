package arrays.medium;

import util.ArrayUtils;
import util.Utils;

import java.util.ArrayList;
import java.util.List;

// https://takeuforward.org/plus/dsa/problems/pascals-triangle-i?subject=dsa&tab=description

public class PascalTriangle {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        Utils.print("rows: ");
        int rows = scanner.nextInt();

        List<List<Integer>> pascalTriangle = getPascalTriangle(rows);
        ArrayUtils.printMatrix(pascalTriangle);
    }

    public static List<List<Integer>> getPascalTriangle(int rows) {

        List<List<Integer>> triangle = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    int val = triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j);
                    row.add(val);
                }
            }

            triangle.add(row);
        }

        return triangle;
    }


}
