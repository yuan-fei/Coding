/*
 * @lc app=leetcode id=813 lang=java
 *
 * [813] Largest Sum of Averages
 *
 * https://leetcode.com/problems/largest-sum-of-averages/description/
 *
 * algorithms
 * Medium (53.01%)
 * Likes:    1791
 * Dislikes: 90
 * Total Accepted:    45.1K
 * Total Submissions: 85.1K
 * Testcase Example:  '[9,1,2,3,9]\n3'
 *
 * You are given an integer array nums and an integer k. You can partition the
 * array into at most k non-empty adjacent subarrays. The score of a partition
 * is the sum of the averages of each subarray.
 * 
 * Note that the partition must use every integer in nums, and that the score
 * is not necessarily an integer.
 * 
 * Return the maximum score you can achieve of all the possible partitions.
 * Answers within 10^-6 of the actual answer will be accepted.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [9,1,2,3,9], k = 3
 * Output: 20.00000
 * Explanation: 
 * The best choice is to partition nums into [9], [1, 2, 3], [9]. The answer is
 * 9 + (1 + 2 + 3) / 3 + 9 = 20.
 * We could have also partitioned nums into [9, 1], [2], [3, 9], for example.
 * That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3,4,5,6,7], k = 4
 * Output: 20.50000
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 * 
 * 
 */

// @lc code=start
class Solution {
    int MAX = (int)1e6 + 5;
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[][] dp = new double[n + 1][k + 1];
        for(int i1 = 1; i1 <= n; i1++){
            Arrays.fill(dp[i1], -MAX);
            for(int j = k; j >= 1; j--){
                double sum = 0;
                for(int i2 = i1; i2 >= 1; i2--){
                    sum += nums[i2 - 1];
                    dp[i1][j] = Math.max(dp[i1][j], dp[i2 - 1][j - 1] + sum / (i1 - i2 + 1));
                }
            }
        }

        // System.out.println(Arrays.deepToString(dp));
        double ans = 0;
        for(double x : dp[n]){
            ans = Math.max(ans, x);
        }
        return ans;
    }
}
// @lc code=end
