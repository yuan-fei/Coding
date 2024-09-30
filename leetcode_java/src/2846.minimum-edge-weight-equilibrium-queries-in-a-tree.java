/*
 * @lc app=leetcode id=2846 lang=java
 *
 * [2846] Minimum Edge Weight Equilibrium Queries in a Tree
 *
 * https://leetcode.com/problems/minimum-edge-weight-equilibrium-queries-in-a-tree/description/
 *
 * algorithms
 * Hard (43.39%)
 * Likes:    284
 * Dislikes: 6
 * Total Accepted:    5.5K
 * Total Submissions: 12.5K
 * Testcase Example:  '7\n' +
  '[[0,1,1],[1,2,1],[2,3,1],[3,4,2],[4,5,2],[5,6,2]]\n' +
  '[[0,3],[3,6],[2,6],[0,6]]'
 *
 * There is an undirected tree with n nodes labeled from 0 to n - 1. You are
 * given the integer n and a 2D integer array edges of length n - 1, where
 * edges[i] = [ui, vi, wi] indicates that there is an edge between nodes ui and
 * vi with weight wi in the tree.
 * 
 * You are also given a 2D integer array queries of length m, where queries[i]
 * = [ai, bi]. For each query, find the minimum number of operations required
 * to make the weight of every edge on the path from ai to bi equal. In one
 * operation, you can choose any edge of the tree and change its weight to any
 * value.
 * 
 * Note that:
 * 
 * 
 * Queries are independent of each other, meaning that the tree returns to its
 * initial state on each new query.
 * The path from ai to bi is a sequence of distinct nodes starting with node ai
 * and ending with node bi such that every two adjacent nodes in the sequence
 * share an edge in the tree.
 * 
 * 
 * Return an array answer of length m where answer[i] is the answer to the i^th
 * query.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 7, edges = [[0,1,1],[1,2,1],[2,3,1],[3,4,2],[4,5,2],[5,6,2]],
 * queries = [[0,3],[3,6],[2,6],[0,6]]
 * Output: [0,0,1,3]
 * Explanation: In the first query, all the edges in the path from 0 to 3 have
 * a weight of 1. Hence, the answer is 0.
 * In the second query, all the edges in the path from 3 to 6 have a weight of
 * 2. Hence, the answer is 0.
 * In the third query, we change the weight of edge [2,3] to 2. After this
 * operation, all the edges in the path from 2 to 6 have a weight of 2. Hence,
 * the answer is 1.
 * In the fourth query, we change the weights of edges [0,1], [1,2] and [2,3]
 * to 2. After these operations, all the edges in the path from 0 to 6 have a
 * weight of 2. Hence, the answer is 3.
 * For each queries[i], it can be shown that answer[i] is the minimum number of
 * operations needed to equalize all the edge weights in the path from ai to
 * bi.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 8, edges =
 * [[1,2,6],[1,3,4],[2,4,6],[2,5,3],[3,6,6],[3,0,8],[7,0,2]], queries =
 * [[4,6],[0,4],[6,5],[7,4]]
 * Output: [1,2,2,3]
 * Explanation: In the first query, we change the weight of edge [1,3] to 6.
 * After this operation, all the edges in the path from 4 to 6 have a weight of
 * 6. Hence, the answer is 1.
 * In the second query, we change the weight of edges [0,3] and [3,1] to 6.
 * After these operations, all the edges in the path from 0 to 4 have a weight
 * of 6. Hence, the answer is 2.
 * In the third query, we change the weight of edges [1,3] and [5,2] to 6.
 * After these operations, all the edges in the path from 6 to 5 have a weight
 * of 6. Hence, the answer is 2.
 * In the fourth query, we change the weights of edges [0,7], [0,3] and [1,3]
 * to 6. After these operations, all the edges in the path from 7 to 4 have a
 * weight of 6. Hence, the answer is 3.
 * For each queries[i], it can be shown that answer[i] is the minimum number of
 * operations needed to equalize all the edge weights in the path from ai to
 * bi.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^4
 * edges.length == n - 1
 * edges[i].length == 3
 * 0 <= ui, vi < n
 * 1 <= wi <= 26
 * The input is generated such that edges represents a valid tree.
 * 1 <= queries.length == m <= 2 * 10^4
 * queries[i].length == 2
 * 0 <= ai, bi < n
 * 
 * 
 */

// @lc code=start
class Solution {
    Map<Integer, List<Integer>> queriesByNode = new HashMap<>();
    int[] lcasByQuery;
    int[][] valueFreqByNode;
    List<Integer>[] adj;
    boolean[] visited;
    int[] parents;
    int[] ancesters;
    int[][] edges;
    int[][] queries;
    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        int m = queries.length;
        this.edges = edges;
        this.queries = queries;
        lcasByQuery = new int[m];
        queriesByNode = buildQueriesByNode(n, queries);
        valueFreqByNode = new int[n][];
        
        visited = new boolean[n];
        parents = new int[n];
        for(int i = 0; i < n; i++){
            parents[i] = i;
        }
        ancesters = new int[n];
        adj = buildAdj(n, edges);
        valueFreqByNode[0] = new int[26];
        dfs(0);
        // System.out.println(Arrays.toString(lcasByQuery));
        int[] ans = new int[m];
        for(int i = 0; i < m; i++){
            int[] valueFrqBeteen = getValueFreq(queries[i][0], queries[i][1], lcasByQuery[i]);
            int totalFreq = Arrays.stream(valueFrqBeteen).sum();
            int maxFreq = Arrays.stream(valueFrqBeteen).max().getAsInt();
            ans[i] = totalFreq - maxFreq;
        }
        return ans;
    }

    List[] buildAdj(int n, int[][] edges){
        List[] adj = new List[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < edges.length; i++){
            adj[edges[i][0]].add(i);
            adj[edges[i][1]].add(i);
        }
        return adj;
    }

    Map<Integer, List<Integer>> buildQueriesByNode(int n, int[][] queries){
        Map<Integer, List<Integer>> queriesByNode = new HashMap<>();
        for(int i = 0; i < n; i++){
            queriesByNode.put(i, new ArrayList<>());
        }
        for(int i = 0; i < queries.length; i++){
            queriesByNode.get(queries[i][0]).add(i);
            queriesByNode.get(queries[i][1]).add(i);
        }
        return queriesByNode;
    }

    int find(int x){
        if(parents[x] != x){
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }

    void union(int u, int v){
        int pu = find(u);
        int pv = find(v);
        if(pu != pv){
            parents[pu] = pv;
        }
    }

    int[] getValueFreq(int u, int v, int lca){
        int[] ret = new int[26];
        for (int i = 0; i < 26; i++) {
            ret[i] = valueFreqByNode[u][i] + valueFreqByNode[v][i] - 2 * valueFreqByNode[lca][i];
        }
        return ret;
    }

    void dfs(int u){
        visited[u] = true;
        ancesters[u] = u;
        for(int e : adj[u]){
            int v = edges[e][0] + edges[e][1] - u;
            if(!visited[v]){
                valueFreqByNode[v] = Arrays.copyOf(valueFreqByNode[u], 26);
                valueFreqByNode[v][edges[e][2] - 1]++;
                dfs(v);
                union(u, v);
                ancesters[find(u)] = u;
            }
        }
        for(int queryId : queriesByNode.get(u)){
            int v = queries[queryId][0] + queries[queryId][1] - u;
            if(visited[v]){
                lcasByQuery[queryId] = ancesters[find(v)]; 
            }
        }
    }
}
// @lc code=end
