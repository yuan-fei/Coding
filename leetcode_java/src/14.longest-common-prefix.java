/*
 * [14] Longest Common Prefix
 *
 * https://leetcode.com/problems/longest-common-prefix/description/
 *
 * algorithms
 * Easy (31.60%)
 * Total Accepted:    251K
 * Total Submissions: 793.3K
 * Testcase Example:  '[]'
 *
 * Write a function to find the longest common prefix string amongst an array
 * of strings.
 * 
 */
class Solution {
    public String longestCommonPrefix(String[] strs) {
    	if(strs == null || strs.length == 0){
    		return "";
    	}
    	String first = strs[0];
    	int i = 0;
        for (; i < first.length(); i++ ) {
        	if(!isMatch(first.charAt(i), i, strs)){
        		break;
        	}
        }
        return first.substring(0, i);
    }

    private boolean isMatch(char c, int pos, String[] strs){
    	for (String str : strs) {
    		if(str.length() < pos + 1 || str.charAt(pos) != c){
    			return false;
    		}
    	}
    	return true;
    }
}
