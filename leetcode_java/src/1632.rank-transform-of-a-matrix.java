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
    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] res = new int[m][n];
        TreeMap<Integer, List<int[]>> map = new TreeMap<>(); //<value, List<Pos>>
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                map.computeIfAbsent(matrix[i][j], value -> new ArrayList<>()).add(new int[]{i, j});
        int[] rowMax = new int[m], colMax = new int[n];
        for (Integer key : map.keySet()) {
            List<int[]> list = map.get(key);
            int N = list.size();
            DSU dsu = new DSU(N);
            for (int i = 0; i < N; i++)
                for (int j = i + 1; j < N; j++) {
                    int[] pos1 = list.get(i), pos2 = list.get(j);
                    if (pos1[0] == pos2[0] || pos1[1] == pos2[1]) dsu.union(i, j);
                }
            Map<Integer, List<int[]>> group = new HashMap<>();
            for (int i = 0; i < N; i++)
                group.computeIfAbsent(dsu.find(i), value -> new ArrayList<>()).add(list.get(i));
            for (Integer root : group.keySet()) {
                int rank = 0;
                for (int[] pos : group.get(root)){
                    int x = pos[0], y = pos[1];
                    rank = Math.max(rank, Math.max(rowMax[x], colMax[y]) + 1);
                }
                for (int[] pos : group.get(root)) {
                    int x = pos[0], y = pos[1];
                    res[x][y] = rank;
                    rowMax[x] = Math.max(rowMax[x], rank);
                    colMax[y] = Math.max(colMax[y], rank);
                }     
            }
        }
        return res;
    }
}
class DSU {
    int[] parent;
    public DSU(int N) {
        parent = new int[N];
        for (int i = 0; i< N; i++) parent[i] = i;
    }
    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    public void union(int x, int y) {parent[find(x)] = find(y);}
}
// @lc code=end
