/*
 * @lc app=leetcode id=2642 lang=java
 *
 * [2642] Design Graph With Shortest Path Calculator
 *
 * https://leetcode.com/problems/design-graph-with-shortest-path-calculator/description/
 *
 * algorithms
 * Hard (52.63%)
 * Likes:    61
 * Dislikes: 5
 * Total Accepted:    4.8K
 * Total Submissions: 9.2K
 * Testcase Example:  '["Graph","shortestPath","shortestPath","addEdge","shortestPath"]\n' +
  '[[4,[[0,2,5],[0,1,2],[1,2,1],[3,0,3]]],[3,2],[0,3],[[1,3,4]],[0,3]]'
 *
 * There is a directed weighted graph that consists of n nodes numbered from 0
 * to n - 1. The edges of the graph are initially represented by the given
 * array edges where edges[i] = [fromi, toi, edgeCosti] meaning that there is
 * an edge from fromi to toi with the cost edgeCosti.
 * 
 * Implement the Graph class:
 * 
 * 
 * Graph(int n, int[][] edges) initializes the object with n nodes and the
 * given edges.
 * addEdge(int[] edge) adds an edge to the list of edges where edge = [from,
 * to, edgeCost]. It is guaranteed that there is no edge between the two nodes
 * before adding this one.
 * int shortestPath(int node1, int node2) returns the minimum cost of a path
 * from node1 to node2. If no path exists, return -1. The cost of a path is the
 * sum of the costs of the edges in the path.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["Graph", "shortestPath", "shortestPath", "addEdge", "shortestPath"]
 * [[4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]], [3, 2], [0, 3], [[1, 3,
 * 4]], [0, 3]]
 * Output
 * [null, 6, -1, null, 6]
 * 
 * Explanation
 * Graph g = new Graph(4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]);
 * g.shortestPath(3, 2); // return 6. The shortest path from 3 to 2 in the
 * first diagram above is 3 -> 0 -> 1 -> 2 with a total cost of 3 + 2 + 1 = 6.
 * g.shortestPath(0, 3); // return -1. There is no path from 0 to 3.
 * g.addEdge([1, 3, 4]); // We add an edge from node 1 to node 3, and we get
 * the second diagram above.
 * g.shortestPath(0, 3); // return 6. The shortest path from 0 to 3 now is 0 ->
 * 1 -> 3 with a total cost of 2 + 4 = 6.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 100
 * 0 <= edges.length <= n * (n - 1)
 * edges[i].length == edge.length == 3
 * 0 <= fromi, toi, from, to, node1, node2 <= n - 1
 * 1 <= edgeCosti, edgeCost <= 10^6
 * There are no repeated edges and no self-loops in the graph at any point.
 * At most 100 calls will be made for addEdge.
 * At most 100 calls will be made for shortestPath.
 * 
 * 
 */

// @lc code=start
class Graph {
    List<int[]>[] adj;
    int n;
    public Graph(int n, int[][] edges) {
        this.n = n;
        adj = new List[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int[] e : edges){
            adj[e[0]].add(new int[]{e[1], e[2]});
        }
    }
    
    public void addEdge(int[] edge) {
        adj[edge[0]].add(new int[]{edge[1], edge[2]});
    }
    
    public int shortestPath(int node1, int node2) {
        int MAX = (int)1e9;
        int[] dist = new int[n];
        Arrays.fill(dist, MAX);
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        q.offer(new int[] { node1, 0 });
        int done = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (dist[cur[0]] > cur[1]) {
                dist[cur[0]] = cur[1];
                if(cur[0] == node2){
                    return dist[node2];
                }
                for (int[] e : adj[cur[0]]) {
                    int v = e[0];
                    if (dist[v] > dist[cur[0]] + e[1]) {
                        q.offer(new int[] { v, dist[cur[0]] + e[1]});
                    }
                }
            }
        }
        return -1;
    }
}

/**
 * Your Graph object will be instantiated and called as such:
 * Graph obj = new Graph(n, edges);
 * obj.addEdge(edge);
 * int param_2 = obj.shortestPath(node1,node2);
 */
// @lc code=end
