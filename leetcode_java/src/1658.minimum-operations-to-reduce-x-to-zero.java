/*
 * @lc app=leetcode id=1658 lang=java
 *
 * [1658] Minimum Operations to Reduce X to Zero
 *
 * https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/description/
 *
 * algorithms
 * Medium (25.08%)
 * Likes:    101
 * Dislikes: 3
 * Total Accepted:    3.2K
 * Total Submissions: 12.6K
 * Testcase Example:  '[1,1,4,2,3]\n5'
 *
 * You are given an integer array nums and an integer x. In one operation, you
 * can either remove the leftmost or the rightmost element from the array nums
 * and subtract its value from x. Note that this modifies the array for future
 * operations.
 * 
 * Return the minimum number of operations to reduce x to exactly 0 if it's
 * possible, otherwise, return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,1,4,2,3], x = 5
 * Output: 2
 * Explanation: The optimal solution is to remove the last two elements to
 * reduce x to zero.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [5,6,7,8,9], x = 4
 * Output: -1
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [3,2,20,1,1,3], x = 10
 * Output: 5
 * Explanation: The optimal solution is to remove the last three elements and
 * the first two elements (5 operations in total) to reduce x to zero.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 * 1 <= x <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minOperations(int[] nums, int x) {
        long prefSum = 0;
        long total = 0;
        for(int n : nums){
        	total += n;
        }
        long target = total - x;
        Map<Long, Integer> pos = new HashMap<>();
        pos.put(0L, -1);
        int min = nums.length + 1;
        for(int i = 0; i < nums.length; i++){
        	prefSum += nums[i];
        	if(!pos.containsKey(prefSum)){
        		pos.put(prefSum, i);
        	}
        	if(pos.containsKey(prefSum - target)){
        		min = Math.min(min, nums.length - (i - pos.get(prefSum - target)));
        	}
        }
        if(min == nums.length + 1){
        	return -1;
        }
        else{
        	return min;
        }
    }
}
// @lc code=end
