/*
 * @lc app=leetcode id=903 lang=java
 *
 * [903] Valid Permutations for DI Sequence
 *
 * https://leetcode.com/problems/valid-permutations-for-di-sequence/description/
 *
 * algorithms
 * Hard (57.57%)
 * Likes:    677
 * Dislikes: 41
 * Total Accepted:    14.2K
 * Total Submissions: 24.7K
 * Testcase Example:  '"DID"'
 *
 * You are given a string s of length n where s[i] is either:
 * 
 * 
 * 'D' means decreasing, or
 * 'I' means increasing.
 * 
 * 
 * A permutation perm of n + 1 integers of all the integers in the range [0, n]
 * is called a valid permutation if for all valid i:
 * 
 * 
 * If s[i] == 'D', then perm[i] > perm[i + 1], and
 * If s[i] == 'I', then perm[i] < perm[i + 1].
 * 
 * 
 * Return the number of valid permutations perm. Since the answer may be large,
 * return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "DID"
 * Output: 5
 * Explanation: The 5 valid permutations of (0, 1, 2, 3) are:
 * (1, 0, 3, 2)
 * (2, 0, 3, 1)
 * (2, 1, 3, 0)
 * (3, 0, 2, 1)
 * (3, 1, 2, 0)
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "D"
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == s.length
 * 1 <= n <= 200
 * s[i] is either 'I' or 'D'.
 * 
 * 
 */

// @lc code=start
class Solution {
    static final int MOD = (int)1e9 + 7;
    public int numPermsDISequence(String s) {
        int[][] pSum = new int[s.length() + 1][s.length() + 2];
        pSum[0][1] = 1;
        
        for(int i = 1; i <= s.length(); i++){
            for(int j = 0; j <= i; j++){
                pSum[i][j + 1] = add(pSum[i][j], 
                    (s.charAt(i - 1) == 'D') ? add(pSum[i - 1][i], -pSum[i - 1][j]) : pSum[i - 1][j]);
            }
        }
        return pSum[s.length()][s.length() + 1];
    }

    int add(int x, int y){
        return Math.floorMod(x + y, MOD);
    }
}
// @lc code=end
