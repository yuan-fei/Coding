/*
 * @lc app=leetcode id=1955 lang=java
 *
 * [1955] Count Number of Special Subsequences
 *
 * https://leetcode.com/problems/count-number-of-special-subsequences/description/
 *
 * algorithms
 * Hard (49.63%)
 * Likes:    215
 * Dislikes: 2
 * Total Accepted:    4.2K
 * Total Submissions: 8.5K
 * Testcase Example:  '[0,1,2,2]'
 *
 * A sequence is special if it consists of a positive number of 0s, followed by
 * a positive number of 1s, then a positive number of 2s.
 * 
 * 
 * For example, [0,1,2] and [0,0,1,1,1,2] are special.
 * In contrast, [2,1,0], [1], and [0,1,2,0] are not special.
 * 
 * 
 * Given an array nums (consisting of only integers 0, 1, and 2), return the
 * number of different subsequences that are special. Since the answer may be
 * very large, return it modulo 10^9 + 7.
 * 
 * A subsequence of an array is a sequence that can be derived from the array
 * by deleting some or no elements without changing the order of the remaining
 * elements. Two subsequences are different if the set of indices chosen are
 * different.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [0,1,2,2]
 * Output: 3
 * Explanation: The special subsequences are [0,1,2,2], [0,1,2,2], and
 * [0,1,2,2].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,2,0,0]
 * Output: 0
 * Explanation: There are no special subsequences in [2,2,0,0].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [0,1,2,0,1,2]
 * Output: 7
 * Explanation: The special subsequences are:
 * - [0,1,2,0,1,2]
 * - [0,1,2,0,1,2]
 * - [0,1,2,0,1,2]
 * - [0,1,2,0,1,2]
 * - [0,1,2,0,1,2]
 * - [0,1,2,0,1,2]
 * - [0,1,2,0,1,2]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 2
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countSpecialSubsequences(int[] nums) {
        int[] dp = new int[3];
        int mod = (int)1e9 + 7;
        for(int x : nums){
            int[] newDp = new int[3];
            switch(x){
                case 0: 
                    newDp[0] = (dp[0] * 2 + 1) % mod;
                    newDp[1] = dp[1];
                    newDp[2] = dp[2];
                    break;
                case 1: 
                    newDp[0] = dp[0];
                    newDp[1] = ((2 * dp[1]) % mod + dp[0]) % mod;
                    newDp[2] = dp[2];
                    break;
                case 2: 
                    newDp[0] = dp[0];
                    newDp[1] = dp[1];
                    newDp[2] = ((2 * dp[2]) % mod + dp[1]) % mod;
                    break;
            }
            dp = newDp;
        }
        return dp[2];
    }
}
// @lc code=end
