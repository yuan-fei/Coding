/*
 * @lc app=leetcode id=2516 lang=java
 *
 * [2516] Take K of Each Character From Left and Right
 *
 * https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/description/
 *
 * algorithms
 * Medium (27.44%)
 * Likes:    182
 * Dislikes: 31
 * Total Accepted:    4.5K
 * Total Submissions: 16.2K
 * Testcase Example:  '"aabaaaacaabc"\n2'
 *
 * You are given a string s consisting of the characters 'a', 'b', and 'c' and
 * a non-negative integer k. Each minute, you may take either the leftmost
 * character of s, or the rightmost character of s.
 * 
 * Return the minimum number of minutes needed for you to take at least k of
 * each character, or return -1 if it is not possible to take k of each
 * character.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "aabaaaacaabc", k = 2
 * Output: 8
 * Explanation: 
 * Take three characters from the left of s. You now have two 'a' characters,
 * and one 'b' character.
 * Take five characters from the right of s. You now have four 'a' characters,
 * two 'b' characters, and two 'c' characters.
 * A total of 3 + 5 = 8 minutes is needed.
 * It can be proven that 8 is the minimum number of minutes needed.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "a", k = 1
 * Output: -1
 * Explanation: It is not possible to take one 'b' or 'c' so return -1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s consists of only the letters 'a', 'b', and 'c'.
 * 0 <= k <= s.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int takeCharacters(String s, int k) {
        int n = s.length();
        int[][] prefSum = new int[3][n + 1];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 3; j++){
                prefSum[j][i + 1] = prefSum[j][i] + (s.charAt(i) - 'a' == j ? 1 : 0);
            }
        }
        Map<Integer, Integer>[] cnt2Index = new Map[3];
        for(int i = 0; i < 3; i++){
            cnt2Index[i] = new HashMap<>();
            for(int j = 0; j <= n; j++){
                cnt2Index[i].putIfAbsent(prefSum[i][j], j);
            }
        }

        int ans = n + 1;
        for(int i = n; i > 0; i--){
            int minLen = 0;
            for(int j = 0; j < 3; j++){
                int existing = prefSum[j][n] - prefSum[j][i];
                int need = Math.max(0, k - existing);
                int idx = cnt2Index[j].getOrDefault(need, n + 1);
                if(idx > i){
                    return -1;
                }
                minLen = Math.max(minLen, idx + n - i);
            }
            ans = Math.min(ans, minLen);
        }
        return ans;
    }
}
// @lc code=end
