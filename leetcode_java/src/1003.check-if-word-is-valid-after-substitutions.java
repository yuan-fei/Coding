/*
 * @lc app=leetcode id=1003 lang=java
 *
 * [1003] Check If Word Is Valid After Substitutions
 *
 * https://leetcode.com/problems/check-if-word-is-valid-after-substitutions/description/
 *
 * algorithms
 * Medium (51.83%)
 * Total Accepted:    9.9K
 * Total Submissions: 19K
 * Testcase Example:  '"aabcbc"'
 *
 * We are given that the string "abc" is valid.
 * 
 * From any valid string V, we may split V into two pieces X and Y such that X
 * + Y (X concatenated with Y) is equal to V.  (X or Y may be empty.)  Then, X
 * + "abc" + Y is also valid.
 * 
 * If for example S = "abc", then examples of valid strings are: "abc",
 * "aabcbc", "abcabc", "abcabcababcc".  Examples of invalid strings are:
 * "abccba", "ab", "cababc", "bac".
 * 
 * Return true if and only if the given string S is valid.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: "aabcbc"
 * Output: true
 * Explanation: 
 * We start with the valid string "abc".
 * Then we can insert another "abc" between "a" and "bc", resulting in "a" +
 * "abc" + "bc" which is "aabcbc".
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "abcabcababcc"
 * Output: true
 * Explanation: 
 * "abcabcabc" is valid after consecutive insertings of "abc".
 * Then we can insert "abc" before the last letter, resulting in "abcabcab" +
 * "abc" + "c" which is "abcabcababcc".
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: "abccba"
 * Output: false
 * 
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: "cababc"
 * Output: false
 * 
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= S.length <= 20000
 * S[i] is 'a', 'b', or 'c'
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
	
	public boolean isValid(String S) {
		while(pos < S.length()){
			if(!isValidHelper(S)){
				return false;
			}
		}
		return true;
	}

	int pos = 0;
    public boolean isValidHelper(String S) {
    	int state = 0;
        while (pos < S.length()) {
        	char c = S.charAt(pos);
        	switch(state){
        		case 0: 
        			if(c!='a'){
        				return false;
        			}
        			else{
        				state++;
        				pos++;
        			}
        			break;
        		case 1:
        			if(c=='b'){
        				state++;
        				pos++;
        			}
        			else if(c=='a'){
        				if(!isValidHelper(S)){
        					return false;
        				}
        			}
        			else{
        				return false;
        			}
        			break;
        		case 2:
        			if(c=='c'){
        				state=0;
        				pos++;
        				return true;
        			}
        			else if(c=='a'){
        				if(!isValidHelper(S)){
        					return false;
        				}
        			}
        			else{
        				return false;
        			}
        			break;
        	}
        }
        return state==0;
    }

    public boolean isValidItr(String S) {
        Stack<Character> s = new Stack<>();
        for (char c : S.toCharArray()) {
        	switch(c){
        		case 'a': 
        			s.push(c); 
        			break;
        		case 'b':
        			if(!s.isEmpty() && s.peek()=='a'){
        				s.push(c);
        			}
        			else{
        				return false;
        			}
        			break;
        		case 'c':
        			if(!s.isEmpty() && s.peek()=='b'){
        				s.pop();
        				s.pop();
        			}
        			else{
        				return false;
        			}
        			break;
        	}
        }
        return s.isEmpty();
    }
}
