/*
 * [139] Word Break
 *
 * https://leetcode.com/problems/word-break/description/
 *
 * algorithms
 * Medium (31.21%)
 * Total Accepted:    196K
 * Total Submissions: 627.8K
 * Testcase Example:  '"leetcode"\n["leet","code"]'
 *
 * 
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, determine if s can be segmented into a space-separated
 * sequence of one or more dictionary words. You may assume the dictionary does
 * not contain duplicate words.
 * 
 * 
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * 
 * 
 * 
 * Return true because "leetcode" can be segmented as "leet code".
 * 
 * 
 * 
 * UPDATE (2017/1/4):
 * The wordDict parameter had been changed to a list of strings (instead of a
 * set of strings). Please reload the code definition to get the latest
 * changes.
 * 
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
    	if(s == null || s.length() == 0){
    		return false;
    	}
        boolean[] isBreakable = new boolean[s.length() + 1];
        isBreakable[0] = true;
        Set dic = new HashSet();
        for (int i = 0; i< wordDict.size(); i++) {
        	dic.add(wordDict.get(i));
        }
        for (int i = 1; i < isBreakable.length; i++) {
        	for (int j = 0; j < i  ; j++) {
        		if(isBreakable[j] && dic.contains(s.substring(j, i))){
        			isBreakable[i] = true;
        			break;
        		}
        	}
        }
        return isBreakable[s.length()];
    }
}
