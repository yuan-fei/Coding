/*
 * @lc app=leetcode id=310 lang=java
 *
 * [310] Minimum Height Trees
 *
 * https://leetcode.com/problems/minimum-height-trees/description/
 *
 * algorithms
 * Medium (31.26%)
 * Likes:    1378
 * Dislikes: 82
 * Total Accepted:    79.8K
 * Total Submissions: 255.3K
 * Testcase Example:  '4\n[[1,0],[1,2],[1,3]]'
 *
 * For an undirected graph with tree characteristics, we can choose any node as
 * the root. The result graph is then a rooted tree. Among all possible rooted
 * trees, those with minimum height are called minimum height trees (MHTs).
 * Given such a graph, write a function to find all the MHTs and return a list
 * of their root labels.
 * 
 * Format
 * The graph contains n nodes which are labeled from 0 to n - 1. You will be
 * given the number n and a list of undirected edges (each edge is a pair of
 * labels).
 * 
 * You can assume that no duplicate edges will appear in edges. Since all edges
 * are undirected, [0, 1] is the same as [1, 0] and thus will not appear
 * together in edges.
 * 
 * Example 1 :
 * 
 * 
 * Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 * 
 * ⁠       0
 * ⁠       |
 * ⁠       1
 * ⁠      / \
 * ⁠     2   3 
 * 
 * Output: [1]
 * 
 * 
 * Example 2 :
 * 
 * 
 * Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 * 
 * ⁠    0  1  2
 * ⁠     \ | /
 * ⁠       3
 * ⁠       |
 * ⁠       4
 * ⁠       |
 * ⁠       5 
 * 
 * Output: [3, 4]
 * 
 * Note:
 * 
 * 
 * According to the definition of tree on Wikipedia: “a tree is an undirected
 * graph in which any two vertices are connected by exactly one path. In other
 * words, any connected graph without simple cycles is a tree.”
 * The height of a rooted tree is the number of edges on the longest downward
 * path between the root and a leaf.
 * 
 * 
 */

// @lc code=start
class Solution {
	List<Integer>[] adj;
	// max height
	int[] height1;
	// 2nd max height
	int[] height2;
	// height
	int[] dp;
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        adj = new List[n];
        height1 = new int[n];
        height2 = new int[n];
        dp = new int[n];
        for(int i = 0; i < n; i++){
        	adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
        	adj[e[0]].add(e[1]);
        	adj[e[1]].add(e[0]);
        }
        //randomly pick a root
        dfs1(0, -1);
        dfs2(0, -1, 0);
        int minHeight = Integer.MAX_VALUE;
        for(int h: dp){
        	minHeight = Math.min(minHeight, h);
        }
        List<Integer> minHeightRoots = new ArrayList<>();
        for(int i = 0; i < n; i++){
        	if(dp[i] == minHeight){
        		minHeightRoots.add(i);
        	}
        }
        return minHeightRoots;
    }

    int dfs1(int u, int p){
    	for(int v : adj[u]){
    		if(v != p){
    			dfs1(v, u);
    			if(height1[v] + 1 > height1[u]){
    				height2[u] = height1[u];
    				height1[u] = height1[v] + 1;
    			}
    			else if(height1[v] + 1 > height2[u]){
    				height2[u] = height1[v] + 1;
    			}
    		}
    	}
    	return height1[u];
    }

    void dfs2(int u, int p, int maxHeightUp){
    	dp[u] = Math.max(maxHeightUp, height1[u]);
    	for(int v : adj[u]){
    		if(v != p){
    			int maxHeightUpNext = 0;
    			if(height1[v] + 1 == height1[u]){
    				maxHeightUpNext = Math.max(maxHeightUp + 1, height2[u] + 1);
    			}
    			else{
    				maxHeightUpNext = Math.max(maxHeightUp + 1, height1[u] + 1);
    			}
    			dfs2(v, u, maxHeightUpNext);
    		}
    	}
    }
}
// @lc code=end
