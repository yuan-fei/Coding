/*
 * @lc app=leetcode id=462 lang=java
 *
 * [462] Minimum Moves to Equal Array Elements II
 *
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/description/
 *
 * algorithms
 * Medium (53.44%)
 * Likes:    480
 * Dislikes: 43
 * Total Accepted:    43.9K
 * Total Submissions: 82.1K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a non-empty integer array, find the minimum number of moves required
 * to make all array elements equal, where a move is incrementing a selected
 * element by 1 or decrementing a selected element by 1.
 * 
 * You may assume the array's length is at most 10,000.
 * 
 * Example:
 * 
 * Input:
 * [1,2,3]
 * 
 * Output:
 * 2
 * 
 * Explanation:
 * Only two moves are needed (remember each move increments or decrements one
 * element):
 * 
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 * 
 * 
 */

// @lc code=start
class Solution {
	//diff to median
    public int minMoves2(int[] nums) {
    	int n = nums.length;
    	Arrays.sort(nums);
        int i = 0;
        int j = n - 1;
        int ans = 0;
        while(i < j){
        	ans += nums[j--] - nums[i++];
        }
        return ans;
    }
}
// @lc code=end
