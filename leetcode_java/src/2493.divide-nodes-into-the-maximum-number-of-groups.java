/*
 * @lc app=leetcode id=2493 lang=java
 *
 * [2493] Divide Nodes Into the Maximum Number of Groups
 *
 * https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/description/
 *
 * algorithms
 * Hard (23.97%)
 * Likes:    46
 * Dislikes: 5
 * Total Accepted:    1.1K
 * Total Submissions: 4.7K
 * Testcase Example:  '6\n[[1,2],[1,4],[1,5],[2,6],[2,3],[4,6]]'
 *
 * You are given a positive integer n representing the number of nodes in an
 * undirected graph. The nodes are labeled from 1 to n.
 * 
 * You are also given a 2D integer array edges, where edges[i] = [ai, bi]
 * indicates that there is a bidirectional edge between nodes ai and bi. Notice
 * that the given graph may be disconnected.
 * 
 * Divide the nodes of the graph into m groups (1-indexed) such that:
 * 
 * 
 * Each node in the graph belongs to exactly one group.
 * For every pair of nodes in the graph that are connected by an edge [ai, bi],
 * if ai belongs to the group with index x, and bi belongs to the group with
 * index y, then |y - x| = 1.
 * 
 * 
 * Return the maximum number of groups (i.e., maximum m) into which you can
 * divide the nodes. Return -1 if it is impossible to group the nodes with the
 * given conditions.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 6, edges = [[1,2],[1,4],[1,5],[2,6],[2,3],[4,6]]
 * Output: 4
 * Explanation: As shown in the image we:
 * - Add node 5 to the first group.
 * - Add node 1 to the second group.
 * - Add nodes 2 and 4 to the third group.
 * - Add nodes 3 and 6 to the fourth group.
 * We can see that every edge is satisfied.
 * It can be shown that that if we create a fifth group and move any node from
 * the third or fourth group to it, at least on of the edges will not be
 * satisfied.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 3, edges = [[1,2],[2,3],[3,1]]
 * Output: -1
 * Explanation: If we add node 1 to the first group, node 2 to the second
 * group, and node 3 to the third group to satisfy the first two edges, we can
 * see that the third edge will not be satisfied.
 * It can be shown that no grouping is possible.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 500
 * 1 <= edges.length <= 10^4
 * edges[i].length == 2
 * 1 <= ai, bi <= n
 * ai != bi
 * There is at most one edge between any pair of vertices.
 * 
 * 
 */

// @lc code=start
class Solution {
    Map<Integer, List<Integer>> map;
    public int magnificentSets(int n, int[][] edges) {
        map = new HashMap<>();
        for(int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }
        // adjacency list
        for(int[] edge : edges) {
            int u = edge[0], v = edge[1];
            map.get(u).add(v);
            map.get(v).add(u);
        }
        
        // get all components as Graph can be disconnected
        List<List<Integer>> components = getComponents(n);
        
        int ans = 0;
        /*
            - Take each component and get max groups can be formed from that component
            - return -1 if you can't form groups from any one of the components
        */
        for(List<Integer> component : components) {
            int groups = -1;
            for(int node : component) {
                groups = Math.max(groups, find(node, n));
            }
            if(groups == -1)
                return -1;
            ans += groups;
        }
        return ans;
    }
    
    private List<List<Integer>> getComponents(int n) {
        boolean[] visited = new boolean[n + 1];
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            if(!visited[i]) {
                ans.add(visit(i, new ArrayList<>(), visited));
            }
        }
        return ans;
    }
    
    private List<Integer> visit(int cur, List<Integer> nodes, boolean[] visited) {
        visited[cur] = true;
        nodes.add(cur);
        for(int next : map.get(cur)) {
            // skip if you have already visited this node
            if(visited[next]) continue;
            visit(next, nodes, visited);
        }
        return nodes;
    }
    
    private int find(int node, int n) {
        int[] group = new int[n + 1];
        Arrays.fill(group, -1);
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        int groups = 0;
        while(!queue.isEmpty()) {
            int k = queue.size();
            // store nodes in set to avoid duplicates
            Set<Integer> set = new HashSet<>();
            while(k-- > 0) {
                int cur = queue.poll();
                // this case occurs when 2 nodes in the same level are connected
                // so, return -1
                if(group[cur] != -1) return -1;
                group[cur] = groups;
                for(int next : map.get(cur)) {
                    if(group[next] == -1) {
                        set.add(next);
                    }
                }
            }
            queue.addAll(set);
            groups++;
        }
        return groups;
    }
}
// @lc code=end
