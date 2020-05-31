/*
 * @lc app=leetcode id=1461 lang=java
 *
 * [1461] Check If a String Contains All Binary Codes of Size K
 *
 * https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/description/
 *
 * algorithms
 * Medium (38.00%)
 * Likes:    47
 * Dislikes: 26
 * Total Accepted:    5.1K
 * Total Submissions: 13.5K
 * Testcase Example:  '"00110110"\n2'
 *
 * Given a binary string s and an integer k.
 * 
 * Return True if all binary codes of length k is a substring of s. Otherwise,
 * return False.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "00110110", k = 2
 * Output: true
 * Explanation: The binary codes of length 2 are "00", "01", "10" and "11".
 * They can be all found as substrings at indicies 0, 1, 3 and 2
 * respectively.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "00110", k = 2
 * Output: true
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "0110", k = 1
 * Output: true
 * Explanation: The binary codes of length 1 are "0" and "1", it is clear that
 * both exist as a substring. 
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "0110", k = 2
 * Output: false
 * Explanation: The binary code "00" is of length 2 and doesn't exist in the
 * array.
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: s = "0000000001011100", k = 4
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 5 * 10^5
 * s consists of 0's and 1's only.
 * 1 <= k <= 20
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean hasAllCodes(String s, int k) {
    	if(k > s.length()){
    		return false;
    	}
    	int mask = (1 << k) - 1;
        boolean[] seen = new boolean[1 << k];
        int cnt = 0;
        int cur = 0;
        for (int i = 0; i < s.length(); i++) {
			cur <<= 1;
			cur += s.charAt(i) - '0';
			cur &= mask;
			// System.out.println(cur);
			if(i >= k - 1){
				if(!seen[cur]){
					seen[cur] = true;
					cnt++;
				}
			}
        }
        // System.out.println(Arrays.toString(seen));
        return cnt == (1 << k);
    }
}
// @lc code=end
