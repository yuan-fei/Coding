/*
 * @lc app=leetcode id=1849 lang=java
 *
 * [1849] Splitting a String Into Descending Consecutive Values
 *
 * https://leetcode.com/problems/splitting-a-string-into-descending-consecutive-values/description/
 *
 * algorithms
 * Medium (23.36%)
 * Likes:    75
 * Dislikes: 39
 * Total Accepted:    4K
 * Total Submissions: 17.3K
 * Testcase Example:  '"1234"'
 *
 * You are given a string s that consists of only digits.
 * 
 * Check if we can split s into two or more non-empty substrings such that the
 * numerical values of the substrings are in descending order and the
 * difference between numerical values of every two adjacent substrings is
 * equal to 1.
 * 
 * 
 * For example, the string s = "0090089" can be split into ["0090", "089"] with
 * numerical values [90,89]. The values are in descending order and adjacent
 * values differ by 1, so this way is valid.
 * Another example, the string s = "001" can be split into ["0", "01"], ["00",
 * "1"], or ["0", "0", "1"]. However all the ways are invalid because they have
 * numerical values [0,1], [0,1], and [0,0,1] respectively, all of which are
 * not in descending order.
 * 
 * 
 * Return true if it is possible to split s​​​​​​ as described above, or false
 * otherwise.
 * 
 * A substring is a contiguous sequence of characters in a string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "1234"
 * Output: false
 * Explanation: There is no valid way to split s.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "050043"
 * Output: true
 * Explanation: s can be split into ["05", "004", "3"] with numerical values
 * [5,4,3].
 * The values are in descending order with adjacent values differing by 1.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "9080701"
 * Output: false
 * Explanation: There is no valid way to split s.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "10009998"
 * Output: true
 * Explanation: s can be split into ["100", "099", "98"] with numerical values
 * [100,99,98].
 * The values are in descending order with adjacent values differing by 1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 20
 * s only consists of digits.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean splitString(String s) {
        for(int l = 1; l <= s.length() - 1; l++){
        	long cur = Long.parseUnsignedLong(s.substring(0, l));
        	int left = l;
        	boolean ok = true;
        	while(left < s.length()){
        		cur--;
        		if(Long.compareUnsigned(cur, 0) < 0){
        			ok = false;
        			break;
        		}
        		while(left < s.length() && s.charAt(left) == '0'){
        			left++;
        		}
        		String sCur = "" + (cur == 0 ? "" : Long.toUnsignedString(cur));
	        	if(left + sCur.length() - 1 < s.length() && s.substring(left, left + sCur.length()).equals(sCur)){
	        		left += sCur.length();
	        	}
	        	else{
	        		ok = false;
	        		break;
	        	}
        	}
        	if(ok){
        		// System.out.println(Long.toUnsignedString(cur));
        		return true;
        	}
        }
        return false;
    }
}
// @lc code=end
