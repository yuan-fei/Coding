/*
 * @lc app=leetcode id=2027 lang=java
 *
 * [2027] Minimum Moves to Convert String
 *
 * https://leetcode.com/problems/minimum-moves-to-convert-string/description/
 *
 * algorithms
 * Easy (48.92%)
 * Likes:    27
 * Dislikes: 22
 * Total Accepted:    6.5K
 * Total Submissions: 13.4K
 * Testcase Example:  '"XXX"'
 *
 * You are given a string s consisting of n characters which are either 'X' or
 * 'O'.
 * 
 * A move is defined as selecting three consecutive characters of s and
 * converting them to 'O'. Note that if a move is applied to the character 'O',
 * it will stay the same.
 * 
 * Return the minimum number of moves required so that all the characters of s
 * are converted to 'O'.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "XXX"
 * Output: 1
 * Explanation: XXX -> OOO
 * We select all the 3 characters and convert them in one move.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "XXOX"
 * Output: 2
 * Explanation: XXOX -> OOOX -> OOOO
 * We select the first 3 characters in the first move, and convert them to 'O'.
 * Then we select the last 3 characters and convert them so that the final
 * string contains all 'O's.
 * 
 * Example 3:
 * 
 * 
 * Input: s = "OOOO"
 * Output: 0
 * Explanation: There are no 'X's in s to convert.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= s.length <= 1000
 * s[i] is either 'X' or 'O'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumMoves(String s) {
        int cnt = 0;
        char[] chars = s.toCharArray();
        for(int i = 0; i < s.length(); i++){
            // System.out.println(chars);
            if(chars[i] == 'X'){
                cnt++;
                chars[i] = 'O';
                if(i + 1 < s.length()){
                    chars[i + 1] = 'O';
                }
                if(i + 2 < s.length()){
                    chars[i + 2] = 'O';
                }
            }
        }
        return cnt;
    }
}
// @lc code=end
