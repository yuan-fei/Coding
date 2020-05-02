/*
 * @lc app=leetcode id=1408 lang=java
 *
 * [1408] String Matching in an Array
 *
 * https://leetcode.com/problems/string-matching-in-an-array/description/
 *
 * algorithms
 * Easy (59.93%)
 * Likes:    59
 * Dislikes: 26
 * Total Accepted:    13.4K
 * Total Submissions: 22.4K
 * Testcase Example:  '["mass","as","hero","superhero"]'
 *
 * Given an array of string words. Return all strings in words which is
 * substring of another word in any order. 
 * 
 * String words[i] is substring of words[j], if can be obtained removing some
 * characters to left and/or right side of words[j].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["mass","as","hero","superhero"]
 * Output: ["as","hero"]
 * Explanation: "as" is substring of "mass" and "hero" is substring of
 * "superhero".
 * ["hero","as"] is also a valid answer.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["leetcode","et","code"]
 * Output: ["et","code"]
 * Explanation: "et", "code" are substring of "leetcode".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: words = ["blue","green","bu"]
 * Output: []
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 30
 * words[i] contains only lowercase English letters.
 * It's guaranteed that words[i] will be unique.
 * 
 */

// @lc code=start
class Solution {
    public List<String> stringMatching(String[] words) {
    	List<String> res = new ArrayList<>();
        for(String w1 : words){
        	for (String w2 : words) {
        		if(!w1.equals(w2) && w2.indexOf(w1) != -1){
        			res.add(w1);
        			break;
        		}
        	}
        }
        return res;
    }
}
// @lc code=end
