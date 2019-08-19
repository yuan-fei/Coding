/*
 * @lc app=leetcode id=1161 lang=java
 *
 * [1161] Maximum Level Sum of a Binary Tree
 *
 * https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/description/
 *
 * algorithms
 * Medium (72.80%)
 * Total Accepted:    4K
 * Total Submissions: 5.5K
 * Testcase Example:  '[1,7,0,7,-8,null,null]'
 *
 * Given the root of a binary tree, the level of its root is 1, the level of
 * its children is 2, and so on.
 * 
 * Return the smallest level X such that the sum of all the values of nodes at
 * level X is maximal.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation: 
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The number of nodes in the given tree is between 1 and 10^4.
 * -10^5 <= node.val <= 10^5
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
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int max=Integer.MIN_VALUE;
        int maxLevel = 1;
        int level = 1;
        while(!q.isEmpty()){
            int n = q.size();
            int sum = 0;
            for(int i = 0; i < n; i++){
                TreeNode tn = q.poll();
                sum+=tn.val;
                if(tn.left!=null){
                    q.offer(tn.left);    
                }
                if(tn.right!=null){
                    q.offer(tn.right);    
                }
            }
            if(sum>max){
                max = sum;
                maxLevel = level;
            }
            level++;
        }
        return maxLevel;
    }
}
