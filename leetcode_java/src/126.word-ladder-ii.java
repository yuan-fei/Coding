/*
 * [126] Word Ladder II
 *
 * https://leetcode.com/problems/word-ladder-ii/description/
 *
 * algorithms
 * Hard (14.72%)
 * Total Accepted:    79.3K
 * Total Submissions: 537.8K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * 
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * all shortest transformation sequence(s) from beginWord to endWord, such
 * that:
 * 
 * 
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is
 * not a transformed word.
 * 
 * 
 * 
 * For example,
 * 
 * 
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * 
 * Return
 * 
 * ⁠ [
 * ⁠   ["hit","hot","dot","dog","cog"],
 * ⁠   ["hit","hot","lot","log","cog"]
 * ⁠ ]
 * 
 * 
 * 
 * 
 * Note:
 * 
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * 
 * 
 * 
 * 
 * UPDATE (2017/1/20):
 * The wordList parameter had been changed to a list of strings (instead of a
 * set of strings). Please reload the code definition to get the latest
 * changes.
 * 
 */
class Solution {
	static class Node{
		String s;
		List<Node> parents;
		Node(String s){
			this.s = s;
			parents = new ArrayList<Node>();
		}
	}
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Node endNode = buildLadder(beginWord, endWord, wordList);
        System.out.println(wordList.size());
        List<List<String>> resultSet = new ArrayList<List<String>>();
        if(endNode != null){
        	outputLadder(endNode, resultSet, new ArrayList<String>());	
        }
        return resultSet;
    }

    private void outputLadder(Node endNode, List<List<String>> resultSet, List<String> current){
    	current.add(endNode.s);
    	if(endNode.parents.size() == 0){
    		List<String> result = new ArrayList(current);
    		Collections.reverse(result);
    		resultSet.add(result);
    	}
    	else{
    		for (Node n : endNode.parents) {
    			outputLadder(n, resultSet, current);
    		}	
    	}
    	current.remove(current.size() - 1);	
    }

    private Node buildLadder(String beginWord, String endWord, List<String> wordList) {
    	Set<String> s = new HashSet<String>(wordList);
    	Map<String, Node> nodeMap = new HashMap<String, Node>();
        Queue<String> queue = new LinkedList<String>();
		
		nodeMap.put(beginWord, new Node(beginWord));
        queue.offer(beginWord);
        s.remove(beginWord);
        int length = 0;
        while(!queue.isEmpty()){
        	length++;
        	int size = queue.size();
        	List<String> removed = new ArrayList<String>();
	        for(int i = 0; i < size; i++){
	        	String word = queue.poll();
	        	Node wordNode = nodeMap.get(word);
	        	if(word.equals(endWord)){
	        		return wordNode;
	        	}
	        	
	        	for (String w: s) {
	        		if(isTransitable(w, word)){
	        			Node wNode = nodeMap.get(w);
	        			if(wNode == null){
	        				wNode = new Node(w);
	        				nodeMap.put(w, wNode);
	        				queue.offer(w);
	        				removed.add(w);
	        			}
	        			wNode.parents.add(wordNode);
	        		}
	        	}
	        }
	        s.removeAll(removed);
        }
        
        return null;
    }

    private boolean isTransitable(String s1, String s2){
    	int difference = 0;
    	for (int i = 0; i < s1.length(); i++) {
    		if(s1.charAt(i) != s2.charAt(i)){
    			difference++;
    		}
    		if(difference == 2){
    			return false;
    		}
    	}
    	return true;
    }
}
