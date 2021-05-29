/*
 * @lc app=leetcode id=1869 lang=java
 *
 * [1869] Longer Contiguous Segments of Ones than Zeros
 *
 * https://leetcode.com/problems/longer-contiguous-segments-of-ones-than-zeros/description/
 *
 * algorithms
 * Easy (88.91%)
 * Likes:    89
 * Dislikes: 3
 * Total Accepted:    18.5K
 * Total Submissions: 20.8K
 * Testcase Example:  '"1101"'
 *
 * Given a binary string s, return true if the longest contiguous segment of 1s
 * is strictly longer than the longest contiguous segment of 0s in s. Return
 * false otherwise.
 * 
 * 
 * For example, in s = "110100010" the longest contiguous segment of 1s has
 * length 2, and the longest contiguous segment of 0s has length 3.
 * 
 * 
 * Note that if there are no 0s, then the longest contiguous segment of 0s is
 * considered to have length 0. The same applies if there are no 1s.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "1101"
 * Output: true
 * Explanation:
 * The longest contiguous segment of 1s has length 2: "1101"
 * The longest contiguous segment of 0s has length 1: "1101"
 * The segment of 1s is longer, so return true.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "111000"
 * Output: false
 * Explanation:
 * The longest contiguous segment of 1s has length 3: "111000"
 * The longest contiguous segment of 0s has length 3: "111000"
 * The segment of 1s is not longer, so return false.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "110100010"
 * Output: false
 * Explanation:
 * The longest contiguous segment of 1s has length 2: "110100010"
 * The longest contiguous segment of 0s has length 3: "110100010"
 * The segment of 1s is not longer, so return false.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 100
 * s[i] is either '0' or '1'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean checkZeroOnes(String s) {
    	int max = 1;
    	boolean ret = (s.charAt(0) == '1');
    	int left = 0;
        for(int i = 1; i < s.length(); i++){
        	if(s.charAt(i) != s.charAt(i - 1)){
        		left = i;
        	}
        	if(max < i - left + 1){
    			ret = (s.charAt(i) == '1');
    			max = i - left + 1;
    		}
    		else if(max == i - left + 1 && s.charAt(i) == '0'){
    			ret = false;
    		}
        }
        return ret;
    }
}
// @lc code=end
