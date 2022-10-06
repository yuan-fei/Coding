/*
 * @lc app=leetcode id=2429 lang=java
 *
 * [2429] Minimize XOR
 *
 * https://leetcode.com/problems/minimize-xor/description/
 *
 * algorithms
 * Medium (39.15%)
 * Likes:    154
 * Dislikes: 11
 * Total Accepted:    11.5K
 * Total Submissions: 29.5K
 * Testcase Example:  '3\n5'
 *
 * Given two positive integers num1 and num2, find the integer x such
 * that:
 * 
 * 
 * x has the same number of set bits as num2, and
 * The value x XOR num1 is minimal.
 * 
 * 
 * Note that XOR is the bitwise XOR operation.
 * 
 * Return the integer x. The test cases are generated such that x is uniquely
 * determined.
 * 
 * The number of set bits of an integer is the number of 1's in its binary
 * representation.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num1 = 3, num2 = 5
 * Output: 3
 * Explanation:
 * The binary representations of num1 and num2 are 0011 and 0101, respectively.
 * The integer 3 has the same number of set bits as num2, and the value 3 XOR 3
 * = 0 is minimal.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num1 = 1, num2 = 12
 * Output: 3
 * Explanation:
 * The binary representations of num1 and num2 are 0001 and 1100, respectively.
 * The integer 3 has the same number of set bits as num2, and the value 3 XOR 1
 * = 2 is minimal.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= num1, num2 <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimizeXor(int num1, int num2) {
        int cnt1 = Integer.bitCount(num1);
        int cnt2 = Integer.bitCount(num2);
        if(cnt1 >= cnt2){
            int ans = 0;
            for(int i = 31; i >= 0; i--){
                if(((num1 >> i) & 1) == 1){
                    ans |= 1 << i;
                    cnt2--;
                    if(cnt2 == 0){
                        break;
                    }
                }
            }
            return ans;
        }
        else{
            int ans = num1;
            cnt2 -= cnt1;
            for(int i = 0; i < 32; i++){
                if(((num1 >> i) & 1) == 0){
                    ans |= 1 << i;
                    cnt2--;
                    if(cnt2 == 0){
                        break;
                    }
                }
            }
            return ans;
        }
    }
}
// @lc code=end
