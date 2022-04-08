/*
 * @lc app=leetcode id=2225 lang=java
 *
 * [2225] Find Players With Zero or One Losses
 *
 * https://leetcode.com/problems/find-players-with-zero-or-one-losses/description/
 *
 * algorithms
 * Medium (66.36%)
 * Likes:    89
 * Dislikes: 4
 * Total Accepted:    12.2K
 * Total Submissions: 18.4K
 * Testcase Example:  '[[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]'
 *
 * You are given an integer array matches where matches[i] = [winneri, loseri]
 * indicates that the player winneri defeated player loseri in a match.
 * 
 * Return a list answer of size 2 where:
 * 
 * 
 * answer[0] is a list of all players that have not lost any matches.
 * answer[1] is a list of all players that have lost exactly one match.
 * 
 * 
 * The values in the two lists should be returned in increasing order.
 * 
 * Note:
 * 
 * 
 * You should only consider the players that have played at least one
 * match.
 * The testcases will be generated such that no two matches will have the same
 * outcome.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: matches =
 * [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
 * Output: [[1,2,10],[4,5,7,8]]
 * Explanation:
 * Players 1, 2, and 10 have not lost any matches.
 * Players 4, 5, 7, and 8 each have lost one match.
 * Players 3, 6, and 9 each have lost two matches.
 * Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: matches = [[2,3],[1,3],[5,4],[6,4]]
 * Output: [[1,2,5,6],[]]
 * Explanation:
 * Players 1, 2, 5, and 6 have not lost any matches.
 * Players 3 and 4 each have lost two matches.
 * Thus, answer[0] = [1,2,5,6] and answer[1] = [].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= matches.length <= 10^5
 * matches[i].length == 2
 * 1 <= winneri, loseri <= 10^5
 * winneri != loseri
 * All matches[i] are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        int[] win = new int[100005];
        int[] lose = new int[100005];
        for(int[] m : matches){
            win[m[0]]++;
            lose[m[1]]++;
        }
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList());
        ans.add(new ArrayList());
        for(int i = 0; i < win.length; i++){
            if(win[i] + lose[i] > 0){
                if(lose[i] == 0){
                    ans.get(0).add(i);
                }
                else if(lose[i] == 1){
                    ans.get(1).add(i);   
                }
            }
        }
        return ans;
    }
}
// @lc code=end
