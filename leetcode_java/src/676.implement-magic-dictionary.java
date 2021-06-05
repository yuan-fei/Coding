/*
 * @lc app=leetcode id=676 lang=java
 *
 * [676] Implement Magic Dictionary
 *
 * https://leetcode.com/problems/implement-magic-dictionary/description/
 *
 * algorithms
 * Medium (55.51%)
 * Likes:    753
 * Dislikes: 163
 * Total Accepted:    50.6K
 * Total Submissions: 91.3K
 * Testcase Example:  '["MagicDictionary", "buildDict", "search", "search", "search", "search"]\n' +
  '[[], [["hello","leetcode"]], ["hello"], ["hhllo"], ["hell"], ' +
  '["leetcoded"]]'
 *
 * Design a data structure that is initialized with a list of different words.
 * Provided a string, you should determine if you can change exactly one
 * character in this string to match any word in the data structure.
 * 
 * Implement the MagicDictionary class:
 * 
 * 
 * MagicDictionary() Initializes the object.
 * void buildDict(String[] dictionary) Sets the data structure with an array of
 * distinct strings dictionary.
 * bool search(String searchWord) Returns true if you can change exactly one
 * character in searchWord to match any string in the data structure, otherwise
 * returns false.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * Output
 * [null, null, false, true, false, false]
 * 
 * Explanation
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // return False
 * magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to
 * match "hello" so we return True
 * magicDictionary.search("hell"); // return False
 * magicDictionary.search("leetcoded"); // return False
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= dictionary.length <= 100
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] consists of only lower-case English letters.
 * All the strings in dictionary are distinct.
 * 1 <= searchWord.length <= 100
 * searchWord consists of only lower-case English letters.
 * buildDict will be called only once before search.
 * At most 100 calls will be made to search.
 * 
 * 
 */

// @lc code=start
class MagicDictionary {
	Set<String> set = new HashSet<>();
    /** Initialize your data structure here. */
    public MagicDictionary() {
        
    }
    
    public void buildDict(String[] dictionary) {
        for(String s : dictionary){
        	set.add(s);	
        }
    }
    
    public boolean search(String s) {
    	for(int i = 0; i < s.length(); i++){
    		for(char c = 'a'; c <= 'z'; c++){
    			if(c != s.charAt(i)){
        			String tmp = s.substring(0, i) + c + s.substring(i + 1);
        			if(set.contains(tmp)){
        				return true;
        			}
    			}
    		}
    	}
        return false;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
// @lc code=end
