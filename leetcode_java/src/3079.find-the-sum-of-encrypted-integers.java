/*
 * @lc app=leetcode id=3079 lang=java
 *
 * [3079] Find the Sum of Encrypted Integers
 *
 * https://leetcode.com/problems/find-the-sum-of-encrypted-integers/description/
 *
 * algorithms
 * Easy (74.26%)
 * Likes:    113
 * Dislikes: 16
 * Total Accepted:    52.2K
 * Total Submissions: 70.1K
 * Testcase Example:  '[1,2,3]'
 *
 * You are given an integer array nums containing positive integers. We define
 * a function encrypt such that encrypt(x) replaces every digit in x with the
 * largest digit in x. For example, encrypt(523) = 555 and encrypt(213) = 333.
 * 
 * Return the sum of encrypted elements.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3]
 * 
 * Output: 6
 * 
 * Explanation: The encrypted elements are [1,2,3]. The sum of encrypted
 * elements is 1 + 2 + 3 == 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [10,21,31]
 * 
 * Output: 66
 * 
 * Explanation: The encrypted elements are [11,22,33]. The sum of encrypted
 * elements is 11 + 22 + 33 == 66.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 50
 * 1 <= nums[i] <= 1000
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int sumOfEncryptedInt(int[] nums) {
        return Arrays.stream(nums).map(this::encrypt).sum();
    }

    int encrypt(int x) {
        int t = x;
        int maxDigit = 0;
        while (t != 0) {
            maxDigit = Math.max(maxDigit, t % 10);
            t /= 10;
        }
        int ret = 0;
        while (x != 0) {
            ret *= 10;
            ret += maxDigit;
            x /= 10;
        }
        return ret;
    }
}
// @lc code=end
