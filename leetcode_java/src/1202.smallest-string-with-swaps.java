/*
 * @lc app=leetcode id=1202 lang=java
 *
 * [1202] Smallest String With Swaps
 *
 * https://leetcode.com/problems/smallest-string-with-swaps/description/
 *
 * algorithms
 * Medium (30.62%)
 * Total Accepted:    1.7K
 * Total Submissions: 5.5K
 * Testcase Example:  '"dcab"\n[[0,3],[1,2]]'
 *
 * You are given a string s, and an array of pairs of indices in the string
 * pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 * 
 * You can swap the characters at any pair of indices in the given pairs any
 * number of times.
 * 
 * Return the lexicographically smallest string that s can be changed to after
 * using the swaps.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination: 
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination: 
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 * 
 * Example 3:
 * 
 * 
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination: 
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s only contains lower case English letters.
 * 
 * 
 */
class Solution {
	List<Integer>[]	adj;
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
    	char[] chars = s.toCharArray();
        adj = buildAdjList(s.length(), pairs);
        boolean[] visited = new boolean[s.length()];
        for (int u = 0; u < s.length(); u++) {
        	if(!adj[u].isEmpty() && !visited[u]){
        		List<Integer> group = new ArrayList<>();
        		dfs(u, visited, group);
        		Collections.sort(group);
        		int[] cnt = new int[26];
        		for (int i : group) {
        			cnt[chars[i] - 'a']++;
        		}
        		int j = 0;
        		for (int i = 0; i < cnt.length; i++) {
        			while(cnt[i]!= 0){
        				chars[group.get(j)] = (char)('a' + i);
        				cnt[i]--;
        				j++;
        			}
        		}
        	}
        }
        return new String(chars);
    }

    List<Integer>[] buildAdjList(int n, List<List<Integer>> pairs){
    	List<Integer>[] ans = new List[n];
    	for(int i = 0; i < n; i++){
    		ans[i] = new ArrayList<>();
    	}
    	for (List<Integer> pair: pairs) {
    		ans[pair.get(0)].add(pair.get(1));
    		ans[pair.get(1)].add(pair.get(0));
    	}
    	return ans;
    }

    void dfs(int u, boolean[] visited, List<Integer> pos){
    	visited[u] = true;
    	pos.add(u);
    	for (int v : adj[u]) {
    		if(!visited[v]){
    			dfs(v, visited, pos);
    		}
    	}
    }
}
