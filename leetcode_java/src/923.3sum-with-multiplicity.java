/*
 * @lc app=leetcode id=923 lang=java
 *
 * [923] 3Sum With Multiplicity
 *
 * https://leetcode.com/problems/3sum-with-multiplicity/description/
 *
 * algorithms
 * Medium (33.42%)
 * Total Accepted:    12K
 * Total Submissions: 35.2K
 * Testcase Example:  '[1,1,2,2,3,3,4,4,5,5]\n8'
 *
 * Given an integer array A, and an integer target, return the number of tuples
 * i, j, k  such that i < j < k and A[i] + A[j] + A[k] == target.
 * 
 * As the answer can be very large, return it modulo 10^9 + 7.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
 * Output: 20
 * Explanation: 
 * Enumerating by the values (A[i], A[j], A[k]):
 * (1, 2, 5) occurs 8 times;
 * (1, 3, 4) occurs 8 times;
 * (2, 2, 4) occurs 2 times;
 * (2, 3, 3) occurs 2 times.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: A = [1,1,2,2,2,2], target = 5
 * Output: 12
 * Explanation: 
 * A[i] = 1, A[j] = A[k] = 2 occurs 12 times:
 * We choose one 1 from [1,1] in 2 ways,
 * and two 2s from [2,2,2,2] in 6 ways.
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 3 <= A.length <= 3000
 * 0 <= A[i] <= 100
 * 0 <= target <= 300
 * 
 */
class Solution {
	long mod = 1000000007;
    public int threeSumMulti(int[] A, int target) {
        long[] cnt = new long[101];
        int n = A.length;
        for (int i = 0; i < n; i++) {
        	cnt[A[i]]++;
        }
        long total = 0;
        for(int i = 0; i <= 100; i++){
        	for(int j = i; j<=100; j++){
        		for (int k = j; k<=100; k++) {
        			if(i+j+k==target){
        				long delta = 0;
        				if(i==j&&j==k){
        					if(cnt[i]>=3){
        						delta = (cnt[i] * (cnt[i] -1) * (cnt[i] - 2)/6)%mod;	
        					}
        				}
        				else if(i==j){
        					if(cnt[i]>=2){
        						delta = (cnt[i] * (cnt[i] -1) * cnt[k]/2)%mod;
        					}
        				}
        				else if(j==k){
        					if(cnt[j]>=2){
        						delta = (cnt[i] * cnt[j] * (cnt[j] - 1)/2)%mod;
        					}
        				}
        				else{
        					delta = (cnt[i] * cnt[j] * cnt[k])%mod;	
        				}
        				total=(total+delta)%mod;
        			}
        		}
        	}
        }
        return (int)total;
    }
}
