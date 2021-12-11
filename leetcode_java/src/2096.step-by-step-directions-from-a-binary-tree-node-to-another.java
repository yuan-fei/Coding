/*
 * @lc app=leetcode id=2096 lang=java
 *
 * [2096] Step-By-Step Directions From a Binary Tree Node to Another
 *
 * https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/description/
 *
 * algorithms
 * Medium (38.81%)
 * Likes:    124
 * Dislikes: 18
 * Total Accepted:    3.9K
 * Total Submissions: 10.1K
 * Testcase Example:  '[5,1,2,3,null,6,4]\n3\n6'
 *
 * You are given the root of a binary tree with n nodes. Each node is uniquely
 * assigned a value from 1 to n. You are also given an integer startValue
 * representing the value of the start node s, and a different integer
 * destValue representing the value of the destination node t.
 * 
 * Find the shortest path starting from node s and ending at node t. Generate
 * step-by-step directions of such path as a string consisting of only the
 * uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific
 * direction:
 * 
 * 
 * 'L' means to go from a node to its left child node.
 * 'R' means to go from a node to its right child node.
 * 'U' means to go from a node to its parent node.
 * 
 * 
 * Return the step-by-step directions of the shortest path from node s to node
 * t.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
 * Output: "UURL"
 * Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [2,1], startValue = 2, destValue = 1
 * Output: "L"
 * Explanation: The shortest path is: 2 → 1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is n.
 * 2 <= n <= 10^5
 * 1 <= Node.val <= n
 * All the values in the tree are unique.
 * 1 <= startValue, destValue <= n
 * startValue != destValue
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
    public String getDirections(TreeNode root, int startValue, int endValue) {
        List<StringBuilder> ret = dfs(root, startValue, endValue);
        StringBuilder start;
        StringBuilder end;
        if(ret.get(0).charAt(0) == 'S'){
            start = ret.get(0);
            end = ret.get(1);
        }
        else{
            start = ret.get(1);
            end = ret.get(0);
        }
        end.reverse();
        return repeat("U", start.length() - 1) + end.substring(0, end.length() - 1);
    }

    String repeat(String c, int cnt){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < cnt; i++){
            sb.append(c);
        }
        return sb.toString();
    }

    List<StringBuilder> dfs(TreeNode root, int startValue, int endValue){
        if(root == null){
            return new ArrayList<>();
        }
        else {
            List<StringBuilder> lRet = dfs(root.left, startValue, endValue);
            List<StringBuilder> rRet = dfs(root.right, startValue, endValue);
            if(lRet.size() == 2){
                return lRet;
            }
            else if(rRet.size() == 2){
                return rRet;
            }
            else{
                List<StringBuilder> l = new ArrayList<>();
                if(!lRet.isEmpty()){
                    l.add(lRet.get(0).append("L"));
                }
                if(!rRet.isEmpty()){
                    l.add(rRet.get(0).append("R"));
                }
                if(root.val == startValue){
                    l.add(new StringBuilder("S"));
                }
                else if(root.val == endValue){
                    l.add(new StringBuilder("E"));
                }
                return l;
            }
        }
    }
}
// @lc code=end
