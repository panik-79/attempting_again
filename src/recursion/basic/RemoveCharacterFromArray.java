package recursion.basic;

import util.Utils;

public class RemoveCharacterFromArray {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        String str = scanner.next("Enter string: ");
        char target = scanner.next("Enter target char: ").charAt(0);

        char[] arr = str.toCharArray();

        int newSize = recursive(arr, 0, target, 0);

        Utils.println("Result: " + new String(arr, 0, newSize));
    }

    // Time  : O(n)
    // Space : O(n) due to recursion stack.
    public static int recursive(
            char[] arr,
            int index,
            char target,
            int writeIndex) {

        // Reached the end; writeIndex is the new logical size.
        if (index == arr.length) {
            return writeIndex;
        }

        // Keep the current character if it is not the target.
        if (arr[index] != target) {
            arr[writeIndex++] = arr[index];
        }

        // Process the next character.
        return recursive(arr, index + 1, target, writeIndex);
    }
}