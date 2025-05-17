/*
 * @lc app=leetcode id=3151 lang=java
 *
 * [3151] Special Array I
 *
 * https://leetcode.com/problems/special-array-i/description/
 *
 * algorithms
 * Easy (82.19%)
 * Likes:    540
 * Dislikes: 32
 * Total Accepted:    236.3K
 * Total Submissions: 288.7K
 * Testcase Example:  '[1]'
 *
 * An array is considered special if the parity of every pair of adjacent
 * elements is different. In other words, one element in each pair must be
 * even, and the other must be odd.
 * 
 * You are given an array of integers nums. Return true if nums is a special
 * array, otherwise, return false.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1]
 * 
 * Output: true
 * 
 * Explanation:
 * 
 * There is only one element. So the answer is true.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,1,4]
 * 
 * Output: true
 * 
 * Explanation:
 * 
 * There is only two pairs: (2,1) and (1,4), and both of them contain numbers
 * with different parity. So the answer is true.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [4,3,1,6]
 * 
 * Output: false
 * 
 * Explanation:
 * 
 * nums[1] and nums[2] are both odd. So the answer is false.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * 
 * 
 */

// @lc code=start

import java.util.stream.IntStream;

class Solution {
    public boolean isArraySpecial(int[] nums) {
        return IntStream.range(1, nums.length).map(i -> Math.abs(nums[i] - nums[i - 1])).allMatch(i -> i % 2 == 1);
    }
}
// @lc code=end
