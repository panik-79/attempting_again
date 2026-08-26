package strings.basic;

import util.Utils;

// https://leetcode.com/problems/faulty-keyboard/
public class FaultyKeyboard {
    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        String s = scanner.nextLine("Enter string: ");
        String result = solve(s);
        Utils.println("Result: " + result);
    }

    public static String solve(String s) {
        String ans = "";

        for (char ch : s.toCharArray()) {
            if (ch == 'i') {
                ans = reversed(ans);
            } else {
                ans += ch;
            }
        }

        return ans;
    }

    public static String reversed(String s) {

        StringBuilder reversed = new StringBuilder();

        for (int i = s.length() - 1; i >= 0; i--) {
            reversed.append(s.charAt(i));
        }

        return reversed.toString();
    }
}
