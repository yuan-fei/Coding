/*
 * @lc app=leetcode id=520 lang=java
 *
 * [520] Detect Capital
 *
 * https://leetcode.com/problems/detect-capital/description/
 *
 * algorithms
 * Easy (54.01%)
 * Likes:    634
 * Dislikes: 278
 * Total Accepted:    167.8K
 * Total Submissions: 310.8K
 * Testcase Example:  '"USA"'
 *
 * Given a word, you need to judge whether the usage of capitals in it is right
 * or not.
 * 
 * We define the usage of capitals in a word to be right when one of the
 * following cases holds:
 * 
 * 
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 * 
 * Otherwise, we define that this word doesn't use capitals in a right way.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: "USA"
 * Output: True
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "FlaG"
 * Output: False
 * 
 * 
 * 
 * 
 * Note: The input will be a non-empty word consisting of uppercase and
 * lowercase latin letters.
 * 
 */

// @lc code=start
class Solution {
    public boolean detectCapitalUse(String word) {
    	if(word.isEmpty()){
    		return false;
    	}
        if(!Character.isUpperCase(word.charAt(0))){
        	return word.toLowerCase().equals(word);
        }
        else{
        	if(word.length() > 1){
        		if(Character.isUpperCase(word.charAt(1))){
        			return word.toUpperCase().equals(word);
        		}
        		else{
        			String s = word.substring(1);
        			return s.toLowerCase().equals(s);	
        		}
        	}
        	else{
        		return true;
        	}
        }
    }
}
// @lc code=end
