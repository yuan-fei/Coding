/*
 * @lc app=leetcode id=2471 lang=java
 *
 * [2471] Minimum Number of Operations to Sort a Binary Tree by Level
 *
 * https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/description/
 *
 * algorithms
 * Medium (62.44%)
 * Likes:    165
 * Dislikes: 2
 * Total Accepted:    9.8K
 * Total Submissions: 15.8K
 * Testcase Example:  '[1,4,3,7,6,8,5,null,null,null,null,9,null,10]'
 *
 * You are given the root of a binary tree with unique values.
 * 
 * In one operation, you can choose any two nodes at the same level and swap
 * their values.
 * 
 * Return the minimum number of operations needed to make the values at each
 * level sorted in a strictly increasing order.
 * 
 * The level of a node is the number of edges along the path between it and the
 * root node.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,4,3,7,6,8,5,null,null,null,null,9,null,10]
 * Output: 3
 * Explanation:
 * - Swap 4 and 3. The 2^nd level becomes [3,4].
 * - Swap 7 and 5. The 3^rd level becomes [5,6,8,7].
 * - Swap 8 and 7. The 3^rd level becomes [5,6,7,8].
 * We used 3 operations so return 3.
 * It can be proven that 3 is the minimum number of operations needed.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,3,2,7,6,5,4]
 * Output: 3
 * Explanation:
 * - Swap 3 and 2. The 2^nd level becomes [2,3].
 * - Swap 7 and 4. The 3^rd level becomes [4,6,5,7].
 * - Swap 6 and 5. The 3^rd level becomes [4,5,6,7].
 * We used 3 operations so return 3.
 * It can be proven that 3 is the minimum number of operations needed.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = [1,2,3,4,5,6]
 * Output: 0
 * Explanation: Each level is already sorted in increasing order so return
 * 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 10^5].
 * 1 <= Node.val <= 10^5
 * All the values of the tree are unique.
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
    public int minimumOperations(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int ans = 0;
        while(!q.isEmpty()){
            List<TreeNode> l = new ArrayList<>();
            TreeMap<Integer, Integer> tm = new TreeMap<>();
            int n = q.size();
            for(int x = 0; x < n; x++){
                TreeNode t = q.poll();
                l.add(t);
                tm.put(t.val, x);
                if(t.left != null){
                    q.offer(t.left);
                }
                if(t.right != null){
                    q.offer(t.right);
                }
            }
            // System.out.print("origin: ");
            // l.stream().forEach(e -> System.out.print(e.val + ", "));
            // System.out.println("");
            for(int i = 0; i < n; i++){
                int min = tm.firstKey();
                int minId = tm.get(min);
                TreeNode cur = l.get(i);
                if(cur.val != min){
                    Collections.swap(l, i, minId);
                    tm.put(cur.val, minId);
                    ans++;
                    // l.stream().forEach(e -> System.out.print(e.val + ", "));
                    // System.out.println("");
                }
                tm.remove(min);
                
            }
        }
        return ans;
    }
}
// @lc code=end
