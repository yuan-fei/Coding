/*
 * @lc app=leetcode id=2196 lang=java
 *
 * [2196] Create Binary Tree From Descriptions
 *
 * https://leetcode.com/problems/create-binary-tree-from-descriptions/description/
 *
 * algorithms
 * Medium (68.92%)
 * Likes:    205
 * Dislikes: 6
 * Total Accepted:    9.4K
 * Total Submissions: 13.6K
 * Testcase Example:  '[[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]'
 *
 * You are given a 2D integer array descriptions where descriptions[i] =
 * [parenti, childi, isLefti] indicates that parenti is the parent of childi in
 * a binary tree of unique values. Furthermore,
 * 
 * 
 * If isLefti == 1, then childi is the left child of parenti.
 * If isLefti == 0, then childi is the right child of parenti.
 * 
 * 
 * Construct the binary tree described by descriptions and return its root.
 * 
 * The test cases will be generated such that the binary tree is valid.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
 * Output: [50,20,80,15,17,19]
 * Explanation: The root node is the node with value 50 since it has no parent.
 * The resulting binary tree is shown in the diagram.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
 * Output: [1,2,null,null,3,4]
 * Explanation: The root node is the node with value 1 since it has no parent.
 * The resulting binary tree is shown in the diagram.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= descriptions.length <= 10^4
 * descriptions[i].length == 3
 * 1 <= parenti, childi <= 10^5
 * 0 <= isLefti <= 1
 * The binary tree described by descriptions is valid.
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
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> m = new HashMap<>();

        for(int[] d: descriptions){
            if(!m.containsKey(d[0])){
                m.put(d[0], new TreeNode(d[0]));
            }
            if(!m.containsKey(d[1])){
                m.put(d[1], new TreeNode(d[1]));
            }
            if(d[2] == 1){
                m.get(d[0]).left = m.get(d[1]);
            }
            else{
                m.get(d[0]).right = m.get(d[1]);
            }
        }
        for(int[] d: descriptions){
            m.remove(d[1]);
        }
        return new ArrayList<>(m.values()).get(0);
    }
}
// @lc code=end
