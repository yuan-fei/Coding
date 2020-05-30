/*
 * @lc app=leetcode id=485 lang=java
 *
 * [485] Max Consecutive Ones
 *
 * https://leetcode.com/problems/max-consecutive-ones/description/
 *
 * algorithms
 * Easy (55.56%)
 * Likes:    592
 * Dislikes: 352
 * Total Accepted:    208.1K
 * Total Submissions: 374.9K
 * Testcase Example:  '[1,0,1,1,0,1]'
 *
 * Given a binary array, find the maximum number of consecutive 1s in this
 * array.
 * 
 * Example 1:
 * 
 * Input: [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive
 * 1s.
 * ⁠   The maximum number of consecutive 1s is 3.
 * 
 * 
 * 
 * Note:
 * 
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
    	int cnt = nums[0];
    	int max = cnt;
        for(int i = 1; i < nums.length; i++){
        	if(nums[i] == 1){
        		cnt++;
        	}
        	else{
        		cnt = 0;
        	}
        	max = Math.max(max, cnt);
        }
        return max;
    }
}
// @lc code=end
