/*
 * [6] ZigZag Conversion
 *
 * https://leetcode.com/problems/zigzag-conversion/description/
 *
 * algorithms
 * Medium (27.17%)
 * Total Accepted:    194.3K
 * Total Submissions: 714.9K
 * Testcase Example:  '""\n1'
 *
 * 
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number
 * of rows like this: (you may want to display this pattern in a fixed font for
 * better legibility)
 * 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 
 * 
 * And then read line by line: "PAHNAPLSIIGYIR"
 * 
 * 
 * Write the code that will take a string and make this conversion given a
 * number of rows:
 * 
 * string convert(string text, int nRows);
 * 
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * 
 */
class Solution {
    public String convert(String s, int numRows) {
    	if(numRows < 1){
    		return "";
    	}
    	if(numRows == 1){
    		return s;
    	}
    	List<Character>[] strs = new List[numRows];
        for (int i = 0; i < numRows; i++) {
        	strs[i] = new ArrayList<Character>();
        }
        for (int i = 0; i < s.length(); i++) {
        	int offset = i % (2 * (numRows - 1));
        	int index = numRows - 1 - Math.abs(offset - numRows + 1);
        	strs[index].add(s.charAt(i));
        }
        String result = "";
        for (int i = 0; i < numRows; i++) {
        	for (int j = 0; j < strs[i].size(); j++) {
        		result+=strs[i].get(j);
        	}
        }
        return result;
    }
}
