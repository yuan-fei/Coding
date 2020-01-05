/*
 * @lc app=leetcode id=1309 lang=java
 *
 * [1309] Decrypt String from Alphabet to Integer Mapping
 *
 * https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping/description/
 *
 * algorithms
 * Easy (78.08%)
 * Likes:    19
 * Dislikes: 2
 * Total Accepted:    4.6K
 * Total Submissions: 5.8K
 * Testcase Example:  '"10#11#12"'
 *
 * Given a string s formed by digits ('0' - '9') and '#' . We want to map s to
 * English lowercase characters as follows:
 * 
 * 
 * Characters ('a' to 'i') are represented by ('1' to '9') respectively.
 * Characters ('j' to 'z') are represented by ('10#' to '26#') respectively. 
 * 
 * 
 * Return the string formed after mapping.
 * 
 * It's guaranteed that a unique mapping will always exist.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "10#11#12"
 * Output: "jkab"
 * Explanation: "j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "1326#"
 * Output: "acz"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "25#"
 * Output: "y"
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
 * Output: "abcdefghijklmnopqrstuvwxyz"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 1000
 * s[i] only contains digits letters ('0'-'9') and '#' letter.
 * s will be valid string such that mapping is always possible.
 * 
 */

// @lc code=start
class Solution {
    public String freqAlphabets(String s) {
        String ans = "";
    	int last = 0;
    	for(int i = 0; i < s.length(); i++){
    		if(s.charAt(i) == '#'){
    			ans += freqAlphabetsSimple(s, last, i - 3);
    			ans += (char)(Integer.parseInt(s.substring(i - 2, i)) - 10 + 'j');
    			last = i + 1;
    		}
    	}
    	ans += freqAlphabetsSimple(s, last, s.length() - 1);
    	return ans;
    }

    private String freqAlphabetsSimple(String s, int start, int end){
    	String ans = "";
    	for(int i = start; i <= end; i++){
    		ans += (char)(s.charAt(i) - '1' + 'a');
    	}
    	return ans;
    }
}
// @lc code=end
