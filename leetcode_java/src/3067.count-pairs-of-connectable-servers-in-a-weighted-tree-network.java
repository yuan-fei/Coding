/*
 * @lc app=leetcode id=3067 lang=java
 *
 * [3067] Count Pairs of Connectable Servers in a Weighted Tree Network
 *
 * https://leetcode.com/problems/count-pairs-of-connectable-servers-in-a-weighted-tree-network/description/
 *
 * algorithms
 * Medium (56.40%)
 * Likes:    211
 * Dislikes: 25
 * Total Accepted:    13.9K
 * Total Submissions: 24.6K
 * Testcase Example:  '[[0,1,1],[1,2,5],[2,3,13],[3,4,9],[4,5,2]]\n1'
 *
 * You are given an unrooted weighted tree with n vertices representing servers
 * numbered from 0 to n - 1, an array edges where edges[i] = [ai, bi, weighti]
 * represents a bidirectional edge between vertices ai and bi of weight
 * weighti. You are also given an integer signalSpeed.
 * 
 * Two servers a and b are connectable through a server c if:
 * 
 * 
 * a < b, a != c and b != c.
 * The distance from c to a is divisible by signalSpeed.
 * The distance from c to b is divisible by signalSpeed.
 * The path from c to b and the path from c to a do not share any edges.
 * 
 * 
 * Return an integer array count of length n where count[i] is the number of
 * server pairs that are connectable through the server i.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: edges = [[0,1,1],[1,2,5],[2,3,13],[3,4,9],[4,5,2]], signalSpeed = 1
 * Output: [0,4,6,6,4,0]
 * Explanation: Since signalSpeed is 1, count[c] is equal to the number of
 * pairs of paths that start at c and do not share any edges.
 * In the case of the given path graph, count[c] is equal to the number of
 * servers to the left of c multiplied by the servers to the right of c.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: edges = [[0,6,3],[6,5,3],[0,3,1],[3,2,7],[3,1,6],[3,4,2]],
 * signalSpeed = 3
 * Output: [2,0,0,0,0,0,2]
 * Explanation: Through server 0, there are 2 pairs of connectable servers: (4,
 * 5) and (4, 6).
 * Through server 6, there are 2 pairs of connectable servers: (4, 5) and (0,
 * 5).
 * It can be shown that no two servers are connectable through servers other
 * than 0 and 6.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 1000
 * edges.length == n - 1
 * edges[i].length == 3
 * 0 <= ai, bi < n
 * edges[i] = [ai, bi, weighti]
 * 1 <= weighti <= 10^6
 * 1 <= signalSpeed <= 10^6
 * The input is generated such that edges represents a valid tree.
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    List<int[]>[] adj;
    @SuppressWarnings("unchecked")
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
            adj[e[1]].add(new int[]{e[0], e[2]});
        }
        int[] ans = new int[n];
        for(int u = 0; u < n; u++){
            int total = 0;
            for (int[] e : adj[u]) {
                int v = e[0];
                int w = e[1];
                int childTotal = dfs(v, u, w, signalSpeed);
                ans[u] += childTotal * total;
                total += childTotal;
                // System.out.println(Arrays.asList(u, v, paths));
            }
        }
        return ans;
    }

    int dfs(int u, int p, int dist, int signalSpeed){
        int ret = (dist % signalSpeed == 0) ? 1 : 0;
        for (int[] e : adj[u]) {
            int v = e[0];
            int w = e[1];
            if(v != p){
                ret += dfs(v, u, dist + w, signalSpeed);
            }
        }
        return ret;
    }
}
// @lc code=end
