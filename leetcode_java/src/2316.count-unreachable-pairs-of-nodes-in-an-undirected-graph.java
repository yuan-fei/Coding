/*
 * @lc app=leetcode id=2316 lang=java
 *
 * [2316] Count Unreachable Pairs of Nodes in an Undirected Graph
 *
 * https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/description/
 *
 * algorithms
 * Medium (35.89%)
 * Likes:    222
 * Dislikes: 2
 * Total Accepted:    10.1K
 * Total Submissions: 28K
 * Testcase Example:  '3\n[[0,1],[0,2],[1,2]]'
 *
 * You are given an integer n. There is an undirected graph with n nodes,
 * numbered from 0 to n - 1. You are given a 2D integer array edges where
 * edges[i] = [ai, bi] denotes that there exists an undirected edge connecting
 * nodes ai and bi.
 * 
 * Return the number of pairs of different nodes that are unreachable from each
 * other.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, edges = [[0,1],[0,2],[1,2]]
 * Output: 0
 * Explanation: There are no pairs of nodes that are unreachable from each
 * other. Therefore, we return 0.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
 * Output: 14
 * Explanation: There are 14 pairs of nodes that are unreachable from each
 * other:
 * 
 * [[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
 * Therefore, we return 14.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^5
 * 0 <= edges.length <= 2 * 10^5
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * There are no repeated edges.
 * 
 * 
 */

// @lc code=start
class Solution {
    List<Integer>[] adj;
    boolean[] seen;
    public long countPairs(int n, int[][] edges) {
        adj = new List[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int[] e : edges){
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        seen = new boolean[n];
        long sum = 0;
        long ans = 0;
        for(int i = 0; i < n; i++){
            if(!seen[i]){
                int cnt = dfs(i);
                ans += sum * cnt;
                sum += cnt;
            }
        }
        return ans;
    }

    int dfs(int u){
        seen[u] = true;
        int cnt = 1;
        for(int v : adj[u]){
            if(!seen[v]){
                cnt += dfs(v);    
            }
        }
        return cnt;
    }
}
// @lc code=end
