/*
 * @lc app=leetcode id=1838 lang=java
 *
 * [1838] Frequency of the Most Frequent Element
 *
 * https://leetcode.com/problems/frequency-of-the-most-frequent-element/description/
 *
 * algorithms
 * Medium (27.12%)
 * Likes:    171
 * Dislikes: 5
 * Total Accepted:    3.7K
 * Total Submissions: 13.7K
 * Testcase Example:  '[1,2,4]\n5'
 *
 * The frequency of an element is the number of times it occurs in an array.
 * 
 * You are given an integer array nums and an integer k. In one operation, you
 * can choose an index of nums and increment the element at that index by 1.
 * 
 * Return the maximum possible frequency of an element after performing at most
 * k operations.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,4], k = 5
 * Output: 3
 * Explanation: Increment the first element three times and the second element
 * two times to make nums = [4,4,4].
 * 4 has a frequency of 3.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,4,8,13], k = 5
 * Output: 2
 * Explanation: There are multiple optimal solutions:
 * - Increment the first element three times to make nums = [4,4,8,13]. 4 has a
 * frequency of 2.
 * - Increment the second element four times to make nums = [1,8,8,13]. 8 has a
 * frequency of 2.
 * - Increment the third element five times to make nums = [1,4,13,13]. 13 has
 * a frequency of 2.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [3,9,6], k = 2
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxFrequency(int[] nums, int k) {
    	long[] pSum = new long[nums.length + 1];
    	int left = 0;
    	int maxFreq = 0;
    	Arrays.sort(nums);
        for(int i = 1; i <= nums.length; i++){
        	pSum[i] = pSum[i - 1] + nums[i - 1];
        	while(!good(nums, pSum, left, i, k)){
        		left++;
        	}
        	maxFreq = Math.max(maxFreq, i - left);
        }
        return maxFreq;
    }

    private boolean good(int[] nums, long[] pSum, int left, int right, int k){
    	return 1L * (right - left) * nums[right - 1] - (pSum[right] - pSum[left]) <= k;
    }
}
// @lc code=end
