/*
 * @lc app=leetcode id=2646 lang=java
 *
 * [2646] Minimize the Total Price of the Trips
 *
 * https://leetcode.com/problems/minimize-the-total-price-of-the-trips/description/
 *
 * algorithms
 * Hard (25.56%)
 * Likes:    68
 * Dislikes: 7
 * Total Accepted:    1.6K
 * Total Submissions: 6.1K
 * Testcase Example:  '4\n[[0,1],[1,2],[1,3]]\n[2,2,10,6]\n[[0,3],[2,1],[2,3]]'
 *
 * There exists an undirected and unrooted tree with n nodes indexed from 0 to
 * n - 1. You are given the integer n and a 2D integer array edges of length n
 * - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes
 * ai and bi in the tree.
 * 
 * Each node has an associated price. You are given an integer array price,
 * where price[i] is the price of the i^th node.
 * 
 * The price sum of a given path is the sum of the prices of all nodes lying on
 * that path.
 * 
 * Additionally, you are given a 2D integer array trips, where trips[i] =
 * [starti, endi] indicates that you start the i^th trip from the node starti
 * and travel to the node endi by any path you like.
 * 
 * Before performing your first trip, you can choose some non-adjacent nodes
 * and halve the prices.
 * 
 * Return the minimum total price sum to perform all the given trips.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips =
 * [[0,3],[2,1],[2,3]]
 * Output: 23
 * Explanation: The diagram above denotes the tree after rooting it at node 2.
 * The first part shows the initial tree and the second part shows the tree
 * after choosing nodes 0, 2, and 3, and making their price half.
 * For the 1^st trip, we choose path [0,1,3]. The price sum of that path is 1 +
 * 2 + 3 = 6.
 * For the 2^nd trip, we choose path [2,1]. The price sum of that path is 2 + 5
 * = 7.
 * For the 3^rd trip, we choose path [2,1,3]. The price sum of that path is 5 +
 * 2 + 3 = 10.
 * The total price sum of all trips is 6 + 7 + 10 = 23.
 * It can be proven, that 23 is the minimum answer that we can achieve.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
 * Output: 1
 * Explanation: The diagram above denotes the tree after rooting it at node 0.
 * The first part shows the initial tree and the second part shows the tree
 * after choosing node 0, and making its price half.
 * For the 1^st trip, we choose path [0]. The price sum of that path is 1.
 * The total price sum of all trips is 1. It can be proven, that 1 is the
 * minimum answer that we can achieve.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 50
 * edges.length == n - 1
 * 0 <= ai, bi <= n - 1
 * edges represents a valid tree.
 * price.length == n
 * price[i] is an even integer.
 * 1 <= price[i] <= 1000
 * 1 <= trips.length <= 100
 * 0 <= starti, endi <= n - 1
 * 
 * 
 */

// @lc code=start
class Solution {
    List<Integer>[] adj;
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        adj = new List[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int[] e: edges){
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        int[] cnt = new int[n];
        for(int[] t : trips){
            dfs(t[0], t[1], -1, cnt);
        }
        // System.out.println(Arrays.toString(cnt));
        int[] ret = minTotol(0, -1, price, cnt);
        return Math.min(ret[0], ret[1]);
    }

    boolean dfs(int u, int t, int p, int[] cnt){
        if(u == t){
            cnt[u]++;
            return true;
        }
        for(int v : adj[u]){
            if(v != p){
                if(dfs(v, t, u, cnt)){
                    cnt[u]++;
                    return true;
                }
            }
        }
        return false;
    }

    int[] minTotol(int u, int p, int[] price, int[] cnt){
        int[] ret = new int[2];
        ret[0] += price[u] / 2 * cnt[u];
        ret[1] += price[u] * cnt[u];
        for(int v : adj[u]){
            if(v != p){
                int[] r = minTotol(v, u, price, cnt);
                ret[0] += r[1];
                ret[1] += Math.min(r[0], r[1]);
            }
        }
        // System.out.println(Arrays.asList(u, ret[0], ret[1]));
        return ret;
    }
}
// @lc code=end
