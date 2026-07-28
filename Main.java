import java.io.*;
import java.util.*;

class Main{

    public static final boolean DEBUG = true;

    // Modular Arithmetic Utilities (Class: Mod)
    static class Mod {
        public static final long MOD = 1_000_000_007L;

        // Purpose: Modular Addition (a + b) % MOD | Input: long valA, long valB | Output: long
        public static long add(long a, long b) { long res = (a % MOD + b % MOD + MOD) % MOD; DBG.log("Mod.add (" + a + "+" + b + ")", res); return res; }
        // Purpose: Modular Subtraction (a - b) % MOD | Input: long valA, long valB | Output: long
        public static long sub(long a, long b) { long res = (a % MOD - b % MOD + MOD) % MOD; DBG.log("Mod.sub (" + a + "-" + b + ")", res); return res; }
        // Purpose: Modular Multiplication (a * b) % MOD | Input: long valA, long valB | Output: long
        public static long mul(long a, long b) { long res = ((a % MOD) * (b % MOD)) % MOD; DBG.log("Mod.mul (" + a + "*" + b + ")", res); return res; }
        // Purpose: Modular Inverse via Fermat's Little Theorem (a^(MOD-2) % MOD) | Input: long val | Output: long
        public static long inv(long a) { long res = Mth.pow(a, MOD - 2, MOD); DBG.log("Mod.inv (" + a + ")", res); return res; }
        // Purpose: Modular Division (a / b) % MOD via Fermat's Little Theorem | Input: long valA, long valB | Output: long
        public static long div(long a, long b) { long res = mul(a, inv(b)); DBG.log("Mod.div (" + a + "/" + b + ")", res); return res; }
    }

    // Debug Utilities (Class: DBG)
    static class DBG {
        // Purpose: Log variable name and value | Input: String label, Object val | Output: void
        public static void log(String label, Object val) { if (DEBUG) System.err.println("[DEBUG] " + label + " = " + (val instanceof int[] ? Arrays.toString((int[]) val) : val instanceof long[] ? Arrays.toString((long[]) val) : val instanceof Object[] ? Arrays.deepToString((Object[]) val) : val)); }
        // Purpose: Print 2D char matrix | Input: String label, char[][] grid | Output: void
        public static void grid(String label, char[][] g) { if (!DEBUG) return; System.err.println("[DEBUG GRID] " + label + ":"); for (char[] r : g) System.err.println("  " + new String(r)); }
        // Purpose: Log execution milestone | Input: String infoMessage | Output: void
        public static void msg(String info) { if (DEBUG) System.err.println("[DEBUG LOG] " + info); }
    }

    // Edge Case & Test Case Generator (Class: Gen)
    static class Gen {
        private static final Random rnd = new Random();
        // Purpose: Generate random integer in range | Input: int minVal, int maxVal | Output: int
        public static int i(int min, int max) { int val = min + rnd.nextInt(max - min + 1); DBG.log("Gen.i [" + min + "," + max + "]", val); return val; }
        // Purpose: Generate random long in range | Input: long minVal, long maxVal | Output: long
        public static long l(long min, long max) { long val = min + (long)(rnd.nextDouble() * (max - min + 1)); DBG.log("Gen.l [" + min + "," + max + "]", val); return val; }
        // Purpose: Generate random integer array | Input: int size, int minVal, int maxVal | Output: int[]
        public static int[] iArr(int sz, int min, int max) { int[] a = new int[sz]; for (int k = 0; k < sz; k++) a[k] = i(min, max); DBG.log("Gen.iArr", a); return a; }
        // Purpose: Generate edge-case array (0, 1, -1, INT_MAX, INT_MIN) | Input: int size | Output: int[]
        public static int[] edgeArr(int sz) { int[] a = new int[sz]; int[] pool = {0, 1, -1, Integer.MAX_VALUE, Integer.MIN_VALUE}; for (int k = 0; k < sz; k++) a[k] = pool[rnd.nextInt(pool.length)]; DBG.log("Gen.edgeArr", a); return a; }
        // Purpose: Generate random lowercase English string | Input: int length | Output: String
        public static String str(int len) { StringBuilder sb = new StringBuilder(); for (int k = 0; k < len; k++) sb.append((char)('a' + rnd.nextInt(26))); String res = sb.toString(); DBG.log("Gen.str", res); return res; }
    }

    // Fast Input Reader (Class: IO)
    static class IO {
        private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private static StringTokenizer st;

        // Purpose: Read next space-separated token | Input: none | Output: String
        public static String next() { while (st == null || !st.hasMoreTokens()) { try { String line = br.readLine(); if (line == null) return null; st = new StringTokenizer(line); } catch (IOException e) { e.printStackTrace(); } } String res = st.nextToken(); DBG.log("IO.next", res); return res; }
        // Purpose: Read next integer | Input: none | Output: int
        public static int i() { int val = Integer.parseInt(next()); DBG.log("IO.i", val); return val; }
        // Purpose: Read next long integer | Input: none | Output: long
        public static long l() { long val = Long.parseLong(next()); DBG.log("IO.l", val); return val; }
        // Purpose: Read full remaining line | Input: none | Output: String
        public static String line() { try { String res = br.readLine(); DBG.log("IO.line", res); return res; } catch (IOException e) { e.printStackTrace(); return null; } }
        // Purpose: Read 1D integer array | Input: int size | Output: int[]
        public static int[] iArr(int sz) { int[] a = new int[sz]; for (int k = 0; k < sz; k++) a[k] = i(); DBG.log("IO.iArr", a); return a; }
        // Purpose: Read 1D long array | Input: int size | Output: long[]
        public static long[] lArr(int sz) { long[] a = new long[sz]; for (int k = 0; k < sz; k++) a[k] = l(); DBG.log("IO.lArr", a); return a; }
        // Purpose: Read 1D string array | Input: int size | Output: String[]
        public static String[] sArr(int sz) { String[] a = new String[sz]; for (int k = 0; k < sz; k++) a[k] = next(); DBG.log("IO.sArr", a); return a; }
    }

    // Monotonic Stack Utilities (Class: Stk)
    static class Stk {
        // Purpose: Find Next Greater Element values | Input: int[] array | Output: int[]
        public static int[] nge(int[] a) { DBG.log("Stk.nge Input", a); int n = a.length, res[] = new int[n]; Arrays.fill(res, -1); Deque<Integer> st = new ArrayDeque<>(); for (int k = n - 1; k >= 0; k--) { while (!st.isEmpty() && st.peek() <= a[k]) st.pop(); if (!st.isEmpty()) res[k] = st.peek(); st.push(a[k]); } DBG.log("Stk.nge Result", res); return res; }
        // Purpose: Find Next Greater Element indices | Input: int[] array | Output: int[]
        public static int[] ngei(int[] a) { DBG.log("Stk.ngei Input", a); int n = a.length, res[] = new int[n]; Arrays.fill(res, -1); Deque<Integer> st = new ArrayDeque<>(); for (int k = n - 1; k >= 0; k--) { while (!st.isEmpty() && a[st.peek()] <= a[k]) st.pop(); if (!st.isEmpty()) res[k] = st.peek(); st.push(k); } DBG.log("Stk.ngei Result", res); return res; }
    }

    // Grid Traversal Utilities (Class: Grd)
    static class Grd {
        private static final int[] dR = {-1, 1, 0, 0}, dC = {0, 0, -1, 1};

        // Purpose: Check if grid cell coordinates are valid | Input: int row, int col, int maxRows, int maxCols | Output: boolean
        public static boolean ok(int r, int c, int R, int C) { boolean valid = r >= 0 && r < R && c >= 0 && c < C; DBG.log("Grd.ok (" + r + "," + c + ")", valid); return valid; }
        // Purpose: 4-directional Depth-First Search on grid | Input: int row, int col, char[][] grid, boolean[][] visited | Output: void
        public static void dfs(int r, int c, char[][] g, boolean[][] vis) { vis[r][c] = true; DBG.log("Grd.dfs Visit", r + "," + c); for (int k = 0; k < 4; k++) { int nR = r + dR[k], nC = c + dC[k]; if (ok(nR, nC, g.length, g[0].length) && !vis[nR][nC] && g[nR][nC] != '#') dfs(nR, nC, g, vis); } }
        // Purpose: 4-directional Breadth-First Search on grid | Input: int startRow, int startCol, char[][] grid, boolean[][] visited | Output: void
        public static void bfs(int sR, int sC, char[][] g, boolean[][] vis) { Queue<int[]> q = new ArrayDeque<>(); q.add(new int[]{sR, sC}); vis[sR][sC] = true; DBG.log("Grd.bfs Start", sR + "," + sC); while (!q.isEmpty()) { int[] cur = q.poll(); DBG.log("Grd.bfs Cell", cur[0] + "," + cur[1]); for (int k = 0; k < 4; k++) { int nR = cur[0] + dR[k], nC = cur[1] + dC[k]; if (ok(nR, nC, g.length, g[0].length) && !vis[nR][nC] && g[nR][nC] != '#') { vis[nR][nC] = true; q.add(new int[]{nR, nC}); } } } }
    }

    // Disjoint Set Union (Class: DSU)
    static class DSU {
        private final int[] p, sz;

        // Purpose: Initialize DSU structure | Input: int nodeCount | Output: DSU instance
        public DSU(int n) { p = new int[n]; sz = new int[n]; for (int k = 0; k < n; k++) { p[k] = k; sz[k] = 1; } DBG.msg("DSU.Init with size " + n); }
        // Purpose: Find set representative with path compression | Input: int node | Output: int
        public int find(int k) { int root = p[k] == k ? k : (p[k] = find(p[k])); DBG.log("DSU.find (" + k + ")", root); return root; }
        // Purpose: Union two sets by size | Input: int nodeA, int nodeB | Output: boolean
        public boolean union(int a, int b) { int rA = find(a), rB = find(b); if (rA == rB) { DBG.log("DSU.union Redundant", a + "-" + b); return false; } if (sz[rA] < sz[rB]) { int t = rA; rA = rB; rB = t; } p[rB] = rA; sz[rA] += sz[rB]; DBG.log("DSU.union Merged", a + " into " + b); return true; }
        // Purpose: Check if two nodes belong to same set | Input: int nodeA, int nodeB | Output: boolean
        public boolean same(int a, int b) { boolean res = find(a) == find(b); DBG.log("DSU.same (" + a + "," + b + ")", res); return res; }
    }

    // Segment Tree for Range Sum (Class: ST)
    static class ST {
        private final int n; private final long[] t;

        // Purpose: Build Segment Tree from initial array | Input: long[] array | Output: ST instance
        public ST(long[] a) { this.n = a.length; this.t = new long[4 * n]; DBG.log("ST.Init Input", a); build(a, 0, 0, n - 1); }
        private void build(long[] a, int node, int s, int e) { if (s == e) { t[node] = a[s]; return; } int m = (s + e) / 2; build(a, 2 * node + 1, s, m); build(a, 2 * node + 2, m + 1, e); t[node] = t[2 * node + 1] + t[2 * node + 2]; }
        // Purpose: Perform point update | Input: int targetIndex, long newValue | Output: void
        public void upd(int idx, long val) { DBG.log("ST.upd Idx " + idx, val); upd(0, 0, n - 1, idx, val); }
        private void upd(int node, int s, int e, int idx, long val) { if (s == e) { t[node] = val; return; } int m = (s + e) / 2; if (s <= idx && idx <= m) upd(2 * node + 1, s, m, idx, val); else upd(2 * node + 2, m + 1, e, idx, val); t[node] = t[2 * node + 1] + t[2 * node + 2]; }
        // Purpose: Query range sum [left, right] | Input: int leftIndex, int rightIndex | Output: long
        public long q(int l, int r) { long res = q(0, 0, n - 1, l, r); DBG.log("ST.q [" + l + "," + r + "]", res); return res; }
        private long q(int node, int s, int e, int l, int r) { if (r < s || e < l) return 0; if (l <= s && e <= r) return t[node]; int m = (s + e) / 2; return q(2 * node + 1, s, m, l, r) + q(2 * node + 2, m + 1, e, l, r); }
    }

    // Math Utilities (Class: Mth)
    static class Mth {
        // Purpose: Compute Greatest Common Divisor | Input: long firstNum, long secondNum | Output: long
        public static long gcd(long a, long b) { long res = b == 0 ? a : gcd(b, a % b); DBG.log("Mth.gcd (" + a + "," + b + ")", res); return res; }
        // Purpose: Compute Least Common Multiple | Input: long firstNum, long secondNum | Output: long
        public static long lcm(long a, long b) { long res = (a / gcd(a, b)) * b; DBG.log("Mth.lcm (" + a + "," + b + ")", res); return res; }
        // Purpose: Compute modular exponentiation (base^exp % mod) | Input: long baseNum, long exponent, long modulo | Output: long
        public static long pow(long b, long e, long m) { long res = 1, origB = b, origE = e; b %= m; while (e > 0) { if ((e & 1) == 1) res = (res * b) % m; b = (b * b) % m; e >>= 1; } DBG.log("Mth.pow (" + origB + "^" + origE + "%" + m + ")", res); return res; }
        // Purpose: Test primality in O(sqrt(N)) | Input: long number | Output: boolean
        public static boolean prime(long n) { if (n <= 1) { DBG.log("Mth.prime (" + n + ")", false); return false; } if (n <= 3) { DBG.log("Mth.prime (" + n + ")", true); return true; } if (n % 2 == 0 || n % 3 == 0) { DBG.log("Mth.prime (" + n + ")", false); return false; } for (long k = 5; k * k <= n; k += 6) if (n % k == 0 || n % (k + 2) == 0) { DBG.log("Mth.prime (" + n + ")", false); return false; } DBG.log("Mth.prime (" + n + ")", true); return true; }
        // Purpose: Generate prime table using Sieve of Eratosthenes | Input: int maxLimit | Output: boolean[]
        public static boolean[] sieve(int n) { boolean[] isP = new boolean[n + 1]; Arrays.fill(isP, true); if (n >= 0) isP[0] = false; if (n >= 1) isP[1] = false; for (int p = 2; p * p <= n; p++) if (isP[p]) for (int k = p * p; k <= n; k += p) isP[k] = false; DBG.msg("Mth.sieve Completed for N=" + n); return isP; }
    }

    // Array Binary Search & Prefix/Suffix Utilities (Class: Arr)
    static class Arr {
        // Purpose: Find lower bound index (first index where element >= target) | Input: int[] sortedArray, int targetValue | Output: int
        public static int lb(int[] a, int x) { int l = 0, h = a.length; while (l < h) { int m = (l + h) / 2; if (a[m] >= x) h = m; else l = m + 1; } DBG.log("Arr.lb for " + x, l); return l; }
        // Purpose: Find upper bound index (first index where element > target) | Input: int[] sortedArray, int targetValue | Output: int
        public static int ub(int[] a, int x) { int l = 0, h = a.length; while (l < h) { int m = (l + h) / 2; if (a[m] > x) h = m; else l = m + 1; } DBG.log("Arr.ub for " + x, l); return l; }

        // --- PREFIX / SUFFIX UTILITIES ---
        // Purpose: Compute 1-indexed Prefix Sum array | Input: int[] array | Output: long[]
        public static long[] pSum(int[] a) { int n = a.length; long[] p = new long[n + 1]; for (int k = 0; k < n; k++) p[k + 1] = p[k] + a[k]; DBG.log("Arr.pSum Result", p); return p; }
        // Purpose: Compute Suffix Sum array | Input: int[] array | Output: long[]
        public static long[] sSum(int[] a) { int n = a.length; long[] s = new long[n + 1]; for (int k = n - 1; k >= 0; k--) s[k] = s[k + 1] + a[k]; DBG.log("Arr.sSum Result", s); return s; }
        // Purpose: Compute Prefix Minimums | Input: int[] array | Output: int[]
        public static int[] pMin(int[] a) { int n = a.length, p[] = new int[n]; if (n == 0) return p; p[0] = a[0]; for (int k = 1; k < n; k++) p[k] = Math.min(p[k - 1], a[k]); DBG.log("Arr.pMin Result", p); return p; }
        // Purpose: Compute Suffix Minimums | Input: int[] array | Output: int[]
        public static int[] sMin(int[] a) { int n = a.length, s[] = new int[n]; if (n == 0) return s; s[n - 1] = a[n - 1]; for (int k = n - 2; k >= 0; k--) s[k] = Math.min(s[k + 1], a[k]); DBG.log("Arr.sMin Result", s); return s; }
        // Purpose: Compute Prefix Maximums | Input: int[] array | Output: int[]
        public static int[] pMax(int[] a) { int n = a.length, p[] = new int[n]; if (n == 0) return p; p[0] = a[0]; for (int k = 1; k < n; k++) p[k] = Math.max(p[k - 1], a[k]); DBG.log("Arr.pMax Result", p); return p; }
        // Purpose: Compute Suffix Maximums | Input: int[] array | Output: int[]
        public static int[] sMax(int[] a) { int n = a.length, s[] = new int[n]; if (n == 0) return s; s[n - 1] = a[n - 1]; for (int k = n - 2; k >= 0; k--) s[k] = Math.max(s[k + 1], a[k]); DBG.log("Arr.sMax Result", s); return s; }
        // Purpose: Compute Prefix GCDs | Input: int[] array | Output: long[]
        public static long[] pGcd(int[] a) { int n = a.length; long[] p = new long[n]; if (n == 0) return p; p[0] = a[0]; for (int k = 1; k < n; k++) p[k] = Mth.gcd(p[k - 1], a[k]); DBG.log("Arr.pGcd Result", p); return p; }
        // Purpose: Compute Suffix GCDs | Input: int[] array | Output: long[]
        public static long[] sGcd(int[] a) { int n = a.length; long[] s = new long[n]; if (n == 0) return s; s[n - 1] = a[n - 1]; for (int k = n - 2; k >= 0; k--) s[k] = Mth.gcd(s[k + 1], a[k]); DBG.log("Arr.sGcd Result", s); return s; }
    }

public static void main(String[] args) {

        IO in = new IO();
        PrintWriter out = new PrintWriter(System.out);

        // CALL SYNTAX: Gen Class
        // int[] arr = Gen.iArr(size, minVal, maxVal);
        // String str = Gen.str(length);

        // CALL SYNTAX: IO Class
        // int n = IO.i();
        // long val = IO.l();
        // String s = IO.next();
        // String line = IO.line();
        // int[] arr = IO.iArr(size);
        // long[] lArr = IO.lArr(size);
        // String[] sArr = IO.sArr(size);

        out.flush();

    }
}
