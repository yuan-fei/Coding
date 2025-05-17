/*
 * @lc app=leetcode id=3153 lang=java
 *
 * [3153] Sum of Digit Differences of All Pairs
 *
 * https://leetcode.com/problems/sum-of-digit-differences-of-all-pairs/description/
 *
 * algorithms
 * Medium (42.80%)
 * Likes:    201
 * Dislikes: 20
 * Total Accepted:    26.1K
 * Total Submissions: 61.8K
 * Testcase Example:  '[13,23,12]'
 *
 * You are given an array nums consisting of positive integers where all
 * integers have the same number of digits.
 * 
 * The digit difference between two integers is the count of different digits
 * that are in the same position in the two integers.
 * 
 * Return the sum of the digit differences between all pairs of integers in
 * nums.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [13,23,12]
 * 
 * Output: 4
 * 
 * Explanation:
 * We have the following:
 * - The digit difference between 13 and 23 is 1.
 * - The digit difference between 13 and 12 is 1.
 * - The digit difference between 23 and 12 is 2.
 * So the total sum of digit differences between all pairs of integers is 1 + 1
 * + 2 = 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [10,10,10,10]
 * 
 * Output: 0
 * 
 * Explanation:
 * All the integers in the array are the same. So the total sum of digit
 * differences between all pairs of integers will be 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] < 10^9
 * All integers in nums have the same number of digits.
 * 
 * 
 */

// @lc code=start
class Solution {
    public long sumDigitDifferences(int[] nums) {
        int n = nums.length;
        long ans = 0;
        for (int i = 0; i < 10; i++) {
            long[] cnt = new long[10];
            for (int j = 0; j < n; j++) {
                cnt[nums[j] % 10]++;
                nums[j] /= 10;
            }
            ans += 1L * n * (n - 1) / 2;
            for (long x : cnt) {
                ans -= x * (x - 1) / 2;
            }
        }
        return ans;
    }
}
// @lc code=end
