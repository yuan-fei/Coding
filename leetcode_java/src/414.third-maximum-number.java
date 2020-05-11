/*
 * @lc app=leetcode id=414 lang=java
 *
 * [414] Third Maximum Number
 *
 * https://leetcode.com/problems/third-maximum-number/description/
 *
 * algorithms
 * Easy (30.26%)
 * Likes:    583
 * Dislikes: 1078
 * Total Accepted:    134.8K
 * Total Submissions: 445.6K
 * Testcase Example:  '[3,2,1]'
 *
 * Given a non-empty array of integers, return the third maximum number in this
 * array. If it does not exist, return the maximum number. The time complexity
 * must be in O(n).
 * 
 * Example 1:
 * 
 * Input: [3, 2, 1]
 * 
 * Output: 1
 * 
 * Explanation: The third maximum is 1.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [1, 2]
 * 
 * Output: 2
 * 
 * Explanation: The third maximum does not exist, so the maximum (2) is
 * returned instead.
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: [2, 2, 3, 1]
 * 
 * Output: 1
 * 
 * Explanation: Note that the third maximum here means the third maximum
 * distinct number.
 * Both numbers with value 2 are both considered as second maximum.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int thirdMax(int[] nums) {
    	long max1 = Long.MIN_VALUE;
    	long max2 = Long.MIN_VALUE;
    	long max3 = Long.MIN_VALUE;
        for(int x : nums){
        	if(x > max1){
        		max3 = max2;
        		max2 = max1;
        		max1 = x;
        	}
        	else if(x < max1 && x > max2){
        		max3 = max2;
        		max2 = x;
        	}
        	else if(x < max2 && x > max3){
        		max3 = x;
        	}
        }
        if(max3 == Long.MIN_VALUE){
        	return (int)max1;
        }
        else{
        	return (int)max3;
        }
    }
}
// @lc code=end
