/*
 * @lc app=leetcode id=685 lang=java
 *
 * [685] Redundant Connection II
 *
 * https://leetcode.com/problems/redundant-connection-ii/description/
 *
 * algorithms
 * Hard (33.35%)
 * Likes:    1338
 * Dislikes: 257
 * Total Accepted:    46.9K
 * Total Submissions: 140.8K
 * Testcase Example:  '[[1,2],[1,3],[2,3]]'
 *
 * In this problem, a rooted tree is a directed graph such that, there is
 * exactly one node (the root) for which all other nodes are descendants of
 * this node, plus every node has exactly one parent, except for the root node
 * which has no parents.
 * 
 * The given input is a directed graph that started as a rooted tree with n
 * nodes (with distinct values from 1 to n), with one additional directed edge
 * added. The added edge has two different vertices chosen from 1 to n, and was
 * not an edge that already existed.
 * 
 * The resulting graph is given as a 2D-array of edges. Each element of edges
 * is a pair [ui, vi] that represents a directed edge connecting nodes ui and
 * vi, where ui is a parent of child vi.
 * 
 * Return an edge that can be removed so that the resulting graph is a rooted
 * tree of n nodes. If there are multiple answers, return the answer that
 * occurs last in the given 2D-array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: edges = [[1,2],[1,3],[2,3]]
 * Output: [2,3]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
 * Output: [4,1]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 * ui != vi
 * 
 * 
 */

// @lc code=start
class Solution {
    Set<Integer>[] adj;
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        adj = new Set[n + 1];
        int[] parent = new int[n + 1];
        for(int i = 0; i <= n; i++){
            adj[i] = new HashSet<>();
        }
        for(int i = 0; i < n; i++){
            adj[edges[i][0]].add(edges[i][1]);
            parent[edges[i][1]]++;
        }

        for(int i = n - 1; i >= 0; i--){
            adj[edges[i][0]].remove(edges[i][1]);
            parent[edges[i][1]]--;
            int root = 0;
            int rootCnt = 0;
            for(int j = 1; j <= n; j++){
                if(parent[j] == 0){
                    root = j;
                    rootCnt++;
                }
            }
            if(rootCnt == 1){
                boolean[] visited = new boolean[n + 1];
                dfs(root, visited);
                boolean bad = false;
                for(int j = 1; j <= n; j++){
                    if(!visited[j]){
                        bad = true;
                    }
                }
                if(!bad){
                    return edges[i];
                }    
            }
            adj[edges[i][0]].add(edges[i][1]);   
            parent[edges[i][1]]++;
        }
        return new int[0];
    }

    void dfs(int u, boolean[] visited){
        visited[u] = true;
        for (int v : adj[u]) {
            if(!visited[v]){
                dfs(v, visited);    
            }
        }
    }
}
// @lc code=end
