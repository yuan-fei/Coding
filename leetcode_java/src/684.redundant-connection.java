/*
 * @lc app=leetcode id=684 lang=java
 *
 * [684] Redundant Connection
 *
 * https://leetcode.com/problems/redundant-connection/description/
 *
 * algorithms
 * Medium (59.55%)
 * Likes:    2268
 * Dislikes: 259
 * Total Accepted:    136.1K
 * Total Submissions: 228.4K
 * Testcase Example:  '[[1,2],[1,3],[2,3]]'
 *
 * In this problem, a tree is an undirected graph that is connected and has no
 * cycles.
 * 
 * You are given a graph that started as a tree with n nodes labeled from 1 to
 * n, with one additional edge added. The added edge has two different vertices
 * chosen from 1 to n, and was not an edge that already existed. The graph is
 * represented as an array edges of length n where edges[i] = [ai, bi]
 * indicates that there is an edge between nodes ai and bi in the graph.
 * 
 * Return an edge that can be removed so that the resulting graph is a tree of
 * n nodes. If there are multiple answers, return the answer that occurs last
 * in the input.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: edges = [[1,2],[1,3],[2,3]]
 * Output: [2,3]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
 * Output: [1,4]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai < bi <= edges.length
 * ai != bi
 * There are no repeated edges.
 * The given graph is connected.
 * 
 * 
 */

// @lc code=start
class Solution {
	Set<Integer>[] adj;
    public int[] findRedundantConnection(int[][] edges) {
    	adj = new Set[1001];   
    	for(int i = 0; i < adj.length; i++){
    		adj[i] = new HashSet<>();
    	}
    	int maxN = 0;
    	for(int[] e : edges){
    		maxN = Math.max(maxN, e[0]);
    		maxN = Math.max(maxN, e[1]);
    		adj[e[0]].add(e[1]);
    		adj[e[1]].add(e[0]);
    	}
    	for(int i = edges.length - 1; i >= 0 ; i--){
    		adj[edges[i][0]].remove(edges[i][1]);
    		adj[edges[i][1]].remove(edges[i][0]);
    		boolean[] state = new boolean[1001];
    		dfs(1, state);
    		boolean good = true;
    		for(int ii = 1; ii <= maxN; ii++){
    			if(!state[ii]){
    				good =false;
    				break;
    			}
    		}
    		if(good){
    			return edges[i];
    		}
    		adj[edges[i][0]].add(edges[i][1]);
    		adj[edges[i][1]].add(edges[i][0]);
    	}
    	return null;
    }

    void dfs(int u, boolean[] state){
    	state[u] = true;
    	for(int v : adj[u]){
    		if(!state[v]){
    			dfs(v, state);
    		}
    	}
    }
}
// @lc code=end
