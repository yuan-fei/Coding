/*
 * [97] Interleaving String
 *
 * https://leetcode.com/problems/interleaving-string/description/
 *
 * algorithms
 * Hard (25.08%)
 * Total Accepted:    80.4K
 * Total Submissions: 320.4K
 * Testcase Example:  '""\n""\n""'
 *
 * 
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and
 * s2.
 * 
 * 
 * 
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 * 
 * 
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 * 
 */
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
    	if(s1 == null || s2 == null || s3 == null){
    		return false;
    	}
    	if(s1.length() + s2.length() != s3.length()){
    		return false;
    	}
        boolean[][] state = new boolean[s1.length() + 1][s2. length() + 1];
        state[0][0] = true;
        for (int i = 1; i <= s1.length(); i++) {
        	state[i][0] = (state[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1)));
        }
        for (int i = 1; i <= s2.length(); i++) {
        	state[0][i] = (state[0][i - 1] && (s2.charAt(i - 1) == s3.charAt(i - 1)));
        }
        for (int i = 1; i <= s1.length(); i++) {
        	for (int j = 1; j <= s2.length(); j++) {
        		if(state[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)){
        			state[i][j] = true;
        		}
        		else if(state[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)){
        			state[i][j] = true;
        		}
        		else{
        			state[i][j] = false;
        		}
        		
        	}
        }
        return state[s1.length()][s2.length()];
    }
}
