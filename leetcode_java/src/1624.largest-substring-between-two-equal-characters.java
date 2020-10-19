/*
 * @lc app=leetcode id=1624 lang=java
 *
 * [1624] Largest Substring Between Two Equal Characters
 *
 * https://leetcode.com/problems/largest-substring-between-two-equal-characters/description/
 *
 * algorithms
 * Easy (59.82%)
 * Likes:    47
 * Dislikes: 2
 * Total Accepted:    7.5K
 * Total Submissions: 12.5K
 * Testcase Example:  '"aa"'
 *
 * Given a string s, return the length of the longest substring between two
 * equal characters, excluding the two characters. If there is no such
 * substring return -1.
 * 
 * A substring is a contiguous sequence of characters within a string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "aa"
 * Output: 0
 * Explanation: The optimal substring here is an empty substring between the
 * two 'a's.
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abca"
 * Output: 2
 * Explanation: The optimal substring here is "bc".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "cbzxy"
 * Output: -1
 * Explanation: There are no characters that appear twice in s.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "cabbac"
 * Output: 4
 * Explanation: The optimal substring here is "abba". Other non-optimal
 * substrings include "bb" and "".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 300
 * s contains only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] pos = new int[26];
        int max = -1;
        for(int i = 0; i < s.length(); i++){
        	int id = s.charAt(i) - 'a';
        	if(pos[id] > 0){
        		max = Math.max(max, i - pos[id]);
        	}
        	else{
        		pos[id] = i + 1;
        	}
        }
        return max;
    }
}
// @lc code=end
