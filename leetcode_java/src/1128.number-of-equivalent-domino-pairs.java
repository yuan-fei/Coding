/*
 * @lc app=leetcode id=1128 lang=java
 *
 * [1128] Number of Equivalent Domino Pairs
 *
 * https://leetcode.com/problems/number-of-equivalent-domino-pairs/description/
 *
 * algorithms
 * Easy (47.93%)
 * Likes:    663
 * Dislikes: 325
 * Total Accepted:    65.1K
 * Total Submissions: 135.7K
 * Testcase Example:  '[[1,2],[2,1],[3,4],[5,6]]'
 *
 * Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j]
 * = [c, d] if and only if either (a == c and b == d), or (a == d and b == c) -
 * that is, one domino can be rotated to be equal to another domino.
 * 
 * Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length,
 * and dominoes[i] is equivalent to dominoes[j].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * Output: 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
 * Output: 3
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= dominoes.length <= 4 * 10^4
 * dominoes[i].length == 2
 * 1 <= dominoes[i][j] <= 9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<List<Integer>, Integer> freq = new HashMap<>();
        int ans = 0;
        for(int[] domino: dominoes){
            List<Integer> dList = Arrays.stream(domino).boxed().collect(Collectors.toList());
            Collections.sort(dList);
            ans += freq.getOrDefault(dList, 0);
            freq.compute(dList, (k, v) -> v == null ? 1 : v + 1);
            
        }
        return ans;
    }
}
// @lc code=end
