/*
 * @lc app=leetcode id=661 lang=java
 *
 * [661] Image Smoother
 *
 * https://leetcode.com/problems/image-smoother/description/
 *
 * algorithms
 * Easy (52.07%)
 * Likes:    282
 * Dislikes: 1182
 * Total Accepted:    52.2K
 * Total Submissions: 100.3K
 * Testcase Example:  '[[1,1,1],[1,0,1],[1,1,1]]'
 *
 * Given a 2D integer matrix M representing the gray scale of an image, you
 * need to design a smoother to make the gray scale of each cell becomes the
 * average gray scale (rounding down) of all the 8 surrounding cells and
 * itself.  If a cell has less than 8 surrounding cells, then use as many as
 * you can.
 * 
 * Example 1:
 * 
 * Input:
 * [[1,1,1],
 * ⁠[1,0,1],
 * ⁠[1,1,1]]
 * Output:
 * [[0, 0, 0],
 * ⁠[0, 0, 0],
 * ⁠[0, 0, 0]]
 * Explanation:
 * For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
 * For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
 * For the point (1,1): floor(8/9) = floor(0.88888889) = 0
 * 
 * 
 * 
 * Note:
 * 
 * The value in the given matrix is in the range of [0, 255].
 * The length and width of the given matrix are in the range of [1, 150].
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[][] imageSmoother(int[][] M) {
        int n = M.length;
        int m = M[0].length;
        int[][] ret = new int[n][m];
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        for(int i = 0; i < n; i++){
        	for(int j = 0; j < m; j++){
        		int sum = M[i][j];
        		int cnt = 1;
        		for(int[] dir: dirs){
        			int x = i + dir[0];
        			int y = j + dir[1];
        			if(x >= 0 && x < n && y >= 0 && y < m){
        				sum += M[x][y];
        				cnt++;
        			}
        		}
        		ret[i][j] = sum / cnt;
        	}
        }
        return ret;
    }
}
// @lc code=end
