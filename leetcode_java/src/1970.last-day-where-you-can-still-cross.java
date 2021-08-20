/*
 * @lc app=leetcode id=1970 lang=java
 *
 * [1970] Last Day Where You Can Still Cross
 *
 * https://leetcode.com/problems/last-day-where-you-can-still-cross/description/
 *
 * algorithms
 * Hard (45.46%)
 * Likes:    250
 * Dislikes: 5
 * Total Accepted:    4.7K
 * Total Submissions: 10.3K
 * Testcase Example:  '2\n2\n[[1,1],[2,1],[1,2],[2,2]]'
 *
 * There is a 1-based binary matrix where 0 represents land and 1 represents
 * water. You are given integers row and col representing the number of rows
 * and columns in the matrix, respectively.
 * 
 * Initially on day 0, the entire matrix is land. However, each day a new cell
 * becomes flooded with water. You are given a 1-based 2D array cells, where
 * cells[i] = [ri, ci] represents that on the i^th day, the cell on the ri^th
 * row and ci^th column (1-based coordinates) will be covered with water (i.e.,
 * changed to 1).
 * 
 * You want to find the last day that it is possible to walk from the top to
 * the bottom by only walking on land cells. You can start from any cell in the
 * top row and end at any cell in the bottom row. You can only travel in the
 * four cardinal directions (left, right, up, and down).
 * 
 * Return the last day where it is possible to walk from the top to the bottom
 * by only walking on land cells.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: row = 2, col = 2, cells = [[1,1],[2,1],[1,2],[2,2]]
 * Output: 2
 * Explanation: The above image depicts how the matrix changes each day
 * starting from day 0.
 * The last day where it is possible to cross from top to bottom is on day 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: row = 2, col = 2, cells = [[1,1],[1,2],[2,1],[2,2]]
 * Output: 1
 * Explanation: The above image depicts how the matrix changes each day
 * starting from day 0.
 * The last day where it is possible to cross from top to bottom is on day 1.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: row = 3, col = 3, cells =
 * [[1,2],[2,1],[3,3],[2,2],[1,1],[1,3],[2,3],[3,2],[3,1]]
 * Output: 3
 * Explanation: The above image depicts how the matrix changes each day
 * starting from day 0.
 * The last day where it is possible to cross from top to bottom is on day
 * 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= row, col <= 2 * 10^4
 * 4 <= row * col <= 2 * 10^4
 * cells.length == row * col
 * 1 <= ri <= row
 * 1 <= ci <= col
 * All the values of cells are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    int n;
    int m;
    public int latestDayToCross(int row, int col, int[][] cells) {
        n = row;
        m = col;
        DSU dsu = new DSU(row * col + 1);
        boolean[][] state = new boolean[n][m];
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        Set<Integer> leftBound = new HashSet<>();
        Set<Integer> rightBound = new HashSet<>();
        for(int i = 0; i < cells.length; i++){
            int[] cell = cells[i];
            state[cell[0] - 1][cell[1] - 1] = true;
            int id = flat(cell[0], cell[1]);
            dsu.add(id);
            if(cell[1] == 1){
                leftBound.add(id);
            }
            if(cell[1] == m){
                rightBound.add(id);
            }
            for(int[] dir : dirs){
                int newR = cell[0] + dir[0];
                int newC = cell[1] + dir[1];
                if(newR >= 1 && newR <= n && newC >= 1 && newC <= m && state[newR - 1][newC - 1]){
                    int newId = flat(newR, newC);
                    dsu.union(id, newId);
                    // System.out.println(id + "+" + newId);
                }
            }
            Set<Integer> s = new HashSet<>();
            for(int leftId : leftBound){
                s.add(dsu.find(leftId));
            }
            // System.out.println("" + leftBound + rightBound + s);
            for(int rightId : rightBound){
                if(s.contains(dsu.find(rightId))){
                    return i;
                }
            }
        }
        return cells.length - 1;
    }

    int flat(int r, int c){
        return (r - 1) * m + c;
    }

    class DSU {
        int[] parent;

        public DSU(int N) {
            this.parent = new int[N];
            for (int i = 0; i < N; i++) {
                add(i);
            }
        }

        public void add(int x) {
            parent[x] = x;
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            parent[find(x)] = parent[find(y)];
        }
    }
}
// @lc code=end
