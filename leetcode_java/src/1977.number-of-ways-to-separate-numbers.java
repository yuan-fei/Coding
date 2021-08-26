/*
 * @lc app=leetcode id=1977 lang=java
 *
 * [1977] Number of Ways to Separate Numbers
 *
 * https://leetcode.com/problems/number-of-ways-to-separate-numbers/description/
 *
 * algorithms
 * Hard (19.84%)
 * Likes:    75
 * Dislikes: 17
 * Total Accepted:    864
 * Total Submissions: 4.4K
 * Testcase Example:  '"327"'
 *
 * You wrote down many positive integers in a string called num. However, you
 * realized that you forgot to add commas to seperate the different numbers.
 * You remember that the list of integers was non-decreasing and that no
 * integer had leading zeros.
 * 
 * Return the number of possible lists of integers that you could have written
 * down to get the string num. Since the answer may be large, return it modulo
 * 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = "327"
 * Output: 2
 * Explanation: You could have written down the numbers:
 * 3, 27
 * 327
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = "094"
 * Output: 0
 * Explanation: No numbers can have leading zeros and all numbers must be
 * positive.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: num = "0"
 * Output: 0
 * Explanation: No numbers can have leading zeros and all numbers must be
 * positive.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: num = "9999999999999"
 * Output: 101
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= num.length <= 3500
 * num consists of digits '0' through '9'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numberOfCombinations(String num) {
        return numberOfCombinations(num.toCharArray());
    }

    int mod = (int)1e9+7;
    int maxn = 3501;
    int[][] dp;
    int[] sum;
    int[][] prefix;
    int numberOfCombinations(char[] num) {
        int n = num.length;
        maxn = n + 1;
        dp = new int[maxn][maxn];
        sum = new int[maxn];
        prefix = new int[maxn][maxn];
        if (num[0] == '0') return 0;
        
        for (int i = n-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (num[i] == num[j]) {
                    prefix[i][j] = prefix[i+1][j+1] + 1;
                }
            }
        }
        
        for (int j = 1; j <= n; j++) dp[1][j] = 1;
        sum[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            if (num[i-1] == '0') {  // handling the leading zero
                for (int j = i; j <= n; j++) dp[i][j] = 0;
                for (int k = 1; k <= i; k++) sum[k] = (sum[k-1] + dp[k][i]) % mod;
                continue;
            }
            for (int j = i; j <= n; j++) {
                int k = 2*i - 1 - j;
                if (k <= 0) {
                    dp[i][j] += sum[i-1];
                } else {
                    int c = k;
                    int len = prefix[k-1][i-1];
                    if (len >= j-i+1) {
                        c = k;
                    } else {
                        if (num[k-1+len] > num[i-1+len]) c = k+1;
                    }
                    dp[i][j] += (sum[i-1] - sum[c-1] + mod) % mod;
                }
            }
            Arrays.fill(sum, 0);
            for (int k = 1; k <= i; k++) sum[k] = (sum[k-1] + dp[k][i]) % mod;
        }
        int ans = 0;
        for (int j = 1; j <= n; j++) {
            ans += dp[j][n];
            ans %= mod;
        }

        return ans;
    }
}
// @lc code=end
