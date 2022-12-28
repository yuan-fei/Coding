/*
 * @lc app=leetcode id=773 lang=java
 *
 * [773] Sliding Puzzle
 *
 * https://leetcode.com/problems/sliding-puzzle/description/
 *
 * algorithms
 * Hard (64.05%)
 * Likes:    1762
 * Dislikes: 45
 * Total Accepted:    81.7K
 * Total Submissions: 127.5K
 * Testcase Example:  '[[1,2,3],[4,0,5]]'
 *
 * On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty
 * square represented by 0. A move consists of choosing 0 and a 4-directionally
 * adjacent number and swapping it.
 * 
 * The state of the board is solved if and only if the board is
 * [[1,2,3],[4,5,0]].
 * 
 * Given the puzzle board board, return the least number of moves required so
 * that the state of the board is solved. If it is impossible for the state of
 * the board to be solved, return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: board = [[1,2,3],[5,4,0]]
 * Output: -1
 * Explanation: No number of moves will make the board solved.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * Explanation: 5 is the smallest number of moves that solves the board.
 * An example path:
 * After move 0: [[4,1,2],[5,0,3]]
 * After move 1: [[4,1,2],[0,5,3]]
 * After move 2: [[0,1,2],[4,5,3]]
 * After move 3: [[1,0,2],[4,5,3]]
 * After move 4: [[1,2,0],[4,5,3]]
 * After move 5: [[1,2,3],[4,5,0]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * board.length == 2
 * board[i].length == 3
 * 0 <= board[i][j] <= 5
 * Each value board[i][j] is unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int slidingPuzzle(int[][] board) {
        boolean[] dp = new boolean[6*6*6*6*6*6 + 1];
        int[][] finalState = {{1,2,3,},{4,5,0}};
        int finalCode = encode(finalState);
        int initialCode = encode(board);
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(initialCode);
        int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};
        int step = 0;
        while(!q.isEmpty()){
            for(int x = q.size(); x > 0; x--){
                int code = q.poll();
                if(code == finalCode){
                    return step;
                }
                int[][] curState = decode(code);
                int[] cur = findTarget(curState);
                for(int[] dir : dirs){
                    int[] nxt = {cur[0] + dir[0], cur[1] + dir[1]};
                    if(0 <= nxt[0] && nxt[0] < 2 && 0 <= nxt[1] && nxt[1] < 3){
                        swap(curState, cur, nxt);
                        int nxtCode = encode(curState);
                        if(!dp[nxtCode]){
                            q.offer(nxtCode);
                            dp[nxtCode] = true;
                        }
                        swap(curState, cur, nxt);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    int encode(int[][] board){
        int ret = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                ret *= 6;
                ret += board[i][j];
            }
        }
        return ret;
    }

    int[][] decode(int code){
        int[][] ret = new int[2][3];
        int i = 5;
        while(code != 0){
            ret[i / 3][i % 3] = code % 6;
            code /= 6;
            i--;
        }
        return ret;
    }

    int[] findTarget(int[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == 0){
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    void swap(int[][] board, int[] pos1, int[] pos2){
        int t = board[pos1[0]][pos1[1]];
        board[pos1[0]][pos1[1]] = board[pos2[0]][pos2[1]];
        board[pos2[0]][pos2[1]] = t;
    }
}
// @lc code=end
