/*
 * @lc app=leetcode id=1971 lang=java
 *
 * [1971] Find if Path Exists in Graph
 *
 * https://leetcode.com/problems/find-if-path-exists-in-graph/description/
 *
 * algorithms
 * Easy (51.83%)
 * Likes:    54
 * Dislikes: 3
 * Total Accepted:    2.1K
 * Total Submissions: 4.1K
 * Testcase Example:  '3\n[[0,1],[1,2],[2,0]]\n0\n2'
 *
 * There is a bi-directional graph with n vertices, where each vertex is
 * labeled from 0 to n - 1 (inclusive). The edges in the graph are represented
 * as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a
 * bi-directional edge between vertex ui and vertex vi. Every vertex pair is
 * connected by at most one edge, and no vertex has an edge to itself.
 * 
 * You want to determine if there is a valid path that exists from vertex start
 * to vertex end.
 * 
 * Given edges and the integers n, start, and end, return true if there is a
 * valid path from start to end, or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, edges = [[0,1],[1,2],[2,0]], start = 0, end = 2
 * Output: true
 * Explanation: There are two paths from vertex 0 to vertex 2:
 * - 0 → 1 → 2
 * - 0 → 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], start = 0, end = 5
 * Output: false
 * Explanation: There is no path from vertex 0 to vertex 5.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 2 * 10^5
 * 0 <= edges.length <= 2 * 10^5
 * edges[i].length == 2
 * 1 <= ui, vi <= n - 1
 * ui != vi
 * 1 <= start, end <= n - 1
 * There are no duplicate edges.
 * There are no self edges.
 * 
 * 
 */

// @lc code=start
class Solution {
    List<Integer>[] adj;
    boolean[] state;
    public boolean validPath(int n, int[][] edges, int start, int end) {
        adj = new List[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int[] e : edges){
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        state = new boolean[n];
        return dfs(start, end);
    }

    boolean dfs(int u, int v){
        if(u == v){
            return true;
        }
        state[u] = true;
        for(int w : adj[u]){
            if(!state[w] && dfs(w, v)){
                return true;
            }
        }
        return false;
    }
}
// @lc code=end
