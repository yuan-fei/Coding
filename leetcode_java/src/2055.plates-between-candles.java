/*
 * @lc app=leetcode id=2055 lang=java
 *
 * [2055] Plates Between Candles
 *
 * https://leetcode.com/problems/plates-between-candles/description/
 *
 * algorithms
 * Medium (46.95%)
 * Likes:    87
 * Dislikes: 2
 * Total Accepted:    2.3K
 * Total Submissions: 5K
 * Testcase Example:  '"**|**|***|"\n[[2,5],[5,9]]'
 *
 * There is a long table with a line of plates and candles arranged on top of
 * it. You are given a 0-indexed string s consisting of characters '*' and '|'
 * only, where a '*' represents a plate and a '|' represents a candle.
 * 
 * You are also given a 0-indexed 2D integer array queries where queries[i] =
 * [lefti, righti] denotes the substring s[lefti...righti] (inclusive). For
 * each query, you need to find the number of plates between candles that are
 * in the substring. A plate is considered between candles if there is at least
 * one candle to its left and at least one candle to its right in the
 * substring.
 * 
 * 
 * For example, s = "||**||**|*", and a query [3, 8] denotes the substring
 * "*||**|". The number of plates between candles in this substring is 2, as
 * each of the two plates has at least one candle in the substring to its left
 * and right.
 * 
 * 
 * Return an integer array answer where answer[i] is the answer to the i^th
 * query.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "**|**|***|", queries = [[2,5],[5,9]]
 * Output: [2,3]
 * Explanation:
 * - queries[0] has two plates between candles.
 * - queries[1] has three plates between candles.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "***|**|*****|**||**|*", queries =
 * [[1,17],[4,5],[14,17],[5,11],[15,16]]
 * Output: [9,0,0,0,0]
 * Explanation:
 * - queries[0] has nine plates between candles.
 * - The other queries have zero plates between candles.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= s.length <= 10^5
 * s consists of '*' and '|' characters.
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * 0 <= lefti <= righti < s.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] preCandle = new int[n + 2];
        int[] nxtCandle = new int[n + 2];
        int[] prefSum = new int[n + 1];
        
        for(int i = 1; i <= n; i++){
            if(s.charAt(i - 1) == '|'){
                preCandle[i] = i;
            }
            else{
                preCandle[i] = preCandle[i - 1];
            }
        }

        nxtCandle[n + 1] = n;
        for(int i = n; i >= 1; i--){
            if(s.charAt(i - 1) == '|'){
                nxtCandle[i] = i;
            }
            else{
                nxtCandle[i] = nxtCandle[i + 1];
            }
        }

        int total = 0;
        boolean seenLeftCandle = false;
        for(int i = 1; i <= n; i++){
            if(s.charAt(i - 1) == '|'){
                seenLeftCandle = true;
                prefSum[i] = total;
            }
            else{
                prefSum[i] = prefSum[i - 1];
                if(seenLeftCandle){
                    total++;    
                }
            }
        }

        System.out.println(Arrays.toString(preCandle));
        System.out.println(Arrays.toString(nxtCandle));
        System.out.println(Arrays.toString(prefSum));
        int[] ret = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            int[] q = queries[i];
            int right = preCandle[q[1] + 1];
            int left = nxtCandle[q[0] + 1];
            ret[i] = Math.max(0, prefSum[right] - prefSum[left]);
        }
        return ret;
    }
}
// @lc code=end
