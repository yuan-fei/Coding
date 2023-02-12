/*
 * @lc app=leetcode id=834 lang=java
 *
 * [834] Sum of Distances in Tree
 *
 * https://leetcode.com/problems/sum-of-distances-in-tree/description/
 *
 * algorithms
 * Hard (59.32%)
 * Likes:    4404
 * Dislikes: 102
 * Total Accepted:    79.5K
 * Total Submissions: 134K
 * Testcase Example:  '6\n[[0,1],[0,2],[2,3],[2,4],[2,5]]'
 *
 * There is an undirected connected tree with n nodes labeled from 0 to n - 1
 * and n - 1 edges.
 * 
 * You are given the integer n and the array edges where edges[i] = [ai, bi]
 * indicates that there is an edge between nodes ai and bi in the tree.
 * 
 * Return an array answer of length n where answer[i] is the sum of the
 * distances between the i^th node in the tree and all other nodes.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * Output: [8,12,6,10,10,10]
 * Explanation: The tree is shown above.
 * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * equals 1 + 1 + 2 + 2 + 2 = 8.
 * Hence, answer[0] = 8, and so on.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 1, edges = []
 * Output: [0]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 2, edges = [[1,0]]
 * Output: [1,1]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 3 * 10^4
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * The given input represents a valid tree.
 * 
 * 
 */

// @lc code=start
class Solution {
    List<Integer>[] adj;
    int total;
    int[][] dist1;
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        adj = new List[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < edges.length; i++){
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
        }
        dist1 = new int[n][2];
        int[] dist2 = new int[n];
        dfs1(0, -1);
        dfs2(0, -1, dist2);
        return dist2;
    }

    void dfs1(int u, int p){
        for(int v : adj[u]){
            if(v != p){
                dfs1(v, u);
                dist1[u][0] += dist1[v][0];
                dist1[u][1] += dist1[v][1];
            }
        }
        dist1[u][1] += dist1[u][0];
        dist1[u][0]++;
    }

    void dfs2(int u, int p, int[] dist2){
        dist2[u] = dist1[u][1];
        if(p != -1){
            dist2[u] += dist2[p] - (dist1[u][1] + dist1[u][0]) + dist1[0][0] - dist1[u][0];    
        }
        for(int v : adj[u]){
            if(v != p){
                dfs2(v, u, dist2);
            }
        }
    }

}
// @lc code=end
