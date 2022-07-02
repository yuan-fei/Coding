/*
 * @lc app=leetcode id=2318 lang=java
 *
 * [2318] Number of Distinct Roll Sequences
 *
 * https://leetcode.com/problems/number-of-distinct-roll-sequences/description/
 *
 * algorithms
 * Hard (52.92%)
 * Likes:    175
 * Dislikes: 4
 * Total Accepted:    4.1K
 * Total Submissions: 7.7K
 * Testcase Example:  '4'
 *
 * You are given an integer n. You roll a fair 6-sided dice n times. Determine
 * the total number of distinct sequences of rolls possible such that the
 * following conditions are satisfied:
 * 
 * 
 * The greatest common divisor of any adjacent values in the sequence is equal
 * to 1.
 * There is at least a gap of 2 rolls between equal valued rolls. More
 * formally, if the value of the i^th roll is equal to the value of the j^th
 * roll, then abs(i - j) > 2.
 * 
 * 
 * Return the total number of distinct sequences possible. Since the answer may
 * be very large, return it modulo 10^9 + 7.
 * 
 * Two sequences are considered distinct if at least one element is
 * different.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 4
 * Output: 184
 * Explanation: Some of the possible sequences are (1, 2, 3, 4), (6, 1, 2, 3),
 * (1, 2, 3, 1), etc.
 * Some invalid sequences are (1, 2, 1, 3), (1, 2, 3, 6).
 * (1, 2, 1, 3) is invalid since the first and third roll have an equal value
 * and abs(1 - 3) = 2 (i and j are 1-indexed).
 * (1, 2, 3, 6) is invalid since the greatest common divisor of 3 and 6 = 3.
 * There are a total of 184 distinct sequences possible, so we return 184.
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2
 * Output: 22
 * Explanation: Some of the possible sequences are (1, 2), (2, 1), (3, 2).
 * Some invalid sequences are (3, 6), (2, 4) since the greatest common divisor
 * is not equal to 1.
 * There are a total of 22 distinct sequences possible, so we return 22.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int distinctSequences(int n) {
        if(n == 1){
            return 6;
        }
        int MOD = (int)1e9 + 7;
        int[][] dp = new int[7][7];
        for(int j1 = 1; j1 <= 6; j1++){
            for(int j2 = 1; j2 <= 6; j2++){
                if(j1 != j2 && gcd(j1, j2) == 1){
                    dp[j1][j2] = 1;
                }
            }
        }
        for(int i = 3; i <= n; i++){
            int[][] newDp = new int[7][7];
            for(int j1 = 1; j1 <= 6; j1++){
                for(int j2 = 1; j2 <= 6; j2++){
                    for(int j3 = 1; j3 <= 6; j3++){
                        if(j3 != j1 && j3 != j2 && gcd(j3, j2) == 1){
                            newDp[j2][j3] += dp[j1][j2];
                            newDp[j2][j3] %= MOD;
                        }
                    }
                }
            }
            dp = newDp;
        }
        int ret = 0;
        for(int j1 = 1; j1 <= 6; j1++){
            for(int j2 = 1; j2 <= 6; j2++){
                ret += dp[j1][j2];
                ret %= MOD;
            }
        }
        return ret;
    }

    int gcd(int a, int b){
        if(b == 0){
            return a;
        }
        else{
            return gcd(b, a % b);
        }
    }
}
// @lc code=end
