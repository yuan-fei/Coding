/*
 * @lc app=leetcode id=2858 lang=java
 *
 * [2858] Minimum Edge Reversals So Every Node Is Reachable
 *
 * https://leetcode.com/problems/minimum-edge-reversals-so-every-node-is-reachable/description/
 *
 * algorithms
 * Hard (58.53%)
 * Likes:    306
 * Dislikes: 5
 * Total Accepted:    9K
 * Total Submissions: 15.3K
 * Testcase Example:  '4\n[[2,0],[2,1],[1,3]]'
 *
 * There is a simple directed graph with n nodes labeled from 0 to n - 1. The
 * graph would form a tree if its edges were bi-directional.
 * 
 * You are given an integer n and a 2D integer array edges, where edges[i] =
 * [ui, vi] represents a directed edge going from node ui to node vi.
 * 
 * An edge reversal changes the direction of an edge, i.e., a directed edge
 * going from node ui to node vi becomes a directed edge going from node vi to
 * node ui.
 * 
 * For every node i in the range [0, n - 1], your task is to independently
 * calculate the minimum number of edge reversals required so it is possible to
 * reach any other node starting from node i through a sequence of directed
 * edges.
 * 
 * Return an integer array answer, where answer[i] is the  minimum number of
 * edge reversals required so it is possible to reach any other node starting
 * from node i through a sequence of directed edges.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: n = 4, edges = [[2,0],[2,1],[1,3]]
 * Output: [1,1,0,2]
 * Explanation: The image above shows the graph formed by the edges.
 * For node 0: after reversing the edge [2,0], it is possible to reach any
 * other node starting from node 0.
 * So, answer[0] = 1.
 * For node 1: after reversing the edge [2,1], it is possible to reach any
 * other node starting from node 1.
 * So, answer[1] = 1.
 * For node 2: it is already possible to reach any other node starting from
 * node 2.
 * So, answer[2] = 0.
 * For node 3: after reversing the edges [1,3] and [2,1], it is possible to
 * reach any other node starting from node 3.
 * So, answer[3] = 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: n = 3, edges = [[1,2],[2,0]]
 * Output: [2,0,1]
 * Explanation: The image above shows the graph formed by the edges.
 * For node 0: after reversing the edges [2,0] and [1,2], it is possible to
 * reach any other node starting from node 0.
 * So, answer[0] = 2.
 * For node 1: it is already possible to reach any other node starting from
 * node 1.
 * So, answer[1] = 0.
 * For node 2: after reversing the edge [1, 2], it is possible to reach any
 * other node starting from node 2.
 * So, answer[2] = 1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ui == edges[i][0] < n
 * 0 <= vi == edges[i][1] < n
 * ui != vi
 * The input is generated such that if the edges were bi-directional, the graph
 * would be a tree.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] minEdgeReversals(int n, int[][] edges) {
        List<Integer>[] adj = buildAdj(n, edges);
        int[] ans = new int[n];
        ans[0] = dfs1(0, adj, edges, new boolean[n]);
        dfs2(0, -1, adj, edges, new boolean[n], ans);
        return ans;
    }

    int dfs1(int u, List<Integer>[] adj, int[][] edges, boolean[] seen){
        int ret = 0;
        seen[u] = true;
        for(int e : adj[u]){
            int v = edges[e][0] + edges[e][1] - u;
            if(!seen[v]){
                if(edges[e][0] == v){
                    ret++;
                }
                ret += dfs1(v, adj, edges, seen);
            }
        }
        return ret;
    }

    void dfs2(int u, int e, List<Integer>[] adj, int[][] edges, boolean[] seen, int[] cost){
        seen[u] = true;
        if(u != 0){
            if(edges[e][0] == u){
                cost[u] = cost[edges[e][1]] - 1;
            }
            else{
                cost[u] = cost[edges[e][0]] + 1;
            }
            // System.out.println(Arrays.asList(u, e, cost[u]));
        }
        for(int e1 : adj[u]){
            int v = edges[e1][0] + edges[e1][1] - u;
            if(!seen[v]){
                dfs2(v, e1, adj, edges, seen, cost);
            }
        }
    }

    List<Integer>[] buildAdj(int n, int[][] edges){
        List<Integer>[] adj = new List[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            adj[edges[i][0]].add(i);
            adj[edges[i][1]].add(i);
        }
        return adj;
    }
}
// @lc code=end
