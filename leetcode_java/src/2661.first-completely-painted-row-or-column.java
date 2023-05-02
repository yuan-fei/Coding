/*
 * @lc app=leetcode id=2661 lang=java
 *
 * [2661] First Completely Painted Row or Column
 *
 * https://leetcode.com/problems/first-completely-painted-row-or-column/description/
 *
 * algorithms
 * Medium (46.44%)
 * Likes:    104
 * Dislikes: 2
 * Total Accepted:    9K
 * Total Submissions: 19.1K
 * Testcase Example:  '[1,3,4,2]\n[[1,4],[2,3]]'
 *
 * You are given a 0-indexed integer array arr, and an m x n integer matrix
 * mat. arr and mat both contain all the integers in the range [1, m * n].
 * 
 * Go through each index i in arr starting from index 0 and paint the cell in
 * mat containing the integer arr[i].
 * 
 * Return the smallest index i at which either a row or a column will be
 * completely painted in mat.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [1,3,4,2], mat = [[1,4],[2,3]]
 * Output: 2
 * Explanation: The moves are shown in order, and both the first row and second
 * column of the matrix become fully painted at arr[2].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [2,8,7,4,1,3,5,6,9], mat = [[3,2,5],[1,4,6],[8,7,9]]
 * Output: 3
 * Explanation: The second column becomes fully painted at arr[3].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == mat.length
 * n = mat[i].length
 * arr.length == m * n
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 10^5
 * 1 <= arr[i], mat[r][c] <= m * n
 * All the integers of arr are unique.
 * All the integers of mat are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int[] pos = new int[100005];
        for(int i = 0; i < arr.length; i++){
            pos[arr[i]] = i;
        }
        int n = mat.length;
        int m = mat[0].length;
        int ans = arr.length;
        for(int i = 0; i < n; i++){
            int max = 0;
            for(int j = 0; j < m; j++){
                max = Math.max(pos[mat[i][j]], max);
            }
            ans = Math.min(ans, max);
        }
        for(int i = 0; i < m; i++){
            int max = 0;
            for(int j = 0; j < n; j++){
                max = Math.max(pos[mat[j][i]], max);
            }
            ans = Math.min(ans, max);
        }
        return ans;
    }
}
// @lc code=end
