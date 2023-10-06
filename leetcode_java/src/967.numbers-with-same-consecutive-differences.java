/*
 * @lc app=leetcode id=967 lang=java
 *
 * [967] Numbers With Same Consecutive Differences
 *
 * https://leetcode.com/problems/numbers-with-same-consecutive-differences/description/
 *
 * algorithms
 * Medium (57.58%)
 * Likes:    2726
 * Dislikes: 190
 * Total Accepted:    125.1K
 * Total Submissions: 217K
 * Testcase Example:  '3\n7'
 *
 * Given two integers n and k, return an array of all the integers of length n
 * where the difference between every two consecutive digits is k. You may
 * return the answer in any order.
 * 
 * Note that the integers should not have leading zeros. Integers as 02 and 043
 * are not allowed.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, k = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading
 * zeroes.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2, k = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 9
 * 0 <= k <= 9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> res = new ArrayList<>();
        for(int i = 1; i < 10; i++){
            rec(n, k, i, res);
        }
        
        int[] ans = new int[res.size()];
        for(int i = 0; i < res.size(); i++){
            ans[i] = res.get(i);
        }
        return ans;
    }

    void rec(int n, int k, int cur, List<Integer> res){
        if(("" + cur).length() == n){
            res.add(cur);
            return;
        }
        int last = cur % 10;
        
        
        if(last - k >= 0){
            rec(n, k, cur * 10 + last - k, res);
        }
        if(k > 0 && last + k < 10){
            rec(n, k, cur * 10 + last + k, res);
        }
    }
}
// @lc code=end
