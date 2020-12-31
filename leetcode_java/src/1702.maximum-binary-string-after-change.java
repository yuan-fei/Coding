/*
 * @lc app=leetcode id=1702 lang=java
 *
 * [1702] Maximum Binary String After Change
 *
 * https://leetcode.com/problems/maximum-binary-string-after-change/description/
 *
 * algorithms
 * Medium (37.76%)
 * Likes:    107
 * Dislikes: 12
 * Total Accepted:    3.1K
 * Total Submissions: 8.3K
 * Testcase Example:  '"000110"'
 *
 * You are given a binary string binary consisting of only 0's or 1's. You can
 * apply each of the following operations any number of times:
 * 
 * 
 * Operation 1: If the number contains the substring "00", you can replace it
 * with "10".
 * 
 * 
 * For example, "00010" -> "10010"
 * 
 * 
 * Operation 2: If the number contains the substring "10", you can replace it
 * with "01".
 * 
 * For example, "00010" -> "00001"
 * 
 * 
 * 
 * 
 * Return the maximum binary string you can obtain after any number of
 * operations. Binary string x is greater than binary string y if x's decimal
 * representation is greater than y's decimal representation.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: binary = "000110"
 * Output: "111011"
 * Explanation: A valid transformation sequence can be:
 * "000110" -> "000101" 
 * "000101" -> "100101" 
 * "100101" -> "110101" 
 * "110101" -> "110011" 
 * "110011" -> "111011"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: binary = "01"
 * Output: "01"
 * Explanation: "01" cannot be transformed any further.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= binary.length <= 10^5
 * binary consist of '0' and '1'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String maximumBinaryString(String binary) {
    	int ones = 0;
    	boolean suffixOne = true;
        for(int i = 0; i < binary.length(); i++){
        	if(!suffixOne && binary.charAt(i) == '1'){
        		ones++;	
        	}
        	else if(binary.charAt(i) == '0'){
        		suffixOne = false;
        	}
        }
        if(suffixOne){
        	return binary;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < binary.length(); i++){
        	if(i == binary.length() - 1 - ones){
        		sb.append('0');
        	}
        	else{
        		sb.append('1');
        	}
        }
        return sb.toString();
    }
}
// @lc code=end
