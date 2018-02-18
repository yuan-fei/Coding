/*
 * [654] Maximum Binary Tree
 *
 * https://leetcode.com/problems/maximum-binary-tree/description/
 *
 * algorithms
 * Medium (69.71%)
 * Total Accepted:    24.8K
 * Total Submissions: 35.6K
 * Testcase Example:  '[3,2,1,6,0,5]'
 *
 * 
 * Given an integer array with no duplicates. A maximum tree building on this
 * array is defined as follow:
 * 
 * The root is the maximum number in the array. 
 * The left subtree is the maximum tree constructed from left part subarray
 * divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray
 * divided by the maximum number. 
 * 
 * 
 * 
 * 
 * Construct the maximum tree by the given array and output the root node of
 * this tree.
 * 
 * 
 * Example 1:
 * 
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 * 
 * ⁠     6
 * ⁠   /   \
 * ⁠  3     5
 * ⁠   \    / 
 * ⁠    2  0   
 * ⁠      \
 * ⁠       1
 * 
 * 
 * 
 * Note:
 * 
 * The size of the given array will be in the range [1,1000].
 * 
 * 
 */
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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
    	if(nums == null){
    		return null;
    	}

        Stack<TreeNode> s = new Stack<TreeNode>();
        // int[] new_nums = new int[nums.length + 1];
        // System.arraycopy(nums, 0, new_nums, 0, nums.length);
        // new_nums[new_nums.length - 1] = Integer.MAX_VALUE;
        // nums = new_nums;
        // s.push(new TreeNode(nums[0]));
        int i = 0;
        while(i < nums.length){
        	if(s.isEmpty()){
        		s.push(new TreeNode(nums[i]));
        		i++;
        	}
        	else if(s.peek().val > nums[i]){
        		s.push(new TreeNode(nums[i]));
        		i++;
        	}
        	else {
        		TreeNode top = s.pop();
        		if(!s.isEmpty() && s.peek().val <= nums[i]){
        			s.peek().right = top;
        		}
        		else{
	        		TreeNode root = new TreeNode(nums[i]);
	        		root.left = top;
	        		s.push(root);
	        		i++;
        		}
        	}
        	
        }

        TreeNode root = null;
        while(!s.isEmpty()){
        	s.peek().right = root;
        	root = s.pop();
        }
        // return s.pop().left;
        return root;
    }
}
