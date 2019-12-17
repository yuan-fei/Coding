/*
 * @lc app=leetcode id=1292 lang=java
 *
 * [1292] Maximum Side Length of a Square with Sum Less than or Equal to Threshold
 *
 * https://leetcode.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/description/
 *
 * algorithms
 * Medium (38.76%)
 * Likes:    63
 * Dislikes: 2
 * Total Accepted:    2.7K
 * Total Submissions: 7K
 * Testcase Example:  '[[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]]\n4'
 *
 * Given a m x n matrix mat and an integer threshold. Return the maximum
 * side-length of a square with a sum less than or equal to threshold or return
 * 0 if there is no such square.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold =
 * 4
 * Output: 2
 * Explanation: The maximum side length of square with sum less than 4 is 2 as
 * shown.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]],
 * threshold = 1
 * Output: 0
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
 * Output: 3
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]],
 * threshold = 40184
 * Output: 2
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= m, n <= 300
 * m == mat.length
 * n == mat[i].length
 * 0 <= mat[i][j] <= 10000
 * 0 <= threshold <= 10^5
 * 
 */

// @lc code=start
class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
    	int n = mat.length;
    	int m = mat[0].length;
        int[][] pSum = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++){
        	for(int j = 1; j <= m; j++){
        		pSum[i][j] = pSum[i - 1][j] + pSum[i][j - 1] - pSum[i - 1][j - 1] + mat[i - 1][j - 1];
        	}
        }

        // System.out.println(Arrays.deepToString(pSum));
        int low = 0;
        int high = Math.min(n, m);
        while(low + 1 < high){
        	int mid = (low + high ) / 2;
        	if(feasible(mat, pSum, threshold, mid)){
        		low = mid;
        	}
        	else{
        		high = mid;
        	}
        }
        if(feasible(mat, pSum, threshold, high)){
        	return high;
        }
        else{
        	return low;
        }
    }

    boolean feasible(int[][] mat, int[][] pSum, int threshold, int target){
		int n = mat.length;
    	int m = mat[0].length;
    	for(int i = 1; i <= n; i++){
        	for(int j = 1; j <= m; j++){
        		if(i + target - 1 <= n && j + target - 1 <= m){
        			int area = pSum[i + target - 1][j + target - 1] - pSum[i + target - 1][j - 1] - pSum[i - 1][j + target - 1] + pSum[i - 1][j - 1];
        			// System.out.println(i + ", "+j+", "+target+" = "+area);
        			if(area <= threshold){
        				return true;
        			}
        		}
        	}
        }
        return false;
    }
}
// @lc code=end
