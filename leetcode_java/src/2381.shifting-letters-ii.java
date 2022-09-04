/*
 * @lc app=leetcode id=2381 lang=java
 *
 * [2381] Shifting Letters II
 *
 * https://leetcode.com/problems/shifting-letters-ii/description/
 *
 * algorithms
 * Medium (30.61%)
 * Likes:    314
 * Dislikes: 7
 * Total Accepted:    8.3K
 * Total Submissions: 27.1K
 * Testcase Example:  '"abc"\n[[0,1,0],[1,2,1],[0,2,1]]'
 *
 * You are given a string s of lowercase English letters and a 2D integer array
 * shifts where shifts[i] = [starti, endi, directioni]. For every i, shift the
 * characters in s from the index starti to the index endi (inclusive) forward
 * if directioni = 1, or shift the characters backward if directioni = 0.
 * 
 * Shifting a character forward means replacing it with the next letter in the
 * alphabet (wrapping around so that 'z' becomes 'a'). Similarly, shifting a
 * character backward means replacing it with the previous letter in the
 * alphabet (wrapping around so that 'a' becomes 'z').
 * 
 * Return the final string after all such shifts to s are applied.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
 * Output: "ace"
 * Explanation: Firstly, shift the characters from index 0 to index 1 backward.
 * Now s = "zac".
 * Secondly, shift the characters from index 1 to index 2 forward. Now s =
 * "zbd".
 * Finally, shift the characters from index 0 to index 2 forward. Now s =
 * "ace".
 * 
 * Example 2:
 * 
 * 
 * Input: s = "dztz", shifts = [[0,0,0],[1,1,1]]
 * Output: "catz"
 * Explanation: Firstly, shift the characters from index 0 to index 0 backward.
 * Now s = "cztz".
 * Finally, shift the characters from index 1 to index 1 forward. Now s =
 * "catz".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length, shifts.length <= 5 * 10^4
 * shifts[i].length == 3
 * 0 <= starti <= endi < s.length
 * 0 <= directioni <= 1
 * s consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] ofs = new int[n + 1];
        for(int[] shift : shifts){
            if(shift[2] == 0){
                ofs[shift[0]]--;
                ofs[shift[1] + 1]++;
            }
            else{
                ofs[shift[0]]++;
                ofs[shift[1] + 1]--;
            }
        }
        StringBuilder sb = new StringBuilder();
        ofs[0] %= 26;
        ofs[0] = (ofs[0] + 26) % 26;
        sb.append((char)('a' + (s.charAt(0) - 'a' + ofs[0]) % 26));
        for(int i = 1; i < n; i++){
            ofs[i] += ofs[i - 1];
            ofs[i] %= 26;
            ofs[i] = (ofs[i] + 26) % 26;
            sb.append((char)('a' + (s.charAt(i) - 'a' + ofs[i]) % 26));
        }
        
        return sb.toString();
    }
}
// @lc code=end
