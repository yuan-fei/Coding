/*
 * @lc app=leetcode id=847 lang=java
 *
 * [847] Shortest Path Visiting All Nodes
 *
 * https://leetcode.com/problems/shortest-path-visiting-all-nodes/description/
 *
 * algorithms
 * Hard (48.35%)
 * Total Accepted:    9.8K
 * Total Submissions: 20.1K
 * Testcase Example:  '[[1,2,3],[0],[0],[0]]'
 *
 * An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is
 * given as graph.
 * 
 * graph.length = N, and j != i is in the list graph[i] exactly once, if and
 * only if nodes i and j are connected.
 * 
 * Return the length of the shortest path that visits every node. You may start
 * and stop at any node, you may revisit nodes multiple times, and you may
 * reuse edges.
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [[1,2,3],[0],[0],[0]]
 * Output: 4
 * Explanation: One possible path is [1,0,2,0,3]
 * 
 * Example 2:
 * 
 * 
 * Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * Output: 4
 * Explanation: One possible path is [0,1,4,2,3]
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= graph.length <= 12
 * 0 <= graph[i].length < graph.length
 * 
 * 
 */
class Solution {
    public int shortestPathLength(int[][] graph) {
    	int N = graph.length;
    	boolean[][] adj = new boolean[N][N];
    	for (int i = 0; i < graph.length; i++) {
    		for(int j : graph[i]){
    			adj[i][j] = true;
    		}
    	}
    	int MAX = N * N;
    	int[][] d = new int[N][1 << N];
    	for(int[] row:d){
    		Arrays.fill(row, MAX);	
    	}
    	
		Queue<State> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
        	d[i][1<<i] = 0;
    		q.offer(new State(i, 1 << i));
        }
        
        while(!q.isEmpty()){
        	State s = q.poll();
        	if(s.b == (1 << N) - 1){
        		return d[s.h][s.b];
        	}
        	for(int i = 0; i < N; i++){
        		if(adj[i][s.h]){
        			if(d[i][s.b | (1 << i)] > d[s.h][s.b] + 1){
        				d[i][s.b | (1 << i)] = d[s.h][s.b] + 1;
        				q.offer(new State(i, s.b | (1 << i)));
        			}
        			
        		}
        	}
        }
        
        
        return -1;
    }

    static class State {
	    int h, b;
	    State(int hh, int bb) {
	        h = hh;
	        b = bb;
	    }
	}
}
