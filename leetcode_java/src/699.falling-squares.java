/*
 * @lc app=leetcode id=699 lang=java
 *
 * [699] Falling Squares
 *
 * https://leetcode.com/problems/falling-squares/description/
 *
 * algorithms
 * Hard (43.58%)
 * Likes:    402
 * Dislikes: 68
 * Total Accepted:    18.2K
 * Total Submissions: 41.8K
 * Testcase Example:  '[[1,2],[2,3],[6,1]]'
 *
 * There are several squares being dropped onto the X-axis of a 2D plane.
 * 
 * You are given a 2D integer array positions where positions[i] = [lefti,
 * sideLengthi] represents the i^th square with a side length of sideLengthi
 * that is dropped with its left edge aligned with X-coordinate lefti.
 * 
 * Each square is dropped one at a time from a height above any landed squares.
 * It then falls downward (negative Y direction) until it either lands on the
 * top side of another square or on the X-axis. A square brushing the
 * left/right side of another square does not count as landing on it. Once it
 * lands, it freezes in place and cannot be moved.
 * 
 * After each square is dropped, you must record the height of the current
 * tallest stack of squares.
 * 
 * Return an integer array ans where ans[i] represents the height described
 * above after dropping the i^th square.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: positions = [[1,2],[2,3],[6,1]]
 * Output: [2,5,5]
 * Explanation:
 * After the first drop, the tallest stack is square 1 with a height of 2.
 * After the second drop, the tallest stack is squares 1 and 2 with a height of
 * 5.
 * After the third drop, the tallest stack is still squares 1 and 2 with a
 * height of 5.
 * Thus, we return an answer of [2, 5, 5].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: positions = [[100,100],[200,100]]
 * Output: [100,100]
 * Explanation:
 * After the first drop, the tallest stack is square 1 with a height of 100.
 * After the second drop, the tallest stack is either square 1 or square 2,
 * both with heights of 100.
 * Thus, we return an answer of [100, 100].
 * Note that square 2 only brushes the right side of square 1, which does not
 * count as landing on it.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= positions.length <= 1000
 * 1 <= lefti <= 10^8
 * 1 <= sideLengthi <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> heights = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int maxH = 0;
        for (int i = 0; i < positions.length; i++) {
            int h = positions[i][1];
            for(int j = 0; j < i; j++){
                if(positions[i][0] < positions[j][0] + positions[j][1] && positions[i][0] + positions[i][1] > positions[j][0]){
                    h = Math.max(h, heights.get(j) + positions[i][1]);
                }
            }
            heights.add(h);
            maxH = Math.max(maxH, h);
            res.add(maxH);
        }
        return res;
    }
}
// @lc code=end
