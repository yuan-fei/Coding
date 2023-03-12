/*
 * @lc app=leetcode id=2587 lang=java
 *
 * [2587] Rearrange Array to Maximize Prefix Score
 *
 * https://leetcode.com/problems/rearrange-array-to-maximize-prefix-score/description/
 *
 * algorithms
 * Medium (37.22%)
 * Likes:    62
 * Dislikes: 5
 * Total Accepted:    12K
 * Total Submissions: 32.4K
 * Testcase Example:  '[2,-1,0,1,-3,3,-3]'
 *
 * You are given a 0-indexed integer array nums. You can rearrange the elements
 * of nums to any order (including the given order).
 * 
 * Let prefix be the array containing the prefix sums of nums after rearranging
 * it. In other words, prefix[i] is the sum of the elements from 0 to i in nums
 * after rearranging it. The score of nums is the number of positive integers
 * in the array prefix.
 * 
 * Return the maximum score you can achieve.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,-1,0,1,-3,3,-3]
 * Output: 6
 * Explanation: We can rearrange the array into nums = [2,3,1,-1,-3,0,-3].
 * prefix = [2,5,6,5,2,2,-1], so the score is 6.
 * It can be shown that 6 is the maximum score we can obtain.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [-2,-3,0]
 * Output: 0
 * Explanation: Any rearrangement of the array will result in a score of 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * -10^6 <= nums[i] <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxScore(int[] nums) {
        Arrays.sort(nums);
        long sum = 0;
        for(int i = nums.length - 1; i >=0; i--){
            sum += nums[i];
            if(sum <= 0){
                return nums.length - i - 1;
            }
        }
        return nums.length;
    }
}
// @lc code=end
