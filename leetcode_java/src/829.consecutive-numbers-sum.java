/*
 * @lc app=leetcode id=829 lang=java
 *
 * [829] Consecutive Numbers Sum
 *
 * https://leetcode.com/problems/consecutive-numbers-sum/description/
 *
 * algorithms
 * Hard (41.55%)
 * Likes:    1197
 * Dislikes: 1330
 * Total Accepted:    76.9K
 * Total Submissions: 185K
 * Testcase Example:  '5'
 *
 * Given an integer n, return the number of ways you can write n as the sum of
 * consecutive positive integers.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 5
 * Output: 2
 * Explanation: 5 = 2 + 3
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 9
 * Output: 3
 * Explanation: 9 = 4 + 5 = 2 + 3 + 4
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 15
 * Output: 4
 * Explanation: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int consecutiveNumbersSum(int n) {
        // x = (2n - k^2 + k)/(2*k)
        int ans = 0;
        for(int k = 2; 2L*n - 1L*k*k + k > 0; k++){
            if((2L*n - 1L*k*k + k) % (2 * k) == 0){
                ans++;
            }
        }
        return ans + 1;
    }
}
// @lc code=end
