/*
 * @lc app=leetcode id=365 lang=java
 *
 * [365] Water and Jug Problem
 *
 * https://leetcode.com/problems/water-and-jug-problem/description/
 *
 * algorithms
 * Medium (29.89%)
 * Likes:    220
 * Dislikes: 628
 * Total Accepted:    34.2K
 * Total Submissions: 114.4K
 * Testcase Example:  '3\n5\n4'
 *
 * You are given two jugs with capacities x and y litres. There is an infinite
 * amount of water supply available. You need to determine whether it is
 * possible to measure exactly z litres using these two jugs.
 * 
 * If z liters of water is measurable, you must have z liters of water
 * contained within one or both buckets by the end.
 * 
 * Operations allowed:
 * 
 * 
 * Fill any of the jugs completely with water.
 * Empty any of the jugs.
 * Pour water from one jug into another till the other jug is completely full
 * or the first jug itself is empty.
 * 
 * 
 * Example 1: (From the famous "Die Hard" example)
 * 
 * 
 * Input: x = 3, y = 5, z = 4
 * Output: True
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: x = 2, y = 6, z = 5
 * Output: False
 * 
 */

// @lc code=start
class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
    	if(x == 0){
    		return z == 0 || y == z;
    	}
    	if(y == 0){
    		return canMeasureWater(y, x, z);
    	}
        int gcd = gcd(x, y);
        if(z % gcd == 0 && z <= x + y){
        	return true;
        }
        return false;
    }

    int gcd(int a, int b){
    	if(b == 0){
    		return a;
    	}
    	else{
    		return gcd(b, a % b);
    	}
    }
}
// @lc code=end
