/*
 * @lc app=leetcode id=979 lang=java
 *
 * [979] Distribute Coins in Binary Tree
 *
 * https://leetcode.com/problems/distribute-coins-in-binary-tree/description/
 *
 * algorithms
 * Medium (72.34%)
 * Likes:    4781
 * Dislikes: 160
 * Total Accepted:    106.7K
 * Total Submissions: 147.4K
 * Testcase Example:  '[3,0,0]'
 *
 * You are given the root of a binary tree with n nodes where each node in the
 * tree has node.val coins. There are n coins in total throughout the whole
 * tree.
 * 
 * In one move, we may choose two adjacent nodes and move one coin from one
 * node to another. A move may be from parent to child, or from child to
 * parent.
 * 
 * Return the minimum number of moves required to make every node have exactly
 * one coin.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [3,0,0]
 * Output: 2
 * Explanation: From the root of the tree, we move one coin to its left child,
 * and one coin to its right child.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [0,3,0]
 * Output: 3
 * Explanation: From the left child of the root, we move two coins to the root
 * [taking two moves]. Then, we move one coin from the root of the tree to the
 * right child.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is n.
 * 1 <= n <= 100
 * 0 <= Node.val <= n
 * The sum of all Node.val is n.
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
    int ans = 0;
    public int distributeCoins(TreeNode root) {
        dfs1(root);
        // System.out.println(needByNodes);
        // System.out.println(ans);
        dfs2(root);
        // System.out.println(needByNodes);
        // System.out.println(ans);
        dfs3(root, 0);
        // System.out.println(needByNodes);
        // System.out.println(ans);
        return ans;
    }
    Map<TreeNode, Integer> needByNodes = new HashMap<>();
    int dfs1(TreeNode root){
        if(root == null){
            return 0;
        }
        int need = 1;
        need += dfs1(root.left);
        need += dfs1(root.right);
        needByNodes.put(root, need - root.val);
        return needByNodes.get(root);
    }

    int dfs2(TreeNode root){
        if(root == null){
            return 0;
        }
        int givenLeft = dfs2(root.left);
        int givenRight = dfs2(root.right);
        root.val += givenLeft;
        root.val += givenRight;
        ans += givenLeft;
        ans += givenRight;
        int need = needByNodes.get(root);
        if(need < 0){
            root.val += need;
            needByNodes.put(root, 0);
            return -need;
        }
        return 0;
    }

    void dfs3(TreeNode root, int given){
        root.val += given;
        if(root.left != null){
            int needLeft = needByNodes.get(root.left);
            root.val -= needLeft;
            ans += needLeft;
            dfs3(root.left, -needLeft);
        }
        if(root.right != null){
            int needRight = needByNodes.get(root.right);
            root.val -= needRight;
            ans += needRight;
            dfs3(root.right, -needRight);
        }
    }
}
// @lc code=end
