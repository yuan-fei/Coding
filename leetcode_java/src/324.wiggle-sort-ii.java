/*
 * @lc app=leetcode id=324 lang=java
 *
 * [324] Wiggle Sort II
 *
 * https://leetcode.com/problems/wiggle-sort-ii/description/
 *
 * algorithms
 * Medium (29.04%)
 * Likes:    834
 * Dislikes: 459
 * Total Accepted:    72.8K
 * Total Submissions: 250.8K
 * Testcase Example:  '[1,5,1,1,6,4]'
 *
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] >
 * nums[2] < nums[3]....
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1, 5, 1, 1, 6, 4]
 * Output: One possible answer is [1, 4, 1, 5, 1, 6].
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1, 3, 2, 2, 3, 1]
 * Output: One possible answer is [2, 3, 1, 3, 1, 2].
 * 
 * Note:
 * You may assume all input has valid answer.
 * 
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 */

// @lc code=start
class Solution {
    public void wiggleSort(int[] nums) {
		int n = nums.length;
    	Integer[] ans = new Integer[n];
    	for(int i = 0; i < n; i++){
    		ans[i] = nums[i];
    	}
        Arrays.sort(ans, (a, b) -> Integer.compare(b, a));

        for(int i = 0; i < n / 2; i++){
        	nums[i * 2 + 1] = ans[i];
        }
        for(int i = n / 2; i < n; i++){
        	nums[(i - n / 2) * 2] = ans[i];
        }
    }
}
// @lc code=end
