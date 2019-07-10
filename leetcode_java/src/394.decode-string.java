/*
 * @lc app=leetcode id=394 lang=java
 *
 * [394] Decode String
 *
 * https://leetcode.com/problems/decode-string/description/
 *
 * algorithms
 * Medium (44.83%)
 * Total Accepted:    107.6K
 * Total Submissions: 238.4K
 * Testcase Example:  '"3[a]2[bc]"'
 *
 * Given an encoded string, return its decoded string.
 * 
 * The encoding rule is: k[encoded_string], where the encoded_string inside the
 * square brackets is being repeated exactly k times. Note that k is guaranteed
 * to be a positive integer.
 * 
 * You may assume that the input string is always valid; No extra white spaces,
 * square brackets are well-formed, etc.
 * 
 * Furthermore, you may assume that the original data does not contain any
 * digits and that digits are only for those repeat numbers, k. For example,
 * there won't be input like 3a or 2[4].
 * 
 * Examples:
 * 
 * 
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 * 
 * 
 * 
 * 
 */
class Solution {
	public String decodeString(String s) {
		Stack<String> pref = new Stack<>();
		Stack<Integer> op = new Stack<>();
		int i = 0;
		String curPref = "";
		while(i < s.length()){
			if(Character.isDigit(s.charAt(i))){
	    		String num ="";
	    		while(i < s.length() && Character.isDigit(s.charAt(i))){
	    			num+=s.charAt(i++);
	    		}	
	    		op.push(Integer.parseInt(num));
    		}
    		else if(Character.isLetter(s.charAt(i))){
    			String letter ="";
	    		while(i < s.length() && Character.isLetter(s.charAt(i))){
	    			letter+=s.charAt(i++);
	    		}
	    		curPref+=letter;
	    	}
	    	else if(s.charAt(i)=='['){
				pref.push(curPref);
				curPref = "";
				i++;
			}
	    	else{
	    		String part = "";
	    		int k = op.pop();
				for(int j =0; j < k; j++){
					part+=curPref;
				}
				if(!pref.isEmpty()){
					curPref = pref.pop()+part;
				}
				else{
					curPref = part;
				}
				i++;
	    	}
		}
		return curPref;
	}

    public String decodeString1(String s) {
    	Stack<String> stk = new Stack<>();
    	int i= 0;
    	while(i<s.length()){
    		if(Character.isDigit(s.charAt(i))){
	    		String num ="";
	    		while(i < s.length() && Character.isDigit(s.charAt(i))){
	    			num+=s.charAt(i++);
	    		}	
	    		stk.push(num);
    		}
    		else if(Character.isLetter(s.charAt(i))){
    			String letter ="";
	    		while(i < s.length() && Character.isLetter(s.charAt(i))){
	    			letter+=s.charAt(i++);
	    		}
	    		stk.push(letter);
	    	}
	    	else{
    			if(s.charAt(i)==']'){
    				String part = "";
    				while(!stk.isEmpty()&&Character.isLetter(stk.peek().charAt(0))){
    					part=stk.pop()+part;
    				}
    				String rep = "";
    				if(Character.isDigit(stk.peek().charAt(0))){
	    				int k = Integer.parseInt(stk.pop());
	    				for(int j =0; j < k; j++){
	    					rep=part+rep;
	    				}	
    				}
    				else{
    					rep=part;
    				}
    				stk.push(rep);
    			}
    			i++;
    		}
    	}
    	String r ="";
        while(!stk.isEmpty()){
			r=stk.pop()+r;
		}
		return r;
    }
}
