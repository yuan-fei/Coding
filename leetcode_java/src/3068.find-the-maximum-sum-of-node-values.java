/*
 * @lc app=leetcode id=3068 lang=java
 *
 * [3068] Find the Maximum Sum of Node Values
 *
 * https://leetcode.com/problems/find-the-maximum-sum-of-node-values/description/
 *
 * algorithms
 * Hard (65.70%)
 * Likes:    660
 * Dislikes: 94
 * Total Accepted:    73.1K
 * Total Submissions: 111.3K
 * Testcase Example:  '[1,2,1]\n3\n[[0,1],[0,2]]'
 *
 * There exists an undirected tree with n nodes numbered 0 to n - 1. You are
 * given a 0-indexed 2D integer array edges of length n - 1, where edges[i] =
 * [ui, vi] indicates that there is an edge between nodes ui and vi in the
 * tree. You are also given a positive integer k, and a 0-indexed array of
 * non-negative integers nums of length n, where nums[i] represents the value
 * of the node numbered i.
 * 
 * Alice wants the sum of values of tree nodes to be maximum, for which Alice
 * can perform the following operation any number of times (including zero) on
 * the tree:
 * 
 * 
 * Choose any edge [u, v] connecting the nodes u and v, and update their values
 * as follows:
 * 
 * 
 * nums[u] = nums[u] XOR k
 * nums[v] = nums[v] XOR k
 * 
 * 
 * 
 * 
 * Return the maximum possible sum of the values Alice can achieve by
 * performing the operation any number of times.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,1], k = 3, edges = [[0,1],[0,2]]
 * Output: 6
 * Explanation: Alice can achieve the maximum sum of 6 using a single
 * operation:
 * - Choose the edge [0,2]. nums[0] and nums[2] become: 1 XOR 3 = 2, and the
 * array nums becomes: [1,2,1] -> [2,2,2].
 * The total sum of values is 2 + 2 + 2 = 6.
 * It can be shown that 6 is the maximum achievable sum of values.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,3], k = 7, edges = [[0,1]]
 * Output: 9
 * Explanation: Alice can achieve the maximum sum of 9 using a single
 * operation:
 * - Choose the edge [0,1]. nums[0] becomes: 2 XOR 7 = 5 and nums[1] become: 3
 * XOR 7 = 4, and the array nums becomes: [2,3] -> [5,4].
 * The total sum of values is 5 + 4 = 9.
 * It can be shown that 9 is the maximum achievable sum of values.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [7,7,7,7,7,7], k = 3, edges = [[0,1],[0,2],[0,3],[0,4],[0,5]]
 * Output: 42
 * Explanation: The maximum achievable sum is 42 which can be achieved by Alice
 * performing no operations.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n == nums.length <= 2 * 10^4
 * 1 <= k <= 10^9
 * 0 <= nums[i] <= 10^9
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= edges[i][0], edges[i][1] <= n - 1
 * The input is generated such that edges represent a valid tree.
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    long[][] dp;
    List<Integer>[] adj;
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        int n = nums.length;
        adj = buildAdj(edges, n);
        dp = new long[n][];
        long ans = maximumValueSum(nums, k, 0, -1)[0];
        // System.out.println(Arrays.deepToString(dp));
        return ans;
    }
    
    @SuppressWarnings("unchecked")
    private List<Integer>[] buildAdj(int[][] edges, int n) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        return adj;
    }

    long[] maximumValueSum(int[] nums, int k, int u, int p){
        if(dp[u] == null){
            dp[u] = new long[2];
            long[] maxValue = {0, -1};
            boolean hasChild = false;
            for (int v : adj[u]) {
                if(v != p){
                    hasChild = true;
                    long[] newMaxValue = new long[2];
                    long[] childMax = maximumValueSum(nums, k, v, u);
                    newMaxValue[0] = Math.max(maxValue[0] + childMax[0], maxValue[1] == -1 ? -1 : maxValue[1] + childMax[1]);
                    newMaxValue[1] = Math.max(maxValue[1] == -1 ? -1 : maxValue[1] + childMax[0], maxValue[0] + childMax[1]);
                    maxValue = newMaxValue;
                }
            }
            
            for(int flipped : new int[]{0, 1}){
                int[] initValue = new int[2];
                initValue[1 - flipped] += nums[u] ^ k;
                initValue[flipped] += nums[u];    
                dp[u][flipped] = Math.max(dp[u][flipped], maxValue[0] + initValue[0]);
                if(hasChild){
                    dp[u][flipped] = Math.max(dp[u][flipped], maxValue[1] + initValue[1]);
                }
            }
        }
        return dp[u];
    }

}
// @lc code=end
