/*
 * @lc app=leetcode id=3154 lang=java
 *
 * [3154] Find Number of Ways to Reach the K-th Stair
 *
 * https://leetcode.com/problems/find-number-of-ways-to-reach-the-k-th-stair/description/
 *
 * algorithms
 * Hard (36.07%)
 * Likes:    162
 * Dislikes: 11
 * Total Accepted:    13.3K
 * Total Submissions: 37.1K
 * Testcase Example:  '0'
 *
 * You are given a non-negative integer k. There exists a staircase with an
 * infinite number of stairs, with the lowest stair numbered 0.
 * 
 * Alice has an integer jump, with an initial value of 0. She starts on stair 1
 * and wants to reach stair k using any number of operations. If she is on
 * stair i, in one operation she can:
 * 
 * 
 * Go down to stair i - 1. This operation cannot be used consecutively or on
 * stair 0.
 * Go up to stair i + 2^jump. And then, jump becomes jump + 1.
 * 
 * 
 * Return the total number of ways Alice can reach stair k.
 * 
 * Note that it is possible that Alice reaches the stair k, and performs some
 * operations to reach the stair k again.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: k = 0
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * The 2 possible ways of reaching stair 0 are:
 * 
 * 
 * Alice starts at stair 1.
 * 
 * Using an operation of the first type, she goes down 1 stair to reach stair
 * 0.
 * 
 * 
 * Alice starts at stair 1.
 * 
 * Using an operation of the first type, she goes down 1 stair to reach stair
 * 0.
 * Using an operation of the second type, she goes up 2^0 stairs to reach stair
 * 1.
 * Using an operation of the first type, she goes down 1 stair to reach stair
 * 0.
 * 
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: k = 1
 * 
 * Output: 4
 * 
 * Explanation:
 * 
 * The 4 possible ways of reaching stair 1 are:
 * 
 * 
 * Alice starts at stair 1. Alice is at stair 1.
 * Alice starts at stair 1.
 * 
 * Using an operation of the first type, she goes down 1 stair to reach stair
 * 0.
 * Using an operation of the second type, she goes up 2^0 stairs to reach stair
 * 1.
 * 
 * 
 * Alice starts at stair 1.
 * 
 * Using an operation of the second type, she goes up 2^0 stairs to reach stair
 * 2.
 * Using an operation of the first type, she goes down 1 stair to reach stair
 * 1.
 * 
 * 
 * Alice starts at stair 1.
 * 
 * Using an operation of the first type, she goes down 1 stair to reach stair
 * 0.
 * Using an operation of the second type, she goes up 2^0 stairs to reach stair
 * 1.
 * Using an operation of the first type, she goes down 1 stair to reach stair
 * 0.
 * Using an operation of the second type, she goes up 2^1 stairs to reach stair
 * 2.
 * Using an operation of the first type, she goes down 1 stair to reach stair
 * 1.
 * 
 * 
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= k <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int waysToReachStair(int k) {
        int ans = 0;
        for (int up = 0; (1 << up) - (up + 1) <= k; up++) {
            int down = (1 << up) - k;
            if (down >= 0) {
                ans += C(up + 1, down);
                // System.out.println(ans);
            }
        }
        return ans;
    }

    int C(int n, int m) {
        // System.out.println(Arrays.asList(n, m));
        long ret = 1;
        for (int i = 1; i <= m; i++) {
            ret *= (n - i + 1);
            ret /= i;
        }
        return (int) ret;
    }
}
// @lc code=end
