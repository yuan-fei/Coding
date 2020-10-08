/*
 * @lc app=leetcode id=593 lang=java
 *
 * [593] Valid Square
 *
 * https://leetcode.com/problems/valid-square/description/
 *
 * algorithms
 * Medium (43.24%)
 * Likes:    277
 * Dislikes: 441
 * Total Accepted:    37.3K
 * Total Submissions: 86.3K
 * Testcase Example:  '[0,0]\n[1,1]\n[1,0]\n[0,1]'
 *
 * Given the coordinates of four points in 2D space, return whether the four
 * points could construct a square.
 * 
 * The coordinate (x,y) of a point is represented by an integer array with two
 * integers.
 * 
 * Example:
 * 
 * 
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * Output: True
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * All the input integers are in the range [-10000, 10000].
 * A valid square has four equal sides with positive length and four equal
 * angles (90-degree angles).
 * Input points have no order.
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] points = {p1, p2, p3, p4};
        Map<Integer, Integer> m = new HashMap<>();
        for(int i = 0; i < 4; i++){
        	for(int j = i + 1; j < 4; j++){
        		int d2 = (points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
        		int cnt = m.getOrDefault(d2, 0) + 1;
        		m.put(d2, cnt);
        	}
        }
        if(m.size() != 2){
        	return false;
        }
        int k1 = -1;
        for(int k : m.keySet()){
        	if(k1 == -1){
        		k1 = k;
        	}
        	else{
        		return k == k1 * 2 || k1 == k * 2;
        	}
        }
        return false;
    }
}
// @lc code=end
