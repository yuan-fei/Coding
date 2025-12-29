/*
 * @lc app=leetcode id=3192 lang=java
 *
 * [3192] Minimum Operations to Make Binary Array Elements Equal to One II
 *
 * https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-ii/description/
 *
 * algorithms
 * Medium (64.50%)
 * Likes:    150
 * Dislikes: 9
 * Total Accepted:    42.1K
 * Total Submissions: 65.1K
 * Testcase Example:  '[0,1,1,0,1]'
 *
 * You are given a binary array nums.
 * 
 * You can do the following operation on the array any number of times
 * (possibly zero):
 * 
 * 
 * Choose any index i from the array and flip all the elements from index i to
 * the end of the array.
 * 
 * 
 * Flipping an element means changing its value from 0 to 1, and from 1 to 0.
 * 
 * Return the minimum number of operations required to make all elements in
 * nums equal to 1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [0,1,1,0,1]
 * 
 * Output: 4
 * 
 * Explanation:
 * We can do the following operations:
 * 
 * 
 * Choose the index i = 1. The resulting array will be nums = [0,0,0,1,0].
 * Choose the index i = 0. The resulting array will be nums = [1,1,1,0,1].
 * Choose the index i = 4. The resulting array will be nums = [1,1,1,0,0].
 * Choose the index i = 3. The resulting array will be nums = [1,1,1,1,1].
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,0,0,0]
 * 
 * Output: 1
 * 
 * Explanation:
 * We can do the following operation:
 * 
 * 
 * Choose the index i = 1. The resulting array will be nums = [1,1,1,1].
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minOperations(int[] nums) {
        int flip = 0;
        int ans = 0;
        for (int i : nums) {
            int cur = (i + flip) % 2;
            int delta = 1 - cur;
            ans += delta;
            flip += delta;
            flip %= 2;
        }
        return ans;
    }
}
// @lc code=end
