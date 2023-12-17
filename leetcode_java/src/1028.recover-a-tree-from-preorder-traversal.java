/*
 * @lc app=leetcode id=1028 lang=java
 *
 * [1028] Recover a Tree From Preorder Traversal
 *
 * https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/description/
 *
 * algorithms
 * Hard (73.62%)
 * Likes:    1553
 * Dislikes: 41
 * Total Accepted:    46.3K
 * Total Submissions: 62.9K
 * Testcase Example:  '"1-2--3--4-5--6--7"'
 *
 * We run a preorder depth-first search (DFS) on the root of a binary tree.
 * 
 * At each node in this traversal, we output D dashes (where D is the depth of
 * this node), then we output the value of this node.  If the depth of a node
 * is D, the depth of its immediate child is D + 1.  The depth of the root node
 * is 0.
 * 
 * If a node has only one child, that child is guaranteed to be the left
 * child.
 * 
 * Given the output traversal of this traversal, recover the tree and return
 * its root.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: traversal = "1-2--3--4-5--6--7"
 * Output: [1,2,5,3,4,6,7]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: traversal = "1-2--3---4-5--6---7"
 * Output: [1,2,5,3,null,6,null,4,null,7]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: traversal = "1-401--349---90--88"
 * Output: [1,401,null,349,88,90]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the original tree is in the range [1, 1000].
 * 1 <= Node.val <= 10^9
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
    int curIdx = 0;
    public TreeNode recoverFromPreorder(String traversal) {
        Deque<TreeNode> s = new ArrayDeque<>();
        while(true){
            Token token = getNextToken(traversal);
            if(token == null){
                break;
            }
            while(s.size() > token.depth){
                s.pop();
            }
            TreeNode node = new TreeNode(token.value);
            if(!s.isEmpty()){
                TreeNode parent = s.peek();
                if(parent.left == null){
                    parent.left = node;
                }
                else{
                    parent.right = node;
                }
            }
            s.push(node);
        }
        while(s.size() > 1){
            s.pop();
        }
        return s.pop();
    }

    Token getNextToken(String traversal){
        if(curIdx >= traversal.length()){
            return null;
        }
        int depth = 0;
        int value = 0;
        while(curIdx < traversal.length() && traversal.charAt(curIdx) == '-'){
            depth++;
            curIdx++;
        }
        while(curIdx < traversal.length() && traversal.charAt(curIdx) != '-'){
            value = value * 10 + traversal.charAt(curIdx) - '0';
            curIdx++;
        }
        return new Token(depth, value);
    }

    record Token(int depth, int value){}
}
// @lc code=end
