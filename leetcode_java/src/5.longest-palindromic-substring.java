/*
 * [5] Longest Palindromic Substring
 *
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 *
 * algorithms
 * Medium (25.16%)
 * Total Accepted:    283.3K
 * Total Submissions: 1.1M
 * Testcase Example:  '"babad"'
 *
 * Given a string s, find the longest palindromic substring in s. You may
 * assume that the maximum length of s is 1000.
 * 
 * Example:
 * 
 * 
 * Input: "babad"
 * 
 * Output: "bab"
 * 
 * Note: "aba" is also a valid answer.
 * 
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: "cbbd"
 * 
 * Output: "bb"
 * 
 * 
 * 
 */
class Solution {
    public String longestPalindrome(String s) {
    	boolean[][] isPalindrome = new boolean[s.length()][s.length()];
    	int maxLength = 0;
    	for (int i = 0; i < s.length(); i++) {
    	    isPalindrome[i][i] = true;
    	    if(i > 0){
    	    	isPalindrome[i - 1][i] = (s.charAt(i - 1) == s.charAt(i));
    	    	if(isPalindrome[i - 1][i]){
    	    		maxLength = 1;
    	    	}
    	    }
    	}
    	
    	for (int length = 2; length < s.length(); length++) {
    		for (int j = 0; j < s.length() - length; j++) {
    			isPalindrome[j][j + length] = (isPalindrome[j + 1][j + length - 1] && (s.charAt(j) == s.charAt(j + length)));
    			if(isPalindrome[j][j + length]){
    				maxLength = length;
    			}
    		}
    	}
    	for (int i = 0; i < s.length(); i++) {
    		if(isPalindrome[i][i + maxLength]){
    			return s.substring(i, i + maxLength + 1);
    		}
    	}
    	return "";
    }


}
