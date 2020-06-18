/*
 * @lc app=leetcode id=1458 lang=java
 *
 * [1458] Max Dot Product of Two Subsequences
 *
 * https://leetcode.com/problems/max-dot-product-of-two-subsequences/description/
 *
 * algorithms
 * Hard (39.49%)
 * Likes:    184
 * Dislikes: 3
 * Total Accepted:    5.8K
 * Total Submissions: 14.7K
 * Testcase Example:  '[2,1,-2,5]\r\n[3,0,-6]\r'
 *
 * Given two arrays nums1 and nums2.
 * 
 * Return the maximum dot product between non-empty subsequences of nums1 and
 * nums2 with the same length.
 * 
 * A subsequence of a array is a new array which is formed from the original
 * array by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, [2,3,5] is a
 * subsequence of [1,2,3,4,5] while [1,5,3] is not).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [2,1,-2,5], nums2 = [3,0,-6]
 * Output: 18
 * Explanation: Take subsequence [2,-2] from nums1 and subsequence [3,-6] from
 * nums2.
 * Their dot product is (2*3 + (-2)*(-6)) = 18.
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [3,-2], nums2 = [2,-6,7]
 * Output: 21
 * Explanation: Take subsequence [3] from nums1 and subsequence [7] from nums2.
 * Their dot product is (3*7) = 21.
 * 
 * Example 3:
 * 
 * 
 * Input: nums1 = [-1,-1], nums2 = [1,1]
 * Output: -1
 * Explanation: Take subsequence [-1] from nums1 and subsequence [1] from
 * nums2.
 * Their dot product is -1.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums1.length, nums2.length <= 500
 * -1000 <= nums1[i], nums2[i] <= 1000
 * 
 */

// @lc code=start
class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
    	int n = nums1.length;
    	int m = nums2.length;
    	int max1 = Integer.MIN_VALUE;
    	int max2 = Integer.MIN_VALUE;
		int min1 = Integer.MAX_VALUE;
    	int min2 = Integer.MAX_VALUE;

    	for(int i = 0; i < n; i++){
    		max1 = Math.max(nums1[i], max1);
    		min1 = Math.min(nums1[i], min1);
    	}
    	for(int i = 0; i < m; i++){
    		max2 = Math.max(nums2[i], max2);	
    		min2 = Math.min(nums2[i], min2);
    	}
    	if((min1 > 0 && max2 < 0) ){
    		return min1 * max2;
    	}
    	if((min2 > 0 && max1 < 0)){
    		return min2 * max1;
    	}
    	int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
        	for(int j = 1; j <= m; j++){
        		dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        		dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + nums1[i - 1] * nums2[j - 1]);
        	}
        }
        return dp[n][m];
    }
}
// @lc code=end