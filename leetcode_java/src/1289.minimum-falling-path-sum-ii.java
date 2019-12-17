/*
 * @lc app=leetcode id=1289 lang=java
 *
 * [1289] Minimum Falling Path Sum II
 *
 * https://leetcode.com/problems/minimum-falling-path-sum-ii/description/
 *
 * algorithms
 * Hard (47.79%)
 * Likes:    15
 * Dislikes: 0
 * Total Accepted:    1.1K
 * Total Submissions: 2.3K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * Given a square grid of integers arr, a falling path with non-zero shifts is
 * a choice of exactly one element from each row of arr, such that no two
 * elements chosen in adjacent rows are in the same column.
 * 
 * Return the minimum sum of a falling path with non-zero shifts.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: 13
 * Explanation: 
 * The possible falling paths are:
 * [1,5,9], [1,5,7], [1,6,7], [1,6,8],
 * [2,4,8], [2,4,9], [2,6,7], [2,6,8],
 * [3,4,8], [3,4,9], [3,5,7], [3,5,9]
 * The falling path with the smallest sum is [1,5,7], so the answer is 13.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length == arr[i].length <= 200
 * -99 <= arr[i][j] <= 99
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minFallingPathSum(int[][] arr) {
        int n = arr.length;
        int min1 = 0;
        int min2 = 0;
        int min1Col = -1;
        int min2Col = -1;
        for(int i = 0; i < n; i++){
            int min1Tmp = Integer.MAX_VALUE;
            int min2Tmp = Integer.MAX_VALUE;
            int min1ColTmp = -1;
            int min2ColTmp = -1;
            for(int j = 0; j < n; j++){
                int v = arr[i][j];
                if(j == min1Col){
                    v += min2;
                }
                else{
                    v += min1;
                }
                if(v < min1Tmp){
                    min2Tmp = min1Tmp;
                    min2ColTmp = min1ColTmp;
                    min1Tmp = v;
                    min1ColTmp = j;
                }
                else if(v < min2Tmp){
                    min2Tmp = v;
                    min2ColTmp = j;
                }
            }
            min1 = min1Tmp;
            min2 = min2Tmp;
            min1Col = min1ColTmp;
            min2Col = min2ColTmp;
            //System.out.println(min1 + ", "+ min2);
        }
        return min1;
    }
}
// @lc code=end
