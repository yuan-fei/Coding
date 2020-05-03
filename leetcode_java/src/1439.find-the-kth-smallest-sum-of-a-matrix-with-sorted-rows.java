/*
 * @lc app=leetcode id=1439 lang=java
 *
 * [1439] Find the Kth Smallest Sum of a Matrix With Sorted Rows
 *
 * https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/description/
 *
 * algorithms
 * Hard (47.40%)
 * Likes:    92
 * Dislikes: 0
 * Total Accepted:    2.3K
 * Total Submissions: 4.7K
 * Testcase Example:  '[[1,3,11],[2,4,6]]\n5'
 *
 * You are given an m * n matrix, mat, and an integer k, which has its rows
 * sorted in non-decreasing order.
 * 
 * You are allowed to choose exactly 1 element from each row to form an array.
 * Return the Kth smallest array sum among all possible arrays.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: mat = [[1,3,11],[2,4,6]], k = 5
 * Output: 7
 * Explanation: Choosing one element from each row, the first k smallest sum
 * are:
 * [1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.  
 * 
 * Example 2:
 * 
 * 
 * Input: mat = [[1,3,11],[2,4,6]], k = 9
 * Output: 17
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
 * Output: 9
 * Explanation: Choosing one element from each row, the first k smallest sum
 * are:
 * [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th
 * sum is 9.  
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: mat = [[1,1,10],[2,2,9]], k = 7
 * Output: 12
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == mat.length
 * n == mat.length[i]
 * 1 <= m, n <= 40
 * 1 <= k <= min(200, n ^ m)
 * 1 <= mat[i][j] <= 5000
 * mat[i] is a non decreasing array.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int kthSmallest(int[][] mat, int k) {
    	int n = mat.length;
    	int m = mat[0].length;
    	PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> -Integer.compare(a, b));
    	for(int j = 0; j < m; j++){
    		q.offer(mat[0][j]);
    		if(q.size() > k){
    			q.poll();
    		}
    	}
        
        for(int i = 1; i < n; i++){
        	PriorityQueue<Integer> newQ = new PriorityQueue<>((a, b) -> -Integer.compare(a, b));
        	while(!q.isEmpty()){
        		int cur = q.poll();
	        	for(int j = 0; j < m; j++){
	        		newQ.offer(cur + mat[i][j]);
	        		if(newQ.size() > k){
	        			newQ.poll();
	        		}
	        	}	
        	}
        	q = newQ;
        }
        return q.poll();
    }

    static class State{
    	int r;
    	int[] off;
    	int v;
    	State(int row, int[] offset, int value){
    		r = row;
    		off = offset;
    		v = value;
    	}

    	State(State s){
    		off = Arrays.copyOf(s.off, s.off.length);
    		v = s.v;
    	}

    	public String toString(){
    		return "("+Arrays.toString(off) + ", " + r + ", " + v+")";
    	}
    }
}
// @lc code=end
