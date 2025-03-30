/*
 * @lc app=leetcode id=3099 lang=java
 *
 * [3099] Harshad Number
 *
 * https://leetcode.com/problems/harshad-number/description/
 *
 * algorithms
 * Easy (83.03%)
 * Likes:    151
 * Dislikes: 7
 * Total Accepted:    78.2K
 * Total Submissions: 94.1K
 * Testcase Example:  '18'
 *
 * An integer divisible by the sum of its digits is said to be a Harshad
 * number. You are given an integer x. Return the sum of the digits of x if x
 * is a Harshad number, otherwise, return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: x = 18
 * 
 * Output: 9
 * 
 * Explanation:
 * 
 * The sum of digits of x is 9. 18 is divisible by 9. So 18 is a Harshad number
 * and the answer is 9.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: x = 23
 * 
 * Output: -1
 * 
 * Explanation:
 * 
 * The sum of digits of x is 5. 23 is not divisible by 5. So 23 is not a
 * Harshad number and the answer is -1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= x <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int t = x;
        int sum = 0;
        while(t != 0){
            sum += t % 10;
            t /= 10;
        }
        return (x % sum == 0) ? sum : -1;
    }
}
// @lc code=end
