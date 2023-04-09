/*
 * @lc app=leetcode id=2617 lang=java
 *
 * [2617] Minimum Number of Visited Cells in a Grid
 *
 * https://leetcode.com/problems/minimum-number-of-visited-cells-in-a-grid/description/
 *
 * algorithms
 * Hard (21.05%)
 * Likes:    46
 * Dislikes: 0
 * Total Accepted:    1.4K
 * Total Submissions: 7K
 * Testcase Example:  '[[3,4,2,1],[4,2,3,1],[2,1,0,0],[2,4,0,0]]'
 *
 * You are given a 0-indexed m x n integer matrix grid. Your initial position
 * is at the top-left cell (0, 0).
 * 
 * Starting from the cell (i, j), you can move to one of the following
 * cells:
 * 
 * 
 * Cells (i, k) with j < k <= grid[i][j] + j (rightward movement), or
 * Cells (k, j) with i < k <= grid[i][j] + i (downward movement).
 * 
 * 
 * Return the minimum number of cells you need to visit to reach the
 * bottom-right cell (m - 1, n - 1). If there is no valid path, return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[3,4,2,1],[4,2,3,1],[2,1,0,0],[2,4,0,0]]
 * Output: 4
 * Explanation: The image above shows one of the paths that visits exactly 4
 * cells.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[3,4,2,1],[4,2,1,1],[2,1,1,0],[3,4,1,0]]
 * Output: 3
 * Explanation: The image above shows one of the paths that visits exactly 3
 * cells.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[2,1,0],[1,0,0]]
 * Output: -1
 * Explanation: It can be proven that no path exists.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 10^5
 * 0 <= grid[i][j] < m * n
 * grid[m - 1][n - 1] == 0
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumVisitedCells(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if(n == 1 && m == 1){
            return 1;
        }
        PriorityQueue<int[]>[] rowQueue = new PriorityQueue[n];
        PriorityQueue<int[]>[] colQueue = new PriorityQueue[m];
        TreeSet<int[]>[] rowMinDist = new TreeSet[n];
        TreeSet<int[]>[] colMinDist = new TreeSet[m];
        for(int i = 0; i < n; i++){ 
            rowQueue[i] = new PriorityQueue<>(Comparator.comparing((int[] a) -> a[1]));
            rowMinDist[i] = new TreeSet<>(Comparator.comparing((int[] a) -> a[2]).thenComparing(a -> a[0]));
        }
        for(int i = 0; i < m; i++){ 
            colQueue[i] = new PriorityQueue<>(Comparator.comparing((int[] a) -> a[1]));
            colMinDist[i] = new TreeSet<>(Comparator.comparing((int[] a) -> a[2]).thenComparing(a -> a[0]));
        }
        int[] initRow = {0, grid[0][0], 1};
        int[] initCol = {0, grid[0][0], 1};
        rowQueue[0].offer(initRow);
        colQueue[0].offer(initCol);
        rowMinDist[0].add(initRow);
        colMinDist[0].add(initCol);
        int MAX = n * m + 5;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(i == 0 && j == 0) continue;
                int minDist = MAX;
                while(!rowQueue[i].isEmpty() && rowQueue[i].peek()[1] < j){
                    int[] item = rowQueue[i].poll();
                    rowMinDist[i].remove(item);
                }
                if(!rowMinDist[i].isEmpty()){
                    minDist = Math.min(minDist, rowMinDist[i].first()[2] + 1);
                }
                while(!colQueue[j].isEmpty() && colQueue[j].peek()[1] < i){
                    int[] item = colQueue[j].poll();
                    colMinDist[j].remove(item);
                }
                if(!colMinDist[j].isEmpty()){
                    minDist = Math.min(minDist, colMinDist[j].first()[2] + 1);
                }
                int[] rowItem = new int[]{i * m + j, j + grid[i][j], minDist};
                int[] colItem = new int[]{i * m + j, i + grid[i][j], minDist};
                rowQueue[i].offer(rowItem);
                rowMinDist[i].add(rowItem);
                colQueue[j].offer(colItem);
                colMinDist[j].add(colItem);
                // System.out.println(Arrays.asList(i, j, minDist));
                if(i == n - 1 && j == m - 1){
                    return minDist >= MAX ? -1 : minDist;
                }
            }
        }
        return -1;
    }
}
// @lc code=end
