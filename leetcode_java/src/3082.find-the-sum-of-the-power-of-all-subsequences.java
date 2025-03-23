/*
 * @lc app=leetcode id=3082 lang=java
 *
 * [3082] Find the Sum of the Power of All Subsequences
 *
 * https://leetcode.com/problems/find-the-sum-of-the-power-of-all-subsequences/description/
 *
 * algorithms
 * Hard (36.78%)
 * Likes:    143
 * Dislikes: 3
 * Total Accepted:    8.2K
 * Total Submissions: 22.1K
 * Testcase Example:  '[1,2,3]\n3'
 *
 * You are given an integer array nums of length n and a positive integer k.
 * 
 * The power of an array of integers is defined as the number of subsequences
 * with their sum equal to k.
 * 
 * Return the sum of power of all subsequences of nums.
 * 
 * Since the answer may be very large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:   nums = [1,2,3], k = 3 
 * 
 * Output:   6 
 * 
 * Explanation:
 * 
 * There are 5 subsequences of nums with non-zero power:
 * 
 * 
 * The subsequence [1,2,3] has 2 subsequences with sum == 3: [1,2,3] and
 * [1,2,3].
 * The subsequence [1,2,3] has 1 subsequence with sum == 3: [1,2,3].
 * The subsequence [1,2,3] has 1 subsequence with sum == 3: [1,2,3].
 * The subsequence [1,2,3] has 1 subsequence with sum == 3: [1,2,3].
 * The subsequence [1,2,3] has 1 subsequence with sum == 3: [1,2,3].
 * 
 * 
 * Hence the answer is 2 + 1 + 1 + 1 + 1 = 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:   nums = [2,3,3], k = 5 
 * 
 * Output:   4 
 * 
 * Explanation:
 * 
 * There are 3 subsequences of nums with non-zero power:
 * 
 * 
 * The subsequence [2,3,3] has 2 subsequences with sum == 5: [2,3,3] and
 * [2,3,3].
 * The subsequence [2,3,3] has 1 subsequence with sum == 5: [2,3,3].
 * The subsequence [2,3,3] has 1 subsequence with sum == 5: [2,3,3].
 * 
 * 
 * Hence the answer is 2 + 1 + 1 = 4.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:   nums = [1,2,3], k = 7 
 * 
 * Output:   0 
 * 
 * Explanation: There exists no subsequence with sum 7. Hence all subsequences
 * of nums have power = 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 100
 * 1 <= nums[i] <= 10^4
 * 1 <= k <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    long MOD = (long)1e9 + 7;
    public int sumOfPower(int[] nums, int k) {
        int n = nums.length;
        long[][] dp  = new long[k + 1][n + 1];
        dp[0][0] = 1;
        for(int x : nums){
            for(int i = k; i >= x; i--){
                for(int j = 1; j <= n; j++){
                    dp[i][j] = add(dp[i][j], dp[i - x][j - 1]);
                }
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        long multiplier = 1;
        long ans = 0;
        for(int i = 0; i <= n; i++){
            ans = add(ans, mul(dp[k][n - i], multiplier));
            multiplier = mul(multiplier, 2);
        }
        return (int)ans;
    }

    long add(long a, long b){
        return Math.floorMod(a + b, MOD);
    }

    long mul(long a, long b){
        return Math.floorMod(a * b, MOD);
    }
}
// @lc code=end
