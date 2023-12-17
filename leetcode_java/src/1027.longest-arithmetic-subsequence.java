/*
 * @lc app=leetcode id=1027 lang=java
 *
 * [1027] Longest Arithmetic Subsequence
 *
 * https://leetcode.com/problems/longest-arithmetic-subsequence/description/
 *
 * algorithms
 * Medium (49.01%)
 * Likes:    4514
 * Dislikes: 202
 * Total Accepted:    163.7K
 * Total Submissions: 333.9K
 * Testcase Example:  '[3,6,9,12]'
 *
 * Given an array nums of integers, return the length of the longest arithmetic
 * subsequence in nums.
 * 
 * Note that:
 * 
 * 
 * A subsequence is an array that can be derived from another array by deleting
 * some or no elements without changing the order of the remaining
 * elements.
 * A sequence seq is arithmetic if seq[i + 1] - seq[i] are all the same value
 * (for 0 <= i < seq.length - 1).
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,6,9,12]
 * Output: 4
 * Explanation:  The whole array is an arithmetic sequence with steps of length
 * = 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [9,4,7,2,10]
 * Output: 3
 * Explanation:  The longest arithmetic subsequence is [4,7,10].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [20,1,15,3,10,5,8]
 * Output: 4
 * Explanation:  The longest arithmetic subsequence is [20,15,10,5].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 1000
 * 0 <= nums[i] <= 500
 * 
 * 
 */

// @lc code=start
class Solution {
    int OFS = 500;
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int[][] dp = new int[1001][1001];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                int delta = nums[i] - nums[j] + OFS;
                dp[i][delta] = dp[j][delta] + 1;
                ans = Math.max(ans, dp[i][delta]);
            }
        }
        return ans + 1;
    }
}
// @lc code=end
