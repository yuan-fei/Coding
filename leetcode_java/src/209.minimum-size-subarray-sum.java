/*
 * [209] Minimum Size Subarray Sum
 *
 * https://leetcode.com/problems/minimum-size-subarray-sum/description/
 *
 * algorithms
 * Medium (31.86%)
 * Total Accepted:    109.6K
 * Total Submissions: 343K
 * Testcase Example:  '7\n[2,3,1,2,4,3]'
 *
 * 
 * Given an array of n positive integers and a positive integer s, find the
 * minimal length of a contiguous subarray of which the sum ≥ s. If there isn't
 * one, return 0 instead.
 * 
 * 
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * 
 * 
 * click to show more practice.
 * 
 * More practice:
 * 
 * If you have figured out the O(n) solution, try coding another solution of
 * which the time complexity is O(n log n).
 * 
 * 
 * Credits:Special thanks to @Freezen for adding this problem and creating all
 * test cases.
 */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
    	return solve1(s, nums);
    }

    public int solve1(int s, int[] nums) {
    	if(nums == null || nums.length == 0){
    		return 0;
    	}
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
        	prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int low = 0;
        int high = 1;
        int minLength = Integer.MAX_VALUE;
        while(high <= nums.length && low < high){
        	if(prefixSum[high] - prefixSum[low] < s){
        		high++;
        	}
        	else{
        		minLength = Math.min(minLength, high - low);
        		low++;
        	}
        }
        if(minLength == Integer.MAX_VALUE){
        	minLength = 0;
        }
        return minLength;
    }

}
