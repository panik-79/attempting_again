package recursion.medium.backtracking;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/restore-ip-addresses/description/
public class RestoreIPAddresses {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        String s = scanner.next("Enter string: ");

        List<String> result = restoreIpAddresses(
                s,
                0,
                new ArrayList<>(),
                new ArrayList<>()
        );

        Utils.println("IP Addresses: " + result);
        Utils.println("Count: " + result.size());
    }

    /*
     * ============================================================
     * BACKTRACKING MODEL
     * ============================================================
     *
     * STATE:
     *
     *     index
     *         -> where the unprocessed part of the string starts
     *
     *     current
     *         -> IP parts chosen so far
     *
     *     result
     *         -> all valid IP addresses
     *
     *
     * CHOICES:
     *
     *     Choose every possible substring starting from index.
     *
     *     Example:
     *
     *         str = "255..."
     *         index = 0
     *
     *         "2"
     *         "25"
     *         "255"
     *
     *
     * VALID CHOICE:
     *
     *     The chosen substring must be a valid IP segment:
     *
     *         - 1 to 3 digits
     *         - value <= 255
     *         - no leading zero
     *
     *
     * BASE CASE:
     *
     *     When index reaches the end of the string,
     *     the entire string has been consumed.
     *
     *     It is a valid answer only when exactly 4
     *     IP parts have been chosen.
     *
     *
     * CHOOSE:
     *
     *     current.add(part)
     *
     *
     * EXPLORE:
     *
     *     Recursively solve the remaining string.
     *
     *
     * UNDO:
     *
     *     current.removeLast()
     *
     *
     * IMPORTANT:
     *
     *     `i` is the exclusive endpoint.
     *
     *         substring(index, i)
     *
     *     Therefore the next recursive call starts at `i`.
     *
     * ============================================================
     */
    public static List<String> restoreIpAddresses(
            String str,
            int index,
            List<String> current,
            List<String> result) {

        // Entire string consumed
        if (index == str.length()) {
            if (current.size() == 4) {
                result.add(generateIP(current));
            }
            return result;
        }

        // Already have 4 parts
        if (current.size() == 4) {
            return result;
        }

        // i = exclusive endpoint
        for (int i = index + 1; i <= str.length(); i++) {

            String part = str.substring(index, i);

            // Invalid IP part
            if (!isValid(part)) {
                continue;
            }

            // Choose
            current.add(part);

            // Explore
            restoreIpAddresses(str, i, current, result);

            // Undo
            current.removeLast();
        }

        return result;
    }

    private static boolean isValid(String s) {
        // Leading zero
        if (s.startsWith("0") && s.length() > 1) {
            return false;
        }
        // IP part can have max 3 digits
        if (s.length() > 3) {
            return false;
        }
        int value = Integer.parseInt(s);
        return value <= 255;
    }

    private static String generateIP(List<String> parts) {
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            result.append(part).append(".");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}