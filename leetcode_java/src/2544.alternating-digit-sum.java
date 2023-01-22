/*
 * @lc app=leetcode id=2544 lang=java
 *
 * [2544] Alternating Digit Sum
 *
 * https://leetcode.com/problems/alternating-digit-sum/description/
 *
 * algorithms
 * Easy (68.68%)
 * Likes:    59
 * Dislikes: 3
 * Total Accepted:    15.7K
 * Total Submissions: 22.9K
 * Testcase Example:  '521'
 *
 * You are given a positive integer n. Each digit of n has a sign according to
 * the following rules:
 * 
 * 
 * The most significant digit is assigned a positive sign.
 * Each other digit has an opposite sign to its adjacent digits.
 * 
 * 
 * Return the sum of all digits with their corresponding sign.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 521
 * Output: 4
 * Explanation: (+5) + (-2) + (+1) = 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 111
 * Output: 1
 * Explanation: (+1) + (-1) + (+1) = 1.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 886996
 * Output: 0
 * Explanation: (+8) + (-8) + (+6) + (-9) + (+9) + (-6) = 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^9
 * 
 * 
 * 
 * .spoilerbutton {display:block; border:dashed; padding: 0px 0px; margin:10px
 * 0px; font-size:150%; font-weight: bold; color:#000000;
 * background-color:cyan; outline:0; 
 * }
 * .spoiler {overflow:hidden;}
 * .spoiler > div {-webkit-transition: all 0s ease;-moz-transition: margin 0s
 * ease;-o-transition: all 0s ease;transition: margin 0s ease;}
 * .spoilerbutton[value="Show Message"] + .spoiler > div {margin-top:-500%;}
 * .spoilerbutton[value="Hide Message"] + .spoiler {padding:5px;}
 * 
 * 
 */

// @lc code=start
class Solution {
    public int alternateDigitSum(int n) {
        int digit = 0;
        int ans = 0;
        int cur = 1;
        while(n != 0){
            digit++;
            ans += cur * (n % 10);
            n /= 10;
            cur *= -1;
        }
        return digit % 2 == 0 ? -ans : ans;
    }
}
// @lc code=end
