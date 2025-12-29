/*
 * @lc app=leetcode id=3190 lang=java
 *
 * [3190] Find Minimum Operations to Make All Elements Divisible by Three
 *
 * https://leetcode.com/problems/find-minimum-operations-to-make-all-elements-divisible-by-three/description/
 *
 * algorithms
 * Easy (89.12%)
 * Likes:    476
 * Dislikes: 32
 * Total Accepted:    244.8K
 * Total Submissions: 269.1K
 * Testcase Example:  '[1,2,3,4]'
 *
 * You are given an integer array nums. In one operation, you can add or
 * subtract 1 from any element of nums.
 * 
 * Return the minimum number of operations to make all elements of nums
 * divisible by 3.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,4]
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * All array elements can be made divisible by 3 using 3 operations:
 * 
 * 
 * Subtract 1 from 1.
 * Add 1 to 2.
 * Subtract 1 from 4.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,6,9]
 * 
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 50
 * 1 <= nums[i] <= 50
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int minimumOperations(int[] nums) {
        return Arrays.stream(nums).map(i -> i % 3).map(i -> (i == 2) ? 1 : i).sum();
    }
}
// @lc code=end
