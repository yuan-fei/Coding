/*
 * @lc app=leetcode id=2484 lang=java
 *
 * [2484] Count Palindromic Subsequences
 *
 * https://leetcode.com/problems/count-palindromic-subsequences/description/
 *
 * algorithms
 * Hard (23.26%)
 * Likes:    110
 * Dislikes: 5
 * Total Accepted:    1.9K
 * Total Submissions: 8.3K
 * Testcase Example:  '"103301"'
 *
 * Given a string of digits s, return the number of palindromic subsequences of
 * s having length 5. Since the answer may be very large, return it modulo 10^9
 * + 7.
 * 
 * Note:
 * 
 * 
 * A string is palindromic if it reads the same forward and backward.
 * A subsequence is a string that can be derived from another string by
 * deleting some or no characters without changing the order of the remaining
 * characters.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "103301"
 * Output: 2
 * Explanation: 
 * There are 6 possible subsequences of length 5:
 * "10330","10331","10301","10301","13301","03301". 
 * Two of them (both equal to "10301") are palindromic.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "0000000"
 * Output: 21
 * Explanation: All 21 subsequences are "00000", which is palindromic.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "9999900000"
 * Output: 2
 * Explanation: The only two palindromic subsequences are "99999" and
 * "00000".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^4
 * s consists of digits.
 * 
 * 
 */

// @lc code=start
class Solution {
    int MOD = (int)1e9 + 7;
    public int countPalindromes(String s) {
        int ans = 0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                for(int k = 0; k < 10; k++){
                    ans += countMatch(s, "" + i + j + k + j + i);
                    ans %= MOD;
                }
            }
        }
        return ans;
    }

    private int countMatch(String s, String p){
        int[] dp = new int[p.length() + 1];
        dp[0] = 1;
        for(int i = 0; i < s.length(); i++){
            for(int j = p.length(); j >= 1; j--){
                if(p.charAt(j - 1) == s.charAt(i)){
                    dp[j] += dp[j - 1];
                    dp[j] %= MOD;
                }
            }
        }
        return dp[p.length()];
    }
}
// @lc code=end
