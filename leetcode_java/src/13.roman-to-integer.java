/*
 * [13] Roman to Integer
 *
 * https://leetcode.com/problems/roman-to-integer/description/
 *
 * algorithms
 * Easy (47.57%)
 * Total Accepted:    212.7K
 * Total Submissions: 445.8K
 * Testcase Example:  '"DCXXI"'
 *
 * Given a roman numeral, convert it to an integer.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 */
class Solution {
    public int romanToInt(String s) {
    	Map<Character, Integer> values = new HashMap<Character, Integer>();
    	values.put('M', 1000);
    	values.put('D', 500);
    	values.put('C', 100);
    	values.put('L', 50);
    	values.put('X', 10);
    	values.put('V', 5);
    	values.put('I', 1);
    	int lastMax = 0;
    	int total = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
        	int value = values.get(s.charAt(i));
        	if(value < lastMax){
        		total -= value;
        	}
        	else{
        		lastMax = value;
        		total += value;
        	}
        }
        return total;
    }
}
