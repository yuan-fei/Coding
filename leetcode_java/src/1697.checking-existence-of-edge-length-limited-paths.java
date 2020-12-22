/*
 * @lc app=leetcode id=1697 lang=java
 *
 * [1697] Checking Existence of Edge Length Limited Paths
 *
 * https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/description/
 *
 * algorithms
 * Hard (43.30%)
 * Likes:    131
 * Dislikes: 3
 * Total Accepted:    2.1K
 * Total Submissions: 4.8K
 * Testcase Example:  '3\n[[0,1,2],[1,2,4],[2,0,8],[1,0,16]]\n[[0,1,2],[0,2,5]]'
 *
 * An undirected graph of n nodes is defined by edgeList, where edgeList[i] =
 * [ui, vi, disi] denotes an edge between nodes ui and vi with distance disi.
 * Note that there may be multiple edges between two nodes.
 * 
 * Given an array queries, where queries[j] = [pj, qj, limitj], your task is to
 * determine for each queries[j] whether there is a path between pj and qj such
 * that each edge on the path has a distance strictly less than limitj .
 * 
 * Return a boolean array answer, where answer.length == queries.length and the
 * j^th value of answer is true if there is a path for queries[j] is true, and
 * false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries =
 * [[0,1,2],[0,2,5]]
 * Output: [false,true]
 * Explanation: The above figure shows the given graph. Note that there are two
 * overlapping edges between 0 and 1 with distances 2 and 16.
 * For the first query, between 0 and 1 there is no path where each distance is
 * less than 2, thus we return false for this query.
 * For the second query, there is a path (0 -> 1 -> 2) of two edges with
 * distances less than 5, thus we return true for this query.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries =
 * [[0,4,14],[1,4,13]]
 * Output: [true,false]
 * Exaplanation: The above figure shows the given graph.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 10^5
 * 1 <= edgeList.length, queries.length <= 10^5
 * edgeList[i].length == 3
 * queries[j].length == 3
 * 0 <= ui, vi, pj, qj <= n - 1
 * ui != vi
 * pj != qj
 * 1 <= disi, limitj <= 10^9
 * There may be multiple edges between two nodes.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int M = edgeList.length, N = queries.length;
        DSU dsu = new DSU(n);
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) map.put(Arrays.toString(queries[i]), i);
        Arrays.sort(queries, (a, b) -> a[2] - b[2]);
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        boolean[] res = new boolean[N];
        for (int i = 0, j = 0; i < N; i++) {
            int[] query = queries[i];
            while (j < M && edgeList[j][2] < queries[i][2])
                dsu.union(edgeList[j][0], edgeList[j++][1]);
            res[map.get(Arrays.toString(queries[i]))] = 
                dsu.find(queries[i][0]) == dsu.find(queries[i][1]);
        }
        return res;
    }


}

class DSU {
    int[] parent;
    public DSU(int N) {
        this.parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;
    }
    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    public void union(int x, int y) {
        parent[find(x)] = parent[find(y)];
    }
}
// @lc code=end
