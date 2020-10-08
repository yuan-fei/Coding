/*
 * @lc app=leetcode id=594 lang=java
 *
 * [594] Longest Harmonious Subsequence
 *
 * https://leetcode.com/problems/longest-harmonious-subsequence/description/
 *
 * algorithms
 * Easy (46.96%)
 * Likes:    715
 * Dislikes: 89
 * Total Accepted:    59.8K
 * Total Submissions: 127.2K
 * Testcase Example:  '[1,3,2,2,5,2,3,7]'
 *
 * We define a harmonious array as an array where the difference between its
 * maximum value and its minimum value is exactly 1.
 * 
 * Given an integer array nums, return the length of its longest harmonious
 * subsequence among all its possible subsequences.
 * 
 * A subsequence of array is a sequence that can be derived from the array by
 * deleting some or no elements without changing the order of the remaining
 * elements.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,3,2,2,5,2,3,7]
 * Output: 5
 * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3,4]
 * Output: 2
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,1,1,1]
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 2 * 10^4
 * -10^9 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        int max = 0;
        for(int i = 0; i < nums.length; i++){
        	int cnt = m.getOrDefault(nums[i], 0) + 1;
        	m.put(nums[i], cnt);
        	if(m.containsKey(nums[i] - 1)){
        		max = Math.max(max, m.getOrDefault(nums[i] - 1, 0) + cnt);	
        	}
        	if(m.containsKey(nums[i] + 1)){
	        	max = Math.max(max, m.getOrDefault(nums[i] + 1, 0) + cnt);
	        }
        }
        return max;
    }
}
// @lc code=end
