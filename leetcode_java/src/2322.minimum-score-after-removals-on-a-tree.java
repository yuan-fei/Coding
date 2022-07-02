/*
 * @lc app=leetcode id=2322 lang=java
 *
 * [2322] Minimum Score After Removals on a Tree
 *
 * https://leetcode.com/problems/minimum-score-after-removals-on-a-tree/description/
 *
 * algorithms
 * Hard (44.97%)
 * Likes:    141
 * Dislikes: 5
 * Total Accepted:    2.3K
 * Total Submissions: 5.1K
 * Testcase Example:  '[1,5,5,4,11]\n[[0,1],[1,2],[1,3],[3,4]]'
 *
 * There is an undirected connected tree with n nodes labeled from 0 to n - 1
 * and n - 1 edges.
 * 
 * You are given a 0-indexed integer array nums of length n where nums[i]
 * represents the value of the i^th node. You are also given a 2D integer array
 * edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an
 * edge between nodes ai and bi in the tree.
 * 
 * Remove two distinct edges of the tree to form three connected components.
 * For a pair of removed edges, the following steps are defined:
 * 
 * 
 * Get the XOR of all the values of the nodes for each of the three components
 * respectively.
 * The difference between the largest XOR value and the smallest XOR value is
 * the score of the pair.
 * 
 * 
 * 
 * For example, say the three components have the node values: [4,5,7], [1,9],
 * and [3,3,3]. The three XOR values are 4 ^ 5 ^ 7 = 6, 1 ^ 9 = 8, and 3 ^ 3 ^
 * 3 = 3. The largest XOR value is 8 and the smallest XOR value is 3. The score
 * is then 8 - 3 = 5.
 * 
 * 
 * Return the minimum score of any possible pair of edge removals on the given
 * tree.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,5,5,4,11], edges = [[0,1],[1,2],[1,3],[3,4]]
 * Output: 9
 * Explanation: The diagram above shows a way to make a pair of removals.
 * - The 1^st component has nodes [1,3,4] with values [5,4,11]. Its XOR value
 * is 5 ^ 4 ^ 11 = 10.
 * - The 2^nd component has node [0] with value [1]. Its XOR value is 1 = 1.
 * - The 3^rd component has node [2] with value [5]. Its XOR value is 5 = 5.
 * The score is the difference between the largest and smallest XOR value which
 * is 10 - 1 = 9.
 * It can be shown that no other pair of removals will obtain a smaller score
 * than 9.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [5,5,2,4,4,2], edges = [[0,1],[1,2],[5,2],[4,3],[1,3]]
 * Output: 0
 * Explanation: The diagram above shows a way to make a pair of removals.
 * - The 1^st component has nodes [3,4] with values [4,4]. Its XOR value is 4 ^
 * 4 = 0.
 * - The 2^nd component has nodes [1,0] with values [5,5]. Its XOR value is 5 ^
 * 5 = 0.
 * - The 3^rd component has nodes [2,5] with values [2,2]. Its XOR value is 2 ^
 * 2 = 0.
 * The score is the difference between the largest and smallest XOR value which
 * is 0 - 0 = 0.
 * We cannot obtain a smaller score than 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length
 * 3 <= n <= 1000
 * 1 <= nums[i] <= 10^8
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges represents a valid tree.
 * 
 * 
 */

// @lc code=start
class Solution {
    Set<Integer>[] adj;
    int minScore = Integer.MAX_VALUE;
    int[] nums;
    public int minimumScore(int[] nums, int[][] edges) {
        this.nums = nums;
        int n = nums.length;
        adj = new Set[n];
        for(int i = 0; i < n; i++){
            adj[i] = new HashSet<>();
        }
        for(int[] e : edges){
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        int xor = totalXor(0, -1);
        for(int[] e : edges){
            adj[e[0]].remove(e[1]);
            adj[e[1]].remove(e[0]);
            int totalXor = totalXor(e[0], -1);
            dfs(e[0], -1, totalXor, xor ^ totalXor);
            dfs(e[1], -1, xor ^ totalXor, totalXor);
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        return minScore;
    }

    int totalXor(int r, int p){
        int ret = nums[r];
        for(int c : adj[r]){
            if(c != p){
                ret ^= totalXor(c, r);    
            }
        }
        return ret;
    }

    int dfs(int r, int p, int totalXor, int xor){
        int ret = nums[r];
        for(int c : adj[r]){
            if(c != p){
                int cRet = dfs(c, r, totalXor, xor);
                int max = Math.max(Math.max(xor, cRet), totalXor ^ cRet);
                int min = Math.min(Math.min(xor, cRet), totalXor ^ cRet);
                minScore = Math.min(minScore, max - min);
                // System.out.println(c + " , " + r + ", " + xor + ", " + (max - min) + "=" + minScore);
                ret ^= cRet;
            }
        }
        return ret;
    }
}
// @lc code=end
