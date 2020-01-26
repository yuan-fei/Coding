/*
 * @lc app=leetcode id=1329 lang=java
 *
 * [1329] Sort the Matrix Diagonally
 *
 * https://leetcode.com/problems/sort-the-matrix-diagonally/description/
 *
 * algorithms
 * Medium (75.90%)
 * Likes:    20
 * Dislikes: 13
 * Total Accepted:    1.9K
 * Total Submissions: 2.5K
 * Testcase Example:  '[[3,3,1,1],[2,2,1,2],[1,1,1,2]]'
 *
 * Given a m * n matrix mat of integers, sort it diagonally in ascending order
 * from the top-left to the bottom-right then return the sorted array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 * 
 */

// @lc code=start
class Solution {
    public int[][] diagonalSort(int[][] mat) {
    	int m = mat.length;
    	if(m == 0){
    		return mat;
    	}
    	int n = mat[0].length;
        for(int i = 0; i < m; i++){
        	List<Integer> l = new ArrayList<>();
        	for(int j = 0; j < n && i + j < m; j++){
        		// System.out.println(Arrays.toString(new int[]{i + j, j}));
        		l.add(mat[i + j][j]);
        	}
        	Collections.sort(l);
        	int k = 0;
        	for(int j = 0; j < n && i + j < m; j++){
        		// System.out.println(Arrays.toString(new int[]{i + j, j}));
        		mat[i + j][j] = l.get(k++);
        	}
        }
        for(int i = 1; i < n; i++){
        	List<Integer> l = new ArrayList<>();
        	for(int j = 0; j < m && i + j < n; j++){
        		// System.out.println(Arrays.toString(new int[]{j, i + j}));
        		l.add(mat[j][i + j]);
        	}
        	int k = 0;
        	Collections.sort(l);
        	for(int j = 0; j < m && i + j < n; j++){
        		// System.out.println(Arrays.toString(new int[]{j, i + j}));
        		mat[j][i + j] = l.get(k++);
        	}
        }
        return mat;
    }
}
// @lc code=end
