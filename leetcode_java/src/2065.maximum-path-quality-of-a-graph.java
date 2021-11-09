/*
 * @lc app=leetcode id=2065 lang=java
 *
 * [2065] Maximum Path Quality of a Graph
 *
 * https://leetcode.com/problems/maximum-path-quality-of-a-graph/description/
 *
 * algorithms
 * Hard (53.51%)
 * Likes:    54
 * Dislikes: 9
 * Total Accepted:    2K
 * Total Submissions: 3.8K
 * Testcase Example:  '[0,32,10,43]\n[[0,1,10],[1,2,15],[0,3,10]]\n49'
 *
 * There is an undirected graph with n nodes numbered from 0 to n - 1
 * (inclusive). You are given a 0-indexed integer array values where values[i]
 * is the value of the i^th node. You are also given a 0-indexed 2D integer
 * array edges, where each edges[j] = [uj, vj, timej] indicates that there is
 * an undirected edge between the nodes uj and vj, and it takes timej seconds
 * to travel between the two nodes. Finally, you are given an integer maxTime.
 * 
 * A valid path in the graph is any path that starts at node 0, ends at node 0,
 * and takes at most maxTime seconds to complete. You may visit the same node
 * multiple times. The quality of a valid path is the sum of the values of the
 * unique nodes visited in the path (each node's value is added at most once to
 * the sum).
 * 
 * Return the maximum quality of a valid path.
 * 
 * Note: There are at most four edges connected to each node.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime
 * = 49
 * Output: 75
 * Explanation:
 * One possible path is 0 -> 1 -> 0 -> 3 -> 0. The total time taken is 10 + 10
 * + 10 + 10 = 40 <= 49.
 * The nodes visited are 0, 1, and 3, giving a maximal path quality of 0 + 32 +
 * 43 = 75.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime
 * = 30
 * Output: 25
 * Explanation:
 * One possible path is 0 -> 3 -> 0. The total time taken is 10 + 10 = 20 <=
 * 30.
 * The nodes visited are 0 and 3, giving a maximal path quality of 5 + 20 =
 * 25.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]],
 * maxTime = 50
 * Output: 7
 * Explanation:
 * One possible path is 0 -> 1 -> 3 -> 1 -> 0. The total time taken is 10 + 13
 * + 13 + 10 = 46 <= 50.
 * The nodes visited are 0, 1, and 3, giving a maximal path quality of 1 + 2 +
 * 4 = 7.
 * 
 * Example 4:
 * 
 * 
 * 
 * 
 * Input: values = [0,1,2], edges = [[1,2,10]], maxTime = 10
 * Output: 0
 * Explanation: 
 * The only path is 0. The total time taken is 0.
 * The only node visited is 0, giving a maximal path quality of 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == values.length
 * 1 <= n <= 1000
 * 0 <= values[i] <= 10^8
 * 0 <= edges.length <= 2000
 * edges[j].length == 3 
 * 0 <= uj < vj <= n - 1
 * 10 <= timej, maxTime <= 100
 * All the pairs [uj, vj] are unique.
 * There are at most four edges connected to each node.
 * The graph may not be connected.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; ++i) adj[i] = new LinkedList();
        for (int[] e : edges) {
            int i = e[0], j = e[1], t = e[2];
            adj[i].add(new int[]{j, t});
            adj[j].add(new int[]{i, t});
        }
        int[] res = new int[1];
        int[] seen = new int[n];
        seen[0]++;
        dfs(adj, 0, values, maxTime, seen, res, values[0]);
        return res[0];
    }
    private void dfs(List<int[]>[] adj, int src, int[] values, int maxTime, int[] seen, int[] res, int sum) {
        if (0 == src) {
            res[0] = Math.max(res[0], sum);
        }
        if (0 > maxTime) return;
        for (int[] data : adj[src]) {
            int dst = data[0], t = data[1];
            if (0 > maxTime - t) continue;
            seen[dst]++;
            dfs(adj, dst, values, maxTime - t, seen, res, sum + (1 == seen[dst] ? values[dst] : 0));
            seen[dst]--;
        }
    }
}
// @lc code=end
