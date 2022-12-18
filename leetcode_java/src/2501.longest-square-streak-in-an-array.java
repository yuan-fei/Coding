/*
 * @lc app=leetcode id=2501 lang=java
 *
 * [2501] Longest Square Streak in an Array
 *
 * https://leetcode.com/problems/longest-square-streak-in-an-array/description/
 *
 * algorithms
 * Medium (37.42%)
 * Likes:    77
 * Dislikes: 2
 * Total Accepted:    8.3K
 * Total Submissions: 22.2K
 * Testcase Example:  '[4,3,6,16,8,2]'
 *
 * You are given an integer array nums. A subsequence of nums is called a
 * square streak if:
 * 
 * 
 * The length of the subsequence is at least 2, and
 * after sorting the subsequence, each element (except the first element) is
 * the square of the previous number.
 * 
 * 
 * Return the length of the longest square streak in nums, or return -1 if
 * there is no square streak.
 * 
 * A subsequence is an array that can be derived from another array by deleting
 * some or no elements without changing the order of the remaining elements.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [4,3,6,16,8,2]
 * Output: 3
 * Explanation: Choose the subsequence [4,16,2]. After sorting it, it becomes
 * [2,4,16].
 * - 4 = 2 * 2.
 * - 16 = 4 * 4.
 * Therefore, [4,16,2] is a square streak.
 * It can be shown that every subsequence of length 4 is not a square streak.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,3,5,6,7]
 * Output: -1
 * Explanation: There is no square streak in nums so return -1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 10^5
 * 2 <= nums[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestSquareStreak(int[] nums) {
        Arrays.sort(nums);
        int[] dp = new int[100005];
        int ans = -1;
        for(int i = 0; i < nums.length; i++){
            int sqrt = (int)Math.sqrt(nums[i]);
            if(sqrt * sqrt == nums[i]){
                dp[nums[i]] = Math.max(dp[sqrt] + 1, dp[nums[i]]);
                ans = Math.max(ans, dp[nums[i]]);
            }else{
                dp[nums[i]] = 1;
            }
        }
        return (ans >= 2) ? ans : -1;
    }
}
// @lc code=end
