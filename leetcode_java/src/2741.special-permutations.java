/*
 * @lc app=leetcode id=2741 lang=java
 *
 * [2741] Special Permutations
 *
 * https://leetcode.com/problems/special-permutations/description/
 *
 * algorithms
 * Medium (27.32%)
 * Likes:    531
 * Dislikes: 59
 * Total Accepted:    12.8K
 * Total Submissions: 46.5K
 * Testcase Example:  '[2,3,6]'
 *
 * You are given a 0-indexed integer array nums containing n distinct positive
 * integers. A permutation of nums is called special if:
 * 
 * 
 * For all indexes 0 <= i < n - 1, either nums[i] % nums[i+1] == 0 or nums[i+1]
 * % nums[i] == 0.
 * 
 * 
 * Return the total number of special permutations. As the answer could be
 * large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,3,6]
 * Output: 2
 * Explanation: [3,6,2] and [2,6,3] are the two special permutations of nums.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,4,3]
 * Output: 2
 * Explanation: [3,1,4] and [4,1,3] are the two special permutations of
 * nums.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 14
 * 1 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    int MOD = (int)1e9 + 7;
    public int specialPerm(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[1 << n][n];
        for(int j = 0; j < n; j++){
            dp[1 << j][j] = 1;    
        }
        
        for(int i = 0; i < (1 << n); i++){
            for(int j = 0; j < n; j++){
                if(((i >> j) & 1) == 1){
                    for(int k = 0; k < n; k++){
                        if(((i >> k) & 1) == 1 && (nums[k] % nums[j] == 0 || nums[j] % nums[k] == 0)){
                            dp[i][j] += dp[i ^ (1 << j)][k];
                            dp[i][j] %= MOD;
                        }
                    }
                }
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        int ans = 0;
        for(int j = 0; j < n; j++){
            ans += dp[(1 << n) - 1][j];
            ans %= MOD;
        }
        return ans;
    }
}
// @lc code=end
