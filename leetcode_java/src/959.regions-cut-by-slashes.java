/*
 * @lc app=leetcode id=959 lang=java
 *
 * [959] Regions Cut By Slashes
 *
 * https://leetcode.com/problems/regions-cut-by-slashes/description/
 *
 * algorithms
 * Medium (69.29%)
 * Likes:    2975
 * Dislikes: 558
 * Total Accepted:    48.7K
 * Total Submissions: 70.3K
 * Testcase Example:  '[" /","/ "]'
 *
 * An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists
 * of a '/', '\', or blank space ' '. These characters divide the square into
 * contiguous regions.
 * 
 * Given the grid grid represented as a string array, return the number of
 * regions.
 * 
 * Note that backslash characters are escaped, so a '\' is represented as
 * '\\'.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [" /","/ "]
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [" /","  "]
 * Output: 1
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = ["/\\","\\/"]
 * Output: 5
 * Explanation: Recall that because \ characters are escaped, "\\/" refers to
 * \/, and "/\\" refers to /\.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == grid.length == grid[i].length
 * 1 <= n <= 30
 * grid[i][j] is either '/', '\', or ' '.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int regionsBySlashes(String[] grid) {
        int N = grid.length;
        DSU dsu = new DSU(4 * N * N);
        for (int r = 0; r < N; ++r)
            for (int c = 0; c < N; ++c) {
                int root = 4 * (r * N + c);
                char val = grid[r].charAt(c);
                if (val != '\\') {
                    dsu.union(root + 0, root + 1);
                    dsu.union(root + 2, root + 3);
                }
                if (val != '/') {
                    dsu.union(root + 0, root + 2);
                    dsu.union(root + 1, root + 3);
                }

                // north south
                if (r + 1 < N)
                    dsu.union(root + 3, (root + 4 * N) + 0);
                if (r - 1 >= 0)
                    dsu.union(root + 0, (root - 4 * N) + 3);
                // east west
                if (c + 1 < N)
                    dsu.union(root + 2, (root + 4) + 1);
                if (c - 1 >= 0)
                    dsu.union(root + 1, (root - 4) + 2);
            }

        int ans = 0;
        for (int x = 0; x < 4 * N * N; ++x) {
            if (dsu.find(x) == x)
                ans++;
        }

        return ans;
    }
}

class DSU {
    int[] parent;
    public DSU(int N) {
        parent = new int[N];
        for (int i = 0; i < N; ++i)
            parent[i] = i;
    }
    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}
// @lc code=end
