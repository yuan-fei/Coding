/*
 * @lc app=leetcode id=1032 lang=java
 *
 * [1032] Stream of Characters
 *
 * https://leetcode.com/problems/stream-of-characters/description/
 *
 * algorithms
 * Hard (42.07%)
 * Total Accepted:    6K
 * Total Submissions: 14.3K
 * Testcase Example:  '["StreamChecker","query","query","query","query","query","query","query","query","query","query","query","query"]\n[[["cd","f","kl"]],["a"],["b"],["c"],["d"],["e"],["f"],["g"],["h"],["i"],["j"],["k"],["l"]]'
 *
 * Implement the StreamChecker class as follows:
 * 
 * 
 * StreamChecker(words): Constructor, init the data structure with the given
 * words.
 * query(letter): returns true if and only if for some k >= 1, the last k
 * characters queried (in order from oldest to newest, including this letter
 * just queried) spell one of the words in the given list.
 * 
 * 
 * 
 * 
 * Example:
 * 
 * 
 * StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init
 * the dictionary.
 * streamChecker.query('a');          // return false
 * streamChecker.query('b');          // return false
 * streamChecker.query('c');          // return false
 * streamChecker.query('d');          // return true, because 'cd' is in the
 * wordlist
 * streamChecker.query('e');          // return false
 * streamChecker.query('f');          // return true, because 'f' is in the
 * wordlist
 * streamChecker.query('g');          // return false
 * streamChecker.query('h');          // return false
 * streamChecker.query('i');          // return false
 * streamChecker.query('j');          // return false
 * streamChecker.query('k');          // return false
 * streamChecker.query('l');          // return true, because 'kl' is in the
 * wordlist
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 2000
 * Words will only consist of lowercase English letters.
 * Queries will only consist of lowercase English letters.
 * The number of queries is at most 40000.
 * 
 * 
 */
class StreamChecker {

    TrieNode root;
    List<TrieNode> activeQueries;
    public StreamChecker(String[] words) {
        root = new TrieNode();
        for(String word: words){
            insert(root, word);
        }
        activeQueries = new ArrayList<>();
        activeQueries.add(root);
    }
    
    public boolean query(char l) {
        boolean ret=false;
        List<TrieNode> queries = new ArrayList<>();
        queries.add(root);
        
        for(int i = 0; i < activeQueries.size(); i++){
            TrieNode q = activeQueries.get(i);
            if(q.children[l-'a']!=null){
                if(q.children[l-'a'].isLeaf == true){
                    ret = true;
                }
                queries.add(q.children[l-'a']);    
            }
        }
        activeQueries = queries;
        // System.out.println(queries.size());
        return ret;
    }
    
    static class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isLeaf = false;
    }
    
    void insert(TrieNode root, String str){
        TrieNode last = root;        
        for(int i = 0; i < str.length(); i++){
            if(last.children[str.charAt(i)-'a']==null){
                last.children[str.charAt(i)-'a'] = new TrieNode();    
            }
            last = last.children[str.charAt(i)-'a'];
        }
        last.isLeaf = true;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
