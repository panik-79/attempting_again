package arrays.basic;

import util.Utils;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/roman-to-integer/description/
public class RomanToInteger {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        String romanString = scanner.next("Enter Roman : ");
        int intVal = convert(romanString);
        Utils.print("Int value: " + intVal);
    }

    public static int convert(String romanString) {
        Map<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int ans = 0;

        for (int i = 0; i < romanString.length(); i++) {
            int curr = map.get(romanString.charAt(i));

            if (i + 1 < romanString.length() && curr < map.get(romanString.charAt(i + 1))) {
                ans -= curr;
            } else {
                ans += curr;
            }
        }

        return ans;
    }

}
