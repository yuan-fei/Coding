/*
 * @lc app=leetcode id=1351 lang=java
 *
 * [1351] Count Negative Numbers in a Sorted Matrix
 *
 * https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/description/
 *
 * algorithms
 * Easy (84.61%)
 * Likes:    23
 * Dislikes: 1
 * Total Accepted:    5.8K
 * Total Submissions: 6.8K
 * Testcase Example:  '[[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]'
 *
 * Given a m * n matrix grid which is sorted in non-increasing order both
 * row-wise and column-wise. 
 * 
 * Return the number of negative numbers in grid.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * Output: 8
 * Explanation: There are 8 negatives number in the matrix.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[3,2],[1,0]]
 * Output: 0
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[1,-1],[-1,-1]]
 * Output: 3
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: grid = [[-1]]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * -100 <= grid[i][j] <= 100
 * 
 */

// @lc code=start
class Solution {
    public int countNegatives(int[][] grid) {
    	int n = grid[0].length;
    	int cnt = 0;
    	int ub = n - 1;
        for(int[] r : grid){
        	int start = bSearch(0, ub, r);
        	// System.out.println(start);
        	if(start >= 0){
        		cnt += n - start;
        		ub = start;
        	}
        }
        return cnt;
    }

    int bSearch(int lb, int ub, int[] row){
    	int low = lb;
    	int high = ub;
    	while(low + 1 < high){
    		int mid = (low + high) / 2;
    		if(row[mid] < 0){
    			high = mid;
    		}
    		else{
    			low = mid;
    		}
    	}
    	if(row[low] < 0){
    		return low;
    	}
    	else if(row[high] < 0){
    		return high;
    	}
    	else{
    		return -1;
    	}
    }
}
// @lc code=end
