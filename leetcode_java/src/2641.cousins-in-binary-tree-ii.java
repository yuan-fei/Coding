/*
 * @lc app=leetcode id=2641 lang=java
 *
 * [2641] Cousins in Binary Tree II
 *
 * https://leetcode.com/problems/cousins-in-binary-tree-ii/description/
 *
 * algorithms
 * Medium (62.29%)
 * Likes:    117
 * Dislikes: 2
 * Total Accepted:    6K
 * Total Submissions: 9.7K
 * Testcase Example:  '[5,4,9,1,10,null,7]'
 *
 * Given the root of a binary tree, replace the value of each node in the tree
 * with the sum of all its cousins' values.
 * 
 * Two nodes of a binary tree are cousins if they have the same depth with
 * different parents.
 * 
 * Return the root of the modified tree.
 * 
 * Note that the depth of a node is the number of edges in the path from the
 * root node to it.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [5,4,9,1,10,null,7]
 * Output: [0,0,0,7,7,null,11]
 * Explanation: The diagram above shows the initial binary tree and the binary
 * tree after changing the value of each node.
 * - Node with value 5 does not have any cousins so its sum is 0.
 * - Node with value 4 does not have any cousins so its sum is 0.
 * - Node with value 9 does not have any cousins so its sum is 0.
 * - Node with value 1 has a cousin with value 7 so its sum is 7.
 * - Node with value 10 has a cousin with value 7 so its sum is 7.
 * - Node with value 7 has cousins with values 1 and 10 so its sum is 11.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [3,1,2]
 * Output: [0,0,0]
 * Explanation: The diagram above shows the initial binary tree and the binary
 * tree after changing the value of each node.
 * - Node with value 3 does not have any cousins so its sum is 0.
 * - Node with value 1 does not have any cousins so its sum is 0.
 * - Node with value 2 does not have any cousins so its sum is 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 10^5].
 * 1 <= Node.val <= 10^4
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
    
    public TreeNode replaceValueInTree(TreeNode root) {
        List<TreeNode> l1 = new ArrayList<>();
        TreeNode dummy = new TreeNode(0, root, null);
        l1.add(dummy);
        while(!l1.isEmpty()){
            List<TreeNode> l2 = new ArrayList<>();
            int sum = 0;
            for(int i = 0; i < l1.size(); i++){
                TreeNode cur = l1.get(i);
                if(cur.left != null){
                    l2.add(cur.left);
                    sum += cur.left.val;
                }
                if(cur.right != null){
                    l2.add(cur.right);
                    sum += cur.right.val;
                }
            }
            for(int i = 0; i < l1.size(); i++){
                TreeNode cur = l1.get(i);
                int localSum = 0;
                if(cur.left != null){
                    localSum += cur.left.val;
                }
                if(cur.right != null){
                    localSum += cur.right.val;
                }
                if(cur.left != null){
                    cur.left.val = sum - localSum;
                }
                if(cur.right != null){
                    cur.right.val = sum - localSum;
                }     
            }
            l1 = l2;
        }    
        return root;
    }

    
}
// @lc code=end
