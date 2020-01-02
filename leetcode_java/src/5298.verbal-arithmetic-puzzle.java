/*
 * @lc app=leetcode id=5298 lang=java
 *
 * [5298] Verbal Arithmetic Puzzle
 *
 * https://leetcode.com/problems/verbal-arithmetic-puzzle/description/
 *
 * algorithms
 * Hard (26.27%)
 * Likes:    40
 * Dislikes: 20
 * Total Accepted:    1.1K
 * Total Submissions: 3.9K
 * Testcase Example:  '["SEND","MORE"]\n"MONEY"'
 *
 * Given an equation, represented by words on left side and the result on right
 * side.
 * 
 * You need to check if the equation is solvable under the following
 * rules:
 * 
 * 
 * Each character is decoded as one digit (0 - 9).
 * Every pair of different characters they must map to different digits.
 * Each words[i] and result are decoded as one number without leading
 * zeros.
 * Sum of numbers on left side (words) will equal to the number on right side
 * (result). 
 * 
 * 
 * Return True if the equation is solvable otherwise return False.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["SEND","MORE"], result = "MONEY"
 * Output: true
 * Explanation: Map 'S'-> 9, 'E'->5, 'N'->6, 'D'->7, 'M'->1, 'O'->0, 'R'->8,
 * 'Y'->'2'
 * Such that: "SEND" + "MORE" = "MONEY" ,  9567 + 1085 = 10652
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
 * Output: true
 * Explanation: Map 'S'-> 6, 'I'->5, 'X'->0, 'E'->8, 'V'->7, 'N'->2, 'T'->1,
 * 'W'->'3', 'Y'->4
 * Such that: "SIX" + "SEVEN" + "SEVEN" = "TWENTY" ,  650 + 68782 + 68782 =
 * 138214
 * 
 * Example 3:
 * 
 * 
 * Input: words = ["THIS","IS","TOO"], result = "FUNNY"
 * Output: true
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: words = ["LEET","CODE"], result = "POINT"
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= words.length <= 5
 * 1 <= words[i].length, results.length <= 7
 * words[i], result contains only upper case English letters.
 * Number of different characters used on the expression is at most 10.
 * 
 */

// @lc code=start
class Solution {
	int[] m;
	Set<Character> initials;
	Set<Character> characters;
	int[] digit = new int[10];
	String[] words;
	String result;
    public boolean isSolvable(String[] words, String result) {
    	this.words = words;
    	this.result = result;
    	m = new int[26];
    	initials = new HashSet<>();
    	characters = new HashSet<>();
        for(String w: words){
        	initials.add(w.charAt(0));
        	for (char c : w.toCharArray()) {
        		characters.add(c);
        	}
        }
        initials.add(result.charAt(0));
        for(char c: result.toCharArray()){
        	characters.add(c);
        }
        int[] chars = new int[characters.size()];
        int j = 0;
        for (char c: characters) {
        	chars[j++] = c - 'A';
        }
        for(int i = 0; i < digit.length; i++){
        	digit[i] = i;
        }
        return dfs(chars, 0);
    }

    boolean dfs(int[] chars, int pos){
    	if(pos == chars.length){
    		return check();
    	}
    	else{
    		for(int i = pos; i < 10; i++){
	    		swap(digit, pos, i);
	    		if(!initials.contains(chars[pos]) || digit[pos] != 0){
					m[chars[pos]] = digit[pos];
		    		if(dfs(chars, pos + 1)){
		    			// System.out.println(Arrays.toString(m));
		    			return true;
		    		}
    			}
	    		swap(digit, pos, i);
	    	}
	    	return false;	
    	}
    	
    }

    void swap(int[] nums, int i, int j){
    	int t = nums[i];
    	nums[i] = nums[j];
    	nums[j] = t;
    }

    boolean check(){
    	long r = convertToNumber(result);
    	for(String w: words){
    		r -= convertToNumber(w);
    		if(r < 0){
    			return false;
    		}
    	}
    	return r == 0;
    }

    long convertToNumber(String s){
    	long ans = 0;
    	for(int i = 0; i < s.length(); i++){
    		ans *= 10;
    		ans += m[s.charAt(i) - 'A'];
    	}
    	return ans;
    }
}
// @lc code=end
