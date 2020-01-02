/*
 * @lc app=leetcode id=211 lang=java
 *
 * [211] Add and Search Word - Data structure design
 *
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/description/
 *
 * algorithms
 * Medium (33.23%)
 * Likes:    1182
 * Dislikes: 65
 * Total Accepted:    143.6K
 * Total Submissions: 431.8K
 * Testcase Example:  '["WordDictionary","addWord","addWord","addWord","search","search","search","search"]\n[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]'
 *
 * Design a data structure that supports the following two operations:
 * 
 * 
 * void addWord(word)
 * bool search(word)
 * 
 * 
 * search(word) can search a literal word or a regular expression string
 * containing only letters a-z or .. A . means it can represent any one
 * letter.
 * 
 * Example:
 * 
 * 
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * 
 * 
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 * 
 */

// @lc code=start
class WordDictionary {

	class TrieNode{
		TrieNode[] children = new TrieNode[26];
		boolean isWord = false;
	}

	TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
    	TrieNode cur = root;
        for(char c : word.toCharArray()){
        	if(cur.children[c - 'a'] == null){
        		cur.children[c - 'a'] = new TrieNode();
        	}
        	cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word.toCharArray(), 0, root);
    }

    private boolean search(char[] word, int pos, TrieNode t){
    	if(t == null){
    		return false;
    	}
    	if(pos == word.length){
    		if(t.isWord){
    			return true;	
    		}
    		else{
    			return false;
    		}
    	}
    	if(word[pos] == '.'){
	    	for(TrieNode child : t.children){
	    		if(child != null && search(word, pos + 1, child)){
	    			return true;
	    		}
	    	}
	    	return false;	
    	}
    	else{
    		return search(word, pos + 1, t.children[word[pos] - 'a']);
    	}
    	
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
// @lc code=end
