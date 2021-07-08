/*
 * @lc app=leetcode id=1922 lang=java
 *
 * [1922] Count Good Numbers
 *
 * https://leetcode.com/problems/count-good-numbers/description/
 *
 * algorithms
 * Medium (39.02%)
 * Likes:    128
 * Dislikes: 120
 * Total Accepted:    7.9K
 * Total Submissions: 20.3K
 * Testcase Example:  '1'
 *
 * A digit string is good if the digits (0-indexed) at even indices are even
 * and the digits at odd indices are prime (2, 3, 5, or 7).
 * 
 * 
 * For example, "2582" is good because the digits (2 and 8) at even positions
 * are even and the digits (5 and 2) at odd positions are prime. However,
 * "3245" is not good because 3 is at an even index but is not even.
 * 
 * 
 * Given an integer n, return the total number of good digit strings of length
 * n. Since the answer may be large, return it modulo 10^9 + 7.
 * 
 * A digit string is a string consisting of digits 0 through 9 that may contain
 * leading zeros.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 1
 * Output: 5
 * Explanation: The good numbers of length 1 are "0", "2", "4", "6", "8".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 4
 * Output: 400
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 50
 * Output: 564908303
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^15
 * 
 * 
 */

// @lc code=start
class Solution {
    int MOD = (int)1e9 + 7;
    public int countGoodNumbers(long n) {
        long oddCnt = (n + 1) / 2;
        long evenCnt = n / 2;
        return (int)((pow(5, oddCnt) * pow(4, evenCnt)) % MOD);
    }

    long pow(int base, long exp){
        if(exp == 0){
            return 1;
        }
        else if(exp % 2 == 1){
            return (base * pow(base, exp - 1)) % MOD;
        }
        else{
            long p = pow(base, exp / 2);
            return (p * p) % MOD;
        }
    }
}
// @lc code=end
