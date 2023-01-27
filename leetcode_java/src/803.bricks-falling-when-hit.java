/*
 * @lc app=leetcode id=803 lang=java
 *
 * [803] Bricks Falling When Hit
 *
 * https://leetcode.com/problems/bricks-falling-when-hit/description/
 *
 * algorithms
 * Hard (34.30%)
 * Likes:    957
 * Dislikes: 180
 * Total Accepted:    27.3K
 * Total Submissions: 79.7K
 * Testcase Example:  '[[1,0,0,0],[1,1,1,0]]\n[[1,0]]'
 *
 * You are given an m x n binary grid, where each 1 represents a brick and 0
 * represents an empty space. A brick is stable if:
 * 
 * 
 * It is directly connected to the top of the grid, or
 * At least one other brick in its four adjacent cells is stable.
 * 
 * 
 * You are also given an array hits, which is a sequence of erasures we want to
 * apply. Each time we want to erase the brick at the location hits[i] = (rowi,
 * coli). The brick on that location (if it exists) will disappear. Some other
 * bricks may no longer be stable because of that erasure and will fall. Once a
 * brick falls, it is immediately erased from the grid (i.e., it does not land
 * on other stable bricks).
 * 
 * Return an array result, where each result[i] is the number of bricks that
 * will fall after the i^th erasure is applied.
 * 
 * Note that an erasure may refer to a location with no brick, and if it does,
 * no bricks drop.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
 * Output: [2]
 * Explanation: Starting with the grid:
 * [[1,0,0,0],
 * ⁠[1,1,1,0]]
 * We erase the underlined brick at (1,0), resulting in the grid:
 * [[1,0,0,0],
 * ⁠[0,1,1,0]]
 * The two underlined bricks are no longer stable as they are no longer
 * connected to the top nor adjacent to another stable brick, so they will
 * fall. The resulting grid is:
 * [[1,0,0,0],
 * ⁠[0,0,0,0]]
 * Hence the result is [2].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
 * Output: [0,0]
 * Explanation: Starting with the grid:
 * [[1,0,0,0],
 * ⁠[1,1,0,0]]
 * We erase the underlined brick at (1,1), resulting in the grid:
 * [[1,0,0,0],
 * ⁠[1,0,0,0]]
 * All remaining bricks are still stable, so no bricks fall. The grid remains
 * the same:
 * [[1,0,0,0],
 * ⁠[1,0,0,0]]
 * Next, we erase the underlined brick at (1,0), resulting in the grid:
 * [[1,0,0,0],
 * ⁠[0,0,0,0]]
 * Once again, all remaining bricks are still stable, so no bricks fall.
 * Hence the result is [0,0].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * grid[i][j] is 0 or 1.
 * 1 <= hits.length <= 4 * 10^4
 * hits[i].length == 2
 * 0 <= xi <= m - 1
 * 0 <= yi <= n - 1
 * All (xi, yi) are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int R = grid.length, C = grid[0].length;
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};

        int[][] A = new int[R][C];
        for (int r = 0; r < R; ++r)
            A[r] = grid[r].clone();
        for (int[] hit: hits)
            A[hit[0]][hit[1]] = 0;

        DSU dsu = new DSU(R*C + 1);
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (A[r][c] == 1) {
                    int i = r * C + c;
                    if (r == 0)
                        dsu.union(i, R*C);
                    if (r > 0 && A[r-1][c] == 1)
                        dsu.union(i, (r-1) *C + c);
                    if (c > 0 && A[r][c-1] == 1)
                        dsu.union(i, r * C + c-1);
                }
            }
        }
        int t = hits.length;
        int[] ans = new int[t--];

        while (t >= 0) {
            int r = hits[t][0];
            int c = hits[t][1];
            int preRoof = dsu.top();
            if (grid[r][c] == 0) {
                t--;
            } else {
                int i = r * C + c;
                for (int k = 0; k < 4; ++k) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if (0 <= nr && nr < R && 0 <= nc && nc < C && A[nr][nc] == 1)
                        dsu.union(i, nr * C + nc);
                }
                if (r == 0)
                    dsu.union(i, R*C);
                A[r][c] = 1;
                ans[t--] = Math.max(0, dsu.top() - preRoof - 1);
            }
        }

        return ans;
    }
}

class DSU {
    int[] parent;
    int[] rank;
    int[] sz;

    public DSU(int N) {
        parent = new int[N];
        for (int i = 0; i < N; ++i)
            parent[i] = i;
        rank = new int[N];
        sz = new int[N];
        Arrays.fill(sz, 1);
    }

    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y) {
        int xr = find(x), yr = find(y);
        if (xr == yr) return;

        if (rank[xr] < rank[yr]) {
            int tmp = yr;
            yr = xr;
            xr = tmp;
        }
        if (rank[xr] == rank[yr])
            rank[xr]++;

        parent[yr] = xr;
        sz[xr] += sz[yr];
    }

    public int size(int x) {
        return sz[find(x)];
    }

    public int top() {
        return size(sz.length - 1) - 1;
    }
}
// @lc code=end
