/*
 * @lc app=leetcode id=3091 lang=java
 *
 * [3091] Apply Operations to Make Sum of Array Greater Than or Equal to k
 *
 * https://leetcode.com/problems/apply-operations-to-make-sum-of-array-greater-than-or-equal-to-k/description/
 *
 * algorithms
 * Medium (44.36%)
 * Likes:    163
 * Dislikes: 16
 * Total Accepted:    29.5K
 * Total Submissions: 66.2K
 * Testcase Example:  '11'
 *
 * You are given a positive integer k. Initially, you have an array nums =
 * [1].
 * 
 * You can perform any of the following operations on the array any number of
 * times (possibly zero):
 * 
 * 
 * Choose any element in the array and increase its value by 1.
 * Duplicate any element in the array and add it to the end of the array.
 * 
 * 
 * Return the minimum number of operations required to make the sum of elements
 * of the final array greater than or equal to k.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: k = 11
 * 
 * Output: 5
 * 
 * Explanation:
 * 
 * We can do the following operations on the array nums = [1]:
 * 
 * 
 * Increase the element by 1 three times. The resulting array is nums =
 * [4].
 * Duplicate the element two times. The resulting array is nums = [4,4,4].
 * 
 * 
 * The sum of the final array is 4 + 4 + 4 = 12 which is greater than or equal
 * to k = 11.
 * The total number of operations performed is 3 + 2 = 5.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: k = 1
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * The sum of the original array is already greater than or equal to 1, so no
 * operations are needed.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minOperations(int k) {
        int value = (int) Math.sqrt(k);
        return Math.max((k + value - 1) / value - 1, 0) + value - 1;
    }
}
// @lc code=end
