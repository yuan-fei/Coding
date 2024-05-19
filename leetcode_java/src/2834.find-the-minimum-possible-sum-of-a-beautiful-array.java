/*
 * @lc app=leetcode id=2834 lang=java
 *
 * [2834] Find the Minimum Possible Sum of a Beautiful Array
 *
 * https://leetcode.com/problems/find-the-minimum-possible-sum-of-a-beautiful-array/description/
 *
 * algorithms
 * Medium (40.25%)
 * Likes:    291
 * Dislikes: 48
 * Total Accepted:    29.9K
 * Total Submissions: 76.5K
 * Testcase Example:  '2\n3'
 *
 * You are given positive integers n and target.
 * 
 * An array nums is beautiful if it meets the following conditions:
 * 
 * 
 * nums.length == n.
 * nums consists of pairwise distinct positive integers.
 * There doesn't exist two distinct indices, i and j, in the range [0, n - 1],
 * such that nums[i] + nums[j] == target.
 * 
 * 
 * Return the minimum possible sum that a beautiful array could have modulo
 * 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 2, target = 3
 * Output: 4
 * Explanation: We can see that nums = [1,3] is beautiful.
 * - The array nums has length n = 2.
 * - The array nums consists of pairwise distinct positive integers.
 * - There doesn't exist two distinct indices, i and j, with nums[i] + nums[j]
 * == 3.
 * It can be proven that 4 is the minimum possible sum that a beautiful array
 * could have.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 3, target = 3
 * Output: 8
 * Explanation: We can see that nums = [1,3,4] is beautiful.
 * - The array nums has length n = 3.
 * - The array nums consists of pairwise distinct positive integers.
 * - There doesn't exist two distinct indices, i and j, with nums[i] + nums[j]
 * == 3.
 * It can be proven that 8 is the minimum possible sum that a beautiful array
 * could have.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 1, target = 1
 * Output: 1
 * Explanation: We can see, that nums = [1] is beautiful.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^9
 * 1 <= target <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    long MOD = (long)1e9 + 7;
    public int minimumPossibleSum(int n, int target) {
        if(n == 1){
            return 1;
        }
        long ans = 0;
        long mid = target / 2;
        long cnt = Math.min(n, mid);
        ans += 1L * (1 + cnt) * cnt / 2;
        ans %= MOD;
        cnt = Math.max(0, n - cnt);
        ans += 1L * (2L * target + cnt - 1) * cnt / 2;
        ans %= MOD;
        return (int)ans;
    }
}
// @lc code=end
