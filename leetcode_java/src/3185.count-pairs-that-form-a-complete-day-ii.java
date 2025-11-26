/*
 * @lc app=leetcode id=3185 lang=java
 *
 * [3185] Count Pairs That Form a Complete Day II
 *
 * https://leetcode.com/problems/count-pairs-that-form-a-complete-day-ii/description/
 *
 * algorithms
 * Medium (43.02%)
 * Likes:    185
 * Dislikes: 12
 * Total Accepted:    46.9K
 * Total Submissions: 108K
 * Testcase Example:  '[12,12,30,24,24]'
 *
 * Given an integer array hours representing times in hours, return an integer
 * denoting the number of pairs i, j where i < j and hours[i] + hours[j] forms
 * a complete day.
 * 
 * A complete day is defined as a time duration that is an exact multiple of 24
 * hours.
 * 
 * For example, 1 day is 24 hours, 2 days is 48 hours, 3 days is 72 hours, and
 * so on.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: hours = [12,12,30,24,24]
 * 
 * Output: 2
 * 
 * Explanation: The pairs of indices that form a complete day are (0, 1) and
 * (3, 4).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: hours = [72,48,24,3]
 * 
 * Output: 3
 * 
 * Explanation: The pairs of indices that form a complete day are (0, 1), (0,
 * 2), and (1, 2).
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= hours.length <= 5 * 10^5
 * 1 <= hours[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public long countCompleteDayPairs(int[] hours) {
        int[] modCount = new int[24];
        long pairCount = 0;
        for (int hour : hours) {
            int mod = hour % 24;
            int complement = (24 - mod) % 24;
            pairCount += modCount[complement];
            modCount[mod]++;
        }
        return pairCount;
    }
}
// @lc code=end
