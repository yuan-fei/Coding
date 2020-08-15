/*
 * @lc app=leetcode id=507 lang=java
 *
 * [507] Perfect Number
 *
 * https://leetcode.com/problems/perfect-number/description/
 *
 * algorithms
 * Easy (35.50%)
 * Likes:    278
 * Dislikes: 628
 * Total Accepted:    64.8K
 * Total Submissions: 182.6K
 * Testcase Example:  '28'
 *
 * We define the Perfect Number is a positive integer that is equal to the sum
 * of all its positive divisors except itself. 
 * 
 * Now, given an integer n, write a function that returns true when it is a
 * perfect number and false when it is not.
 * 
 * 
 * Example:
 * 
 * Input: 28
 * Output: True
 * Explanation: 28 = 1 + 2 + 4 + 7 + 14
 * 
 * 
 * 
 * Note:
 * The input number n will not exceed 100,000,000. (1e8)
 * 
 */

// @lc code=start
class Solution {
    public boolean checkPerfectNumber(int num) {
    	if(num == 0){
    		return false;
    	}
    	int sum = 0;
        for(int d = 1; d * d <= num; d++){
        	if(num % d == 0){
        		if(d != num){
					sum += d;
        		}
        		
        		if(num / d != d && num / d != num){
        			sum += num / d;
        		}
        	}
        }
        return num == sum;
    }
}
// @lc code=end
