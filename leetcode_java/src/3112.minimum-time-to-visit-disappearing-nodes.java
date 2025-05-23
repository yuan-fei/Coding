/*
 * @lc app=leetcode id=3112 lang=java
 *
 * [3112] Minimum Time to Visit Disappearing Nodes
 *
 * https://leetcode.com/problems/minimum-time-to-visit-disappearing-nodes/description/
 *
 * algorithms
 * Medium (35.17%)
 * Likes:    188
 * Dislikes: 22
 * Total Accepted:    22.2K
 * Total Submissions: 63.3K
 * Testcase Example:  '3\n[[0,1,2],[1,2,1],[0,2,4]]\n[1,1,5]'
 *
 * There is an undirected graph of n nodes. You are given a 2D array edges,
 * where edges[i] = [ui, vi, lengthi] describes an edge between node ui and
 * node vi with a traversal time of lengthi units.
 * 
 * Additionally, you are given an array disappear, where disappear[i] denotes
 * the time when the node i disappears from the graph and you won't be able to
 * visit it.
 * 
 * Note that the graph might be disconnected and might contain multiple edges.
 * 
 * Return the array answer, with answer[i] denoting the minimum units of time
 * required to reach node i from node 0. If node i is unreachable from node 0
 * then answer[i] is -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,1,5]
 * 
 * Output: [0,-1,4]
 * 
 * Explanation:
 * 
 * 
 * 
 * We are starting our journey from node 0, and our goal is to find the minimum
 * time required to reach each node before it disappears.
 * 
 * 
 * For node 0, we don't need any time as it is our starting point.
 * For node 1, we need at least 2 units of time to traverse edges[0].
 * Unfortunately, it disappears at that moment, so we won't be able to visit
 * it.
 * For node 2, we need at least 4 units of time to traverse edges[2].
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,3,5]
 * 
 * Output: [0,2,3]
 * 
 * Explanation:
 * 
 * 
 * 
 * We are starting our journey from node 0, and our goal is to find the minimum
 * time required to reach each node before it disappears.
 * 
 * 
 * For node 0, we don't need any time as it is the starting point.
 * For node 1, we need at least 2 units of time to traverse edges[0].
 * For node 2, we need at least 3 units of time to traverse edges[0] and
 * edges[1].
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 2, edges = [[0,1,1]], disappear = [1,1]
 * 
 * Output: [0,-1]
 * 
 * Explanation:
 * 
 * Exactly when we reach node 1, it disappears.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 5 * 10^4
 * 0 <= edges.length <= 10^5
 * edges[i] == [ui, vi, lengthi]
 * 0 <= ui, vi <= n - 1
 * 1 <= lengthi <= 10^5
 * disappear.length == n
 * 1 <= disappear[i] <= 10^5
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

    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        List<Integer>[] adj = buildAdj(n, edges);
        int[] time = dijkstra(adj, edges, disappear);
        return IntStream.range(0, n).map(i -> time[i] > disappear[i] ? -1 : time[i]).toArray();
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

    int[] dijkstra(List<Integer>[] adj, int[][] edges, int[] disappear) {
        int n = adj.length;
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(x -> x[1]));
        pq.offer(new int[] { 0, 0 });
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cost[cur[0]] > cur[1] && disappear[cur[0]] > cur[1]) {
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
