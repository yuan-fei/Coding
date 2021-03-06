/*
 * @lc app=leetcode id=1332 lang=java
 *
 * [1332] Remove Palindromic Subsequences
 *
 * https://leetcode.com/problems/remove-palindromic-subsequences/description/
 *
 * algorithms
 * Easy (45.39%)
 * Likes:    23
 * Dislikes: 85
 * Total Accepted:    3K
 * Total Submissions: 6.3K
 * Testcase Example:  '"ababa"'
 *
 * Given a string s consisting only of letters 'a' and 'b'. In a single step
 * you can remove one palindromic subsequence from s.
 * 
 * Return the minimum number of steps to make the given string empty.
 * 
 * A string is a subsequence of a given string, if it is generated by deleting
 * some characters of a given string without changing its order.
 * 
 * A string is called palindrome if is one that reads the same backward as well
 * as forward.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "ababa"
 * Output: 1
 * Explanation: String is already palindrome
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abb"
 * Output: 2
 * Explanation: "abb" -> "bb" -> "". 
 * Remove palindromic subsequence "a" then "bb".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "baabb"
 * Output: 2
 * Explanation: "baabb" -> "b" -> "". 
 * Remove palindromic subsequence "baab" then "b".
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = ""
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= s.length <= 1000
 * s only consists of letters 'a' and 'b'
 * 
 */

// @lc code=start
class Solution {
    public int removePalindromeSub(String s) {
    	if(s.equals("")){
    		return 0;
    	}
        else if(isPalindrome(s)){
        	return 1;
        }
        else{
        	return 2;
        }
    }

    boolean isPalindrome(String s){
    	for(int i = 0; i < s.length(); i++){
    		if(s.charAt(i) != s.charAt(s.length() - i - 1)){
    			return false;
    		}
    	}
    	return true;
    }
}
// @lc code=end
