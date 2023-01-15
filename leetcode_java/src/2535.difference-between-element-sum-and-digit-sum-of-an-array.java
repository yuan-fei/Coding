/*
 * @lc app=leetcode id=2535 lang=java
 *
 * [2535] Difference Between Element Sum and Digit Sum of an Array
 *
 * https://leetcode.com/problems/difference-between-element-sum-and-digit-sum-of-an-array/description/
 *
 * algorithms
 * Easy (90.63%)
 * Likes:    29
 * Dislikes: 0
 * Total Accepted:    14K
 * Total Submissions: 15.5K
 * Testcase Example:  '[1,15,6,3]'
 *
 * You are given a positive integer array nums.
 * 
 * 
 * The element sum is the sum of all the elements in nums.
 * The digit sum is the sum of all the digits (not necessarily distinct) that
 * appear in nums.
 * 
 * 
 * Return the absolute difference between the element sum and digit sum of
 * nums.
 * 
 * Note that the absolute difference between two integers x and y is defined as
 * |x - y|.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,15,6,3]
 * Output: 9
 * Explanation: 
 * The element sum of nums is 1 + 15 + 6 + 3 = 25.
 * The digit sum of nums is 1 + 1 + 5 + 6 + 3 = 16.
 * The absolute difference between the element sum and digit sum is |25 - 16| =
 * 9.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3,4]
 * Output: 0
 * Explanation:
 * The element sum of nums is 1 + 2 + 3 + 4 = 10.
 * The digit sum of nums is 1 + 2 + 3 + 4 = 10.
 * The absolute difference between the element sum and digit sum is |10 - 10| =
 * 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 2000
 * 1 <= nums[i] <= 2000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int differenceOfSum(int[] nums) {
        return Math.abs(Arrays.stream(nums).map(x -> x - getDigitSum(x)).sum());
    }

    int getDigitSum(int x){
        int ret = 0;
        while(x != 0){
            ret += x % 10;
            x /= 10;
        }
        return ret;
    }
}
// @lc code=end
