/*
 * @lc app=leetcode id=2746 lang=java
 *
 * [2746] Decremental String Concatenation
 *
 * https://leetcode.com/problems/decremental-string-concatenation/description/
 *
 * algorithms
 * Medium (25.78%)
 * Likes:    342
 * Dislikes: 31
 * Total Accepted:    7.1K
 * Total Submissions: 27.6K
 * Testcase Example:  '["aa","ab","bc"]'
 *
 * You are given a 0-indexed array words containing n strings.
 * 
 * Let's define a join operation join(x, y) between two strings x and y as
 * concatenating them into xy. However, if the last character of x is equal to
 * the first character of y, one of them is deleted.
 * 
 * For example join("ab", "ba") = "aba" and join("ab", "cde") = "abcde".
 * 
 * You are to perform n - 1 join operations. Let str0 = words[0]. Starting from
 * i = 1 up to i = n - 1, for the i^th operation, you can do one of the
 * following:
 * 
 * 
 * Make stri = join(stri - 1, words[i])
 * Make stri = join(words[i], stri - 1)
 * 
 * 
 * Your task is to minimize the length of strn - 1.
 * 
 * Return an integer denoting the minimum possible length of strn - 1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["aa","ab","bc"]
 * Output: 4
 * Explanation: In this example, we can perform join operations in the
 * following order to minimize the length of str2: 
 * str0 = "aa"
 * str1 = join(str0, "ab") = "aab"
 * str2 = join(str1, "bc") = "aabc" 
 * It can be shown that the minimum possible length of str2 is 4.
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["ab","b"]
 * Output: 2
 * Explanation: In this example, str0 = "ab", there are two ways to get str1: 
 * join(str0, "b") = "ab" or join("b", str0) = "bab". 
 * The first string, "ab", has the minimum length. Hence, the answer is 2.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: words = ["aaa","c","aba"]
 * Output: 6
 * Explanation: In this example, we can perform join operations in the
 * following order to minimize the length of str2: 
 * str0 = "aaa"
 * str1 = join(str0, "c") = "aaac"
 * str2 = join("aba", str1) = "abaaac"
 * It can be shown that the minimum possible length of str2 is 6.
 * 
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 50
 * Each character in words[i] is an English lowercase letter
 * 
 * 
 */

// @lc code=start
class Solution {
    int[][][] dp;
    public int minimizeConcatenatedLength(String[] words) {
        dp = new int[words.length][26][26];
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[i].length; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
        int ans = words[0].length() + solve(words, 1, words[0].charAt(0), words[0].charAt(words[0].length() - 1));
        // System.out.println(Arrays.deepToString(dp));
        return ans;
    }

    int solve(String[] words, int i, char firstChar, char lastChar){
        if(i >= words.length) return 0;

        if(dp[i][firstChar - 'a'][lastChar - 'a'] != -1) {
            // System.out.println(Arrays.asList(i, firstChar, lastChar) + ": hit");
            return dp[i][firstChar - 'a'][lastChar - 'a'];
        }
        // System.out.println(Arrays.asList(i, firstChar, lastChar) + ": miss");
        int res1, res2, wordSize = words[i].length();

        //When word is kept first & then the ans string 
        res1 = wordSize + solve(words, i+1, words[i].charAt(0), lastChar);
        if(words[i].charAt(words[i].length() - 1) == firstChar) {
            res1--;
        }

        //When word is kept at last after the ans string 
        res2 = wordSize + solve(words, i+1, firstChar, words[i].charAt(words[i].length() - 1));
        if(words[i].charAt(0) == lastChar) {
            res2--;
        }

        return dp[i][firstChar - 'a'][lastChar - 'a'] = Math.min(res1, res2);
    }
}
// @lc code=end
