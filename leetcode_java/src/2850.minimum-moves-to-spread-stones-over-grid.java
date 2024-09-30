/*
 * @lc app=leetcode id=2850 lang=java
 *
 * [2850] Minimum Moves to Spread Stones Over Grid
 *
 * https://leetcode.com/problems/minimum-moves-to-spread-stones-over-grid/description/
 *
 * algorithms
 * Medium (41.36%)
 * Likes:    504
 * Dislikes: 69
 * Total Accepted:    23K
 * Total Submissions: 51.3K
 * Testcase Example:  '[[1,1,0],[1,1,1],[1,2,1]]'
 *
 * You are given a 0-indexed 2D integer matrix grid of size 3 * 3, representing
 * the number of stones in each cell. The grid contains exactly 9 stones, and
 * there can be multiple stones in a single cell.
 * 
 * In one move, you can move a single stone from its current cell to any other
 * cell if the two cells share a side.
 * 
 * Return the minimum number of moves required to place one stone in each
 * cell.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[1,1,0],[1,1,1],[1,2,1]]
 * Output: 3
 * Explanation: One possible sequence of moves to place one stone in each cell
 * is: 
 * 1- Move one stone from cell (2,1) to cell (2,2).
 * 2- Move one stone from cell (2,2) to cell (1,2).
 * 3- Move one stone from cell (1,2) to cell (0,2).
 * In total, it takes 3 moves to place one stone in each cell of the grid.
 * It can be shown that 3 is the minimum number of moves required to place one
 * stone in each cell.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,3,0],[1,0,0],[1,0,3]]
 * Output: 4
 * Explanation: One possible sequence of moves to place one stone in each cell
 * is:
 * 1- Move one stone from cell (0,1) to cell (0,2).
 * 2- Move one stone from cell (0,1) to cell (1,1).
 * 3- Move one stone from cell (2,2) to cell (1,2).
 * 4- Move one stone from cell (2,2) to cell (2,1).
 * In total, it takes 4 moves to place one stone in each cell of the grid.
 * It can be shown that 4 is the minimum number of moves required to place one
 * stone in each cell.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * grid.length == grid[i].length == 3
 * 0 <= grid[i][j] <= 9
 * Sum of grid is equal to 9.
 * 
 * 
 */

// @lc code=start
class Solution {
    int N = 3;
    int minMoves;
    public int minimumMoves(int[][] grid) {
        List<int[]> stones = new ArrayList<>();
        List<int[]> vacancies = new ArrayList<>();
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                while(grid[i][j] > 1){
                    stones.add(new int[]{i, j});
                    grid[i][j]--;
                }
                if(grid[i][j] == 0){
                    vacancies.add(new int[]{i, j});
                }
            }
        }
        minMoves = 2 * N * N * N;
        minMovesHelper(stones, vacancies, 0);
        return minMoves;

    }

    void minMovesHelper(List<int[]> stones, List<int[]> vacancies, int pos){
        if(pos == stones.size()){
            int total = 0;
            for(int i = 0; i < stones.size(); i++){
                total += Math.abs(stones.get(i)[0] - vacancies.get(i)[0]) + Math.abs(stones.get(i)[1] - vacancies.get(i)[1]);
            }
            minMoves = Math.min(minMoves, total);
            return;
        }

        for(int i = pos; i < stones.size(); i++){
            swap(stones, i, pos);
            minMovesHelper(stones, vacancies, pos + 1);
            swap(stones, i, pos);
        }
    }

    void swap(List<int[]> list, int i, int j){
        int[] t = list.get(i);
        list.set(i, list.get(j));
        list.set(j, t);
    }
}
// @lc code=end
