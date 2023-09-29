/*
 * @lc app=leetcode id=919 lang=java
 *
 * [919] Complete Binary Tree Inserter
 *
 * https://leetcode.com/problems/complete-binary-tree-inserter/description/
 *
 * algorithms
 * Medium (64.60%)
 * Likes:    1032
 * Dislikes: 109
 * Total Accepted:    47.6K
 * Total Submissions: 73.6K
 * Testcase Example:  '["CBTInserter","insert","insert","get_root"]\n[[[1,2]],[3],[4],[]]'
 *
 * A complete binary tree is a binary tree in which every level, except
 * possibly the last, is completely filled, and all nodes are as far left as
 * possible.
 * 
 * Design an algorithm to insert a new node to a complete binary tree keeping
 * it complete after the insertion.
 * 
 * Implement the CBTInserter class:
 * 
 * 
 * CBTInserter(TreeNode root) Initializes the data structure with the root of
 * the complete binary tree.
 * int insert(int v) Inserts a TreeNode into the tree with value Node.val ==
 * val so that the tree remains complete, and returns the value of the parent
 * of the inserted TreeNode.
 * TreeNode get_root() Returns the root node of the tree.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["CBTInserter", "insert", "insert", "get_root"]
 * [[[1, 2]], [3], [4], []]
 * Output
 * [null, 1, 2, [1, 2, 3, 4]]
 * 
 * Explanation
 * CBTInserter cBTInserter = new CBTInserter([1, 2]);
 * cBTInserter.insert(3);  // return 1
 * cBTInserter.insert(4);  // return 2
 * cBTInserter.get_root(); // return [1, 2, 3, 4]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * The number of nodes in the tree will be in the range [1, 1000].
 * 0 <= Node.val <= 5000
 * root is a complete binary tree.
 * 0 <= val <= 5000
 * At most 10^4 calls will be made to insert and get_root.
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
class CBTInserter {

    TreeNode r;
    List<TreeNode> lastLayer = new ArrayList<>();
    int cur = 0;

    public CBTInserter(TreeNode root) {
        r = root;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()){
            if(q.size() < 2 * lastLayer.size()){
                cur = q.size() / 2;
                break;
            }
            lastLayer = new ArrayList();
            for(int x = q.size(); x > 0; x--){
                TreeNode curNode = q.poll();
                if(curNode.left != null){
                    q.offer(curNode.left);
                }
                if(curNode.right != null){
                    q.offer(curNode.right);
                }
                lastLayer.add(curNode);
            }
        }
    }
    
    public int insert(int val) {
        TreeNode curParent = lastLayer.get(cur);
        int ans = curParent.val;;
        if(curParent.left == null){
            curParent.left = new TreeNode(val);
        }
        else{
            curParent.right = new TreeNode(val);
            cur++;
            if(cur == lastLayer.size()){
                List<TreeNode> nextLayer = new ArrayList<>();
                for(TreeNode t : lastLayer){
                    nextLayer.add(t.left);
                    nextLayer.add(t.right);
                }
                lastLayer = nextLayer;
                cur = 0;
            }
        }
        return ans;
    }
    
    public TreeNode get_root() {
        return r;
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 */
// @lc code=end
