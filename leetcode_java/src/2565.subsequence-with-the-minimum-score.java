/*
 * @lc app=leetcode id=2565 lang=java
 *
 * [2565] Subsequence With the Minimum Score
 *
 * https://leetcode.com/problems/subsequence-with-the-minimum-score/description/
 *
 * algorithms
 * Hard (20.85%)
 * Likes:    47
 * Dislikes: 1
 * Total Accepted:    979
 * Total Submissions: 4.7K
 * Testcase Example:  '"abacaba"\n"bzaa"'
 *
 * You are given two strings s and t.
 * 
 * You are allowed to remove any number of characters from the string t.
 * 
 * The score string is 0 if no characters are removed from the string t,
 * otherwise:
 * 
 * 
 * Let left be the minimum index among all removed characters.
 * Let right be the maximum index among all removed characters.
 * 
 * 
 * Then the score of the string is right - left + 1.
 * 
 * Return the minimum possible score to make t a subsequence of s.
 * 
 * A subsequence of a string is a new string that is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (i.e., "ace" is a
 * subsequence of "abcde" while "aec" is not).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abacaba", t = "bzaa"
 * Output: 1
 * Explanation: In this example, we remove the character "z" at index 1
 * (0-indexed).
 * The string t becomes "baa" which is a subsequence of the string "abacaba"
 * and the score is 1 - 1 + 1 = 1.
 * It can be proven that 1 is the minimum score that we can achieve.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "cde", t = "xyz"
 * Output: 3
 * Explanation: In this example, we remove characters "x", "y" and "z" at
 * indices 0, 1, and 2 (0-indexed).
 * The string t becomes "" which is a subsequence of the string "cde" and the
 * score is 2 - 0 + 1 = 3.
 * It can be proven that 3 is the minimum score that we can achieve.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length, t.length <= 10^5
 * s and t consist of only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumScore(String s, String t) {
        int[] forward = getMinMatch(s, t);
        StringBuilder sbS = new StringBuilder(s);
        sbS.reverse();
        StringBuilder sbT = new StringBuilder(t);
        sbT.reverse();
        int[] backward = getMinMatch(sbS.toString(), sbT.toString());
        reverse(backward);
        for(int i = 0; i < backward.length; i++){
            backward[i] = s.length() - 1 - backward[i];
        }
        backward = Arrays.copyOf(backward, backward.length + 1);
        backward[backward.length - 1] = s.length();
        // System.out.println(Arrays.toString(forward));
        // System.out.println(Arrays.toString(backward));
        int ans = t.length();
        int j = 0;
        while(backward[j] == -1){
            j++;
        }
        ans = j;
        for(int i = 0; i < forward.length; i++){
            while(j < backward.length && forward[i] >= backward[j]){
                j++;
            }
            if(j < backward.length && j - i - 1 >= 0){
                ans = Math.min(ans, j - i - 1);
            }
        }
        return ans;
    }

    int[] getMinMatch(String s, String t){
        int[] minMatch = new int[t.length()];
        Arrays.fill(minMatch, s.length());
        int i = 0; 
        int j = 0;
        while(i < s.length() && j < t.length()){
            if(s.charAt(i) == t.charAt(j)){
                minMatch[j] = i;
                i++;
                j++;
            }
            else{
                i++;
            }
        }
        return minMatch;
    }

    void reverse(int[] arr){
        for(int i = 0; i < arr.length / 2; i++)
        {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }
}
// @lc code=end
