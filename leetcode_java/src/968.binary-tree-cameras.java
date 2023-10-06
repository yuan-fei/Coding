/*
 * @lc app=leetcode id=968 lang=java
 *
 * [968] Binary Tree Cameras
 *
 * https://leetcode.com/problems/binary-tree-cameras/description/
 *
 * algorithms
 * Hard (46.50%)
 * Likes:    5103
 * Dislikes: 65
 * Total Accepted:    127.7K
 * Total Submissions: 274.5K
 * Testcase Example:  '[0,0,null,0,0]'
 *
 * You are given the root of a binary tree. We install cameras on the tree
 * nodes where each camera at a node can monitor its parent, itself, and its
 * immediate children.
 * 
 * Return the minimum number of cameras needed to monitor all nodes of the
 * tree.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as shown.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the
 * tree. The above image shows one of the valid configurations of camera
 * placement.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 1000].
 * Node.val == 0
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
    int MAX = 5000;
    public int minCameraCover(TreeNode root) {
        return dfs(root, true, false);
    }
    Map<TreeNode, Integer> nodeToId = new HashMap<>();
    Map<List<Integer>, Integer> cache = new HashMap<>();
    int gid = 0;
    int dfs(TreeNode root, boolean parentCovered, boolean covered){
        if(root == null){
            if(!parentCovered){
                return MAX;
            }
            else{
                return 0;    
            }
        }
        if(!nodeToId.containsKey(root)){
            nodeToId.put(root, gid++);
        }
        int id = nodeToId.get(root);
        List<Integer> cacheKey = Arrays.asList(id, parentCovered? 1 : 0, covered? 1 : 0);
        if(!cache.containsKey(cacheKey)){
            int ret = MAX;
            if(!parentCovered){
                ret = Math.min(ret, 1 + dfs(root.left, true, true) + dfs(root.right, true, true));
            }
            else {
                ret = Math.min(ret, 1 + dfs(root.left, true, true) + dfs(root.right, true, true));
                if(covered){
                    ret = Math.min(ret, dfs(root.left, true, false) + dfs(root.right, true, false));
                }
                else{
                    ret = Math.min(ret, dfs(root.left, false, false) + dfs(root.right, true, false));
                    ret = Math.min(ret, dfs(root.left, true, false) + dfs(root.right, false, false));
                }
                
            }
            // System.out.println(id + ", " + parentCovered + ", " + covered + " = " + ret);
            cache.put(cacheKey, ret);
        }
        return cache.get(cacheKey);
    }
}
// @lc code=end
