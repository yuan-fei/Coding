/*
 * @lc app=leetcode id=1232 lang=java
 *
 * [1232] Check If It Is a Straight Line
 *
 * https://leetcode.com/problems/check-if-it-is-a-straight-line/description/
 *
 * algorithms
 * Easy (47.48%)
 * Likes:    29
 * Dislikes: 2
 * Total Accepted:    5.7K
 * Total Submissions: 11.9K
 * Testcase Example:  '[[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]'
 *
 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y]
 * represents the coordinate of a point. Check if these points make a straight
 * line in the XY plane.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates contains no duplicate point.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
    	int n = coordinates.length;
    	if(n == 2){
    		return true;
    	}
        for(int i = 0; i < n-2; i++){
        	if(!checkThreePointsInLine(coordinates[i], coordinates[i + 1], coordinates[i + 2])){
        		return false;
        	}
        }
        return true;
    }

    boolean checkThreePointsInLine(int[] p1, int[] p2, int[] p3){
    	return (p2[1] - p1[1]) * (p3[0] - p2[0]) == (p2[0] - p1[0]) * (p3[1] - p2[1]);
    }
}
// @lc code=end
