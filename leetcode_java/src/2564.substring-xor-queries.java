/*
 * @lc app=leetcode id=2564 lang=java
 *
 * [2564] Substring XOR Queries
 *
 * https://leetcode.com/problems/substring-xor-queries/description/
 *
 * algorithms
 * Medium (27.00%)
 * Likes:    57
 * Dislikes: 42
 * Total Accepted:    3.9K
 * Total Submissions: 14.7K
 * Testcase Example:  '"101101"\n[[0,5],[1,2]]'
 *
 * You are given a binary string s, and a 2D integer array queries where
 * queries[i] = [firsti, secondi].
 * 
 * For the i^th query, find the shortest substring of s whose decimal value,
 * val, yields secondi when bitwise XORed with firsti. In other words, val ^
 * firsti == secondi.
 * 
 * The answer to the i^th query is the endpoints (0-indexed) of the substring
 * [lefti, righti] or [-1, -1] if no such substring exists. If there are
 * multiple answers, choose the one with the minimum lefti.
 * 
 * Return an array ans where ans[i] = [lefti, righti] is the answer to the i^th
 * query.
 * 
 * A substring is a contiguous non-empty sequence of characters within a
 * string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "101101", queries = [[0,5],[1,2]]
 * Output: [[0,2],[2,3]]
 * Explanation: For the first query the substring in range [0,2] is "101" which
 * has a decimal value of 5, and 5 ^ 0 = 5, hence the answer to the first query
 * is [0,2]. In the second query, the substring in range [2,3] is "11", and has
 * a decimal value of 3, and 3 ^ 1 = 2. So, [2,3] is returned for the second
 * query. 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "0101", queries = [[12,8]]
 * Output: [[-1,-1]]
 * Explanation: In this example there is no substring that answers the query,
 * hence [-1,-1] is returned.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "1", queries = [[4,5]]
 * Output: [[0,0]]
 * Explanation: For this example, the substring in range [0,0] has a decimal
 * value of 1, and 1 ^ 4 = 5. So, the answer is [0,0].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^4
 * s[i] is either '0' or '1'.
 * 1 <= queries.length <= 10^5
 * 0 <= firsti, secondi <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[][] substringXorQueries(String s, int[][] queries) {
        Map<Integer, int[]> m = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '0'){
                m.putIfAbsent(0, new int[]{i, i});
            }
            else{
                int v = 0;
                for(int j = i; j < Math.min(i + 31, s.length()); j++){
                    v <<= 1;
                    v += s.charAt(j) - '0';
                    m.putIfAbsent(v, new int[]{i, j});
                }    
            }
        }
        // System.out.println(m);
        int[][] ans = new int[queries.length][];
        for(int i = 0; i < queries.length; i++){
            ans[i] = m.getOrDefault(queries[i][0] ^ queries[i][1], new int[]{-1, -1});
        }
        return ans;
    }
}
// @lc code=end
