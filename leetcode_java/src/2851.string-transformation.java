/*
 * @lc app=leetcode id=2851 lang=java
 *
 * [2851] String Transformation
 *
 * https://leetcode.com/problems/string-transformation/description/
 *
 * algorithms
 * Hard (27.96%)
 * Likes:    165
 * Dislikes: 34
 * Total Accepted:    4.1K
 * Total Submissions: 13.8K
 * Testcase Example:  '"abcd"\n"cdab"\n2'
 *
 * You are given two strings s and t of equal length n. You can perform the
 * following operation on the string s:
 * 
 * 
 * Remove a suffix of s of length l where 0 < l < n and append it at the start
 * of s.
 * For example, let s = 'abcd' then in one operation you can remove the suffix
 * 'cd' and append it in front of s making s = 'cdab'.
 * 
 * 
 * You are also given an integer k. Return the number of ways in which s can be
 * transformed into t in exactly k operations.
 * 
 * Since the answer can be large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abcd", t = "cdab", k = 2
 * Output: 2
 * Explanation: 
 * First way:
 * In first operation, choose suffix from index = 3, so resulting s = "dabc".
 * In second operation, choose suffix from index = 3, so resulting s = "cdab".
 * 
 * Second way:
 * In first operation, choose suffix from index = 1, so resulting s = "bcda".
 * In second operation, choose suffix from index = 1, so resulting s =
 * "cdab".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "ababab", t = "ababab", k = 1
 * Output: 2
 * Explanation: 
 * First way:
 * Choose suffix from index = 2, so resulting s = "ababab".
 * 
 * Second way:
 * Choose suffix from index = 4, so resulting s = "ababab".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= s.length <= 5 * 10^5
 * 1 <= k <= 10^15
 * s.length == t.length
 * s and t consist of only lowercase English alphabets.
 * 
 * 
 */

// @lc code=start
class Solution {
    // https://codeforces.com/blog/entry/60442
    long BASE = (long)37;
    long MOD = (long)1e9 + 7;
    public int numberOfWays(String s, String t, long k) {
        long ans = 0;
        int n = s.length();
        long hs = getHash(s);
        long ht = getHash(t);
        long HIGHBASE = 1;
        for(int i = 0; i < n - 1; i++){
            HIGHBASE = modMultiply(HIGHBASE, BASE);
        }
        List<Integer> checkPoints = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(hs == ht){
                checkPoints.add(i);
            }
            hs = modAdd(hs, -modMultiply(s.charAt(i), HIGHBASE));
            hs = modMultiply(hs, BASE);
            hs = modAdd(hs, s.charAt(i));
        }
        long[][] pow = quickPow(new long[][]{{0, 1},{n - 1, n - 2}}, k);
        // System.out.println(checkPoints);
        // System.out.println(Arrays.deepToString(pow));
        for (int p : checkPoints) {
            ans = modAdd(ans, pow[0][Math.min(p, 1)]);
        }
        return (int)ans;
    }

    long getHash(String s){
        long ret = 0;
        for(char c : s.toCharArray()){
            ret = modMultiply(ret, BASE);
            ret = modAdd(ret, c);
        }
        return ret;
    }

    long[][] quickPow(long[][] base, long exp){
        if(exp == 1){
            return base;
        }
        else if(exp % 2 == 1){
            return matrixMultiply(base, quickPow(base, exp - 1));
        }
        else{
            return quickPow(matrixMultiply(base, base), exp / 2);
        }
    }

    long[][] matrixMultiply(long[][] a, long[][] b){
        long[][] ret = new long[a.length][b[0].length];
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < b[0].length; j++){
                for(int k = 0; k < a[i].length; k++){
                    long t = modMultiply(a[i][k], b[k][j]);
                    ret[i][j] = modAdd(ret[i][j], t);
                }
            }
        }
        return ret;
    }

    long modAdd(long a, long b){
        return Math.floorMod(a + b, MOD);
    }

    long modMultiply(long a , long b){
        return (a * b) % MOD;
    }
}
// @lc code=end
