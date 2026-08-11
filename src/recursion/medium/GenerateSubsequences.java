package recursion.medium;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class GenerateSubsequences {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        String str = scanner.next("Enter string: ");

        List<String> returnResults = recursive(str, 0);

        List<String> storedResults = new ArrayList<>();
        recursiveStore(str, 0, "", storedResults);

        Utils.println("Return Results : " + returnResults);
        Utils.println("Stored Results : " + storedResults);
    }


    // =========================================================
    // APPROACH 1: RETURN RESULTS
    // =========================================================
    //
    // Idea:
    // Solve the smaller problem first.
    //
    // For "abc":
    //
    // recursive("bc") gives 4 results:
    // ["", "c", "b", "bc"]
    //
    // Then for 'a':
    //
    // Don't take 'a':
    //     keep all 4 results as they are.
    //
    // Take 'a':
    //     add 'a' to the beginning of all 4 results.
    //
    // Therefore:
    //
    // 4 without 'a' + 4 with 'a' = 8 results.
    //
    // ---------------------------------------------------------
    // Time Complexity : O(n * 2^n)
    //
    // There are 2^n subsequences.
    // Each generated string can take O(n) time to construct.
    //
    // Space Complexity : O(n * 2^n)
    //
    // O(2^n) strings are stored.
    // Each string can contain up to O(n) characters.
    // Recursion stack itself is O(n).
    // =========================================================

    public static List<String> recursive(String str, int index) {

        // Base case:
        // The empty string has exactly one subsequence: "".
        if (index == str.length()) {
            return List.of("");
        }

        // Trust recursion to generate all subsequences
        // of the smaller remaining string.
        List<String> smaller = recursive(str, index + 1);

        // DON'T TAKE current character.
        // Copy all smaller subsequences as they are.
        List<String> result = new ArrayList<>(smaller);

        // TAKE current character.
        // Add current character to every smaller subsequence.
        for (String s : smaller) {

            String stringToAdd =
                    String.valueOf(str.charAt(index)).concat(s);

            result.add(stringToAdd);
        }

        return result;
    }


    // =========================================================
    // APPROACH 2: STORE RESULTS / CARRY STATE
    // =========================================================
    //
    // Idea:
    // Instead of returning all answers from the smaller problem,
    // carry the currently constructed subsequence downward.
    //
    // At every character we have two choices:
    //
    // 1. Don't take the character.
    // 2. Take the character.
    //
    // When we reach the end, the current subsequence is complete,
    // so we store it in the result list.
    //
    // ---------------------------------------------------------
    // Time Complexity : O(n * 2^n)
    //
    // There are 2^n possible subsequences.
    // Creating/copying each resulting string can take O(n).
    //
    // Space Complexity : O(n * 2^n)
    //
    // O(2^n) resulting strings are stored.
    // Recursion stack is O(n).
    // The current string construction also requires O(n).
    // =========================================================

    public static void recursiveStore(
            String str,
            int index,
            String current,
            List<String> subsequences) {

        // Base case:
        // Current subsequence is complete.
        if (index == str.length()) {
            subsequences.add(current);
            return;
        }

        // DON'T TAKE current character.
        recursiveStore(
                str,
                index + 1,
                current,
                subsequences
        );

        // TAKE current character.
        recursiveStore(
                str,
                index + 1,
                current + str.charAt(index),
                subsequences
        );
    }
}