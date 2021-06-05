/*
 * @lc app=leetcode id=673 lang=java
 *
 * [673] Number of Longest Increasing Subsequence
 *
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/
 *
 * algorithms
 * Medium (38.70%)
 * Likes:    2245
 * Dislikes: 121
 * Total Accepted:    78.3K
 * Total Submissions: 202.3K
 * Testcase Example:  '[1,3,5,4,7]'
 *
 * Given an integer array nums, return the number of longest increasing
 * subsequences.
 * 
 * Notice that the sequence has to be strictly increasing.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and
 * [1, 3, 5, 7].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of longest continuous increasing subsequence is 1,
 * and there are 5 subsequences' length is 1, so output 5.
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 2000
 * -10^6 <= nums[i] <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int[] state = new int[nums.length + 1];
        int[] cnt = new int[nums.length + 1];
		state[0] = 0;

		for (int i = 1; i <= nums.length; i++) {
			state[i] = 1;
			cnt[i] = 1;
			for (int j = 1; j <= i; j++) {
				if (nums[i - 1] > nums[j - 1]) {
					if(state[i] < state[j] + 1){
						state[i] = state[j] + 1;
						cnt[i] = cnt[j];
					}
					else if(state[i] == state[j] + 1){
						cnt[i] += cnt[j];
					}
				}
			}
		}
		int res = 0;
		int maxLength = 0;
		for (int i = 0; i < state.length; i++) {
			if(maxLength < state[i]){
				maxLength = state[i];
				res = cnt[i];
			}
			else if(maxLength == state[i]){
				res += cnt[i];
			}
		}
		return res;
    }
}
// @lc code=end
