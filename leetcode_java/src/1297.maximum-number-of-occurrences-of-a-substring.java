/*
 * @lc app=leetcode id=1297 lang=java
 *
 * [1297] Maximum Number of Occurrences of a Substring
 *
 * https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/description/
 *
 * algorithms
 * Medium (44.06%)
 * Likes:    55
 * Dislikes: 55
 * Total Accepted:    4.4K
 * Total Submissions: 9.9K
 * Testcase Example:  '"aababcaab"\n2\n3\n4'
 *
 * Given a string s, return the maximum number of ocurrences of any substring
 * under the following rules:
 * 
 * 
 * The number of unique characters in the substring must be less than or equal
 * to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * Output: 2
 * Explanation: Substring "aab" has 2 ocurrences in the original string.
 * It satisfies the conditions, 2 unique letters and size 3 (between minSize
 * and maxSize).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * Output: 2
 * Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
 * Output: 3
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * 1 <= maxLetters <= 26
 * 1 <= minSize <= maxSize <= min(26, s.length)
 * s only contains lowercase English letters.
 * 
 */

// @lc code=start
class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> cnt = new HashMap<>();
        Map<Character, Integer> occur = new HashMap<>();
        for(int i = 0; i < minSize; i++){
        	occur.put(s.charAt(i), occur.getOrDefault(s.charAt(i), 0) + 1);
        }
        if(occur.size() <= maxLetters){
        	cnt.put(s.substring(0, minSize), 1);
        }
        for(int i = 1; i + minSize - 1 < s.length(); i++){
        	String ss = s.substring(i, i + minSize);
        	occur.put(s.charAt(i + minSize - 1), occur.getOrDefault(s.charAt(i + minSize - 1), 0) + 1);
        	int c = occur.get(s.charAt(i - 1)) - 1;
        	if(c == 0){
        		occur.remove(s.charAt(i - 1));
        	}
        	else{
        		occur.put(s.charAt(i - 1), c);
        	}
        	// System.out.println(occur);
        	if(occur.size() <= maxLetters){
	        	cnt.put(ss, cnt.getOrDefault(ss, 0) + 1);
	        }
        }
        int max = 0;
        for (int c : cnt.values()) {
        	max = Math.max(c, max);
        }
        return max;
    }
}
// @lc code=end
