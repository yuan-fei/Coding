/*
 * @lc app=leetcode id=2977 lang=java
 *
 * [2977] Minimum Cost to Convert String II
 *
 * https://leetcode.com/problems/minimum-cost-to-convert-string-ii/description/
 *
 * algorithms
 * Hard (22.60%)
 * Likes:    104
 * Dislikes: 76
 * Total Accepted:    5.4K
 * Total Submissions: 21K
 * Testcase Example:  '"abcd"\n"acbe"\n["a","b","c","c","e","d"]\n' +
  '["b","c","b","e","b","e"]\n[2,5,5,1,2,20]'
 *
 * You are given two 0-indexed strings source and target, both of length n and
 * consisting of lowercase English characters. You are also given two 0-indexed
 * string arrays original and changed, and an integer array cost, where cost[i]
 * represents the cost of converting the string original[i] to the string
 * changed[i].
 * 
 * You start with the string source. In one operation, you can pick a substring
 * x from the string, and change it to y at a cost of z if there exists any
 * index j such that cost[j] == z, original[j] == x, and changed[j] == y. You
 * are allowed to do any number of operations, but any pair of operations must
 * satisfy either of these two conditions:
 * 
 * 
 * The substrings picked in the operations are source[a..b] and source[c..d]
 * with either b < c or d < a. In other words, the indices picked in both
 * operations are disjoint.
 * The substrings picked in the operations are source[a..b] and source[c..d]
 * with a == c and b == d. In other words, the indices picked in both
 * operations are identical.
 * 
 * 
 * Return the minimum cost to convert the string source to the string target
 * using any number of operations. If it is impossible to convert source to
 * target, return -1.
 * 
 * Note that there may exist indices i, j such that original[j] == original[i]
 * and changed[j] == changed[i].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: source = "abcd", target = "acbe", original =
 * ["a","b","c","c","e","d"], changed = ["b","c","b","e","b","e"], cost =
 * [2,5,5,1,2,20]
 * Output: 28
 * Explanation: To convert "abcd" to "acbe", do the following operations:
 * - Change substring source[1..1] from "b" to "c" at a cost of 5.
 * - Change substring source[2..2] from "c" to "e" at a cost of 1.
 * - Change substring source[2..2] from "e" to "b" at a cost of 2.
 * - Change substring source[3..3] from "d" to "e" at a cost of 20.
 * The total cost incurred is 5 + 1 + 2 + 20 = 28. 
 * It can be shown that this is the minimum possible cost.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: source = "abcdefgh", target = "acdeeghh", original =
 * ["bcd","fgh","thh"], changed = ["cde","thh","ghh"], cost = [1,3,5]
 * Output: 9
 * Explanation: To convert "abcdefgh" to "acdeeghh", do the following
 * operations:
 * - Change substring source[1..3] from "bcd" to "cde" at a cost of 1.
 * - Change substring source[5..7] from "fgh" to "thh" at a cost of 3. We can
 * do this operation because indices [5,7] are disjoint with indices picked in
 * the first operation.
 * - Change substring source[5..7] from "thh" to "ghh" at a cost of 5. We can
 * do this operation because indices [5,7] are disjoint with indices picked in
 * the first operation, and identical with indices picked in the second
 * operation.
 * The total cost incurred is 1 + 3 + 5 = 9.
 * It can be shown that this is the minimum possible cost.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: source = "abcdefgh", target = "addddddd", original = ["bcd","defgh"],
 * changed = ["ddd","ddddd"], cost = [100,1578]
 * Output: -1
 * Explanation: It is impossible to convert "abcdefgh" to "addddddd".
 * If you select substring source[1..3] as the first operation to change
 * "abcdefgh" to "adddefgh", you cannot select substring source[3..7] as the
 * second operation because it has a common index, 3, with the first operation.
 * If you select substring source[3..7] as the first operation to change
 * "abcdefgh" to "abcddddd", you cannot select substring source[1..3] as the
 * second operation because it has a common index, 3, with the first
 * operation.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= source.length == target.length <= 1000
 * source, target consist only of lowercase English characters.
 * 1 <= cost.length == original.length == changed.length <= 100
 * 1 <= original[i].length == changed[i].length <= source.length
 * original[i], changed[i] consist only of lowercase English characters.
 * original[i] != changed[i]
 * 1 <= cost[i] <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    int MAX_ASSP_COST = (int)1e8 + 5;
    long MAX_COST = (long)1e11 + 5;
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        Map<String, Integer> strToIdx = new HashMap<>();
        Set<Integer>[] strByLastChar = new HashSet[26];
        for(int i = 0; i < 26; i++){
            strByLastChar[i] = new HashSet<>();
        }
        int id = 0;
        for(int i = 0; i < original.length; i++){
            if(null == strToIdx.putIfAbsent(original[i], id)){
                strByLastChar[original[i].charAt(original[i].length() - 1) - 'a'].add(original[i].length());
                id++;
            }
            if(null == strToIdx.putIfAbsent(changed[i], id)){
                strByLastChar[changed[i].charAt(changed[i].length() - 1) - 'a'].add(changed[i].length());
                id++;
            }
        }
        int n = strToIdx.size();
        int[][] asspCost = new int[n * 2][n * 2];
        for(int i = 0; i < asspCost.length; i++){
            Arrays.fill(asspCost[i], MAX_ASSP_COST);
            asspCost[i][i] = 0;
        }

        for(int i = 0; i < original.length; i++){
            asspCost[strToIdx.get(original[i])][strToIdx.get(changed[i])] 
                = Math.min(asspCost[strToIdx.get(original[i])][strToIdx.get(changed[i])], cost[i]);
        }
        assp(asspCost);
        // System.out.println(Arrays.deepToString(asspCost));
        int m = source.length();
        long[] dp = new long[m + 1];
        Arrays.fill(dp, MAX_COST);
        dp[0] = 0L;
        for(int i = 1; i <= m; i++){
            if(source.charAt(i - 1) == target.charAt(i - 1)){
                dp[i] = dp[i - 1];
            }
            for(int l : strByLastChar[source.charAt(i - 1) - 'a']){
                int j = i - l;
                if(j >= 0){
                    Integer srcId = strToIdx.get(source.substring(j, i));
                    Integer targetId = strToIdx.get(target.substring(j, i));
                    if(srcId != null && targetId != null && asspCost[srcId][targetId] < MAX_ASSP_COST){
                        dp[i] = Math.min(dp[j] + asspCost[srcId][targetId], dp[i]);    
                    }    
                }
            }
        }
        // System.out.println(Arrays.toString(dp));
        return dp[m] >= MAX_COST ? -1 : dp[m];
    }

    void assp(int[][] asspCost){
        int n = asspCost.length;
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    asspCost[i][j] = Math.min(asspCost[i][j], asspCost[i][k] + asspCost[k][j]);
                }
            }
        }
    }
}
// @lc code=end
