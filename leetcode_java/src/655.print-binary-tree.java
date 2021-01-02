/*
 * @lc app=leetcode id=655 lang=java
 *
 * [655] Print Binary Tree
 *
 * https://leetcode.com/problems/print-binary-tree/description/
 *
 * algorithms
 * Medium (55.63%)
 * Likes:    393
 * Dislikes: 917
 * Total Accepted:    36.2K
 * Total Submissions: 65K
 * Testcase Example:  '[1,2]'
 *
 * Print a binary tree in an m*n 2D string array following these rules: 
 * 
 * 
 * The row number m should be equal to the height of the given binary tree.
 * The column number n should always be an odd number.
 * The root node's value (in string format) should be put in the exactly middle
 * of the first row it can be put. The column and the row where the root node
 * belongs will separate the rest space into two parts (left-bottom part and
 * right-bottom part). You should print the left subtree in the left-bottom
 * part and print the right subtree in the right-bottom part. The left-bottom
 * part and the right-bottom part should have the same size. Even if one
 * subtree is none while the other is not, you don't need to print anything for
 * the none subtree but still need to leave the space as large as that for the
 * other subtree. However, if two subtrees are none, then you don't need to
 * leave space for both of them. 
 * Each unused space should contain an empty string "".
 * Print the subtrees following the same rules.
 * 
 * 
 * Example 1:
 * 
 * Input:
 * ⁠    1
 * ⁠   /
 * ⁠  2
 * Output:
 * [["", "1", ""],
 * ⁠["2", "", ""]]
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * ⁠    1
 * ⁠   / \
 * ⁠  2   3
 * ⁠   \
 * ⁠    4
 * Output:
 * [["", "", "", "1", "", "", ""],
 * ⁠["", "2", "", "", "", "3", ""],
 * ⁠["", "", "4", "", "", "", ""]]
 * 
 * 
 * 
 * Example 3:
 * 
 * Input:
 * ⁠     1
 * ⁠    / \
 * ⁠   2   5
 * ⁠  / 
 * ⁠ 3 
 * ⁠/ 
 * 4 
 * Output:
 * 
 * [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 * ⁠["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 * ⁠["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 * ⁠["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 * 
 * 
 * 
 * Note:
 * The height of binary tree is in the range of [1, 10].
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
    public List<List<String>> printTree(TreeNode root) {
        int h = getHeight(root);
        int w = (1 << h) - 1;
		List<List<String>> result = new ArrayList<>();        
        for(int i = 0; i < h; i++){
        	result.add(new ArrayList<>());
        	for(int j = 0; j < w; j++){
        		result.get(i).add("");
        	}
        }
        dfs(root, 0, 0, w, result);
        return result;
    }

    int getHeight(TreeNode root){
    	if(root == null){
    		return 0;
    	}
    	return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    void dfs(TreeNode root, int level, int left, int right, List<List<String>> result){
    	if(root != null){
    		result.get(level).set((left + right) / 2, "" + root.val);
    		dfs(root.left, level + 1, left, (left + right) / 2, result);
    		dfs(root.right, level + 1, (left + right) / 2 + 1, right, result);
    	}
    }
}
// @lc code=end
