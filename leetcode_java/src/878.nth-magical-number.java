/*
 * @lc app=leetcode id=878 lang=java
 *
 * [878] Nth Magical Number
 *
 * https://leetcode.com/problems/nth-magical-number/description/
 *
 * algorithms
 * Hard (35.34%)
 * Likes:    1149
 * Dislikes: 147
 * Total Accepted:    33.3K
 * Total Submissions: 94.1K
 * Testcase Example:  '1\n2\n3'
 *
 * A positive integer is magical if it is divisible by either a or b.
 * 
 * Given the three integers n, a, and b, return the n^th magical number. Since
 * the answer may be very large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 1, a = 2, b = 3
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 4, a = 2, b = 3
 * Output: 6
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^9
 * 2 <= a, b <= 4 * 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    int MOD = (int)1e9 + 7;
    public int nthMagicalNumber(int n, int a, int b) {
        long low = 1;
        long high = Long.MAX_VALUE;
        int g = gcd(a, b);
        int m = a / g * b;
        while(low + 1 < high){
            long mid = low + (high - low) / 2;
            long t = count(a, b, m, mid);
            if(t >= n){
                high = mid;
            }
            else{
                low = mid;
            }
        }
        if(count(a, b, m, low) == n){
            return (int)(low % MOD);
        }
        return (int)(high % MOD);
    }

    int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }

    long count(int a, int b, int m, long n){
        return n / a + n/ b - n / m;
    }
}
// @lc code=end
