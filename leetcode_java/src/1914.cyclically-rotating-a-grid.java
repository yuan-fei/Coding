/*
 * @lc app=leetcode id=1914 lang=java
 *
 * [1914] Cyclically Rotating a Grid
 *
 * https://leetcode.com/problems/cyclically-rotating-a-grid/description/
 *
 * algorithms
 * Medium (42.73%)
 * Likes:    86
 * Dislikes: 168
 * Total Accepted:    5.3K
 * Total Submissions: 12.4K
 * Testcase Example:  '[[40,10],[30,20]]\n1'
 *
 * You are given an m x n integer matrix grid​​​, where m and n are both even
 * integers, and an integer k.
 * 
 * The matrix is composed of several layers, which is shown in the below image,
 * where each color is its own layer:
 * 
 * 
 * 
 * A cyclic rotation of the matrix is done by cyclically rotating each layer in
 * the matrix. To cyclically rotate a layer once, each element in the layer
 * will take the place of the adjacent element in the counter-clockwise
 * direction. An example rotation is shown below:
 * 
 * Return the matrix after applying k cyclic rotations to it.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[40,10],[30,20]], k = 1
 * Output: [[10,20],[40,30]]
 * Explanation: The figures above represent the grid at every state.
 * 
 * 
 * Example 2:
 * ⁠ 
 * 
 * 
 * Input: grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
 * Output: [[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
 * Explanation: The figures above represent the grid at every state.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 50
 * Both m and n are even integers.
 * 1 <= grid[i][j] <=^ 5000
 * 1 <= k <= 10^9
 * 
 */

// @lc code=start
class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
    	int i = 0;
    	int n = grid.length;
    	int m = grid[0].length;
    	List<List<Integer>> ll = new ArrayList<>();
    	List<Integer> offset = new ArrayList<>();
        while(i < Math.min(n, m) / 2){
        	List<Integer> l = new ArrayList<>();
        	ll.add(l);
        	int r = i;
        	int c = i;
        	while(c < m - i){
        		l.add(grid[r][c]);
        		c++;
        	}
        	c--;
        	r++;
        	while(r < n - i){
        		l.add(grid[r][c]);
        		r++;
        	}
        	r--;
        	c--;
        	while(c >= i){
        		l.add(grid[r][c]);
        		c--;
        	}
        	c++;
        	r--;
        	while(r > i){
        		l.add(grid[r][c]);
        		r--;
        	}
        	i++;
        }
        // System.out.println(ll);
        for(i = 0; i < Math.min(n, m) / 2; i++){
        	List<Integer> l = ll.get(i);
        	offset.add(k % l.size());
        }
        i = 0;
        while(i < Math.min(n, m) / 2){
        	List<Integer> l = ll.get(i);
        	int j = offset.get(i);
        	int r = i;
        	int c = i;
        	while(c < m - i){
        		grid[r][c] = l.get(j++);
        		j %= l.size();
        		c++;
        	}
        	c--;
        	r++;
        	while(r < n - i){
        		grid[r][c] = l.get(j++);
        		j %= l.size();
        		r++;
        	}
        	r--;
        	c--;
        	while(c >= i){
        		grid[r][c] = l.get(j++);
        		j %= l.size();
        		c--;
        	}
        	c++;
        	r--;
        	while(r > i){
        		grid[r][c] = l.get(j++);
        		j %= l.size();
        		r--;
        	}
        	i++;
        }
        return grid;
    }
}
// @lc code=end
