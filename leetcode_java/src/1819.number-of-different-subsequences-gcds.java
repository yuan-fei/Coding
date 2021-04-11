/*
 * @lc app=leetcode id=1819 lang=java
 *
 * [1819] Number of Different Subsequences GCDs
 *
 * https://leetcode.com/problems/number-of-different-subsequences-gcds/description/
 *
 * algorithms
 * Hard (13.07%)
 * Likes:    33
 * Dislikes: 17
 * Total Accepted:    562
 * Total Submissions: 5.1K
 * Testcase Example:  '[6,10,3]'
 *
 * You are given an array nums that consists of positive integers.
 * 
 * The GCD of a sequence of numbers is defined as the greatest integer that
 * divides all the numbers in the sequence evenly.
 * 
 * 
 * For example, the GCD of the sequence [4,6,16] is 2.
 * 
 * 
 * A subsequence of an array is a sequence that can be formed by removing some
 * elements (possibly none) of the array.
 * 
 * 
 * For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
 * 
 * 
 * Return the number of different GCDs among all non-empty subsequences of
 * nums.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [6,10,3]
 * Output: 5
 * Explanation: The figure shows all the non-empty subsequences and their GCDs.
 * The different GCDs are 6, 10, 3, 2, and 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [5,15,40,5,6]
 * Output: 7
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 2 * 10^5
 * 
 * 
 */
//O(n^1.5)
// @lc code=start
class Solution {
    public int countDifferentSubsequenceGCDs(int[] nums) {
    	int[] gcds = new int[2 * (int)1e5 + 1];
    	
        for(int n : nums){
        	for(int p = 1; p * p <= n; p++){
        		if(n % p == 0){
        			gcds[p] = gcd(gcds[p], n);
        			gcds[n / p] = gcd(gcds[n / p], n);
        		}
        	}
        }
        int cnt = 0;
        for(int i = 1; i < gcds.length; i++){
        	if(gcds[i] == i){
        		cnt++;
        	}
        }
        return cnt;
    }

    int gcd(int a, int b){
    	if(b == 0){
    		return a;
    	}
    	else{
    		return gcd(b, a % b);
    	}
    }
}
// @lc code=end
