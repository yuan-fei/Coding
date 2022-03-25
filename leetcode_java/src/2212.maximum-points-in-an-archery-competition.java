/*
 * @lc app=leetcode id=2212 lang=java
 *
 * [2212] Maximum Points in an Archery Competition
 *
 * https://leetcode.com/problems/maximum-points-in-an-archery-competition/description/
 *
 * algorithms
 * Medium (43.09%)
 * Likes:    112
 * Dislikes: 10
 * Total Accepted:    4.4K
 * Total Submissions: 10.2K
 * Testcase Example:  '9\n[1,1,0,1,0,0,2,1,0,1,2,0]'
 *
 * Alice and Bob are opponents in an archery competition. The competition has
 * set the following rules:
 * 
 * 
 * Alice first shoots numArrows arrows and then Bob shoots numArrows
 * arrows.
 * The points are then calculated as follows:
 * 
 * The target has integer scoring sections ranging from 0 to 11
 * inclusive.
 * For each section of the target with score k (in between 0 to 11), say Alice
 * and Bob have shot ak and bk arrows on that section respectively. If ak >=
 * bk, then Alice takes k points. If ak < bk, then Bob takes k points.
 * However, if ak == bk == 0, then nobody takes k points.
 * 
 * 
 * 
 * 
 * 
 * 
 * For example, if Alice and Bob both shot 2 arrows on the section with score
 * 11, then Alice takes 11 points. On the other hand, if Alice shot 0 arrows on
 * the section with score 11 and Bob shot 2 arrows on that same section, then
 * Bob takes 11 points.
 * 
 * 
 * 
 * You are given the integer numArrows and an integer array aliceArrows of size
 * 12, which represents the number of arrows Alice shot on each scoring section
 * from 0 to 11. Now, Bob wants to maximize the total number of points he can
 * obtain.
 * 
 * Return the array bobArrows which represents the number of arrows Bob shot on
 * each scoring section from 0 to 11. The sum of the values in bobArrows should
 * equal numArrows.
 * 
 * If there are multiple ways for Bob to earn the maximum total points, return
 * any one of them.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: numArrows = 9, aliceArrows = [1,1,0,1,0,0,2,1,0,1,2,0]
 * Output: [0,0,0,0,1,1,0,0,1,2,3,1]
 * Explanation: The table above shows how the competition is scored. 
 * Bob earns a total point of 4 + 5 + 8 + 9 + 10 + 11 = 47.
 * It can be shown that Bob cannot obtain a score higher than 47 points.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: numArrows = 3, aliceArrows = [0,0,1,0,0,0,0,0,0,0,0,2]
 * Output: [0,0,0,0,0,0,0,0,1,1,1,0]
 * Explanation: The table above shows how the competition is scored.
 * Bob earns a total point of 8 + 9 + 10 = 27.
 * It can be shown that Bob cannot obtain a score higher than 27 points.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= numArrows <= 10^5
 * aliceArrows.length == bobArrows.length == 12
 * 0 <= aliceArrows[i], bobArrows[i] <= numArrows
 * sum(aliceArrows[i]) == numArrows
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        int[] dp = new int[numArrows + 1];
        boolean[][] aux = new boolean[aliceArrows.length][numArrows + 1];
        for(int i = 0; i < aliceArrows.length; i++){
            for(int j = numArrows; j >= 0; j--){
                if(j - aliceArrows[i] - 1 >= 0){
                    if(dp[j] < dp[j - aliceArrows[i] - 1] + i){
                        dp[j] = dp[j - aliceArrows[i] - 1] + i;  
                        aux[i][j] = true;
                    }
                }
            }
        }
        int[] ans = new int[aliceArrows.length];
        int cur = numArrows;
        for(int i = aliceArrows.length - 1; i >= 0; i--){
            if(aux[i][cur]){
                ans[i] = aliceArrows[i] + 1;
                cur -= aliceArrows[i] + 1;
            }
        }
        ans[0] = cur;
        return ans;
    }
}
// @lc code=end
