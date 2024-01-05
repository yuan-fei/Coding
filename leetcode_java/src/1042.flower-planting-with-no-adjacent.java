/*
 * @lc app=leetcode id=1042 lang=java
 *
 * [1042] Flower Planting With No Adjacent
 *
 * https://leetcode.com/problems/flower-planting-with-no-adjacent/description/
 *
 * algorithms
 * Medium (50.91%)
 * Likes:    1372
 * Dislikes: 711
 * Total Accepted:    74K
 * Total Submissions: 145K
 * Testcase Example:  '3\n[[1,2],[2,3],[3,1]]'
 *
 * You have n gardens, labeled from 1 to n, and an array paths where paths[i] =
 * [xi, yi] describes a bidirectional path between garden xi to garden yi. In
 * each garden, you want to plant one of 4 types of flowers.
 * 
 * All gardens have at most 3 paths coming into or leaving it.
 * 
 * Your task is to choose a flower type for each garden such that, for any two
 * gardens connected by a path, they have different types of flowers.
 * 
 * Return any such a choice as an array answer, where answer[i] is the type of
 * flower planted in the (i+1)^th garden. The flower types are denoted 1, 2, 3,
 * or 4. It is guaranteed an answer exists.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, paths = [[1,2],[2,3],[3,1]]
 * Output: [1,2,3]
 * Explanation:
 * Gardens 1 and 2 have different types.
 * Gardens 2 and 3 have different types.
 * Gardens 3 and 1 have different types.
 * Hence, [1,2,3] is a valid answer. Other valid answers include [1,2,4],
 * [1,4,2], and [3,2,1].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 4, paths = [[1,2],[3,4]]
 * Output: [1,2,1,2]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * Output: [1,2,3,4]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^4
 * 0 <= paths.length <= 2 * 10^4
 * paths[i].length == 2
 * 1 <= xi, yi <= n
 * xi != yi
 * Every garden has at most 3 paths coming into or leaving it.
 * 
 * 
 */

// @lc code=start
class Solution {
    List<Integer>[] adj;
    public int[] gardenNoAdj(int n, int[][] paths) {
        adj = new List[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int[] p : paths){
            adj[p[0] - 1].add(p[1] - 1);
            adj[p[1] - 1].add(p[0] - 1);
        }
        int[] colors = new int[n];
        for(int i = 0; i < n; i++){
            if(colors[i] == 0){
                dfs(i, colors);
            }
        }
        return colors;
    }

    void dfs(int i, int[] colors){
        boolean[] seen = new boolean[4];
        for(int j : adj[i]){
            if(colors[j] > 0){
                seen[colors[j] - 1] = true;
            }
        }
        for(int c = 0; c < 4; c++){
            if(!seen[c]){
                colors[i] = c + 1;
                break;
            }
        }
        for(int j : adj[i]){
            if(colors[j] == 0){
                dfs(j, colors);
            }
        }
    }

}
// @lc code=end
