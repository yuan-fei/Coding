/*
 * @lc app=leetcode id=948 lang=java
 *
 * [948] Bag of Tokens
 *
 * https://leetcode.com/problems/bag-of-tokens/description/
 *
 * algorithms
 * Medium (52.15%)
 * Likes:    2297
 * Dislikes: 426
 * Total Accepted:    104.1K
 * Total Submissions: 199.6K
 * Testcase Example:  '[100]\n50'
 *
 * You have an initial power of power, an initial score of 0, and a bag of
 * tokens where tokens[i] is the value of the i^th token (0-indexed).
 * 
 * Your goal is to maximize your total score by potentially playing each token
 * in one of two ways:
 * 
 * 
 * If your current power is at least tokens[i], you may play the i^th token
 * face up, losing tokens[i] power and gaining 1 score.
 * If your current score is at least 1, you may play the i^th token face down,
 * gaining tokens[i] power and losing 1 score.
 * 
 * 
 * Each token may be played at most once and in any order. You do not have to
 * play all the tokens.
 * 
 * Return the largest possible score you can achieve after playing any number
 * of tokens.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: tokens = [100], power = 50
 * Output: 0
 * Explanation: Playing the only token in the bag is impossible because you
 * either have too little power or too little score.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: tokens = [100,200], power = 150
 * Output: 1
 * Explanation: Play the 0^th token (100) face up, your power becomes 50 and
 * score becomes 1.
 * There is no need to play the 1^st token since you cannot play it face up to
 * add to your score.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: tokens = [100,200,300,400], power = 200
 * Output: 2
 * Explanation: Play the tokens in this order to get a score of 2:
 * 1. Play the 0^th token (100) face up, your power becomes 100 and score
 * becomes 1.
 * 2. Play the 3^rd token (400) face down, your power becomes 500 and score
 * becomes 0.
 * 3. Play the 1^st token (200) face up, your power becomes 300 and score
 * becomes 1.
 * 4. Play the 2^nd token (300) face up, your power becomes 0 and score becomes
 * 2.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= tokens.length <= 1000
 * 0 <= tokens[i], power < 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    
    public int bagOfTokensScore(int[] tokens, int power) {
        int n = tokens.length;
        if(n == 0){
            return 0;
        }
        Arrays.sort(tokens);
        int ans = 0;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] seen = new boolean[n + 2][n + 2];
        q.offer(new int[]{0, n - 1, power, 0});
        seen[0][n - 1] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            // System.out.println(Arrays.toString(cur));
            ans = Math.max(ans, cur[3]);
            if(cur[0] <= cur[1]){
                if(tokens[cur[0]] <= cur[2] && !seen[cur[0] + 2][cur[1] + 1]){
                    q.offer(new int[]{cur[0] + 1, cur[1], cur[2] - tokens[cur[0]], cur[3] + 1});
                    seen[cur[0] + 2][cur[1] + 1] = true;
                }
                if(cur[3] > 0 && !seen[cur[0] + 1][cur[1]]){
                    q.offer(new int[]{cur[0], cur[1] - 1, cur[2] + tokens[cur[1]], cur[3] - 1});
                    seen[cur[0] + 1][cur[1]] = true;
                }
            }
        }
        return ans;
    }


}
// @lc code=end
