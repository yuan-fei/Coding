/*
 * @lc app=leetcode id=1921 lang=java
 *
 * [1921] Eliminate Maximum Number of Monsters
 *
 * https://leetcode.com/problems/eliminate-maximum-number-of-monsters/description/
 *
 * algorithms
 * Medium (37.13%)
 * Likes:    169
 * Dislikes: 38
 * Total Accepted:    9.3K
 * Total Submissions: 25K
 * Testcase Example:  '[1,3,4]\n[1,1,1]'
 *
 * You are playing a video game where you are defending your city from a group
 * of n monsters. You are given a 0-indexed integer array dist of size n, where
 * dist[i] is the initial distance in meters of the i^th monster from the
 * city.
 * 
 * The monsters walk toward the city at a constant speed. The speed of each
 * monster is given to you in an integer array speed of size n, where speed[i]
 * is the speed of the i^th monster in meters per minute.
 * 
 * The monsters start moving at minute 0. You have a weapon that you can choose
 * to use at the start of every minute, including minute 0. You cannot use the
 * weapon in the middle of a minute. The weapon can eliminate any monster that
 * is still alive. You lose when any monster reaches your city. If a monster
 * reaches the city exactly at the start of a minute, it counts as a loss, and
 * the game ends before you can use your weapon in that minute.
 * 
 * Return the maximum number of monsters that you can eliminate before you
 * lose, or n if you can eliminate all the monsters before they reach the
 * city.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: dist = [1,3,4], speed = [1,1,1]
 * Output: 3
 * Explanation:
 * At the start of minute 0, the distances of the monsters are [1,3,4], you
 * eliminate the first monster.
 * At the start of minute 1, the distances of the monsters are [X,2,3], you
 * don't do anything.
 * At the start of minute 2, the distances of the monsters are [X,1,2], you
 * eliminate the second monster.
 * At the start of minute 3, the distances of the monsters are [X,X,1], you
 * eliminate the third monster.
 * All 3 monsters can be eliminated.
 * 
 * Example 2:
 * 
 * 
 * Input: dist = [1,1,2,3], speed = [1,1,1,1]
 * Output: 1
 * Explanation:
 * At the start of minute 0, the distances of the monsters are [1,1,2,3], you
 * eliminate the first monster.
 * At the start of minute 1, the distances of the monsters are [X,0,1,2], so
 * you lose.
 * You can only eliminate 1 monster.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: dist = [3,2,4], speed = [5,3,2]
 * Output: 1
 * Explanation:
 * At the start of minute 0, the distances of the monsters are [3,2,4], you
 * eliminate the first monster.
 * At the start of minute 1, the distances of the monsters are [X,0,2], so you
 * lose.
 * You can only eliminate 1 monster.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == dist.length == speed.length
 * 1 <= n <= 10^5
 * 1 <= dist[i], speed[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int[] t = new int[n];
        for(int i = 0; i < n; i++){
            t[i] = (dist[i] + speed[i] - 1) / speed[i];
        }
        Arrays.sort(t);
        int cur = 0;
        int cnt = 0;
        for(int x : t){
            System.out.println(x + ", " + cur);
            if(x > cur){
                cnt++;
            }
            else{
                break;
            }
            cur++;
        }
        return cnt;
    }
}
// @lc code=end
