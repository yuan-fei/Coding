/*
 * @lc app=leetcode id=1328 lang=java
 *
 * [1328] Break a Palindrome
 *
 * https://leetcode.com/problems/break-a-palindrome/description/
 *
 * algorithms
 * Medium (35.22%)
 * Likes:    18
 * Dislikes: 18
 * Total Accepted:    2.1K
 * Total Submissions: 5.9K
 * Testcase Example:  '"abccba"'
 *
 * Given a palindromic string palindrome, replace exactly one character by any
 * lowercase English letter so that the string becomes the lexicographically
 * smallest possible string that isn't a palindrome.
 * 
 * After doing so, return the final string.  If there is no way to do so,
 * return the empty string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: palindrome = "abccba"
 * Output: "aaccba"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: palindrome = "a"
 * Output: ""
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= palindrome.length <= 1000
 * palindrome consists of only lowercase English letters.
 * 
 */

// @lc code=start
class Solution {
    public String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        if(n == 1){
        	return "";
        }
        for(int i = 0; i < n / 2; i++){
        	if(palindrome.charAt(i) != 'a'){
        		return palindrome.substring(0, i) + "a" + palindrome.substring(i + 1);
        	}
        }
        return palindrome.substring(0, n - 1) + "b";
    }
}
// @lc code=end
