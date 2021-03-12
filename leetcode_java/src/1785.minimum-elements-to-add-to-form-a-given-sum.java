/*
 * @lc app=leetcode id=1785 lang=java
 *
 * [1785] Minimum Elements to Add to Form a Given Sum
 *
 * https://leetcode.com/problems/minimum-elements-to-add-to-form-a-given-sum/description/
 *
 * algorithms
 * Medium (38.51%)
 * Likes:    62
 * Dislikes: 56
 * Total Accepted:    7.4K
 * Total Submissions: 19.2K
 * Testcase Example:  '[1,-1,1]\n3\n-4'
 *
 * You are given an integer array nums and two integers limit and goal. The
 * array nums has an interesting property that abs(nums[i]) <= limit.
 * 
 * Return the minimum number of elements you need to add to make the sum of the
 * array equal to goal. The array must maintain its property that abs(nums[i])
 * <= limit.
 * 
 * Note that abs(x) equals x if x >= 0, and -x otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,-1,1], limit = 3, goal = -4
 * Output: 2
 * Explanation: You can add -2 and -3, then the sum of the array will be 1 - 1
 * + 1 - 2 - 3 = -4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,-10,9,1], limit = 100, goal = 0
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= limit <= 10^6
 * -limit <= nums[i] <= limit
 * -10^9 <= goal <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minElements(int[] nums, int limit, int goal) {
    	long sum = 0;
        for(int x : nums){
        	sum += x;
        }
        return (int)((Math.abs(goal - sum) + limit - 1) / limit);
    }
}
// @lc code=end
