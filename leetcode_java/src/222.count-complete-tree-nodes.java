/*
 * @lc app=leetcode id=222 lang=java
 *
 * [222] Count Complete Tree Nodes
 *
 * https://leetcode.com/problems/count-complete-tree-nodes/description/
 *
 * algorithms
 * Medium (39.56%)
 * Likes:    1462
 * Dislikes: 163
 * Total Accepted:    167.8K
 * Total Submissions: 423.5K
 * Testcase Example:  '[1,2,3,4,5,6]'
 *
 * Given a complete binary tree, count the number of nodes.
 * 
 * Note: 
 * 
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is
 * completely filled, and all nodes in the last level are as far left as
 * possible. It can have between 1 and 2^h nodes inclusive at the last level
 * h.
 * 
 * Example:
 * 
 * 
 * Input: 
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   3
 * ⁠/ \  /
 * 4  5 6
 * 
 * Output: 6
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
    public int countNodes(TreeNode root) {
    	if(root == null){
    		return 0;
    	}
        int h = getHeight(root);
        int low = 0;
        int high = (1 << h) - 1;
        while(low + 1 < high){
        	int mid = low + (high - low) / 2;
        	if(check(root, mid, h)){
        		low = mid;
        	}
        	else{
        		high = mid;
        	}
        }
        int nOfLeaves = 0;
        if(check(root, high, h)){
        	nOfLeaves = high + 1;
        }
        else if(check(root, low, h)){
        	nOfLeaves = low + 1;
        }
        // System.out.println(nOfLeaves);
        return (1 << h) - 1 + nOfLeaves;
    }

    private boolean check(TreeNode root, int n, int b){
    	if(root == null){
    		return false;
    	}
    	if(b == 0){
    		// System.out.println(n+", "+b+", "+root);
    		return true;
    	}
    	else{
    		int d = (n >> (b - 1)) & 1;
    		if(d == 0){
    			return check(root.left, n, b - 1);
    		}
    		else{
    			return check(root.right, n, b - 1);	
    		}
    	}
    }

    private int getHeight(TreeNode root){
    	int h = 0;
    	while(root.left != null){
    		root = root.left;
    		h++;
    	}
    	return h;
    }
}
// @lc code=end
