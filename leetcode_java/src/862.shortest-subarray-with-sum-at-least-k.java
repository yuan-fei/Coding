/*
 * @lc app=leetcode id=862 lang=java
 *
 * [862] Shortest Subarray with Sum at Least K
 *
 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/
 *
 * algorithms
 * Hard (22.46%)
 * Total Accepted:    14.5K
 * Total Submissions: 64.5K
 * Testcase Example:  '[1]\n1'
 *
 * Return the length of the shortest, non-empty, contiguous subarray of A with
 * sum at least K.
 * 
 * If there is no non-empty subarray with sum at least K, return -1.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: A = [1], K = 1
 * Output: 1
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: A = [1,2], K = 4
 * Output: -1
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: A = [2,-1,2], K = 3
 * Output: 3
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= A.length <= 50000
 * -10 ^ 5 <= A[i] <= 10 ^ 5
 * 1 <= K <= 10 ^ 9
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    public int shortestSubarray(int[] A, int K) {
        Deque<Integer> q = new LinkedList<>();
        int n = A.length;
        long[] ps = new long[n+1];
        for (int i = 1; i <=n; i++) {
        	ps[i] = ps[i-1] + A[i-1];
        }
        int min = n+1;
        for (int i = 0; i<=n ; i++) {
        	while(!q.isEmpty() && ps[i] - ps[q.peekFirst()] >=K){
        		min = Math.min(min, i - q.pollFirst());
        	}
        	while(!q.isEmpty() && ps[i]<=ps[q.peekLast()]){
        		q.pollLast();
        	}
        	q.offerLast(i);
        }
        if(min == n+1){
        	min=-1;
        }
        return min;
    }
}
