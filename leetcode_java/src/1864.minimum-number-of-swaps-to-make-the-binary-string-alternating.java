/*
 * @lc app=leetcode id=1864 lang=java
 *
 * [1864] Minimum Number of Swaps to Make the Binary String Alternating
 *
 * https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-binary-string-alternating/description/
 *
 * algorithms
 * Medium (30.83%)
 * Likes:    57
 * Dislikes: 14
 * Total Accepted:    4.3K
 * Total Submissions: 13.9K
 * Testcase Example:  '"111000"'
 *
 * Given a binary string s, return the minimum number of character swaps to
 * make it alternating, or -1 if it is impossible.
 * 
 * The string is called alternating if no two adjacent characters are equal.
 * For example, the strings "010" and "1010" are alternating, while the string
 * "0100" is not.
 * 
 * Any two characters may be swapped, even if they are not adjacent.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "111000"
 * Output: 1
 * Explanation: Swap positions 1 and 4: "111000" -> "101010"
 * The string is now alternating.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "010"
 * Output: 0
 * Explanation: The string is already alternating, no swaps are needed.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "1110"
 * Output: -1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 1000
 * s[i] is either '0' or '1'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minSwaps(String s) {
    	int cnt0 = 0;
        for(int i = 0; i < s.length(); i++){
        	if(s.charAt(i) == '0'){
        		cnt0++;
        	}
        }
        if(s.length() == 2 * cnt0 + 1){
        	// s[0] = 1
        	return count('1', s);
        }
        else if(s.length() == 2 * cnt0 - 1){
        	// s[0] = 0
        	return count('0', s);
        }
        else if(s.length() == 2 * cnt0){
        	return Math.min(count('1', s), count('0', s));
        }
        return -1;
    }

    int count(char firstChar, String s){
    	int cnt = 0;
    	for(int i = 0; i < s.length(); i++){
    		if(s.charAt(i) == firstChar && i % 2 == 1){
    			cnt++;
    		}
    	}
    	return cnt;
    }
}
// @lc code=end
