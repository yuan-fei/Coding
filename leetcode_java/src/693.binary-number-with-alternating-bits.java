/*
 * @lc app=leetcode id=693 lang=java
 *
 * [693] Binary Number with Alternating Bits
 *
 * https://leetcode.com/problems/binary-number-with-alternating-bits/description/
 *
 * algorithms
 * Easy (60.45%)
 * Likes:    738
 * Dislikes: 98
 * Total Accepted:    82.2K
 * Total Submissions: 135.9K
 * Testcase Example:  '5'
 *
 * Given a positive integer, check whether it has alternating bits: namely, if
 * two adjacent bits will always have different values.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 5
 * Output: true
 * Explanation: The binary representation of 5 is: 101
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 7
 * Output: false
 * Explanation: The binary representation of 7 is: 111.
 * 
 * Example 3:
 * 
 * 
 * Input: n = 11
 * Output: false
 * Explanation: The binary representation of 11 is: 1011.
 * 
 * Example 4:
 * 
 * 
 * Input: n = 10
 * Output: true
 * Explanation: The binary representation of 10 is: 1010.
 * 
 * Example 5:
 * 
 * 
 * Input: n = 3
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 2^31 - 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean hasAlternatingBits(int n) {
        int pattern = Integer.highestOneBit(n);
        int mask = pattern - 1;
        return (n ^ (n >> 1) ^ mask) == pattern;
    }
}
// @lc code=end
