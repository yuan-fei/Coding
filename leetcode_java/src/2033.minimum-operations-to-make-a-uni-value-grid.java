/*
 * @lc app=leetcode id=2033 lang=java
 *
 * [2033] Minimum Operations to Make a Uni-Value Grid
 *
 * https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/description/
 *
 * algorithms
 * Medium (44.85%)
 * Likes:    125
 * Dislikes: 14
 * Total Accepted:    6.7K
 * Total Submissions: 15.1K
 * Testcase Example:  '[[2,4],[6,8]]\n2'
 *
 * You are given a 2D integer grid of size m x n and an integer x. In one
 * operation, you can add x to or subtract x from any element in the grid.
 * 
 * A uni-value grid is a grid where all the elements of it are equal.
 * 
 * Return the minimum number of operations to make the grid uni-value. If it is
 * not possible, return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[2,4],[6,8]], x = 2
 * Output: 4
 * Explanation: We can make every element equal to 4 by doing the following: 
 * - Add x to 2 once.
 * - Subtract x from 6 once.
 * - Subtract x from 8 twice.
 * A total of 4 operations were used.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,5],[2,3]], x = 1
 * Output: 5
 * Explanation: We can make every element equal to 3.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[1,2],[3,4]], x = 2
 * Output: -1
 * Explanation: It is impossible to make every element equal.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 10^5
 * 1 <= x, grid[i][j] <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minOperations(int[][] grid, int d) {
        int n = grid.length;
        int m = grid[0].length;
        int[] nums = new int[n * m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                nums[i * m + j] = grid[i][j];
            }
        }
        Arrays.sort(nums);
        int median = nums[n * m / 2];
        int total = 0;
        for (int x : nums) {
            if((x - median) % d != 0){
                return -1;
            }
            else {
                total += Math.abs(x - median);
            }
        }
        return total / d;
    }
}
// @lc code=end
