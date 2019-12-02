/*
 * @lc app=leetcode id=1271 lang=java
 *
 * [1271] Hexspeak
 *
 * https://leetcode.com/problems/hexspeak/description/
 *
 * algorithms
 * Easy (46.47%)
 * Likes:    9
 * Dislikes: 19
 * Total Accepted:    1.9K
 * Total Submissions: 4.1K
 * Testcase Example:  '"257"'
 *
 * A decimal number can be converted to its Hexspeak representation by first
 * converting it to an uppercase hexadecimal string, then replacing all
 * occurrences of the digit 0 with the letter O, and the digit 1 with the
 * letter I.  Such a representation is valid if and only if it consists only of
 * the letters in the set {"A", "B", "C", "D", "E", "F", "I", "O"}.
 * 
 * Given a string num representing a decimal integer N, return the Hexspeak
 * representation of N if it is valid, otherwise return "ERROR".
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = "257"
 * Output: "IOI"
 * Explanation:  257 is 101 in hexadecimal.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = "3"
 * Output: "ERROR"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= N <= 10^12
 * There are no leading zeros in the given string.
 * All answers must be in uppercase letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String toHexspeak(String num) {
        long N = 0;
        for (char c : num.toCharArray()) {
        	int d = c - '0';
        	N = N * 10 + d;
        }
        HashMap<Long, String> map = new HashMap<>();
        map.put(10L, "A");
        map.put(11L, "B");
        map.put(12L, "C");
        map.put(13L, "D");
        map.put(14L, "E");
        map.put(15L, "F");
        map.put(1L, "I");
        map.put(0L, "O");
        
        StringBuilder sb = new StringBuilder();
        while(N != 0){
        	String c = map.get(N % 16);
        	if(c == null){
        		// System.out.println(N);		
        		return "ERROR";
        	}
        	else{
        		sb.append(c);
        	}
        	N /= 16;
        }
        return sb.reverse().toString();
    }
}
// @lc code=end
