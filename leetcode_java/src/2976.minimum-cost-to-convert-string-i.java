/*
 * @lc app=leetcode id=2976 lang=java
 *
 * [2976] Minimum Cost to Convert String I
 *
 * https://leetcode.com/problems/minimum-cost-to-convert-string-i/description/
 *
 * algorithms
 * Medium (38.64%)
 * Likes:    890
 * Dislikes: 62
 * Total Accepted:    103.5K
 * Total Submissions: 178.2K
 * Testcase Example:  '"abcd"\n"acbe"\n["a","b","c","c","e","d"]\n' +
  '["b","c","b","e","b","e"]\n[2,5,5,1,2,20]'
 *
 * You are given two 0-indexed strings source and target, both of length n and
 * consisting of lowercase English letters. You are also given two 0-indexed
 * character arrays original and changed, and an integer array cost, where
 * cost[i] represents the cost of changing the character original[i] to the
 * character changed[i].
 * 
 * You start with the string source. In one operation, you can pick a character
 * x from the string and change it to the character y at a cost of z if there
 * exists any index j such that cost[j] == z, original[j] == x, and changed[j]
 * == y.
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
 * Explanation: To convert the string "abcd" to string "acbe":
 * - Change value at index 1 from 'b' to 'c' at a cost of 5.
 * - Change value at index 2 from 'c' to 'e' at a cost of 1.
 * - Change value at index 2 from 'e' to 'b' at a cost of 2.
 * - Change value at index 3 from 'd' to 'e' at a cost of 20.
 * The total cost incurred is 5 + 1 + 2 + 20 = 28.
 * It can be shown that this is the minimum possible cost.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: source = "aaaa", target = "bbbb", original = ["a","c"], changed =
 * ["c","b"], cost = [1,2]
 * Output: 12
 * Explanation: To change the character 'a' to 'b' change the character 'a' to
 * 'c' at a cost of 1, followed by changing the character 'c' to 'b' at a cost
 * of 2, for a total cost of 1 + 2 = 3. To change all occurrences of 'a' to
 * 'b', a total cost of 3 * 4 = 12 is incurred.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: source = "abcd", target = "abce", original = ["a"], changed = ["e"],
 * cost = [10000]
 * Output: -1
 * Explanation: It is impossible to convert source to target because the value
 * at index 3 cannot be changed from 'd' to 'e'.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= source.length == target.length <= 10^5
 * source, target consist of lowercase English letters.
 * 1 <= cost.length == original.length == changed.length <= 2000
 * original[i], changed[i] are lowercase English letters.
 * 1 <= cost[i] <= 10^6
 * original[i] != changed[i]
 * 
 * 
 */

// @lc code=start
class Solution {
    int MAX_ASSP_COST = (int)1e8 + 5;
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int[][] asspCost = new int[26][26];
        for(int i = 0; i < asspCost.length; i++){
            Arrays.fill(asspCost[i], MAX_ASSP_COST);
            asspCost[i][i] = 0;
        }

        for(int i = 0; i < original.length; i++){
            asspCost[original[i] - 'a'][changed[i] - 'a'] 
                = Math.min(asspCost[original[i] - 'a'][changed[i] - 'a'], cost[i]);
        }        
        assp(asspCost);
        // System.out.println(Arrays.deepToString(asspCost));
        int m = source.length();
        if(IntStream.range(0, m).anyMatch(i -> asspCost[source.charAt(i) - 'a'][target.charAt(i) - 'a'] >= MAX_ASSP_COST)){
            return -1;
        }
        // for(int i = 0; i < original.length; i++){
        //     System.out.println(asspCost[original[i] - 'a'][changed[i] - 'a']);
        // }
        return IntStream.range(0, m).mapToLong(i -> asspCost[source.charAt(i) - 'a'][target.charAt(i) - 'a']).sum();
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
