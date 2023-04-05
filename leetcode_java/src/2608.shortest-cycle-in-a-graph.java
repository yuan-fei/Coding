/*
 * @lc app=leetcode id=2608 lang=java
 *
 * [2608] Shortest Cycle in a Graph
 *
 * https://leetcode.com/problems/shortest-cycle-in-a-graph/description/
 *
 * algorithms
 * Hard (36.54%)
 * Likes:    140
 * Dislikes: 8
 * Total Accepted:    5.7K
 * Total Submissions: 15.6K
 * Testcase Example:  '7\n[[0,1],[1,2],[2,0],[3,4],[4,5],[5,6],[6,3]]'
 *
 * There is a bi-directional graph with n vertices, where each vertex is
 * labeled from 0 to n - 1. The edges in the graph are represented by a given
 * 2D integer array edges, where edges[i] = [ui, vi] denotes an edge between
 * vertex ui and vertex vi. Every vertex pair is connected by at most one edge,
 * and no vertex has an edge to itself.
 * 
 * Return the length of the shortest cycle in the graph. If no cycle exists,
 * return -1.
 * 
 * A cycle is a path that starts and ends at the same node, and each edge in
 * the path is used only once.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 7, edges = [[0,1],[1,2],[2,0],[3,4],[4,5],[5,6],[6,3]]
 * Output: 3
 * Explanation: The cycle with the smallest length is : 0 -> 1 -> 2 -> 0 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 4, edges = [[0,1],[0,2]]
 * Output: -1
 * Explanation: There are no cycles in this graph.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 1000
 * 1 <= edges.length <= 1000
 * edges[i].length == 2
 * 0 <= ui, vi < n
 * ui != vi
 * There are no repeated edges.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findShortestCycle(int n, int[][] edges) {
        Set<Integer>[] adj = new Set[n];
        for(int i = 0; i < n; i++){
            adj[i] = new HashSet<>();
        }
        for(int[] e : edges){
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        int ans = n + 1;
        for(int[] e : edges){
            adj[e[0]].remove(e[1]);
            adj[e[1]].remove(e[0]);
            int ret = 1 + bfs(adj, e[0], e[1]);
            // System.out.println(ret);
            ans = Math.min(ret, ans);
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        if(ans > n){
            return -1;
        }
        return ans;
    }

    int bfs(Set<Integer>[] adj, int u, int v){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(u);
        int d = 0;
        boolean[] seen = new boolean[adj.length];
        while(!q.isEmpty()){
            // if(u == 0 && v == 1){
            //     System.out.println(q);    
            // }
            for(int x = q.size(); x > 0; x--){
                int cur = q.poll();
                if(cur == v){
                    return d;
                }
                for(int nxt : adj[cur]){
                    if(!seen[nxt]){
                        q.offer(nxt);
                        seen[nxt] = true;
                    }
                }
            }
            d++;
        }
        return adj.length + 1;
    }
}
// @lc code=end
