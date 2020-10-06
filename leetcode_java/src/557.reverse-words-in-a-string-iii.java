/*
 * @lc app=leetcode id=557 lang=java
 *
 * [557] Reverse Words in a String III
 *
 * https://leetcode.com/problems/reverse-words-in-a-string-iii/description/
 *
 * algorithms
 * Easy (70.63%)
 * Likes:    1174
 * Dislikes: 94
 * Total Accepted:    221.2K
 * Total Submissions: 313.1K
 * Testcase Example:  '"Let\'s take LeetCode contest"'
 *
 * Given a string, you need to reverse the order of characters in each word
 * within a sentence while still preserving whitespace and initial word order.
 * 
 * Example 1:
 * 
 * Input: "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * 
 * 
 * 
 * Note:
 * In the string, each word is separated by single space and there will not be
 * any extra space in the string.
 * 
 */

// @lc code=start
class Solution {
    public String reverseWords(String s) {
    	String ans = "";
    	int j = 0;
        for(int i = 0; i < s.length(); i++){
        	if(s.charAt(i) == ' '){
        		for(int k = i - 1; k >= j; k--){
        			ans += s.charAt(k);
        		}
        		ans += " ";
        		j = i + 1;
        	}
        }
        for(int k = s.length() - 1; k >= j; k--){
			ans += s.charAt(k);
		}
		return ans;
    }
}
// @lc code=end
