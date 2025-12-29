/*
 * @lc app=leetcode id=3203 lang=java
 *
 * [3203] Find Minimum Diameter After Merging Two Trees
 *
 * https://leetcode.com/problems/find-minimum-diameter-after-merging-two-trees/description/
 *
 * algorithms
 * Hard (57.99%)
 * Likes:    687
 * Dislikes: 39
 * Total Accepted:    77.3K
 * Total Submissions: 135.3K
 * Testcase Example:  '[[0,1],[0,2],[0,3]]\n[[0,1]]'
 *
 * There exist two undirected trees with n and m nodes, numbered from 0 to n -
 * 1 and from 0 to m - 1, respectively. You are given two 2D integer arrays
 * edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i]
 * = [ai, bi] indicates that there is an edge between nodes ai and bi in the
 * first tree and edges2[i] = [ui, vi] indicates that there is an edge between
 * nodes ui and vi in the second tree.
 * 
 * You must connect one node from the first tree with another node from the
 * second tree with an edge.
 * 
 * Return the minimum possible diameter of the resulting tree.
 * 
 * The diameter of a tree is the length of the longest path between any two
 * nodes in the tree.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: edges1 = [[0,1],[0,2],[0,3]], edges2 = [[0,1]]
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * We can obtain a tree of diameter 3 by connecting node 0 from the first tree
 * with any node from the second tree.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: edges1 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]], edges2 =
 * [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]]
 * 
 * Output: 5
 * 
 * Explanation:
 * 
 * We can obtain a tree of diameter 5 by connecting node 0 from the first tree
 * with node 0 from the second tree.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n, m <= 10^5
 * edges1.length == n - 1
 * edges2.length == m - 1
 * edges1[i].length == edges2[i].length == 2
 * edges1[i] = [ai, bi]
 * 0 <= ai, bi < n
 * edges2[i] = [ui, vi]
 * 0 <= ui, vi < m
 * The input is generated such that edges1 and edges2 represent valid trees.
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int diameter1 = calDiameter(edges1);
        int diameter2 = calDiameter(edges2);
        int ans = 0;
        ans = Math.max(diameter1, ans);
        ans = Math.max(diameter2, ans);
        ans = Math.max((diameter1 + 1) / 2 + (diameter2 + 1) / 2 + 1, ans);
        return ans;
    }

    List<Integer>[] buildAdj(int[][] edges) {
        int n = edges.length + 1;
        @SuppressWarnings("unchecked")
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        return adj;
    }

    int[] dfs(int u, int p, int d, List<Integer>[] adj) {
        int[] ret = new int[] { d, u };
        for (int v : adj[u]) {
            if (v != p) {
                int[] r = dfs(v, u, d + 1, adj);
                if (r[0] > ret[0]) {
                    ret = r;
                }
            }
        }
        return ret;
    }

    int calDiameter(int[][] edges) {
        List<Integer>[] adj = buildAdj(edges);
        int[] end = dfs(0, -1, 0, adj);
        return dfs(end[1], -1, 0, adj)[0];
    }
}
// @lc code=end
