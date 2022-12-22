/*
 * @lc app=leetcode id=2508 lang=java
 *
 * [2508] Add Edges to Make Degrees of All Nodes Even
 *
 * https://leetcode.com/problems/add-edges-to-make-degrees-of-all-nodes-even/description/
 *
 * algorithms
 * Hard (26.30%)
 * Likes:    83
 * Dislikes: 22
 * Total Accepted:    3.9K
 * Total Submissions: 15K
 * Testcase Example:  '5\n[[1,2],[2,3],[3,4],[4,2],[1,4],[2,5]]'
 *
 * There is an undirected graph consisting of n nodes numbered from 1 to n. You
 * are given the integer n and a 2D array edges where edges[i] = [ai, bi]
 * indicates that there is an edge between nodes ai and bi. The graph can be
 * disconnected.
 * 
 * You can add at most two additional edges (possibly none) to this graph so
 * that there are no repeated edges and no self-loops.
 * 
 * Return true if it is possible to make the degree of each node in the graph
 * even, otherwise return false.
 * 
 * The degree of a node is the number of edges connected to it.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 5, edges = [[1,2],[2,3],[3,4],[4,2],[1,4],[2,5]]
 * Output: true
 * Explanation: The above diagram shows a valid way of adding an edge.
 * Every node in the resulting graph is connected to an even number of edges.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 4, edges = [[1,2],[3,4]]
 * Output: true
 * Explanation: The above diagram shows a valid way of adding two edges.
 * 
 * Example 3:
 * 
 * 
 * Input: n = 4, edges = [[1,2],[1,3],[1,4]]
 * Output: false
 * Explanation: It is not possible to obtain a valid graph with adding at most
 * 2 edges.
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= n <= 10^5
 * 2 <= edges.length <= 10^5
 * edges[i].length == 2
 * 1 <= ai, bi <= n
 * ai != bi
 * There are no repeated edges.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {
        Set<Integer>[] adj = new Set[n + 1];
        for(int i = 0; i < adj.length; i++){
            adj[i] = new HashSet<>();
        }
        for(List<Integer> e : edges){
            adj[e.get(0)].add(e.get(1));
            adj[e.get(1)].add(e.get(0));
        }
        List<Integer> oddDegreeNodes = new ArrayList<>();
        for(int i = 1; i < adj.length; i++){
            if(adj[i].size() % 2 != 0){
                oddDegreeNodes.add(i);
            }
        }
        // System.out.println(oddDegreeNodes);
        if(oddDegreeNodes.size() == 0){
            return true;
        }
        else if(oddDegreeNodes.size() == 2){
            for(int i = 1; i < adj.length; i++){
                if(!adj[i].contains(oddDegreeNodes.get(0)) && !adj[i].contains(oddDegreeNodes.get(1))){
                    return true;
                }
            }
            return false;
        }
        else if(oddDegreeNodes.size() == 4){
            for(int i = 1; i < 4; i++){
                Collections.swap(oddDegreeNodes, 1, i);
                if(!adj[oddDegreeNodes.get(0)].contains(oddDegreeNodes.get(1)) && !adj[oddDegreeNodes.get(2)].contains(oddDegreeNodes.get(3))){
                    return true;
                }
                Collections.swap(oddDegreeNodes, 1, i);
            }
        }
        return false;

    }
}
// @lc code=end
