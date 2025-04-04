/*
 * @lc app=leetcode id=3123 lang=java
 *
 * [3123] Find Edges in Shortest Paths
 *
 * https://leetcode.com/problems/find-edges-in-shortest-paths/description/
 *
 * algorithms
 * Hard (45.90%)
 * Likes:    265
 * Dislikes: 5
 * Total Accepted:    14.7K
 * Total Submissions: 32.4K
 * Testcase Example:  '6\n[[0,1,4],[0,2,1],[1,3,2],[1,4,3],[1,5,1],[2,3,1],[3,5,3],[4,5,2]]'
 *
 * You are given an undirected weighted graph of n nodes numbered from 0 to n -
 * 1. The graph consists of m edges represented by a 2D array edges, where
 * edges[i] = [ai, bi, wi] indicates that there is an edge between nodes ai and
 * bi with weight wi.
 * 
 * Consider all the shortest paths from node 0 to node n - 1 in the graph. You
 * need to find a boolean array answer where answer[i] is true if the edge
 * edges[i] is part of at least one shortest path. Otherwise, answer[i] is
 * false.
 * 
 * Return the array answer.
 * 
 * Note that the graph may not be connected.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 6, edges =
 * [[0,1,4],[0,2,1],[1,3,2],[1,4,3],[1,5,1],[2,3,1],[3,5,3],[4,5,2]]
 * 
 * Output: [true,true,true,false,true,true,true,false]
 * 
 * Explanation:
 * 
 * The following are all the shortest paths between nodes 0 and 5:
 * 
 * 
 * The path 0 -> 1 -> 5: The sum of weights is 4 + 1 = 5.
 * The path 0 -> 2 -> 3 -> 5: The sum of weights is 1 + 1 + 3 = 5.
 * The path 0 -> 2 -> 3 -> 1 -> 5: The sum of weights is 1 + 1 + 2 + 1 = 5.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 4, edges = [[2,0,1],[0,1,1],[0,3,4],[3,2,2]]
 * 
 * Output: [true,false,false,true]
 * 
 * Explanation:
 * 
 * There is one shortest path between nodes 0 and 3, which is the path 0 -> 2
 * -> 3 with the sum of weights 1 + 2 = 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 5 * 10^4
 * m == edges.length
 * 1 <= m <= min(5 * 10^4, n * (n - 1) / 2)
 * 0 <= ai, bi < n
 * ai != bi
 * 1 <= wi <= 10^5
 * There are no repeated edges.
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Solution {
    int MAX = Integer.MAX_VALUE;

    public boolean[] findAnswer(int n, int[][] edges) {
        List<Integer>[] adj = buildAdj(n, edges);
        int[] costFromStart = dijkstra(adj, edges, 0);
        int[] costFromEnd = dijkstra(adj, edges, n - 1);
        int min = costFromStart[n - 1];
        boolean[] ans = new boolean[edges.length];
        for (int i = 0; i < ans.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (costFromStart[u] != MAX && costFromEnd[v] != MAX
                    && costFromStart[u] + costFromEnd[v] + edges[i][2] == min) {
                ans[i] = true;
            } else if (costFromStart[v] != MAX && costFromEnd[u] != MAX
                    && costFromStart[v] + costFromEnd[u] + edges[i][2] == min) {
                ans[i] = true;
            }
        }
        return ans;
    }

    List<Integer>[] buildAdj(int n, int[][] edges) {
        @SuppressWarnings("unchecked")
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            adj[edges[i][0]].add(i);
            adj[edges[i][1]].add(i);
        }
        return adj;
    }

    int[] dijkstra(List<Integer>[] adj, int[][] edges, int start) {
        int n = adj.length;
        int[] cost = new int[n];
        Arrays.fill(cost, MAX);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(x -> x[1]));
        pq.offer(new int[] { start, 0 });
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cost[cur[0]] > cur[1]) {
                cost[cur[0]] = cur[1];
                for (int e : adj[cur[0]]) {
                    pq.offer(new int[] { edges[e][0] + edges[e][1] - cur[0], cost[cur[0]] + edges[e][2] });
                }
            }
        }
        return cost;
    }

}
// @lc code=end
