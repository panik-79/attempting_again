package recursion.medium.combinations;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class GenerateCombinations {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter n: ");
        int k = scanner.nextInt("Enter k: ");

        List<List<Integer>> result = recursive(n, k, 1);

        Utils.println("Combinations: " + result);
    }

    public static List<List<Integer>> recursive(
            int n,
            int k,
            int start) {

        // Base condition:
        // We need to choose 0 more elements.
        if (k == 0) {
            return List.of(new ArrayList<>());
        }

        List<List<Integer>> result = new ArrayList<>();

        // Try every possible element as the next choice.
        for (int i = start; i <= n; i++) {

            // Choose i.
            // Now we need to choose k - 1 more elements
            // from the elements after i.
            List<List<Integer>> smaller =
                    recursive(n, k - 1, i + 1);

            // Add i to every combination returned
            // by the smaller problem.
            for (List<Integer> list : smaller) {

                List<Integer> combination = new ArrayList<>();

                combination.add(i);
                combination.addAll(list);

                result.add(combination);
            }
        }

        return result;
    }
}