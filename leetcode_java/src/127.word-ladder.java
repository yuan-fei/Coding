/*
 * [127] Word Ladder
 *
 * https://leetcode.com/problems/word-ladder/description/
 *
 * algorithms
 * Medium (19.89%)
 * Total Accepted:    154.8K
 * Total Submissions: 777K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * 
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * the length of shortest transformation sequence from beginWord to endWord,
 * such that:
 * 
 * 
 * Only one letter can be changed at a time.
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
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * 
 * 
 * 
 * Note:
 * 
 * Return 0 if there is no such transformation sequence.
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
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    	Set<String> s = new HashSet<String>(wordList);
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        int length = 0;
        while(!queue.isEmpty()){
        	length++;
        	int size = queue.size();
	        for(int i = 0; i < size; i++){
	        	String word = queue.poll();
	        	if(word.equals(endWord)){
	        		return length;
	        	}
	        	List<String> removed = new ArrayList<String>();
	        	for (String w: s) {
	        		if(isTransitable(w, word)){
	        			queue.offer(w);
	        			removed.add(w);
	        		}
	        	}
	        	s.removeAll(removed);
	        }
        }
        
        return 0;
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
