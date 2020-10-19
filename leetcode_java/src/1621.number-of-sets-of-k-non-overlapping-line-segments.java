/*
 * @lc app=leetcode id=1621 lang=java
 *
 * [1621] Number of Sets of K Non-Overlapping Line Segments
 *
 * https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments/description/
 *
 * algorithms
 * Medium (33.33%)
 * Likes:    62
 * Dislikes: 14
 * Total Accepted:    1.4K
 * Total Submissions: 4.2K
 * Testcase Example:  '4\n2'
 *
 * Given n points on a 1-D plane, where the i^th point (from 0 to n-1) is at x
 * = i, find the number of ways we can draw exactly k non-overlapping line
 * segments such that each segment covers two or more points. The endpoints of
 * each segment must have integral coordinates. The k line segments do not have
 * to cover all n points, and they are allowed to share endpoints.
 * 
 * Return the number of ways we can draw k non-overlapping line segments. Since
 * this number can be huge, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 4, k = 2
 * Output: 5
 * Explanation: 
 * The two line segments are shown in red and blue.
 * The image above shows the 5 different ways {(0,2),(2,3)}, {(0,1),(1,3)},
 * {(0,1),(2,3)}, {(1,2),(2,3)}, {(0,1),(1,2)}.
 * 
 * Example 2:
 * 
 * 
 * Input: n = 3, k = 1
 * Output: 3
 * Explanation: The 3 ways are {(0,1)}, {(0,2)}, {(1,2)}.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 30, k = 7
 * Output: 796297179
 * Explanation: The total number of possible ways to draw 7 line segments is
 * 3796297200. Taking this number modulo 10^9 + 7 gives us 796297179.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: n = 5, k = 3
 * Output: 7
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: n = 3, k = 2
 * Output: 1
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 1000
 * 1 <= k <= n-1
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numberOfSets(int n, int k) {
    	cache = new int[n + 1][k + 1];
    	for (int[] c : cache) {
    		Arrays.fill(c, -1);
    	}
        return rec(n - 1, k);
    }

    int[][] cache;
    int MOD = 1000000007;

    int rec(int n, int k){
    	if(n < k){
    		return 0;
    	}
    	if(k == 0){
    		return 1;
    	}
    	if(n == k){
    		return 1;
    	}
    	if(cache[n][k] == -1){
    		cache[n][k] = 0;
	    	// last bit unset
	    	cache[n][k] = (cache[n][k] + rec(n - 1, k)) % MOD;
	    	// last bit alone
	    	cache[n][k] = (cache[n][k] + rec(n - 1, k - 1)) % MOD;
	    	// last bit set combined with previous
	    	cache[n][k] = (cache[n][k] + rec(n - 1, k)) % MOD;
	    	cache[n][k] = (MOD + cache[n][k] - rec(n - 2, k)) % MOD;
    	}
    	return cache[n][k];
    }
}
// @lc code=end
