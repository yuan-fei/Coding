/*
 * @lc app=leetcode id=1478 lang=java
 *
 * [1478] Allocate Mailboxes
 *
 * https://leetcode.com/problems/allocate-mailboxes/description/
 *
 * algorithms
 * Hard (50.96%)
 * Likes:    91
 * Dislikes: 2
 * Total Accepted:    1.5K
 * Total Submissions: 2.9K
 * Testcase Example:  '[1,4,8,10,20]\n3'
 *
 * Given the array houses and an integer k. where houses[i] is the location of
 * the ith house along a street, your task is to allocate k mailboxes in the
 * street.
 * 
 * Return the minimum total distance between each house and its nearest
 * mailbox.
 * 
 * The answer is guaranteed to fit in a 32-bit signed integer.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: houses = [1,4,8,10,20], k = 3
 * Output: 5
 * Explanation: Allocate mailboxes in position 3, 9 and 20.
 * Minimum total distance from each houses to nearest mailboxes is |3-1| +
 * |4-3| + |9-8| + |10-9| + |20-20| = 5 
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: houses = [2,3,5,12,18], k = 2
 * Output: 9
 * Explanation: Allocate mailboxes in position 3 and 14.
 * Minimum total distance from each houses to nearest mailboxes is |2-3| +
 * |3-3| + |5-3| + |12-14| + |18-14| = 9.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: houses = [7,4,6,1], k = 1
 * Output: 8
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: houses = [3,6,14,10], k = 4
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == houses.length
 * 1 <= n <= 100
 * 1 <= houses[i] <= 10^4
 * 1 <= k <= n
 * Array houses contain unique integers.
 * 
 */

// @lc code=start
class Solution {
    public int minDistance(int[] houses, int k) {
    	int n = houses.length;
    	Arrays.sort(houses);
    	int[][] dist = getTotalDist(houses);

        int[][] dp = new int[n + 1][k + 1];
        int MAX = 2000000;
        for (int i = 0; i <= n; i++) {
    		for(int j = 0; j <= k; j++){
    			dp[i][j] = MAX;
    		}
    	}
    	dp[0][0] = 0;
        for (int i1 = 1; i1 <= n; i1++) {
        	for(int j = 1; j <= k; j++){
        		for(int i2 = i1; i2 >= 1; i2--){
        			dp[i1][j] = Math.min(dp[i1][j], dist[i2 - 1][i1 - 1] + dp[i2 - 1][j - 1]);
        		}
        	}
        }
        System.out.println(Arrays.deepToString(dist));
        System.out.println(Arrays.deepToString(dp));
        return dp[n][k];
    }

    int[][] getTotalDist(int[] houses){
    	int n = houses.length;
    	int[][] ret = new int[n][n];
    	for (int i = 0; i < n; i++) {
    		int mid = i;
    		for(int j = i + 1; j < n; j++){
    			ret[i][j] = ret[i][j - 1] + houses[j] - houses[mid];
    			if((j - i) % 2 == 1){
    				mid++;
    			}
    		}
    	}
    	return ret;
    }
}
// @lc code=end
