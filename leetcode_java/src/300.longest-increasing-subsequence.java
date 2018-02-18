/*
 * [300] Longest Increasing Subsequence
 *
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 *
 * algorithms
 * Medium (38.86%)
 * Total Accepted:    113.5K
 * Total Submissions: 292K
 * Testcase Example:  '[10,9,2,5,3,7,101,18]'
 *
 * 
 * Given an unsorted array of integers, find the length of longest increasing
 * subsequence.
 * 
 * 
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length
 * is 4. Note that there may be more than one LIS combination, it is only
 * necessary for you to return the length.
 * 
 * 
 * Your algorithm should run in O(n2) complexity.
 * 
 * 
 * Follow up: Could you improve it to O(n log n) time complexity? 
 * 
 * Credits:Special thanks to @pbrother for adding this problem and creating all
 * test cases.
 */
class Solution {
    public int lengthOfLIS(int[] nums) {
    	if(nums == null || nums.length == 0){
    		return 0;
    	}
    	int[] state = new int[nums.length];
    	state[0] = 1;

        for (int i = 1; i < nums.length; i++) {
        	state[i] = 1;
        	for (int j = 0; j < i; j++) {
        		if(nums[i] > nums[j]){
        			state[i] = Math.max(state[i], state[j] + 1);
        		}
        	}
        }
        
        int maxLength = 0;
        for (int i = 0; i < state.length; i++) {
        	maxLength = Math.max(maxLength, state[i]);
        }
        return maxLength;
    }
}
