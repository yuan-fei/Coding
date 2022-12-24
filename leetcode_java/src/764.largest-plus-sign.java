/*
 * @lc app=leetcode id=764 lang=java
 *
 * [764] Largest Plus Sign
 *
 * https://leetcode.com/problems/largest-plus-sign/description/
 *
 * algorithms
 * Medium (48.41%)
 * Likes:    1261
 * Dislikes: 207
 * Total Accepted:    51.7K
 * Total Submissions: 106.8K
 * Testcase Example:  '5\n[[4,2]]'
 *
 * You are given an integer n. You have an n x n binary grid grid with all
 * values initially 1's except for some indices given in the array mines. The
 * i^th element of the array mines is defined as mines[i] = [xi, yi] where
 * grid[xi][yi] == 0.
 * 
 * Return the order of the largest axis-aligned plus sign of 1's contained in
 * grid. If there is none, return 0.
 * 
 * An axis-aligned plus sign of 1's of order k has some center grid[r][c] == 1
 * along with four arms of length k - 1 going up, down, left, and right, and
 * made of 1's. Note that there could be 0's or 1's beyond the arms of the plus
 * sign, only the relevant area of the plus sign is checked for 1's.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 5, mines = [[4,2]]
 * Output: 2
 * Explanation: In the above grid, the largest plus sign can only be of order
 * 2. One of them is shown.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 1, mines = [[0,0]]
 * Output: 0
 * Explanation: There is no plus sign, so return 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 500
 * 1 <= mines.length <= 5000
 * 0 <= xi, yi < n
 * All the pairs (xi, yi) are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    int[][] board;
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        board = new int[n][n];
        for(int[] m : mines){
            board[m[0]][m[1]] = 1;
        }
        for(int i = (n + 1) / 2; i >= 0; i--){
            int min = i - 1;
            int max = n - i;
            for(int r = min; r <= max; r++){
                for(int c = min; c <= max; c++){
                    if(check(r, c, i)){
                        return i;
                    }
                }
            }
        }
        return 0;
    }

    boolean check(int r, int c, int d){
        int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};
        for(int[] dir : dirs){
            for(int i = 0; i < d; i++){
                if(board[r + i * dir[0]][c + i * dir[1]] == 1){
                    return false;
                }
            }
        }
        return true;
    }
}
// @lc code=end
