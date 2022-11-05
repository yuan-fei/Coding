/*
 * @lc app=leetcode id=2457 lang=java
 *
 * [2457] Minimum Addition to Make Integer Beautiful
 *
 * https://leetcode.com/problems/minimum-addition-to-make-integer-beautiful/description/
 *
 * algorithms
 * Medium (35.39%)
 * Likes:    250
 * Dislikes: 14
 * Total Accepted:    10.2K
 * Total Submissions: 29K
 * Testcase Example:  '16\n6'
 *
 * You are given two positive integers n and target.
 * 
 * An integer is considered beautiful if the sum of its digits is less than or
 * equal to target.
 * 
 * Return the minimum non-negative integer x such that n + x is beautiful. The
 * input will be generated such that it is always possible to make n
 * beautiful.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 16, target = 6
 * Output: 4
 * Explanation: Initially n is 16 and its digit sum is 1 + 6 = 7. After adding
 * 4, n becomes 20 and digit sum becomes 2 + 0 = 2. It can be shown that we can
 * not make n beautiful with adding non-negative integer less than 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 467, target = 6
 * Output: 33
 * Explanation: Initially n is 467 and its digit sum is 4 + 6 + 7 = 17. After
 * adding 33, n becomes 500 and digit sum becomes 5 + 0 + 0 = 5. It can be
 * shown that we can not make n beautiful with adding non-negative integer less
 * than 33.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 1, target = 1
 * Output: 0
 * Explanation: Initially n is 1 and its digit sum is 1, which is already
 * smaller than or equal to target.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^12
 * 1 <= target <= 150
 * The input will be generated such that it is always possible to make n
 * beautiful.
 * 
 * 
 */

// @lc code=start
class Solution {
    public long makeIntegerBeautiful(long n, int target) {
        Stack<Integer> stk = new Stack<>();
        String s = "" + n;
        int sum = 0;
        for(int i = 0; i < s.length(); i++){
            stk.push(s.charAt(i) - '0');
            sum += s.charAt(i) - '0';
        }

        long ans = 0;
        long base = 1;
        int remainder = 0;
        while(sum > target){
            int x = stk.pop();
            x += remainder;
            ans += (10 - x) * base;
            remainder = 1;
            base *= 10;
            sum -= x - 1;
        }
        return ans;
    }
}
// @lc code=end
