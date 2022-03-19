/*
 * @lc app=leetcode id=2203 lang=java
 *
 * [2203] Minimum Weighted Subgraph With the Required Paths
 *
 * https://leetcode.com/problems/minimum-weighted-subgraph-with-the-required-paths/description/
 *
 * algorithms
 * Hard (31.61%)
 * Likes:    225
 * Dislikes: 8
 * Total Accepted:    3.3K
 * Total Submissions: 10.4K
 * Testcase Example:  '6\n' +
  '[[0,2,2],[0,5,6],[1,0,3],[1,4,5],[2,1,1],[2,3,3],[2,3,4],[3,4,2],[4,5,1]]\n' +
  '0\n1\n5'
 *
 * You are given an integer n denoting the number of nodes of a weighted
 * directed graph. The nodes are numbered from 0 to n - 1.
 * 
 * You are also given a 2D integer array edges where edges[i] = [fromi, toi,
 * weighti] denotes that there exists a directed edge from fromi to toi with
 * weight weighti.
 * 
 * Lastly, you are given three distinct integers src1, src2, and dest denoting
 * three distinct nodes of the graph.
 * 
 * Return the minimum weight of a subgraph of the graph such that it is
 * possible to reach dest from both src1 and src2 via a set of edges of this
 * subgraph. In case such a subgraph does not exist, return -1.
 * 
 * A subgraph is a graph whose vertices and edges are subsets of the original
 * graph. The weight of a subgraph is the sum of weights of its constituent
 * edges.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 6, edges =
 * [[0,2,2],[0,5,6],[1,0,3],[1,4,5],[2,1,1],[2,3,3],[2,3,4],[3,4,2],[4,5,1]],
 * src1 = 0, src2 = 1, dest = 5
 * Output: 9
 * Explanation:
 * The above figure represents the input graph.
 * The blue edges represent one of the subgraphs that yield the optimal answer.
 * Note that the subgraph [[1,0,3],[0,5,6]] also yields the optimal answer. It
 * is not possible to get a subgraph with less weight satisfying all the
 * constraints.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 3, edges = [[0,1,1],[2,1,1]], src1 = 0, src2 = 1, dest = 2
 * Output: -1
 * Explanation:
 * The above figure represents the input graph.
 * It can be seen that there does not exist any path from node 1 to node 2,
 * hence there are no subgraphs satisfying all the constraints.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= n <= 10^5
 * 0 <= edges.length <= 10^5
 * edges[i].length == 3
 * 0 <= fromi, toi, src1, src2, dest <= n - 1
 * fromi != toi
 * src1, src2, and dest are pairwise distinct.
 * 1 <= weight[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    long MAX = (long)1e10 + 5;
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        List<Integer>[] adj = buildAdj(n, edges, false);
        List<Integer>[] adjReverse = buildAdj(n, edges, true);
        long[] destDist = dijkstra(n, adjReverse, edges, dest);
        long[] src1Dist = dijkstra(n, adj, edges, src1);
        long[] src2Dist = dijkstra(n, adj, edges, src2);
        long min = MAX * 2;
        for(int i = 0; i < n; i++){
            if(destDist[i] < MAX && src2Dist[i] < MAX && src1Dist[i] < MAX){
                min = Math.min(min, src1Dist[i] + src2Dist[i] + destDist[i]);
            }
        }
        return min == MAX * 2 ? -1 : min;
    }

    List<Integer>[] buildAdj(int n, int[][] edges, boolean reverse){
        List<Integer>[] adj = new List[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < edges.length; i++){
            if(reverse){
                adj[edges[i][1]].add(i);
            }
            else{
                adj[edges[i][0]].add(i);
            }
            
        }
        return adj;
    }

    long[] dijkstra(int n, List<Integer>[] adj, int[][] edges, int s){
        long[] ans = new long[n];
        Arrays.fill(ans, MAX);
        PriorityQueue<long[]> q = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        q.offer(new long[] { s, 0 });
        while(!q.isEmpty()){
            long[] cur = q.poll();
            int u = (int)cur[0];
            if(cur[1] < ans[u]){
                ans[u] = cur[1];
                for(int e : adj[u]){
                    int v = (int)(edges[e][0] + edges[e][1] - u);
                    if(ans[v] > ans[u] + edges[e][2]){
                        q.offer(new long[]{v, ans[u] + edges[e][2]});
                    }
                }
            }
        }
        return ans;
    }
}
// @lc code=end
