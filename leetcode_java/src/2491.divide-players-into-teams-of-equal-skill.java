/*
 * @lc app=leetcode id=2491 lang=java
 *
 * [2491] Divide Players Into Teams of Equal Skill
 *
 * https://leetcode.com/problems/divide-players-into-teams-of-equal-skill/description/
 *
 * algorithms
 * Medium (56.23%)
 * Likes:    41
 * Dislikes: 1
 * Total Accepted:    10K
 * Total Submissions: 17.7K
 * Testcase Example:  '[3,2,5,1,3,4]'
 *
 * You are given a positive integer array skill of even length n where skill[i]
 * denotes the skill of the i^th player. Divide the players into n / 2 teams of
 * size 2 such that the total skill of each team is equal.
 * 
 * The chemistry of a team is equal to the product of the skills of the players
 * on that team.
 * 
 * Return the sum of the chemistry of all the teams, or return -1 if there is
 * no way to divide the players into teams such that the total skill of each
 * team is equal.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: skill = [3,2,5,1,3,4]
 * Output: 22
 * Explanation: 
 * Divide the players into the following teams: (1, 5), (2, 4), (3, 3), where
 * each team has a total skill of 6.
 * The sum of the chemistry of all the teams is: 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8
 * + 9 = 22.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: skill = [3,4]
 * Output: 12
 * Explanation: 
 * The two players form a team with a total skill of 7.
 * The chemistry of the team is 3 * 4 = 12.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: skill = [1,1,2,3]
 * Output: -1
 * Explanation: 
 * There is no way to divide the players into teams such that the total skill
 * of each team is equal.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= skill.length <= 10^5
 * skill.length is even.
 * 1 <= skill[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public long dividePlayers(int[] skill) {
        int n = skill.length;
        int sum = 0;
        int[] cnt = new int[1002];
        for(int x : skill){
            sum += x;
            cnt[x]++;
        }
        if(sum % (n / 2) != 0){
            return -1;
        }
        int target = sum * 2 / n;
        long ans = 0;
        for(int i = 0; i < cnt.length; i++){
            while(cnt[i] > 0){
                if(cnt[target - i] > 0){
                    cnt[target - i]--;
                    cnt[i]--;
                    ans += i * (target - i);
                }
                else{
                    return -1;
                }
            }
        }
        return ans;
    }
}
// @lc code=end
