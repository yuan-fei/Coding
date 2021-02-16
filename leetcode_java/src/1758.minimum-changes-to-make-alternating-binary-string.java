/*
 * @lc app=leetcode id=1758 lang=java
 *
 * [1758] Minimum Changes To Make Alternating Binary String
 *
 * https://leetcode.com/problems/minimum-changes-to-make-alternating-binary-string/description/
 *
 * algorithms
 * Easy (60.45%)
 * Likes:    97
 * Dislikes: 2
 * Total Accepted:    8.5K
 * Total Submissions: 14.1K
 * Testcase Example:  '"0100"'
 *
 * You are given a string s consisting only of the characters '0' and '1'. In
 * one operation, you can change any '0' to '1' or vice versa.
 * 
 * The string is called alternating if no two adjacent characters are equal.
 * For example, the string "010" is alternating, while the string "0100" is
 * not.
 * 
 * Return the minimum number of operations needed to make s alternating.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "0100"
 * Output: 1
 * Explanation: If you change the last character to '1', s will be "0101",
 * which is alternating.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "10"
 * Output: 0
 * Explanation: s is already alternating.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "1111"
 * Output: 2
 * Explanation: You need two operations to reach "0101" or "1010".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^4
 * s[i] is either '0' or '1'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minOperations(String s) {
    	int n = s.length();
    	int odd1 = 0;
    	int even0 = 0;
        for(int i = 0; i < n; i++){
        	if(i % 2 == 0){
        		even0 += 1 - (s.charAt(i) - '0');
        	}
        	else{
        		odd1 += (s.charAt(i) - '0');	
        	}
        }
        return Math.min(odd1 + even0, n - even0 - odd1);
    }
}
// @lc code=end
