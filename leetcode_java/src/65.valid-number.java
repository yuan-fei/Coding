/*
 * @lc app=leetcode id=65 lang=java
 *
 * [65] Valid Number
 *
 * https://leetcode.com/problems/valid-number/description/
 *
 * algorithms
 * Hard (13.99%)
 * Total Accepted:    129.5K
 * Total Submissions: 913.8K
 * Testcase Example:  '"0"'
 *
 * Validate if a given string can be interpreted as a decimal number.
 * 
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 * 
 * Note: It is intended for the problem statement to be ambiguous. You should
 * gather all requirements up front before implementing one. However, here is a
 * list of characters that can be in a valid decimal number:
 * 
 * 
 * Numbers 0-9
 * Exponent - "e"
 * Positive/negative sign - "+"/"-"
 * Decimal point - "."
 * 
 * 
 * Of course, the context of these characters also matters in the input.
 * 
 * Update (2015-02-10):
 * The signature of the C++ function had been updated. If you still see your
 * function signature accepts a const char * argument, please click the reload
 * button to reset your code definition.
 * 
 */
class Solution {
	String s;
	int pos = 0;
    public boolean isNumber(String s) {
    	this.s = s;
    	skipWhiteSpace();
        skipSign();
        if(checkScientificNotationDecimal()){
        	skipWhiteSpace();
        	// System.out.println('1'+pos);
        	return pos==s.length();
        }
        return false;
    }

    private void skipWhiteSpace(){
    	while(pos < s.length() && s.charAt(pos) == ' '){
    		pos++;
    	}
    }

    private void skipSign(){
    	if(pos < s.length() && (s.charAt(pos) == '+' || s.charAt(pos) == '-')){
    		pos++;
    	}
    }

    private boolean checkScientificNotationDecimal(){
    	if(!checkPlainDecimal()){
    		// System.out.println("checkPlainDecimal");
    		return false;
    	}
    	if(checkExponent()){
    		skipSign();
    		if(!checkInteger()){
    			return false;	
    		}
    	}
    	return true;
    }

    private boolean checkInteger(){
    	int origPos = pos;
    	while(pos < s.length() && s.charAt(pos) >= '0' && s.charAt(pos) <= '9'){
    		pos++;
    	}
    	// System.out.println(pos+", "+origPos);
    	return pos > origPos;
    }

    private boolean checkExponent(){
		if(pos < s.length() && s.charAt(pos) == 'e'){
    		pos++;
    		return true;
    	}
    	return false;
    }

    private boolean checkPlainDecimal(){
    	boolean r = checkInteger();
    	if(r){
    		checkPoint();
    		checkInteger();
    		return true;
    	}
    	else{
    		return checkPoint() && checkInteger();
    	}
    }

    private boolean checkPoint(){
    	if(pos < s.length() && s.charAt(pos) == '.'){
    		pos++;
    		return true;
    	}
    	// System.out.println("checkPoint");
    	return false;
    }
}