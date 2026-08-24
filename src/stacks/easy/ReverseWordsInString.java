package stacks.easy;

import util.Utils;

import java.util.Stack;

// https://leetcode.com/problems/reverse-words-in-a-string/
public class ReverseWordsInString {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        String s = scanner.next("Enter string: ");
        String result = reverseWords(s);
        Utils.println("Result: " + result);
    }


    /*
     * ============================================================
     * PROBLEM
     * ============================================================
     *
     * Given a string s, reverse the order of the words.
     *
     * A word is a sequence of non-space characters.
     *
     * The returned string must:
     *
     * - Have the words in reverse order.
     * - Have exactly one space between words.
     * - Have no leading spaces.
     * - Have no trailing spaces.
     *
     */


    public static String reverseWords(String s) {
        s = s.trim();

        Stack<String> stack = new Stack<>();

        StringBuilder ans = new StringBuilder();
        StringBuilder currentWord = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == ' ') {

                // Ignore consecutive spaces
                if (!currentWord.isEmpty()) {
                    stack.push(currentWord.toString());
                    currentWord.setLength(0);
                }

            } else {
                currentWord.append(ch);
            }
        }

        // Add last word
        if (!currentWord.isEmpty()) {
            stack.push(currentWord.toString());
        }

        while (!stack.isEmpty()) {

            ans.append(stack.pop());

            if (!stack.isEmpty()) {
                ans.append(" ");
            }
        }

        return ans.toString();
    }
}