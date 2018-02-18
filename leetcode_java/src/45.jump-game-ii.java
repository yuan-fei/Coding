/*
 * [45] Jump Game II
 *
 * https://leetcode.com/problems/jump-game-ii/description/
 *
 * algorithms
 * Hard (26.16%)
 * Total Accepted:    109.8K
 * Total Submissions: 419.9K
 * Testcase Example:  '[0]'
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
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * 
 * 
 * For example:
 * Given array A = [2,3,1,1,4]
 * 
 * 
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from
 * index 0 to 1, then 3 steps to the last index.)
 * 
 * 
 * 
 * Note:
 * You can assume that you can always reach the last index.
 */
class Solution {
    public int jump(int[] nums) {
        return jumpGreedy(nums);
    }

    private int jumpDp(int[] nums){
    	if(nums == null && nums.length == 0){
        	return 0;
        }
        int[] state = new int[nums.length];
        state[0] = 0;
        for (int i = 1 ; i < nums.length; i++) {
        	state[i] = Integer.MAX_VALUE;
        	for (int j = i - 1; j >= 0; j--) {
        		if(state[j] != Integer.MAX_VALUE && (j + nums[j] >= i)){
        			state[i] = Math.min(state[i], state[j]) + 1;	
        		}
        	}
        }
        return state[nums.length - 1];
    }

    //BFS
    public int jumpGreedy(int[] nums) {
    	if(nums == null|nums.length < 2){
    		return 0;
    	}
        int steps = 1;
        int end = 0;
        int nextEnd = 0;
        for (int i = 0; i < nums.length; i++) {
        	if(i > end){
        		steps++;
        		end = nextEnd;
        	}
    		nextEnd = Math.max(nextEnd, nums[i] + i);
    		if(nextEnd >= nums.length - 1){
    			return steps;
    		}
        }
        return steps;
    }
}
