/*
 * @lc app=leetcode id=2246 lang=java
 *
 * [2246] Longest Path With Different Adjacent Characters
 *
 * https://leetcode.com/problems/longest-path-with-different-adjacent-characters/description/
 *
 * algorithms
 * Hard (41.79%)
 * Likes:    223
 * Dislikes: 4
 * Total Accepted:    4.9K
 * Total Submissions: 11.7K
 * Testcase Example:  '[-1,0,0,1,1,2]\n"abacbe"'
 *
 * You are given a tree (i.e. a connected, undirected graph that has no cycles)
 * rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is
 * represented by a 0-indexed array parent of size n, where parent[i] is the
 * parent of node i. Since node 0 is the root, parent[0] == -1.
 * 
 * You are also given a string s of length n, where s[i] is the character
 * assigned to node i.
 * 
 * Return the length of the longest path in the tree such that no pair of
 * adjacent nodes on the path have the same character assigned to them.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: parent = [-1,0,0,1,1,2], s = "abacbe"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different
 * characters in the tree is the path: 0 -> 1 -> 3. The length of this path is
 * 3, so 3 is returned.
 * It can be proven that there is no longer path that satisfies the
 * conditions. 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: parent = [-1,0,0,0], s = "aabc"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different
 * characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is
 * returned.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == parent.length == s.length
 * 1 <= n <= 10^5
 * 0 <= parent[i] <= n - 1 for all i >= 1
 * parent[0] == -1
 * parent represents a valid tree.
 * s consists of only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    List<Integer>[] adj;
    char[] chars;
    int ans = 0;
    public int longestPath(int[] parent, String s) {
        chars = s.toCharArray();
        adj = buildAdj(parent);
        dfs(0, -1);
        return ans;
    }

    List<Integer>[] buildAdj(int[] parent){
        List<Integer>[] ret = new List[parent.length];
        for(int i = 0; i < parent.length; i++){
            ret[i] = new ArrayList<>();
        }
        for(int i = 1; i < parent.length; i++){
            ret[parent[i]].add(i);
        }
        return ret;
    }

    int dfs(int u, int p){
        int max = 1;
        for(int v : adj[u]){
            if(v != p){
                int ret = dfs(v, u);;
                if(chars[v] == chars[u]){
                    ret = 0;    
                }
                ans = Math.max(ret + max, ans);
                max = Math.max(ret + 1, max);
            }
        }
        ans = Math.max(max, ans);
        // System.out.println(u + "," + max + "," + ans);
        return max;
    }
}
// @lc code=end
