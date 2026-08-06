package arrays.medium;

import util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/group-anagrams/description/
public class GroupAnagrams {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt();
        String[] arr = scanner.nextStringArray(size);

        Utils.printArray(arr);
        List<List<String>> ans = groupAnagrams(arr);
        Utils.print(ans);
    }

    public static List<List<String>> groupAnagrams(String[] arr) {

        Map<String, List<String>> mp = new HashMap<>();

        for (String s : arr) {
            String binaryCode = getBinaryCode(s);
            mp.computeIfAbsent(binaryCode, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(mp.values());
    }

    private static String getBinaryCode(String s) {
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder key = new StringBuilder();

        for (int x : count) {
            key.append(x).append('#');
        }

        return key.toString();
    }

}
