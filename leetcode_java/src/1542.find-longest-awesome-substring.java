/*
 * @lc app=leetcode id=1542 lang=java
 *
 * [1542] Find Longest Awesome Substring
 *
 * https://leetcode.com/problems/find-longest-awesome-substring/description/
 *
 * algorithms
 * Hard (25.75%)
 * Likes:    67
 * Dislikes: 4
 * Total Accepted:    1.4K
 * Total Submissions: 5.3K
 * Testcase Example:  '"3242415"'
 *
 * Given a string s. An awesome substring is a non-empty substring of s such
 * that we can make any number of swaps in order to make it palindrome.
 * 
 * Return the length of the maximum length awesome substring of s.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "3242415"
 * Output: 5
 * Explanation: "24241" is the longest awesome substring, we can form the
 * palindrome "24142" with some swaps.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "12345678"
 * Output: 1
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "213123"
 * Output: 6
 * Explanation: "213123" is the longest awesome substring, we can form the
 * palindrome "231132" with some swaps.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "00"
 * Output: 2
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s consists only of digits.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestAwesome(String s) {
    	int[] lastBitmaskPos = new int[1024];
    	Arrays.fill(lastBitmaskPos, -1);
    	int curBitmask = 0;
		for(int i = 0; i < s.length(); i++){
			int d = s.charAt(i) - '0';
			curBitmask ^= 1 << d;
			lastBitmaskPos[curBitmask] = i;
		}

		int maxLen = 0;
		curBitmask = 0;
		// include s[0]
		maxLen = Math.max(maxLen, lastBitmaskPos[curBitmask] + 1);
		for(int j = 0; j < 10; j++){
			int target = (curBitmask ^ (1 << j));
			if(lastBitmaskPos[target] > -1){
				maxLen = Math.max(maxLen, lastBitmaskPos[target] + 1);
			}
		}

		// exclude s[0]
		for(int i = 0; i < s.length(); i++){
			int d = s.charAt(i) - '0';
			curBitmask ^= 1 << d;
			// all even occurrances
			maxLen = Math.max(maxLen, lastBitmaskPos[curBitmask] - i);
			// 1 odd occurrance
			for(int j = 0; j < 10; j++){
				int target = (curBitmask ^ (1 << j));
				if(lastBitmaskPos[target] > i){
					maxLen = Math.max(maxLen, lastBitmaskPos[target] - i);
				}
			}
		}
		return maxLen;
    }
}
// @lc code=end
