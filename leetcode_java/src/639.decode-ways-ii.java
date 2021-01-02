/*
 * @lc app=leetcode id=639 lang=java
 *
 * [639] Decode Ways II
 *
 * https://leetcode.com/problems/decode-ways-ii/description/
 *
 * algorithms
 * Hard (27.28%)
 * Likes:    531
 * Dislikes: 573
 * Total Accepted:    36.1K
 * Total Submissions: 131.7K
 * Testcase Example:  '"*"'
 *
 * 
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping way:
 * 
 * 
 * 
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 
 * 
 * 
 * Beyond that, now the encoded string can also contain the character '*',
 * which can be treated as one of the numbers from 1 to 9.
 * 
 * 
 * 
 * 
 * Given the encoded message containing digits and the character '*', return
 * the total number of ways to decode it.
 * 
 * 
 * 
 * Also, since the answer may be very large, you should return the output mod
 * 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * Input: "*"
 * Output: 9
 * Explanation: The encoded message can be decoded to the string: "A", "B",
 * "C", "D", "E", "F", "G", "H", "I".
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "1*"
 * Output: 9 + 9 = 18
 * 
 * 
 * 
 * Note:
 * 
 * The length of the input string will fit in range [1, 10^5].
 * The input string will only contain the character '*' and digits '0' - '9'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numDecodings(String s) {
    	cache = new long[s.length() + 1];
    	// Arrays.fill(cache, -1);
        return (int)rec(s, 0);
    }

    long[] cache; 
    long MOD = 1000000007;
    long rec(String s, int pos){
    	if(pos == s.length()){
    		return 1;
    	}
    	if(cache[pos] == 0){
    		switch(s.charAt(pos)){
    			case '*':
    				// single: 1 - 9
    				cache[pos] += (9 * rec(s, pos + 1)) % MOD;
    				cache[pos] %= MOD;
    				// 1x
    				if(pos + 1 < s.length()){
    					if(s.charAt(pos + 1) == '*'){
    						//1{*}
    						cache[pos] += (9 * rec(s, pos + 2)) % MOD;
    						cache[pos] %= MOD;
    					}
    					else{
    						// 1{digit}
    						cache[pos] += rec(s, pos + 2);	
    						cache[pos] %= MOD;
    					}
    				}
    				// 2x
    				if(pos + 1 < s.length()){
    					if(s.charAt(pos + 1) == '*'){
    						//1{*}
    						cache[pos] += (6 * rec(s, pos + 2)) % MOD;
    						cache[pos] %= MOD;
    					}
    					else if('0' <= s.charAt(pos + 1) && s.charAt(pos + 1) <= '6'){
    						// 1{digit}
    						cache[pos] += rec(s, pos + 2);	
    						cache[pos] %= MOD;
    					}
    				}
    				break;
    			case '0':
    				return 0;
    			case '1':
    				// single
    				cache[pos] += rec(s, pos + 1);
    				cache[pos] %= MOD;
    				// 1x
    				if(pos + 1 < s.length()){
    					if(s.charAt(pos + 1) == '*'){
    						//1{*}
    						cache[pos] += (9 * rec(s, pos + 2)) % MOD;
    						cache[pos] %= MOD;
    					}
    					else{
    						// 1{digit}
    						cache[pos] += rec(s, pos + 2);	
    						cache[pos] %= MOD;
    					}
    				}
    				break;
    			case '2':
    				// single
    				cache[pos] += rec(s, pos + 1);
    				cache[pos] %= MOD;
    				// 1x
    				if(pos + 1 < s.length()){
    					if(s.charAt(pos + 1) == '*'){
    						//1{*}
    						cache[pos] += (6 * rec(s, pos + 2)) % MOD;
    						cache[pos] %= MOD;
    					}
    					else if('0' <= s.charAt(pos + 1) && s.charAt(pos + 1) <= '6'){
    						// 1{digit}
    						cache[pos] += rec(s, pos + 2);	
    						cache[pos] %= MOD;
    					}
    				}
    				break;
    			default:
    				cache[pos] += rec(s, pos + 1);
    				cache[pos] %= MOD;
    				break;
    		}
    	}
    	return cache[pos];
    }
}
// @lc code=end
