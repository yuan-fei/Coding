/*
 * @lc app=leetcode id=839 lang=java
 *
 * [839] Similar String Groups
 *
 * https://leetcode.com/problems/similar-string-groups/description/
 *
 * algorithms
 * Hard (47.86%)
 * Likes:    1035
 * Dislikes: 182
 * Total Accepted:    63.2K
 * Total Submissions: 132.1K
 * Testcase Example:  '["tars","rats","arts","star"]'
 *
 * Two strings X and Y are similar if we can swap two letters (in different
 * positions) of X, so that it equals Y. Also two strings X and Y are similar
 * if they are equal.
 * 
 * For example, "tars" and "rats" are similar (swapping at positions 0 and 2),
 * and "rats" and "arts" are similar, but "star" is not similar to "tars",
 * "rats", or "arts".
 * 
 * Together, these form two connected groups by similarity: {"tars", "rats",
 * "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group
 * even though they are not similar.  Formally, each group is such that a word
 * is in the group if and only if it is similar to at least one other word in
 * the group.
 * 
 * We are given a list strs of strings where every string in strs is an anagram
 * of every other string in strs. How many groups are there?
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: strs = ["tars","rats","arts","star"]
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: strs = ["omv","ovm"]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= strs.length <= 300
 * 1 <= strs[i].length <= 300
 * strs[i] consists of lowercase letters only.
 * All words in strs have the same length and are anagrams of each other.
 * 
 * 
 */

// @lc code=start
class Solution {
    List<Integer>[] adj;
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        adj = new List[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(isSameGroup(strs[i], strs[j])){
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        boolean[] seen = new boolean[n];
        int ret = 0;
        for(int i = 0; i < n; i++){
            if(!seen[i]){
                ret++;
                dfs(i, seen);
            }
        }
        return ret;
    }

    boolean isSameGroup(String a, String b){
        int diff1 = -1;
        int diff2 = -1;
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i)){
                if(diff1 == -1){
                    diff1 = i;
                }
                else if(diff2 == -1){
                    diff2 = i;
                }
                else{
                    return false;
                }
            }
        }
        return diff1 == -1 || a.charAt(diff1) == b.charAt(diff2) && a.charAt(diff2) == b.charAt(diff1);
    }

    void dfs(int u, boolean[] seen){
        seen[u] = true;
        for(int v : adj[u]){
            if(!seen[v]){
                dfs(v, seen);    
            }
        }
    }
}
// @lc code=end
