/*
 * @lc app=leetcode id=1417 lang=java
 *
 * [1417] Reformat The String
 *
 * https://leetcode.com/problems/reformat-the-string/description/
 *
 * algorithms
 * Easy (55.91%)
 * Likes:    51
 * Dislikes: 25
 * Total Accepted:    12K
 * Total Submissions: 21.4K
 * Testcase Example:  '"a0b1c2"'
 *
 * Given alphanumeric string s. (Alphanumeric string is a string consisting of
 * lowercase English letters and digits).
 * 
 * You have to find a permutation of the string where no letter is followed by
 * another letter and no digit is followed by another digit. That is, no two
 * adjacent characters have the same type.
 * 
 * Return the reformatted string or return an empty string if it is impossible
 * to reformat the string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "a0b1c2"
 * Output: "0a1b2c"
 * Explanation: No two adjacent characters have the same type in "0a1b2c".
 * "a0b1c2", "0a1b2c", "0c2a1b" are also valid permutations.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "leetcode"
 * Output: ""
 * Explanation: "leetcode" has only characters so we cannot separate them by
 * digits.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "1229857369"
 * Output: ""
 * Explanation: "1229857369" has only digits so we cannot separate them by
 * characters.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "covid2019"
 * Output: "c2o0v1i9d"
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: s = "ab123"
 * Output: "1a2b3"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 500
 * s consists of only lowercase English letters and/or digits.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String reformat(String s) {
        String letters = "";
        String digits = "";
        for(char c : s.toCharArray()){
        	if(Character.isDigit(c)){
        		digits += c;
        	}
        	else{
        		letters += c;	
        	}
        }
        if(letters.length() == digits.length() - 1){
        	return shuffle(digits, letters);
        }
        if(letters.length() == digits.length() + 1){
        	return shuffle(letters, digits);	
        }
        if(letters.length() == digits.length()){
        	return shuffle(letters, digits);	
        }
        return "";
    }

	String shuffle(String l, String s){
		String ret = "";
		for(int i = 0; i < s.length(); i++){
			ret += l.charAt(i);
			ret += s.charAt(i);
		}
		if(l.length() > s.length()){
			ret += l.charAt(l.length() - 1);
		}
		return ret;
	}
}
// @lc code=end
