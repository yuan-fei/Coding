/*
 * @lc app=leetcode id=140 lang=java
 *
 * [140] Word Break II
 *
 * https://leetcode.com/problems/word-break-ii/description/
 *
 * algorithms
 * Hard (28.19%)
 * Total Accepted:    174.2K
 * Total Submissions: 617.6K
 * Testcase Example:  '"catsanddog"\n["cat","cats","and","sand","dog"]'
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, add spaces in s to construct a sentence where each word is
 * a valid dictionary word. Return all such possible sentences.
 * 
 * Note:
 * 
 * 
 * The same word in the dictionary may be reused multiple times in the
 * segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 * 
 */
class Solution {
	Map<Integer, List<String>> cache = new HashMap<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        List<String> ans = dfs(s, dict, 0);
        return ans;
    }

    List<String> dfs(String s, Set<String> dict, int pos){
    	if(!cache.containsKey(pos)){	
	    	if(pos == s.length()){
	    		return Arrays.asList("");
	    	}
	    	List<String> ans = new ArrayList<>();
	    	for(int i = pos + 1; i <= s.length(); i++){
	    		if(dict.contains(s.substring(pos, i))){
	    			List<String> subAns = dfs(s, dict, i);
	    			for (String sa : subAns) {
	    				ans.add(s.substring(pos, i)+(sa.isEmpty()?"":" ")+sa);
	    			}
	    		}
	    	}
	    	cache.put(pos, ans);
	    }
    	return cache.get(pos);
    }
}
