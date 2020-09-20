/*
 * @lc app=leetcode id=1584 lang=java
 *
 * [1584] Min Cost to Connect All Points
 *
 * https://leetcode.com/problems/min-cost-to-connect-all-points/description/
 *
 * algorithms
 * Medium (35.43%)
 * Likes:    46
 * Dislikes: 10
 * Total Accepted:    2.7K
 * Total Submissions: 7.7K
 * Testcase Example:  '[[0,0],[2,2],[3,10],[5,2],[7,0]]'
 *
 * You are given an array points representing integer coordinates of some
 * points on a 2D-plane, where points[i] = [xi, yi].
 * 
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan
 * distance between them: |xi - xj| + |yi - yj|, where |val| denotes the
 * absolute value of val.
 * 
 * Return the minimum cost to make all points connected. All points are
 * connected if there is exactly one simple path between any two points.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * Output: 20
 * Explanation:
 * 
 * We can connect the points as shown above to get the minimum cost of 20.
 * Notice that there is a unique path between every pair of points.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: points = [[3,12],[-2,5],[-4,1]]
 * Output: 18
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: points = [[0,0],[1,1],[1,0],[-1,1]]
 * Output: 4
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: points = [[-1000000,-1000000],[1000000,1000000]]
 * Output: 4000000
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: points = [[0,0]]
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= points.length <= 1000
 * -10^6 <= xi, yi <= 10^6
 * All pairs (xi, yi) are distinct.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[][] d = getDist(points);
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b)->Integer.compare(d[a[0]][a[1]], d[b[0]][b[1]]));
        boolean[] visited = new boolean[n];
        int total = 0;
        int min = Integer.MAX_VALUE;
        int minId = 0;
        for(int i = 1; i < n; i++){
        	q.offer(new int[]{0, i});
        }
        // System.out.println(Arrays.deepToString(d));
        
        visited[0] = true;
        while(!q.isEmpty()){
        	int[] cur = q.poll();
        	if(!visited[cur[1]]){
        		// System.out.println(Arrays.toString(cur));
        		visited[cur[1]] = true;
        		total += d[cur[0]][cur[1]];
        		for(int i = 0; i < n; i++){
        			if(!visited[i]){
        				q.offer(new int[]{cur[1], i});
        			}
        		}
        	}
        }
        return total;
    }

    int[][] getDist(int[][] points){
    	int n = points.length;
    	int[][] dis = new int[n][n];
    	for (int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++){
    			dis[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
    		}
    	}
    	return dis;
    }
}
// @lc code=end
