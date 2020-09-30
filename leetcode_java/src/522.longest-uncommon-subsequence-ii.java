/*
 * @lc app=leetcode id=522 lang=java
 *
 * [522] Longest Uncommon Subsequence II
 *
 * https://leetcode.com/problems/longest-uncommon-subsequence-ii/description/
 *
 * algorithms
 * Medium (33.94%)
 * Likes:    180
 * Dislikes: 611
 * Total Accepted:    22.6K
 * Total Submissions: 66.6K
 * Testcase Example:  '["aba","cdc","eae"]'
 *
 * 
 * Given a list of strings, you need to find the longest uncommon subsequence
 * among them. The longest uncommon subsequence is defined as the longest
 * subsequence of one of these strings and this subsequence should not be any
 * subsequence of the other strings.
 * 
 * 
 * 
 * A subsequence is a sequence that can be derived from one sequence by
 * deleting some characters without changing the order of the remaining
 * elements. Trivially, any string is a subsequence of itself and an empty
 * string is a subsequence of any string.
 * 
 * 
 * 
 * The input will be a list of strings, and the output needs to be the length
 * of the longest uncommon subsequence. If the longest uncommon subsequence
 * doesn't exist, return -1.
 * 
 * 
 * Example 1:
 * 
 * Input: "aba", "cdc", "eae"
 * Output: 3
 * 
 * 
 * 
 * Note:
 * 
 * All the given strings' lengths will not exceed 10.
 * The length of the given list will be in the range of [2, 50].
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findLUSlength(String[] strs) {
        TreeMap<String, Integer> tm = new TreeMap<>((a, b) -> Integer.compare(b.length(), a.length()) == 0 ? a.compareTo(b) : Integer.compare(b.length(), a.length()));
        for(String s : strs){
        	int cnt = tm.getOrDefault(s, 0) + 1;
        	tm.put(s, cnt);
        }
        // System.out.println(tm);
        for(String t : tm.keySet()){
        	if(tm.get(t) == 1){
        		for(String s : tm.keySet()){
        			if(s.equals(t)){
        				return t.length();
        			}
        			if(isSubsequence(s, t)){
        				break;
        			}
        		}
        	}
        }
        return -1;
    }

    boolean isSubsequence(String s, String t){
    	int j = 0;
    	for(int i = 0; i < s.length(); i++){
    		if(s.charAt(i) == t.charAt(j)){
    			j++;
    			if(j == t.length()){
    				return true;
    			}
    		}
    	}
    	return false;
    }
}
// @lc code=end
