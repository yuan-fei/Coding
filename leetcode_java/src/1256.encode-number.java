/*
 * @lc app=leetcode id=1256 lang=java
 *
 * [1256] Encode Number
 *
 * https://leetcode.com/problems/encode-number/description/
 *
 * algorithms
 * Medium (59.45%)
 * Likes:    13
 * Dislikes: 46
 * Total Accepted:    1.7K
 * Total Submissions: 2.9K
 * Testcase Example:  '23'
 *
 * Given a non-negative integer num, Return its encoding string.
 * 
 * The encoding is done by converting the integer to a string using a secret
 * function that you should deduce from the following table:
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = 23
 * Output: "1000"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = 107
 * Output: "101100"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= num <= 10^9
 * 
 */

// @lc code=start
class Solution {
    public String encode(int num) {
    	if(num == 0){
    		return "";
    	}
    	
    	int mask = 0;
    	int diff = 0;
    	int l = 0;
        while(num - mask >= 0){
        	diff = num - mask;
        	l++;
        	mask = (1 << l) - 1;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = l - 2; i >= 0; i--){
        	sb.append((diff >> i) & 1);
        }
        return sb.toString();
    }
}
// @lc code=end
