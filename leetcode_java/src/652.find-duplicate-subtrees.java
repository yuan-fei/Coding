/*
 * @lc app=leetcode id=652 lang=java
 *
 * [652] Find Duplicate Subtrees
 *
 * https://leetcode.com/problems/find-duplicate-subtrees/description/
 *
 * algorithms
 * Medium (51.63%)
 * Likes:    1683
 * Dislikes: 225
 * Total Accepted:    78.6K
 * Total Submissions: 151.6K
 * Testcase Example:  '[1,2,3,4,null,2,4,null,null,4]'
 *
 * Given the root of a binary tree, return all duplicate subtrees.
 * 
 * For each kind of duplicate subtrees, you only need to return the root node
 * of any one of them.
 * 
 * Two trees are duplicate if they have the same structure with the same node
 * values.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,2,3,4,null,2,4,null,null,4]
 * Output: [[2,4],[4]]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [2,1,1]
 * Output: [[1]]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = [2,2,2,3,null,3,null]
 * Output: [[2,3],[3]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of the nodes in the tree will be in the range [1, 10^4]
 * -200 <= Node.val <= 200
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
	Map<String, List<TreeNode>> m = new HashMap<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        List<TreeNode> ans = new ArrayList<>();
        for(String k : m.keySet()){
        	if(m.get(k).size() > 1){
        		ans.add(m.get(k).get(0));
        	}
        }
        return ans;
    }

    String dfs(TreeNode root){
    	if(root == null){
    		return "N";
    	}
    	else{
    		String left = dfs(root.left);
    		String right = dfs(root.right);
    		String rep = "" + root.val + "[" + left +"][" + right +"]";
    		List<TreeNode> l = m.getOrDefault(rep, new ArrayList<>());
    		l.add(root);
    		m.put(rep, l);
    		return rep;
    	}
    }
}
// @lc code=end
