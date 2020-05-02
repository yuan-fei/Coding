/*
 * @lc app=leetcode id=1420 lang=java
 *
 * [1420] Build Array Where You Can Find The Maximum Exactly K Comparisons
 *
 * https://leetcode.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons/description/
 *
 * algorithms
 * Hard (67.68%)
 * Likes:    120
 * Dislikes: 5
 * Total Accepted:    4K
 * Total Submissions: 5.9K
 * Testcase Example:  '2\n3\n1'
 *
 * Given three integers n, m and k. Consider the following algorithm to find
 * the maximum element of an array of positive integers:
 * 
 * You should build the array arr which has the following properties:
 * 
 * 
 * arr has exactly n integers.
 * 1 <= arr[i] <= m where (0 <= i < n).
 * After applying the mentioned algorithm to arr, the value search_cost is
 * equal to k.
 * 
 * 
 * Return the number of ways to build the array arr under the mentioned
 * conditions. As the answer may grow large, the answer must be computed modulo
 * 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 2, m = 3, k = 1
 * Output: 6
 * Explanation: The possible arrays are [1, 1], [2, 1], [2, 2], [3, 1], [3, 2]
 * [3, 3]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 5, m = 2, k = 3
 * Output: 0
 * Explanation: There are no possible arrays that satisify the mentioned
 * conditions.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 9, m = 1, k = 1
 * Output: 1
 * Explanation: The only possible array is [1, 1, 1, 1, 1, 1, 1, 1, 1]
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: n = 50, m = 100, k = 25
 * Output: 34549172
 * Explanation: Don't forget to compute the answer modulo 1000000007
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: n = 37, m = 17, k = 7
 * Output: 418930126
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 50
 * 1 <= m <= 100
 * 0 <= k <= n
 * 
 */

// @lc code=start
class Solution {
    public int numOfArrays(int n, int m, int k) {
    	int mod = 1000000007;
        int[][] dp = new int[m + 1][k + 1];
        for(int i = 0; i < dp.length; i++){
        	dp[i][0] = 1;	
        }
        for(int i = 0; i < n; i++){
        	int[][] newDp = new int[m + 1][k + 1];
        	for(int j = 1; j <= m; j++){
        		for(int l = 1; l <= k; l++){
        			newDp[j][l] = newDp[j - 1][l];
        			newDp[j][l] = (newDp[j][l] + dp[j - 1][l - 1]) % mod;
        			long t = (dp[j][l] - dp[j - 1][l] + mod) % mod;
        			t = (t * j) % mod;
        			newDp[j][l] = (newDp[j][l] + (int)t) % mod;
        		}
        	}
        	dp = newDp;
        	// System.out.println(Arrays.deepToString(dp));
        }
        return dp[m][k];
    }
}
// @lc code=end
