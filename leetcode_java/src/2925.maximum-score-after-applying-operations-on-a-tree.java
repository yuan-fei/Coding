/*
 * @lc app=leetcode id=2925 lang=java
 *
 * [2925] Maximum Score After Applying Operations on a Tree
 *
 * https://leetcode.com/problems/maximum-score-after-applying-operations-on-a-tree/description/
 *
 * algorithms
 * Medium (48.05%)
 * Likes:    327
 * Dislikes: 71
 * Total Accepted:    13.6K
 * Total Submissions: 27.5K
 * Testcase Example:  '[[0,1],[0,2],[0,3],[2,4],[4,5]]\n[5,2,5,2,1,1]'
 *
 * There is an undirected tree with n nodes labeled from 0 to n - 1, and rooted
 * at node 0. You are given a 2D integer array edges of length n - 1, where
 * edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi
 * in the tree.
 * 
 * You are also given a 0-indexed integer array values of length n, where
 * values[i] is the value associated with the i^th node.
 * 
 * You start with a score of 0. In one operation, you can:
 * 
 * 
 * Pick any node i.
 * Add values[i] to your score.
 * Set values[i] to 0.
 * 
 * 
 * A tree is healthy if the sum of values on the path from the root to any leaf
 * node is different than zero.
 * 
 * Return the maximum score you can obtain after performing these operations on
 * the tree any number of times so that it remains healthy.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: edges = [[0,1],[0,2],[0,3],[2,4],[4,5]], values = [5,2,5,2,1,1]
 * Output: 11
 * Explanation: We can choose nodes 1, 2, 3, 4, and 5. The value of the root is
 * non-zero. Hence, the sum of values on the path from the root to any leaf is
 * different than zero. Therefore, the tree is healthy and the score is
 * values[1] + values[2] + values[3] + values[4] + values[5] = 11.
 * It can be shown that 11 is the maximum score obtainable after any number of
 * operations on the tree.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], values =
 * [20,10,9,7,4,3,5]
 * Output: 40
 * Explanation: We can choose nodes 0, 2, 3, and 4.
 * - The sum of values on the path from 0 to 4 is equal to 10.
 * - The sum of values on the path from 0 to 3 is equal to 10.
 * - The sum of values on the path from 0 to 5 is equal to 3.
 * - The sum of values on the path from 0 to 6 is equal to 5.
 * Therefore, the tree is healthy and the score is values[0] + values[2] +
 * values[3] + values[4] = 40.
 * It can be shown that 40 is the maximum score obtainable after any number of
 * operations on the tree.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 2 * 10^4
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * values.length == n
 * 1 <= values[i] <= 10^9
 * The input is generated such that edges represents a valid tree.
 * 
 * 
 */

// @lc code=start
class Solution {
    List<Integer>[] adj;
    int[] v;
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        v = values;
        adj = buildAdj(edges.length + 1, edges, false);
        long[] ret = dfs(0, -1);
        return ret[1];
    }

    List<Integer>[] buildAdj(int n, int[][] edges, boolean directed) {
        @SuppressWarnings("unchecked")
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            if (!directed) {
                adj[e[1]].add(e[0]);
            }
        }
        return adj;
    }

    long[] dfs(int r, int p){
        long[] ans = new long[]{0, 0};
        boolean isLeaf = true;
        for(int c : adj[r]){
            if(p != c){
                isLeaf = false;
                long[] ret = dfs(c, r);
                ans[0] += ret[0];
                ans[1] += ret[1];
            }
        }
        if(!isLeaf){
            ans[1] = Math.max(ans[0], ans[1] + v[r]);
        }
        ans[0] += v[r];    
        // System.out.println(r + ", " + Arrays.toString(ans));
        return ans;
    }
}
// @lc code=end
