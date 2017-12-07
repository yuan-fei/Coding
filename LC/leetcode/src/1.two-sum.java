/*
 * [1] Two Sum
 *
 * https://leetcode.com/problems/two-sum
 *
 * algorithms
 * Easy (36.29%)
 * Total Accepted:    714.4K
 * Total Submissions: 2M
 * Testcase Example:  '[3,2,4]\n6'
 *
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target.
 * 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * 
 * 
 * Example:
 * 
 * Given nums = [2, 7, 11, 15], target = 9,
 * 
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * 
 * 
 */
import java.util.Map;
import java.util.HashMap;
class Solution {
    public int[] twoSum(int[] nums, int target) {
    	if(nums != null){
    		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
	        for (int i=0; i<nums.length; i++) {
	        	if(map.containsKey(target-nums[i])){
	        		return new int[]{map.get(target-nums[i]),i};
	        	}
	        	else{
	        		map.put(nums[i],i);
	        	}
	        }	
    	}
        return new int[0];
    }
}
