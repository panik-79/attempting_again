package util;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/**
 * ╔══════════════════════════════════════════════════════╗
 * ║              ArrayUtil — Java Array Toolkit          ║
 * ║  Generation · Conversion · Sorting · Search · More  ║
 * ╚══════════════════════════════════════════════════════╝
 *
 * A comprehensive utility class for int[], Integer[], double[],
 * String[], and generic arrays.
 *
 * Usage:
 *   int[] arr = ArrayUtil.randomIntArray(10, 1, 100);
 *   ArrayUtil.sortAsc(arr);
 *   System.out.println(ArrayUtil.toString(arr));
 */
public final class ArrayUtils {

    private static final Random RANDOM = new Random();

    // Prevent instantiation
    private ArrayUtils() {
        throw new UnsupportedOperationException("ArrayUtil is a static utility class.");
    }


    // ═══════════════════════════════════════════════════════════════
    //  SECTION 1 ── RANDOM ARRAY GENERATORS
    // ═══════════════════════════════════════════════════════════════

    /**
     * Generates an unsorted int[] with random values in [min, max].
     */
    public static int[] randomIntArray(int size, int min, int max) {
        validateSize(size);
        validateRange(min, max);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RANDOM.nextInt(max - min + 1) + min;
        }
        return arr;
    }

    /**
     * Generates a sorted (ascending) int[] with random values in [min, max].
     */
    public static int[] randomSortedIntArray(int size, int min, int max) {
        int[] arr = randomIntArray(size, min, max);
        Arrays.sort(arr);
        return arr;
    }

    /**
     * Generates a sorted (descending) int[] with random values in [min, max].
     */
    public static int[] randomSortedDescIntArray(int size, int min, int max) {
        int[] arr = randomSortedIntArray(size, min, max);
        reverseInPlace(arr);
        return arr;
    }

    /**
     * Generates an unsorted double[] with random values in [min, max).
     */
    public static double[] randomDoubleArray(int size, double min, double max) {
        validateSize(size);
        if (min >= max) throw new IllegalArgumentException("min must be < max");
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = min + (max - min) * RANDOM.nextDouble();
        }
        return arr;
    }

    /**
     * Generates a sorted (ascending) double[] with random values in [min, max).
     */
    public static double[] randomSortedDoubleArray(int size, double min, double max) {
        double[] arr = randomDoubleArray(size, min, max);
        Arrays.sort(arr);
        return arr;
    }

    /**
     * Generates an int[] with unique random values (no duplicates) in [min, max].
     * Requires (max - min + 1) >= size.
     */
    public static int[] randomUniqueIntArray(int size, int min, int max) {
        validateSize(size);
        int range = max - min + 1;
        if (range < size) {
            throw new IllegalArgumentException(
                    "Range [" + min + ", " + max + "] too small for " + size + " unique values.");
        }
        List<Integer> pool = new ArrayList<>();
        for (int i = min; i <= max; i++) pool.add(i);
        Collections.shuffle(pool, RANDOM);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = pool.get(i);
        return arr;
    }

    /**
     * Generates a String[] with random lowercase alphabetic strings of given length.
     */
    public static String[] randomStringArray(int size, int wordLength) {
        validateSize(size);
        String[] arr = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder(wordLength);
            for (int j = 0; j < wordLength; j++) {
                sb.append((char) ('a' + RANDOM.nextInt(26)));
            }
            arr[i] = sb.toString();
        }
        return arr;
    }

    /**
     * Generates an int[] from a range [start, end] inclusive, step = 1.
     * Example: range(1, 5) → [1, 2, 3, 4, 5]
     */
    public static int[] range(int start, int end) {
        return range(start, end, 1);
    }

    /**
     * Generates an int[] from a range [start, end] with a custom step.
     * Example: range(0, 10, 2) → [0, 2, 4, 6, 8, 10]
     */
    public static int[] range(int start, int end, int step) {
        if (step <= 0) throw new IllegalArgumentException("Step must be positive.");
        int size = (int) Math.ceil((end - start + 1.0) / step);
        if (size <= 0) return new int[0];
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = start + i * step;
        return arr;
    }

    /**
     * Generates an int[] filled with a constant value.
     */
    public static int[] filled(int size, int value) {
        validateSize(size);
        int[] arr = new int[size];
        Arrays.fill(arr, value);
        return arr;
    }


    // ═══════════════════════════════════════════════════════════════
    //  SECTION 2 ── CONVERTERS
    // ═══════════════════════════════════════════════════════════════

    /** int[] → ArrayList<Integer> */
    public static ArrayList<Integer> toArrayList(int[] arr) {
        requireNonNull(arr);
        ArrayList<Integer> list = new ArrayList<>(arr.length);
        for (int v : arr) list.add(v);
        return list;
    }

    /** ArrayList<Integer> → int[] */
    public static int[] toIntArray(ArrayList<Integer> list) {
        requireNonNull(list);
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) arr[i] = list.get(i);
        return arr;
    }

    /** int[] → Integer[] (boxed) */
    public static Integer[] toBoxedArray(int[] arr) {
        requireNonNull(arr);
        Integer[] boxed = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) boxed[i] = arr[i];
        return boxed;
    }

    /** Integer[] → int[] (unboxed) */
    public static int[] toUnboxedArray(Integer[] arr) {
        requireNonNull(arr);
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) throw new NullPointerException("Null element at index " + i);
            result[i] = arr[i];
        }
        return result;
    }

    /** int[] → List<Integer> (immutable) */
    public static List<Integer> toList(int[] arr) {
        requireNonNull(arr);
        return IntStream.of(arr).boxed().collect(Collectors.toList());
    }

    /** List<Integer> → int[] */
    public static int[] toIntArray(List<Integer> list) {
        requireNonNull(list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /** int[] → double[] */
    public static double[] toDoubleArray(int[] arr) {
        requireNonNull(arr);
        double[] result = new double[arr.length];
        for (int i = 0; i < arr.length; i++) result[i] = arr[i];
        return result;
    }

    /** double[] → int[] (truncates decimals) */
    public static int[] toIntArray(double[] arr) {
        requireNonNull(arr);
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) result[i] = (int) arr[i];
        return result;
    }

    /** int[] → String[] */
    public static String[] toStringArray(int[] arr) {
        requireNonNull(arr);
        String[] result = new String[arr.length];
        for (int i = 0; i < arr.length; i++) result[i] = String.valueOf(arr[i]);
        return result;
    }

    /** String[] → int[] (parses each element) */
    public static int[] toIntArray(String[] arr) {
        requireNonNull(arr);
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            try {
                result[i] = Integer.parseInt(arr[i].trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Cannot parse \"" + arr[i] + "\" at index " + i);
            }
        }
        return result;
    }

    /** int[] → int[][] (matrix, row-major) */
    public static int[][] toMatrix(int[] arr, int cols) {
        requireNonNull(arr);
        if (cols <= 0) throw new IllegalArgumentException("cols must be > 0");
        int rows = (int) Math.ceil((double) arr.length / cols);
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < arr.length; i++) matrix[i / cols][i % cols] = arr[i];
        return matrix;
    }

    /** int[][] → int[] (flatten) */
    public static int[] flatten(int[][] matrix) {
        requireNonNull(matrix);
        int total = 0;
        for (int[] row : matrix) total += row.length;
        int[] result = new int[total];
        int idx = 0;
        for (int[] row : matrix) for (int v : row) result[idx++] = v;
        return result;
    }


    // ═══════════════════════════════════════════════════════════════
    //  SECTION 3 ── SORTING
    // ═══════════════════════════════════════════════════════════════

    /** Sort int[] ascending (in-place). */
    public static void sortAsc(int[] arr) {
        requireNonNull(arr);
        Arrays.sort(arr);
    }

    /** Sort int[] descending (in-place). */
    public static void sortDesc(int[] arr) {
        requireNonNull(arr);
        Arrays.sort(arr);
        reverseInPlace(arr);
    }

    /** Sort double[] ascending (in-place). */
    public static void sortAsc(double[] arr) {
        requireNonNull(arr);
        Arrays.sort(arr);
    }

    /** Sort double[] descending (in-place). */
    public static void sortDesc(double[] arr) {
        requireNonNull(arr);
        Arrays.sort(arr);
        reverseInPlace(arr);
    }

    /** Sort String[] ascending (in-place, lexicographic). */
    public static void sortAsc(String[] arr) {
        requireNonNull(arr);
        Arrays.sort(arr);
    }

    /** Sort String[] descending (in-place, lexicographic). */
    public static void sortDesc(String[] arr) {
        requireNonNull(arr);
        Arrays.sort(arr, Comparator.reverseOrder());
    }

    /** Sort any T[] ascending with a custom comparator (in-place). */
    public static <T> void sort(T[] arr, Comparator<T> comparator) {
        requireNonNull(arr);
        requireNonNull(comparator);
        Arrays.sort(arr, comparator);
    }

    /** Returns a new sorted (ascending) copy of the array without modifying the original. */
    public static int[] sortedCopy(int[] arr) {
        requireNonNull(arr);
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        return copy;
    }

    /** Returns a new sorted (descending) copy of the array without modifying the original. */
    public static int[] sortedDescCopy(int[] arr) {
        int[] copy = sortedCopy(arr);
        reverseInPlace(copy);
        return copy;
    }

    // ── Specific sorting algorithms ──────────────────────────────

    /** Bubble sort ascending (in-place). O(n²). */
    public static void bubbleSortAsc(int[] arr) {
        requireNonNull(arr);
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) swap(arr, j, j + 1);
            }
        }
    }

    /** Selection sort ascending (in-place). O(n²). */
    public static void selectionSortAsc(int[] arr) {
        requireNonNull(arr);
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            swap(arr, i, minIdx);
        }
    }

    /** Insertion sort ascending (in-place). O(n²), fast for small/nearly-sorted arrays. */
    public static void insertionSortAsc(int[] arr) {
        requireNonNull(arr);
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i], j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j--];
            }
            arr[j + 1] = key;
        }
    }

    /** Merge sort ascending (returns new sorted array). O(n log n). */
    public static int[] mergeSortAsc(int[] arr) {
        requireNonNull(arr);
        if (arr.length <= 1) return Arrays.copyOf(arr, arr.length);
        int mid = arr.length / 2;
        int[] left = mergeSortAsc(Arrays.copyOfRange(arr, 0, mid));
        int[] right = mergeSortAsc(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(left, right);
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            result[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
        return result;
    }

    /** Quick sort ascending (in-place). O(n log n) average. */
    public static void quickSortAsc(int[] arr) {
        requireNonNull(arr);
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) swap(arr, ++i, j);
        }
        swap(arr, i + 1, high);
        return i + 1;
    }


    // ═══════════════════════════════════════════════════════════════
    //  SECTION 4 ── SWAPS & MANIPULATION
    // ═══════════════════════════════════════════════════════════════

    /** Swap two elements in an int[] by index. */
    public static void swap(int[] arr, int i, int j) {
        requireNonNull(arr);
        checkIndex(arr.length, i);
        checkIndex(arr.length, j);
        int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
    }

    /** Swap two elements in a generic T[] by index. */
    public static <T> void swap(T[] arr, int i, int j) {
        requireNonNull(arr);
        checkIndex(arr.length, i);
        checkIndex(arr.length, j);
        T tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
    }

    /** Reverse an int[] in-place. */
    public static void reverseInPlace(int[] arr) {
        requireNonNull(arr);
        int left = 0, right = arr.length - 1;
        while (left < right) swap(arr, left++, right--);
    }

    /** Reverse a double[] in-place. */
    public static void reverseInPlace(double[] arr) {
        requireNonNull(arr);
        int left = 0, right = arr.length - 1;
        while (left < right) {
            double tmp = arr[left]; arr[left] = arr[right]; arr[right] = tmp;
            left++; right--;
        }
    }

    /** Returns a reversed copy of int[] (original unchanged). */
    public static int[] reversedCopy(int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        reverseInPlace(copy);
        return copy;
    }

    /** Rotate array left by k positions (in-place). */
    public static void rotateLeft(int[] arr, int k) {
        requireNonNull(arr);
        if (arr.length == 0) return;
        k = k % arr.length;
        if (k < 0) k += arr.length;
        reverseRange(arr, 0, k - 1);
        reverseRange(arr, k, arr.length - 1);
        reverseRange(arr, 0, arr.length - 1);
    }

    /** Rotate array right by k positions (in-place). */
    public static void rotateRight(int[] arr, int k) {
        rotateLeft(arr, arr.length - k);
    }

    private static void reverseRange(int[] arr, int l, int r) {
        while (l < r) swap(arr, l++, r--);
    }

    /** Shuffle an int[] randomly (Fisher-Yates). */
    public static void shuffle(int[] arr) {
        requireNonNull(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, RANDOM.nextInt(i + 1));
        }
    }

    /** Shuffle a generic T[] randomly (Fisher-Yates). */
    public static <T> void shuffle(T[] arr) {
        requireNonNull(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, RANDOM.nextInt(i + 1));
        }
    }

    /** Returns a shuffled copy of int[]. */
    public static int[] shuffledCopy(int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        shuffle(copy);
        return copy;
    }

    /** Fill a subrange of int[] with a value. */
    public static void fillRange(int[] arr, int fromIndex, int toIndex, int value) {
        requireNonNull(arr);
        Arrays.fill(arr, fromIndex, toIndex, value);
    }

    /** Copy a subarray (inclusive of both ends). */
    public static int[] subArray(int[] arr, int from, int to) {
        requireNonNull(arr);
        return Arrays.copyOfRange(arr, from, to + 1);
    }

    /** Concatenate two int[] arrays into one. */
    public static int[] concat(int[] a, int[] b) {
        requireNonNull(a);
        requireNonNull(b);
        int[] result = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    /** Remove duplicates from int[] (preserves original order). */
    public static int[] deduplicate(int[] arr) {
        requireNonNull(arr);
        LinkedHashSet<Integer> seen = new LinkedHashSet<>();
        for (int v : arr) seen.add(v);
        int[] result = new int[seen.size()];
        int i = 0;
        for (int v : seen) result[i++] = v;
        return result;
    }

    /** Filter int[] by a predicate. */
    public static int[] filter(int[] arr, IntPredicate predicate) {
        requireNonNull(arr);
        return IntStream.of(arr).filter(predicate).toArray();
    }

    /** Map int[] element-wise with a function. */
    public static int[] map(int[] arr, IntUnaryOperator fn) {
        requireNonNull(arr);
        return IntStream.of(arr).map(fn).toArray();
    }

    /** Intersection of two int[] (elements in both). */
    public static int[] intersect(int[] a, int[] b) {
        Set<Integer> setB = new HashSet<>();
        for (int v : b) setB.add(v);
        return IntStream.of(a).filter(setB::contains).distinct().toArray();
    }

    /** Union of two int[] (all unique elements from both). */
    public static int[] union(int[] a, int[] b) {
        Set<Integer> seen = new LinkedHashSet<>();
        for (int v : a) seen.add(v);
        for (int v : b) seen.add(v);
        return seen.stream().mapToInt(Integer::intValue).toArray();
    }

    /** Difference of a - b (elements in a not in b). */
    public static int[] difference(int[] a, int[] b) {
        Set<Integer> setB = new HashSet<>();
        for (int v : b) setB.add(v);
        return IntStream.of(a).filter(v -> !setB.contains(v)).distinct().toArray();
    }


    // ═══════════════════════════════════════════════════════════════
    //  SECTION 5 ── SEARCH
    // ═══════════════════════════════════════════════════════════════

    /** Linear search: returns first index of target, or -1 if not found. */
    public static int linearSearch(int[] arr, int target) {
        requireNonNull(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    /** Linear search for String (case-sensitive). */
    public static int linearSearch(String[] arr, String target) {
        requireNonNull(arr);
        for (int i = 0; i < arr.length; i++) {
            if (Objects.equals(arr[i], target)) return i;
        }
        return -1;
    }

    /**
     * Binary search on a SORTED ascending int[].
     * Returns index of target, or -1 if not found.
     */
    public static int binarySearch(int[] arr, int target) {
        requireNonNull(arr);
        int result = Arrays.binarySearch(arr, target);
        return result >= 0 ? result : -1;
    }

    /**
     * Manual binary search (ascending, sorted).
     * Returns index or -1.
     */
    public static int binarySearchManual(int[] arr, int target) {
        requireNonNull(arr);
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    /** Returns all indices where target appears. */
    public static int[] findAllIndices(int[] arr, int target) {
        requireNonNull(arr);
        return IntStream.range(0, arr.length)
                .filter(i -> arr[i] == target)
                .toArray();
    }

    /** Returns true if the target exists in the array. */
    public static boolean contains(int[] arr, int target) {
        return linearSearch(arr, target) != -1;
    }

    /** Returns true if the target exists in a SORTED array (uses binary search). */
    public static boolean containsSorted(int[] arr, int target) {
        return binarySearch(arr, target) != -1;
    }

    /** Returns the index of the minimum element. */
    public static int indexOfMin(int[] arr) {
        requireNonEmpty(arr);
        int idx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[idx]) idx = i;
        }
        return idx;
    }

    /** Returns the index of the maximum element. */
    public static int indexOfMax(int[] arr) {
        requireNonEmpty(arr);
        int idx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[idx]) idx = i;
        }
        return idx;
    }

    /** Returns all indices where predicate is true. */
    public static int[] findIndices(int[] arr, IntPredicate predicate) {
        requireNonNull(arr);
        return IntStream.range(0, arr.length)
                .filter(i -> predicate.test(arr[i]))
                .toArray();
    }


    // ═══════════════════════════════════════════════════════════════
    //  SECTION 6 ── STATISTICS & AGGREGATION
    // ═══════════════════════════════════════════════════════════════

    /** Returns the minimum value. */
    public static int min(int[] arr) {
        requireNonEmpty(arr);
        return arr[indexOfMin(arr)];
    }

    /** Returns the maximum value. */
    public static int max(int[] arr) {
        requireNonEmpty(arr);
        return arr[indexOfMax(arr)];
    }

    /** Returns the sum of all elements. */
    public static long sum(int[] arr) {
        requireNonNull(arr);
        long s = 0;
        for (int v : arr) s += v;
        return s;
    }

    /** Returns the arithmetic mean. */
    public static double mean(int[] arr) {
        requireNonEmpty(arr);
        return (double) sum(arr) / arr.length;
    }

    /** Returns the median value. */
    public static double median(int[] arr) {
        requireNonEmpty(arr);
        int[] sorted = sortedCopy(arr);
        int n = sorted.length;
        return (n % 2 == 0)
                ? (sorted[n / 2 - 1] + sorted[n / 2]) / 2.0
                : sorted[n / 2];
    }

    /** Returns the mode (most frequent element). Ties: returns smallest. */
    public static int mode(int[] arr) {
        requireNonEmpty(arr);
        Map<Integer, Integer> freq = new HashMap<>();
        for (int v : arr) freq.merge(v, 1, Integer::sum);
        return freq.entrySet().stream()
                .max(Map.Entry.<Integer, Integer>comparingByValue()
                        .thenComparing(Map.Entry.<Integer, Integer>comparingByKey().reversed()))
                .get().getKey();
    }

    /** Returns the population standard deviation. */
    public static double stdDev(int[] arr) {
        requireNonEmpty(arr);
        double m = mean(arr), sum = 0;
        for (int v : arr) sum += (v - m) * (v - m);
        return Math.sqrt(sum / arr.length);
    }

    /** Returns the variance. */
    public static double variance(int[] arr) {
        requireNonEmpty(arr);
        double m = mean(arr), sum = 0;
        for (int v : arr) sum += (v - m) * (v - m);
        return sum / arr.length;
    }

    /** Returns the frequency map of all elements. */
    public static Map<Integer, Integer> frequencyMap(int[] arr) {
        requireNonNull(arr);
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int v : arr) map.merge(v, 1, Integer::sum);
        return map;
    }

    /** Returns the count of occurrences of target. */
    public static int count(int[] arr, int target) {
        requireNonNull(arr);
        int c = 0;
        for (int v : arr) if (v == target) c++;
        return c;
    }

    /** Returns the count of elements matching predicate. */
    public static int countIf(int[] arr, IntPredicate predicate) {
        requireNonNull(arr);
        int c = 0;
        for (int v : arr) if (predicate.test(v)) c++;
        return c;
    }

    /** Returns true if the int[] is sorted in ascending order. */
    public static boolean isSortedAsc(int[] arr) {
        requireNonNull(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) return false;
        }
        return true;
    }

    /** Returns true if the int[] is sorted in descending order. */
    public static boolean isSortedDesc(int[] arr) {
        requireNonNull(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) return false;
        }
        return true;
    }

    /** Returns true if all elements are unique. */
    public static boolean isUnique(int[] arr) {
        requireNonNull(arr);
        Set<Integer> seen = new HashSet<>();
        for (int v : arr) if (!seen.add(v)) return false;
        return true;
    }

    /** Returns true if all elements satisfy the predicate. */
    public static boolean allMatch(int[] arr, IntPredicate predicate) {
        requireNonNull(arr);
        for (int v : arr) if (!predicate.test(v)) return false;
        return true;
    }

    /** Returns true if any element satisfies the predicate. */
    public static boolean anyMatch(int[] arr, IntPredicate predicate) {
        requireNonNull(arr);
        for (int v : arr) if (predicate.test(v)) return true;
        return false;
    }

    /** Returns true if no element satisfies the predicate. */
    public static boolean noneMatch(int[] arr, IntPredicate predicate) {
        return !anyMatch(arr, predicate);
    }


    // ═══════════════════════════════════════════════════════════════
    //  SECTION 7 ── PRINTING & DISPLAY
    // ═══════════════════════════════════════════════════════════════

    /** Returns "[1, 2, 3, ...]" representation. */
    public static String toString(int[] arr) {
        return Arrays.toString(arr);
    }

    /** Returns "[1.0, 2.5, ...]" representation. */
    public static String toString(double[] arr) {
        return Arrays.toString(arr);
    }

    /** Returns "[a, b, c, ...]" representation for any T[]. */
    public static <T> String toString(T[] arr) {
        return Arrays.toString(arr);
    }

    /** Prints the int[] to stdout. */
    public static void print(int[] arr) {
        System.out.println(toString(arr));
    }

    /** Prints the double[] to stdout. */
    public static void print(double[] arr) {
        System.out.println(toString(arr));
    }

    /** Prints any T[] to stdout. */
    public static <T> void print(T[] arr) {
        System.out.println(toString(arr));
    }

    /** Prints a 2D matrix in grid format. */
    public static void printMatrix(int[][] matrix) {
        requireNonNull(matrix);
        for (int[] row : matrix) System.out.println(Arrays.toString(row));
    }

    /** Prints elements one per line with their index. */
    public static void printIndexed(int[] arr) {
        requireNonNull(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("[%d] %d%n", i, arr[i]);
        }
    }

    /** Prints a frequency map. */
    public static void printFrequencyMap(int[] arr) {
        frequencyMap(arr).forEach((k, v) ->
                System.out.printf("%d → %d time(s)%n", k, v));
    }


    // ═══════════════════════════════════════════════════════════════
    //  SECTION 8 ── VALIDATION HELPERS (PRIVATE)
    // ═══════════════════════════════════════════════════════════════

    private static void requireNonNull(Object obj) {
        if (obj == null) throw new NullPointerException("Array must not be null.");
    }

    private static void requireNonEmpty(int[] arr) {
        requireNonNull(arr);
        if (arr.length == 0) throw new IllegalArgumentException("Array must not be empty.");
    }

    private static void validateSize(int size) {
        if (size < 0) throw new IllegalArgumentException("Size must be >= 0, got: " + size);
    }

    private static void validateRange(int min, int max) {
        if (min > max) throw new IllegalArgumentException("min (" + min + ") must be <= max (" + max + ")");
    }

    private static void checkIndex(int length, int index) {
        if (index < 0 || index >= length)
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
    }


    // ═══════════════════════════════════════════════════════════════
    //  SECTION 9 ── DEMO MAIN
    // ═══════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════╗");
        System.out.println("║      ArrayUtil  Demo           ║");
        System.out.println("╚═══════════════════════════════╝\n");

        // Generation
        int[] rand   = randomIntArray(10, 1, 50);
        int[] unique = randomUniqueIntArray(8, 1, 20);
        int[] r      = range(1, 10);
        System.out.println("Random      : " + toString(rand));
        System.out.println("Unique      : " + toString(unique));
        System.out.println("Range(1-10) : " + toString(r));

        // Sorting
        int[] asc  = sortedCopy(rand);
        int[] desc = sortedDescCopy(rand);
        System.out.println("\nSorted Asc  : " + toString(asc));
        System.out.println("Sorted Desc : " + toString(desc));

        // Statistics
        System.out.println("\nMin    : " + min(rand));
        System.out.println("Max    : " + max(rand));
        System.out.println("Sum    : " + sum(rand));
        System.out.printf ("Mean   : %.2f%n", mean(rand));
        System.out.printf ("Median : %.1f%n", median(rand));
        System.out.printf ("StdDev : %.2f%n", stdDev(rand));

        // Manipulation
        int[] copy = Arrays.copyOf(rand, rand.length);
        rotateLeft(copy, 3);
        System.out.println("\nRotate Left(3) : " + toString(copy));
        shuffle(copy);
        System.out.println("Shuffled       : " + toString(copy));

        // Search
        int target = rand[0];
        System.out.println("\nSearch target  : " + target);
        System.out.println("Linear index   : " + linearSearch(rand, target));
        System.out.println("Binary index   : " + binarySearch(asc, target));
        System.out.println("Contains       : " + contains(rand, target));

        // Converters
        ArrayList<Integer> list = toArrayList(rand);
        int[] back = toIntArray(list);
        System.out.println("\nArrayList      : " + list);
        System.out.println("Back to int[]  : " + toString(back));

        // Set operations
        int[] a = {1,2,3,4,5};
        int[] b = {3,4,5,6,7};
        System.out.println("\nUnion      : " + toString(union(a, b)));
        System.out.println("Intersect  : " + toString(intersect(a, b)));
        System.out.println("Difference : " + toString(difference(a, b)));

        System.out.println("\n✓ Done.");
    }
}