/*
 * @lc app=leetcode id=1524 lang=java
 *
 * [1524] Number of Sub-arrays With Odd Sum
 *
 * https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/description/
 *
 * algorithms
 * Medium (27.73%)
 * Likes:    38
 * Dislikes: 4
 * Total Accepted:    3.3K
 * Total Submissions: 12.1K
 * Testcase Example:  '[1,3,5]\r'
 *
 * Given an array of integers arr. Return the number of sub-arrays with odd
 * sum.
 * 
 * As the answer may grow large, the answer must be computed modulo 10^9 +
 * 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [1,3,5]
 * Output: 4
 * Explanation: All sub-arrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
 * All sub-arrays sum are [1,4,9,3,8,5].
 * Odd sums are [1,9,3,5] so the answer is 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [2,4,6]
 * Output: 0
 * Explanation: All sub-arrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
 * All sub-arrays sum are [2,6,12,4,10,6].
 * All sub-arrays have even sum and the answer is 0.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr = [1,2,3,4,5,6,7]
 * Output: 16
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: arr = [100,100,99,99]
 * Output: 4
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: arr = [7]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 100
 * 
 */

// @lc code=start
class Solution {
    public int numOfSubarrays(int[] arr) {
    	int n = arr.length;
        int odd = 0;
        int even = 0;
        int tt = 0;
        int MOD = 1000000007;
        for(int i = 0; i < n; i++){
        	if(arr[i] % 2 == 0){
        		even = (even + 1) % MOD;
        	}
        	else{
        		int t = even;
        		even = odd;
        		odd = (t + 1) % MOD;
        	}
        	tt = (tt + odd) % MOD;
        }
        return tt;
    }
}
// @lc code=end
