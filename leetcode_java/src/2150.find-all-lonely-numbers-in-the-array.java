/*
 * @lc app=leetcode id=2150 lang=java
 *
 * [2150] Find All Lonely Numbers in the Array
 *
 * https://leetcode.com/problems/find-all-lonely-numbers-in-the-array/description/
 *
 * algorithms
 * Medium (60.41%)
 * Likes:    194
 * Dislikes: 28
 * Total Accepted:    21.7K
 * Total Submissions: 35.9K
 * Testcase Example:  '[10,6,5,8]'
 *
 * You are given an integer array nums. A number x is lonely when it appears
 * only once, and no adjacent numbers (i.e. x + 1 and x - 1) appear in the
 * array.
 * 
 * Return all lonely numbers in nums. You may return the answer in any
 * order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [10,6,5,8]
 * Output: [10,8]
 * Explanation: 
 * - 10 is a lonely number since it appears exactly once and 9 and 11 does not
 * appear in nums.
 * - 8 is a lonely number since it appears exactly once and 7 and 9 does not
 * appear in nums.
 * - 5 is not a lonely number since 6 appears in nums and vice versa.
 * Hence, the lonely numbers in nums are [10, 8].
 * Note that [8, 10] may also be returned.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,3,5,3]
 * Output: [1,5]
 * Explanation: 
 * - 1 is a lonely number since it appears exactly once and 0 and 2 does not
 * appear in nums.
 * - 5 is a lonely number since it appears exactly once and 4 and 6 does not
 * appear in nums.
 * - 3 is not a lonely number since it appears twice.
 * Hence, the lonely numbers in nums are [1, 5].
 * Note that [5, 1] may also be returned.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> findLonely(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int x : nums) {
            m.put(x, m.getOrDefault(x, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        for (int x : nums) {
            if(m.get(x) == 1 && !m.containsKey(x - 1) && !m.containsKey(x + 1)){
                ans.add(x);
            }
        }
        return ans;
    }
}
// @lc code=end
