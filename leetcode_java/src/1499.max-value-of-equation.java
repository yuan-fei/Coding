/*
 * @lc app=leetcode id=1499 lang=java
 *
 * [1499] Max Value of Equation
 *
 * https://leetcode.com/problems/max-value-of-equation/description/
 *
 * algorithms
 * Hard (44.15%)
 * Likes:    89
 * Dislikes: 3
 * Total Accepted:    2.7K
 * Total Submissions: 6.1K
 * Testcase Example:  '[[1,3],[2,0],[5,10],[6,-10]]\n1'
 *
 * Given an array points containing the coordinates of points on a 2D plane,
 * sorted by the x-values, where points[i] = [xi, yi] such that xi < xj for all
 * 1 <= i < j <= points.length. You are also given an integer k.
 * 
 * Find the maximum value of the equation yi + yj + |xi - xj| where |xi - xj|
 * <= k and 1 <= i < j <= points.length. It is guaranteed that there exists at
 * least one pair of points that satisfy the constraint |xi - xj| <= k.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
 * Output: 4
 * Explanation: The first two points satisfy the condition |xi - xj| <= 1 and
 * if we calculate the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth
 * points also satisfy the condition and give a value of 10 + -10 + |5 - 6| =
 * 1.
 * No other pairs satisfy the condition, so we return the max of 4 and 1.
 * 
 * Example 2:
 * 
 * 
 * Input: points = [[0,0],[3,0],[9,2]], k = 3
 * Output: 3
 * Explanation: Only the first two points have an absolute difference of 3 or
 * less in the x-values, and give the value of 0 + 0 + |0 - 3| = 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= points.length <= 10^5
 * points[i].length == 2
 * -10^8 <= points[i][0], points[i][1] <= 10^8
 * 0 <= k <= 2 * 10^8
 * points[i][0] < points[j][0] for all 1 <= i < j <= points.length
 * xi form a strictly increasing sequence.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
    	int n = points.length;
    	Deque<Integer> q = new ArrayDeque<>();
    	int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
        	while(!q.isEmpty() && points[i][0] - points[q.peekFirst()][0] > k){
        		q.pollFirst();
        	}
        	if(!q.isEmpty()){
        		int j = q.peekFirst();
        		max = Math.max(max, points[i][0] - points[j][0] + points[j][1] + points[i][1]);
        	}
        	while(!q.isEmpty() && points[q.peekLast()][1] - points[q.peekLast()][0] <= points[i][1] - points[i][0]){
        		q.pollLast();
        	}
        	q.offerLast(i);
        }
        return max;
    }
}
// @lc code=end
