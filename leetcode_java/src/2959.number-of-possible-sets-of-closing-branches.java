/*
 * @lc app=leetcode id=2959 lang=java
 *
 * [2959] Number of Possible Sets of Closing Branches
 *
 * https://leetcode.com/problems/number-of-possible-sets-of-closing-branches/description/
 *
 * algorithms
 * Hard (49.35%)
 * Likes:    167
 * Dislikes: 14
 * Total Accepted:    7.6K
 * Total Submissions: 15.1K
 * Testcase Example:  '3\n5\n[[0,1,2],[1,2,10],[0,2,10]]'
 *
 * There is a company with n branches across the country, some of which are
 * connected by roads. Initially, all branches are reachable from each other by
 * traveling some roads.
 * 
 * The company has realized that they are spending an excessive amount of time
 * traveling between their branches. As a result, they have decided to close
 * down some of these branches (possibly none). However, they want to ensure
 * that the remaining branches have a distance of at most maxDistance from each
 * other.
 * 
 * The distance between two branches is the minimum total traveled length
 * needed to reach one branch from another.
 * 
 * You are given integers n, maxDistance, and a 0-indexed 2D array roads, where
 * roads[i] = [ui, vi, wi] represents the undirected road between branches ui
 * and vi with length wi.
 * 
 * Return the number of possible sets of closing branches, so that any branch
 * has a distance of at most maxDistance from any other.
 * 
 * Note that, after closing a branch, the company will no longer have access to
 * any roads connected to it.
 * 
 * Note that, multiple roads are allowed.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, maxDistance = 5, roads = [[0,1,2],[1,2,10],[0,2,10]]
 * Output: 5
 * Explanation: The possible sets of closing branches are:
 * - The set [2], after closing, active branches are [0,1] and they are
 * reachable to each other within distance 2.
 * - The set [0,1], after closing, the active branch is [2].
 * - The set [1,2], after closing, the active branch is [0].
 * - The set [0,2], after closing, the active branch is [1].
 * - The set [0,1,2], after closing, there are no active branches.
 * It can be proven, that there are only 5 possible sets of closing branches.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 3, maxDistance = 5, roads = [[0,1,20],[0,1,10],[1,2,2],[0,2,2]]
 * Output: 7
 * Explanation: The possible sets of closing branches are:
 * - The set [], after closing, active branches are [0,1,2] and they are
 * reachable to each other within distance 4.
 * - The set [0], after closing, active branches are [1,2] and they are
 * reachable to each other within distance 2.
 * - The set [1], after closing, active branches are [0,2] and they are
 * reachable to each other within distance 2.
 * - The set [0,1], after closing, the active branch is [2].
 * - The set [1,2], after closing, the active branch is [0].
 * - The set [0,2], after closing, the active branch is [1].
 * - The set [0,1,2], after closing, there are no active branches.
 * It can be proven, that there are only 7 possible sets of closing branches.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 1, maxDistance = 10, roads = []
 * Output: 2
 * Explanation: The possible sets of closing branches are:
 * - The set [], after closing, the active branch is [0].
 * - The set [0], after closing, there are no active branches.
 * It can be proven, that there are only 2 possible sets of closing
 * branches.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10
 * 1 <= maxDistance <= 10^5
 * 0 <= roads.length <= 1000
 * roads[i].length == 3
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 1 <= wi <= 1000
 * All branches are reachable from each other by traveling some roads.
 * 
 * 
 */

// @lc code=start
class Solution {
    int MAX = 0;
    int[][] weights;
    int N;
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        MAX = maxDistance + 5;
        N = n;
        weights = new int[n][n];
        fill(weights, MAX);
        for(int[] r : roads){
            weights[r[0]][r[1]] = Math.min(weights[r[0]][r[1]], r[2]);
            weights[r[1]][r[0]] = Math.min(weights[r[1]][r[0]], r[2]);
        }

        int ans = 0;
        for(int i = 0; i < (1 << n); i++){
            if(checkShortestPaths(i, maxDistance)){
                ans++;
            }
        }
        return ans;
    }

    void fill(int[][] arr, int d) {
        for (int[] ds : arr) {
            Arrays.fill(ds, d);
        }
    }

    boolean checkShortestPaths(int bitmap, int maxDistance) {
        int[][] distances = new int[N][N];
        fill(distances, MAX);
        copyIfSet(weights, distances, bitmap);
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }
        }
        // System.out.println(bitmap);
        // System.out.println(Arrays.deepToString(distances));
        for(int i = 0; i < N; i++){
            if(((bitmap >> i) & 1) == 1){
                for(int j = 0; j < N; j++){
                    if(i != j && ((bitmap >> j) & 1) == 1){
                        if(distances[i][j] > maxDistance){
                            return false;
                        }
                    }
                }
            }
        }
        // System.out.println(bitmap);
        return true;
    }

    void copyIfSet(int[][] src, int[][] dest, int bitmap) {
        for (int i = 0; i < src.length; i++) {
            if(((bitmap >> i) & 1) == 1){
                for (int j = 0; j < src[i].length; j++) {
                    if(((bitmap >> j) & 1) == 1){
                        dest[i][j] = src[i][j];
                    }
                }    
            }
        }
    }
}
// @lc code=end
