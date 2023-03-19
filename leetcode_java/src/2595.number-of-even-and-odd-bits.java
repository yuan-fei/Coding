/*
 * @lc app=leetcode id=2595 lang=java
 *
 * [2595] Number of Even and Odd Bits
 *
 * https://leetcode.com/problems/number-of-even-and-odd-bits/description/
 *
 * algorithms
 * Easy (66.73%)
 * Likes:    29
 * Dislikes: 9
 * Total Accepted:    14.4K
 * Total Submissions: 21.6K
 * Testcase Example:  '17'
 *
 * You are given a positive integer n.
 * 
 * Let even denote the number of even indices in the binary representation of n
 * (0-indexed) with value 1.
 * 
 * Let odd denote the number of odd indices in the binary representation of n
 * (0-indexed) with value 1.
 * 
 * Return an integer array answer where answer = [even, odd].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 17
 * Output: [2,0]
 * Explanation: The binary representation of 17 is 10001. 
 * It contains 1 on the 0^th and 4^th indices. 
 * There are 2 even and 0 odd indices.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2
 * Output: [0,1]
 * Explanation: The binary representation of 2 is 10.
 * It contains 1 on the 1^st index. 
 * There are 0 even and 1 odd indices.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] evenOddBit(int n) {
        int[] ans = new int[2];
        for(int i = 0; i < 31; i++){
            if(((n >> i) & 1) == 1){
                ans[i % 2]++;
            }
        }
        return ans;
    }
}
// @lc code=end
