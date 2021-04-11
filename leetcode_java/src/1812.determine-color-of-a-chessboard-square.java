/*
 * @lc app=leetcode id=1812 lang=java
 *
 * [1812] Determine Color of a Chessboard Square
 *
 * https://leetcode.com/problems/determine-color-of-a-chessboard-square/description/
 *
 * algorithms
 * Easy (77.05%)
 * Likes:    32
 * Dislikes: 2
 * Total Accepted:    4.8K
 * Total Submissions: 6.3K
 * Testcase Example:  '"a1"'
 *
 * You are given coordinates, a string that represents the coordinates of a
 * square of the chessboard. Below is a chessboard for your reference.
 * 
 * 
 * 
 * Return true if the square is white, and false if the square is black.
 * 
 * The coordinate will always represent a valid chessboard square. The
 * coordinate will always have the letter first, and the number second.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: coordinates = "a1"
 * Output: false
 * Explanation: From the chessboard above, the square with coordinates "a1" is
 * black, so return false.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: coordinates = "h3"
 * Output: true
 * Explanation: From the chessboard above, the square with coordinates "h3" is
 * white, so return true.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: coordinates = "c7"
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * coordinates.length == 2
 * 'a' <= coordinates[0] <= 'h'
 * '1' <= coordinates[1] <= '8'
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean squareIsWhite(String coordinates) {
    	return ((coordinates.charAt(0) - 'a') + (coordinates.charAt(1) - '1')) % 2 == 1;
    }
}
// @lc code=end
