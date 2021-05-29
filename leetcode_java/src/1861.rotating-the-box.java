/*
 * @lc app=leetcode id=1861 lang=java
 *
 * [1861] Rotating the Box
 *
 * https://leetcode.com/problems/rotating-the-box/description/
 *
 * algorithms
 * Medium (59.17%)
 * Likes:    100
 * Dislikes: 14
 * Total Accepted:    4.6K
 * Total Submissions: 7.8K
 * Testcase Example:  '[["#",".","#"]]'
 *
 * You are given an m x n matrix of characters box representing a side-view of
 * a box. Each cell of the box is one of the following:
 * 
 * 
 * A stone '#'
 * A stationary obstacle '*'
 * Empty '.'
 * 
 * 
 * The box is rotated 90 degrees clockwise, causing some of the stones to fall
 * due to gravity. Each stone falls down until it lands on an obstacle, another
 * stone, or the bottom of the box. Gravity does not affect the obstacles'
 * positions, and the inertia from the box's rotation does not affect the
 * stones' horizontal positions.
 * 
 * It is guaranteed that each stone in box rests on an obstacle, another stone,
 * or the bottom of the box.
 * 
 * Return an n x m matrix representing the box after the rotation described
 * above.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: box = [["#",".","#"]]
 * Output: [["."],
 * ["#"],
 * ["#"]]
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: box = [["#",".","*","."],
 * ["#","#","*","."]]
 * Output: [["#","."],
 * ["#","#"],
 * ["*","*"],
 * [".","."]]
 * 
 * 
 * Example 3:
 * 
 * 
 * 
 * 
 * Input: box = [["#","#","*",".","*","."],
 * ["#","#","#","*",".","."],
 * ["#","#","#",".","#","."]]
 * Output: [[".","#","#"],
 * [".","#","#"],
 * ["#","#","*"],
 * ["#","*","."],
 * ["#",".","*"],
 * ["#",".","."]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == box.length
 * n == box[i].length
 * 1 <= m, n <= 500
 * box[i][j] is either '#', '*', or '.'.
 * 
 */

// @lc code=start
class Solution {
    public char[][] rotateTheBox(char[][] box) {
        box = rotate(box);
        int n = box.length;
    	int m = box[0].length;
        char[][] ret = new char[n][m];
		for(int i = 0; i < n; i++){
			Arrays.fill(ret[i], '.');
		}
        for(int i = 0; i < m; i++){
        	int last = n - 1;
    		for(int j = n - 1; j >= 0; j--){
    			if(box[j][i] == '#'){
    				ret[last][i] = '#';
    				last--;
    			}
    			else if(box[j][i] == '*'){
    				ret[j][i] = '*';
    				last = j - 1;
    			}
    		}
    	}
    	return ret;
    }

    char[][] rotate(char[][] box){
    	int n = box.length;
    	int m = box[0].length;
    	char[][] ret = new char[m][n];
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < m; j++){
    			ret[j][n - i - 1] = box[i][j];
    		}
    	}
    	return ret;
    }
}
// @lc code=end
