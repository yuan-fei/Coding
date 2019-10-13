/*
 * @lc app=leetcode id=1220 lang=java
 *
 * [1220] Count Vowels Permutation
 *
 * https://leetcode.com/problems/count-vowels-permutation/description/
 *
 * algorithms
 * Hard (49.88%)
 * Total Accepted:    2.4K
 * Total Submissions: 4.7K
 * Testcase Example:  '1'
 *
 * Given an integer n, your task is to count how many strings of length n can
 * be formed under the following rules:
 * 
 * 
 * Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
 * Each vowel 'a' may only be followed by an 'e'.
 * Each vowel 'e' may only be followed by an 'a' or an 'i'.
 * Each vowel 'i' may not be followed by another 'i'.
 * Each vowel 'o' may only be followed by an 'i' or a 'u'.
 * Each vowel 'u' may only be followed by an 'a'.
 * 
 * 
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 1
 * Output: 5
 * Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2
 * Output: 10
 * Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io",
 * "iu", "oi", "ou" and "ua".
 * 
 * 
 * Example 3: 
 * 
 * 
 * Input: n = 5
 * Output: 68
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 2 * 10^4
 * 
 * 
 */
class Solution {
	long mod = 1000000007L;
    public int countVowelPermutation(int n) {
        int[][] legalPrecedings = {
        	{1,2,4},
        	{0,2},
        	{1,3},
        	{2},
        	{2,3}
        };
        
        long[] dp = new long[5];
        Arrays.fill(dp, 1L);
        for (int i = 2; i <= n; i++) {
        	long[] newDp = new long[5];
        	for (int j = 0; j < 5; j++) {
        		for(int k = 0; k < legalPrecedings[j].length; k++){
        			newDp[j] = add(newDp[j], dp[legalPrecedings[j][k]]);
        		}
        	}
        	dp = newDp;
        }
        long sum = 0;
        for (int j = 0; j < 5; j++) {
        	sum = add(sum, dp[j]);
        }
        return (int)sum;
    }

    private long add(long a, long b){
    	return (a + b) % mod;
    }
}
