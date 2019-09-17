/*
 * @lc app=leetcode id=1191 lang=java
 *
 * [1191] K-Concatenation Maximum Sum
 *
 * https://leetcode.com/problems/k-concatenation-maximum-sum/description/
 *
 * algorithms
 * Medium (18.93%)
 * Total Accepted:    2.3K
 * Total Submissions: 11.6K
 * Testcase Example:  '[1,2]\n3'
 *
 * Given an integer array arr and an integer k, modify the array by repeating
 * it k times.
 * 
 * For example, if arr = [1, 2] and k = 3 then the modified array will be [1,
 * 2, 1, 2, 1, 2].
 * 
 * Return the maximum sub-array sum in the modified array. Note that the length
 * of the sub-array can be 0 and its sum in that case is 0.
 * 
 * As the answer can be very large, return the answer modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [1,2], k = 3
 * Output: 9
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [1,-2,1], k = 5
 * Output: 2
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr = [-1,-2], k = 7
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 10^5
 * 1 <= k <= 10^5
 * -10^4 <= arr[i] <= 10^4
 * 
 */
class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
    	int N = arr.length;
        long[] pSum = new long[N + 1];
        for(int i = 1; i <= N; i++){
        	pSum[i] = pSum[i - 1] + arr[i - 1];
        }
        long min = 0;
        long max = 0;
        long maxSubSum = Long.MIN_VALUE;
        for(int i = 0; i <= N; i++){
        	min = Math.min(min, pSum[i]);
        	max = Math.max(max, pSum[i]);
        	maxSubSum = Math.max(maxSubSum, pSum[i] - min);
        }
        if(k == 1){
        	return (int)(maxSubSum % mod);
        }
        else if(pSum[N] < 0){
        	return (int)(Math.max(maxSubSum, pSum[N] - min + max) % mod);
        }
        else {
        	return (int)(Math.max(maxSubSum, (k-2) * pSum[N] + pSum[N] - min + max) % mod);
        }
    }
    long mod = 1000000007;
}
