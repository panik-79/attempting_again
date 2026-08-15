package recursion.medium.backtracking;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/palindrome-partitioning/description/
// https://takeuforward.org/plus/dsa/problems/palindrome-partitioning
public class PalindromePartitioning {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        String str = scanner.next("Enter string: ");

        List<List<String>> result = partition(
                str,
                0,
                new ArrayList<>(),
                new ArrayList<>()
        );

        Utils.println("Partitions: " + result);
        Utils.println("Count: " + result.size());
    }


    /*
     * ============================================================
     * PROBLEM
     * ============================================================
     *
     * Partition the string into substrings such that EVERY
     * substring in the partition is a palindrome.
     *
     * Example:
     *
     *     s = "aab"
     *
     *     [
     *         ["a", "a", "b"],
     *         ["aa", "b"]
     *     ]
     *
     *
     * ============================================================
     * BACKTRACKING IDEA
     * ============================================================
     *
     * At index `index`, try every possible substring starting
     * from that index.
     *
     * Example:
     *
     *     s = "aab"
     *          ↑
     *        index = 0
     *
     * Possible choices:
     *
     *     "a"
     *     "aa"
     *     "aab"
     *
     * Only choose the substring if it is a palindrome.
     *
     *
     * ============================================================
     * STATE
     * ============================================================
     *
     * index:
     *     Where the unprocessed part of the string starts.
     *
     * current:
     *     Palindromic substrings chosen so far.
     *
     * result:
     *     All valid partitions.
     *
     *
     * ============================================================
     * CHOICE
     * ============================================================
     *
     * Choose a substring:
     *
     *     s[index ... end]
     *
     * where `end` can be anywhere from:
     *
     *     index → s.length() - 1
     *
     *
     * ============================================================
     * VALID CHOICE
     * ============================================================
     *
     * The substring must be a palindrome.
     *
     *
     * ============================================================
     * BASE CASE
     * ============================================================
     *
     * When:
     *
     *     index == s.length()
     *
     * the entire string has been partitioned.
     *
     * Since we only allowed palindromic substrings into
     * `current`, `current` is a valid answer.
     *
     *
     * ============================================================
     * BACKTRACKING
     * ============================================================
     *
     * CHOOSE:
     *
     *     current.add(substring)
     *
     * EXPLORE:
     *
     *     recurse from the next index
     *
     * UNDO:
     *
     *     current.removeLast()
     *
     *
     * ============================================================
     */

    public static List<List<String>> partition(
            String str,
            int index,
            List<String> current,
            List<List<String>> result) {

        if (index == str.length()) {
            result.add(new ArrayList<>(current));
            return result;
        }

        for (int i = index + 1; i <= str.length(); i++) {

            String s = str.substring(index, i);

            if (!Utils.isPalindrome(s)) {
                continue;
            }

            // CHOOSE
            current.add(s);

            // EXPLORE
            partition(str, i, current, result);

            // UNDO
            current.removeLast();
        }

        return result;
    }

}