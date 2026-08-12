package recursion.medium.generation;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class GenerateKLengthStrings {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int k = scanner.nextInt("Enter k: ");

        List<String> allowedChars = List.of("A", "B", "C");
        List<String> result = recursive(k, allowedChars);

        Utils.println("Strings: " + result);
        Utils.println("Count: " + result.size());
    }

    // Time  : O(k * m^k)
    // Space : O(k * m^k), including output
    // where m = number of allowed characters
    public static List<String> recursive(int k, List<String> allowedChars) {

        // One valid string of length 0: the empty string.
        if (k == 0) {
            return List.of("");
        }

        // Generate all strings of length k - 1.
        List<String> smaller = recursive(k - 1, allowedChars);

        List<String> result = new ArrayList<>();

        // Try every allowed character as the current choice.
        for (String allowedChar : allowedChars) {

            // Add the current character to every smaller result.
            for (String s : smaller) {
                result.add(allowedChar + s);
            }
        }

        return result;
    }
}