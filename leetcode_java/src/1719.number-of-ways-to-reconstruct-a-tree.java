/*
 * @lc app=leetcode id=1719 lang=java
 *
 * [1719] Number Of Ways To Reconstruct A Tree
 *
 * https://leetcode.com/problems/number-of-ways-to-reconstruct-a-tree/description/
 *
 * algorithms
 * Hard (38.60%)
 * Likes:    38
 * Dislikes: 26
 * Total Accepted:    926
 * Total Submissions: 2.4K
 * Testcase Example:  '[[1,2],[2,3]]'
 *
 * You are given an array pairs, where pairs[i] = [xi, yi], and:
 * 
 * 
 * There are no duplicates.
 * xi < yi
 * 
 * 
 * Let ways be the number of rooted trees that satisfy the following
 * conditions:
 * 
 * 
 * The tree consists of nodes whose values appeared in pairs.
 * A pair [xi, yi] exists in pairs if and only if xi is an ancestor of yi or yi
 * is an ancestor of xi.
 * Note: the tree does not have to be a binary tree.
 * 
 * 
 * Two ways are considered to be different if there is at least one node that
 * has different parents in both ways.
 * 
 * Return:
 * 
 * 
 * 0 if ways == 0
 * 1 if ways == 1
 * 2 if ways > 1
 * 
 * 
 * A rooted tree is a tree that has a single root node, and all edges are
 * oriented to be outgoing from the root.
 * 
 * An ancestor of a node is any node on the path from the root to that node
 * (excluding the node itself). The root has no ancestors.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: pairs = [[1,2],[2,3]]
 * Output: 1
 * Explanation: There is exactly one valid rooted tree, which is shown in the
 * above figure.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: pairs = [[1,2],[2,3],[1,3]]
 * Output: 2
 * Explanation: There are multiple valid rooted trees. Three of them are shown
 * in the above figures.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: pairs = [[1,2],[2,3],[2,4],[1,5]]
 * Output: 0
 * Explanation: There are no valid rooted trees.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= pairs.length <= 10^5
 * 1 <= xi < yi <= 500
 * The elements in pairs are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
	Set<Integer>[] adj;
	boolean[] visited;
    public int checkWays(int[][] pairs) {
        //[1,2][2,3][1,4]
        getAdj(pairs);
        visited = new boolean[501];
        int vCnt = 0;
        for(int i = 0; i < adj.length; i++){
        	if(adj[i].size() > 0){
        		vCnt++;
        	}
        }
        // System.out.println(Arrays.toString(adj));
        int root = 0;
        for(int i = 0; i < adj.length; i++){
        	if(adj[i].size() == vCnt){
        		root = i;
        		break;
        	}
        }
        if(root == 0){
        	return 0;
        }
        else{
        	return rec(root);
        }
    }

    void getAdj(int[][] pairs){
    	adj = new Set[501];
    	for(int i = 0; i < adj.length; i++){
    		adj[i] = new HashSet<>();
    	}
    	for(int[] pair: pairs){
    		adj[pair[0]].add(pair[1]);
    		adj[pair[1]].add(pair[0]);
    		adj[pair[0]].add(pair[0]);
    		adj[pair[1]].add(pair[1]);
    	}
    	// System.out.println(Arrays.toString(adj));
    	// for(int i = 0; i < adj.length; i++){
    	// 	TreeSet<Integer> ts = new TreeSet<>((a, b) -> Integer.compare(adj[b].size(), adj[a].size()));
    	// 	ts.addAll(adj[i]);
    	// 	adj[i] = ts;
    	// }
    }

    int rec(int u){
    	// System.out.println(u);
    	visited[u] = true;
    	int ans = 1;
    	List<Integer> to = new ArrayList<>(adj[u]);
    	Collections.sort(to, (a, b) -> Integer.compare(adj[b].size(), adj[a].size()));
    	for(int v : to){
    		if(!visited[v]){
    			// System.out.println(adj[u] + " + " + adj[v]);
				if(adj[u].containsAll(adj[v])){
					int ret = rec(v);
					if(ret == 0){
						return 0;
					}
					else{
						if(adj[u].size() == adj[v].size()){
							ans = 2;
						}
						ans = Math.max(ret, ans);
					}
				} 
				else{
					return 0;
				}   			
    		}
    	}
    	return ans;
    }
}
// @lc code=end
