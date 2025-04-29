/*
 * @lc app=leetcode id=3149 lang=java
 *
 * [3149] Find the Minimum Cost Array Permutation
 *
 * https://leetcode.com/problems/find-the-minimum-cost-array-permutation/description/
 *
 * algorithms
 * Hard (22.71%)
 * Likes:    128
 * Dislikes: 6
 * Total Accepted:    5.6K
 * Total Submissions: 24K
 * Testcase Example:  '[1,0,2]'
 *
 * You are given an array nums which is a permutation of [0, 1, 2, ..., n - 1].
 * The score of any permutation of [0, 1, 2, ..., n - 1] named perm is defined
 * as:
 * 
 * score(perm) = |perm[0] - nums[perm[1]]| + |perm[1] - nums[perm[2]]| + ... +
 * |perm[n - 1] - nums[perm[0]]|
 * 
 * Return the permutation perm which has the minimum possible score. If
 * multiple permutations exist with this score, return the one that is
 * lexicographically smallest among them.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,0,2]
 * 
 * Output: [0,1,2]
 * 
 * Explanation:
 * 
 * 
 * 
 * The lexicographically smallest permutation with minimum cost is [0,1,2]. The
 * cost of this permutation is |0 - 0| + |1 - 2| + |2 - 1| = 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [0,2,1]
 * 
 * Output: [0,2,1]
 * 
 * Explanation:
 * 
 * 
 * 
 * The lexicographically smallest permutation with minimum cost is [0,2,1]. The
 * cost of this permutation is |0 - 1| + |2 - 2| + |1 - 0| = 2.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n == nums.length <= 14
 * nums is a permutation of [0, 1, 2, ..., n - 1].
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    int MAX = 1000;
    int[][][] dp;

    public int[] findPermutation(int[] nums) {
        int n = nums.length;
        dp = new int[n][n][1 << n];
        int[][][] nextHead = new int[n][n][1 << n];
        @SuppressWarnings("unchecked")
        Set<Integer>[] lengthToPatterns = new Set[n + 1];
        for (int i = 0; i < n; i++) {
            lengthToPatterns[i] = new HashSet<>();
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], MAX);
                Arrays.fill(nextHead[i][j], n);
            }
            dp[i][i][1 << i] = 0;
            nextHead[i][i][1 << i] = i;
        }
        lengthToPatterns[n] = new HashSet<>();
        for (int m = 0; m < (1 << n); m++) {
            lengthToPatterns[Integer.bitCount(m)].add(m);
        }
        for (int l = 1; l <= n; l++) {
            for (int m : lengthToPatterns[l]) {
                for (int h = 0; h < n; h++) {
                    if (((m >> h) & 1) == 1) {
                        for (int t = 0; t < n; t++)
                            if (((m >> t) & 1) == 1 && dp[h][t][m] != MAX) {
                                for (int hh = 0; hh < n; hh++)
                                    if (((m >> hh) & 1) == 0) {
                                        if (dp[hh][t][m ^ (1 << hh)] > dp[h][t][m] + Math.abs(hh - nums[h])) {
                                            dp[hh][t][m ^ (1 << hh)] = dp[h][t][m] + Math.abs(hh - nums[h]);
                                            nextHead[hh][t][m ^ (1 << hh)] = h;
                                        }
                                        if (dp[hh][t][m ^ (1 << hh)] == dp[h][t][m] + Math.abs(hh - nums[h])) {
                                            nextHead[hh][t][m ^ (1 << hh)] = Math.min(nextHead[hh][t][m ^ (1 << hh)],
                                                    h);
                                        }
                                    }
                            }
                    }
                }
            }

        }
        int min = MAX;
        int minH = n;
        Set<Integer> minT = new HashSet<>();
        for (int h = 0; h < n; h++) {
            for (int t = 0; t < n; t++) {
                if (t == h) {
                    continue;
                }
                if (min > dp[h][t][(1 << n) - 1] + Math.abs(t - nums[h])) {
                    min = dp[h][t][(1 << n) - 1] + Math.abs(t - nums[h]);
                    minH = h;
                    minT = new HashSet<>();
                    minT.add(t);
                } else if (min == dp[h][t][(1 << n) - 1] + Math.abs(t - nums[h])) {
                    if (minH > h) {
                        minH = h;
                        minT = new HashSet<>();
                        minT.add(t);
                    } else if (minH == h) {
                        minT.add(t);
                    }
                }
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        // System.out.println(Arrays.deepToString(nextHead));
        // System.out.println(Arrays.asList(min, minH, minT));
        int[] minPath = new int[n];
        Arrays.fill(minPath, n);
        for (int t : minT) {
            int[] path = getPath(n, nextHead, minH, t);
            if (Arrays.compare(path, minPath) < 0) {
                minPath = path;
            }
        }
        return minPath;
    }

    private int[] getPath(int n, int[][][] nextHead, int minH, int minT) {
        int[] ans = new int[n];
        int mask = (1 << n) - 1;
        ans[0] = minH;
        for (int i = 1; i < n; i++) {
            ans[i] = nextHead[ans[i - 1]][minT][mask];
            mask ^= (1 << ans[i - 1]);
            // System.out.println(Arrays.asList(ans[i], ans[i - 1], minT, mask, dp[ans[i -
            // 1]][minT][mask]));
        }
        return ans;
    }
}
// @lc code=end
