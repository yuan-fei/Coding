/*
 * [115] Distinct Subsequences
 *
 * https://leetcode.com/problems/distinct-subsequences/description/
 *
 * algorithms
 * Hard (32.11%)
 * Total Accepted:    80.3K
 * Total Submissions: 249.9K
 * Testcase Example:  '""\n"a"'
 *
 * 
 * Given a string S and a string T, count the number of distinct subsequences
 * of S which equals T.
 * 
 * 
 * 
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, "ACE" is a
 * subsequence of "ABCDE" while "AEC" is not).
 * 
 * 
 * 
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 * 
 * 
 * Return 3.
 * 
 */
class Solution {
    public int numDistinct(String s, String t) {
    	if(s == null || t == null){
    		return 0;
    	}
        int[][] state = new int[s.length() + 1][t.length() + 1];
        state[0][0] = 1;
        for (int i = 1; i <= s.length(); i++) {
        	state[i][0] = 1;
        }
        for (int j = 1; j <= t.length(); j++) {
        	state[0][j] = 0;
        }

        for (int i = 1; i <= s.length(); i++) {
	        for (int j = 1; j <= t.length(); j++) {
	        	if(state[i - 1][j - 1] > 0 && s.charAt(i - 1) == t.charAt(j - 1)){
	        		state[i][j] = state[i - 1][j - 1];	
	        	}
	        	state[i][j] += state[i - 1][j];	
	        }
    	}
    	return state[s.length()][t.length()];
    }
}
