/*
 * @lc app=leetcode id=2165 lang=java
 *
 * [2165] Smallest Value of the Rearranged Number
 *
 * https://leetcode.com/problems/smallest-value-of-the-rearranged-number/description/
 *
 * algorithms
 * Medium (48.45%)
 * Likes:    102
 * Dislikes: 4
 * Total Accepted:    10.8K
 * Total Submissions: 22.1K
 * Testcase Example:  '310'
 *
 * You are given an integer num. Rearrange the digits of num such that its
 * value is minimized and it does not contain any leading zeros.
 * 
 * Return the rearranged number with minimal value.
 * 
 * Note that the sign of the number does not change after rearranging the
 * digits.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = 310
 * Output: 103
 * Explanation: The possible arrangements for the digits of 310 are 013, 031,
 * 103, 130, 301, 310. 
 * The arrangement with the smallest value that does not contain any leading
 * zeros is 103.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = -7605
 * Output: -7650
 * Explanation: Some possible arrangements for the digits of -7605 are -7650,
 * -6705, -5076, -0567.
 * The arrangement with the smallest value that does not contain any leading
 * zeros is -7650.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * -10^15 <= num <= 10^15
 * 
 * 
 */

// @lc code=start
class Solution {
    public long smallestNumber(long num) {
        if(num == 0){
            return 0;
        }
        boolean negative = num < 0;
        num = Math.abs(num);
        char[] nChars = ("" + num).toCharArray();
        int[] cnt = new int[10];
        for(char c : nChars){
            cnt[c - '0']++;
        }
        if(negative){
            String ret = "";
            for(int i = 9; i >= 0; i--){
                while(cnt[i] != 0){
                    ret += i;
                    cnt[i]--;
                }
            }
            return -Long.parseLong(ret);
        }
        else{
            String ret = "";
            for(int i = 1; i < 10; i++){
                while(cnt[i] != 0){
                    ret += i;
                    cnt[i]--;
                    while(cnt[0] > 0){
                        ret += '0';
                        cnt[0]--;
                    }
                }
            }
            return Long.parseLong(ret);
        }
    }


}
// @lc code=end
