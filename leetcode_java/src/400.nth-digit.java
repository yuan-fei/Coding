/*
 * @lc app=leetcode id=400 lang=java
 *
 * [400] Nth Digit
 *
 * https://leetcode.com/problems/nth-digit/description/
 *
 * algorithms
 * Medium (31.14%)
 * Likes:    331
 * Dislikes: 976
 * Total Accepted:    55.2K
 * Total Submissions: 177.1K
 * Testcase Example:  '3'
 *
 * Find the n^th digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8,
 * 9, 10, 11, ... 
 * 
 * Note:
 * n is positive and will fit within the range of a 32-bit signed integer (n <
 * 2^31).
 * 
 * 
 * Example 1:
 * 
 * Input:
 * 3
 * 
 * Output:
 * 3
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * 11
 * 
 * Output:
 * 0
 * 
 * Explanation:
 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a
 * 0, which is part of the number 10.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findNthDigit(int num) {
    	if(num < 10){
    		return num;
    	}
    	long n = num;
    	int nDigit = 1;
    	long cnt = 9;
    	long totalDigits = 9;
        while(n > totalDigits){
        	n -= totalDigits;
        	nDigit++;
        	cnt *= 10;
			totalDigits = cnt * nDigit;
        }
        long target = (int)Math.pow(10, nDigit - 1) + (n + nDigit - 1) / nDigit - 1;
        System.out.println(target);
        String s = "" + target;
        return Integer.parseInt(s.charAt((int)((n + nDigit - 1) % nDigit)) + "");
    }
}
// @lc code=end
