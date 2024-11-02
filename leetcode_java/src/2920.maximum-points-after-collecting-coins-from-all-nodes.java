/*
 * @lc app=leetcode id=2920 lang=java
 *
 * [2920] Maximum Points After Collecting Coins From All Nodes
 *
 * https://leetcode.com/problems/maximum-points-after-collecting-coins-from-all-nodes/description/
 *
 * algorithms
 * Hard (37.14%)
 * Likes:    201
 * Dislikes: 17
 * Total Accepted:    8.2K
 * Total Submissions: 22K
 * Testcase Example:  '[[0,1],[1,2],[2,3]]\n[10,10,3,3]\n5'
 *
 * There exists an undirected tree rooted at node 0 with n nodes labeled from 0
 * to n - 1. You are given a 2D integer array edges of length n - 1, where
 * edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi
 * in the tree. You are also given a 0-indexed array coins of size n where
 * coins[i] indicates the number of coins in the vertex i, and an integer k.
 * 
 * Starting from the root, you have to collect all the coins such that the
 * coins at a node can only be collected if the coins of its ancestors have
 * been already collected.
 * 
 * Coins at nodei can be collected in one of the following ways:
 * 
 * 
 * Collect all the coins, but you will get coins[i] - k points. If coins[i] - k
 * is negative then you will lose abs(coins[i] - k) points.
 * Collect all the coins, but you will get floor(coins[i] / 2) points. If this
 * way is used, then for all the nodej present in the subtree of nodei,
 * coins[j] will get reduced to floor(coins[j] / 2).
 * 
 * 
 * Return the maximum points you can get after collecting the coins from all
 * the tree nodes.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: edges = [[0,1],[1,2],[2,3]], coins = [10,10,3,3], k = 5
 * Output: 11                        
 * Explanation: 
 * Collect all the coins from node 0 using the first way. Total points = 10 - 5
 * = 5.
 * Collect all the coins from node 1 using the first way. Total points = 5 +
 * (10 - 5) = 10.
 * Collect all the coins from node 2 using the second way so coins left at node
 * 3 will be floor(3 / 2) = 1. Total points = 10 + floor(3 / 2) = 11.
 * Collect all the coins from node 3 using the second way. Total points = 11 +
 * floor(1 / 2) = 11.
 * It can be shown that the maximum points we can get after collecting coins
 * from all the nodes is 11. 
 * 
 * 
 * Example 2:
 * ⁠
 * 
 * 
 * Input: edges = [[0,1],[0,2]], coins = [8,4,4], k = 0
 * Output: 16
 * Explanation: 
 * Coins will be collected from all the nodes using the first way. Therefore,
 * total points = (8 - 0) + (4 - 0) + (4 - 0) = 16.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == coins.length
 * 2 <= n <= 10^5
 * 0 <= coins[i] <= 10^4
 * edges.length == n - 1
 * 0 <= edges[i][0], edges[i][1] < n
 * 0 <= k <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    int MAX = 10000;
    int MAX_FOLDS = 14; // 2^14 >= 10000
    List<Integer>[] adj;
    int k;
    int[][] foldedCoins;
    int[][] cache;
    public int maximumPoints(int[][] edges, int[] coins, int k) {
        int n = coins.length;
        this.k = k;
        foldedCoins = new int[n][MAX_FOLDS + 1];
        for(int i = 0; i < n; i++){
            foldedCoins[i][0] = coins[i];
            for(int j = 1; j <= MAX_FOLDS; j++){
                foldedCoins[i][j] = foldedCoins[i][j - 1] / 2;
            }
        }
        adj = buildAdj(n, edges);
        cache = new int[n][MAX_FOLDS + 1];
        for (int[] slice : cache) {
            Arrays.fill(slice, -1);
            slice[MAX_FOLDS] = 0;
        }
        return maximumPoints(0, -1, 0);
    }

    List<Integer>[] buildAdj(int n, int[][] edges) {
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
    
    int maximumPoints(int r, int p, int folds){
        if(cache[r][folds] == -1){
            int noFold = foldedCoins[r][folds] - k;
            for(int c : adj[r]){
                if(c != p){
                    noFold += maximumPoints(c, r, folds);
                }
            }
            int folded = foldedCoins[r][folds + 1];
            for(int c : adj[r]){
                if(c != p){
                    folded += maximumPoints(c, r, folds + 1);
                }
            }    
            cache[r][folds] = Math.max(noFold, folded);
        }
        return cache[r][folds];
        
    }
}
// @lc code=end
