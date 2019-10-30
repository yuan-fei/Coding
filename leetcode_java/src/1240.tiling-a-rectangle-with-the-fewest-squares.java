/*
 * @lc app=leetcode id=1240 lang=java
 *
 * [1240] Tiling a Rectangle with the Fewest Squares
 *
 * https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/description/
 *
 * algorithms
 * Hard (42.88%)
 * Likes:    9
 * Dislikes: 65
 * Total Accepted:    741
 * Total Submissions: 1.7K
 * Testcase Example:  '2\n3'
 *
 * Given a rectangle of size n x m, find the minimum number of integer-sided
 * squares that tile the rectangle.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: n = 2, m = 3
 * Output: 3
 * Explanation: 3 squares are necessary to cover the rectangle.
 * 2 (squares of 1x1)
 * 1 (square of 2x2)
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: n = 5, m = 8
 * Output: 5
 * 
 * 
 * Example 3:
 * 
 * 
 * 
 * 
 * Input: n = 11, m = 13
 * Output: 6
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 13
 * 1 <= m <= 13
 * 
 * 
 */

// @lc code=start
class Solution {
    int ans = Integer.MAX_VALUE;
    public int tilingRectangle(int n, int m) {
        dfs(0, 0, new boolean[n][m], 0);
        return ans;
    }
    // (r, c) is the starting point for selecting a square
    // rect records the status of current rectangle
    // cnt is the number of squares we have covered
    private void dfs(int r, int c, boolean[][] rect, int cnt) {
        int n = rect.length, m = rect[0].length;
        // optimization 1: The current cnt >= the current answer
        if (cnt >= ans) return;
        
        // Successfully cover the whole rectangle
        if (r >= n) {
            ans = cnt; 
            return;
        }
        
        // Successfully cover the area [0, ..., n][0, ..., c] => Move to next row
        if (c >= m) {
            dfs(r+1, 0, rect, cnt); 
            return;
        }
        
        // If (r, c) is already covered => move to next point (r, c+1)
        if (rect[r][c]) {
            dfs(r, c+1, rect, cnt);
            return;
        }
        
        // Try all the possible size of squares starting from (r, c)
        for (int k = Math.min(n-r, m-c); k >= 1 && isAvailable(rect, r, c, k); k--) {
            cover(rect, r, c, k);
	    	dfs(r, c+1, rect, cnt+1);
            uncover(rect, r, c, k);
	    }
    }
    // Check if the area [r, ..., r+k][c, ..., c+k] is alreadc covered
    private boolean isAvailable(boolean[][] rect, int r, int c, int k) {
        for (int i = 0; i < k; i++){
            for(int j = 0; j < k; j++){
                if(rect[r+i][c+j]) return false;
            }
        }
        return true;
    }
    // Cover the area [r, ..., r+k][c, ..., c+k] with a k * k square
    private void cover(boolean[][] rect, int r, int c, int k) {
        for(int i = 0; i < k; i++){
            for(int j = 0; j < k; j++){
                rect[r+i][c+j] = true;
            }
        }
    }
    // Uncover the area [r, ..., r+k][c, ..., c+k]
    private void uncover(boolean[][] rect, int r, int c, int k) {
        for(int i = 0; i < k; i++){
            for(int j = 0; j < k; j++){
                rect[r+i][c+j] = false;
            }
        }
    }
}
// @lc code=end
