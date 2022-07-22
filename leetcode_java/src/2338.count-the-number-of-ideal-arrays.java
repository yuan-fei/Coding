/*
 * @lc app=leetcode id=2338 lang=java
 *
 * [2338] Count the Number of Ideal Arrays
 *
 * https://leetcode.com/problems/count-the-number-of-ideal-arrays/description/
 *
 * algorithms
 * Hard (19.23%)
 * Likes:    146
 * Dislikes: 12
 * Total Accepted:    1.8K
 * Total Submissions: 9.4K
 * Testcase Example:  '2\n5'
 *
 * You are given two integers n and maxValue, which are used to describe an
 * ideal array.
 * 
 * A 0-indexed integer array arr of length n is considered ideal if the
 * following conditions hold:
 * 
 * 
 * Every arr[i] is a value from 1 to maxValue, for 0 <= i < n.
 * Every arr[i] is divisible by arr[i - 1], for 0 < i < n.
 * 
 * 
 * Return the number of distinct ideal arrays of length n. Since the answer may
 * be very large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 2, maxValue = 5
 * Output: 10
 * Explanation: The following are the possible ideal arrays:
 * - Arrays starting with the value 1 (5 arrays): [1,1], [1,2], [1,3], [1,4],
 * [1,5]
 * - Arrays starting with the value 2 (2 arrays): [2,2], [2,4]
 * - Arrays starting with the value 3 (1 array): [3,3]
 * - Arrays starting with the value 4 (1 array): [4,4]
 * - Arrays starting with the value 5 (1 array): [5,5]
 * There are a total of 5 + 2 + 1 + 1 + 1 = 10 distinct ideal arrays.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 5, maxValue = 3
 * Output: 11
 * Explanation: The following are the possible ideal arrays:
 * - Arrays starting with the value 1 (9 arrays): 
 * ⁠  - With no other distinct values (1 array): [1,1,1,1,1] 
 * ⁠  - With 2^nd distinct value 2 (4 arrays): [1,1,1,1,2], [1,1,1,2,2],
 * [1,1,2,2,2], [1,2,2,2,2]
 * ⁠  - With 2^nd distinct value 3 (4 arrays): [1,1,1,1,3], [1,1,1,3,3],
 * [1,1,3,3,3], [1,3,3,3,3]
 * - Arrays starting with the value 2 (1 array): [2,2,2,2,2]
 * - Arrays starting with the value 3 (1 array): [3,3,3,3,3]
 * There are a total of 9 + 1 + 1 = 11 distinct ideal arrays.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 10^4
 * 1 <= maxValue <= 10^4
 * 
 * 
 */

// @lc code=start
import java.math.BigInteger;

class Solution {
    public int idealArrays(int n, int maxValue) {
        int m = 1000000007;
        BigInteger res = BigInteger.ZERO;
        long[][] dp = new long[15][maxValue + 1]; 
        Map<Integer, List<Integer>> map = buildMap(maxValue);
        
        // step 1: compute dp for the alternative problem (strictly increasing case)
        for (int i = 1; i <= maxValue; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= n && i <= 14; i++) {
            for (int j = 1; j <= maxValue; j++) {
                for (int k : map.get(j)) {
                    dp[i][j] += dp[i - 1][k];
                    dp[i][j] %= m;
                }
            }
        }
        for (int i = 1; i <= n && i <= 14; i++) {
            for (int j = 1; j <= maxValue; j++) {
                dp[i][0] += dp[i][j];
                dp[i][0] %= m; // dp[i][0] = number of ideal arrays (strictly increasing case) of length i
            }
        }
        
        // step 2: use combinatorics to get the final answer for the actual problem from the alternative problem (strictly increasing case)
        for (int i = 1; i <= n && i <= 14; i++) {
            res = res.add(nCk(n - 1, i - 1).multiply(BigInteger.valueOf(dp[i][0])));
            res = res.mod(BigInteger.valueOf(m));
        }
        return res.intValue();
    }
    
    // helper function to compute "n choose k" 
    private BigInteger nCk(int n, int k) {
        BigInteger res = BigInteger.ONE;
        for (int i = 1; i <=k; i++) {
            res = res.multiply(BigInteger.valueOf(n - (i - 1))).divide(BigInteger.valueOf(i));
        }
        return res;
    }
    
    // helper funciton to build map {Integer -> {its divisors}}
    private Map<Integer, List<Integer>> buildMap(int maxValue) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= maxValue; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int i = 1; i <= maxValue; i++) {
            int j = i * 2; // strictly increasing
            while (j <= maxValue) {
                map.get(j).add(i);
                j += i;
            }
        }
        return map;
    }
}
// @lc code=end
