/*
 * @lc app=leetcode id=1770 lang=java
 *
 * [1770] Maximum Score from Performing Multiplication Operations
 *
 * https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/description/
 *
 * algorithms
 * Medium (26.63%)
 * Likes:    181
 * Dislikes: 73
 * Total Accepted:    6.1K
 * Total Submissions: 22.2K
 * Testcase Example:  '[1,2,3]\n[3,2,1]'
 *
 * You are given two integer arrays nums and multipliers of size n and m
 * respectively, where n >= m. The arrays are 1-indexed.
 * 
 * You begin with a score of 0. You want to perform exactly m operations. On
 * the i^th operation (1-indexed), you will:
 * 
 * 
 * Choose one integer x from either the start or the end of the array nums.
 * Add multipliers[i] * x to your score.
 * Remove x from the array nums.
 * 
 * 
 * Return the maximum score after performing m operations.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3], multipliers = [3,2,1]
 * Output: 14
 * Explanation: An optimal solution is as follows:
 * - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
 * - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
 * - Choose from the end, [1], adding 1 * 1 = 1 to the score.
 * The total score is 9 + 4 + 1 = 14.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * Output: 102
 * Explanation: An optimal solution is as follows:
 * - Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the
 * score.
 * - Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
 * - Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
 * - Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
 * - Choose from the end, [-2,7], adding 7 * 6 = 42 to the score. 
 * The total score is 50 + 15 - 9 + 4 + 42 = 102.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length
 * m == multipliers.length
 * 1 <= m <= 10^3
 * m <= n <= 10^5 
 * -1000 <= nums[i], multipliers[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
	int[][] cache;
    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        cache = new int[m][m];
        for(int i = 0; i < m; i++){
        	for(int j = 0; j < m; j++){
        		cache[i][j] = Integer.MIN_VALUE;
        	}
        }
        return rec(nums, multipliers, 0, n - 1);
    }

    int rec(int[] nums, int[] multipliers, int s, int e){
    	// System.out.println(s + "~" + e);
    	int n = nums.length;
    	int m = multipliers.length;
    	int ie = n - e - 1;
    	int i = n + s - e - 1;
    	// System.out.println(s + "~" + e + "~" + i);
    	if(i == m){
    		return 0;
    	}
    	if(cache[s][ie] == Integer.MIN_VALUE){
    		cache[s][ie] = Math.max(rec(nums, multipliers, s + 1, e) + multipliers[i] * nums[s], rec(nums, multipliers, s, e - 1) + multipliers[i] * nums[e]);
    	}
    	return cache[s][ie];
    }
}
// @lc code=end
