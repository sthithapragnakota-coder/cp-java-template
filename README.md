# Java Competitive Programming Template

Java template containing commonly used algorithms, data structures, and helper functions for faster implementation during coding contests.

---

# Classes

## `IO` — Fast Input Utilities
**Purpose:** Read input efficiently with minimal boilerplate.

| Function | Purpose |
|----------|---------|
| `next()` | Read the next space-separated token |
| `i()` | Read an integer |
| `l()` | Read a long |
| `line()` | Read an entire line |
| `iArr(n)` | Read an integer array of size `n` |
| `lArr(n)` | Read a long array of size `n` |
| `sArr(n)` | Read a string array of size `n` |

---

## `Mod` — Modular Arithmetic
**Purpose:** Perform arithmetic operations under modulo `1e9+7`.

| Function | Purpose |
|----------|---------|
| `add(a,b)` | Compute `(a + b) % MOD` |
| `sub(a,b)` | Compute `(a - b) % MOD` |
| `mul(a,b)` | Compute `(a × b) % MOD` |
| `inv(a)` | Find modular multiplicative inverse |
| `div(a,b)` | Compute `(a / b) % MOD` |

---

## `Mth` — Mathematical Utilities
**Purpose:** Frequently used mathematical algorithms.

| Function | Purpose |
|----------|---------|
| `gcd(a,b)` | Greatest Common Divisor |
| `lcm(a,b)` | Least Common Multiple |
| `pow(b,e,m)` | Fast modular exponentiation |
| `prime(n)` | Check if a number is prime |
| `sieve(n)` | Generate prime table using Sieve of Eratosthenes |

---

## `Arr` — Array Utilities
**Purpose:** Binary search and prefix/suffix preprocessing.

| Function | Purpose |
|----------|---------|
| `lb(arr,x)` | First index where value ≥ `x` |
| `ub(arr,x)` | First index where value > `x` |
| `pSum(arr)` | Compute prefix sums |
| `sSum(arr)` | Compute suffix sums |
| `pMin(arr)` | Prefix minimum array |
| `sMin(arr)` | Suffix minimum array |
| `pMax(arr)` | Prefix maximum array |
| `sMax(arr)` | Suffix maximum array |
| `pGcd(arr)` | Prefix GCD array |
| `sGcd(arr)` | Suffix GCD array |

---

## `Stk` — Monotonic Stack
**Purpose:** Solve Next Greater Element type problems.

| Function | Purpose |
|----------|---------|
| `nge(arr)` | Next Greater Element values |
| `ngei(arr)` | Next Greater Element indices |

---

## `Grd` — Grid Algorithms
**Purpose:** Common utilities for grid traversal.

| Function | Purpose |
|----------|---------|
| `ok(r,c,R,C)` | Check whether a cell is inside the grid |
| `dfs(r,c,grid,vis)` | Perform Depth First Search |
| `bfs(r,c,grid,vis)` | Perform Breadth First Search |

---

## `DSU` — Disjoint Set Union
**Purpose:** Maintain connected components efficiently.

| Function | Purpose |
|----------|---------|
| `find(x)` | Find representative of a set |
| `union(a,b)` | Merge two disjoint sets |
| `same(a,b)` | Check if two nodes belong to the same set |

---

## `ST` — Segment Tree
**Purpose:** Efficient range query and point update operations.

| Function | Purpose |
|----------|---------|
| `upd(idx,val)` | Update value at an index |
| `q(l,r)` | Query range sum over `[l, r]` |

---

## `Gen` — Test Case Generator
**Purpose:** Generate random and edge-case test data.

| Function | Purpose |
|----------|---------|
| `i(min,max)` | Generate random integer |
| `l(min,max)` | Generate random long |
| `iArr(size,min,max)` | Generate random integer array |
| `edgeArr(size)` | Generate edge-case integer array |
| `str(len)` | Generate random lowercase string |

---

## `DBG` — Debug Utilities
**Purpose:** Print debugging information during development.

| Function | Purpose |
|----------|---------|
| `log(name,val)` | Print a variable with its label |
| `grid(name,grid)` | Print a 2D character grid |
| `msg(text)` | Print a custom debug message |

---

# Debug Mode

```java
public static final boolean DEBUG = true;
```

Set `DEBUG = false` before submitting your solution.

---

# Quick Example

```java
int[] a = IO.iArr(n);

long g = Mth.gcd(a, b);

int pos = Arr.lb(sortedArray, x);

DSU dsu = new DSU(n);
dsu.union(u, v);

ST seg = new ST(values);
long sum = seg.q(l, r);

long ans = Mod.mul(x, y);
```
