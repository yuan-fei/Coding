/*
 * @lc app=leetcode id=1739 lang=java
 *
 * [1739] Building Boxes
 *
 * https://leetcode.com/problems/building-boxes/description/
 *
 * algorithms
 * Hard (48.02%)
 * Likes:    86
 * Dislikes: 9
 * Total Accepted:    2.2K
 * Total Submissions: 4.5K
 * Testcase Example:  '3'
 *
 * You have a cubic storeroom where the width, length, and height of the room
 * are all equal to n units. You are asked to place n boxes in this room where
 * each box is a cube of unit side length. There are however some rules to
 * placing the boxes:
 * 
 * 
 * You can place the boxes anywhere on the floor.
 * If box x is placed on top of the box y, then each side of the four vertical
 * sides of the box y must either be adjacent to another box or to a wall.
 * 
 * 
 * Given an integer n, return the minimum possible number of boxes touching the
 * floor.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: n = 3
 * Output: 3
 * Explanation: The figure above is for the placement of the three boxes.
 * These boxes are placed in the corner of the room, where the corner is on the
 * left side.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: n = 4
 * Output: 3
 * Explanation: The figure above is for the placement of the four boxes.
 * These boxes are placed in the corner of the room, where the corner is on the
 * left side.
 * 
 * 
 * Example 3:
 * 
 * 
 * 
 * 
 * Input: n = 10
 * Output: 6
 * Explanation: The figure above is for the placement of the ten boxes.
 * These boxes are placed in the corner of the room, where the corner is on the
 * back side.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumBoxes(int n) {
    	long lb = 0;
    	long ub = n;
    	while(lb + 1 < ub){
    		long mid = (lb + ub) / 2;
	    	if(maxTotalFromFloor(mid) >= n){
	    		ub = mid;	
	    	}
	    	else{
	    		lb = mid;
	    	}
    	}
    	if(maxTotalFromFloor(lb) >= n){
    		return (int)lb;
    	}
    	return (int)ub;   
    }


    long maxTotalFromFloor(long floor){
    	long total = floor;
    	long maxTop = maxBoxesOnTop(floor);
    	while(maxTop > 0){
    		total += maxTop;
    		maxTop = maxBoxesOnTop(maxTop);
    	}
    	return total;
    }

    long maxBoxesOnTop(long m){
    	// (1+n) * n / 2 = m => n^2 + n -2m = 0
    	long layer = (long)((-1 + Math.sqrt(1 + 8 * m)) / 2);
    	long missing = (2 + layer) * (layer + 1) / 2 - m;
    	long maxTop = (1 + layer) * layer / 2 - Math.min(missing, layer);
    	// System.out.println(m + "=" + maxTop);
    	return Math.max(maxTop, 0);
    }
}
// @lc code=end
