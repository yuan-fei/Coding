/*
 * @lc app=leetcode id=472 lang=java
 *
 * [472] Concatenated Words
 *
 * https://leetcode.com/problems/concatenated-words/description/
 *
 * algorithms
 * Hard (42.47%)
 * Likes:    647
 * Dislikes: 97
 * Total Accepted:    55.4K
 * Total Submissions: 130.4K
 * Testcase Example:  '["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]'
 *
 * Given a list of words (without duplicates), please write a program that
 * returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at
 * least two shorter words in the given array.
 * 
 * Example:
 * 
 * Input:
 * ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * 
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * 
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; "ratcatdogcat"
 * can be concatenated by "rat", "cat", "dog" and "cat".
 * 
 * 
 * 
 * Note:
 * 
 * The number of elements of the given array will not exceed 10,000 
 * The length sum of elements in the given array will not exceed 600,000. 
 * All the input string will only include lower case letters.
 * The returned elements order does not matter. 
 * 
 * 
 */

// @lc code=start
class Solution {
	TrieNode root = new TrieNode();
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
    	List<String> res = new ArrayList<>();
    	Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
    	for(String w : words){
    		if(check(w)){
    			res.add(w);
    		}
    		else{
    			insert(w);
    		}
    	}
    	return res;
    }

    boolean check(String w){
    	return dfs(w, 0);
    }

    boolean dfs(String w, int pos){
    	if(pos == w.length()){
    		return !w.isEmpty();
    	}
    	TrieNode tn = root;
    	for(int i = pos; i < w.length(); i++){
    		char c = w.charAt(i);
    		tn = tn.surffixes[c - 'a'];
    		if(tn != null && tn.isWord && dfs(w, i + 1)){
    			return true;
    		}
            if(tn == null){
            	return false;
            }
    	}
    	return false;
    }

    static class TrieNode{
        TrieNode[] surffixes;
        boolean isWord;
        TrieNode(){
            surffixes = new TrieNode[26];
        }
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode tn = root;
        for (char c: word.toCharArray()) {
            if(tn.surffixes[c - 'a'] == null){
                tn.surffixes[c - 'a'] = new TrieNode();    
            }
            tn = tn.surffixes[c - 'a'];
        }
        tn.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode tn = root;
        for (char c: word.toCharArray()) {
            tn = tn.surffixes[c - 'a'];
            if(tn == null){
                return false;
            }
        }
        return tn.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode tn = root;
        for (char c: prefix.toCharArray()) {
            tn = tn.surffixes[c - 'a'];
            if(tn == null){
                return false;
            }
        }
        return true;
    }
}
// @lc code=end
