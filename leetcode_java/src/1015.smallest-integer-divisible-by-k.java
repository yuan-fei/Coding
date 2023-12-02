/*
 * @lc app=leetcode id=1015 lang=java
 *
 * [1015] Smallest Integer Divisible by K
 *
 * https://leetcode.com/problems/smallest-integer-divisible-by-k/description/
 *
 * algorithms
 * Medium (46.80%)
 * Likes:    1117
 * Dislikes: 847
 * Total Accepted:    61.1K
 * Total Submissions: 130.8K
 * Testcase Example:  '1'
 *
 * Given a positive integer k, you need to find the length of the smallest
 * positive integer n such that n is divisible by k, and n only contains the
 * digit 1.
 * 
 * Return the length of n. If there is no such n, return -1.
 * 
 * Note: n may not fit in a 64-bit signed integer.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: k = 1
 * Output: 1
 * Explanation: The smallest answer is n = 1, which has length 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: k = 2
 * Output: -1
 * Explanation: There is no such positive integer n divisible by 2.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: k = 3
 * Output: 3
 * Explanation: The smallest answer is n = 111, which has length 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int smallestRepunitDivByK(int k) {
        boolean[] seen = new boolean[k];
        int n = 1;
        for(int i = 1; i <= k; i++){
            n %= k;
            if(n == 0){
                return i;
            }
            seen[n] = true;
            n *= 10;
            n++;
        }
        return -1;
    }
}
// @lc code=end
