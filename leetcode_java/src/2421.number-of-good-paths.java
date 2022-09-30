/*
 * @lc app=leetcode id=2421 lang=java
 *
 * [2421] Number of Good Paths
 *
 * https://leetcode.com/problems/number-of-good-paths/description/
 *
 * algorithms
 * Hard (32.46%)
 * Likes:    280
 * Dislikes: 8
 * Total Accepted:    3.7K
 * Total Submissions: 11.4K
 * Testcase Example:  '[1,3,2,1,3]\n[[0,1],[0,2],[2,3],[2,4]]'
 *
 * There is a tree (i.e. a connected, undirected graph with no cycles)
 * consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges.
 * 
 * You are given a 0-indexed integer array vals of length n where vals[i]
 * denotes the value of the i^th node. You are also given a 2D integer array
 * edges where edges[i] = [ai, bi] denotes that there exists an undirected edge
 * connecting nodes ai and bi.
 * 
 * A good path is a simple path that satisfies the following conditions:
 * 
 * 
 * The starting node and the ending node have the same value.
 * All nodes between the starting node and the ending node have values less
 * than or equal to the starting node (i.e. the starting node's value should be
 * the maximum value along the path).
 * 
 * 
 * Return the number of distinct good paths.
 * 
 * Note that a path and its reverse are counted as the same path. For example,
 * 0 -> 1 is considered to be the same as 1 -> 0. A single node is also
 * considered as a valid path.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: vals = [1,3,2,1,3], edges = [[0,1],[0,2],[2,3],[2,4]]
 * Output: 6
 * Explanation: There are 5 good paths consisting of a single node.
 * There is 1 additional good path: 1 -> 0 -> 2 -> 4.
 * (The reverse path 4 -> 2 -> 0 -> 1 is treated as the same as 1 -> 0 -> 2 ->
 * 4.)
 * Note that 0 -> 2 -> 3 is not a good path because vals[2] > vals[0].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: vals = [1,1,2,2,3], edges = [[0,1],[1,2],[2,3],[2,4]]
 * Output: 7
 * Explanation: There are 5 good paths consisting of a single node.
 * There are 2 additional good paths: 0 -> 1 and 2 -> 3.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: vals = [1], edges = []
 * Output: 1
 * Explanation: The tree consists of only one node, so there is one good
 * path.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == vals.length
 * 1 <= n <= 3 * 10^4
 * 0 <= vals[i] <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges represents a valid tree.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        List<Integer>[] adj = new List[n];

        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        
        for(int[] e : edges){
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        TreeMap<Integer, List<Integer>> m = new TreeMap<>();
        for(int i = 0; i < n; i++){
            if(!m.containsKey(vals[i])){
                m.put(vals[i], new ArrayList<>());
            }
            m.get(vals[i]).add(i);
        }

        DSU dsu = new DSU(n);
        int ans = 0;
        for(int k : m.keySet()){
            List<Integer> nodes = m.get(k);
            for(int u : nodes){
                for(int v : adj[u]){
                    if(vals[u] >= vals[v]){
                        dsu.union(u, v);
                    }
                }
            }
            Map<Integer, Integer> cnt = new HashMap<>();
            for(int u : nodes){
                int group = dsu.find(u);
                cnt.put(group, cnt.getOrDefault(group, 0) + 1);
                ans += cnt.get(group);
            }
            System.out.println(nodes + ", " + ans);
        }
        return ans;

    }

    static class DSU {
        int[] parent;

        public DSU(int N) {
            this.parent = new int[N];
            for (int i = 0; i < N; i++) {
                add(i);
            }
        }

        public void add(int x) {
            parent[x] = x;
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            if (find(x) != find(y)) {
                parent[find(x)] = parent[find(y)];
            }
        }
    }
}
// @lc code=end
