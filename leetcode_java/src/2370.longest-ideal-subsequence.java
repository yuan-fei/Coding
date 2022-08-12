/*
 * @lc app=leetcode id=2370 lang=java
 *
 * [2370] Longest Ideal Subsequence
 *
 * https://leetcode.com/problems/longest-ideal-subsequence/description/
 *
 * algorithms
 * Medium (32.77%)
 * Likes:    162
 * Dislikes: 9
 * Total Accepted:    6.5K
 * Total Submissions: 19.9K
 * Testcase Example:  '"acfgbd"\n2'
 *
 * You are given a string s consisting of lowercase letters and an integer k.
 * We call a string t ideal if the following conditions are satisfied:
 * 
 * 
 * t is a subsequence of the string s.
 * The absolute difference in the alphabet order of every two adjacent letters
 * in t is less than or equal to k.
 * 
 * 
 * Return the length of the longest ideal string.
 * 
 * A subsequence is a string that can be derived from another string by
 * deleting some or no characters without changing the order of the remaining
 * characters.
 * 
 * Note that the alphabet order is not cyclic. For example, the absolute
 * difference in the alphabet order of 'a' and 'z' is 25, not 1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "acfgbd", k = 2
 * Output: 4
 * Explanation: The longest ideal string is "acbd". The length of this string
 * is 4, so 4 is returned.
 * Note that "acfgbd" is not ideal because 'c' and 'f' have a difference of 3
 * in alphabet order.
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abcd", k = 3
 * Output: 4
 * Explanation: The longest ideal string is "abcd". The length of this string
 * is 4, so 4 is returned.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * 0 <= k <= 25
 * s consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestIdealString(String s, int k) {
        int[] dp = new int[26];
        for(char c : s.toCharArray()){
            int x = c - 'a';
            dp[x] = Math.max(dp[x], dp[x] + 1);
            for(int y = 0; y < 26; y++){
                if(Math.abs(x - y) <= k && x != y){
                    dp[x] = Math.max(dp[x], dp[y] + 1);
                }
            }
            // System.out.println(Arrays.toString(dp));
        }
        int max = 0;
        for(int x: dp){
            max = Math.max(max, x);
        }
        return max;
    }
}
// @lc code=end
