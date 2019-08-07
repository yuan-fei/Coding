/*
 * @lc app=leetcode id=1130 lang=java
 *
 * [1130] Minimum Cost Tree From Leaf Values
 *
 * https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/description/
 *
 * algorithms
 * Medium (59.12%)
 * Total Accepted:    3.7K
 * Total Submissions: 6.2K
 * Testcase Example:  '[6,2,4]'
 *
 * Given an array arr of positive integers, consider all binary trees such
 * that:
 * 
 * 
 * Each node has either 0 or 2 children;
 * The values of arr correspond to the values of each leaf in an in-order
 * traversal of the tree.  (Recall that a node is a leaf if and only if it has
 * 0 children.)
 * The value of each non-leaf node is equal to the product of the largest leaf
 * value in its left and right subtree respectively.
 * 
 * 
 * Among all possible binary trees considered, return the smallest possible sum
 * of the values of each non-leaf node.  It is guaranteed this sum fits into a
 * 32-bit integer.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [6,2,4]
 * Output: 32
 * Explanation:
 * There are two possible trees.  The first has non-leaf node sum 36, and the
 * second has non-leaf node sum 32.
 * 
 * ⁠   24            24
 * ⁠  /  \          /  \
 * ⁠ 12   4        6    8
 * ⁠/  \               / \
 * 6    2             2   4
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= arr.length <= 40
 * 1 <= arr[i] <= 15
 * It is guaranteed that the answer fits into a 32-bit signed integer (ie. it
 * is less than 2^31).
 * 
 */
class Solution {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] minSum = new int[n][n];
        int[][] max = new int[n][n];
        for(int i = 0; i < n; i++){
            max[i][i] = arr[i];
        }
        for(int l = 2; l <= n; l++){
            for(int i = 0; i<=n-l;i++){
                max[i][i+l-1] = Math.max(max[i][i+l-2], arr[i+l-1]);
                minSum[i][i+l-1] = Integer.MAX_VALUE;
                for(int j = i; j <i+l-1; j++){
                    minSum[i][i+l-1] = Math.min(minSum[i][i+l-1], minSum[i][j]+minSum[j+1][i+l-1]+max[i][j]*max[j+1][i+l-1]);
                }
            }
        }
        // System.out.println(Arrays.deepToString(minSum));
        // System.out.println(Arrays.deepToString(max));
        return minSum[0][n-1];
    }
}
