/*
 * @lc app=leetcode id=257 lang=java
 *
 * [257] Binary Tree Paths
 *
 * https://leetcode.com/problems/binary-tree-paths/description/
 *
 * algorithms
 * Easy (48.34%)
 * Likes:    1191
 * Dislikes: 83
 * Total Accepted:    267.3K
 * Total Submissions: 552.3K
 * Testcase Example:  '[1,2,3,null,5]'
 *
 * Given a binary tree, return all root-to-leaf paths.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example:
 * 
 * 
 * Input:
 * 
 * ⁠  1
 * ⁠/   \
 * 2     3
 * ⁠\
 * ⁠ 5
 * 
 * Output: ["1->2->5", "1->3"]
 * 
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
    	if(root == null){
    		return new ArrayList<>();
    	}
        List<String> ans = new ArrayList<>();
        dfs(root, new ArrayList<>(), ans);
        return ans;
    }

    void dfs(TreeNode root, List<String> cur, List<String> ans){
    	cur.add("" + root.val);
    	if(root.left == null && root.right == null){
    		ans.add(String.join("->", cur));
    	}
    	if(root.left != null){
    		dfs(root.left, cur, ans);
    	}
    	if(root.right != null){
    		dfs(root.right, cur, ans);
    	}
    	cur.remove(cur.size() - 1);
    }
}
// @lc code=end
