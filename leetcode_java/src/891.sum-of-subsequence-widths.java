/*
 * @lc app=leetcode id=891 lang=java
 *
 * [891] Sum of Subsequence Widths
 *
 * https://leetcode.com/problems/sum-of-subsequence-widths/description/
 *
 * algorithms
 * Hard (36.70%)
 * Likes:    650
 * Dislikes: 164
 * Total Accepted:    18.6K
 * Total Submissions: 50.6K
 * Testcase Example:  '[2,1,3]'
 *
 * The width of a sequence is the difference between the maximum and minimum
 * elements in the sequence.
 * 
 * Given an array of integers nums, return the sum of the widths of all the
 * non-empty subsequences of nums. Since the answer may be very large, return
 * it modulo 10^9 + 7.
 * 
 * A subsequence is a sequence that can be derived from an array by deleting
 * some or no elements without changing the order of the remaining elements.
 * For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,1,3]
 * Output: 6
 * Explanation: The subsequences are [1], [2], [3], [2,1], [2,3], [1,3],
 * [2,1,3].
 * The corresponding widths are 0, 0, 0, 1, 1, 2, 2.
 * The sum of these widths is 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2]
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {

    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        long MOD = (long)1e9 + 7;
        int n = nums.length;
        long totalWidth = 0;
        long multiplier = 1;
        for(int i = 0; i < n; i++){
            long width = (nums[i] * multiplier - nums[n - i - 1] * multiplier) % MOD;
            totalWidth += width;
            totalWidth %= MOD;
            multiplier = (multiplier << 1) % MOD;
        }
        return (int)totalWidth;
    }

    public int sumSubseqWidths1(int[] nums) {
        Arrays.sort(nums);
        long MOD = (long)1e9 + 7;
        int n = nums.length;
        long cnt = 1;
        long totalHigh = nums[0];
        long totalWidth = 0;
        for(int i = 1; i < n; i++){
            long width = nums[i] * cnt + totalWidth - totalHigh + MOD;
            width %= MOD;
            totalHigh += nums[i] * cnt + nums[i];
            totalHigh %= MOD;
            // System.out.println(i + ", " +width + "," + totalHigh);
            totalWidth += width;
            totalWidth %= MOD;
            cnt <<= 1;
            cnt += 1;
            cnt %= MOD;
        }
        return (int)totalWidth;
    }
}
// @lc code=end
