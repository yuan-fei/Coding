/*
 * @lc app=leetcode id=95 lang=java
 *
 * [95] Unique Binary Search Trees II
 *
 * https://leetcode.com/problems/unique-binary-search-trees-ii/description/
 *
 * algorithms
 * Medium (36.64%)
 * Total Accepted:    148.7K
 * Total Submissions: 405.9K
 * Testcase Example:  '3'
 *
 * Given an integer n, generate all structurally unique BST's (binary search
 * trees) that store values 1 ... n.
 * 
 * Example:
 * 
 * 
 * Input: 3
 * Output:
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 * 
 * ⁠  1         3     3      2      1
 * ⁠   \       /     /      / \      \
 * ⁠    3     2     1      1   3      2
 * ⁠   /     /       \                 \
 * ⁠  2     1         2                 3
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
    public List<TreeNode> generateTrees(int n) {
    	if(n == 0){
    		return new ArrayList();
    	}
        return generateTrees(1, n);
    }

    List<TreeNode> generateTrees(int start, int end) {

    	List<TreeNode> ans = new ArrayList<>();
    	if(start == end){
    		ans.add(new TreeNode(start));
    	}
    	else if(start > end){
    		ans.add(null);
    	}
    	else{
	    	for(int i = start; i <= end; i++){
	        	List<TreeNode> left = generateTrees(start, i-1);
	        	List<TreeNode> right = generateTrees(i+1, end);
	        	for(TreeNode lt: left){
	        		for(TreeNode rt: right){
	        			TreeNode r = new TreeNode(i);
	        			r.left = lt;
	        			r.right = rt;
	        			ans.add(r);
	        		}
	        	}
	        }	
    	}
        
        return ans;
    }
}
