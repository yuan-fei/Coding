/*
 * @lc app=leetcode id=1512 lang=java
 *
 * [1512] Number of Good Pairs
 *
 * https://leetcode.com/problems/number-of-good-pairs/description/
 *
 * algorithms
 * Easy (90.89%)
 * Likes:    91
 * Dislikes: 8
 * Total Accepted:    13.6K
 * Total Submissions: 15K
 * Testcase Example:  '[1,2,3,1,1,3]'
 *
 * Given an array of integers nums.
 * 
 * A pair (i,j) is called good if nums[i] == nums[j] and i < j.
 * 
 * Return the number of good pairs.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,1,1,3]
 * Output: 4
 * Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,1,1,1]
 * Output: 6
 * Explanation: Each pair in the array are good.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,2,3]
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * 
 */

// @lc code=start
class Solution {
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        int cnt = 0;
        for (int x : nums) {
        	m.put(x, m.getOrDefault(x, 0) + 1);
        	cnt += m.get(x) - 1;
        }
        return cnt;
    }
}
// @lc code=end
