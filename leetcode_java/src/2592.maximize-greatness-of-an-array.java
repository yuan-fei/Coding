/*
 * @lc app=leetcode id=2592 lang=java
 *
 * [2592] Maximize Greatness of an Array
 *
 * https://leetcode.com/problems/maximize-greatness-of-an-array/description/
 *
 * algorithms
 * Medium (52.37%)
 * Likes:    119
 * Dislikes: 2
 * Total Accepted:    9.5K
 * Total Submissions: 18.1K
 * Testcase Example:  '[1,3,5,2,1,3,1]'
 *
 * You are given a 0-indexed integer array nums. You are allowed to permute
 * nums into a new array perm of your choosing.
 * 
 * We define the greatness of nums be the number of indices 0 <= i <
 * nums.length for which perm[i] > nums[i].
 * 
 * Return the maximum possible greatness you can achieve after permuting
 * nums.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,3,5,2,1,3,1]
 * Output: 4
 * Explanation: One of the optimal rearrangements is perm = [2,5,1,3,3,1,1].
 * At indices = 0, 1, 3, and 4, perm[i] > nums[i]. Hence, we return 4.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3,4]
 * Output: 3
 * Explanation: We can prove the optimal perm is [2,3,4,1].
 * At indices = 0, 1, and 2, perm[i] > nums[i]. Hence, we return 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximizeGreatness(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for(int x : nums){
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        int max = 0;
        for(int v : cnt.values()){
            max = Math.max(max, v);
        }
        return nums.length - max;
    }
}
// @lc code=end
