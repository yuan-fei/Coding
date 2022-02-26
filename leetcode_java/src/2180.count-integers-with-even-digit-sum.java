/*
 * @lc app=leetcode id=2180 lang=java
 *
 * [2180] Count Integers With Even Digit Sum
 *
 * https://leetcode.com/problems/count-integers-with-even-digit-sum/description/
 *
 * algorithms
 * Easy (62.61%)
 * Likes:    21
 * Dislikes: 4
 * Total Accepted:    10.2K
 * Total Submissions: 16.3K
 * Testcase Example:  '4'
 *
 * Given a positive integer num, return the number of positive integers less
 * than or equal to num whose digit sums are even.
 * 
 * The digit sum of a positive integer is the sum of all its digits.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = 4
 * Output: 2
 * Explanation:
 * The only integers less than or equal to 4 whose digit sums are even are 2
 * and 4.    
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = 30
 * Output: 14
 * Explanation:
 * The 14 integers less than or equal to 30 whose digit sums are even are
 * 2, 4, 6, 8, 11, 13, 15, 17, 19, 20, 22, 24, 26, and 28.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= num <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countEven(int num) {
        int ans = 0;
        for(int i = 1; i <= num; i++){
            if(digitSum(i) % 2 == 0){
                ans++;
            }
        }
        return ans;
    }
    int digitSum(int n){
        int ret = 0;
        while(n != 0){
            ret += n % 10;
            n /= 10;
        }
        return ret;
    }
}
// @lc code=end
