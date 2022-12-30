/*
 * @lc app=leetcode id=782 lang=java
 *
 * [782] Transform to Chessboard
 *
 * https://leetcode.com/problems/transform-to-chessboard/description/
 *
 * algorithms
 * Hard (51.79%)
 * Likes:    317
 * Dislikes: 291
 * Total Accepted:    15.6K
 * Total Submissions: 30.2K
 * Testcase Example:  '[[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]'
 *
 * You are given an n x n binary grid board. In each move, you can swap any two
 * rows with each other, or any two columns with each other.
 * 
 * Return the minimum number of moves to transform the board into a chessboard
 * board. If the task is impossible, return -1.
 * 
 * A chessboard board is a board where no 0's and no 1's are 4-directionally
 * adjacent.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
 * Output: 2
 * Explanation: One potential sequence of moves is shown.
 * The first move swaps the first and second column.
 * The second move swaps the second and third row.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: board = [[0,1],[1,0]]
 * Output: 0
 * Explanation: Also note that the board with 0 in the top left corner, is also
 * a valid chessboard.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: board = [[1,0],[1,0]]
 * Output: -1
 * Explanation: No matter what sequence of moves you make, you cannot end with
 * a valid chessboard.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == board.length
 * n == board[i].length
 * 2 <= n <= 30
 * board[i][j] is either 0 or 1.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int movesToChessboard(int[][] board) {
        int swapCnt1 = countSwap(board);
        if(swapCnt1 == -1){
            return -1;
        }
        transpose(board);
        int swapCnt2 = countSwap(board);
        if(swapCnt2 == -1){
            return -1;
        }
        return swapCnt1 + swapCnt2;
    }

    void transpose(int[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(i < j){
                    swap(board, new int[]{i, j}, new int[]{j, i});
                }
            }
        }
    }

    void swap(int[][] board, int[] x, int[] y){
        int t = board[x[0]][x[1]];
        board[x[0]][x[1]] = board[y[0]][y[1]];
        board[y[0]][y[1]] = t;
    }

    int countSwap(int[][] board){
        int n = board.length;
        Set<Integer> m = new HashSet<>();
        for(int i = 0; i < n; i++){
            int code = 0;
            for(int j = 0; j < n; j++){
                if(board[i][j] == 1){
                    code |= 1 << j;    
                }
            }
            m.add(code);
        }
        // System.out.println(m);
        if(m.size() > 2){
            return -1;
        }
        int overlap = (1 << n) - 1;
        int totalBitCnt = 0;
        for(int k : m){
            if(Integer.bitCount(k) != n / 2 && Integer.bitCount(k) != (n + 1) / 2 ){
                return -1;
            }
            overlap &= k;
            totalBitCnt += Integer.bitCount(k);
        }
        // System.out.println(Arrays.asList(overlap, totalBitCnt));
        if(m.size() == 2 && (totalBitCnt != n || overlap != 0)){
            return -1;
        }
        int ret = n;
        for(int k : m){
            int swap = 0;
            int target = (Integer.bitCount(k) * 2 > n)? 1 : 0;
            for(int i = 0 ; i < n; i += 2){
                if(((k >> i) & 1) != target){
                    swap++;
                }
            }
            ret = Math.min(ret, swap);
        }
        // System.out.println(ret);
        return ret;
    }
}
// @lc code=end
