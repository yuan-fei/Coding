/*
 * @lc app=leetcode id=2443 lang=java
 *
 * [2443] Sum of Number and Its Reverse
 *
 * https://leetcode.com/problems/sum-of-number-and-its-reverse/description/
 *
 * algorithms
 * Medium (38.72%)
 * Likes:    47
 * Dislikes: 96
 * Total Accepted:    12.8K
 * Total Submissions: 33K
 * Testcase Example:  '443'
 *
 * Given a non-negative integer num, return true if num can be expressed as the
 * sum of any non-negative integer and its reverse, or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = 443
 * Output: true
 * Explanation: 172 + 271 = 443 so we return true.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = 63
 * Output: false
 * Explanation: 63 cannot be expressed as the sum of a non-negative integer and
 * its reverse so we return false.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: num = 181
 * Output: true
 * Explanation: 140 + 041 = 181 so we return true. Note that when a number is
 * reversed, there may be leading zeros.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= num <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean sumOfNumberAndReverse(int num) {
        for(int i = 0; i <= num; i++){
            if(i + reverse(i) == num){
                return true;
            }
        }    
        return false;
    }

    int reverse(int x){
        int ret = 0;
        while(x != 0){
            int d = x % 10;
            x /= 10;
            ret *= 10;
            ret += d;
        }
        return ret;
    }
}
// @lc code=end
