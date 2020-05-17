/*
 * @lc app=leetcode id=446 lang=java
 *
 * [446] Arithmetic Slices II - Subsequence
 *
 * https://leetcode.com/problems/arithmetic-slices-ii-subsequence/description/
 *
 * algorithms
 * Hard (32.32%)
 * Likes:    427
 * Dislikes: 49
 * Total Accepted:    20K
 * Total Submissions: 61.7K
 * Testcase Example:  '[2,4,6,8,10]'
 *
 * A sequence of numbers is called arithmetic if it consists of at least three
 * elements and if the difference between any two consecutive elements is the
 * same.
 * 
 * For example, these are arithmetic sequences:
 * 
 * 
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * 
 * The following sequence is not arithmetic.
 * 
 * 
 * 1, 1, 2, 5, 7
 * 
 * 
 * A zero-indexed array A consisting of N numbers is given. A subsequence slice
 * of that array is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0
 * < P1 < ... < Pk < N.
 * 
 * A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the
 * sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic. In particular,
 * this means that k ≥ 2.
 * 
 * The function should return the number of arithmetic subsequence slices in
 * the array A.
 * 
 * The input contains N integers. Every integer is in the range of -2^31 and
 * 2^31-1 and 0 ≤ N ≤ 1000. The output is guaranteed to be less than 2^31-1.
 * 
 * 
 * Example:
 * 
 * 
 * Input: [2, 4, 6, 8, 10]
 * 
 * Output: 7
 * 
 * Explanation:
 * All arithmetic subsequence slices are:
 * [2,4,6]
 * [4,6,8]
 * [6,8,10]
 * [2,4,6,8]
 * [4,6,8,10]
 * [2,4,6,8,10]
 * [2,6,10]
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numberOfArithmeticSlices(int[] A) {
    	int n = A.length;
        Map<Long, Integer>[] cntByDiff = new Map[n];
        Map<Long, Integer>[] candCntByDiff = new Map[n];
        for(int i = 0; i < n; i++){
        	cntByDiff[i] = new HashMap<>();
        	candCntByDiff[i] = new HashMap<>();
        	for(int j = i - 1; j >= 0; j--){	
        		long diff = 1L * A[i] - A[j];
        		int prevCnt = cntByDiff[j].getOrDefault(diff, 0);
        		int prevCandCount = candCntByDiff[j].getOrDefault(diff, 0);
        		int total = cntByDiff[i].getOrDefault(diff, 0) + prevCandCount + prevCnt;
        		if(total > 0){
        			cntByDiff[i].put(diff, total);	
        		}
				candCntByDiff[i].put(diff, candCntByDiff[i].getOrDefault(diff, 0) + 1);
        	}

        }
        // System.out.println(Arrays.toString(cntByDiff));
        // System.out.println(Arrays.toString(candCntByDiff));
        int ans = 0;
        for (Map<Long, Integer> m : cntByDiff) {
        	for (int v : m.values()) {
        		ans += v;
        	}
        }
        return ans;
    }
}
// @lc code=end
