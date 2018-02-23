/*
 * [229] Majority Element II
 *
 * https://leetcode.com/problems/majority-element-ii/description/
 *
 * algorithms
 * Medium (29.05%)
 * Total Accepted:    67.6K
 * Total Submissions: 232.6K
 * Testcase Example:  '[]'
 *
 * Given an integer array of size n, find all elements that appear more than ⌊
 * n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
 */
class Solution {
    public List<Integer> majorityElement(int[] nums) {
    	List<Integer> result = new ArrayList<Integer>();
        int[] candidates = new int[2];
        int[] counts = new int[2];
        for (int num : nums) {
        	if(counts[0] == 0 && (counts[1] == 0 || candidates[1] != num)){
        		candidates[0] = num;
        	}
        	else if(counts[1] == 0 && (counts[0] == 0 || candidates[0] != num)){
        		candidates[1] = num;
        	}
        	if(candidates[0] == num){
        		counts[0]++;
        	}
        	else if(candidates[1] == num){
        		counts[1]++;
        	}
        	else{
        		counts[0]--;
        		counts[1]--;
        	}
        }
        counts[0] = 0;
        counts[1] = 0;
        for (int num : nums) {
        	if(num == candidates[0]){
        		counts[0]++;
        	}
        	else if(num == candidates[1]){
        		counts[1]++;
        	}
        }
        if(counts[0] > nums.length / 3){
        	result.add(candidates[0]);
        }
        if(counts[1] > nums.length / 3){
        	result.add(candidates[1]);
        }
        return result;
    }
}
