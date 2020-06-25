/*
 * @lc app=leetcode id=1489 lang=java
 *
 * [1489] Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree
 *
 * https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/description/
 *
 * algorithms
 * Hard (37.36%)
 * Likes:    22
 * Dislikes: 16
 * Total Accepted:    565
 * Total Submissions: 1.5K
 * Testcase Example:  '5\n[[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]'
 *
 * Given a weighted undirected connected graph with n vertices numbered from 0
 * to n-1, and an array edges where edges[i] = [fromi, toi, weighti] represents
 * a bidirectional and weighted edge between nodes fromi and toi. A minimum
 * spanning tree (MST) is a subset of the edges of the graph that connects all
 * vertices without cycles and with the minimum possible total edge weight.
 * 
 * Find all the critical and pseudo-critical edges in the minimum spanning tree
 * (MST) of the given graph. An MST edge whose deletion from the graph would
 * cause the MST weight to increase is called a critical edge. A
 * pseudo-critical edge, on the other hand, is that which can appear in some
 * MSTs but not all.
 * 
 * Note that you can return the indices of the edges in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: n = 5, edges =
 * [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
 * Output: [[0,1],[2,3,4,5]]
 * Explanation: The figure above describes the graph.
 * The following figure shows all the possible MSTs:
 * 
 * Notice that the two edges 0 and 1 appear in all MSTs, therefore they are
 * critical edges, so we return them in the first list of the output.
 * The edges 2, 3, 4, and 5 are only part of some MSTs, therefore they are
 * considered pseudo-critical edges. We add them to the second list of the
 * output.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
 * Output: [[],[0,1,2,3]]
 * Explanation: We can observe that since all 4 edges have equal weight,
 * choosing any 3 edges from the given 4 will yield an MST. Therefore all 4
 * edges are pseudo-critical.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 100
 * 1 <= edges.length <= min(200, n * (n - 1) / 2)
 * edges[i].length == 3
 * 0 <= fromi < toi < n
 * 1 <= weighti <= 1000
 * All pairs (fromi, toi) are distinct.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
    	int min = mst(n, edges, -1, -1);
    	// System.out.println(min);
    	List<List<Integer>> ans = new ArrayList<>();
    	ans.add(new ArrayList<>());
    	ans.add(new ArrayList<>());
        for(int i = 0; i < edges.length; i++){
        	if(min != mst(n, edges, i, -1)){
        		ans.get(0).add(i);
        	}
        	else if(min == mst(n, edges, -1, i)){
        		ans.get(1).add(i);
        	}
        }
        return ans;
    }

    int mst(int n, int[][] edges, int skip, int pick){
    	boolean[] selected = new boolean[n];
    	List<Integer>[] adj = new List[n];
    	for(int i = 0; i < adj.length; i++){
    		adj[i] = new ArrayList<Integer>();
    	}
    	for(int i = 0; i < edges.length; i++){
    		if(i == skip){
    			continue;
    		}
    		adj[edges[i][0]].add(i);
    		adj[edges[i][1]].add(i);
    	}
    	PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> Integer.compare(edges[a][2], edges[b][2]));
    	int sum = 0;
    	if(pick == -1){
	    	for(int i : adj[0]){
	    		q.offer(i);	
	    	}
	    	selected[0] = true;	
    	}
    	else{
    		sum += edges[pick][2];
    		selected[edges[pick][0]] = true;
    		selected[edges[pick][1]] = true;
    		for(int e : adj[edges[pick][0]]){
    			q.offer(e);
    		}
    		for(int e : adj[edges[pick][1]]){
    			q.offer(e);
    		}
    	}
    	
    	while(!q.isEmpty()){
    		int i = q.poll();
    		int u = edges[i][0];
    		if(selected[u]){
    			u = edges[i][1];
    		}
    		if(!selected[u]){
    			selected[u] = true;
    			// System.out.println(Arrays.toString(edges[i]));
    			sum += edges[i][2];
    			for(int j: adj[u]){
    				int v = edges[j][0] + edges[j][1] - u;
    				if(!selected[v]){
    					q.offer(j);	
    				}
    			}
    		}
    	}
    	boolean all = true;
    	for(boolean x : selected){
    		all &= x;
    	}
    	return all ? sum : -1;
    }
}
// @lc code=end
