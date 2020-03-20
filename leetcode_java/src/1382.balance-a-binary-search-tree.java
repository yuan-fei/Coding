/*
 * @lc app=leetcode id=1382 lang=java
 *
 * [1382] Balance a Binary Search Tree
 *
 * https://leetcode.com/problems/balance-a-binary-search-tree/description/
 *
 * algorithms
 * Medium (74.65%)
 * Likes:    76
 * Dislikes: 13
 * Total Accepted:    6.5K
 * Total Submissions: 8.7K
 * Testcase Example:  '[1,null,2,null,3,null,4,null,null]'
 *
 * Given a binary search tree, return a balanced binary search tree with the
 * same node values.
 * 
 * A binary search tree is balanced if and only if the depth of the two
 * subtrees of every node never differ by more than 1.
 * 
 * If there is more than one answer, return any of them.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: root = [1,null,2,null,3,null,4,null,null]
 * Output: [2,1,3,null,null,null,4]
 * Explanation: This is not the only correct answer, [3,1,4,null,2,null,null]
 * is also correct.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is between 1 and 10^4.
 * The tree nodes will have distinct values between 1 and 10^5.
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
    public TreeNode balanceBST(TreeNode root) {
    	List<Integer> l = new ArrayList<>();
        dfs(root, l);
        // System.out.println(l);
        return buildTree(l, 0, l.size() - 1);
    }

    void dfs(TreeNode root, List<Integer> l){
    	if(root != null){
    		dfs(root.left, l);
    		l.add(root.val);
    		dfs(root.right, l);
    	}
    }

    TreeNode buildTree(List<Integer> l, int start, int end){
    	if(start <= end){
    		if(start == end){
    			return new TreeNode(l.get(start));
    		}
    		else{
    			int mid = (start + end) / 2;
    			TreeNode r = new TreeNode(l.get(mid));
    			r.left = buildTree(l, start, mid - 1);
    			r.right = buildTree(l, mid + 1, end);
    			return r;
    		}
    	}
    	return null;
    }
}
// @lc code=end
