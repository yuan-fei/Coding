/*
 * [424] Longest Repeating Character Replacement
 *
 * https://leetcode.com/problems/longest-repeating-character-replacement/description/
 *
 * algorithms
 * Medium (42.55%)
 * Total Accepted:    30.8K
 * Total Submissions: 69.8K
 * Testcase Example:  '"ABAB"\n2'
 *
 * Given a string that consists of only uppercase English letters, you can
 * replace any letter in the string with another letter at most k times. Find
 * the length of a longest substring containing all repeating letters you can
 * get after performing the above operations.
 * 
 * Note:
 * Both the string's length and k will not exceed 104.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input:
 * s = "ABAB", k = 2
 * 
 * Output:
 * 4
 * 
 * Explanation:
 * Replace the two 'A's with two 'B's or vice versa.
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * s = "AABABBA", k = 1
 * 
 * Output:
 * 4
 * 
 * Explanation:
 * Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * 
 * 
 */
/*
maxCnt is global max, never shrinks
*/
class Solution {
    public int characterReplacement(String s, int k) {
        int[] cnt = new int[26];
        int begin = 0;
        int end = 0;
        int maxCnt = 0;
        int maxSize = 0;
        while(end < s.length()){
        	cnt[s.charAt(end) - 'A']++;
        	maxCnt = Math.max(maxCnt, cnt[s.charAt(end) - 'A']);
        	while(end - begin + 1 - maxCnt > k){
        		cnt[s.charAt(begin) - 'A']--;
        		begin++;
        	}
        	maxSize= Math.max(maxSize, end - begin + 1);
        	end++;
        }
        return maxSize;
    }
}
