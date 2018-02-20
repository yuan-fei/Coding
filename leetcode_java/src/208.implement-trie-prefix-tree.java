/*
 * [208] Implement Trie (Prefix Tree)
 *
 * https://leetcode.com/problems/implement-trie-prefix-tree/description/
 *
 * algorithms
 * Medium (30.36%)
 * Total Accepted:    102.9K
 * Total Submissions: 338K
 * Testcase Example:  '["Trie","search"]\n[[],["a"]]'
 *
 * 
 * Implement a trie with insert, search, and startsWith methods.
 * 
 * 
 * 
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 * 
 */
class Trie {

    static class TrieNode{
        TrieNode[] surfixes;
        boolean isWord;
        TrieNode(){
            surfixes = new TrieNode[26];
        }
    }

    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode tn = root;
        for (char c: word.toCharArray()) {
            if(tn.surfixes[c - 'a'] == null){
                tn.surfixes[c - 'a'] = new TrieNode();    
            }
            tn = tn.surfixes[c - 'a'];
        }
        tn.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode tn = root;
        for (char c: word.toCharArray()) {
            tn = tn.surfixes[c - 'a'];
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
            tn = tn.surfixes[c - 'a'];
            if(tn == null){
                return false;
            }
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
