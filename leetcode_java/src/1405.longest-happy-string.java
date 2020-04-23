/*
 * @lc app=leetcode id=1405 lang=java
 *
 * [1405] Longest Happy String
 *
 * https://leetcode.com/problems/longest-happy-string/description/
 *
 * algorithms
 * Medium (32.57%)
 * Likes:    134
 * Dislikes: 46
 * Total Accepted:    5.7K
 * Total Submissions: 12.9K
 * Testcase Example:  '1\n1\n7'
 *
 * A string is called happy if it does not have any of the strings 'aaa', 'bbb'
 * or 'ccc' as a substring.
 * 
 * Given three integers a, b and c, return any string s, which satisfies
 * following conditions:
 * 
 * 
 * s is happy and longest possible.
 * s contains at most a occurrences of the letter 'a', at most b occurrences of
 * the letter 'b' and at most c occurrences of the letter 'c'.
 * s will only contain 'a', 'b' and 'c' letters.
 * 
 * 
 * If there is no such string s return the empty string "".
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: a = 1, b = 1, c = 7
 * Output: "ccaccbcc"
 * Explanation: "ccbccacc" would also be a correct answer.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: a = 2, b = 2, c = 1
 * Output: "aabbc"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: a = 7, b = 1, c = 0
 * Output: "aabaa"
 * Explanation: It's the only correct answer in this case.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 * 
 * 
 */

// @lc code=start
class Solution {
    public String longestDiverseString(int a, int b, int c) {
        int[][] cnt = {{a,0},{b,1},{c,2}};
        char[] chars = {'a', 'b', 'c'};
        Arrays.sort(cnt, (x, y) -> -Integer.compare(x[0], y[0]));
        String large = "";
        String small = "";
        if(cnt[0][0] >= cnt[1][0] + cnt[2][0]){
        	large = getString(chars[cnt[0][1]], cnt[0][0]);
        	small = getString(chars[cnt[1][1]], cnt[1][0]) + getString(chars[cnt[2][1]], cnt[2][0]);
        }
        else{
			small = getString(chars[cnt[0][1]], cnt[0][0]);
        	large = getString(chars[cnt[1][1]], cnt[1][0]) + getString(chars[cnt[2][1]], cnt[2][0]);
        }
        return buildString(large, small);
    }

    String buildString(String large, String small){
    	String ret = "";
    	if(large.length() > 2 * small.length() + 2){
    		ret =  buildString(large.substring(0, 2 * small.length() + 2), small);
    	}
    	else if(large.length() < 2 * small.length() + 2){
    		if(large.length() > 0){
    			ret += large.charAt(0);
    		}
    		if(small.length() > 0){
    			ret += small.charAt(0);
    		}
    		if(large.length() > 1){
    			ret += buildString(large.substring(1), small.length() > 1 ? small.substring(1) : "");
    		}
    	}
    	else{
    		for(int i = 0; i < small.length(); i++){
    			ret += large.substring(2 * i, 2 * i + 2);
    			ret += small.charAt(i);
    		}
    		ret += large.substring(2 * small.length());
    		return ret;
    	}
    	return ret;
    }

    String getString(char c, int repeat){
    	String s = "";
    	for(int i = 0; i < repeat; i++){
    		s += c;
    	}
    	return s;
    }
}
// @lc code=end
