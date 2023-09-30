/*
 * @lc app=leetcode id=926 lang=java
 *
 * [926] Flip String to Monotone Increasing
 *
 * https://leetcode.com/problems/flip-string-to-monotone-increasing/description/
 *
 * algorithms
 * Medium (61.44%)
 * Likes:    4295
 * Dislikes: 176
 * Total Accepted:    182.7K
 * Total Submissions: 297.4K
 * Testcase Example:  '"00110"'
 *
 * A binary string is monotone increasing if it consists of some number of 0's
 * (possibly none), followed by some number of 1's (also possibly none).
 * 
 * You are given a binary string s. You can flip s[i] changing it from 0 to 1
 * or from 1 to 0.
 * 
 * Return the minimum number of flips to make s monotone increasing.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "00110"
 * Output: 1
 * Explanation: We flip the last digit to get 00111.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "010110"
 * Output: 2
 * Explanation: We flip to get 011111, or alternatively 000111.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "00011000"
 * Output: 2
 * Explanation: We flip to get 00000000.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s[i] is either '0' or '1'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int[] pOneSum = new int[n + 1];
        for(int i = 1; i <= n; i++){
            pOneSum[i] = pOneSum[i - 1];
            if(s.charAt(i - 1) == '1'){
                pOneSum[i]++;
            }
        }
        int ans = pOneSum[n];
        int zeroCnt = 0;
        for(int i = n - 1; i >= 0; i--){
            if(s.charAt(i) == '0'){
                zeroCnt++;
            }
            ans = Math.min(ans, zeroCnt + pOneSum[i]);
        }
        return ans;
    }
}
// @lc code=end
