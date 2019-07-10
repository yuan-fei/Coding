/*
 * @lc app=leetcode id=907 lang=java
 *
 * [907] Sum of Subarray Minimums
 *
 * https://leetcode.com/problems/sum-of-subarray-minimums/description/
 *
 * algorithms
 * Medium (27.09%)
 * Total Accepted:    11.6K
 * Total Submissions: 41.8K
 * Testcase Example:  '[3,1,2,4]'
 *
 * Given an array of integers A, find the sum of min(B), where B ranges over
 * every (contiguous) subarray of A.
 * 
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [3,1,2,4]
 * Output: 17
 * Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2],
 * [1,2,4], [3,1,2,4]. 
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= A.length <= 30000
 * 1 <= A[i] <= 30000
 * 
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    public int sumSubarrayMins(int[] A) {
        Stack<Integer> s = new Stack<>();
        int n = A.length;
        int r = 0;
        for(int i = 0; i<n; i++ ){
        	while(!s.isEmpty() && A[s.peek()]>=A[i]){
        		int cur = s.pop();
        		int left = s.isEmpty()?-1:s.peek();
        		int right = i;
        		int v = A[cur];
        		r = add(mul(v, (cur-left)*(right-cur)), r);
        	}
        	s.push(i);
        }
        while(!s.isEmpty()){
        	int cur = s.pop();
    		int left = s.isEmpty()?-1:s.peek();
    		int right = n;
    		int v = A[cur];
        	r = add(mul(v, (cur-left)*(right-cur)), r);
        }
        return r;
    }

    int add(long a, long b){
    	return (int)((a+b)%1000000007);
    }

    int mul(long a, long b){
    	return (int)((a*b)%1000000007);
    }
}
