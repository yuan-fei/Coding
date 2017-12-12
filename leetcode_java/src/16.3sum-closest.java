
/*
 * [16] 3Sum Closest
 *
 * https://leetcode.com/problems/3sum-closest
 *
 * algorithms
 * Medium (31.39%)
 * Total Accepted:    153.3K
 * Total Submissions: 488.5K
 * Testcase Example:  '[0,0,0]\n1'
 *
 * Given an array S of n integers, find three integers in S such that the sum
 * is closest to a given number, target. Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 * 
 * 
 * ⁠   For example, given array S = {-1 2 1 -4}, and target = 1.
 * 
 * ⁠   The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * 
 */
import java.util.Arrays;

class Solution {
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int closestDiff = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			int first = nums[i];
			int low = i + 1;
			int high = nums.length - 1;
			while(low < high){
				int diff = first + nums[low] + nums[high] - target;
				if(diff == 0){
					return target;
				}
				else if(diff < 0){
					low++;
				}
				else if(diff > 0){
					high--;
				}
				if(Math.abs(closestDiff) > Math.abs(diff)){
					closestDiff = diff;
				}
			}
		}
		return closestDiff + target;
	}
}
