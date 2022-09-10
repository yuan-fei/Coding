/*
 * @lc app=leetcode id=2396 lang=java
 *
 * [2396] Strictly Palindromic Number
 *
 * https://leetcode.com/problems/strictly-palindromic-number/description/
 *
 * algorithms
 * Medium (84.33%)
 * Likes:    57
 * Dislikes: 219
 * Total Accepted:    16.7K
 * Total Submissions: 19.8K
 * Testcase Example:  '9'
 *
 * An integer n is strictly palindromic if, for every base b between 2 and n -
 * 2 (inclusive), the string representation of the integer n in base b is
 * palindromic.
 * 
 * Given an integer n, return true if n is strictly palindromic and false
 * otherwise.
 * 
 * A string is palindromic if it reads the same forward and backward.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 9
 * Output: false
 * Explanation: In base 2: 9 = 1001 (base 2), which is palindromic.
 * In base 3: 9 = 100 (base 3), which is not palindromic.
 * Therefore, 9 is not strictly palindromic so we return false.
 * Note that in bases 4, 5, 6, and 7, n = 9 is also not palindromic.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 4
 * Output: false
 * Explanation: We only consider base 2: 4 = 100 (base 2), which is not
 * palindromic.
 * Therefore, we return false.
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 4 <= n <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isStrictlyPalindromic(int n) {
        return n == 3;
    }
}
// @lc code=end
