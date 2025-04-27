/*
 * @lc app=leetcode id=3133 lang=java
 *
 * [3133] Minimum Array End
 *
 * https://leetcode.com/problems/minimum-array-end/description/
 *
 * algorithms
 * Medium (55.99%)
 * Likes:    784
 * Dislikes: 92
 * Total Accepted:    104.6K
 * Total Submissions: 188.2K
 * Testcase Example:  '3\n4'
 *
 * You are given two integers n and x. You have to construct an array of
 * positive integers nums of size n where for every 0 <= i < n - 1, nums[i + 1]
 * is greater than nums[i], and the result of the bitwise AND operation between
 * all elements of nums is x.
 * 
 * Return the minimum possible value of nums[n - 1].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, x = 4
 * 
 * Output: 6
 * 
 * Explanation:
 * 
 * nums can be [4,5,6] and its last element is 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2, x = 7
 * 
 * Output: 15
 * 
 * Explanation:
 * 
 * nums can be [7,15] and its last element is 15.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n, x <= 10^8
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public long minEnd(int n, int x) {
        n--;
        long mask = (Long.highestOneBit(x) << 1) - 1;
        int zeroBitCount = Long.bitCount(mask - x);
        long cnt = 1L << zeroBitCount;
        long sections = n / cnt;
        long remainder = n % cnt;
        long high = (Long.highestOneBit(x) << 1) * sections;
        int j = 0;
        for (int i = 0; i < 31; i++) {
            if (((x >> i) & 1) == 0) {
                x |= ((remainder >> j) & 1) << i;
                j++;
            }
        }
        // System.out.println(Arrays.asList(high, x));
        return high + x;
    }
}
// @lc code=end
