/*
 * @lc app=leetcode id=1392 lang=java
 *
 * [1392] Longest Happy Prefix
 *
 * https://leetcode.com/problems/longest-happy-prefix/description/
 *
 * algorithms
 * Hard (30.04%)
 * Likes:    22
 * Dislikes: 4
 * Total Accepted:    1.9K
 * Total Submissions: 6.5K
 * Testcase Example:  '"level"'
 *
 * A string is called a happy prefix if is a non-empty prefix which is also a
 * suffix (excluding itself).
 * 
 * Given a string s. Return the longest happy prefix of s .
 * 
 * Return an empty string if no such prefix exists.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "level"
 * Output: "l"
 * Explanation: s contains 4 prefix excluding itself ("l", "le", "lev",
 * "leve"), and suffix ("l", "el", "vel", "evel"). The largest prefix which is
 * also suffix is given by "l".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "ababab"
 * Output: "abab"
 * Explanation: "abab" is the largest prefix which is also suffix. They can
 * overlap in the original string.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "leetcodeleet"
 * Output: "leet"
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "a"
 * Output: ""
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s contains only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String longestPrefix(String s) {
        long mod = 1000000007;
        int maxLen = 0;
        long hashPrefix = 0;
        long hashSurfix = 0;
        long base = 1;
        for(int i = 0; i < s.length() - 1; i++){
        	hashPrefix = (hashPrefix * mod) + s.charAt(i);
        	hashSurfix = hashSurfix + base * s.charAt(s.length() - i - 1);
        	base *= mod;
        	if(hashPrefix == hashSurfix){
        		maxLen = i + 1;
        	}
        }
        return s.substring(0, maxLen);
    }
}
// @lc code=end
