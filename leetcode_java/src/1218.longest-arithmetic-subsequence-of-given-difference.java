/*
 * @lc app=leetcode id=1218 lang=java
 *
 * [1218] Longest Arithmetic Subsequence of Given Difference
 *
 * https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/description/
 *
 * algorithms
 * Medium (31.92%)
 * Total Accepted:    3.4K
 * Total Submissions: 10.6K
 * Testcase Example:  '[1,2,3,4]\n1'
 *
 * Given an integer array arr and an integer difference, return the length of
 * the longest subsequence in arr which is an arithmetic sequence such that the
 * difference between adjacent elements in the subsequence equals
 * difference.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [1,2,3,4], difference = 1
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [1,2,3,4].
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [1,3,5,7], difference = 1
 * Output: 1
 * Explanation: The longest arithmetic subsequence is any single element.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [7,5,3,1].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i], difference <= 10^4
 * 
 * 
 */
class Solution {
    public int longestSubsequence(int[] arr, int difference) {
    	int N = arr.length;
    	int M = 20001;
    	int[] len = new int[M];
    	int max = 1;
        for(int i = 0; i < N; i++){
        	int idx = arr[i] + 10000;
        	int prvIdx = idx - difference;
        	if(prvIdx >= 0 && prvIdx < M){
        		len[idx] = len[prvIdx] + 1;
        	}
        	else{
        		len[idx]  =1;
        	}
        	max = Math.max(max, len[idx]);
        }
        return max;
    }
}
