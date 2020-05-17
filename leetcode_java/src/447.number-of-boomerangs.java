/*
 * @lc app=leetcode id=447 lang=java
 *
 * [447] Number of Boomerangs
 *
 * https://leetcode.com/problems/number-of-boomerangs/description/
 *
 * algorithms
 * Easy (51.45%)
 * Likes:    375
 * Dislikes: 625
 * Total Accepted:    65.6K
 * Total Submissions: 127.5K
 * Testcase Example:  '[[0,0],[1,0],[2,0]]'
 *
 * Given n points in the plane that are all pairwise distinct, a "boomerang" is
 * a tuple of points (i, j, k) such that the distance between i and j equals
 * the distance between i and k (the order of the tuple matters).
 * 
 * Find the number of boomerangs. You may assume that n will be at most 500 and
 * coordinates of points are all in the range [-10000, 10000] (inclusive).
 * 
 * Example:
 * 
 * 
 * Input:
 * [[0,0],[1,0],[2,0]]
 * 
 * Output:
 * 2
 * 
 * Explanation:
 * The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        Map<Integer, List<int[]>> groups = new HashMap<>();
        int n = points.length;
        for(int i = 0; i < n; i++){
        	for(int j = i + 1; j < n; j++){
        		int dist = (points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
        		List<int[]> l = groups.getOrDefault(dist, new ArrayList<>());
        		l.add(new int[]{i, j});
        		groups.put(dist, l);
        	}
        }
        int cnt = 0;
        // System.out.println(groups);
        for (List<int[]> l : groups.values()) {
        	for(int i = 0; i < l.size(); i++){
        		for(int j = i + 1; j < l.size(); j++){
        			if(l.get(i)[0] == l.get(j)[0] || l.get(i)[1] == l.get(j)[1] || l.get(i)[1] == l.get(j)[0] || l.get(i)[0] == l.get(j)[1]){
        				cnt += 2;
        			}
        		}
        	}
        }
        return cnt;
    }
}
// @lc code=end
