/*
 * @lc app=leetcode id=312 lang=java
 *
 * [312] Burst Balloons
 *
 * https://leetcode.com/problems/burst-balloons/description/
 *
 * algorithms
 * Hard (49.57%)
 * Likes:    1851
 * Dislikes: 52
 * Total Accepted:    80.7K
 * Total Submissions: 162.7K
 * Testcase Example:  '[3,1,5,8]'
 *
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a
 * number on it represented by array nums. You are asked to burst all the
 * balloons. If the you burst balloon i you will get nums[left] * nums[i] *
 * nums[right] coins. Here left and right are adjacent indices of i. After the
 * burst, the left and right then becomes adjacent.
 * 
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * 
 * Note:
 * 
 * 
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can
 * not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 
 * 
 * Example:
 * 
 * 
 * Input: [3,1,5,8]
 * Output: 167 
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  -->
 * []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * 
 */

// @lc code=start
class Solution {
    public int maxCoins(int[] nums) {
    	int n = nums.length;
        int[] numsWithBoundary  = new int[n + 2];
        numsWithBoundary[0] = 1;
        numsWithBoundary[n + 1] = 1;
        for(int i = 0; i < n; i++){
        	numsWithBoundary[i + 1] = nums[i];
        }
        cache = new int[n + 2][n + 2];
        for(int i = 0; i < n + 2; i++){
        	for(int j = 0; j < n + 2; j++){
        		cache[i][j] = -1;
        	}
        }

        return maxCoins(numsWithBoundary, 0, n + 1);
    }
    int[][] cache;
    int maxCoins(int[] numsWithBoundary, int left, int right) {
    	if(left + 1 >= right){
    		return 0;
    	}
    	if(cache[left][right] == -1){
	    	int max = 0;
	        for(int i = left + 1; i < right; i++){
	        	max = Math.max(max, maxCoins(numsWithBoundary, left, i) + maxCoins(numsWithBoundary, i, right) + numsWithBoundary[i] * numsWithBoundary[left] * numsWithBoundary[right]);
	        }	
	        cache[left][right] = max;
    	}
    	
        // System.out.println(left + "," + right + "=" + max);
        return cache[left][right];
    }
}
// @lc code=end
