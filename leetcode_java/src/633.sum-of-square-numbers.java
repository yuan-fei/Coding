/*
 * @lc app=leetcode id=633 lang=java
 *
 * [633] Sum of Square Numbers
 *
 * https://leetcode.com/problems/sum-of-square-numbers/description/
 *
 * algorithms
 * Medium (32.35%)
 * Likes:    617
 * Dislikes: 362
 * Total Accepted:    77.3K
 * Total Submissions: 238.8K
 * Testcase Example:  '5'
 *
 * Given a non-negative integer c, decide whether there're two integers a and b
 * such that a^2 + b^2 = c.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: c = 5
 * Output: true
 * Explanation: 1 * 1 + 2 * 2 = 5
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: c = 3
 * Output: false
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: c = 4
 * Output: true
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: c = 2
 * Output: true
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: c = 1
 * Output: true
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= c <= 2^31 - 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean judgeSquareSum(int c) {
    	Set<Long> s = new HashSet<>();
        for(long i = 0; i * i <= c; i++){
        	s.add(i * i);
        	if(s.contains(c - i * i)){
        		return true;
        	}
        }
        return false;
    }
}
// @lc code=end
