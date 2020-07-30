/*
 * @lc app=leetcode id=1530 lang=java
 *
 * [1530] Number of Good Leaf Nodes Pairs
 *
 * https://leetcode.com/problems/number-of-good-leaf-nodes-pairs/description/
 *
 * algorithms
 * Medium (42.69%)
 * Likes:    72
 * Dislikes: 0
 * Total Accepted:    3.5K
 * Total Submissions: 8.3K
 * Testcase Example:  '[1,2,3,null,4]\n3'
 *
 * Given the root of a binary tree and an integer distance. A pair of two
 * different leaf nodes of a binary tree is said to be good if the length of
 * the shortest path between them is less than or equal to distance.
 * 
 * Return the number of good leaf node pairs in the tree.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,2,3,null,4], distance = 3
 * Output: 1
 * Explanation: The leaf nodes of the tree are 3 and 4 and the length of the
 * shortest path between them is 3. This is the only good pair.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [1,2,3,4,5,6,7], distance = 3
 * Output: 2
 * Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The
 * pair [4,6] is not good because the length of ther shortest path between them
 * is 4.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
 * Output: 1
 * Explanation: The only good pair is [2,5].
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: root = [100], distance = 1
 * Output: 0
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: root = [1,1,1], distance = 2
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree is in the range [1, 2^10].
 * Each node's value is between [1, 100].
 * 1 <= distance <= 10
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
    public int countPairs(TreeNode root, int distance) {
        dfs(root, distance);
        return ans;
    }
    int ans = 0;
    int[] dfs(TreeNode root, int distance){
    	int[] ret = new int[11];
    	if(root == null){
    		return ret;
    	}
        if(root.left == null && root.right == null){
            ret[1] = 1;
            return ret;
        }
        int[] left = dfs(root.left, distance);
        int[] right = dfs(root.right, distance);
        // System.out.println(root.val + Arrays.toString(left));
        // System.out.println(root.val + Arrays.toString(right));
        for(int i = 1; i <= 10; i++){
            for(int j = 1; j <= 10; j++){
                if(i + j <= distance){
                    ans += left[i] * right[j];
                }
            }
        }
        
        for(int i = 0; i < 10; i++){
            ret[i + 1] += left[i];
            ret[i + 1] += right[i];
        }
        
        return ret;
    }
}
// @lc code=end
