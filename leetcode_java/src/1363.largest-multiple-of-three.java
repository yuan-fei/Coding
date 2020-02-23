/*
 * @lc app=leetcode id=1363 lang=java
 *
 * [1363] Largest Multiple of Three
 *
 * https://leetcode.com/problems/largest-multiple-of-three/description/
 *
 * algorithms
 * Hard (27.88%)
 * Likes:    35
 * Dislikes: 16
 * Total Accepted:    2.4K
 * Total Submissions: 8.8K
 * Testcase Example:  '[8,1,9]'
 *
 * Given an integer array of digits, return the largest multiple of three that
 * can be formed by concatenating some of the given digits in any order.
 * 
 * Since the answer may not fit in an integer data type, return the answer as a
 * string.
 * 
 * If there is no answer return an empty string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: digits = [8,1,9]
 * Output: "981"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: digits = [8,6,7,1,0]
 * Output: "8760"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: digits = [1]
 * Output: ""
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: digits = [0,0,0,0,0,0]
 * Output: "0"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= digits.length <= 10^4
 * 0 <= digits[i] <= 9
 * The returning answer must not contain unnecessary leading zeros.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String largestMultipleOfThree(int[] digits) {
    	int n = digits.length;
        int[] cnt = new int[10];
        int modulo = 0;
        for(int d: digits){
        	cnt[d]++;
        	modulo = (modulo + d) % 3;
        }
        if(modulo != 0){
	        if(!remove(cnt, 1, modulo)){
	    		remove(cnt, 2, 3 - modulo);
	    	}	
        }
        
    	StringBuilder sb  =new StringBuilder();
    	for(int i = 9; i >= 0; i--){
    		if(cnt[i] > 0){
    			for(int j = 0; j < cnt[i]; j++){
    				sb.append(i);
    			}
    		}
    	}
    	if(sb.length() != 0 && sb.charAt(0) == '0'){
    		return "0";
    	}
    	return sb.toString();
    }

    boolean remove(int[] num, int cnt, int congruence){
    	for(int i = congruence; i < 10; i += 3){
			while(num[i] > 0){
    			num[i]--;
    			cnt--;
    			if(cnt == 0){
    				return true;
    			}
    		}
	    }
	    return false;
    }
}
// @lc code=end
