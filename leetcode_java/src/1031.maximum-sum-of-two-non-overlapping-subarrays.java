/*
 * @lc app=leetcode id=1031 lang=java
 *
 * [1031] Maximum Sum of Two Non-Overlapping Subarrays
 *
 * https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/description/
 *
 * algorithms
 * Medium (54.96%)
 * Total Accepted:    7.1K
 * Total Submissions: 12.9K
 * Testcase Example:  '[0,6,5,2,2,5,1,9,4]\n1\n2'
 *
 * Given an array A of non-negative integers, return the maximum sum of
 * elements in two non-overlapping (contiguous) subarrays, which have lengths L
 * and M.  (For clarification, the L-length subarray could occur before or
 * after the M-length subarray.)
 * 
 * Formally, return the largest V for which V = (A[i] + A[i+1] + ... +
 * A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:
 * 
 * 
 * 0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
 * 0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 * 
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
 * Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
 * Output: 20
 * Explanation: One choice of subarrays is [9] with length 1, and [6,5] with
 * length 2.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
 * Output: 29
 * Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9]
 * with length 2.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
 * Output: 31
 * Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8]
 * with length 3.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * L >= 1
 * M >= 1
 * L + M <= A.length <= 1000
 * 0 <= A[i] <= 1000
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int n = A.length;
        int[] prefix = new int[n+1];
        int[] maxLStart = new int[n+2];
        int[] maxLEnd = new int[n+2];
        int[] maxMStart = new int[n+2];
        int[] maxMEnd = new int[n+2];
        int cur = 0;
        for(int i = 1; i <= n; i++){
            prefix[i] = prefix[i-1] + A[i-1];
        }
        
        for(int i = 1; i+L-1 <= n; i++){
            maxLEnd[i+L-1] = Math.max(maxLEnd[i+L-2], prefix[i+L-1] - prefix[i-1]);
        }
        
        for(int i = n; i-L+1 >= 1; i--){
            maxLStart[i-L+1] = Math.max(maxLStart[i-L+2], prefix[i] - prefix[i-L]);
        }
        
        for(int i = 1; i+M-1 <= n; i++){
            maxMEnd[i+M-1] = Math.max(maxMEnd[i+M-2], prefix[i+M-1] - prefix[i-1]);
        }
        
        for(int i = n; i-M+1 >= 1; i--){
            maxMStart[i-M+1] = Math.max(maxMStart[i-M+2], prefix[i] - prefix[i-M]);
        }
        // System.out.println(Arrays.toString(maxLEnd));
        // System.out.println(Arrays.toString(maxLStart));
        // System.out.println(Arrays.toString(maxMEnd));
        // System.out.println(Arrays.toString(maxMStart));
        int max = 0;
        for(int i = 1; i <= n; i++){
            if(i-L+1>=1&&i+M<=n){
                max = Math.max(max, maxLEnd[i]+maxMStart[i+1]);
            }
            System.out.println(i+" "+max);
            if(i-M+1>=1&&i+L<=n){
                max = Math.max(max, maxMEnd[i]+maxLStart[i+1]);
            }
            // System.out.println(i+" "+max);
        }
        return max;
    }
    
    
}
