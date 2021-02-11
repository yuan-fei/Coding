/*
 * @lc app=leetcode id=1755 lang=java
 *
 * [1755] Closest Subsequence Sum
 *
 * https://leetcode.com/problems/closest-subsequence-sum/description/
 *
 * algorithms
 * Hard (31.87%)
 * Likes:    116
 * Dislikes: 30
 * Total Accepted:    2.7K
 * Total Submissions: 8.6K
 * Testcase Example:  '[5,-7,3,5]\n6'
 *
 * You are given an integer array nums and an integer goal.
 * 
 * You want to choose a subsequence of nums such that the sum of its elements
 * is the closest possible to goal. That is, if the sum of the subsequence's
 * elements is sum, then you want to minimize the absolute difference abs(sum -
 * goal).
 * 
 * Return the minimum possible value of abs(sum - goal).
 * 
 * Note that a subsequence of an array is an array formed by removing some
 * elements (possibly all or none) of the original array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [5,-7,3,5], goal = 6
 * Output: 0
 * Explanation: Choose the whole array as a subsequence, with a sum of 6.
 * This is equal to the goal, so the absolute difference is 0.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [7,-9,15,-2], goal = -5
 * Output: 1
 * Explanation: Choose the subsequence [7,-9,-2], with a sum of -4.
 * The absolute difference is abs(-4 - (-5)) = abs(1) = 1, which is the
 * minimum.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,2,3], goal = -7
 * Output: 7
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 40
 * -10^7 <= nums[i] <= 10^7
 * -10^9 <= goal <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minAbsDifference(int[] nums, int goal) {
        Set<Long> h1 = new HashSet<>();
        TreeSet<Long> h2 = new TreeSet<>();
        h1.add(0L);
        h2.add(0L);
        int n = nums.length;
        getAllCombinations(nums, 0, n / 2, h1);
        getAllCombinations(nums, n / 2, n, h2);
        long min = Long.MAX_VALUE;
        // System.out.println(h1);
        // System.out.println(h2);
        for(long v : h1){
        	long target = goal - v;
        	Long ceiling = h2.ceiling(target);
        	if(ceiling != null){
        		min = Math.min(min, Math.abs(target - ceiling));
        	}
        	Long floor = h2.floor(target);
        	if(floor != null){
        		min = Math.min(min, Math.abs(target - floor));
        	}
        }
        return (int)min;
    }

    void getAllCombinations(int[] arr, int start, int end, Set<Long> ret){
    	int n = end - start;
    	for(int i = 0; i < (1 << n); i++){
    		long sum = 0;
    		for (int j = 0; j < n; j++) {
    			if(((i >> j) & 1) != 0){
    				sum += arr[start + j];
    			}
    		}
    		ret.add(sum);
    	}
    }
}
// @lc code=end
