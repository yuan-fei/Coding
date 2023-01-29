/*
 * @lc app=leetcode id=2549 lang=java
 *
 * [2549] Count Distinct Numbers on Board
 *
 * https://leetcode.com/problems/count-distinct-numbers-on-board/description/
 *
 * algorithms
 * Easy (56.90%)
 * Likes:    44
 * Dislikes: 37
 * Total Accepted:    14.4K
 * Total Submissions: 25.3K
 * Testcase Example:  '5'
 *
 * You are given a positive integer n, that is initially placed on a board.
 * Every day, for 10^9 days, you perform the following procedure:
 * 
 * 
 * For each number x present on the board, find all numbers 1 <= i <= n such
 * that x % i == 1.
 * Then, place those numbers on the board.
 * 
 * 
 * Return the number of distinct integers present on the board after 10^9 days
 * have elapsed.
 * 
 * Note:
 * 
 * 
 * Once a number is placed on the board, it will remain on it until the
 * end.
 * % stands for the modulo operation. For example, 14 % 3 is 2.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 5
 * Output: 4
 * Explanation: Initially, 5 is present on the board. 
 * The next day, 2 and 4 will be added since 5 % 2 == 1 and 5 % 4 == 1. 
 * After that day, 3 will be added to the board because 4 % 3 == 1. 
 * At the end of a billion days, the distinct numbers on the board will be 2,
 * 3, 4, and 5. 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 3
 * Output: 2
 * Explanation: 
 * Since 3 % 2 == 1, 2 will be added to the board. 
 * After a billion days, the only two distinct numbers on the board are 2 and
 * 3. 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int distinctIntegers(int n) {
        return n <= 2 ? 1 : n - 1;   
    }
}
// @lc code=end
