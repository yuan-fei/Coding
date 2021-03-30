/*
 * @lc app=leetcode id=1807 lang=java
 *
 * [1807] Evaluate the Bracket Pairs of a String
 *
 * https://leetcode.com/problems/evaluate-the-bracket-pairs-of-a-string/description/
 *
 * algorithms
 * Medium (67.12%)
 * Likes:    26
 * Dislikes: 2
 * Total Accepted:    4.3K
 * Total Submissions: 6.4K
 * Testcase Example:  '"(name)is(age)yearsold"\n[["name","bob"],["age","two"]]'
 *
 * You are given a string s that contains some bracket pairs, with each pair
 * containing a non-empty key.
 * 
 * 
 * For example, in the string "(name)is(age)yearsold", there are two bracket
 * pairs that contain the keys "name" and "age".
 * 
 * 
 * You know the values of a wide range of keys. This is represented by a 2D
 * string array knowledge where each knowledge[i] = [keyi, valuei] indicates
 * that key keyi has a value of valuei.
 * 
 * You are tasked to evaluate all of the bracket pairs. When you evaluate a
 * bracket pair that contains some key keyi, you will:
 * 
 * 
 * Replace keyi and the bracket pair with the key's corresponding valuei.
 * If you do not know the value of the key, you will replace keyi and the
 * bracket pair with a question mark "?" (without the quotation marks).
 * 
 * 
 * Each key will appear at most once in your knowledge. There will not be any
 * nested brackets in s.
 * 
 * Return the resulting string after evaluating all of the bracket pairs.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "(name)is(age)yearsold", knowledge =
 * [["name","bob"],["age","two"]]
 * Output: "bobistwoyearsold"
 * Explanation:
 * The key "name" has a value of "bob", so replace "(name)" with "bob".
 * The key "age" has a value of "two", so replace "(age)" with "two".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "hi(name)", knowledge = [["a","b"]]
 * Output: "hi?"
 * Explanation: As you do not know the value of the key "name", replace
 * "(name)" with "?".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "(a)(a)(a)aaa", knowledge = [["a","yes"]]
 * Output: "yesyesyesaaa"
 * Explanation: The same key can appear multiple times.
 * The key "a" has a value of "yes", so replace all occurrences of "(a)" with
 * "yes".
 * Notice that the "a"s not in a bracket pair are not evaluated.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "(a)(b)", knowledge = [["a","b"],["b","a"]]
 * Output: "ba"
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * 0 <= knowledge.length <= 10^5
 * knowledge[i].length == 2
 * 1 <= keyi.length, valuei.length <= 10
 * s consists of lowercase English letters and round brackets '(' and ')'.
 * Every open bracket '(' in s will have a corresponding close bracket ')'.
 * The key in each bracket pair of s will be non-empty.
 * There will not be any nested bracket pairs in s.
 * keyi and valuei consist of lowercase English letters.
 * Each keyi in knowledge is unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
    	int n = s.length();
    	Map<String, String> m = new HashMap<>();
    	for(List<String> k : knowledge){
    		m.put(k.get(0), k.get(1));
    	}
    	StringBuilder sb = new StringBuilder();
    	int left = -1;
        for(int i = 0; i < n; i++){
        	if(s.charAt(i) == '('){
        		left = i;
        	}
        	else if(s.charAt(i) == ')'){
        		String sub = s.substring(left + 1, i);
        		if(m.containsKey(sub)){
        			sb.append(m.get(sub));
        		}
        		else{
        			sb.append('?');
        		}
        		left = -1;
        	}
        	else{
        		if(left == -1){
        			sb.append(s.charAt(i));
        		}
        	}
        }
        return sb.toString();
    }
}
// @lc code=end
