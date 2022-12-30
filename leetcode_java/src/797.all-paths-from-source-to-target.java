/*
 * @lc app=leetcode id=797 lang=java
 *
 * [797] All Paths From Source to Target
 *
 * https://leetcode.com/problems/all-paths-from-source-to-target/description/
 *
 * algorithms
 * Medium (81.55%)
 * Likes:    5956
 * Dislikes: 129
 * Total Accepted:    394.5K
 * Total Submissions: 480.6K
 * Testcase Example:  '[[1,2],[3],[3],[]]'
 *
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1,
 * find all possible paths from node 0 to node n - 1 and return them in any
 * order.
 * 
 * The graph is given as follows: graph[i] is a list of all nodes you can visit
 * from node i (i.e., there is a directed edge from node i to node
 * graph[i][j]).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: graph = [[1,2],[3],[3],[]]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == graph.length
 * 2 <= n <= 15
 * 0 <= graph[i][j] < n
 * graph[i][j] != i (i.e., there will be no self-loops).
 * All the elements of graph[i] are unique.
 * The input graph is guaranteed to be a DAG.
 * 
 * 
 */

// @lc code=start
class Solution {
    int[][] graph;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.graph = graph;
        List<List<Integer>> res = new ArrayList<>();
        dfs(graph.length - 1, 0, new ArrayList<Integer>(), res);
        return res;
    }

    void dfs(int n, int u, List<Integer> cur, List<List<Integer>> res){
        cur.add(u);
        if(u == n){
            res.add(new ArrayList<>(cur));
        }
        for(int v : graph[u]){
            dfs(n, v, cur, res);
        }
        cur.remove(cur.size() - 1);
    }
}
// @lc code=end
