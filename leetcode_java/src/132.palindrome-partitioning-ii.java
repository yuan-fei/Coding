/*
 * [132] Palindrome Partitioning II
 *
 * https://leetcode.com/problems/palindrome-partitioning-ii/description/
 *
 * algorithms
 * Hard (24.70%)
 * Total Accepted:    78.9K
 * Total Submissions: 319.1K
 * Testcase Example:  '"aab"'
 *
 * 
 * Given a string s, partition s such that every substring of the partition is
 * a palindrome.
 * 
 * 
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * 
 * 
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced
 * using 1 cut.
 * 
 */
class Solution {
    public int minCut(String s) {
    	if(s == null || s.length() == 0){
    		return 0;
    	}
    	boolean[][] pTable = getPalindromeTable(s);
        int[] state = new int[s.length() + 1];
        state[0] = 0;
        for (int i = 1; i <= s.length(); i++) {
        	state[i] = Integer.MAX_VALUE;
        	for(int j = 0; j < i; j++){
        		if(state[j] != Integer.MAX_VALUE && pTable[j][i - 1]){
        			state[i] = Math.min(state[i], state[j] + 1);	
        		}
        	}
        }
        return state[s.length()] - 1;
    }

    private boolean[][] getPalindromeTable(String s){
    	boolean[][] ret = new boolean[s.length()][s.length()];
    	for (int i = 0 ; i < s.length(); i++) {
    		ret[i][i] = true;
    		if(i > 0){
    			ret[i - 1][i] = (s.charAt(i - 1) == s.charAt(i));	
    		}
    	}

    	for (int length = 2; length < s.length(); length++) {
    		for (int i = 0; i < s.length() - length; i++) {
    			ret[i][i + length] = (ret[i + 1][i + length - 1] && s.charAt(i) == s.charAt(i + length));
    		}
    	}
    	return ret;
    }
}
