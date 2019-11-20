/*
 * @lc app=leetcode id=1260 lang=java
 *
 * [1260] Shift 2D Grid
 *
 * https://leetcode.com/problems/shift-2d-grid/description/
 *
 * algorithms
 * Easy (56.00%)
 * Likes:    16
 * Dislikes: 14
 * Total Accepted:    3.6K
 * Total Submissions: 6.4K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]\n1'
 *
 * Given a 2D grid of size n * m and an integer k. You need to shift the grid k
 * times.
 * 
 * In one shift operation:
 * 
 * 
 * Element at grid[i][j] becomes at grid[i][j + 1].
 * Element at grid[i][m - 1] becomes at grid[i + 1][0].
 * Element at grid[n - 1][m - 1] becomes at grid[0][0].
 * 
 * 
 * Return the 2D grid after applying shift operation k times.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * Output: [[9,1,2],[3,4,5],[6,7,8]]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
 * Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
 * Output: [[1,2,3],[4,5,6],[7,8,9]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= grid.length <= 50
 * 1 <= grid[i].length <= 50
 * -1000 <= grid[i][j] <= 1000
 * 0 <= k <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
    	int m = grid.length;
    	int n = grid[0].length;
        int[][] ans = new int[m][n];
        k %= m * n;
        for(int i = 0; i < m; i++){
        	for(int j = 0; j < n; j++){
        		int idx = (i * n + j + k) % (m * n);
        		ans[idx / n][idx % n] = grid[i][j];
        	}
        }
        List<List<Integer>> ret = new ArrayList<>();
        for(int i = 0; i < m; i++){
        	ret.add(Arrays.stream(ans[i]).boxed().collect(Collectors.toList()));
        }
        return ret;
    }
}
// @lc code=end
