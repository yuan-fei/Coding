/*
 * [3] Longest Substring Without Repeating Characters
 *
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 * algorithms
 * Medium (24.62%)
 * Total Accepted:    433.4K
 * Total Submissions: 1.8M
 * Testcase Example:  '"abcabcbb"'
 *
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * 
 * Examples:
 * 
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * 
 * Given "bbbbb", the answer is "b", with the length of 1.
 * 
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the
 * answer must be a substring, "pwke" is a subsequence and not a substring.
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        char[] chars = s.toCharArray();
        int maxLength = 0;
        int subStrStart = 0;
        for (int i = 0; i < chars.length; i++) {
        	if(map.containsKey(chars[i])){
        		int first = map.get(chars[i]);
        		while (subStrStart < first + 1) {
        			map.remove(chars[subStrStart]);	
        			subStrStart++;
        		}
        	}
        	
	        maxLength = Math.max(maxLength, i - subStrStart + 1);	
	        map.put(chars[i], i);	
        	
        	
        }
        return maxLength;
    }
}
