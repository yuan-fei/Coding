/*
 * @lc app=leetcode id=1483 lang=java
 *
 * [1483] Kth Ancestor of a Tree Node
 *
 * https://leetcode.com/problems/kth-ancestor-of-a-tree-node/description/
 *
 * algorithms
 * Hard (23.31%)
 * Likes:    137
 * Dislikes: 39
 * Total Accepted:    4K
 * Total Submissions: 17.3K
 * Testcase Example:  '["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]\n[[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]'
 *
 * You are given a tree with n nodes numbered from 0 to n-1 in the form of a
 * parent array where parent[i] is the parent of node i. The root of the tree
 * is node 0.
 * 
 * Implement the function getKthAncestor(int node, int k) to return the k-th
 * ancestor of the given node. If there is no such ancestor, return -1.
 * 
 * The k-th ancestor of a tree node is the k-th node in the path from that node
 * to the root.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * 
 * 
 * Input:
 * ["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
 * [[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]
 * 
 * Output:
 * [null,1,0,-1]
 * 
 * Explanation:
 * TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);
 * 
 * treeAncestor.getKthAncestor(3, 1);  // returns 1 which is the parent of 3
 * treeAncestor.getKthAncestor(5, 2);  // returns 0 which is the grandparent of
 * 5
 * treeAncestor.getKthAncestor(6, 3);  // returns -1 because there is no such
 * ancestor
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= n <= 5*10^4
 * parent[0] == -1 indicating that 0 is the root node.
 * 0 <= parent[i] < n for all 0 < i < n
 * 0 <= node < n
 * There will be at most 5*10^4 queries.
 * 
 */

// @lc code=start
class TreeAncestor {

	int[][] ancestors;
	int n;
	// int MAXN = 5 * 10001;
	int MAXLOG = 16;
    public TreeAncestor(int n, int[] parent) {
    	this.n = n;
        ancestors = new int[n][MAXLOG];
        for(int j = 0; j < n; j++){
        	ancestors[j][0] = parent[j];
        }
        for(int i = 1; i < MAXLOG; i++){
        	for(int j = 0; j < n; j++){
        		int k = ancestors[j][i - 1];
        		if(k >= 0){
        			ancestors[j][i] = ancestors[k][i - 1];	
        		}
        		else{
        			ancestors[j][i] = -1;
        		}
        	}
        }
        // System.out.println(Arrays.deepToString(ancestors));
    }
    
    public int getKthAncestor(int node, int k) {
        return getKthAncestor(node, k, 0);
    }

    private int getKthAncestor(int node, int k, int b) {
    	if(b == 31 || node < 0){
    		return node;
    	}
        if(((k >> b) & 1) != 0){
        	return getKthAncestor(ancestors[node][b], k, b + 1);
        }
        else{
        	return getKthAncestor(node, k, b + 1);
        }
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
// @lc code=end
