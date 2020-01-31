/*
 * @lc app=leetcode id=357 lang=java
 *
 * [357] Count Numbers with Unique Digits
 *
 * https://leetcode.com/problems/count-numbers-with-unique-digits/description/
 *
 * algorithms
 * Medium (47.81%)
 * Likes:    318
 * Dislikes: 747
 * Total Accepted:    70.5K
 * Total Submissions: 147.4K
 * Testcase Example:  '2'
 *
 * Given a non-negative integer n, count all numbers with unique digits, x,
 * where 0 ≤ x < 10^n.
 * 
 * 
 * Example:
 * 
 * 
 * Input: 2
 * Output: 91 
 * Explanation: The answer should be the total numbers in the range of 0 ≤ x <
 * 100, 
 * excluding 11,22,33,44,55,66,77,88,99
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if(n > 10){
        	n = 10;
        }
        int ret = 0;
        int p = 362880;
        for(int i = 8; i > n - 1; i--){
        	p /= (9 - i);
        }
        for(int i = n - 1; i >= 0; i--){
        	p /= Math.max((9 - i), 1);
        	ret += 9 * p;
        }
        return ret + 1;
    }
}
// @lc code=end
