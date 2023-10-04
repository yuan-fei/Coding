/*
 * @lc app=leetcode id=960 lang=java
 *
 * [960] Delete Columns to Make Sorted III
 *
 * https://leetcode.com/problems/delete-columns-to-make-sorted-iii/description/
 *
 * algorithms
 * Hard (57.33%)
 * Likes:    565
 * Dislikes: 13
 * Total Accepted:    13.3K
 * Total Submissions: 23.1K
 * Testcase Example:  '["babca","bbazb"]'
 *
 * You are given an array of n strings strs, all of the same length.
 * 
 * We may choose any deletion indices, and we delete all the characters in
 * those indices for each string.
 * 
 * For example, if we have strs = ["abcdef","uvwxyz"] and deletion indices {0,
 * 2, 3}, then the final array after deletions is ["bef", "vyz"].
 * 
 * Suppose we chose a set of deletion indices answer such that after deletions,
 * the final array has every string (row) in lexicographic order. (i.e.,
 * (strs[0][0] <= strs[0][1] <= ... <= strs[0][strs[0].length - 1]), and
 * (strs[1][0] <= strs[1][1] <= ... <= strs[1][strs[1].length - 1]), and so
 * on). Return the minimum possible value of answer.length.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: strs = ["babca","bbazb"]
 * Output: 3
 * Explanation: After deleting columns 0, 1, and 4, the final array is strs =
 * ["bc", "az"].
 * Both these rows are individually in lexicographic order (ie. strs[0][0] <=
 * strs[0][1] and strs[1][0] <= strs[1][1]).
 * Note that strs[0] > strs[1] - the array strs is not necessarily in
 * lexicographic order.
 * 
 * Example 2:
 * 
 * 
 * Input: strs = ["edcba"]
 * Output: 4
 * Explanation: If we delete less than 4 columns, the only row will not be
 * lexicographically sorted.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: strs = ["ghi","def","abc"]
 * Output: 0
 * Explanation: All rows are already lexicographically sorted.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == strs.length
 * 1 <= n <= 100
 * 1 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 * 
 * 
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        boolean[][] ordered = new boolean[m + 1][m + 1];

        for(int c1 = 0; c1 < m; c1++){
            ordered[0][c1 + 1] = true;
            for(int c2 = c1; c2 < m; c2++){
                ordered[c1 + 1][c2 + 1] = isTwoColumnsOrdered(strs, c1, c2);
            }
        }
        int[] dp = new int[m + 1];
        dp[0] = m;
        int ans = m;
        for(int i = 1; i <= m; i++){
            dp[i] = m + 1;
            for(int j = i - 1; j >= 0; j--){
                if(ordered[j][i]){
                    dp[i] = Math.min(dp[i], dp[j] - 1);
                }
            }
            ans = Math.min(ans, dp[i]);
        }
        // System.out.println(Arrays.deepToString(ordered));
        return ans;
    }

    boolean isTwoColumnsOrdered(String[] strs, int c1, int c2){
        int n = strs.length;
        for(int i = 0; i < n; i++){
            if(strs[i].charAt(c1) > strs[i].charAt(c2)){
                return false;
            }
        }
        return true;
    }
}
// @lc code=end
