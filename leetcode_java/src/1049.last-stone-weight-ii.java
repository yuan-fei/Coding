/*
 * @lc app=leetcode id=1049 lang=java
 *
 * [1049] Last Stone Weight II
 *
 * https://leetcode.com/problems/last-stone-weight-ii/description/
 *
 * algorithms
 * Medium (54.55%)
 * Likes:    2983
 * Dislikes: 105
 * Total Accepted:    80.2K
 * Total Submissions: 146.5K
 * Testcase Example:  '[2,7,4,1,8,1]'
 *
 * You are given an array of integers stones where stones[i] is the weight of
 * the i^th stone.
 * 
 * We are playing a game with the stones. On each turn, we choose any two
 * stones and smash them together. Suppose the stones have weights x and y with
 * x <= y. The result of this smash is:
 * 
 * 
 * If x == y, both stones are destroyed, and
 * If x != y, the stone of weight x is destroyed, and the stone of weight y has
 * new weight y - x.
 * 
 * 
 * At the end of the game, there is at most one stone left.
 * 
 * Return the smallest possible weight of the left stone. If there are no
 * stones left, return 0.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: stones = [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We can combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
 * we can combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
 * we can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
 * we can combine 1 and 1 to get 0, so the array converts to [1], then that's
 * the optimal value.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: stones = [31,26,33,21,40]
 * Output: 5
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int MAX = 3005;
        boolean[] values = new boolean[MAX];
        values[0] = true;
        int sum = 0;
        for(int s : stones){
            sum += s;
            for(int i = values.length - s - 1; i >= 0 ; i--){
                values[i + s] |= values[i];
            }
        }
        for(int i = sum / 2; i >= 0; i--){
            if(values[i]){
                return sum - 2 * i;
            }
        }
        return -1;
    }
}
// @lc code=end
