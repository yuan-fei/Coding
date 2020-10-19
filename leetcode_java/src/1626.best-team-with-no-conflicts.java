/*
 * @lc app=leetcode id=1626 lang=java
 *
 * [1626] Best Team With No Conflicts
 *
 * https://leetcode.com/problems/best-team-with-no-conflicts/description/
 *
 * algorithms
 * Medium (31.99%)
 * Likes:    159
 * Dislikes: 8
 * Total Accepted:    3.8K
 * Total Submissions: 11.8K
 * Testcase Example:  '[1,3,5,10,15]\n[1,2,3,4,5]'
 *
 * You are the manager of a basketball team. For the upcoming tournament, you
 * want to choose the team with the highest overall score. The score of the
 * team is the sum of scores of all the players in the team.
 * 
 * However, the basketball team is not allowed to have conflicts. A conflict
 * exists if a younger player has a strictly higher score than an older player.
 * A conflict does not occur between players of the same age.
 * 
 * Given two lists, scores and ages, where each scores[i] and ages[i]
 * represents the score and age of the i^th player, respectively, return the
 * highest overall score of all possible basketball teams.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
 * Output: 34
 * Explanation: You can choose all the players.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: scores = [4,5,6,5], ages = [2,1,2,1]
 * Output: 16
 * Explanation: It is best to choose the last 3 players. Notice that you are
 * allowed to choose multiple people of the same age.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: scores = [1,2,3,5], ages = [8,9,10,1]
 * Output: 6
 * Explanation: It is best to choose the first 3 players. 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= scores.length, ages.length <= 1000
 * scores.length == ages.length
 * 1 <= scores[i] <= 10^6
 * 1 <= ages[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
    	int ans = 0;
    	int n = scores.length;
    	List<Integer> id = new ArrayList<>();
    	for(int i = 0; i < n; i++){
    		id.add(i);
    	}
    	Collections.sort(id, (a, b) -> Integer.compare(ages[a], ages[b]) == 0 ? Integer.compare(scores[a], scores[b]) : Integer.compare(ages[a], ages[b]));
    	TreeMap<Integer, Integer> tm = new TreeMap<>();
    	tm.put(0, 0);

    	for(int i : id){
    		int max = 0;
    		for(int v : tm.subMap(0, scores[i] + 1).values()){
    			max = Math.max(v, max);
    		}
    		tm.put(scores[i], max + scores[i]);
    		ans = Math.max(ans, tm.get(scores[i]));
    	}
    	return ans;
    }
}
// @lc code=end
