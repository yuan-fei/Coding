/*
 * @lc app=leetcode id=2385 lang=java
 *
 * [2385] Amount of Time for Binary Tree to Be Infected
 *
 * https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/description/
 *
 * algorithms
 * Medium (55.49%)
 * Likes:    323
 * Dislikes: 2
 * Total Accepted:    10.6K
 * Total Submissions: 19K
 * Testcase Example:  '[1,5,3,null,4,10,6,9,2]\n3'
 *
 * You are given the root of a binary tree with unique values, and an integer
 * start. At minute 0, an infection starts from the node with value start.
 * 
 * Each minute, a node becomes infected if:
 * 
 * 
 * The node is currently uninfected.
 * The node is adjacent to an infected node.
 * 
 * 
 * Return the number of minutes needed for the entire tree to be infected.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,5,3,null,4,10,6,9,2], start = 3
 * Output: 4
 * Explanation: The following nodes are infected during:
 * - Minute 0: Node 3
 * - Minute 1: Nodes 1, 10 and 6
 * - Minute 2: Node 5
 * - Minute 3: Node 4
 * - Minute 4: Nodes 9 and 2
 * It takes 4 minutes for the whole tree to be infected so we return 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1], start = 1
 * Output: 0
 * Explanation: At minute 0, the only node in the tree is infected so we return
 * 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 10^5].
 * 1 <= Node.val <= 10^5
 * Each node has a unique value.
 * A node with a value of start exists in the tree.
 * 
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<Integer>[] adj;
    public int amountOfTime(TreeNode root, int start) {
        adj = new List[100001];
        for(int i = 0; i < adj.length; i++){
            adj[i] = new ArrayList<>();
        }
        dfs1(root, null);
        return dfs2(start, -1) - 1;
    }

    void dfs1(TreeNode r, TreeNode p){
        if(r != null){
            if(p != null){
                adj[r.val].add(p.val);
                adj[p.val].add(r.val);
            }
            dfs1(r.left, r);
            dfs1(r.right, r);
        }
    }

    int dfs2(int u, int p){
        int rt = 0;
        for(int v : adj[u]){
            if(v != p){
                rt = Math.max(dfs2(v, u), rt);    
            }
        }
        return rt + 1;
    }
}
// @lc code=end
