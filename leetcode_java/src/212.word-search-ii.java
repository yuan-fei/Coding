/*
 * [212] Word Search II
 *
 * https://leetcode.com/problems/word-search-ii/description/
 *
 * algorithms
 * Hard (24.48%)
 * Total Accepted:    60.5K
 * Total Submissions: 246.5K
 * Testcase Example:  '[["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]\n["oath","pea","eat","rain"]'
 *
 * 
 * Given a 2D board and a list of words from the dictionary, find all words in
 * the board.
 * 
 * 
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once in a word.
 * 
 * 
 * 
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board = 
 * 
 * [
 * ⁠ ['o','a','a','n'],
 * ⁠ ['e','t','a','e'],
 * ⁠ ['i','h','k','r'],
 * ⁠ ['i','f','l','v']
 * ]
 * 
 * 
 * Return ["eat","oath"].
 * 
 * 
 * 
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 * 
 * 
 * click to show hint.
 * 
 * You would need to optimize your backtracking to pass the larger test. Could
 * you stop backtracking earlier?
 * 
 * If the current candidate does not exist in all words' prefix, you could stop
 * backtracking immediately. What kind of data structure could answer such
 * query efficiently? Does a hash table work? Why or why not? How about a Trie?
 * If you would like to learn how to implement a basic trie, please work on
 * this problem: Implement Trie (Prefix Tree) first.
 * 
 */
class Solution {
	static class TrieNode{
        TrieNode[] surfixes;
        boolean isWord;
        boolean isFound;
        TrieNode(){
            surfixes = new TrieNode[26];
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        boolean[][] visited = new boolean[board.length][board[0].length];
        List<String> resultSet = new ArrayList<String>();
        for (int i = 0; i < board.length; i++) {
        	for (int j = 0; j < board[0].length; j++) {
        		findWords(board, root, i, j, visited, ""+board[i][j], resultSet); 
        	}
        }
        Collections.sort(resultSet);
        return resultSet;
		
    }

    private void findWords(char[][] board, TrieNode root, int i, int j, boolean[][] visited, String partial, List<String> resultSet){
    	TrieNode tn = root.surfixes[board[i][j] - 'a'];
    	if(tn == null){
    		return;
    	}
		if(tn.isWord){
			if(!tn.isFound){
				resultSet.add(partial);
				tn.isFound = true;
			}
		}
		visited[i][j] = true;
		for(int row = i - 1; row <= i + 1; row++){
			for(int col = j - 1; col <= j + 1; col++){
				if(row >= 0 && row < board.length && col >=0 && col < board[0].length && (row == i || col == j) &&!visited[row][col]){
					findWords(board, tn, row, col, visited, partial + board[row][col], resultSet); 
				}
			}
		}
		visited[i][j] = false;
    }

    private TrieNode buildTrie(String[] words){
    	TrieNode root = new TrieNode();
    	for (String word : words) {
    		TrieNode tn = root;
    		for (char c : word.toCharArray()) {
    			if(tn.surfixes[c - 'a'] == null){
    				tn.surfixes[c - 'a'] = new TrieNode();
    			}
    			tn = tn.surfixes[c - 'a'];
    		}
    		tn.isWord = true;
    	}
    	return root;
    }
}
