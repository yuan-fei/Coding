/*
 * @lc app=leetcode id=1839 lang=java
 *
 * [1839] Longest Substring Of All Vowels in Order
 *
 * https://leetcode.com/problems/longest-substring-of-all-vowels-in-order/description/
 *
 * algorithms
 * Medium (46.76%)
 * Likes:    61
 * Dislikes: 4
 * Total Accepted:    5.4K
 * Total Submissions: 11.6K
 * Testcase Example:  '"aeiaaioaaaaeiiiiouuuooaauuaeiu"'
 *
 * A string is considered beautiful if it satisfies the following
 * conditions:
 * 
 * 
 * Each of the 5 English vowels ('a', 'e', 'i', 'o', 'u') must appear at least
 * once in it.
 * The letters must be sorted in alphabetical order (i.e. all 'a's before 'e's,
 * all 'e's before 'i's, etc.).
 * 
 * 
 * For example, strings "aeiou" and "aaaaaaeiiiioou" are considered beautiful,
 * but "uaeio", "aeoiu", and "aaaeeeooo" are not beautiful.
 * 
 * Given a string word consisting of English vowels, return the length of the
 * longest beautiful substring of word. If no such substring exists, return 0.
 * 
 * A substring is a contiguous sequence of characters in a string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: word = "aeiaaioaaaaeiiiiouuuooaauuaeiu"
 * Output: 13
 * Explanation: The longest beautiful substring in word is "aaaaeiiiiouuu" of
 * length 13.
 * 
 * Example 2:
 * 
 * 
 * Input: word = "aeeeiiiioooauuuaeiou"
 * Output: 5
 * Explanation: The longest beautiful substring in word is "aeiou" of length
 * 5.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: word = "a"
 * Output: 0
 * Explanation: There is no beautiful substring, so return 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= word.length <= 5 * 10^5
 * word consists of characters 'a', 'e', 'i', 'o', and 'u'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestBeautifulSubstring(String word) {
    	int n = word.length();
    	int left = -1;
    	int ans = 0;
    	Map<Character, Integer> m = new HashMap<>();
    	m.put('a', 0);
    	m.put('e', 1);
    	m.put('i', 2);
    	m.put('o', 3);
    	m.put('u', 4);
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < n; i++){
    		sb.append(m.get(word.charAt(i)));
    	}
    	word = sb.toString();
    	// System.out.println(word);
    	char expect = '0';
        for(int i = 0; i < n; i++){
        	if(word.charAt(i) == expect){
        		if(expect == '0'){
        			left = i;
        		}
        		expect++;
        	}
        	else if(expect - word.charAt(i) != 1){
        		if(expect == '5'){
        			ans = Math.max(ans, i - left);	
        		}
        		if(word.charAt(i) == '0'){
        			expect = '1';
        			left = i;
        		}
        		else{
        			expect = '0';
        			left = -1;
        		}
        	}
        }
        if(expect == '5'){
			ans = Math.max(ans, n - left);	
		}
        return ans;
    }
}
// @lc code=end
