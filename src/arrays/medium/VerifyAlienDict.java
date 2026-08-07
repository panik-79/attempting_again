package arrays.medium;

import util.Utils;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/verifying-an-alien-dictionary/
public class VerifyAlienDict {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt();
        String[] words = scanner.nextStringArray(n);
        String order = scanner.next();

        boolean isAlienDictSorted = isSorted(words, order);
        Utils.println("isSorted: " + isAlienDictSorted);
    }

    public static boolean isSorted(String[] words, String order) {

        Map<Character, Integer> mp = new HashMap<>(26);

        for (int i = 0; i < 26; i++) {
            mp.put(order.charAt(i), i);
        }

        for (int i = 0; i < words.length - 1; i++) {

            String word1 = words[i];
            String word2 = words[i + 1];

            int minLength = Math.min(word1.length(), word2.length());
            boolean foundDifference = false;

            for (int j = 0; j < minLength; j++) {

                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);

                if (c1 != c2) {
                    if (mp.get(c1) > mp.get(c2)) {
                        return false;
                    }

                    foundDifference = true;
                    break; // First different character decides the order
                }
            }

            // If all compared characters are the same,
            // the shorter word should come first.
            if (!foundDifference && word1.length() > word2.length()) {
                return false;
            }
        }

        return true;
    }

}
