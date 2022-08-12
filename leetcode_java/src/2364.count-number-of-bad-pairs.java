/*
 * @lc app=leetcode id=2364 lang=java
 *
 * [2364] Count Number of Bad Pairs
 *
 * https://leetcode.com/problems/count-number-of-bad-pairs/description/
 *
 * algorithms
 * Medium (36.25%)
 * Likes:    234
 * Dislikes: 8
 * Total Accepted:    10.3K
 * Total Submissions: 28.4K
 * Testcase Example:  '[4,1,3,3]'
 *
 * You are given a 0-indexed integer array nums. A pair of indices (i, j) is a
 * bad pair if i < j and j - i != nums[j] - nums[i].
 * 
 * Return the total number of bad pairs in nums.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [4,1,3,3]
 * Output: 5
 * Explanation: The pair (0, 1) is a bad pair since 1 - 0 != 1 - 4.
 * The pair (0, 2) is a bad pair since 2 - 0 != 3 - 4, 2 != -1.
 * The pair (0, 3) is a bad pair since 3 - 0 != 3 - 4, 3 != -1.
 * The pair (1, 2) is a bad pair since 2 - 1 != 3 - 1, 1 != 2.
 * The pair (2, 3) is a bad pair since 3 - 2 != 3 - 3, 1 != 0.
 * There are a total of 5 bad pairs, so we return 5.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3,4,5]
 * Output: 0
 * Explanation: There are no bad pairs.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public long countBadPairs(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        int n = nums.length;
        for(int i = 0; i < n; i++){
            m.put(nums[i] - i, m.getOrDefault(nums[i] - i, 0) + 1);
        }
        long ans = 1L * n * (n - 1) / 2;
        for(int v : m.values()){
            ans -= 1L * v * (v - 1) / 2;
        }
        return ans;
    }
}
// @lc code=end
