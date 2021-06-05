/*
 * @lc app=leetcode id=1878 lang=java
 *
 * [1878] Get Biggest Three Rhombus Sums in a Grid
 *
 * https://leetcode.com/problems/get-biggest-three-rhombus-sums-in-a-grid/description/
 *
 * algorithms
 * Medium (39.80%)
 * Likes:    58
 * Dislikes: 152
 * Total Accepted:    3.1K
 * Total Submissions: 7.9K
 * Testcase Example:  '[[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]'
 *
 * You are given an m x n integer matrix grid​​​.
 * 
 * A rhombus sum is the sum of the elements that form the border of a regular
 * rhombus shape in grid​​​. The rhombus must have the shape of a square
 * rotated 45 degrees with each of the corners centered in a grid cell. Below
 * is an image of four valid rhombus shapes with the corresponding colored
 * cells that should be included in each rhombus sum:
 * 
 * Note that the rhombus can have an area of 0, which is depicted by the purple
 * rhombus in the bottom right corner.
 * 
 * Return the biggest three distinct rhombus sums in the grid in descending
 * order. If there are less than three distinct values, return all of them.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid =
 * [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
 * Output: [228,216,211]
 * Explanation: The rhombus shapes for the three biggest distinct rhombus sums
 * are depicted above.
 * - Blue: 20 + 3 + 200 + 5 = 228
 * - Red: 200 + 2 + 10 + 4 = 216
 * - Green: 5 + 200 + 4 + 2 = 211
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [20,9,8]
 * Explanation: The rhombus shapes for the three biggest distinct rhombus sums
 * are depicted above.
 * - Blue: 4 + 2 + 6 + 8 = 20
 * - Red: 9 (area 0 rhombus in the bottom right corner)
 * - Green: 8 (area 0 rhombus in the bottom middle)
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[7,7,7]]
 * Output: [7]
 * Explanation: All three possible rhombus sums are the same, so return
 * [7].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] getBiggestThree(int[][] grid) {
    	int n = grid.length;
    	int m = grid[0].length;
    	Map<Integer, int[]> left = new HashMap<Integer, int[]>();
    	Map<Integer, int[]> right = new HashMap<Integer, int[]>();
    	TreeSet<Integer> ts  = new TreeSet<>();
    	for(int sum = 0; sum < n + m - 1; sum++){
    		int[] pSum = new int[n + 1];
    		for(int i = 0; i < n; i++){
    			pSum[i + 1] = pSum[i];
    			if(sum - i >= 0 && sum - i < m){
    				pSum[i + 1] += grid[i][sum - i];
    			}
    		}
    		// System.out.println(sum + " s= " +  Arrays.toString(pSum));
    		left.put(sum, pSum);
    	}

    	for(int diff = -(n - 1); diff < m; diff++){
    		int[] pSum = new int[n + 1];
    		for(int i = 0; i < n; i++){
    			pSum[i + 1] = pSum[i];
    			if(i + diff >= 0 && i + diff < m){
    				pSum[i + 1] += grid[i][i + diff];
    			}
    		}
    		// System.out.println(diff + " d= " + Arrays.toString(pSum));
    		right.put(diff, pSum);
    	}
    	PriorityQueue<Integer> q = new PriorityQueue<>();
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < m; j++){
    			ts.add(grid[i][j]);
    			for(int d = 2; d < n; d++){
    				if(j - d + 1 >= 0 && j + d - 1 < m && i + 2 * d - 2 < n){
    					int sum = 0;
    					int[] pSum1 = left.get(i + j);
    					sum += pSum1[i + d] - pSum1[i];
    					int[] pSum2 = left.get(i + j + 2 * d - 2);
    					sum += pSum2[i + 2 * d - 1] - pSum2[i + d - 1];
    					int[] pSum3 = right.get(j - i);
    					sum += pSum3[i + d] - pSum3[i];
    					int[] pSum4 = right.get(j - (i + 2 * d - 2));
    					sum += pSum4[i + 2 * d - 1] - pSum4[i + d - 1];
    					// System.out.println(i + "," + j + "," + d + "=" + sum);
    					sum -= grid[i + d - 1][j - d + 1] + grid[i + d - 1][j + d - 1] + grid[i][j] + grid[i + 2 * d - 2][j];
    					ts.add(sum);
    				}
    			}
    		}
    	}
    	int[] ans = new int[Math.min(ts.size(), 3)];
    	for(int i = 0; i < ans.length; i++){
    		ans[i] = ts.last();
    		ts.remove(ans[i]);
    	}
    	return ans;
    }
}
// @lc code=end
