/*
 * @lc app=leetcode id=1012 lang=java
 *
 * [1012] Numbers With Repeated Digits
 *
 * https://leetcode.com/problems/numbers-with-repeated-digits/description/
 *
 * algorithms
 * Hard (35.29%)
 * Total Accepted:    2.9K
 * Total Submissions: 8.2K
 * Testcase Example:  '20'
 *
 * Given a positive integer N, return the number of positive integers less than
 * or equal to N that have at least 1 repeated digit.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: 20
 * Output: 1
 * Explanation: The only positive number (<= 20) with at least 1 repeated digit
 * is 11.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 100
 * Output: 10
 * Explanation: The positive numbers (<= 100) with atleast 1 repeated digit are
 * 11, 22, 33, 44, 55, 66, 77, 88, 99, and 100.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: 1000
 * Output: 262
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= N <= 10^9
 * 
 * 
 * 
 */
class Solution {
    String s;
    int[][][] dp;
    public int numDupDigitsAtMostN(int N) {
        s = N+"";
        dp = new int[s.length()][2][1<<10];
        return N + 1 - solve(0,false,0); //including 0
    }
    
    int solve(int i, boolean isLess, int mask){
        if(i == s.length()){
            return 1;
        }
        int x = s.charAt(i) - '0';
        int max = isLess ? 9 : x;
        int r = 0;
        for(int k=0; k<=max; k++){
            if((mask & (1<<k)) == 0){
                boolean isLessNext = isLess || k < max;
                int newMask = mask | (1<<k);
                if(k == 0 && mask == 0){
                    newMask = 0;
                }
                r += solve(i+1, isLessNext, newMask);    
            }
        }
        return dp[i][isLess?1:0][mask] = r;
    }
}
