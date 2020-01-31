/*
 * @lc app=leetcode id=367 lang=java
 *
 * [367] Valid Perfect Square
 *
 * https://leetcode.com/problems/valid-perfect-square/description/
 *
 * algorithms
 * Easy (40.79%)
 * Likes:    589
 * Dislikes: 133
 * Total Accepted:    140.7K
 * Total Submissions: 344.7K
 * Testcase Example:  '16'
 *
 * Given a positive integer num, write a function which returns True if num is
 * a perfect square else False.
 * 
 * Note: Do not use any built-in library function such as sqrt.
 * 
 * Example 1:
 * 
 * 
 * 
 * Input: 16
 * Output: true
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 14
 * Output: false
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isPerfectSquare(int num) {
        long low = 0;
        long high = num;
        while(low + 1 < high){
        	long mid = low + (high - low) / 2;
        	// System.out.println(Arrays.asList(low, mid, high));
        	if(mid * mid == num){
        		return true;
        	}
        	else if(mid * mid > num){
        		high = mid;
        	}
        	else{
        		low = mid;
        	}
        }
        return (low * low == num || high * high == num);
    }
}
// @lc code=end
