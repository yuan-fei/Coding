/*
 * @lc app=leetcode id=1776 lang=java
 *
 * [1776] Car Fleet II
 *
 * https://leetcode.com/problems/car-fleet-ii/description/
 *
 * algorithms
 * Hard (43.32%)
 * Likes:    117
 * Dislikes: 3
 * Total Accepted:    2.4K
 * Total Submissions: 5.5K
 * Testcase Example:  '[[1,2],[2,1],[4,3],[7,2]]'
 *
 * There are n cars traveling at different speeds in the same direction along a
 * one-lane road. You are given an array cars of length n, where cars[i] =
 * [positioni, speedi] represents:
 * 
 * 
 * positioni is the distance between the i^th car and the beginning of the road
 * in meters. It is guaranteed that positioni < positioni+1.
 * speedi is the initial speed of the i^th car in meters per second.
 * 
 * 
 * For simplicity, cars can be considered as points moving along the number
 * line. Two cars collide when they occupy the same position. Once a car
 * collides with another car, they unite and form a single car fleet. The cars
 * in the formed fleet will have the same position and the same speed, which is
 * the initial speed of the slowest car in the fleet.
 * 
 * Return an array answer, where answer[i] is the time, in seconds, at which
 * the i^th car collides with the next car, or -1 if the car does not collide
 * with the next car. Answers within 10^-5 of the actual answers are
 * accepted.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: cars = [[1,2],[2,1],[4,3],[7,2]]
 * Output: [1.00000,-1.00000,3.00000,-1.00000]
 * Explanation: After exactly one second, the first car will collide with the
 * second car, and form a car fleet with speed 1 m/s. After exactly 3 seconds,
 * the third car will collide with the fourth car, and form a car fleet with
 * speed 2 m/s.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: cars = [[3,4],[5,4],[6,3],[9,1]]
 * Output: [2.00000,1.00000,1.50000,-1.00000]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= cars.length <= 10^5
 * 1 <= positioni, speedi <= 10^6
 * positioni < positioni+1
 * 
 * 
 */

// @lc code=start
class Solution {
    public double[] getCollisionTimes(int[][] cars) {
    	int n = cars.length;
        double[] ans = new double[n];
        Arrays.fill(ans, -1d);
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) 
        	-> Long.compare(1L * (cars[a[1]][0] - cars[a[0]][0]) * (cars[b[0]][1] - cars[b[1]][1]), 1L * (cars[a[0]][1] - cars[a[1]][1]) * (cars[b[1]][0] - cars[b[0]][0])) != 0
        	? Long.compare(1L * (cars[a[1]][0] - cars[a[0]][0]) * (cars[b[0]][1] - cars[b[1]][1]), 1L * (cars[a[0]][1] - cars[a[1]][1]) * (cars[b[1]][0] - cars[b[0]][0])) 
        	: Integer.compare(-a[1], -b[1])
        );
        for(int i = 0; i < n - 1; i++){
        	if(cars[i + 1][1] < cars[i][1]){
        		q.offer(new int[]{i, i + 1});
        	}
        }
        TreeSet<Integer> ts = new TreeSet<>();
        for(int i = 0; i < n; i++){
        	ts.add(i);
        }
        while(!q.isEmpty()){
        	// System.out.println(ts);
        	int[] cur = q.poll();
        	if(ans[cur[0]] == -1){
        		ts.remove(cur[0]);
        		ans[cur[0]] = 1d * (cars[cur[1]][0] - cars[cur[0]][0]) / (cars[cur[0]][1] - cars[cur[1]][1]);
        		Integer prev = ts.lower(cur[0]);
        		if(prev != null){
        			if(ans[prev] == -1 && cars[prev][1] > cars[cur[1]][1]){
        				// System.out.println("cur" + Arrays.toString(cur));
        				q.offer(new int[]{prev, cur[1]});
        			}
        		}
        	}
        }
        return ans;
    }
}
// @lc code=end
