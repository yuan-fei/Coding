/*
 * @lc app=leetcode id=629 lang=java
 *
 * [629] K Inverse Pairs Array
 *
 * https://leetcode.com/problems/k-inverse-pairs-array/description/
 *
 * algorithms
 * Hard (31.48%)
 * Likes:    363
 * Dislikes: 76
 * Total Accepted:    12.2K
 * Total Submissions: 38.8K
 * Testcase Example:  '3\n0'
 *
 * Given two integers n and k, find how many different arrays consist of
 * numbers from 1 to n such that there are exactly k inverse pairs.
 * 
 * We define an inverse pair as following: For ith and jth element in the
 * array, if i < j and a[i] > a[j] then it's an inverse pair; Otherwise, it's
 * not.
 * 
 * Since the answer may be very large, the answer should be modulo 10^9 + 7.
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, k = 0
 * Output: 1
 * Explanation: 
 * Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0
 * inverse pair.
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 3, k = 1
 * Output: 2
 * Explanation: 
 * The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The integer n is in the range [1, 1000] and k is in the range [0, 1000].
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int kInversePairs(int n, int k) {
        int[] dp = new int[k + 1];
        int[] pSum = new int[k + 2];
        int MOD = 1000000007;
        dp[0] = 1;
        for(int i = 1; i <= k + 1; i++){
        	pSum[i] = pSum[i - 1] + dp[i - 1];
        }
        // System.out.println(Arrays.toString(dp));
        // System.out.println(Arrays.toString(pSum));
        for(int i = 0; i < n; i++){
        	int[] newDp = new int[k + 1];
        	int[] newPSum = new int[k + 2];
        	for(int j = 0; j <= k; j++){
        		newDp[j] = (pSum[j + 1] - pSum[Math.max(0, j - i)] + MOD) % MOD;
        		newPSum[j + 1] = (newPSum[j] + newDp[j]) % MOD;
        	}
        	dp = newDp;
        	pSum = newPSum;
        	// System.out.println(Arrays.toString(dp));
        	// System.out.println(Arrays.toString(pSum));
        }
        return dp[k];
    }
}
// @lc code=end
