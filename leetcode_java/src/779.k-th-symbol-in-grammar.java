/*
 * @lc app=leetcode id=779 lang=java
 *
 * [779] K-th Symbol in Grammar
 *
 * https://leetcode.com/problems/k-th-symbol-in-grammar/description/
 *
 * algorithms
 * Medium (40.95%)
 * Likes:    2301
 * Dislikes: 281
 * Total Accepted:    105.9K
 * Total Submissions: 258.7K
 * Testcase Example:  '1\n1'
 *
 * We build a table of n rows (1-indexed). We start by writing 0 in the 1^st
 * row. Now in every subsequent row, we look at the previous row and replace
 * each occurrence of 0 with 01, and each occurrence of 1 with 10.
 * 
 * 
 * For example, for n = 3, the 1^st row is 0, the 2^nd row is 01, and the 3^rd
 * row is 0110.
 * 
 * 
 * Given two integer n and k, return the k^th (1-indexed) symbol in the n^th
 * row of a table of n rows.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 1, k = 1
 * Output: 0
 * Explanation: row 1: 0
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2, k = 1
 * Output: 0
 * Explanation: 
 * row 1: 0
 * row 2: 01
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 2, k = 2
 * Output: 1
 * Explanation: 
 * row 1: 0
 * row 2: 01
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 30
 * 1 <= k <= 2^n - 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public int kthGrammar(int n, int k) {
        return solve(n, k - 1);
    }

    int solve(int n, int k){
        if(n == 1){
            return 0;
        }
        int x = solve(n - 1, k >> 1);
        // System.out.println(Arrays.asList(n - 1, k << 1, x, (k % 2 == 0)? x : 1 - x));
        return (k % 2 == 0)? x : 1 - x;
    }
}
// @lc code=end
