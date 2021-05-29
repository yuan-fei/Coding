/*
 * @lc app=leetcode id=1862 lang=java
 *
 * [1862] Sum of Floored Pairs
 *
 * https://leetcode.com/problems/sum-of-floored-pairs/description/
 *
 * algorithms
 * Hard (20.89%)
 * Likes:    92
 * Dislikes: 14
 * Total Accepted:    2K
 * Total Submissions: 9.4K
 * Testcase Example:  '[2,5,9]'
 *
 * Given an integer array nums, return the sum of floor(nums[i] / nums[j]) for
 * all pairs of indices 0 <= i, j < nums.length in the array. Since the answer
 * may be too large, return it modulo 10^9 + 7.
 * 
 * The floor() function returns the integer part of the division.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,5,9]
 * Output: 10
 * Explanation:
 * floor(2 / 5) = floor(2 / 9) = floor(5 / 9) = 0
 * floor(2 / 2) = floor(5 / 5) = floor(9 / 9) = 1
 * floor(5 / 2) = 2
 * floor(9 / 2) = 4
 * floor(9 / 5) = 1
 * We calculate the floor of the division for every pair of indices in the
 * array then sum them up.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 49
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int sumOfFlooredPairs(int[] nums) {
    	int MAX = 100000;
    	int mod = (int)1e9 + 7;
        
        int[] freq = new int[2 * MAX + 1];
        for(int x : nums){
        	freq[x]++;
        }
        for(int i = 1; i < freq.length; i++){
        	freq[i] += freq[i - 1];
        }
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
        	s.add(x);
        }
        long sum = 0;
        for(int x : s){
        	int n = freq[x] - freq[x - 1];
        	for(int p = 1; p * x <= MAX; p++){
        		sum += (freq[(p + 1) * x - 1] - freq[p * x - 1]) * 1L * p * n;
        		sum %= mod;
        	}
        }
        return (int)sum;
    }
}
// @lc code=end
