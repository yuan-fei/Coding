/*
 * @lc app=leetcode id=2492 lang=java
 *
 * [2492] Minimum Score of a Path Between Two Cities
 *
 * https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/description/
 *
 * algorithms
 * Medium (39.43%)
 * Likes:    54
 * Dislikes: 15
 * Total Accepted:    5.9K
 * Total Submissions: 15K
 * Testcase Example:  '4\n[[1,2,9],[2,3,6],[2,4,5],[1,4,7]]'
 *
 * You are given a positive integer n representing n cities numbered from 1 to
 * n. You are also given a 2D array roads where roads[i] = [ai, bi, distancei]
 * indicates that there is a bidirectional road between cities ai and bi with a
 * distance equal to distancei. The cities graph is not necessarily connected.
 * 
 * The score of a path between two cities is defined as the minimum distance of
 * a road in this path.
 * 
 * Return the minimum possible score of a path between cities 1 and n.
 * 
 * Note:
 * 
 * 
 * A path is a sequence of roads between two cities.
 * It is allowed for a path to contain the same road multiple times, and you
 * can visit cities 1 and n multiple times along the path.
 * The test cases are generated such that there is at least one path between 1
 * and n.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
 * Output: 5
 * Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 ->
 * 4. The score of this path is min(9,5) = 5.
 * It can be shown that no other path has less score.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
 * Output: 2
 * Explanation: The path from city 1 to 4 with the minimum score is: 1 -> 2 ->
 * 1 -> 3 -> 4. The score of this path is min(2,2,4,7) = 2.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 10^5
 * 1 <= roads.length <= 10^5
 * roads[i].length == 3
 * 1 <= ai, bi <= n
 * ai != bi
 * 1 <= distancei <= 10^4
 * There are no repeated edges.
 * There is at least one path between 1 and n.
 * 
 * 
 */

// @lc code=start
class Solution {
    List<Integer>[] adj;
    int[][] roads;
    public int minScore(int n, int[][] roads) {
        this.roads = roads;
        adj = new List[n + 1];
        for(int i = 0; i <= n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < roads.length; i++){
            adj[roads[i][0]].add(i);
            adj[roads[i][1]].add(i);
        }
        return dfs(1, new boolean[n + 1]);
    }

    int dfs(int u, boolean[] seen){
        seen[u] = true;
        int ret = Integer.MAX_VALUE;
        for(int e : adj[u]){
            int v = roads[e][0] + roads[e][1] - u;
            ret = Math.min(roads[e][2], ret);
            if(!seen[v]){
                ret = Math.min(dfs(v, seen), ret);
            }
        }
        return ret;
    }
}
// @lc code=end
