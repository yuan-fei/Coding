/*
 * @lc app=leetcode id=738 lang=java
 *
 * [738] Monotone Increasing Digits
 *
 * https://leetcode.com/problems/monotone-increasing-digits/description/
 *
 * algorithms
 * Medium (47.04%)
 * Likes:    1076
 * Dislikes: 92
 * Total Accepted:    42K
 * Total Submissions: 89.3K
 * Testcase Example:  '10'
 *
 * An integer has monotone increasing digits if and only if each pair of
 * adjacent digits x and y satisfy x <= y.
 * 
 * Given an integer n, return the largest number that is less than or equal to
 * n with monotone increasing digits.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 10
 * Output: 9
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 1234
 * Output: 1234
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 332
 * Output: 299
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= n <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int monotoneIncreasingDigits(int n) {
        String s = "" + n;
        String ans = "";
        String all9s = "";

        for(int i = s.length() - 1; i >= 0; i--){
            if(ans.equals("")|| s.charAt(i) <= ans.charAt(0)){
                ans = s.charAt(i) + ans;
            }
            else{
                ans = (char)(s.charAt(i) - 1) + all9s;
            }
            all9s += '9';
        }
        return Integer.parseInt(ans);
    }
}
// @lc code=end
