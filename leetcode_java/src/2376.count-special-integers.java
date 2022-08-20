/*
 * @lc app=leetcode id=2376 lang=java
 *
 * [2376] Count Special Integers
 *
 * https://leetcode.com/problems/count-special-integers/description/
 *
 * algorithms
 * Hard (28.24%)
 * Likes:    147
 * Dislikes: 15
 * Total Accepted:    3.4K
 * Total Submissions: 12.2K
 * Testcase Example:  '20'
 *
 * We call a positive integer special if all of its digits are distinct.
 * 
 * Given a positive integer n, return the number of special integers that
 * belong to the interval [1, n].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 20
 * Output: 19
 * Explanation: All the integers from 1 to 20, except 11, are special. Thus,
 * there are 19 special integers.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 5
 * Output: 5
 * Explanation: All the integers from 1 to 5 are special.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 135
 * Output: 110
 * Explanation: There are 110 integers from 1 to 135 that are special.
 * Some of the integers that are not special are: 22, 114, and 131.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 2 * 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    String s;
    int[][][] dp;
    public int countSpecialNumbers(int n) {
        s = String.valueOf(n);
        dp = new int[s.length()][2][1 << 10];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return solve(0, false, 0) - 1;
    }

    

    int solve(int i, boolean isLess, int mask) {
        if (i == s.length()) {
            return 1;
        }
        if (dp[i][isLess ? 1 : 0][mask] >= 0) {
            return dp[i][isLess ? 1 : 0][mask];
        }
        int x = s.charAt(i) - '0';
        int max = isLess ? 9 : x;
        int r = 0;
        for (int k = 0; k <= max; k++) {
            if ((mask & (1 << k)) == 0) {
                boolean isLessNext = isLess || k < max;
                int newMask = mask | (1 << k);
                if (k == 0 && mask == 0) {
                    newMask = 0;
                }
                r += solve(i + 1, isLessNext, newMask);
            }
        }
        return dp[i][isLess ? 1 : 0][mask] = r;
    }
}
// @lc code=end
