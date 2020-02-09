/*
 * @lc app=leetcode id=1349 lang=java
 *
 * [1349] Maximum Students Taking Exam
 *
 * https://leetcode.com/problems/maximum-students-taking-exam/description/
 *
 * algorithms
 * Hard (23.56%)
 * Likes:    65
 * Dislikes: 1
 * Total Accepted:    1K
 * Total Submissions: 4.3K
 * Testcase Example:  '[["#",".","#","#",".","#"],[".","#","#","#","#","."],["#",".","#","#",".","#"]]'
 *
 * Given a m * n matrix seats  that represent seats distributions in a
 * classroom. If a seat is broken, it is denoted by '#' character otherwise it
 * is denoted by a '.' character.
 * 
 * Students can see the answers of those sitting next to the left, right, upper
 * left and upper right, but he cannot see the answers of the student sitting
 * directly in front or behind him. Return the maximum number of students that
 * can take the exam together without any cheating being possible..
 * 
 * Students must be placed in seats in good condition.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: seats = [["#",".","#","#",".","#"],
 * [".","#","#","#","#","."],
 * ["#",".","#","#",".","#"]]
 * Output: 4
 * Explanation: Teacher can place 4 students in available seats so they don't
 * cheat on the exam. 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: seats = [[".","#"],
 * ["#","#"],
 * ["#","."],
 * ["#","#"],
 * [".","#"]]
 * Output: 3
 * Explanation: Place all students in available seats. 
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: seats = [["#",".",".",".","#"],
 * [".","#",".","#","."],
 * [".",".","#",".","."],
 * [".","#",".","#","."],
 * ["#",".",".",".","#"]]
 * Output: 10
 * Explanation: Place students in available seats in column 1, 3 and 5.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * seats contains only characters '.' and'#'.
 * m == seats.length
 * n == seats[i].length
 * 1 <= m <= 8
 * 1 <= n <= 8
 * 
 * 
 */

// @lc code=start
class Solution {

	public int maxStudents(char[][] seats) {
    	int n = seats.length;
    	int m = seats[0].length;
    	int[][][] mapping = new int[n][m][2];
    	for(int r = 0; r < n; r++){
        	for(int c = 0; c < m; c++){
        		mapping[r][c][0] = -1;
        		mapping[r][c][1] = -1;
        	}
        }
        int V = 0;
    	int match = 0;
        for(int r = 0; r < n; r++){
        	for(int c = 0; c < m; c++){
        		if(seats[r][c] == '.'){
        			V++;
	        		if(mapping[r][c][0] == -1 && maxBipartiteMatch(seats, mapping, new boolean[n][m], new int[]{r, c})){
	        			match++;
	        		}	
        		}
        		
        	}
        }
        // maximum independent set
        return V - match;
    }

    boolean maxBipartiteMatch(char[][] seats, int[][][] mapping, boolean[][] visited, int[] from){
    	int n = seats.length;
    	int m = seats[0].length;
    	int fromX = from[0];
    	int fromY = from[1];
    	int[] cOffset = {-1, 1};
    	int[] rOffset = {-1, 0, 1};
    	for(int i : cOffset){
    		for (int j : rOffset) {
    			int toX = fromX + j;
    			int toY = fromY + i;
    			int[] to = new int[]{toX, toY};
    			if(toX >= 0 && toX < n && toY >= 0 && toY < m){
    				if(seats[toX][toY] != '#' && !visited[toX][toY]){
    					visited[toX][toY] = true;
    					if(mapping[toX][toY][0] == -1 || maxBipartiteMatch(seats, mapping, visited, mapping[toX][toY]) ){
    						mapping[fromX][fromY] = to;
    						mapping[toX][toY] = from;
    						return true;
    					}
    				}
    			}
    		}
    	}
    	return false;
    }

    public int maxStudentsDP(char[][] seats) {
    	int n = seats.length;
    	int m = seats[0].length;
        int[] maxCnt = new int[1 << m];
        int[] allMask = new int[n + 1];
        for(int r = 1; r <= n; r++){
        	for(int c = 0; c < m; c++){
        		allMask[r] = (allMask[r] << 1);
        		if(seats[r - 1][c] == '.'){
        			allMask[r] |= 1;
        		}
        	}
        }
        // System.out.println(Arrays.toString(allMask));
        int max = 0;
        for(int r = 0; r < n; r++){
        	int[] newMaxCnt = new int[1 << m];
        	for(int prevMask = allMask[r]; prevMask != 0; prevMask = (allMask[r] & (prevMask - 1))){
        		if((prevMask & (prevMask << 1)) == 0){
        			for (int curMask = allMask[r + 1]; curMask != 0; curMask = (allMask[r + 1] & (curMask - 1))) {
						if((curMask & (curMask << 1)) == 0 && (curMask & (prevMask << 1)) == 0 && (curMask & (prevMask >> 1)) == 0){
							// System.out.println(prevMask + ", " + curMask);
							newMaxCnt[curMask] = Math.max(newMaxCnt[curMask], maxCnt[prevMask] + Integer.bitCount(curMask));
							max = Math.max(max, newMaxCnt[curMask]);
						}
					}
					newMaxCnt[0] = Math.max(newMaxCnt[0], maxCnt[prevMask]);
					max = Math.max(max, newMaxCnt[0]);
        		}
        	}
        	for (int curMask = allMask[r + 1]; curMask != 0; curMask = (allMask[r + 1] & (curMask - 1))) {
				if((curMask & (curMask << 1)) == 0){
					// System.out.println(0 + ", " + curMask);
					newMaxCnt[curMask] = Math.max(newMaxCnt[curMask], maxCnt[0] + Integer.bitCount(curMask));
					max = Math.max(max, newMaxCnt[curMask]);
				}
			}
			newMaxCnt[0] = Math.max(newMaxCnt[0], maxCnt[0]);
			max = Math.max(max, newMaxCnt[0]);
        	maxCnt = newMaxCnt;
        	// System.out.println(Arrays.toString(maxCnt));
        }
        return max;
    }
}
// @lc code=end
