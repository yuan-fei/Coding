/*
 * @lc app=leetcode id=1695 lang=java
 *
 * [1695] Maximum Erasure Value
 *
 * https://leetcode.com/problems/maximum-erasure-value/description/
 *
 * algorithms
 * Medium (48.57%)
 * Likes:    112
 * Dislikes: 3
 * Total Accepted:    6.2K
 * Total Submissions: 12.8K
 * Testcase Example:  '[4,2,4,5,6]'
 *
 * You are given an array of positive integers nums and want to erase a
 * subarray containing unique elements. The score you get by erasing the
 * subarray is equal to the sum of its elements.
 * 
 * Return the maximum score you can get by erasing exactly one subarray.
 * 
 * An array b is called to be a subarray of a if it forms a contiguous
 * subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some
 * (l,r).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [4,2,4,5,6]
 * Output: 17
 * Explanation: The optimal subarray here is [2,4,5,6].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [5,2,1,2,5,2,1,2,5]
 * Output: 8
 * Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximumUniqueSubarray(int[] nums) {
    	int n = nums.length;
    	int left = 0;
    	int ans = 0;
    	Set<Integer> s = new HashSet<>();
    	int sum = 0;
        for(int i = 0; i < n; i++){
        	sum += nums[i];
        	while(s.contains(nums[i])){
        		s.remove(nums[left]);
        		sum -= nums[left];
        		left++;
        	}
        	ans = Math.max(ans, sum);
        	s.add(nums[i]);
        }
        return ans;
    }
}
// @lc code=end
