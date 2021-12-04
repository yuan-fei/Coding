/*
 * @lc app=leetcode id=2088 lang=java
 *
 * [2088] Count Fertile Pyramids in a Land
 *
 * https://leetcode.com/problems/count-fertile-pyramids-in-a-land/description/
 *
 * algorithms
 * Hard (59.16%)
 * Likes:    64
 * Dislikes: 1
 * Total Accepted:    1.6K
 * Total Submissions: 2.7K
 * Testcase Example:  '[[0,1,1,0],[1,1,1,1]]'
 *
 * A farmer has a rectangular grid of land with m rows and n columns that can
 * be divided into unit cells. Each cell is either fertile (represented by a 1)
 * or barren (represented by a 0). All cells outside the grid are considered
 * barren.
 * 
 * A pyramidal plot of land can be defined as a set of cells with the following
 * criteria:
 * 
 * 
 * The number of cells in the set has to be greater than 1 and all cells must
 * be fertile.
 * The apex of a pyramid is the topmost cell of the pyramid. The height of a
 * pyramid is the number of rows it covers. Let (r, c) be the apex of the
 * pyramid, and its height be h. Then, the plot comprises of cells (i, j) where
 * r <= i <= r + h - 1 and c - (i - r) <= j <= c + (i - r).
 * 
 * 
 * An inverse pyramidal plot of land can be defined as a set of cells with
 * similar criteria:
 * 
 * 
 * The number of cells in the set has to be greater than 1 and all cells must
 * be fertile.
 * The apex of an inverse pyramid is the bottommost cell of the inverse
 * pyramid. The height of an inverse pyramid is the number of rows it covers.
 * Let (r, c) be the apex of the pyramid, and its height be h. Then, the plot
 * comprises of cells (i, j) where r - h + 1 <= i <= r and c - (r - i) <= j <=
 * c + (r - i).
 * 
 * 
 * Some examples of valid and invalid pyramidal (and inverse pyramidal) plots
 * are shown below. Black cells indicate fertile cells.
 * 
 * Given a 0-indexed m x n binary matrix grid representing the farmland, return
 * the total number of pyramidal and inverse pyramidal plots that can be found
 * in grid.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[0,1,1,0],[1,1,1,1]]
 * Output: 2
 * Explanation:
 * The 2 possible pyramidal plots are shown in blue and red respectively.
 * There are no inverse pyramidal plots in this grid. 
 * Hence total number of pyramidal and inverse pyramidal plots is 2 + 0 = 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,1,1],[1,1,1]]
 * Output: 2
 * Explanation:
 * The pyramidal plot is shown in blue, and the inverse pyramidal plot is shown
 * in red. 
 * Hence the total number of plots is 1 + 1 = 2.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
 * Output: 0
 * Explanation:
 * There are no pyramidal or inverse pyramidal plots in the grid.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: grid = [[1,1,1,1,0],[1,1,1,1,1],[1,1,1,1,1],[0,1,0,0,1]]
 * Output: 13
 * Explanation:
 * There are 7 pyramidal plots, 3 of which are shown in the 2nd and 3rd
 * figures.
 * There are 6 inverse pyramidal plots, 2 of which are shown in the last
 * figure.
 * The total number of plots is 7 + 6 = 13.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 1000
 * 1 <= m * n <= 10^5
 * grid[i][j] is either 0 or 1.
 * 
 * 
 */

// @lc code=start
class Solution {
    int[][] pSum;
    int n,m;
    public int countPyramids(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        pSum = new int[n][m + 1];
        for(int i = 0; i < n; i++){
            for(int j = 1; j <= m; j++){
                pSum[i][j] = pSum[i][j - 1] + grid[i][j - 1];
            }
        }
        int res = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    int cnt = getMaxPyramidal(i, j);
                    res += cnt - 1;
                    cnt = getMaxInversePyramidal(i, j);
                    res += cnt - 1;    
                }
            }
        }
        return res;
    }

    int getMaxPyramidal(int r, int c){
        int i = 0;
        for(; i + r < n; i++){
            if(c + i + 1 <= m && c - i >= 0 && pSum[r + i][c + i + 1] - pSum[r + i][c - i] == 2 * i + 1){

            }
            else{
                break;
            }
        }
        return i;
    }

    int getMaxInversePyramidal(int r, int c){
        int i = 0;
        for(; r - i >= 0; i++){
            if(c + i + 1 <= m && c - i >= 0 && pSum[r - i][c + i + 1] - pSum[r - i][c - i] == 2 * i + 1){

            }
            else{
                break;
            }
        }
        return i;
    }
}
// @lc code=end
