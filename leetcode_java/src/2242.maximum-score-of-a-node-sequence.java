/*
 * @lc app=leetcode id=2242 lang=java
 *
 * [2242] Maximum Score of a Node Sequence
 *
 * https://leetcode.com/problems/maximum-score-of-a-node-sequence/description/
 *
 * algorithms
 * Hard (28.98%)
 * Likes:    201
 * Dislikes: 2
 * Total Accepted:    2.9K
 * Total Submissions: 10K
 * Testcase Example:  '[5,2,9,8,4]\n[[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]'
 *
 * There is an undirected graph with n nodes, numbered from 0 to n - 1.
 * 
 * You are given a 0-indexed integer array scores of length n where scores[i]
 * denotes the score of node i. You are also given a 2D integer array edges
 * where edges[i] = [ai, bi] denotes that there exists an undirected edge
 * connecting nodes ai and bi.
 * 
 * A node sequence is valid if it meets the following conditions:
 * 
 * 
 * There is an edge connecting every pair of adjacent nodes in the
 * sequence.
 * No node appears more than once in the sequence.
 * 
 * 
 * The score of a node sequence is defined as the sum of the scores of the
 * nodes in the sequence.
 * 
 * Return the maximum score of a valid node sequence with a length of 4. If no
 * such sequence exists, return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: scores = [5,2,9,8,4], edges = [[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]
 * Output: 24
 * Explanation: The figure above shows the graph and the chosen node sequence
 * [0,1,2,3].
 * The score of the node sequence is 5 + 2 + 9 + 8 = 24.
 * It can be shown that no other node sequence has a score of more than 24.
 * Note that the sequences [3,1,2,0] and [1,0,2,3] are also valid and have a
 * score of 24.
 * The sequence [0,3,2,4] is not valid since no edge connects nodes 0 and 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: scores = [9,20,6,4,11,12], edges = [[0,3],[5,3],[2,4],[1,3]]
 * Output: -1
 * Explanation: The figure above shows the graph.
 * There are no valid node sequences of length 4, so we return -1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == scores.length
 * 4 <= n <= 5 * 10^4
 * 1 <= scores[i] <= 10^8
 * 0 <= edges.length <= 5 * 10^4
 * edges[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * There are no duplicate edges.
 * 
 * 
 */

// @lc code=start
class Solution {
    List<Integer>[] adj;
    public int maximumScore(int[] scores, int[][] edges) {
        adj = buildAdj(scores.length, edges);
        for(int i = 0; i < scores.length; i++){
            Collections.sort(adj[i], (a, b) -> Integer.compare(scores[b], scores[a]));
        }
        System.out.println(Arrays.deepToString(adj));
        int ans = -1;
        for(int i = 0; i < scores.length; i++){
            for(int j = 0; j < Math.min(3, adj[i].size()); j++){
                int u = adj[i].get(j);
                for(int k = 0; k < adj[i].size(); k++){
                    if(k != j){
                        int v = adj[i].get(k);
                        for(int l = 0; l < Math.min(3, adj[v].size()); l++){
                            int w = adj[v].get(l);
                            if(w != u && w != i){
                                ans = Math.max(ans, scores[i] + scores[u] + scores[v] + scores[w]);
                                // System.out.println(Arrays.asList(i, u, v, w, ans));
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }

    List<Integer>[] buildAdj(int n, int[][] edges){
        List<Integer>[] ret = new List[n];
        for(int i = 0; i < n; i++){
            ret[i] = new ArrayList<>();
        }
        for(int i = 0; i < edges.length; i++){
            ret[edges[i][0]].add(edges[i][1]);
            ret[edges[i][1]].add(edges[i][0]);
        }
        return ret;
    }
}
// @lc code=end
