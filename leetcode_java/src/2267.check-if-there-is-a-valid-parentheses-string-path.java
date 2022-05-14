/*
 * @lc app=leetcode id=2267 lang=java
 *
 * [2267]  Check if There Is a Valid Parentheses String Path
 *
 * https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path/description/
 *
 * algorithms
 * Hard (31.95%)
 * Likes:    112
 * Dislikes: 2
 * Total Accepted:    3.5K
 * Total Submissions: 10.9K
 * Testcase Example:  '[["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]'
 *
 * A parentheses string is a non-empty string consisting only of '(' and ')'.
 * It is valid if any of the following conditions is true:
 * 
 * 
 * It is ().
 * It can be written as AB (A concatenated with B), where A and B are valid
 * parentheses strings.
 * It can be written as (A), where A is a valid parentheses string.
 * 
 * 
 * You are given an m x n matrix of parentheses grid. A valid parentheses
 * string path in the grid is a path satisfying all of the following
 * conditions:
 * 
 * 
 * The path starts from the upper left cell (0, 0).
 * The path ends at the bottom-right cell (m - 1, n - 1).
 * The path only ever moves down or right.
 * The resulting parentheses string formed by the path is valid.
 * 
 * 
 * Return true if there exists a valid parentheses string path in the grid.
 * Otherwise, return false.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
 * Output: true
 * Explanation: The above diagram shows two possible paths that form valid
 * parentheses strings.
 * The first path shown results in the valid parentheses string "()(())".
 * The second path shown results in the valid parentheses string "((()))".
 * Note that there may be other valid parentheses string paths.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[")",")"],["(","("]]
 * Output: false
 * Explanation: The two possible paths form the parentheses strings "))(" and
 * ")((". Since neither of them are valid parentheses strings, we return
 * false.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * grid[i][j] is either '(' or ')'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean hasValidPath(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][][] dp = new boolean[n + 1][m + 1][n + m];
        dp[0][1][0] = true;
        dp[1][0][0] = true;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                for(int k = 0; k < n + m; k++){
                    if(grid[i - 1][j - 1] == '('){
                        if(k > 0){
                            dp[i][j][k] |= dp[i][j - 1][k - 1];
                            dp[i][j][k] |= dp[i - 1][j][k - 1];    
                        }
                    }
                    else{
                        if(k + 1 < m + n){
                            dp[i][j][k] |= dp[i][j - 1][k + 1];
                            dp[i][j][k] |= dp[i - 1][j][k + 1];    
                        }
                    }
                }
                // System.out.println(i + ", " + j + " = " + Arrays.toString(dp[i][j]));
            }
        }

        return dp[n][m][0];
    }
}
// @lc code=end
