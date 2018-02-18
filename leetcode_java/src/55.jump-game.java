/*
 * [55] Jump Game
 *
 * https://leetcode.com/problems/jump-game/description/
 *
 * algorithms
 * Medium (29.59%)
 * Total Accepted:    154.1K
 * Total Submissions: 520.7K
 * Testcase Example:  '[2,3,1,1,4]'
 *
 * 
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * 
 * Each element in the array represents your maximum jump length at that
 * position. 
 * 
 * 
 * Determine if you are able to reach the last index.
 * 
 * 
 * 
 * For example:
 * A = [2,3,1,1,4], return true.
 * 
 * 
 * A = [3,2,1,0,4], return false.
 * 
 */
class Solution {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0){
        	return false;
        }
        boolean[] state = new boolean[nums.length];
        state[0] = true;
        for (int i = 1; i < nums.length; i++) {
        	state[i] = false;
        	for (int j = i - 1; j >= 0; j--) {
        		if(state[j] && nums[j] + j >= i){
        			state[i] = true;
        			break;
        		}
        	}
        }
        return state[nums.length - 1];
    }
}
