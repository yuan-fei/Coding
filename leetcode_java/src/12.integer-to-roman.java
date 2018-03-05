/*
 * [12] Integer to Roman
 *
 * https://leetcode.com/problems/integer-to-roman/description/
 *
 * algorithms
 * Medium (45.92%)
 * Total Accepted:    134.1K
 * Total Submissions: 291.3K
 * Testcase Example:  '1'
 *
 * Given an integer, convert it to a roman numeral.
 * 
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 */
class Solution {
    public String intToRoman(int num) {
    	String[][] pattern = new String[][]{
    		new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
    		new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
    		new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
    		new String[]{"", "M", "MM", "MMM"}
    	};

    	int divider = num;
    	int i = 0;
    	String result = "";
    	while(divider != 0){
    		result = pattern[i][divider % 10] + result;
    		divider /= 10;
    		i++;
    	}
    	return result;
    }
}
