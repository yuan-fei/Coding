/*
 * @lc app=leetcode id=1317 lang=java
 *
 * [1317] Convert Integer to the Sum of Two No-Zero Integers
 *
 * https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/description/
 *
 * algorithms
 * Easy (58.27%)
 * Likes:    6
 * Dislikes: 14
 * Total Accepted:    4.2K
 * Total Submissions: 7.2K
 * Testcase Example:  '2'
 *
 * Given an integer n. No-Zero integer is a positive integer which doesn't
 * contain any 0 in its decimal representation.
 * 
 * Return a list of two integers [A, B] where:
 * 
 * 
 * A and B are No-Zero integers.
 * A + B = n
 * 
 * 
 * It's guarateed that there is at least one valid solution. If there are many
 * valid solutions you can return any of them.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 2
 * Output: [1,1]
 * Explanation: A = 1, B = 1. A + B = n and both A and B don't contain any 0 in
 * their decimal representation.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 11
 * Output: [2,9]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 10000
 * Output: [1,9999]
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: n = 69
 * Output: [1,68]
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: n = 1010
 * Output: [11,999]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 10^4
 * 
 */

// @lc code=start
class Solution {
    public int[] getNoZeroIntegers(int n) {
    	if(n < 2){
    		return new int[]{0, n};
    	}
        if(n / 10 == 0){
        	return new int[]{1, n - 1};
        }
        // n > 10
        int rem = n % 10;
        if(rem < 2){
        	int[] ret = getNoZeroIntegers(n / 10 - 1);
        	ret[0] = ret[0] * 10 + 2;
        	ret[1] = ret[1] * 10 + 10 + rem - 2;
        	return ret;
        }
        else{
        	int[] ret = getNoZeroIntegers(n / 10);
        	ret[0] = ret[0] * 10 + 1;
        	ret[1] = ret[1] * 10 + rem - 1;
        	return ret;
        }
    }
}
// @lc code=end
