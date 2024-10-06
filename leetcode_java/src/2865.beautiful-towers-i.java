/*
 * @lc app=leetcode id=2865 lang=java
 *
 * [2865] Beautiful Towers I
 *
 * https://leetcode.com/problems/beautiful-towers-i/description/
 *
 * algorithms
 * Medium (43.00%)
 * Likes:    294
 * Dislikes: 50
 * Total Accepted:    25.4K
 * Total Submissions: 58.2K
 * Testcase Example:  '[5,3,4,1,1]'
 *
 * You are given an array heights of n integers representing the number of
 * bricks in n consecutive towers. Your task is to remove some bricks to form a
 * mountain-shaped tower arrangement. In this arrangement, the tower heights
 * are non-decreasing, reaching a maximum peak value with one or multiple
 * consecutive towers and then non-increasing.
 * 
 * Return the maximum possible sum of heights of a mountain-shaped tower
 * arrangement.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: heights = [5,3,4,1,1]
 * 
 * Output: 13
 * 
 * Explanation:
 * 
 * We remove some bricks to make heights = [5,3,3,1,1], the peak is at index
 * 0.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: heights = [6,5,3,9,2,7]
 * 
 * Output: 22
 * 
 * Explanation:
 * 
 * We remove some bricks to make heights = [3,3,3,9,2,2], the peak is at index
 * 3.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: heights = [3,2,5,5,2,3]
 * 
 * Output: 18
 * 
 * Explanation:
 * 
 * We remove some bricks to make heights = [2,2,5,5,2,2], the peak is at index
 * 2 or 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n == heights.length <= 10^3
 * 1 <= heights[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public long maximumSumOfHeights(int[] heights) {
        long ans = 0;
        for(int i = 0; i < heights.length; i++){
            ans = Math.max(ans, sumWithPivot(heights, i));
        }
        return ans;
    }

    long sumWithPivot(int[] heights, int p){
        long sum = heights[p];
        int cur = heights[p];
        for(int i = p - 1; i >= 0; i--){
            cur = Math.min(heights[i], cur);
            sum += cur;
        }
        cur = heights[p];
        for(int i = p + 1; i < heights.length; i++){
            cur = Math.min(heights[i], cur);
            sum += cur;
        }
        return sum;
    }
}
// @lc code=end
