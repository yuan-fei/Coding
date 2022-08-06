/*
 * @lc app=leetcode id=2360 lang=java
 *
 * [2360] Longest Cycle in a Graph
 *
 * https://leetcode.com/problems/longest-cycle-in-a-graph/description/
 *
 * algorithms
 * Hard (35.12%)
 * Likes:    268
 * Dislikes: 4
 * Total Accepted:    7.5K
 * Total Submissions: 21.4K
 * Testcase Example:  '[3,3,4,2,3]'
 *
 * You are given a directed graph of n nodes numbered from 0 to n - 1, where
 * each node has at most one outgoing edge.
 * 
 * The graph is represented with a given 0-indexed array edges of size n,
 * indicating that there is a directed edge from node i to node edges[i]. If
 * there is no outgoing edge from node i, then edges[i] == -1.
 * 
 * Return the length of the longest cycle in the graph. If no cycle exists,
 * return -1.
 * 
 * A cycle is a path that starts and ends at the same node.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: edges = [3,3,4,2,3]
 * Output: 3
 * Explanation: The longest cycle in the graph is the cycle: 2 -> 4 -> 3 -> 2.
 * The length of this cycle is 3, so 3 is returned.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: edges = [2,-1,3,1]
 * Output: -1
 * Explanation: There are no cycles in this graph.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == edges.length
 * 2 <= n <= 10^5
 * -1 <= edges[i] < n
 * edges[i] != i
 * 
 * 
 */

// @lc code=start
class Solution {
    int[] e;
    int[] state;
    int ans = -1;
    public int longestCycle(int[] edges) {
        e = edges;
        int n = edges.length;
        state = new int[n];
        Arrays.fill(state, -1);
        for(int i = 0; i < n; i++){
            if(state[i] == -1){
                dfs(i, 0);
            }
        }
        return ans;
    }

    void dfs(int u, int k){
        if(u == -1){
            return;
        }
        if(state[u] == -1){
            state[u] = k;
            dfs(e[u], k + 1);
        }
        else if(state[u] >= 0){
            ans = Math.max(ans, k - state[u]);
        }
        state[u] = -2;
    }
}
// @lc code=end
