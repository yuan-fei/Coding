/*
 * @lc app=leetcode id=581 lang=java
 *
 * [581] Shortest Unsorted Continuous Subarray
 *
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/description/
 *
 * algorithms
 * Easy (31.20%)
 * Likes:    3101
 * Dislikes: 163
 * Total Accepted:    139.3K
 * Total Submissions: 446.3K
 * Testcase Example:  '[2,6,4,8,10,9,15]'
 *
 * Given an integer array, you need to find one continuous subarray that if you
 * only sort this subarray in ascending order, then the whole array will be
 * sorted in ascending order, too.  
 * 
 * You need to find the shortest such subarray and output its length.
 * 
 * Example 1:
 * 
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make
 * the whole array sorted in ascending order.
 * 
 * 
 * 
 * Note:
 * 
 * Then length of the input array is in range [1, 10,000].
 * The input array may contain duplicates, so ascending order here means . 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findUnsortedSubarray(int[] nums) {
    	int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        int i = 0;
        while(i < nums.length && nums[i] == sorted[i]){
        	i++;
        }
        int j = nums.length - 1;
        while(j >= 0 && nums[j] == sorted[j]){
        	j--;
        }
        return Math.max(0, j - i + 1);
    }
}
// @lc code=end
