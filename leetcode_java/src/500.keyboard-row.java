/*
 * @lc app=leetcode id=500 lang=java
 *
 * [500] Keyboard Row
 *
 * https://leetcode.com/problems/keyboard-row/description/
 *
 * algorithms
 * Easy (64.71%)
 * Likes:    552
 * Dislikes: 673
 * Total Accepted:    112.8K
 * Total Submissions: 174.3K
 * Testcase Example:  '["Hello","Alaska","Dad","Peace"]'
 *
 * Given a List of words, return the words that can be typed using letters of
 * alphabet on only one row's of American keyboard like the image
 * below.
 * 
 * 
 * 
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: ["Hello", "Alaska", "Dad", "Peace"]
 * Output: ["Alaska", "Dad"]
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * You may use one character in the keyboard more than once.
 * You may assume the input string will only contain letters of alphabet.
 * 
 * 
 */

// @lc code=start
class Solution {
	String[] lines = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
    public String[] findWords(String[] words) {
    	List<String> ans = new ArrayList<>();
    	for(String s : words){
    		String ls = s.toLowerCase();
    		int ln = -1;
    		boolean sameLine = true;
    		for(char c : ls.toCharArray()){
    			if(ln == -1){
    				ln = getLine(c);

    			}
    			else{
    				if(ln != getLine(c)){
						sameLine = false;
						break;
    				}
    			}
    		}
    		if(sameLine){
    			ans.add(s);	
    		}
    		
    	}
    	return ans.toArray(new String[0]);
    }

    int getLine(char c){
    	for(int i = 0; i < lines.length; i++){
    		if(lines[i].indexOf(c) != -1){
    			// System.out.println(c + " = " + i);
    			return i;
    		}
    	}
    	return -1;
    }
}
// @lc code=end
