/*
 * @lc app=leetcode id=1632 lang=java
 *
 * [1632] Rank Transform of a Matrix
 *
 * https://leetcode.com/problems/rank-transform-of-a-matrix/description/
 *
 * algorithms
 * Hard (27.73%)
 * Likes:    123
 * Dislikes: 6
 * Total Accepted:    2K
 * Total Submissions: 7K
 * Testcase Example:  '[[1,2],[3,4]]'
 *
 * Given an m x n matrix, return a new matrix answer where answer[row][col] is
 * the rank of matrix[row][col].
 * 
 * The rank is an integer that represents how large an element is compared to
 * other elements. It is calculated using the following rules:
 * 
 * 
 * The rank is an integer starting from 1.
 * If two elements p and q are in the same row or column, then:
 * 
 * If p < q then rank(p) < rank(q)
 * If p == q then rank(p) == rank(q)
 * If p > q then rank(p) > rank(q)
 * 
 * 
 * The rank should be as small as possible.
 * 
 * 
 * It is guaranteed that answer is unique under the given rules.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: matrix = [[1,2],[3,4]]
 * Output: [[1,2],[2,3]]
 * Explanation:
 * The rank of matrix[0][0] is 1 because it is the smallest integer in its row
 * and column.
 * The rank of matrix[0][1] is 2 because matrix[0][1] > matrix[0][0] and
 * matrix[0][0] is rank 1.
 * The rank of matrix[1][0] is 2 because matrix[1][0] > matrix[0][0] and
 * matrix[0][0] is rank 1.
 * The rank of matrix[1][1] is 3 because matrix[1][1] > matrix[0][1],
 * matrix[1][1] > matrix[1][0], and both matrix[0][1] and matrix[1][0] are rank
 * 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: matrix = [[7,7],[7,7]]
 * Output: [[1,1],[1,1]]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
 * Output: [[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: matrix = [[7,3,6],[1,4,5],[9,8,2]]
 * Output: [[5,1,4],[1,2,3],[6,3,1]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 500
 * -10^9 <= matrix[row][col] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[][] matrixRankTransform(int[][] m) {
        int M = m.length, N = m[0].length;
        int[][] a = new int[M][N];
        int[] maxRankRow = new int[M];
        int[] maxRankCol = new int[N];
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                map.putIfAbsent(m[i][j], new ArrayList<>());
                map.get(m[i][j]).add(new int[] { i, j });
            }
        }
        
        // go from the lowest value key to the highest
        for (int k : map.keySet()) {
            // repeat for each value until we used all cells with the same number
            // on each step we find cells connected by row/column and calculate their rank
            while (map.get(k).size() > 0) {
                Set<Integer> rowsUsed = new HashSet<>();
                Set<Integer> colsUsed = new HashSet<>();
                List<int[]> allSame = map.get(k);
                
                // get the first cell as the root and find all connected cells
                int[] root = allSame.get(0);
                rowsUsed.add(root[0]);
                colsUsed.add(root[1]);
                boolean[] used = new boolean[allSame.size()];
                used[0] = true;
                // continue until we found all connected 
                while (true) {
                    int added = 0;
                    for (int i = 1; i < allSame.size(); i++) {
                        int[] n = allSame.get(i);
                        if (used[i]) continue;
                        // if the cell is in the same row or column with the root or any one that is already connected with the root
                        if (rowsUsed.contains(n[0]) || colsUsed.contains(n[1])) {
                            rowsUsed.add(n[0]);
                            colsUsed.add(n[1]);
                            used[i] = true;
                            added++;
                        }
                    }
                    if (added == 0) break;
                }
                List<int[]> connected = new ArrayList<>();
                List<int[]> left = new ArrayList<>();
                for (int i = 0; i < allSame.size(); i++) {
                    if (used[i]) connected.add(allSame.get(i));
                    else left.add(allSame.get(i));
                }
                // put all that are not connected back to the map
                map.put(k, left);

                int rank = Integer.MIN_VALUE;
                
                // calculate the maximum rank of all connected cells
                for (int[] n : connected) {
                    rank = Math.max(rank, Math.max(maxRankRow[n[0]], maxRankCol[n[1]]) + 1);
                }
                // update maxRank for all cols and rows and set the rank as answer for each connected cell
                for (int[] n : connected) {
                    maxRankRow[n[0]] = maxRankCol[n[1]] = rank;
                    a[n[0]][n[1]] = rank;
                }
            }
        }
        return a;
    }
}
// @lc code=end
