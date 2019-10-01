/*
 * @lc app=leetcode id=166 lang=java
 *
 * [166] Fraction to Recurring Decimal
 *
 * https://leetcode.com/problems/fraction-to-recurring-decimal/description/
 *
 * algorithms
 * Medium (19.95%)
 * Total Accepted:    96.1K
 * Total Submissions: 480.6K
 * Testcase Example:  '1\n2'
 *
 * Given two integers representing the numerator and denominator of a fraction,
 * return the fraction in string format.
 * 
 * If the fractional part is repeating, enclose the repeating part in
 * parentheses.
 * 
 * Example 1:
 * 
 * 
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * 
 * Example 3:
 * 
 * 
 * Input: numerator = 2, denominator = 3
 * Output: "0.(6)"
 * 
 * 
 */
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
    	if(numerator == 0){
    		return "0";
    	}
    	String ret = "";
    	if(numerator < 0 && denominator > 0){
    		ret += "-";
    	}
    	else if(numerator > 0 && denominator < 0){
    		ret += "-";
    	}
    	long num = numerator;
    	long den = denominator;
    	num = Math.abs(num);
    	den = Math.abs(den);
    	Map<Long, Integer> first = new HashMap<>(); 
    	
    	long d = num / den;
    	long r = num % den;
    	ret += d;
    	if(r == 0){
    		return ret;
    	}
    	else{
    		ret += ".";
    	}
        while(r != 0){
        	if(first.containsKey(r)){
        		int idx = first.get(r);
        		return ret.substring(0, idx) + "(" + ret.substring(idx) + ")";
        	}
        	else{
        		first.put(r, ret.length());
        		// System.out.println(""+d+","+r);
        		d = (r * 10) / den;
        		r = (r * 10) % den;
        		ret += d;
        	}
        }
        return ret;
    }
}
