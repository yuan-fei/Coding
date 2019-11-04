/*
 * @lc app=leetcode id=1245 lang=java
 *
 * [1245] Tree Diameter
 *
 * https://leetcode.com/problems/tree-diameter/description/
 *
 * algorithms
 * Medium (49.26%)
 * Likes:    48
 * Dislikes: 1
 * Total Accepted:    1.5K
 * Total Submissions: 3K
 * Testcase Example:  '[[0,1],[0,2]]'
 *
 * Given an undirected tree, return its diameter: the number of edges in a
 * longest path in that tree.
 * 
 * The tree is given as an array of edges where edges[i] = [u, v] is a
 * bidirectional edge between nodes u and v.  Each node has labels in the set
 * {0, 1, ..., edges.length}.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: edges = [[0,1],[0,2]]
 * Output: 2
 * Explanation: 
 * A longest path of the tree is the path 1 - 0 - 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
 * Output: 4
 * Explanation: 
 * A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= edges.length < 10^4
 * edges[i][0] != edges[i][1]
 * 0 <= edges[i][j] <= edges.length
 * The given edges form an undirected tree.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int treeDiameter(int[][] edges) {
        adj = new List[10005];
        for(int i = 0; i < 10005; i++){
        	adj[i] = new ArrayList<>();
        }
        for(int[] e : edges){
    		adj[e[0]].add(e[1]);
    		adj[e[1]].add(e[0]);
    	}
    	dfs(-1, 0, 0);
    	return dfs(-1, farthestLeaf, 0);
    }
	List<Integer>[] adj;
    int farthestLeaf = -1;
    int maxHeight = 0;
    int dfs(int p, int r, int h){
    	int mH = h;
    	for(int c : adj[r]){
    		if(c != p){
    			mH = Math.max(mH, dfs(r, c, h + 1));
    		}
    	}
    	if(mH > maxHeight){
    		maxHeight = mH;
    		farthestLeaf = r;
    	}
    	return mH;
    }
}
// @lc code=end
