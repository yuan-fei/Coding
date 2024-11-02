/*
 * @lc app=leetcode id=2911 lang=java
 *
 * [2911] Minimum Changes to Make K Semi-palindromes
 *
 * https://leetcode.com/problems/minimum-changes-to-make-k-semi-palindromes/description/
 *
 * algorithms
 * Hard (34.48%)
 * Likes:    123
 * Dislikes: 102
 * Total Accepted:    4.1K
 * Total Submissions: 11.2K
 * Testcase Example:  '"abcac"\n2'
 *
 * Given a string s and an integer k, partition s into k substrings such that
 * the letter changes needed to make each substring a semi-palindrome are
 * minimized.
 * 
 * Return the minimum number of letter changes required.
 * 
 * A semi-palindrome is a special type of string that can be divided into
 * palindromes based on a repeating pattern. To check if a string is a
 * semi-palindrome:​
 * 
 * 
 * Choose a positive divisor d of the string's length. d can range from 1 up
 * to, but not including, the string's length. For a string of length 1, it
 * does not have a valid divisor as per this definition, since the only divisor
 * is its length, which is not allowed.
 * For a given divisor d, divide the string into groups where each group
 * contains characters from the string that follow a repeating pattern of
 * length d. Specifically, the first group consists of characters at positions
 * 1, 1 + d, 1 + 2d, and so on; the second group includes characters at
 * positions 2, 2 + d, 2 + 2d, etc.
 * The string is considered a semi-palindrome if each of these groups forms a
 * palindrome.
 * 
 * 
 * Consider the string "abcabc":
 * 
 * 
 * The length of "abcabc" is 6. Valid divisors are 1, 2, and 3.
 * For d = 1: The entire string "abcabc" forms one group. Not a palindrome.
 * For d = 2:
 * 
 * Group 1 (positions 1, 3, 5): "acb"
 * Group 2 (positions 2, 4, 6): "bac"
 * Neither group forms a palindrome.
 * 
 * 
 * For d = 3:
 * 
 * Group 1 (positions 1, 4): "aa"
 * Group 2 (positions 2, 5): "bb"
 * Group 3 (positions 3, 6): "cc"
 * All groups form palindromes. Therefore, "abcabc" is a
 * semi-palindrome.
 * 
 * 
 * 
 * 
 * 
 * Example 1: 
 * 
 * 
 * Input:   s = "abcac", k = 2 
 * 
 * Output:   1 
 * 
 * Explanation:  Divide s into "ab" and "cac". "cac" is already
 * semi-palindrome. Change "ab" to "aa", it becomes semi-palindrome with d =
 * 1.
 * 
 * 
 * Example 2: 
 * 
 * 
 * Input:   s = "abcdef", k = 2 
 * 
 * Output:   2 
 * 
 * Explanation:  Divide s into substrings "abc" and "def". Each needs one
 * change to become semi-palindrome.
 * 
 * 
 * Example 3: 
 * 
 * 
 * Input:   s = "aabbaa", k = 3 
 * 
 * Output:   0 
 * 
 * Explanation:  Divide s into substrings "aa", "bb" and "aa". All are already
 * semi-palindromes.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= s.length <= 200
 * 1 <= k <= s.length / 2
 * s contains only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    int MAX = 250;
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] changes = getAllSubStringSubPalindromeChanges(s);
        int[][] dp = new int[n + 1][k + 1];

        for(int i = 0; i <= n; i++){
            Arrays.fill(dp[i], MAX);
            if(i < n){
                dp[i][1] = changes[i][n - 1];    
            }
        }
        dp[n][0] = 0;

        for(int j = 2; j <= k; j++){
            for(int begin = 0; begin < n; begin++){
                for(int mid = begin + 1; mid + 1 <= n; mid++){
                    dp[begin][j] = Math.min(dp[begin][j], changes[begin][mid] + dp[mid + 1][j - 1]);
                }
            }
        }
        // System.out.println(Arrays.deepToString(changes));
        // System.out.println(Arrays.deepToString(dp));
        return dp[0][k];
    }

    int getSubPalindromeChanges(String s, int begin, int end){
        int l = end - begin + 1;
        if(l == 1){
            return MAX;
        }
        int ans = l;
        for(int d = 1; d < l; d++){
            if(l % d == 0){
                int changes = 0;
                for(int offs = 0; offs < d; offs++){
                    for(int i = 0, j = l / d - 1; i < j; i++,j--){
                        if(s.charAt(begin + i * d + offs) != s.charAt(begin + j * d + offs)){
                            changes++;
                        }
                    }
                }
                ans = Math.min(ans, changes);
            }
        }
        return ans;
    }

    int[][] getAllSubStringSubPalindromeChanges(String s){
        int n = s.length();
        int[][] ret = new int[n][n];
        for(int begin = 0; begin < n; begin++){
            for(int end = begin; end < n; end++){
                ret[begin][end] = getSubPalindromeChanges(s, begin, end);
            }
        }
        return ret;
    }
}
// @lc code=end
