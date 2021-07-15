/*
 * @lc app=leetcode id=1932 lang=java
 *
 * [1932] Merge BSTs to Create Single BST
 *
 * https://leetcode.com/problems/merge-bsts-to-create-single-bst/description/
 *
 * algorithms
 * Hard (33.10%)
 * Likes:    68
 * Dislikes: 6
 * Total Accepted:    1.7K
 * Total Submissions: 5.2K
 * Testcase Example:  '[[2,1],[3,2,5],[5,4]]'
 *
 * You are given n BST (binary search tree) root nodes for n separate BSTs
 * stored in an array trees (0-indexed). Each BST in trees has at most 3 nodes,
 * and no two roots have the same value. In one operation, you can:
 * 
 * 
 * Select two distinct indices i and j such that the value stored at one of the
 * leaves of trees[i] is equal to the root value of trees[j].
 * Replace the leaf node in trees[i] with trees[j].
 * Remove trees[j] from trees.
 * 
 * 
 * Return the root of the resulting BST if it is possible to form a valid BST
 * after performing n - 1 operations, or null if it is impossible to create a
 * valid BST.
 * 
 * A BST (binary search tree) is a binary tree where each node satisfies the
 * following property:
 * 
 * 
 * Every node in the node's left subtree has a value strictly less than the
 * node's value.
 * Every node in the node's right subtree has a value strictly greater than the
 * node's value.
 * 
 * 
 * A leaf is a node that has no children.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: trees = [[2,1],[3,2,5],[5,4]]
 * Output: [3,2,5,1,null,4]
 * Explanation:
 * In the first operation, pick i=1 and j=0, and merge trees[0] into trees[1].
 * Delete trees[0], so trees = [[3,2,5,1],[5,4]].
 * 
 * In the second operation, pick i=0 and j=1, and merge trees[1] into trees[0].
 * Delete trees[1], so trees = [[3,2,5,1,null,4]].
 * 
 * The resulting tree, shown above, is a valid BST, so return its root.
 * 
 * Example 2:
 * 
 * 
 * Input: trees = [[5,3,8],[3,2,6]]
 * Output: []
 * Explanation:
 * Pick i=0 and j=1 and merge trees[1] into trees[0].
 * Delete trees[1], so trees = [[5,3,8,2,6]].
 * 
 * The resulting tree is shown above. This is the only valid operation that can
 * be performed, but the resulting tree is not a valid BST, so return null.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: trees = [[5,4],[3]]
 * Output: []
 * Explanation: It is impossible to perform any operations.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: trees = [[2,1,3]]
 * Output: [2,1,3]
 * Explanation: There is only one tree, and it is already a valid BST, so
 * return its root.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == trees.length
 * 1 <= n <= 5 * 10^4
 * The number of nodes in each tree is in the range [1, 3].
 * Each node in the input may have children but no grandchildren.
 * No two roots of trees have the same value.
 * All the trees in the input are valid BSTs.
 * 1 <= TreeNode.val <= 5 * 10^4.
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
    public TreeNode canMerge(List<TreeNode> trees) {
        //Map root value to tree
        HashMap<Integer, TreeNode> map = new HashMap<>();
        for(TreeNode t : trees){
            map.put(t.val, t);
        }
        
        // Merge trees
        for(TreeNode t : trees){
            if(map.containsKey(t.val)){
                merger(t, map);
            }
        }

        
        //After merging we should have only one tree left else return null
        if(map.size() != 1) return null;
        else {
            //Return the one tree left after merging
            for(int c : map.keySet()) {
                //Check if final tree is valid else return null
                if(isValidBST(map.get(c))){
                    return map.get(c);
                } else return null;
            }
        }
        
      return null;
        
        
    }
    
    void merger(TreeNode t, HashMap<Integer, TreeNode> map){
        map.remove(t.val); // Remove current tree to prevent cyclical merging For. 2->3(Right) and 3->2(Left)
        //Merge on left
        if(t.left != null && map.containsKey(t.left.val) ){
            // Before merging child node, merge the grandchild nodes
            merger(map.get(t.left.val), map);
            t.left = map.get(t.left.val);
            map.remove(t.left.val);
        }
        
        // Merge on right
        if(t.right!=null &&  map.containsKey(t.right.val)  ){
            // Before merging child node, merge the grandchild nodes
            merger(map.get(t.right.val), map);
            t.right = map.get(t.right.val);
            map.remove(t.right.val);
        }
        // Add tree back to map once right and left merge is complete
        map.put(t.val, t);
    }
    
    // Validate BST
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public boolean helper(TreeNode root, long min, long max){
        if(root == null) return true;
        if(root.val <= min || root.val >= max) return false;
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
}
// @lc code=end
