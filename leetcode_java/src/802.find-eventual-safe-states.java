/*
 * @lc app=leetcode id=802 lang=java
 *
 * [802] Find Eventual Safe States
 *
 * https://leetcode.com/problems/find-eventual-safe-states/description/
 *
 * algorithms
 * Medium (55.92%)
 * Likes:    3307
 * Dislikes: 349
 * Total Accepted:    112.9K
 * Total Submissions: 201.9K
 * Testcase Example:  '[[1,2],[2,3],[5],[0],[5],[],[]]'
 *
 * There is a directed graph of n nodes with each node labeled from 0 to n - 1.
 * The graph is represented by a 0-indexed 2D integer array graph where
 * graph[i] is an integer array of nodes adjacent to node i, meaning there is
 * an edge from node i to each node in graph[i].
 * 
 * A node is a terminal node if there are no outgoing edges. A node is a safe
 * node if every possible path starting from that node leads to a terminal node
 * (or another safe node).
 * 
 * Return an array containing all the safe nodes of the graph. The answer
 * should be sorted in ascending order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Explanation: The given graph is shown above.
 * Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either
 * of them.
 * Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
 * 
 * Example 2:
 * 
 * 
 * Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
 * Output: [4]
 * Explanation:
 * Only node 4 is a terminal node, and every path starting at node 4 leads to
 * node 4.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == graph.length
 * 1 <= n <= 10^4
 * 0 <= graph[i].length <= n
 * 0 <= graph[i][j] <= n - 1
 * graph[i] is sorted in a strictly increasing order.
 * The graph may contain self-loops.
 * The number of edges in the graph will be in the range [1, 4 * 10^4].
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] seen = new int[n];
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(seen[i] == 0){
                dfs(graph, i, seen);
            }
            if(seen[i] == 2){
                ans.add(i);
            }
        }
        return ans;
    }

    void dfs(int[][] graph, int u, int[] seen){
        seen[u] = 1;
        for(int v : graph[u]){
            if(seen[v] == 0){
                dfs(graph, v, seen);
            }
            if(seen[v] == 1 || seen[v] == 3){
                seen[u] = 3;
            }
        }
        if(seen[u] == 1){
            seen[u] = 2;    
        }
    }
}
// @lc code=end
