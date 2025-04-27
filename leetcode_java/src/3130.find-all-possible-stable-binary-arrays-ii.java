/*
 * @lc app=leetcode id=3130 lang=java
 *
 * [3130] Find All Possible Stable Binary Arrays II
 *
 * https://leetcode.com/problems/find-all-possible-stable-binary-arrays-ii/description/
 *
 * algorithms
 * Hard (27.00%)
 * Likes:    66
 * Dislikes: 12
 * Total Accepted:    3.6K
 * Total Submissions: 14.2K
 * Testcase Example:  '1\n1\n2'
 *
 * You are given 3 positive integers zero, one, and limit.
 * 
 * A binary array arr is called stable if:
 * 
 * 
 * The number of occurrences of 0 in arr is exactly zero.
 * The number of occurrences of 1 in arr is exactly one.
 * Each subarray of arr with a size greater than limit must contain both 0 and
 * 1.
 * 
 * 
 * Return the total number of stable binary arrays.
 * 
 * Since the answer may be very large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: zero = 1, one = 1, limit = 2
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * The two possible stable binary arrays are [1,0] and [0,1].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: zero = 1, one = 2, limit = 1
 * 
 * Output: 1
 * 
 * Explanation:
 * 
 * The only possible stable binary array is [1,0,1].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: zero = 3, one = 3, limit = 2
 * 
 * Output: 14
 * 
 * Explanation:
 * 
 * All the possible stable binary arrays are [0,0,1,0,1,1], [0,0,1,1,0,1],
 * [0,1,0,0,1,1], [0,1,0,1,0,1], [0,1,0,1,1,0], [0,1,1,0,0,1], [0,1,1,0,1,0],
 * [1,0,0,1,0,1], [1,0,0,1,1,0], [1,0,1,0,0,1], [1,0,1,0,1,0], [1,0,1,1,0,0],
 * [1,1,0,0,1,0], and [1,1,0,1,0,0].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= zero, one, limit <= 1000
 * 
 * 
 */

// @lc code=start

class Solution {
    int MOD = 1_000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {
        // g(zero, one) = f(zero, one, 0) + f(zero, one, 1)
        // f(zero, one, 0) = sum(f(zero - i, one, 1)) where 1 <= i <= min(limit, zero)
        int[][][] dp = new int[zero + 1][one + 1][2];
        int[][][] pSum = new int[zero + 2][one + 2][2];
        for (int i = 1; i <= limit; i++) {
            if (i <= zero) {
                dp[i][0][0] = 1;
                pSum[i + 1][1][0] = add(pSum[i + 1][0][0], 1);
            }
            if (i <= one) {
                dp[0][i][1] = 1;
                pSum[1][i + 1][1] = add(pSum[0][i + 1][1], 1);
            }
        }
        for (int z = 1; z <= zero; z++) {
            for (int o = 1; o <= one; o++) {
                dp[z][o][0] = add(pSum[z][o + 1][1], -pSum[Math.max(0, z - limit)][o + 1][1]);
                dp[z][o][1] = add(pSum[z + 1][o][0], -pSum[z + 1][Math.max(0, o - limit)][0]);
                pSum[z + 1][o + 1][0] = add(pSum[z + 1][o][0], dp[z][o][0]);
                pSum[z + 1][o + 1][1] = add(pSum[z][o + 1][1], dp[z][o][1]);
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        // System.out.println(Arrays.deepToString(pSum));
        return add(dp[zero][one][0], dp[zero][one][1]);
    }

    int add(int x, int y) {
        return Math.floorMod(x + y, MOD);
    }

}
// @lc code=end
