/*
 * @lc app=leetcode id=2223 lang=java
 *
 * [2223] Sum of Scores of Built Strings
 *
 * https://leetcode.com/problems/sum-of-scores-of-built-strings/description/
 *
 * algorithms
 * Hard (27.77%)
 * Likes:    68
 * Dislikes: 117
 * Total Accepted:    2K
 * Total Submissions: 7.4K
 * Testcase Example:  '"babab"'
 *
 * You are building a string s of length n one character at a time, prepending
 * each new character to the front of the string. The strings are labeled from
 * 1 to n, where the string with length i is labeled si.
 * 
 * 
 * For example, for s = "abaca", s1 == "a", s2 == "ca", s3 == "aca", etc.
 * 
 * 
 * The score of si is the length of the longest common prefix between si and sn
 * (Note that s == sn).
 * 
 * Given the final string s, return the sum of the score of every si.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "babab"
 * Output: 9
 * Explanation:
 * For s1 == "b", the longest common prefix is "b" which has a score of 1.
 * For s2 == "ab", there is no common prefix so the score is 0.
 * For s3 == "bab", the longest common prefix is "bab" which has a score of 3.
 * For s4 == "abab", there is no common prefix so the score is 0.
 * For s5 == "babab", the longest common prefix is "babab" which has a score of
 * 5.
 * The sum of the scores is 1 + 0 + 3 + 0 + 5 = 9, so we return 9.
 * 
 * Example 2:
 * 
 * 
 * Input: s = "azbazbzaz"
 * Output: 14
 * Explanation: 
 * For s2 == "az", the longest common prefix is "az" which has a score of 2.
 * For s6 == "azbzaz", the longest common prefix is "azb" which has a score of
 * 3.
 * For s9 == "azbazbzaz", the longest common prefix is "azbazbzaz" which has a
 * score of 9.
 * For all other si, the score is 0.
 * The sum of the scores is 2 + 3 + 9 = 14, so we return 14.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public long sumScores(String s) {
        int n = s.length();
        int[] arr = new int[n];
        for(int i = 1, l = 0, r = 0; i < n; i++){
            // System.out.println(i + "," + l+", "+r);
            if(i <= r){
                arr[i] = Math.min(r - i + 1, arr[i - l]);
            }
            while(i + arr[i] < n && s.charAt(i + arr[i]) == s.charAt(arr[i])){
                arr[i]++;
            }
            if(r < i + arr[i] - 1){
                l = i;
                r = i + arr[i] - 1;
            }
        }
        arr[0] = n;
        // System.out.println(Arrays.toString(arr));
        long ret = 0;
        for(long x : arr){
            ret += x;
        }
        return ret;
    }
}
// @lc code=end
