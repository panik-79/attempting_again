package util;

import java.io.*;
import java.util.*;

public class Utils {

    /* ============================================================
        FAST INPUT
    ============================================================ */

    public static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastScanner(String filename) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(filename));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }

        int[] nextIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = nextInt();
            return arr;
        }

        long[] nextLongArray(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) arr[i] = nextLong();
            return arr;
        }

        int[][] nextIntMatrix(int r, int c) {
            int[][] mat = new int[r][c];
            for (int i = 0; i < r; i++)
                for (int j = 0; j < c; j++)
                    mat[i][j] = nextInt();
            return mat;
        }

        String nextLine() {
            try { return br.readLine(); }
            catch (IOException e) { return ""; }
        }
    }

    /* ============================================================
        FAST OUTPUT
    ============================================================ */

    public static final PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    // Usage: out.println(...); out.flush(); at the end

    public static void print(Object o)   { System.out.print(o); }
    public static void println(Object o) { System.out.println(o); }

    public static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int x : arr) sb.append(x).append(' ');
        System.out.println(sb.toString().trim());
    }

    public static void printArray(long[] arr) {
        StringBuilder sb = new StringBuilder();
        for (long x : arr) sb.append(x).append(' ');
        System.out.println(sb.toString().trim());
    }

    public static void print2D(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : arr) {
            for (int x : row) sb.append(x).append(' ');
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public static void printList(List<?> list) {
        StringBuilder sb = new StringBuilder();
        for (Object o : list) sb.append(o).append(' ');
        System.out.println(sb.toString().trim());
    }

    public static void printMap(Map<?, ?> map) {
        for (Map.Entry<?, ?> e : map.entrySet())
            System.out.println(e.getKey() + " -> " + e.getValue());
    }

    /* ============================================================
        ARRAY UTILS
    ============================================================ */

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
    }

    public static void reverse(int[] arr, int l, int r) {
        while (l < r) swap(arr, l++, r--);
    }

    public static int max(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int x : arr) max = Math.max(max, x);
        return max;
    }

    public static int min(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int x : arr) min = Math.min(min, x);
        return min;
    }

    public static long sum(int[] arr) {
        long s = 0;
        for (int x : arr) s += x;
        return s;
    }

    // Prefix sum array (1-indexed: prefix[i] = sum of arr[0..i-1])
    public static long[] prefixSum(int[] arr) {
        int n = arr.length;
        long[] pre = new long[n + 1];
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + arr[i];
        return pre;
    }

    // Range sum [l, r] inclusive using prefix sum (1-indexed prefix)
    public static long rangeSum(long[] prefix, int l, int r) {
        return prefix[r + 1] - prefix[l];
    }

    // 2D prefix sum
    public static long[][] prefixSum2D(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        long[][] pre = new long[r + 1][c + 1];
        for (int i = 1; i <= r; i++)
            for (int j = 1; j <= c; j++)
                pre[i][j] = grid[i-1][j-1] + pre[i-1][j] + pre[i][j-1] - pre[i-1][j-1];
        return pre;
    }

    // Range sum in rectangle [r1,c1] to [r2,c2] (0-indexed input)
    public static long rangeSum2D(long[][] pre, int r1, int c1, int r2, int c2) {
        return pre[r2+1][c2+1] - pre[r1][c2+1] - pre[r2+1][c1] + pre[r1][c1];
    }

    // Count of element x in sorted array
    public static int countInSorted(int[] arr, int x) {
        return upperBound(arr, x) - lowerBound(arr, x);
    }

    // Converts int[] to Integer[] for use with Arrays.sort(arr, Comparator)
    public static Integer[] boxed(int[] arr) {
        Integer[] result = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) result[i] = arr[i];
        return result;
    }

    // Frequency map from array
    public static Map<Integer, Integer> freqMap(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : arr) map.merge(x, 1, Integer::sum);
        return map;
    }

    /* ============================================================
        MATH UTILS
    ============================================================ */

    public static long gcd(long a, long b) { return b == 0 ? a : gcd(b, a % b); }
    public static long lcm(long a, long b) { return a / gcd(a, b) * b; }

    public static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (long i = 5; i * i <= n; i += 6)
            if (n % i == 0 || n % (i + 2) == 0) return false;
        return true;
    }

    // Sieve of Eratosthenes — returns boolean array where sieve[i] = true means prime
    public static boolean[] sieve(int limit) {
        boolean[] sieve = new boolean[limit + 1];
        Arrays.fill(sieve, true);
        sieve[0] = sieve[1] = false;
        for (int i = 2; (long) i * i <= limit; i++)
            if (sieve[i])
                for (int j = i * i; j <= limit; j += i)
                    sieve[j] = false;
        return sieve;
    }

    // Smallest prime factor sieve — for fast factorization
    public static int[] spf(int limit) {
        int[] spf = new int[limit + 1];
        for (int i = 0; i <= limit; i++) spf[i] = i;
        for (int i = 2; (long) i * i <= limit; i++)
            if (spf[i] == i) // i is prime
                for (int j = i * i; j <= limit; j += i)
                    if (spf[j] == j) spf[j] = i;
        return spf;
    }

    // Prime factorization using SPF sieve
    public static Map<Integer, Integer> factorize(int n, int[] spf) {
        Map<Integer, Integer> factors = new LinkedHashMap<>();
        while (n > 1) {
            int p = spf[n];
            while (n % p == 0) {
                factors.merge(p, 1, Integer::sum);
                n /= p;
            }
        }
        return factors;
    }

    // Fast modular exponentiation: (base^exp) % mod
    public static long powMod(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return result;
    }

    // Power without mod (be careful of overflow)
    public static long pow(long base, long exp) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) result *= base;
            base *= base;
            exp >>= 1;
        }
        return result;
    }

    // Modular inverse using Fermat's little theorem (mod must be prime)
    public static long modInverse(long a, long mod) {
        return powMod(a, mod - 2, mod);
    }

    // Extended GCD: returns {gcd, x, y} such that a*x + b*y = gcd(a, b)
    public static long[] extGcd(long a, long b) {
        if (b == 0) return new long[]{a, 1, 0};
        long[] r = extGcd(b, a % b);
        return new long[]{r[0], r[2], r[1] - (a / b) * r[2]};
    }

    // Number of digits in n (base 10)
    public static int numDigits(long n) {
        if (n == 0) return 1;
        int count = 0;
        if (n < 0) n = -n;
        while (n > 0) { count++; n /= 10; }
        return count;
    }

    // Check if n is a perfect square
    public static boolean isPerfectSquare(long n) {
        if (n < 0) return false;
        long s = (long) Math.sqrt(n);
        return s * s == n || (s + 1) * (s + 1) == n;
    }

    /* ============================================================
        COMBINATORICS (with modular arithmetic)
    ============================================================ */

    static final long MOD = 1_000_000_007L;

    // Precompute factorials and inverse factorials for nCr mod p
    static long[] fact, inv_fact;

    public static void precomputeFactorials(int n) {
        fact = new long[n + 1];
        inv_fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = fact[i - 1] * i % MOD;
        inv_fact[n] = modInverse(fact[n], MOD);
        for (int i = n - 1; i >= 0; i--) inv_fact[i] = inv_fact[i + 1] * (i + 1) % MOD;
    }

    // nCr mod p (call precomputeFactorials first)
    public static long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        return fact[n] * inv_fact[r] % MOD * inv_fact[n - r] % MOD;
    }

    // nPr mod p (call precomputeFactorials first)
    public static long nPr(int n, int r) {
        if (r < 0 || r > n) return 0;
        return fact[n] * inv_fact[n - r] % MOD;
    }

    /* ============================================================
        BINARY SEARCH
    ============================================================ */

    // First index where arr[i] >= target (like C++ lower_bound)
    public static int lowerBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    // First index where arr[i] > target (like C++ upper_bound)
    public static int upperBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] <= target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    // Binary search on answer: find smallest x in [lo, hi] where condition(x) is true
    // Replace the lambda body with your predicate
    @FunctionalInterface
    public interface LongPredicate { boolean test(long x); }

    public static long binarySearchAnswer(long lo, long hi, LongPredicate condition) {
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (condition.test(mid)) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    /* ============================================================
        SORTING TRICKS
    ============================================================ */

    // Sort int[] in descending order
    public static void sortDesc(int[] arr) {
        // Box, sort reversed, unbox
        Integer[] boxed = boxed(arr);
        Arrays.sort(boxed, Collections.reverseOrder());
        for (int i = 0; i < arr.length; i++) arr[i] = boxed[i];
    }

    // Coordinate compression — returns array where each element is its rank (0-indexed)
    public static int[] compress(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        sorted = Arrays.stream(sorted).distinct().toArray();
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            result[i] = Arrays.binarySearch(sorted, arr[i]);
        return result;
    }

    /* ============================================================
        STRINGS
    ============================================================ */

    // KMP failure function
    public static int[] kmpFailure(String pattern) {
        int m = pattern.length();
        int[] fail = new int[m];
        for (int i = 1; i < m; i++) {
            int j = fail[i - 1];
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) j = fail[j - 1];
            if (pattern.charAt(i) == pattern.charAt(j)) j++;
            fail[i] = j;
        }
        return fail;
    }

    // KMP search: returns list of 0-indexed start positions where pattern occurs in text
    public static List<Integer> kmpSearch(String text, String pattern) {
        int[] fail = kmpFailure(pattern);
        List<Integer> result = new ArrayList<>();
        int n = text.length(), m = pattern.length(), j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) j = fail[j - 1];
            if (text.charAt(i) == pattern.charAt(j)) j++;
            if (j == m) { result.add(i - m + 1); j = fail[j - 1]; }
        }
        return result;
    }

    // Z-function: z[i] = length of longest substring starting from s[i] that is also a prefix of s
    public static int[] zFunction(String s) {
        int n = s.length();
        int[] z = new int[n];
        for (int i = 1, l = 0, r = 0; i < n; i++) {
            if (i < r) z[i] = Math.min(r - i, z[i - l]);
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) z[i]++;
            if (i + z[i] > r) { l = i; r = i + z[i]; }
        }
        return z;
    }

    // Check if s is a palindrome
    public static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) if (s.charAt(l++) != s.charAt(r--)) return false;
        return true;
    }

    // Count character frequencies in a string
    public static int[] charFreq(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;
        return freq;
    }

    /* ============================================================
        BIT MANIPULATION
    ============================================================ */

    public static boolean hasBit(int n, int i)  { return ((n >> i) & 1) == 1; }
    public static int setBit(int n, int i)       { return n | (1 << i); }
    public static int clearBit(int n, int i)     { return n & ~(1 << i); }
    public static int toggleBit(int n, int i)    { return n ^ (1 << i); }
    public static int lowBit(int n)              { return n & (-n); }          // lowest set bit
    public static boolean isPowerOfTwo(int n)    { return n > 0 && (n & (n - 1)) == 0; }
    public static int countBits(int n)           { return Integer.bitCount(n); }

    // Iterate over all submasks of mask
    public static void iterateSubmasks(int mask) {
        for (int sub = mask; sub > 0; sub = (sub - 1) & mask) {
            // process sub
        }
    }

    /* ============================================================
        GRAPH UTILS
    ============================================================ */

    // Build adjacency list (undirected)
    public static List<List<Integer>> buildGraph(int n) {
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i <= n; i++) g.add(new ArrayList<>());
        return g;
    }

    public static void addEdge(List<List<Integer>> g, int u, int v) {
        g.get(u).add(v); g.get(v).add(u);
    }

    // Build weighted graph: g.get(u) holds {v, weight} pairs
    public static List<List<int[]>> buildWeightedGraph(int n) {
        List<List<int[]>> g = new ArrayList<>();
        for (int i = 0; i <= n; i++) g.add(new ArrayList<>());
        return g;
    }

    public static void addWeightedEdge(List<List<int[]>> g, int u, int v, int w) {
        g.get(u).add(new int[]{v, w});
        g.get(v).add(new int[]{u, w});
    }

    // BFS — returns distance array (-1 if unreachable). 1-indexed, n nodes.
    public static int[] bfs(List<List<Integer>> g, int src, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        dist[src] = 0;
        q.add(src);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : g.get(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.add(v);
                }
            }
        }
        return dist;
    }

    // DFS iterative — returns visited array. 1-indexed, n nodes.
    public static boolean[] dfs(List<List<Integer>> g, int src, int n) {
        boolean[] visited = new boolean[n + 1];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(src);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (visited[u]) continue;
            visited[u] = true;
            for (int v : g.get(u))
                if (!visited[v]) stack.push(v);
        }
        return visited;
    }

    // Dijkstra — returns dist array. 1-indexed. g holds {neighbor, weight}.
    public static long[] dijkstra(List<List<int[]>> g, int src, int n) {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, src});
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0]; int u = (int) cur[1];
            if (d > dist[u]) continue;
            for (int[] edge : g.get(u)) {
                int v = edge[0]; long w = edge[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new long[]{dist[v], v});
                }
            }
        }
        return dist;
    }

    // Topological sort (Kahn's algorithm / BFS). Returns empty list if cycle exists.
    public static List<Integer> topoSort(List<List<Integer>> g, int n) {
        int[] indegree = new int[n + 1];
        for (int u = 1; u <= n; u++)
            for (int v : g.get(u)) indegree[v]++;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) if (indegree[i] == 0) q.add(i);
        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (int v : g.get(u)) if (--indegree[v] == 0) q.add(v);
        }
        return order.size() == n ? order : new ArrayList<>();
    }

    // Check if undirected graph is bipartite (BFS coloring). 1-indexed.
    public static boolean isBipartite(List<List<Integer>> g, int n) {
        int[] color = new int[n + 1];
        Arrays.fill(color, -1);
        for (int start = 1; start <= n; start++) {
            if (color[start] != -1) continue;
            Queue<Integer> q = new LinkedList<>();
            q.add(start); color[start] = 0;
            while (!q.isEmpty()) {
                int u = q.poll();
                for (int v : g.get(u)) {
                    if (color[v] == -1) { color[v] = 1 - color[u]; q.add(v); }
                    else if (color[v] == color[u]) return false;
                }
            }
        }
        return true;
    }

    /* ============================================================
        UNION FIND (DSU)
    ============================================================ */

    public static class DSU {
        int[] parent, rank, size;

        public DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            size = new int[n + 1];
            for (int i = 0; i <= n; i++) { parent[i] = i; size[i] = 1; }
        }

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        public boolean union(int x, int y) {
            int px = find(x), py = find(y);
            if (px == py) return false;
            if (rank[px] < rank[py]) { int t = px; px = py; py = t; }
            parent[py] = px;
            size[px] += size[py];
            if (rank[px] == rank[py]) rank[px]++;
            return true;
        }

        public boolean connected(int x, int y) { return find(x) == find(y); }
        public int size(int x) { return size[find(x)]; }
    }

    /* ============================================================
        SEGMENT TREE (range sum, point update)
    ============================================================ */

    public static class SegTree {
        long[] tree;
        int n;

        public SegTree(int n) {
            this.n = n;
            tree = new long[4 * n];
        }

        public SegTree(int[] arr) {
            n = arr.length;
            tree = new long[4 * n];
            build(arr, 1, 0, n - 1);
        }

        void build(int[] arr, int node, int l, int r) {
            if (l == r) { tree[node] = arr[l]; return; }
            int mid = (l + r) / 2;
            build(arr, 2 * node, l, mid);
            build(arr, 2 * node + 1, mid + 1, r);
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }

        public void update(int idx, long val) { update(1, 0, n - 1, idx, val); }

        void update(int node, int l, int r, int idx, long val) {
            if (l == r) { tree[node] = val; return; }
            int mid = (l + r) / 2;
            if (idx <= mid) update(2 * node, l, mid, idx, val);
            else update(2 * node + 1, mid + 1, r, idx, val);
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }

        public long query(int ql, int qr) { return query(1, 0, n - 1, ql, qr); }

        long query(int node, int l, int r, int ql, int qr) {
            if (ql > r || qr < l) return 0;
            if (ql <= l && r <= qr) return tree[node];
            int mid = (l + r) / 2;
            return query(2 * node, l, mid, ql, qr) + query(2 * node + 1, mid + 1, r, ql, qr);
        }
    }

    /* ============================================================
        BINARY INDEXED TREE / FENWICK TREE (1-indexed)
    ============================================================ */

    public static class BIT {
        long[] tree;
        int n;

        public BIT(int n) { this.n = n; tree = new long[n + 1]; }

        public void update(int i, long delta) {
            for (; i <= n; i += i & (-i)) tree[i] += delta;
        }

        public long query(int i) {
            long sum = 0;
            for (; i > 0; i -= i & (-i)) sum += tree[i];
            return sum;
        }

        // Range sum [l, r] inclusive
        public long query(int l, int r) { return query(r) - query(l - 1); }
    }

    /* ============================================================
        SPARSE TABLE (range min/max query in O(1) after O(n log n) build)
    ============================================================ */

    public static class SparseTable {
        int[][] table;
        int[] log2;
        int n;
        boolean isMin; // true = RMQ min, false = RMQ max

        public SparseTable(int[] arr, boolean isMin) {
            n = arr.length;
            this.isMin = isMin;
            int LOG = 1;
            while ((1 << LOG) <= n) LOG++;
            table = new int[LOG][n];
            log2 = new int[n + 1];
            for (int i = 2; i <= n; i++) log2[i] = log2[i / 2] + 1;
            table[0] = arr.clone();
            for (int j = 1; j < LOG; j++)
                for (int i = 0; i + (1 << j) <= n; i++)
                    table[j][i] = isMin
                            ? Math.min(table[j-1][i], table[j-1][i + (1 << (j-1))])
                            : Math.max(table[j-1][i], table[j-1][i + (1 << (j-1))]);
        }

        // O(1) range min/max query [l, r] inclusive
        public int query(int l, int r) {
            int k = log2[r - l + 1];
            return isMin
                    ? Math.min(table[k][l], table[k][r - (1 << k) + 1])
                    : Math.max(table[k][l], table[k][r - (1 << k) + 1]);
        }
    }

    /* ============================================================
        MONOTONIC STACK UTILS
    ============================================================ */

    // Returns index of next greater element for each i (-1 if none)
    public static int[] nextGreater(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i])
                result[stack.pop()] = i;
            stack.push(i);
        }
        return result;
    }

    // Returns index of previous greater element for each i (-1 if none)
    public static int[] prevGreater(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) stack.pop();
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return result;
    }

    /* ============================================================
        PAIR / TUPLE CLASSES
    ============================================================ */

    public static class Pair<A extends Comparable<A>, B extends Comparable<B>>
            implements Comparable<Pair<A, B>> {
        public A first;
        public B second;

        public Pair(A f, B s) { first = f; second = s; }

        @Override
        public int compareTo(Pair<A, B> o) {
            int c = first.compareTo(o.first);
            return c != 0 ? c : second.compareTo(o.second);
        }

        @Override
        public String toString() { return "(" + first + ", " + second + ")"; }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) return false;
            Pair<?, ?> p = (Pair<?, ?>) o;
            return first.equals(p.first) && second.equals(p.second);
        }

        @Override
        public int hashCode() { return Objects.hash(first, second); }
    }

    public static class Triple<A, B, C> {
        public A first; public B second; public C third;
        public Triple(A a, B b, C c) { first = a; second = b; third = c; }
        @Override public String toString() { return "(" + first + ", " + second + ", " + third + ")"; }
    }

    /* ============================================================
        DEBUG HELPERS
    ============================================================ */

    public static void debug(Object... o) { System.err.println(Arrays.deepToString(o)); }

    // Useful during local testing — prints to stderr so it doesn't interfere with output
    public static <T> void debugArr(T[] arr) { System.err.println(Arrays.toString(arr)); }
    public static void debugArr(int[] arr)   { System.err.println(Arrays.toString(arr)); }
    public static void debugArr(long[] arr)  { System.err.println(Arrays.toString(arr)); }

    /* ============================================================
        COMMON CONSTANTS
    ============================================================ */

    static final int INF  = Integer.MAX_VALUE / 2;
    static final long LINF = Long.MAX_VALUE / 2;
    // static final long MOD = 1_000_000_007L;  // already declared above
    static final long MOD2 = 998_244_353L;

    // 4-directional and 8-directional movement deltas
    static final int[] DX4 = {0, 0, 1, -1};
    static final int[] DY4 = {1, -1, 0, 0};
    static final int[] DX8 = {0, 0, 1, -1, 1, 1, -1, -1};
    static final int[] DY8 = {1, -1, 0, 0, 1, -1, 1, -1};

}