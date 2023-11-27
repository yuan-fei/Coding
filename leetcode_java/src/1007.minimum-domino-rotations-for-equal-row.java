/*
 * @lc app=leetcode id=1007 lang=java
 *
 * [1007] Minimum Domino Rotations For Equal Row
 *
 * https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/description/
 *
 * algorithms
 * Medium (52.24%)
 * Likes:    2784
 * Dislikes: 250
 * Total Accepted:    198K
 * Total Submissions: 379K
 * Testcase Example:  '[2,1,2,4,2,2]\n[5,2,6,2,3,2]'
 *
 * In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom
 * halves of the i^th domino. (A domino is a tile with two numbers from 1 to 6
 * - one on each half of the tile.)
 * 
 * We may rotate the i^th domino, so that tops[i] and bottoms[i] swap values.
 * 
 * Return the minimum number of rotations so that all the values in tops are
 * the same, or all the values in bottoms are the same.
 * 
 * If it cannot be done, return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
 * Output: 2
 * Explanation: 
 * The first figure represents the dominoes as given by tops and bottoms:
 * before we do any rotations.
 * If we rotate the second and fourth dominoes, we can make every value in the
 * top row equal to 2, as indicated by the second figure.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: tops = [3,5,1,2,3], bottoms = [3,6,3,3,4]
 * Output: -1
 * Explanation: 
 * In this case, it is not possible to rotate the dominoes to make one row of
 * values equal.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= tops.length <= 2 * 10^4
 * bottoms.length == tops.length
 * 1 <= tops[i], bottoms[i] <= 6
 * 
 * 
 */

// @lc code=start
class Solution {
    int MAX = 20005;
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int ans = MAX;
        for(int target = 1; target <= 6; target++){
            ans = Math.min(ans, minSwap(tops, bottoms, target));
            ans = Math.min(ans, minSwap(bottoms, tops, target));
        }
        return ans == MAX ? -1 : ans;
    }

    int minSwap(int[] tops, int[] bottoms, int target){
        int steps = 0;
        for(int i = 0; i < tops.length; i++){
            if(tops[i] != target){
                if(bottoms[i] != target){
                    return MAX; 
                }    
                steps++;   
            }
        }
        return steps;
    }
}
// @lc code=end
