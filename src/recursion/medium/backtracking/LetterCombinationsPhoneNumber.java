package recursion.medium.backtracking;

import util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
// https://takeuforward.org/plus/dsa/problems/letter-combinations-of-a-phone-number
public class LetterCombinationsPhoneNumber {

    /*
     * ============================================================
     * PROBLEM: Letter Combinations of a Phone Number
     * ============================================================
     *
     * Given a string containing digits from 2 to 9, return all
     * possible letter combinations that the digits could represent.
     *
     * Phone mapping:
     *
     *     2 -> abc
     *     3 -> def
     *     4 -> ghi
     *     5 -> jkl
     *     6 -> mno
     *     7 -> pqrs
     *     8 -> tuv
     *     9 -> wxyz
     *
     * Example:
     *
     *     digits = "23"
     *
     *     Output:
     *     [ad, ae, af,
     *      bd, be, bf,
     *      cd, ce, cf]
     *
     *
     * ============================================================
     * BACKTRACKING PATTERN
     * ============================================================
     *
     * At every index:
     *
     *     1. Get all possible characters for current digit.
     *     2. Choose one character.
     *     3. Add it to current.
     *     4. Recurse for the next digit.
     *     5. Undo the choice.
     *
     *             CHOOSE
     *                ↓
     *             EXPLORE
     *                ↓
     *              UNDO
     *
     *
     * ============================================================
     * STATE
     * ============================================================
     *
     * We need:
     *
     *     digits  -> original input
     *     index   -> which digit are we processing?
     *     current -> combination currently being built
     *     result  -> all completed combinations
     *
     *
     * ============================================================
     * CHOICES
     * ============================================================
     *
     * At a particular index, choices are the characters mapped
     * to digits[index].
     *
     * Example:
     *
     *     digits[index] = '7'
     *
     *     choices = [p, q, r, s]
     *
     *
     * ============================================================
     * BASE CASE
     * ============================================================
     *
     * When:
     *
     *     index == digits.length()
     *
     * we have processed every digit.
     *
     * Therefore current contains one complete answer.
     *
     *
     * ============================================================
     * WHY BACKTRACKING?
     * ============================================================
     *
     * Suppose:
     *
     *     digits = "23"
     *
     * First choose 'a':
     *
     *     current = "a"
     *
     * Then try:
     *
     *     ad
     *     ae
     *     af
     *
     * After finishing 'a', we REMOVE 'a':
     *
     *     current = ""
     *
     * Now we can try 'b':
     *
     *     bd
     *     be
     *     bf
     *
     * The remove operation is the UNDO step.
     *
     *
     * ============================================================
     * RECURSION TREE
     * ============================================================
     *
     *                         ""
     *                    /     |     \
     *                   a      b      c
     *                 / | \  / | \  / | \
     *                d  e  f d  e  f d  e  f
     *                ↓  ↓  ↓
     *               ad ae af ...
     *
     *
     * ============================================================
     * TIME COMPLEXITY
     * ============================================================
     *
     * Each digit has at most 4 choices.
     *
     * For n digits:
     *
     *     O(4^n * n)
     *
     * The extra n comes from creating/storing each string.
     *
     *
     * SPACE COMPLEXITY
     * ============================================================
     *
     * Recursion depth:
     *
     *     O(n)
     *
     * Result storage:
     *
     *     O(4^n * n)
     *
     * ============================================================
     */

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        String digits = scanner.next("Enter digits: ");

        List<String> result = recursive(
                digits,
                0,
                new StringBuilder(),
                new ArrayList<>()
        );

        Utils.println("Combinations: " + result);
        Utils.println("Count: " + result.size());
    }

    /*
     * ------------------------------------------------------------
     * BACKTRACKING FUNCTION
     * ------------------------------------------------------------
     *
     * index:
     *     Current digit we are processing.
     *
     * current:
     *     Partial combination being built.
     *
     * result:
     *     Stores all completed combinations.
     */
    public static List<String> recursive(
            String digits,
            int index,
            StringBuilder current,
            List<String> result) {

        /*
         * BASE CASE
         *
         * No digits left to process.
         *
         * Whatever is inside current is one complete
         * combination.
         */
        if (index == digits.length()) {
            result.add(current.toString());
            return result;
        }

        /*
         * Get the possible characters for the current digit.
         *
         * Example:
         *
         *     digits = "23"
         *     index = 0
         *
         *     digits.charAt(0) = '2'
         *     possibleChars = [a, b, c]
         */
        List<Character> possibleChars =
                getMapping().get(digits.charAt(index));

        /*
         * Try EVERY possible character.
         *
         * This loop represents the CHOICES at this
         * recursion level.
         */
        for (Character possibleChar : possibleChars) {

            // -------------------------------------------------
            // CHOOSE
            // -------------------------------------------------
            //
            // Add the selected character to our current answer.
            //
            current.append(possibleChar);

            // -------------------------------------------------
            // EXPLORE
            // -------------------------------------------------
            //
            // Move to the next digit.
            //
            recursive(
                    digits,
                    index + 1,
                    current,
                    result
            );

            // -------------------------------------------------
            // UNDO / BACKTRACK
            // -------------------------------------------------
            //
            // Remove the character we just chose so that
            // the next choice starts from the previous state.
            //
            current.deleteCharAt(current.length() - 1);
        }

        return result;
    }

    /*
     * ------------------------------------------------------------
     * PHONE KEYPAD MAPPING
     * ------------------------------------------------------------
     *
     * Maps each digit to its possible characters.
     *
     * Example:
     *
     *     '2' -> [a, b, c]
     *     '7' -> [p, q, r, s]
     */
    private static Map<Character, List<Character>> getMapping() {
        Map<Character, List<Character>> mp = new HashMap<>();
        mp.put('2', List.of('a', 'b', 'c'));
        mp.put('3', List.of('d', 'e', 'f'));
        mp.put('4', List.of('g', 'h', 'i'));
        mp.put('5', List.of('j', 'k', 'l'));
        mp.put('6', List.of('m', 'n', 'o'));
        mp.put('7', List.of('p', 'q', 'r', 's'));
        mp.put('8', List.of('t', 'u', 'v'));
        mp.put('9', List.of('w', 'x', 'y', 'z'));
        return mp;
    }
}