/*
 * @lc app=leetcode id=3097 lang=java
 *
 * [3097] Shortest Subarray With OR at Least K II
 *
 * https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii/description/
 *
 * algorithms
 * Medium (50.51%)
 * Likes:    718
 * Dislikes: 69
 * Total Accepted:    95.2K
 * Total Submissions: 188.5K
 * Testcase Example:  '[1,2,3]\n2'
 *
 * You are given an array nums of non-negative integers and an integer k.
 * 
 * An array is called special if the bitwise OR of all of its elements is at
 * least k.
 * 
 * Return the length of the shortest special non-empty subarray of nums, or
 * return -1 if no special subarray exists.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3], k = 2
 * 
 * Output: 1
 * 
 * Explanation:
 * 
 * The subarray [3] has OR value of 3. Hence, we return 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,1,8], k = 10
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * The subarray [2,1,8] has OR value of 11. Hence, we return 3.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,2], k = 0
 * 
 * Output: 1
 * 
 * Explanation:
 * 
 * The subarray [1] has OR value of 1. Hence, we return 1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 2 * 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= k <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    int MAXBIT = 30;

    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int[][] occurances = new int[MAXBIT + 1][n + 1];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= k) {
                return 1;
            }
            for (int b = 0; b <= MAXBIT; b++) {
                occurances[b][i + 1] = occurances[b][i];
                if (((nums[i] >> b) & 1) == 1) {
                    occurances[b][i + 1]++;
                }
            }
        }
        int MAX_VALUE = nums.length + 1;
        int ans = MAX_VALUE;
        // System.out.println(Arrays.deepToString(occurances));
        for (int i = 0; i < nums.length; i++) {
            int end = binarySearch(occurances, i + 1, k);
            System.out.println(Arrays.asList(i + 1, end));
            if (end != -1) {
                ans = Math.min(ans, end - i);
            }
        }
        return (ans >= MAX_VALUE) ? -1 : ans;
    }

    int binarySearch(int[][] occurances, int pos, int k) {
        int low = pos;
        int high = occurances[0].length - 1;
        while (low + 1 < high) {
            int mid = (low + high) / 2;
            if (getOrResult(occurances, pos, mid) >= k) {
                high = mid;
            } else {
                low = mid;
            }
        }
        int res = getOrResult(occurances, pos, low);
        if (res >= k) {
            return low;
        }
        res = getOrResult(occurances, pos, high);
        if (res >= k) {
            return high;
        }
        return -1;
    }

    int getOrResult(int[][] occurances, int start, int end) {
        int ret = 0;
        for (int b = 0; b <= MAXBIT; b++) {
            int cnt = occurances[b][end] - occurances[b][start - 1];
            int curBit = (cnt > 0) ? 1 : 0;
            ret |= (curBit << b);
            // System.out.println(Arrays.asList(start, end, b, cnt));
        }
        // System.out.println(Arrays.asList(start, end, ret));
        return ret;
    }
}
// @lc code=end
