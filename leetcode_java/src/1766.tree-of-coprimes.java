/*
 * @lc app=leetcode id=1766 lang=java
 *
 * [1766] Tree of Coprimes
 *
 * https://leetcode.com/problems/tree-of-coprimes/description/
 *
 * algorithms
 * Hard (33.96%)
 * Likes:    76
 * Dislikes: 7
 * Total Accepted:    1.7K
 * Total Submissions: 5.1K
 * Testcase Example:  '[2,3,3,2]\n[[0,1],[1,2],[1,3]]'
 *
 * There is a tree (i.e., a connected, undirected graph that has no cycles)
 * consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges. Each
 * node has a value associated with it, and the root of the tree is node 0.
 * 
 * To represent this tree, you are given an integer array nums and a 2D array
 * edges. Each nums[i] represents the i^th node's value, and each edges[j] =
 * [uj, vj] represents an edge between nodes uj and vj in the tree.
 * 
 * Two values x and y are coprime if gcd(x, y) == 1 where gcd(x, y) is the
 * greatest common divisor of x and y.
 * 
 * An ancestor of a node i is any other node on the shortest path from node i
 * to the root. A node is not considered an ancestor of itself.
 * 
 * Return an array ans of size n, where ans[i] is the closest ancestor to node
 * i such that nums[i] and nums[ans[i]] are coprime, or -1 if there is no such
 * ancestor.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: nums = [2,3,3,2], edges = [[0,1],[1,2],[1,3]]
 * Output: [-1,0,0,1]
 * Explanation: In the above figure, each node's value is in parentheses.
 * - Node 0 has no coprime ancestors.
 * - Node 1 has only one ancestor, node 0. Their values are coprime (gcd(2,3)
 * == 1).
 * - Node 2 has two ancestors, nodes 1 and 0. Node 1's value is not coprime
 * (gcd(3,3) == 3), but node 0's
 * ⁠ value is (gcd(2,3) == 1), so node 0 is the closest valid ancestor.
 * - Node 3 has two ancestors, nodes 1 and 0. It is coprime with node 1
 * (gcd(3,2) == 1), so node 1 is its
 * ⁠ closest valid ancestor.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: nums = [5,6,10,2,3,6,15], edges =
 * [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
 * Output: [-1,0,-1,0,0,0,-1]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * nums.length == n
 * 1 <= nums[i] <= 50
 * 1 <= n <= 10^5
 * edges.length == n - 1
 * edges[j].length == 2
 * 0 <= uj, vj < n
 * uj != vj
 * 
 * 
 */

// @lc code=start
class Solution {
	List<Integer>[] coPrimeList;
	List<Integer>[] adj;
	List<Integer>[] parent;
	List<Integer>[] parentLevel;
	int[] nums;
	int[] ans;
    public int[] getCoprimes(int[] nums, int[][] edges) {
    	int n = nums.length;
    	this.nums = nums;
    	coPrimeList = buildCoPrimeList();
    	adj = buildAdj(n, edges);
    	ans = new int[n];
    	Arrays.fill(ans, -1);
    	parent = new List[51];
    	parentLevel = new List[51];
    	for(int i = 1; i <= 50; i++){
    		parent[i] = new ArrayList<Integer>();
    		parentLevel[i] = new ArrayList<Integer>();
    	}
    	traverse(0, -1, 0);
    	// for(int i = 1; i < ans.length; i++){
    	// 	if(ans[i] == 63){
    	// 		System.out.println(i + ": " + nums[i]);	
    	// 	}
    	// }
    	// System.out.println(nums[34] + ": " + nums[63]);
    	return ans;
    }

    List<Integer>[] buildCoPrimeList(){
    	List<Integer>[] coPrimeList = new List[51];
    	for(int i = 1; i <= 50; i++){
    		coPrimeList[i] = new ArrayList<Integer>();
    	}
    	coPrimeList[1].add(1);
    	for(int i = 1; i <= 50; i++){
    		for(int j = i + 1; j <= 50; j++){
    			if(gcd(i, j) == 1){
    				coPrimeList[i].add(j);
    				coPrimeList[j].add(i);
    			}
    		}
    	}
    	return coPrimeList;
    }

    void traverse(int l, int p, int u){
    	parent[nums[u]].add(u);
    	parentLevel[nums[u]].add(l);
    	for(int v : adj[u]){
    		if(v != p){
    			traverse(l + 1, u, v);
    		}
    	}
    	parent[nums[u]].remove(parent[nums[u]].size() - 1);
    	parentLevel[nums[u]].remove(parentLevel[nums[u]].size() - 1);
    	int max = -1;
    	int maxLevel = -1;

    	for(int x : coPrimeList[nums[u]]){
    		if(!parentLevel[x].isEmpty() && maxLevel < parentLevel[x].get(parentLevel[x].size() - 1)){
				maxLevel = parentLevel[x].get(parentLevel[x].size() - 1);
				max = parent[x].get(parent[x].size() - 1);
    		}
    	}
    	ans[u] = max;
    }

    int gcd(int x, int y){
    	if(y == 0){
    		return x;
    	}
    	else{
    		return gcd(y, x % y);
    	}
    }

    List<Integer>[] buildAdj(int n, int[][] edges){
    	List<Integer>[] adj = new List[n];
    	for(int i = 0; i < n; i++){
    		adj[i] = new ArrayList<>();
    	}
    	for(int[] e : edges){
    		adj[e[0]].add(e[1]);
    		adj[e[1]].add(e[0]);
    	}
    	return adj;
    }
}
// @lc code=end
