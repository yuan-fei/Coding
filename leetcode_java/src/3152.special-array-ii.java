/*
 * @lc app=leetcode id=3152 lang=java
 *
 * [3152] Special Array II
 *
 * https://leetcode.com/problems/special-array-ii/description/
 *
 * algorithms
 * Medium (45.73%)
 * Likes:    876
 * Dislikes: 62
 * Total Accepted:    133K
 * Total Submissions: 291.1K
 * Testcase Example:  '[3,4,1,2,6]\n[[0,4]]'
 *
 * An array is considered special if every pair of its adjacent elements
 * contains two numbers with different parity.
 * 
 * You are given an array of integer nums and a 2D integer matrix queries,
 * where for queries[i] = [fromi, toi] your task is to check that subarray
 * nums[fromi..toi] is special or not.
 * 
 * Return an array of booleans answer such that answer[i] is true if
 * nums[fromi..toi] is special.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,4,1,2,6], queries = [[0,4]]
 * 
 * Output: [false]
 * 
 * Explanation:
 * 
 * The subarray is [3,4,1,2,6]. 2 and 6 are both even.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [4,3,1,6], queries = [[0,2],[2,3]]
 * 
 * Output: [false,true]
 * 
 * Explanation:
 * 
 * 
 * The subarray is [4,3,1]. 3 and 1 are both odd. So the answer to this query
 * is false.
 * The subarray is [1,6]. There is only one pair: (1,6) and it contains numbers
 * with different parity. So the answer to this query is true.
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * 0 <= queries[i][0] <= queries[i][1] <= nums.length - 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        int[] maxSpecialEnd = new int[n];
        maxSpecialEnd[n - 1] = n - 1;
        for(int i = n - 2; i >= 0; i--){
            if(nums[i] % 2 != nums[i + 1] % 2){
                maxSpecialEnd[i] = maxSpecialEnd[i + 1];
            }
            else{
                maxSpecialEnd[i] = i;
            }
        }
        boolean[] ans = new boolean[m];
        for(int i = 0; i < m; i++){
            int start = queries[i][0];
            int end = queries[i][1];
            ans[i] = (maxSpecialEnd[start] >= end);
        }
        return ans;
    }
}
// @lc code=end
