/*
 * @lc app=leetcode id=1043 lang=java
 *
 * [1043] Partition Array for Maximum Sum
 *
 * https://leetcode.com/problems/partition-array-for-maximum-sum/description/
 *
 * algorithms
 * Medium (72.24%)
 * Likes:    3695
 * Dislikes: 247
 * Total Accepted:    89.2K
 * Total Submissions: 123.3K
 * Testcase Example:  '[1,15,7,9,2,5,10]\n3'
 *
 * Given an integer array arr, partition the array into (contiguous) subarrays
 * of length at most k. After partitioning, each subarray has their values
 * changed to become the maximum value of that subarray.
 * 
 * Return the largest sum of the given array after partitioning. Test cases are
 * generated so that the answer fits in a 32-bit integer.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [1,15,7,9,2,5,10], k = 3
 * Output: 84
 * Explanation: arr becomes [15,15,15,9,10,10,10]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
 * Output: 83
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr = [1], k = 1
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 10^9
 * 1 <= k <= arr.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        for(int i = 1; i <= n; i++){
            int max = arr[i - 1];
            for(int j = 0; j < Math.min(k, i); j++){
                max = Math.max(max, arr[i - j - 1]);
                dp[i] = Math.max(dp[i], max * (j + 1) + dp[i - j - 1]);
            }
        }
        // System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}
// @lc code=end
