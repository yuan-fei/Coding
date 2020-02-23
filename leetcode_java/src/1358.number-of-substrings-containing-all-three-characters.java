/*
 * @lc app=leetcode id=1358 lang=java
 *
 * [1358] Number of Substrings Containing All Three Characters
 *
 * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/
 *
 * algorithms
 * Medium (43.52%)
 * Likes:    51
 * Dislikes: 0
 * Total Accepted:    2K
 * Total Submissions: 4.5K
 * Testcase Example:  '"abcabc"'
 *
 * Given a string s consisting only of characters a, b and c.
 * 
 * Return the number of substrings containing at least one occurrence of all
 * these characters a, b and c.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abcabc"
 * Output: 10
 * Explanation: The substrings containing at least one occurrence of the
 * characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab",
 * "bcabc", "cab", "cabc" and "abc" (again). 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aaacb"
 * Output: 3
 * Explanation: The substrings containing at least one occurrence of the
 * characters a, b and c are "aaacb", "aacb" and "acb". 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "abc"
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= s.length <= 5 x 10^4
 * s only consists of a, b or c characters.
 * 
 */

// @lc code=start
class Solution {
    public int numberOfSubstrings(String s) {
        int[] cnt = new int[3];
        int n = s.length();
        int right = 0;
        int total = 0;
        for(int i = 0; i < n; i++){
        	if(i > 0){
        		cnt[s.charAt(i - 1) - 'a']--;	
        	}
        	while(!containsAll(cnt) && right < n){
        		cnt[s.charAt(right) - 'a']++;
        		right++;
        	}
        	if(containsAll(cnt)){
        		total += n - right + 1;
        	}
        }
        return total;
    }

    boolean containsAll(int[] cnt){
    	for (int x : cnt) {
    		if(x == 0){
    			return false;
    		}
    	}
    	return true;
    }
}
// @lc code=end
