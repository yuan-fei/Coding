/*
 * @lc app=leetcode id=1316 lang=java
 *
 * [1316] Distinct Echo Substrings
 *
 * https://leetcode.com/problems/distinct-echo-substrings/description/
 *
 * algorithms
 * Hard (36.78%)
 * Likes:    16
 * Dislikes: 39
 * Total Accepted:    1.3K
 * Total Submissions: 3.6K
 * Testcase Example:  '"abcabcabc"'
 *
 * Return the number of distinct non-empty substrings of text that can be
 * written as the concatenation of some string with itself.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: text = "abcabcabc"
 * Output: 3
 * Explanation: The 3 substrings are "abcabc", "bcabca" and "cabcab".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: text = "leetcodeleetcode"
 * Output: 2
 * Explanation: The 2 substrings are "ee" and "leetcodeleetcode".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= text.length <= 2000
 * text has only lowercase English letters.
 * 
 */

// @lc code=start
class Solution {
	final long fact = 1000000007;
    public int distinctEchoSubstrings(String text) {
    	int n = text.length();
        long[][] hash = new long[n][n];
        for(int i = 0; i < n; i++){
        	long h = 0;
        	for(int j = i; j < n; j++){
        		hash[i][j] = h * fact + text.charAt(j);
        		h = hash[i][j];
        	}
        }
        Set<Long> s = new HashSet<>();
        int cnt = 0;
        for(int i = 0; i < n; i++){
        	for(int l = 1; i + 2 * l - 1 < n; l++){
        		if(hash[i][i + l - 1] == hash[i + l][i + 2 * l - 1] && s.add(hash[i][i + 2 * l - 1])){
        			cnt++;
        		}
        	}
        }
        return cnt;
    }
}
// @lc code=end
