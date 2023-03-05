/*
 * @lc app=leetcode id=2585 lang=java
 *
 * [2585] Number of Ways to Earn Points
 *
 * https://leetcode.com/problems/number-of-ways-to-earn-points/description/
 *
 * algorithms
 * Hard (58.84%)
 * Likes:    49
 * Dislikes: 1
 * Total Accepted:    3.5K
 * Total Submissions: 6K
 * Testcase Example:  '6\n[[6,1],[3,2],[2,3]]'
 *
 * There is a test that has n types of questions. You are given an integer
 * target and a 0-indexed 2D integer array types where types[i] = [counti,
 * marksi] indicates that there are counti questions of the i^th type, and each
 * one of them is worth marksi points.
 * 
 * 
 * 
 * 
 * Return the number of ways you can earn exactly target points in the exam.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * 
 * Note that questions of the same type are indistinguishable.
 * 
 * 
 * For example, if there are 3 questions of the same type, then solving the
 * 1^st and 2^nd questions is the same as solving the 1^st and 3^rd questions,
 * or the 2^nd and 3^rd questions.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: target = 6, types = [[6,1],[3,2],[2,3]]
 * Output: 7
 * Explanation: You can earn 6 points in one of the seven ways:
 * - Solve 6 questions of the 0^th type: 1 + 1 + 1 + 1 + 1 + 1 = 6
 * - Solve 4 questions of the 0^th type and 1 question of the 1^st type: 1 + 1
 * + 1 + 1 + 2 = 6
 * - Solve 2 questions of the 0^th type and 2 questions of the 1^st type: 1 + 1
 * + 2 + 2 = 6
 * - Solve 3 questions of the 0^th type and 1 question of the 2^nd type: 1 + 1
 * + 1 + 3 = 6
 * - Solve 1 question of the 0^th type, 1 question of the 1^st type and 1
 * question of the 2^nd type: 1 + 2 + 3 = 6
 * - Solve 3 questions of the 1^st type: 2 + 2 + 2 = 6
 * - Solve 2 questions of the 2^nd type: 3 + 3 = 6
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: target = 5, types = [[50,1],[50,2],[50,5]]
 * Output: 4
 * Explanation: You can earn 5 points in one of the four ways:
 * - Solve 5 questions of the 0^th type: 1 + 1 + 1 + 1 + 1 = 5
 * - Solve 3 questions of the 0^th type and 1 question of the 1^st type: 1 + 1
 * + 1 + 2 = 5
 * - Solve 1 questions of the 0^th type and 2 questions of the 1^st type: 1 + 2
 * + 2 = 5
 * - Solve 1 question of the 2^nd type: 5
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: target = 18, types = [[6,1],[3,2],[2,3]]
 * Output: 1
 * Explanation: You can only earn 18 points by answering all questions.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= target <= 1000
 * n == types.length
 * 1 <= n <= 50
 * types[i].length == 2
 * 1 <= counti, marksi <= 50
 * 
 * 
 */

// @lc code=start
class Solution {
    public int waysToReachTarget(int target, int[][] types) {
        int n = types.length;
        int[] sizes = new int[n];
        int[] amount = new int[n];
        for(int i = 0; i < n; i++){
            sizes[i] = types[i][1];
            amount[i] = types[i][0];
        }
        return solveByCompleteBP(target, sizes, amount);
    }

    int MOD = (int)1e9 + 7;
    int solveByCompleteBP(int size_limit, int[] sizes, int[] amount) {
        int[][] state = new int[sizes.length + 1][size_limit + 1];
        for(int i = 0; i < state.length; i++){
            state[i][0] = 1;
        }
        
        for (int i = 1; i <= sizes.length; i++) {
            for (int j = 1; j <= size_limit; j++) {
                state[i][j] = state[i - 1][j];
                for (int k = 1; k <= amount[i - 1]; k++) {
                    if (j >= k * sizes[i - 1]) {
                        state[i][j] = state[i][j] + state[i - 1][j - k * sizes[i - 1]];
                        state[i][j] %= MOD;
                    }
                }
            }
        }
        // System.out.println(Arrays.deepToString(state));
        return state[sizes.length][size_limit];
    }
}
// @lc code=end
