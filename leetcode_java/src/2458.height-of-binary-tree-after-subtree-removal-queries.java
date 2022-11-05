/*
 * @lc app=leetcode id=2458 lang=java
 *
 * [2458] Height of Binary Tree After Subtree Removal Queries
 *
 * https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/description/
 *
 * algorithms
 * Hard (32.68%)
 * Likes:    225
 * Dislikes: 6
 * Total Accepted:    4.3K
 * Total Submissions: 13.1K
 * Testcase Example:  '[1,3,4,2,null,6,5,null,null,null,null,null,7]\n[4]'
 *
 * You are given the root of a binary tree with n nodes. Each node is assigned
 * a unique value from 1 to n. You are also given an array queries of size m.
 * 
 * You have to perform m independent queries on the tree where in the i^th
 * query you do the following:
 * 
 * 
 * Remove the subtree rooted at the node with the value queries[i] from the
 * tree. It is guaranteed that queries[i] will not be equal to the value of the
 * root.
 * 
 * 
 * Return an array answer of size m where answer[i] is the height of the tree
 * after performing the i^th query.
 * 
 * Note:
 * 
 * 
 * The queries are independent, so the tree returns to its initial state after
 * each query.
 * The height of a tree is the number of edges in the longest simple path from
 * the root to some node in the tree.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
 * Output: [2]
 * Explanation: The diagram above shows the tree after removing the subtree
 * rooted at node with value 4.
 * The height of the tree is 2 (The path 1 -> 3 -> 2).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
 * Output: [3,2,3,2]
 * Explanation: We have the following queries:
 * - Removing the subtree rooted at node with value 3. The height of the tree
 * becomes 3 (The path 5 -> 8 -> 2 -> 4).
 * - Removing the subtree rooted at node with value 2. The height of the tree
 * becomes 2 (The path 5 -> 8 -> 1).
 * - Removing the subtree rooted at node with value 4. The height of the tree
 * becomes 3 (The path 5 -> 8 -> 2 -> 6).
 * - Removing the subtree rooted at node with value 8. The height of the tree
 * becomes 2 (The path 5 -> 9 -> 3).
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
 * m == queries.length
 * 1 <= m <= min(n, 10^4)
 * 1 <= queries[i] <= n
 * queries[i] != root.val
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
    Map<Integer, Integer> m; 
    int level = 0;
    public int[] treeQueries(TreeNode root, int[] queries) {
        m = new HashMap<>();
        rec(root);
        Queue<TreeNode> q = new LinkedList<>();
        Map<Integer, Integer> m1 = new HashMap<>();
        q.offer(root);
        while(!q.isEmpty()){
            if(q.size() == 1){
                m.put(q.peek().val, level);
            }
            List<Integer> layer = new ArrayList<>();
            int max1 = -1;
            int max1Id = -1;
            int max2 = -1;
            for(int x = q.size(); x > 0; x--){
                TreeNode n = q.poll();
                layer.add(n.val);
                int l = m.get(n.val);
                if(l > max1){
                    max2 = Math.max(max1, max2);
                    max1 = l;
                    max1Id = n.val;
                }
                else{
                    max2 = Math.max(max2, l);
                }
                if(n.left != null){
                    q.offer(n.left);
                }
                if(n.right != null){
                    q.offer(n.right);
                }
            }
            for(int v : layer){
                if(v == max1Id){
                    m1.put(v, level + max2);
                }
                else{
                    m1.put(v, level + max1);
                }
            }
            // System.out.println(m1);
            level++;
        }
        level--;
        // System.out.println(m + ", " + m1);
        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            ans[i] = m1.get(queries[i]);
        }
        return ans;
    }

    void rec(TreeNode r){
        int l = -1;
        if(r.left != null){
            rec(r.left);
            l = Math.max(l, m.get(r.left.val));
        }
        if(r.right != null){
            rec(r.right);
            l = Math.max(l, m.get(r.right.val));
        }
        m.put(r.val, l + 1);
    }
}
// @lc code=end
