/*
 * @lc app=leetcode id=2711 lang=java
 *
 * [2711] Difference of Number of Distinct Values on Diagonals
 *
 * https://leetcode.com/problems/difference-of-number-of-distinct-values-on-diagonals/description/
 *
 * algorithms
 * Medium (72.61%)
 * Likes:    91
 * Dislikes: 169
 * Total Accepted:    16.6K
 * Total Submissions: 22.8K
 * Testcase Example:  '[[1,2,3],[3,1,5],[3,2,1]]'
 *
 * Given a 0-indexed 2D grid of size m x n, you should find the matrix answer
 * of size m x n.
 * 
 * The value of each cell (r, c) of the matrix answer is calculated in the
 * following way:
 * 
 * 
 * Let topLeft[r][c] be the number of distinct values in the top-left diagonal
 * of the cell (r, c) in the matrix grid.
 * Let bottomRight[r][c] be the number of distinct values in the bottom-right
 * diagonal of the cell (r, c) in the matrix grid.
 * 
 * 
 * Then answer[r][c] = |topLeft[r][c] - bottomRight[r][c]|.
 * 
 * Return the matrix answer.
 * 
 * A matrix diagonal is a diagonal line of cells starting from some cell in
 * either the topmost row or leftmost column and going in the bottom-right
 * direction until reaching the matrix's end.
 * 
 * A cell (r1, c1) belongs to the top-left diagonal of the cell (r, c), if both
 * belong to the same diagonal and r1 < r. Similarly is defined bottom-right
 * diagonal.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * Input: grid = [[1,2,3],[3,1,5],[3,2,1]]
 * Output: [[1,1,0],[1,0,1],[0,1,1]]
 * Explanation: The 1^st diagram denotes the initial grid. 
 * The 2^nd diagram denotes a grid for cell (0,0), where blue-colored cells are
 * cells on its bottom-right diagonal.
 * The 3^rd diagram denotes a grid for cell (1,2), where red-colored cells are
 * cells on its top-left diagonal.
 * The 4^th diagram denotes a grid for cell (1,1), where blue-colored cells are
 * cells on its bottom-right diagonal and red-colored cells are cells on its
 * top-left diagonal.
 * - The cell (0,0) contains [1,1] on its bottom-right diagonal and [] on its
 * top-left diagonal. The answer is |1 - 0| = 1.
 * - The cell (1,2) contains [] on its bottom-right diagonal and [2] on its
 * top-left diagonal. The answer is |0 - 1| = 1.
 * - The cell (1,1) contains [1] on its bottom-right diagonal and [1] on its
 * top-left diagonal. The answer is |1 - 1| = 0.
 * The answers of other cells are similarly calculated.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1]]
 * Output: [[0]]
 * Explanation: - The cell (0,0) contains [] on its bottom-right diagonal and
 * [] on its top-left diagonal. The answer is |0 - 0| = 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n, grid[i][j] <= 50
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[][] differenceOfDistinctValues(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Map<Integer, Set<Integer>> seensByDiagnal = new HashMap<>();
        int[][] topLeftScore = new int[n][m];
        for(int r = 0; r < n; r++){
            for(int c = 0; c < m; c++){
                int diagId = c - r;
                Set<Integer> seen = seensByDiagnal.computeIfAbsent(diagId, k -> new HashSet<>());
                topLeftScore[r][c] = seen.size();
                seen.add(grid[r][c]);
            }
        }
        seensByDiagnal = new HashMap<>();
        int[][] bottomRightScore = new int[n][m];
        for(int r = n - 1; r >= 0; r--){
            for(int c = m - 1; c >= 0; c--){
                int diagId = c - r;
                Set<Integer> seen = seensByDiagnal.computeIfAbsent(diagId, k -> new HashSet<>());
                bottomRightScore[r][c] = seen.size();
                seen.add(grid[r][c]);
            }
        }
        int[][] ans = new int[n][m];
        for(int r = 0; r < n; r++){
            for(int c = 0; c < m; c++){
                ans[r][c] = Math.abs(topLeftScore[r][c] - bottomRightScore[r][c]);
            }
        }
        return ans;
    }
}
// @lc code=end
