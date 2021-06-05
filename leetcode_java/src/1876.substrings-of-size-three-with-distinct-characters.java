/*
 * @lc app=leetcode id=1876 lang=java
 *
 * [1876] Substrings of Size Three with Distinct Characters
 *
 * https://leetcode.com/problems/substrings-of-size-three-with-distinct-characters/description/
 *
 * algorithms
 * Easy (65.15%)
 * Likes:    62
 * Dislikes: 0
 * Total Accepted:    8.8K
 * Total Submissions: 13.5K
 * Testcase Example:  '"xyzzaz"'
 *
 * A string is good if there are no repeated characters.
 * 
 * Given a string s​​​​​, return the number of good substrings of length three
 * in s​​​​​​.
 * 
 * Note that if there are multiple occurrences of the same substring, every
 * occurrence should be counted.
 * 
 * A substring is a contiguous sequence of characters in a string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "xyzzaz"
 * Output: 1
 * Explanation: There are 4 substrings of size 3: "xyz", "yzz", "zza", and
 * "zaz". 
 * The only good substring of length 3 is "xyz".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aababcabc"
 * Output: 4
 * Explanation: There are 7 substrings of size 3: "aab", "aba", "bab", "abc",
 * "bca", "cab", and "abc".
 * The good substrings are "abc", "bca", "cab", and "abc".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 100
 * s​​​​​​ consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countGoodSubstrings(String s) {
    	int cnt = 0;
    	if(s.length() < 3){
    		return 0;
    	}
        for(int i = 0; i <= s.length() - 3; i++){
        	if(s.charAt(i) != s.charAt(i + 1) && s.charAt(i + 2) != s.charAt(i + 1) && s.charAt(i) != s.charAt(i + 2)){
        		cnt++;
        	}
        }
        return cnt;
    }
}
// @lc code=end
