/*
 * @lc app=leetcode id=2573 lang=java
 *
 * [2573] Find the String with LCP
 *
 * https://leetcode.com/problems/find-the-string-with-lcp/description/
 *
 * algorithms
 * Hard (25.53%)
 * Likes:    19
 * Dislikes: 4
 * Total Accepted:    528
 * Total Submissions: 2.1K
 * Testcase Example:  '[[4,0,2,0],[0,3,0,1],[2,0,2,0],[0,1,0,1]]'
 *
 * We define the lcp matrix of any 0-indexed string word of n lowercase English
 * letters as an n x n grid such that:
 * 
 * 
 * lcp[i][j] is equal to the length of the longest common prefix between the
 * substrings word[i,n-1] and word[j,n-1].
 * 
 * 
 * Given an n x n matrix lcp, return the alphabetically smallest string word
 * that corresponds to lcp. If there is no such string, return an empty
 * string.
 * 
 * A string a is lexicographically smaller than a string b (of the same length)
 * if in the first position where a and b differ, string a has a letter that
 * appears earlier in the alphabet than the corresponding letter in b. For
 * example, "aabd" is lexicographically smaller than "aaca" because the first
 * position they differ is at the third letter, and 'b' comes before 'c'.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: lcp = [[4,0,2,0],[0,3,0,1],[2,0,2,0],[0,1,0,1]]
 * Output: "abab"
 * Explanation: lcp corresponds to any 4 letter string with two alternating
 * letters. The lexicographically smallest of them is "abab".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,1]]
 * Output: "aaaa"
 * Explanation: lcp corresponds to any 4 letter string with a single distinct
 * letter. The lexicographically smallest of them is "aaaa". 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: lcp = [[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,3]]
 * Output: ""
 * Explanation: lcp[3][3] cannot be equal to 3 since word[3,...,3] consists of
 * only a single letter; Thus, no answer exists.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n == lcp.length == lcp[i].length <= 1000
 * 0 <= lcp[i][j] <= n
 * 
 * 
 */

// @lc code=start
class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] chars = new char[n];
        chars[0] = 'a';
        int cur = 'a';
        for(int i = 1; i < n; i++){
            boolean seen = false;
            for(int j = 0; j < i; j++){
                int diff = lcp[i][j] - ((i < n - 1 && j < n - 1) ? lcp[i + 1][j + 1] : 0);
                if(diff == 1){
                    chars[i] = chars[j];
                    seen = true;
                    break;
                }
            }
            if(!seen){
                cur++;
                chars[i] = (char)cur;
            }
        }
        // System.out.println(String.valueOf(chars));
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int p = (chars[i] == chars[j])? 1 : 0;
                p += (p == 1 && i < n - 1 && j < n - 1) ? lcp[i + 1][j + 1] : 0;
                if(lcp[i][j] != p){
                    // System.out.println(i + ", " + j + ", " + lcp[i][j] + ", " + p);
                    return "";
                }
            }
        }
        if(cur > 'z'){
            return "";
        }
        return String.valueOf(chars);
    }
}
// @lc code=end
