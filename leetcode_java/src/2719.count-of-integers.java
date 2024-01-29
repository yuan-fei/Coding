/*
 * @lc app=leetcode id=2719 lang=java
 *
 * [2719] Count of Integers
 *
 * https://leetcode.com/problems/count-of-integers/description/
 *
 * algorithms
 * Hard (33.78%)
 * Likes:    494
 * Dislikes: 11
 * Total Accepted:    9.5K
 * Total Submissions: 28.2K
 * Testcase Example:  '"1"\n"12"\n1\n8'
 *
 * You are given two numeric strings num1 and num2 and two integers max_sum and
 * min_sum. We denote an integer x to be good if:
 * 
 * 
 * num1 <= x <= num2
 * min_sum <= digit_sum(x) <= max_sum.
 * 
 * 
 * Return the number of good integers. Since the answer may be large, return it
 * modulo 10^9 + 7.
 * 
 * Note that digit_sum(x) denotes the sum of the digits of x.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num1 = "1", num2 = "12", min_sum = 1, max_sum = 8
 * Output: 11
 * Explanation: There are 11 integers whose sum of digits lies between 1 and 8
 * are 1,2,3,4,5,6,7,8,10,11, and 12. Thus, we return 11.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num1 = "1", num2 = "5", min_sum = 1, max_sum = 5
 * Output: 5
 * Explanation: The 5 integers whose sum of digits lies between 1 and 5 are
 * 1,2,3,4, and 5. Thus, we return 5.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= num1 <= num2 <= 10^22
 * 1 <= min_sum <= max_sum <= 400
 * 
 * 
 */

// @lc code=start
import java.math.BigInteger;
class Solution {
    int MOD = (int)1e9 + 7;
    int[][][] cache = new int[23][2][401];
    public int count(String num1, String num2, int min_sum, int max_sum) {
        String oneLessThanNum1 = new BigInteger(num1).subtract(BigInteger.ONE).toString();
        return Math.floorMod(count(num2, min_sum, max_sum) - count(oneLessThanNum1, min_sum, max_sum), MOD);
    }

    int count(String num, int min_sum, int max_sum){
        for(int i = 0; i < cache.length; i++){
            for(int j = 0; j < cache[0].length; j++){
                Arrays.fill(cache[i][j], -1);
            }
        }
        return Math.floorMod(count(num, 0, true, max_sum) - count(num, 0, true, min_sum - 1), MOD);
    }


    int count(String n, int digit, boolean isTight, int max_sum){
        if(digit == n.length()){
            return 1;
        }
        int tightDim = (isTight ? 1 : 0);
        if(cache[digit][tightDim][max_sum] == -1){
            int total = 0;   
            int ub = isTight ? (n.charAt(digit) - '0') : 9;
            for(int i = 0; i <= ub; i++){
                if(max_sum >= i){
                    total += count(n, digit + 1, isTight && (i == ub), max_sum - i);    
                    total %= MOD;
                }
            }
            cache[digit][tightDim][max_sum] = total;
            // System.out.println(n + ", " + digit + ", " + isTight + ", " + max_sum + " = " + total);    
        }
        
        return cache[digit][tightDim][max_sum];
    }


}
// @lc code=end
