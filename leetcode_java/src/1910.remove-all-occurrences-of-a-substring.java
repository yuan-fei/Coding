/*
 * @lc app=leetcode id=1910 lang=java
 *
 * [1910] Remove All Occurrences of a Substring
 *
 * https://leetcode.com/problems/remove-all-occurrences-of-a-substring/description/
 *
 * algorithms
 * Medium (76.51%)
 * Likes:    122
 * Dislikes: 14
 * Total Accepted:    8.4K
 * Total Submissions: 10.9K
 * Testcase Example:  '"daabcbaabcbc"\n"abc"'
 *
 * Given two strings s and part, perform the following operation on s until all
 * occurrences of the substring part are removed:
 * 
 * 
 * Find the leftmost occurrence of the substring part and remove it from s.
 * 
 * 
 * Return s after removing all occurrences of part.
 * 
 * A substring is a contiguous sequence of characters in a string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "daabcbaabcbc", part = "abc"
 * Output: "dab"
 * Explanation: The following operations are done:
 * - s = "daabcbaabcbc", remove "abc" starting at index 2, so s = "dabaabcbc".
 * - s = "dabaabcbc", remove "abc" starting at index 4, so s = "dababc".
 * - s = "dababc", remove "abc" starting at index 3, so s = "dab".
 * Now s has no occurrences of "abc".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "axxxxyyyyb", part = "xy"
 * Output: "ab"
 * Explanation: The following operations are done:
 * - s = "axxxxyyyyb", remove "xy" starting at index 4 so s = "axxxyyyb".
 * - s = "axxxyyyb", remove "xy" starting at index 3 so s = "axxyyb".
 * - s = "axxyyb", remove "xy" starting at index 2 so s = "axyb".
 * - s = "axyb", remove "xy" starting at index 1 so s = "ab".
 * Now s has no occurrences of "xy".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 1000
 * 1 <= part.length <= 1000
 * s​​​​​​ and part consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String removeOccurrences(String s, String part) {
    	int idx = s.indexOf(part);
        while(idx >= 0){
        	// System.out.println(s);
        	s = s.substring(0, idx) + s.substring(idx + part.length());
        	idx = s.indexOf(part);
        }
        return s;
    }
}
// @lc code=end
