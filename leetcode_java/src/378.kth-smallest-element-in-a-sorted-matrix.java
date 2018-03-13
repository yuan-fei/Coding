/*
 * [378] Kth Smallest Element in a Sorted Matrix
 *
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
 *
 * algorithms
 * Medium (45.38%)
 * Total Accepted:    56.3K
 * Total Submissions: 123.8K
 * Testcase Example:  '[[1,5,9],[10,11,13],[12,13,15]]\n8'
 *
 * Given a n x n matrix where each of the rows and columns are sorted in
 * ascending order, find the kth smallest element in the matrix.
 * 
 * 
 * Note that it is the kth smallest element in the sorted order, not the kth
 * distinct element.
 * 
 * 
 * Example:
 * 
 * matrix = [
 * ⁠  [ 1,  5,  9],
 * ⁠  [10, 11, 13],
 * ⁠  [12, 13, 15]
 * ],
 * k = 8,
 * 
 * return 13.
 * 
 * 
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ n2.
 */
class Solution {
	class Node{
		int val;
		int row;
		int col;
		public Node(int val, int row, int col){
			this.val = val;
			this.row = row;
			this.col = col;
		}
	}

	class NodeComparator implements Comparator<Node>{
		@Override
		public int compare(Node n1, Node n2){
			int r = n1.val - n2.val;
			if(r == 0){
				r = n1.row - n2.row;
				if(r == 0){
					r = n1.col - n2.col;
				}
			}
			return r;
		}
	}
    public int kthSmallest(int[][] matrix, int k) {
        TreeSet<Node> set = new TreeSet<Node>(new NodeComparator());
        set.add(new Node(matrix[0][0], 0 , 0));
        while(!set.isEmpty()){
        	Node n = set.pollFirst();
        	k--;
        	if(k == 0){
        		return n.val;
        	}
        	else{
        		if(n.row < matrix.length - 1){
        			set.add(new Node(matrix[n.row + 1][n.col], n.row + 1, n.col));	
        		}
        		if(n.col < matrix.length - 1){
        			set.add(new Node(matrix[n.row][n.col + 1], n.row, n.col + 1));
        		}
        	}
        }
        return -1;
    }
}
