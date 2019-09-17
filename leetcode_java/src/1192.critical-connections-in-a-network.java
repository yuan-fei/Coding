/*
 * @lc app=leetcode id=1192 lang=java
 *
 * [1192] Critical Connections in a Network
 *
 * https://leetcode.com/problems/critical-connections-in-a-network/description/
 *
 * algorithms
 * Hard (33.60%)
 * Total Accepted:    784
 * Total Submissions: 2.3K
 * Testcase Example:  '4\n[[0,1],[1,2],[2,0],[1,3]]'
 *
 * There are n servers numbered from 0 to n-1 connected by undirected
 * server-to-server connections forming a network where connections[i] = [a, b]
 * represents a connection between servers a and b. Any server can reach any
 * other server directly or indirectly through the network.
 * 
 * A critical connection is a connection that, if removed, will make some
 * server unable to reach some other server.
 * 
 * Return all critical connections in the network in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^5
 * n-1 <= connections.length <= 10^5
 * connections[i][0] != connections[i][1]
 * There are no repeated connections.
 * 
 */
class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		adj = new List[n];
        for(int i = 0; i < n; i++){
        	adj[i] = new ArrayList<>();
        }
        for(List<Integer> c : connections){
        	int u = c.get(0);
        	int v = c.get(1);
        	adj[u].add(v);
        	adj[v].add(u);
        }
        List<List<Integer>> ans = new ArrayList<>();
        startTime = new int[n];
        lowestAncesterReached = new int[n];
        Arrays.fill(startTime, -1);
        dfs(0, -1, ans);
        // System.out.println(Arrays.toString(startTime));
        // System.out.println(Arrays.toString(lowestAncesterReached));
        return ans;
    }

	List<Integer>[] adj;
    int[] startTime;
    int[] lowestAncesterReached;
    int counter = 0;

    void dfs(int u, int p, List<List<Integer>> ans){
    	startTime[u] = counter++;
    	lowestAncesterReached[u] = startTime[u];
    	for (int v : adj[u]) {
    		if(v != p){
	    		if(startTime[v] == -1){
	    			dfs(v, u, ans);
		    		if(startTime[u] < lowestAncesterReached[v]){
		    			ans.add(Arrays.asList(u, v));
		    		}
	    		}
	    		lowestAncesterReached[u] = Math.min(lowestAncesterReached[u], lowestAncesterReached[v]);
    		}
    		
    	}
    }
}
