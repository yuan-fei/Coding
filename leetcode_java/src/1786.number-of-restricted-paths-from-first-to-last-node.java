/*
 * @lc app=leetcode id=1786 lang=java
 *
 * [1786] Number of Restricted Paths From First to Last Node
 *
 * https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/description/
 *
 * algorithms
 * Medium (35.34%)
 * Likes:    171
 * Dislikes: 31
 * Total Accepted:    4.2K
 * Total Submissions: 11.8K
 * Testcase Example:  '5\n[[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]'
 *
 * There is an undirected weighted connected graph. You are given a positive
 * integer n which denotes that the graph has n nodes labeled from 1 to n, and
 * an array edges where each edges[i] = [ui, vi, weighti] denotes that there is
 * an edge between nodes ui and vi with weight equal to weighti.
 * 
 * A path from node start to node end is a sequence of nodes [z0, z1, z2, ...,
 * zk] such that z0 = start and zk = end and there is an edge between zi and
 * zi+1 where 0 <= i <= k-1.
 * 
 * The distance of a path is the sum of the weights on the edges of the path.
 * Let distanceToLastNode(x) denote the shortest distance of a path between
 * node n and node x. A restricted path is a path that also satisfies that
 * distanceToLastNode(zi) > distanceToLastNode(zi+1) where 0 <= i <= k-1.
 * 
 * Return the number of restricted paths from node 1 to node n. Since that
 * number may be too large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 5, edges =
 * [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
 * Output: 3
 * Explanation: Each circle contains the node number in black and its
 * distanceToLastNode value in blue. The three restricted paths are:
 * 1) 1 --> 2 --> 5
 * 2) 1 --> 2 --> 3 --> 5
 * 3) 1 --> 3 --> 5
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 7, edges =
 * [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
 * Output: 1
 * Explanation: Each circle contains the node number in black and its
 * distanceToLastNode value in blue. The only restricted path is 1 --> 3 -->
 * 7.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 2 * 10^4
 * n - 1 <= edges.length <= 4 * 10^4
 * edges[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 1 <= weighti <= 10^5
 * There is at most one edge between any two nodes.
 * There is at least one path between any two nodes.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countRestrictedPaths(int n, int[][] edges) {
    	Set<Integer>[] adj = build(n, edges);
        int[] dist = dijkstra(n, adj, edges);
        // System.out.println(Arrays.toString(dist));
        for(int i = 0; i < edges.length; i++){
        	if(dist[edges[i][0]] > dist[edges[i][1]]){
        		adj[edges[i][1]].remove(i);
        	}
        	else if(dist[edges[i][0]] < dist[edges[i][1]]){
        		adj[edges[i][0]].remove(i);
        	}
        	else{
        		adj[edges[i][0]].remove(i);
        		adj[edges[i][1]].remove(i);
        	}
        }
        // for(Set<Integer> e : adj){
        // 	System.out.println(e);
        // }
        cache = new int[n + 1];
        Arrays.fill(cache, -1);
        cache[n] = 1;
        return dfs(1, adj, edges, dist);
    }

    Set<Integer>[] build(int n, int[][] edges){
    	Set<Integer>[] adj = new Set[n + 1];
    	for(int i = 0; i <= n; i++){
    		adj[i] = new HashSet<>();
    	}
    	for(int i = 0; i < edges.length; i++){
    		adj[edges[i][0]].add(i);
    		adj[edges[i][1]].add(i);	
    	}
    	return adj;
    }

    private int[] dijkstra(int N, Set<Integer>[] adj, int[][] edges) {
    	int MAX = 2 * 1000000001;
    	int[] dist = new int[N + 1];
		Arrays.fill(dist, MAX);
		// int[2]: {vertex, dist}
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		q.offer(new int[] { N, 0 });
		int done = 0;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (dist[cur[0]] > cur[1]) {
				dist[cur[0]] = cur[1];
				done++;
				if (done == N) {
					break;
				} else {
					for (int e : adj[cur[0]]) {
						int v = edges[e][0] + edges[e][1] - cur[0];
						if (dist[v] > dist[cur[0]] + edges[e][2]) {
							q.offer(new int[] { v, dist[cur[0]] + edges[e][2] });
						}
					}
				}
			}
		}
		return dist;
	}

	int[] cache;
	int MOD = 1000000007;
	int dfs(int u, Set<Integer>[] adj, int[][] edges, int[] dist){
		// System.out.println(u);
		if(cache[u] == -1){
			cache[u] = 0;
			for(int e : adj[u]){
				int v = edges[e][0] + edges[e][1] - u;
				if(dist[v] < dist[u]){
					int cnt = dfs(v, adj, edges, dist);
					cache[u] += cnt;
					cache[u] %= MOD;
				}
			}
		}
		return cache[u];
	}
}
// @lc code=end
