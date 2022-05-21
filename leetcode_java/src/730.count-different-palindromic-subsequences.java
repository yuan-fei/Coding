/*
 * @lc app=leetcode id=730 lang=java
 *
 * [730] Count Different Palindromic Subsequences
 *
 * https://leetcode.com/problems/count-different-palindromic-subsequences/description/
 *
 * algorithms
 * Hard (44.43%)
 * Likes:    1296
 * Dislikes: 72
 * Total Accepted:    27K
 * Total Submissions: 60.8K
 * Testcase Example:  '"bccb"'
 *
 * Given a string s, return the number of different non-empty palindromic
 * subsequences in s. Since the answer may be very large, return it modulo 10^9
 * + 7.
 * 
 * A subsequence of a string is obtained by deleting zero or more characters
 * from the string.
 * 
 * A sequence is palindromic if it is equal to the sequence reversed.
 * 
 * Two sequences a1, a2, ... and b1, b2, ... are different if there is some i
 * for which ai != bi.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "bccb"
 * Output: 6
 * Explanation: The 6 different non-empty palindromic subsequences are 'b',
 * 'c', 'bb', 'cc', 'bcb', 'bccb'.
 * Note that 'bcb' is counted only once, even though it occurs twice.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s =
 * "abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba"
 * Output: 104860361
 * Explanation: There are 3104860382 different non-empty palindromic
 * subsequences, which is 104860361 modulo 10^9 + 7.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 1000
 * s[i] is either 'a', 'b', 'c', or 'd'.
 * 
 * 
 */

// @lc code=start
class Solution {
    private final int MOD = 1000000007;

    public int countPalindromicSubsequences(String s) {
        
        //meo[i][j][k] means # distinct subsequences within s[i][j] boardered by char k
        
        Integer[][][] memo = new Integer[s.length()][s.length()][4];
        
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            ans = (ans + distinct(s, memo, 0, s.length() - 1, i)) % MOD;
        }
        
        return ans;
    }
    
    private int distinct(String s, Integer[][][] memo, int left, int right, int letter) {
        // base cases
        if (left == right && letter == s.charAt(left) - 'a') return 1;
        if (right <= left) return 0;
        if (memo[left][right][letter] != null) return memo[left][right][letter];

        int res = 0;
        if (s.charAt(left) == s.charAt(right) && s.charAt(left) - 'a' == letter) {
            // case 1, s[l] == s[r] == letter
            res += 2;
            for (int i = 0; i < 4; i++) {
                res = (res + distinct(s, memo, left + 1, right - 1, i)) % MOD;
            }
        } else {
            // case 2, s[l] != s[r]
            res = (res + distinct(s, memo, left, right - 1, letter)) % MOD;
            res = (res + distinct(s, memo, left + 1, right, letter)) % MOD;
            res = (res - distinct(s, memo, left + 1, right - 1, letter)) % MOD;
            if (res < 0) res += MOD;
        }
        
        memo[left][right][letter] = res;
        
        return res;
    }
}
// @lc code=end
