/*
 * @lc app=leetcode id=497 lang=java
 *
 * [497] Random Point in Non-overlapping Rectangles
 *
 * https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/description/
 *
 * algorithms
 * Medium (37.79%)
 * Likes:    198
 * Dislikes: 202
 * Total Accepted:    13K
 * Total Submissions: 34.4K
 * Testcase Example:  '["Solution", "pick", "pick", "pick"]\n[[[[1, 1, 5, 5]]], [], [], []]'
 *
 * Given a list of non-overlapping axis-aligned rectangles rects, write a
 * function pick which randomly and uniformily picks an integer point in the
 * space covered by the rectangles.
 * 
 * Note:
 * 
 * 
 * An integer point is a point that has integer coordinates. 
 * A point on the perimeter of a rectangle is included in the space covered by
 * the rectangles. 
 * ith rectangle = rects[i] = [x1,y1,x2,y2], where [x1, y1] are the integer
 * coordinates of the bottom-left corner, and [x2, y2] are the integer
 * coordinates of the top-right corner.
 * length and width of each rectangle does not exceed 2000.
 * 1 <= rects.length <= 100
 * pick return a point as an array of integer coordinates [p_x, p_y]
 * pick is called at most 10000 times.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: 
 * ["Solution","pick","pick","pick"]
 * [[[[1,1,5,5]]],[],[],[]]
 * Output: 
 * [null,[4,1],[4,1],[3,3]]
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 
 * ["Solution","pick","pick","pick","pick","pick"]
 * [[[[-2,-2,-1,-1],[1,0,3,0]]],[],[],[],[],[]]
 * Output: 
 * [null,[-1,-2],[2,0],[-2,-1],[3,0],[-2,-2]]
 * 
 * 
 * 
 * Explanation of Input Syntax:
 * 
 * The input is two lists: the subroutines called and their arguments.
 * Solution's constructor has one argument, the array of rectangles rects. pick
 * has no arguments. Arguments are always wrapped with a list, even if there
 * aren't any.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
	Random rnd = new Random();
	int[] prefSum;
	int[][] rects;
    public Solution(int[][] rects) {
        this.rects = rects;
        prefSum = new int[rects.length + 1];
        for(int i = 1; i < prefSum.length; i++){
        	prefSum[i] = prefSum[i - 1] + (rects[i - 1][2] - rects[i - 1][0] + 1) * (rects[i - 1][3] - rects[i - 1][1] + 1);
        }
    }
    
    public int[] pick() {
        int kth = rnd.nextInt(prefSum[rects.length]) + 1;
        int i = 1;
        for(; i < prefSum.length; i++){
        	if(kth - prefSum[i] <= 0){
        		break;
        	}
        }
        kth -= prefSum[i - 1] + 1;
        return new int[]{rects[i - 1][0] + kth / (rects[i - 1][3] - rects[i - 1][1] + 1), rects[i - 1][1] + kth % ((rects[i - 1][3] - rects[i - 1][1] + 1)) };
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */
// @lc code=end
