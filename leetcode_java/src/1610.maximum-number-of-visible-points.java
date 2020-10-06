/*
 * @lc app=leetcode id=1610 lang=java
 *
 * [1610] Maximum Number of Visible Points
 *
 * https://leetcode.com/problems/maximum-number-of-visible-points/description/
 *
 * algorithms
 * Hard (20.86%)
 * Likes:    35
 * Dislikes: 113
 * Total Accepted:    1.9K
 * Total Submissions: 9.1K
 * Testcase Example:  '[[2,1],[2,2],[3,3]]\n90\n[1,1]'
 *
 * You are given an array points, an integer angle, and your location, where
 * location = [posx, posy] and points[i] = [xi, yi] both denote integral
 * coordinates on the X-Y plane.
 * 
 * Initially, you are facing directly east from your position. You cannot move
 * from your position, but you can rotate. In other words, posx and posy cannot
 * be changed. Your field of view in degrees is represented by angle,
 * determining how wide you can see from any given view direction. Let d be the
 * amount in degrees that you rotate counterclockwise. Then, your field of view
 * is the inclusive range of angles [d - angle/2, d + angle/2].
 * 
 * 
 * Your browser does not support the video tag or this video format.
 * 
 * 
 * You can see some set of points if, for each point, the angle formed by the
 * point, your position, and the immediate east direction from your position is
 * in your field of view.
 * 
 * There can be multiple points at one coordinate. There may be points at your
 * location, and you can always see these points regardless of your rotation.
 * Points do not obstruct your vision to other points.
 * 
 * Return the maximum number of points you can see.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
 * Output: 3
 * Explanation: The shaded region represents your field of view. All points can
 * be made visible in your field of view, including [3,3] even though [2,2] is
 * in front and in the same line of sight.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
 * Output: 4
 * Explanation: All points can be made visible in your field of view, including
 * the one at your location.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: points = [[1,0],[2,1]], angle = 13, location = [1,1]
 * Output: 1
 * Explanation: You can only see one of the two points, as shown above.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= points.length <= 10^5
 * points[i].length == 2
 * location.length == 2
 * 0 <= angle < 360
 * 0 <= posx, posy, xi, yi <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int cnt = 0;
        List<List<Integer>> nPoints = normalize(points, location);
        cnt += points.size() - nPoints.size();
        List<Double> angles = convertToAngle(nPoints);
        Collections.sort(angles);
        int l = angles.size();
        for(int i = 0; i < l; i++){
        	angles.add(angles.get(i) + 2 * Math.PI);
        }
        // System.out.println(angles);
        double window = Math.PI * angle / 180;
        int max = 0;
        int left = 0;
        for(int i = 0; i < angles.size(); i++){
        	while(angles.get(i) - angles.get(left) > window){
        		left++;
        	}
        	max = Math.max(i - left + 1, max);
        }
        
        // System.out.println(max + " + ");
        cnt += max;
        return cnt;
    }

    List<List<Integer>> normalize(List<List<Integer>> points, List<Integer> location){
    	List<List<Integer>> ret = new ArrayList<>();
    	for(List<Integer> p : points){
    		if(p.get(0) - location.get(0) != 0 || p.get(1) - location.get(1) != 0){
				ret.add(Arrays.asList(p.get(0) - location.get(0), p.get(1) - location.get(1)));
    		}
    	}
    	return ret;
    }

    List<Double> convertToAngle(List<List<Integer>> points){
    	List<Double> ret = new ArrayList<>();
    	for(List<Integer> p : points){
    		ret.add(Math.atan2(p.get(1), p.get(0)));
    	}
    	return ret;
    }
}
// @lc code=end
