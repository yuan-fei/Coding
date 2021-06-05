/*
 * @lc app=leetcode id=680 lang=java
 *
 * [680] Valid Palindrome II
 *
 * https://leetcode.com/problems/valid-palindrome-ii/description/
 *
 * algorithms
 * Easy (37.16%)
 * Likes:    2551
 * Dislikes: 163
 * Total Accepted:    261K
 * Total Submissions: 702.2K
 * Testcase Example:  '"aba"'
 *
 * 
 * Given a non-empty string s, you may delete at most one character.  Judge
 * whether you can make it a palindrome.
 * 
 * 
 * Example 1:
 * 
 * Input: "aba"
 * Output: True
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * 
 * 
 * 
 * Note:
 * 
 * The string will only contain lowercase characters a-z.
 * The maximum length of the string is 50000.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean validPalindrome(String s) {
    	int i = 0; int j = s.length() - 1;
        while(i < j){
        	if(s.charAt(i) != s.charAt(j)){
        		return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
        	}
        	i++;
        	j--;
        }
        return true;
    }

    boolean isPalindrome(String s, int i, int j){
    	while(i < j){
    		if(s.charAt(i) != s.charAt(j)){
    			return false;
    		}
    		i++;
    		j--;
    	}
    	return true;
    }
}
// @lc code=end
