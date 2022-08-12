/*
 * @lc app=leetcode id=2368 lang=java
 *
 * [2368] Reachable Nodes With Restrictions
 *
 * https://leetcode.com/problems/reachable-nodes-with-restrictions/description/
 *
 * algorithms
 * Medium (50.95%)
 * Likes:    96
 * Dislikes: 4
 * Total Accepted:    11.4K
 * Total Submissions: 22.4K
 * Testcase Example:  '7\n[[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]]\n[4,5]'
 *
 * There is an undirected tree with n nodes labeled from 0 to n - 1 and n - 1
 * edges.
 * 
 * You are given a 2D integer array edges of length n - 1 where edges[i] = [ai,
 * bi] indicates that there is an edge between nodes ai and bi in the tree. You
 * are also given an integer array restricted which represents restricted
 * nodes.
 * 
 * Return the maximum number of nodes you can reach from node 0 without
 * visiting a restricted node.
 * 
 * Note that node 0 will not be a restricted node.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 7, edges = [[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]], restricted =
 * [4,5]
 * Output: 4
 * Explanation: The diagram above shows the tree.
 * We have that [0,1,2,3] are the only nodes that can be reached from node 0
 * without visiting a restricted node.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 7, edges = [[0,1],[0,2],[0,5],[0,4],[3,2],[6,5]], restricted =
 * [4,2,1]
 * Output: 3
 * Explanation: The diagram above shows the tree.
 * We have that [0,5,6] are the only nodes that can be reached from node 0
 * without visiting a restricted node.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges represents a valid tree.
 * 1 <= restricted.length < n
 * 1 <= restricted[i] < n
 * All the values of restricted are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    List<Integer>[] adj;
    Set<Integer> forbidden;
    boolean[] seen;
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        adj = new List[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < edges.length; i++){
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
        }
        forbidden = new HashSet<>();
        for(int x : restricted){
            forbidden.add(x);
        }
        seen = new boolean[n];
        dfs(0);
        int ans = 0;
        for(boolean x : seen){
            ans += (x ? 1 : 0);
        }
        return ans;
    }

    void dfs(int u){
        seen[u] = true;
        for(int v : adj[u]){
            if(!seen[v] && !forbidden.contains(v)){
                dfs(v);
            }
        }
    }
}
// @lc code=end
