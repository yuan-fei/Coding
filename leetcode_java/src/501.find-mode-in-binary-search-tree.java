/*
 * @lc app=leetcode id=501 lang=java
 *
 * [501] Find Mode in Binary Search Tree
 *
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/description/
 *
 * algorithms
 * Easy (42.37%)
 * Likes:    942
 * Dislikes: 348
 * Total Accepted:    88.3K
 * Total Submissions: 208.3K
 * Testcase Example:  '[1,null,2,2]'
 *
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the
 * most frequently occurred element) in the given BST.
 * 
 * Assume a BST is defined as follows:
 * 
 * 
 * The left subtree of a node contains only nodes with keys less than or equal
 * to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or
 * equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 
 * 
 * 
 * 
 * For example:
 * Given BST [1,null,2,2],
 * 
 * 
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  2
 * 
 * 
 * 
 * 
 * return [2].
 * 
 * Note: If a tree has more than one mode, you can return them in any order.
 * 
 * Follow up: Could you do that without using any extra space? (Assume that the
 * implicit stack space incurred due to recursion does not count).
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
	List<Integer> modes = new ArrayList<>();
	TreeNode lastNode = null;
	int maxCnt = 0;
	int curCnt = 0;
    public int[] findMode(TreeNode root) {
        dfs(root);
        if(lastNode != null && curCnt > maxCnt){
			maxCnt = curCnt;
			modes = new ArrayList<>();
			modes.add(lastNode.val);
		}
		else if(lastNode != null && curCnt == maxCnt){
			modes.add(lastNode.val);
		}
        return modes.stream()
                      .mapToInt(Integer::intValue)
                      .toArray();
    }

    void dfs(TreeNode root){
    	if(root != null){
    		dfs(root.left);
    		if(lastNode == null){
    			curCnt = 1;	
    		}
    		else if(root.val == lastNode.val){
    			curCnt++;
    		}
    		else{
    			if(curCnt > maxCnt){
    				maxCnt = curCnt;
    				modes = new ArrayList<>();
    				modes.add(lastNode.val);
    			}
    			else if(curCnt == maxCnt){
    				modes.add(lastNode.val);
    			}
    			curCnt = 1;
    		}
    		lastNode = root;
    		dfs(root.right);
    	}
    }
}
// @lc code=end
