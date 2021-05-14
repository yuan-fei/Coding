/*
 * @lc app=leetcode id=1857 lang=java
 *
 * [1857] Largest Color Value in a Directed Graph
 *
 * https://leetcode.com/problems/largest-color-value-in-a-directed-graph/description/
 *
 * algorithms
 * Hard (32.57%)
 * Likes:    117
 * Dislikes: 5
 * Total Accepted:    2.5K
 * Total Submissions: 7.7K
 * Testcase Example:  '"abaca"\n[[0,1],[0,2],[2,3],[3,4]]'
 *
 * There is a directed graph of n colored nodes and m edges. The nodes are
 * numbered from 0 to n - 1.
 * 
 * You are given a string colors where colors[i] is a lowercase English letter
 * representing the color of the i^th node in this graph (0-indexed). You are
 * also given a 2D array edges where edges[j] = [aj, bj] indicates that there
 * is a directed edge from node aj to node bj.
 * 
 * A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk
 * such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The
 * color value of the path is the number of nodes that are colored the most
 * frequently occurring color along that path.
 * 
 * Return the largest color value of any valid path in the given graph, or -1
 * if the graph contains a cycle.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a"
 * (red in the above image).
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: colors = "a", edges = [[0,0]]
 * Output: -1
 * Explanation: There is a cycle from 0 to 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == colors.length
 * m == edges.length
 * 1 <= n <= 10^5
 * 0 <= m <= 10^5
 * colors consists of lowercase English letters.
 * 0 <= aj, bj < n
 * 
 * 
 */

// @lc code=start
class Solution {
    public int largestPathValue(String colors, int[][] edges) {
    	int n = colors.length();
        List<Integer>[] adj = buildAdj(n, edges);
        int[][] freq = new int[n][26];
        int[] state = new int[n];
        for(int i = 0; i < n; i++){
        	if(state[i] == 0){
        		if(!dfs(i, colors, state, adj, freq)){
        			return -1;
        		}
        	}
        }
        int max = 0;
        for(int[] f: freq){
        	for(int x : f){
        		max = Math.max(max, x);
        	}
        }
        return max;
    }

    List<Integer>[] buildAdj(int n, int[][] edges){
    	List<Integer>[] adj = new List[n];
    	for(int i = 0; i < adj.length; i++){
    		adj[i] = new ArrayList<>();
    	}
    	for(int[] e : edges){
    		adj[e[0]].add(e[1]);
    	}
    	return adj;
    }

    boolean dfs(int u, String colors, int[] state, List<Integer>[] adj, int[][] freq){
    	// System.out.println(u);
    	state[u] = 1;
    	for(int v : adj[u]){
    		if(state[v] == 1){
    			return false;
    		}
    		if(state[v] == 0 && !dfs(v, colors, state, adj, freq)){
    			return false;
    		}
    		addTo(freq[v], freq[u]);
    	}
    	state[u] = 2;
    	freq[u][colors.charAt(u) - 'a']++;
    	// System.out.println(u);
    	return true;
    }

    void addTo(int[] a, int[] b){
    	for(int i = 0; i < a.length; i++){
    		b[i] = Math.max(b[i], a[i]);
    	}
    }
}
// @lc code=end
