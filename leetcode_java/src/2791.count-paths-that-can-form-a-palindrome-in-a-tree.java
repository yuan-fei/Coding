/*
 * @lc app=leetcode id=2791 lang=java
 *
 * [2791] Count Paths That Can Form a Palindrome in a Tree
 *
 * https://leetcode.com/problems/count-paths-that-can-form-a-palindrome-in-a-tree/description/
 *
 * algorithms
 * Hard (44.89%)
 * Likes:    356
 * Dislikes: 4
 * Total Accepted:    4.8K
 * Total Submissions: 10.6K
 * Testcase Example:  '[-1,0,0,1,1,2]\n"acaabc"'
 *
 * You are given a tree (i.e. a connected, undirected graph that has no cycles)
 * rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is
 * represented by a 0-indexed array parent of size n, where parent[i] is the
 * parent of node i. Since node 0 is the root, parent[0] == -1.
 * 
 * You are also given a string s of length n, where s[i] is the character
 * assigned to the edge between i and parent[i]. s[0] can be ignored.
 * 
 * Return the number of pairs of nodes (u, v) such that u < v and the
 * characters assigned to edges on the path from u to v can be rearranged to
 * form a palindrome.
 * 
 * A string is a palindrome when it reads the same backwards as forwards.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: parent = [-1,0,0,1,1,2], s = "acaabc"
 * Output: 8
 * Explanation: The valid pairs are:
 * - All the pairs (0,1), (0,2), (1,3), (1,4) and (2,5) result in one character
 * which is always a palindrome.
 * - The pair (2,3) result in the string "aca" which is a palindrome.
 * - The pair (1,5) result in the string "cac" which is a palindrome.
 * - The pair (3,5) result in the string "acac" which can be rearranged into
 * the palindrome "acca".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: parent = [-1,0,0,0,0], s = "aaaaa"
 * Output: 10
 * Explanation: Any pair of nodes (u,v) where u < v is valid.
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
    Map<Integer, Integer> patternToCnt = new HashMap<>();
    String s;
    long ans = 0;
    public long countPalindromePaths(List<Integer> parent, String s) {
        this.s = s;
        int n = parent.size();
        adj = new List[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 1; i < n; i++){
            adj[parent.get(i)].add(i);
        }
        countPalindromePaths(0, 0);
        return ans;
    }

    void countPalindromePaths(int root, int pattern) {
        ans += patternToCnt.getOrDefault(pattern, 0);
        for(int i = 0; i < 26; i++){
            int newPattern = pattern ^ (1 << i);
            ans += patternToCnt.getOrDefault(newPattern, 0);
        }
        patternToCnt.put(pattern, patternToCnt.getOrDefault(pattern, 0) + 1);
        for(int c : adj[root]){
            countPalindromePaths(c, pattern ^ (1 <<(s.charAt(c) - 'a')));
        }
    }
}
// @lc code=end
