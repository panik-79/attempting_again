package arrays.medium;

import util.Utils;

import java.util.Arrays;

// https://takeuforward.org/plus/dsa/problems/left-rotate-array?subject=dsa
public class LeftRotateArrByK {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);
        int k = scanner.nextInt();

        if (size <= 0) {
            Utils.println("Empty input array");
            return;
        } else {
            Utils.println("Input array: " + Arrays.toString(arr));
        }

        leftRotateByK(arr, k);
        Utils.println("Array after rotating by " + k + " places: " + Arrays.toString(arr));
    }


    // Optimal solution using reversal algorithm : TC = O(n)
    public static void leftRotateByK(int[] arr, int k) {

        int n = arr.length;
        k = k % n;

        Utils.reverse(arr, 0, k - 1);
        Utils.reverse(arr, k, n - 1);
        Utils.reverse(arr, 0, n - 1);

        /*
        For RIGHT rotation using reversal algorithm:
        reverse(arr, 0, n - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
        */
    }

    // Naive solution : TC = O(nk)
    public static void leftRotateByKnaive(int[] arr, int k) {

        k = k % arr.length;

        while (k > 0) {
            LeftRotateArrByOne.leftRotate(arr);
            k--;
        }

        /*
        For RIGHT rotation (naive):
        Use a function that rotates array right by 1.
        Then repeat it k times.
        */
    }


    // Cyclic Replacement Method (Index Mapping)
    // TC = O(n)  SC = O(1)
    public static void leftRotateByKCyclic(int[] arr, int k) {

        int n = arr.length;
        k = k % n;

        int count = 0;

        for (int i = 0; count < n; i++) {

            int current = i;
            int prev = arr[i];

            do {

                int next = (current - k + n) % n;

                int temp = arr[next];
                arr[next] = prev;
                prev = temp;

                current = next;
                count++;

            } while (i != current);
        }

        /*
        For RIGHT rotation using cyclic replacement:
        change index mapping to:
        next = (current + k) % n
        because right rotation moves elements forward.
        */
    }

}


/*
NOTE: Cyclic replacement method

For left rotation by k positions, the new index of an element is:

    newIndex = (i - k + n) % n

where:
i = current index
k = number of rotations
n = length of the array

Explanation:
Left rotation shifts elements toward the beginning of the array.
So the index decreases by k.

However, this may produce a negative index, which is invalid.
To avoid negative indices, we add n before applying modulo.

Example:
arr = [1,2,3,4,5]
n = 5
k = 2

index mapping:

0 -> (0 - 2 + 5) % 5 = 3
1 -> (1 - 2 + 5) % 5 = 4
2 -> (2 - 2 + 5) % 5 = 0
3 -> (3 - 2 + 5) % 5 = 1
4 -> (4 - 2 + 5) % 5 = 2

Result:
[3,4,5,1,2]

Why we add n:
Without adding n, negative indices may occur.

Example:
i = 0, k = 2

0 - 2 = -2 (invalid index)

Adding n fixes it:
-2 + 5 = 3

So the element wraps around to the end of the array.

Key Idea:
Modulo (%) ensures the index stays within array bounds [0, n-1].
*/