/*
 * @lc app=leetcode id=1274 lang=java
 *
 * [1274] Number of Ships in a Rectangle
 *
 * https://leetcode.com/problems/number-of-ships-in-a-rectangle/description/
 *
 * algorithms
 * Hard (62.35%)
 * Likes:    27
 * Dislikes: 1
 * Total Accepted:    938
 * Total Submissions: 1.5K
 * Testcase Example:  '[[1,1],[2,2],[3,3],[5,5]]\n[4,4]\n[0,0]'
 *
 * (This problem is an interactive problem.)
 * 
 * On the sea represented by a cartesian plane, each ship is located at an
 * integer point, and each integer point may contain at most 1 ship.
 * 
 * You have a function Sea.hasShips(topRight, bottomLeft) which takes two
 * points as arguments and returns true if and only if there is at least one
 * ship in the rectangle represented by the two points, including on the
 * boundary.
 * 
 * Given two points, which are the top right and bottom left corners of a
 * rectangle, return the number of ships present in that rectangle.  It is
 * guaranteed that there are at most 10 ships in that rectangle.
 * 
 * Submissions making more than 400 calls to hasShips will be judged Wrong
 * Answer.  Also, any solutions that attempt to circumvent the judge will
 * result in disqualification.
 * 
 * 
 * Example :
 * 
 * 
 * 
 * 
 * Input: 
 * ships = [[1,1],[2,2],[3,3],[5,5]], topRight = [4,4], bottomLeft = [0,0]
 * Output: 3
 * Explanation: From [0,0] to [4,4] we can count 3 ships within the range.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * On the input ships is only given to initialize the map internally. You must
 * solve this problem "blindfolded". In other words, you must find the answer
 * using the given hasShips API, without knowing the ships position.
 * 0 <= bottomLeft[0] <= topRight[0] <= 1000
 * 0 <= bottomLeft[1] <= topRight[1] <= 1000
 * 
 * 
 */

// @lc code=start
/**
 * // This is Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Sea {
 *     public boolean hasShips(int[] topRight, int[] bottomLeft);
 * }
 */

class Solution {
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
    	if(bottomLeft[0] > topRight[0] || bottomLeft[1] > topRight[1]){
    		return 0;
    	}
        boolean hasShips = sea.hasShips(topRight, bottomLeft);
        if(!hasShips){
        	return 0;
        }
        else{
        	if(topRight[0] == bottomLeft[0] && topRight[1] == bottomLeft[1]){
        		return 1;
        	}
        	else{
        		int mid0 = (topRight[0] + bottomLeft[0]) / 2;
        		int mid1 = (topRight[1] + bottomLeft[1]) / 2;
        		int cnt  = 0;
        		cnt += countShips(sea, topRight, new int[]{mid0 + 1, mid1 + 1});
        		cnt += countShips(sea, new int[]{mid0, mid1}, bottomLeft);
        		cnt += countShips(sea, new int[]{topRight[0], mid1}, new int[]{mid0 + 1, bottomLeft[1]});
        		cnt += countShips(sea, new int[]{mid0, topRight[1]}, new int[]{bottomLeft[0], mid1 + 1});
        		return cnt;
        	}
        }
    }

}
// @lc code=end
