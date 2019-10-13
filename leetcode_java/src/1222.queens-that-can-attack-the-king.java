/*
 * @lc app=leetcode id=1222 lang=java
 *
 * [1222] Queens That Can Attack the King
 *
 * https://leetcode.com/problems/queens-that-can-attack-the-king/description/
 *
 * algorithms
 * Medium (69.24%)
 * Total Accepted:    4K
 * Total Submissions: 5.7K
 * Testcase Example:  '[[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]]\n[0,0]'
 *
 * On an 8x8 chessboard, there can be multiple Black Queens and one White
 * King.
 * 
 * Given an array of integer coordinates queens that represents the positions
 * of the Black Queens, and a pair of coordinates king that represent the
 * position of the White King, return the coordinates of all the queens (in any
 * order) that can attack the King.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
 * Output: [[0,1],[1,0],[3,3]]
 * Explanation:  
 * The queen at [0,1] can attack the king cause they're in the same row. 
 * The queen at [1,0] can attack the king cause they're in the same column. 
 * The queen at [3,3] can attack the king cause they're in the same diagnal. 
 * The queen at [0,4] can't attack the king cause it's blocked by the queen at
 * [0,1]. 
 * The queen at [4,0] can't attack the king cause it's blocked by the queen at
 * [1,0]. 
 * The queen at [2,4] can't attack the king cause it's not in the same
 * row/column/diagnal as the king.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
 * Output: [[2,2],[3,4],[4,4]]
 * 
 * 
 * Example 3:
 * 
 * 
 * 
 * 
 * Input: queens =
 * [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]],
 * king = [3,4]
 * Output: [[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= queens.length <= 63
 * queens[0].length == 2
 * 0 <= queens[i][j] < 8
 * king.length == 2
 * 0 <= king[0], king[1] < 8
 * At most one piece is allowed in a cell.
 * 
 * 
 */
class Solution {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> ans = new ArrayList<>();
        int[][] dir = {{0,-1},{0,1},{-1,0},{1,0},{-1,-1},{1,1},{-1,1},{1,-1}};
        int[][] closest = new int[8][2];
        for (int i = 0; i < 8; i++) {
        	int[] d = dir[i];
        	closest[i] = new int[]{king[0] + 9 * d[0], king[1] + 9 * d[1]};
        }

        for (int[] queen : queens) {
        	switch(attack(queen, king)){
        		case 1:
        			if(closest[0][1] < queen[1] && queen[1] < king[1]){
        				closest[0] = queen;
        			}
        			if(closest[1][1] > queen[1] && queen[1] > king[1]){
        				closest[1] = queen;
        			}
        			break;
    			case 2:
	    			if(closest[2][0] < queen[0] && queen[0] < king[0]){
	    				closest[2] = queen;
	    			}
	    			if(closest[3][0] > queen[0] && queen[0] > king[0]){
	    				closest[3] = queen;
	    			}
	    			break;
	    		case 3:
	    			if(closest[4][1] < queen[1] && queen[1] < king[1]){
        				closest[4] = queen;
        			}
        			if(closest[5][1] > queen[1] && queen[1] > king[1]){
        				closest[5] = queen;
        			}
        			break;
        		case 4:
	    			if(closest[6][0] < queen[0] && queen[0] < king[0]){
        				closest[6] = queen;
        			}
        			if(closest[7][0] > queen[0] && queen[0] > king[0]){
        				closest[7] = queen;
        			}
        			break;
        	}
        }
        for(int i = 0; i < 8; i++){
        	if(closest[i][0] != king[0] + 9*dir[i][0] || closest[i][1] != king[1] + 9*dir[i][1])
        	ans.add(Arrays.asList(closest[i][0], closest[i][1]));	
        }
        
        return ans;
    }

    int attack(int[] queen, int[] king){
    	if(queen[0] == king[0]){
    		return 1;
    	} else if(queen[1] == king[1]){
    		return 2;
    	}  else if(queen[0] + queen[1] == king[0] + king[1]){
    		return 3;
    	}  else if( queen[0] - queen[1] == king[0] - king[1]){
    		return 4;
    	}
    	else{
    		return 0;
    	}
    }
}
