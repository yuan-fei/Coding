/*
 * [53] Maximum Subarray
 *
 * https://leetcode.com/problems/maximum-subarray/description/
 *
 * algorithms
 * Easy (40.18%)
 * Total Accepted:    284.7K
 * Total Submissions: 708K
 * Testcase Example:  '[-2,1,-3,4,-1,2,1,-5,4]'
 *
 * 
 * Find the contiguous subarray within an array (containing at least one
 * number) which has the largest sum.
 * 
 * 
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * 
 * 
 * click to show more practice.
 * 
 * More practice:
 * 
 * If you have figured out the O(n) solution, try coding another solution using
 * the divide and conquer approach, which is more subtle.
 * 
 */
class Solution {
    public int maxSubArray(int[] nums) {
        return solve1(nums);
    }

	public int solve1(int[] nums) {
        int[] state = new int[nums.length + 1];
        state[0] = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= nums.length; i++) {
        	state[i] = (state[i - 1] < 0)? nums[i - 1]: state[i - 1] + nums[i - 1];
        	max = Math.max(max, state[i]);
        }
        return max;
    } 
}
