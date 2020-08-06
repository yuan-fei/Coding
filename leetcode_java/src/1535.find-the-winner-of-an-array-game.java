/*
 * @lc app=leetcode id=1535 lang=java
 *
 * [1535] Find the Winner of an Array Game
 *
 * https://leetcode.com/problems/find-the-winner-of-an-array-game/description/
 *
 * algorithms
 * Medium (45.08%)
 * Likes:    144
 * Dislikes: 14
 * Total Accepted:    10.8K
 * Total Submissions: 24K
 * Testcase Example:  '[2,1,3,5,4,6,7]\n2'
 *
 * Given an integer array arr of distinct integers and an integer k.
 * 
 * A game will be played between the first two elements of the array (i.e.
 * arr[0] and arr[1]). In each round of the game, we compare arr[0] with
 * arr[1], the larger integer wins and remains at position 0 and the smaller
 * integer moves to the end of the array. The game ends when an integer wins k
 * consecutive rounds.
 * 
 * Return the integer which will win the game.
 * 
 * It is guaranteed that there will be a winner of the game.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [2,1,3,5,4,6,7], k = 2
 * Output: 5
 * Explanation: Let's see the rounds of the game:
 * Round |       arr       | winner | win_count
 * ⁠ 1   | [2,1,3,5,4,6,7] | 2      | 1
 * ⁠ 2   | [2,3,5,4,6,7,1] | 3      | 1
 * ⁠ 3   | [3,5,4,6,7,1,2] | 5      | 1
 * ⁠ 4   | [5,4,6,7,1,2,3] | 5      | 2
 * So we can see that 4 rounds will be played and 5 is the winner because it
 * wins 2 consecutive games.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [3,2,1], k = 10
 * Output: 3
 * Explanation: 3 will win the first 10 rounds consecutively.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr = [1,9,8,2,3,7,6,4,5], k = 7
 * Output: 9
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: arr = [1,11,22,33,44,55,66,77,88,99], k = 1000000000
 * Output: 99
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^6
 * arr contains distinct integers.
 * 1 <= k <= 10^9
 * 
 */

// @lc code=start
class Solution {
    public int getWinner(int[] arr, int k) {
    	int curMax = 0;
    	for(int i = 0; i < arr.length; i++){
    		if(arr[curMax] < arr[i]){
    			curMax = i;
    		}
    		if(curMax == 0 && i - curMax >= k){
				return arr[curMax];
			}
			if(curMax > 0 && i - curMax + 1 >= k){
				return arr[curMax];
			}
    		// System.out.println(i + "" + curMax);
    	}
    	return arr[curMax];
    }
}
// @lc code=end
