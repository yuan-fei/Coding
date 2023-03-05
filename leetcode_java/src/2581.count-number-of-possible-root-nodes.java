/*
 * @lc app=leetcode id=2581 lang=java
 *
 * [2581] Count Number of Possible Root Nodes
 *
 * https://leetcode.com/problems/count-number-of-possible-root-nodes/description/
 *
 * algorithms
 * Hard (31.26%)
 * Likes:    32
 * Dislikes: 0
 * Total Accepted:    928
 * Total Submissions: 3.1K
 * Testcase Example:  '[[0,1],[1,2],[1,3],[4,2]]\n[[1,3],[0,1],[1,0],[2,4]]\n3'
 *
 * Alice has an undirected tree with n nodes labeled from 0 to n - 1. The tree
 * is represented as a 2D integer array edges of length n - 1 where edges[i] =
 * [ai, bi] indicates that there is an edge between nodes ai and bi in the
 * tree.
 * 
 * Alice wants Bob to find the root of the tree. She allows Bob to make several
 * guesses about her tree. In one guess, he does the following:
 * 
 * 
 * Chooses two distinct integers u and v such that there exists an edge [u, v]
 * in the tree.
 * He tells Alice that u is the parent of v in the tree.
 * 
 * 
 * Bob's guesses are represented by a 2D integer array guesses where guesses[j]
 * = [uj, vj] indicates Bob guessed uj to be the parent of vj.
 * 
 * Alice being lazy, does not reply to each of Bob's guesses, but just says
 * that at least k of his guesses are true.
 * 
 * Given the 2D integer arrays edges, guesses and the integer k, return the
 * number of possible nodes that can be the root of Alice's tree. If there is
 * no such tree, return 0.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: edges = [[0,1],[1,2],[1,3],[4,2]], guesses =
 * [[1,3],[0,1],[1,0],[2,4]], k = 3
 * Output: 3
 * Explanation: 
 * Root = 0, correct guesses = [1,3], [0,1], [2,4]
 * Root = 1, correct guesses = [1,3], [1,0], [2,4]
 * Root = 2, correct guesses = [1,3], [1,0], [2,4]
 * Root = 3, correct guesses = [1,0], [2,4]
 * Root = 4, correct guesses = [1,3], [1,0]
 * Considering 0, 1, or 2 as root node leads to 3 correct guesses.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: edges = [[0,1],[1,2],[2,3],[3,4]], guesses =
 * [[1,0],[3,4],[2,1],[3,2]], k = 1
 * Output: 5
 * Explanation: 
 * Root = 0, correct guesses = [3,4]
 * Root = 1, correct guesses = [1,0], [3,4]
 * Root = 2, correct guesses = [1,0], [2,1], [3,4]
 * Root = 3, correct guesses = [1,0], [2,1], [3,2], [3,4]
 * Root = 4, correct guesses = [1,0], [2,1], [3,2]
 * Considering any node as root will give at least 1 correct guess. 
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * edges.length == n - 1
 * 2 <= n <= 10^5
 * 1 <= guesses.length <= 10^5
 * 0 <= ai, bi, uj, vj <= n - 1
 * ai != bi
 * uj != vj
 * edges represents a valid tree.
 * guesses[j] is an edge of the tree.
 * guesses is unique.
 * 0 <= k <= guesses.length
 * 
 * 
 */

// @lc code=start
class Solution {
    Map<Long, Integer> edgeGuesses = new HashMap<>();
    List<Integer>[] adj;
    int[] correctGuessedTotalEdges;
    public int rootCount(int[][] edges, int[][] guesses, int k) {
        int n = edges.length + 1;
        adj = buildAdj(n, edges);
        correctGuessedTotalEdges = new int[n];
        for(int[] g : guesses){
            long code = encode(g[0], g[1]);
            edgeGuesses.put(code, edgeGuesses.getOrDefault(code, 0) + 1);
        }
        int total = dfs1(0, -1);
        dfs2(0, -1, total);
        int ans = 0;
        for(int x : correctGuessedTotalEdges){
            if(x >= k){
                ans++;
            }
        }
        return ans;
    }

    List<Integer>[] buildAdj(int n, int[][] edges) {
        List<Integer>[] ans = new List[n];
        for (int i = 0; i < n; i++) {
            ans[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            ans[edges[i][0]].add(edges[i][1]);
            ans[edges[i][1]].add(edges[i][0]);
        }
        return ans;
    }

    int dfs1(int u, int p){
        int ret = 0;
        for(int v : adj[u]){
            if(v != p){
                ret += edgeGuesses.getOrDefault(encode(u, v), 0);
                ret += dfs1(v, u);
            }
        }
        return ret;
    }

    void dfs2(int u, int p, int total){
        correctGuessedTotalEdges[u] = total;
        for(int v : adj[u]){
            if(v != p){

                int dec = edgeGuesses.getOrDefault(encode(u, v), 0);
                int inc = edgeGuesses.getOrDefault(encode(v, u), 0);
                dfs2(v, u, total - dec + inc);
            }
        }
    }

    long encode(int u, int v){
        return u * 100000L + v;
    }
}
// @lc code=end
