/*
 * @lc app=leetcode id=1761 lang=java
 *
 * [1761] Minimum Degree of a Connected Trio in a Graph
 *
 * https://leetcode.com/problems/minimum-degree-of-a-connected-trio-in-a-graph/description/
 *
 * algorithms
 * Hard (34.32%)
 * Likes:    43
 * Dislikes: 91
 * Total Accepted:    4K
 * Total Submissions: 11.7K
 * Testcase Example:  '6\n[[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]'
 *
 * You are given an undirected graph. You are given an integer n which is the
 * number of nodes in the graph and an array edges, where each edges[i] = [ui,
 * vi] indicates that there is an undirected edge between ui and vi.
 * 
 * A connected trio is a set of three nodes where there is an edge between
 * every pair of them.
 * 
 * The degree of a connected trio is the number of edges where one endpoint is
 * in the trio, and the other is not.
 * 
 * Return the minimum degree of a connected trio in the graph, or -1 if the
 * graph has no connected trios.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
 * Output: 3
 * Explanation: There is exactly one trio, which is [1,2,3]. The edges that
 * form its degree are bolded in the figure above.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 7, edges = [[1,3],[4,1],[4,3],[2,5],[5,6],[6,7],[7,5],[2,6]]
 * Output: 0
 * Explanation: There are exactly three trios:
 * 1) [1,4,3] with degree 0.
 * 2) [2,5,6] with degree 2.
 * 3) [5,6,7] with degree 2.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 400
 * edges[i].length == 2
 * 1 <= edges.length <= n * (n-1) / 2
 * 1 <= ui, vi <= n
 * ui != vi
 * There are no repeated edges.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minTrioDegree(int n, int[][] edges) {
        int min = Integer.MAX_VALUE;
        Map<Integer, Integer> degrees = new HashMap<>(); // vertex, degree
        boolean[][] isEdge = new boolean[n + 1][n + 1];
        
        for (int[] edge : edges) {
            degrees.put(edge[0], degrees.getOrDefault(edge[0], 0) + 1);
            degrees.put(edge[1], degrees.getOrDefault(edge[1], 0) + 1);
            isEdge[edge[0]][edge[1]] = true;
            isEdge[edge[1]][edge[0]] = true;
        }
        
        for (int[] edge : edges) {
            for (int i = 1; i <= n; i++) {
                if (isEdge[i][edge[0]] && isEdge[i][edge[1]]) {
                    int degree = degrees.get(i) + degrees.get(edge[0]) + degrees.get(edge[1]) - 6;
                    min = Math.min(min, degree);
                }
            }
        }
        
        if (min == Integer.MAX_VALUE)
            return -1;
        return min;
    }
}
// @lc code=end
