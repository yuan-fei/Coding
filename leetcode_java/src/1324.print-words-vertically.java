/*
 * @lc app=leetcode id=1324 lang=java
 *
 * [1324] Print Words Vertically
 *
 * https://leetcode.com/problems/print-words-vertically/description/
 *
 * algorithms
 * Medium (59.25%)
 * Likes:    37
 * Dislikes: 24
 * Total Accepted:    6K
 * Total Submissions: 10.1K
 * Testcase Example:  '"HOW ARE YOU"'
 *
 * Given a string s. Return all the words vertically in the same order in which
 * they appear in s.
 * Words are returned as a list of strings, complete with spaces when is
 * necessary. (Trailing spaces are not allowed).
 * Each word would be put on only one column and that in one column there will
 * be only one word.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "HOW ARE YOU"
 * Output: ["HAY","ORO","WEU"]
 * Explanation: Each word is printed vertically. 
 * ⁠"HAY"
 * "ORO"
 * "WEU"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "TO BE OR NOT TO BE"
 * Output: ["TBONTB","OEROOE","   T"]
 * Explanation: Trailing spaces is not allowed. 
 * "TBONTB"
 * "OEROOE"
 * "   T"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "CONTEST IS COMING"
 * Output: ["CIC","OSO","N M","T I","E N","S G","T"]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 200
 * s contains only upper case English letters.
 * It's guaranteed that there is only one space between 2 words.
 * 
 */

// @lc code=start
class Solution {
    public List<String> printVertically(String s) {
        String[] words = s.split(" ");
        int count = 0;
        for(int i = 0; i < words.length; i++){
        	count = Math.max(count, words[i].length());
        }
        List<String> ans = new ArrayList<>();
        for(int i = 0; i < count; i++){
        	String t = "";
        	int lastNonSpace = -1;
        	for(int j = 0; j < words.length; j++){
        		String w = words[j];
        		if(w.length() > i){
        			t += w.charAt(i);
        			lastNonSpace = j;
        		}
        		else{
        			t += " ";
        		}
        	}
        	ans.add(t.substring(0, lastNonSpace + 1));
        }
        return ans;
    }
}
// @lc code=end
