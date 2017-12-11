import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * [15] 3Sum
 *
 * https://leetcode.com/problems/3sum
 *
 * algorithms
 * Medium (21.79%)
 * Total Accepted:    271K
 * Total Submissions: 1.2M
 * Testcase Example:  '[-1,0,1,2,-1,-4]'
 *
 * Given an array S of n integers, are there elements a, b, c in S such that a
 * + b + c = 0? Find all unique triplets in the array which gives the sum of
 * zero.
 * 
 * Note: The solution set must not contain duplicate triplets.
 * 
 * 
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * 
 * A solution set is:
 * [
 * ⁠ [-1, 0, 1],
 * ⁠ [-1, -1, 2]
 * ]
 * 
 */

/*
 * key points: duplication in input and output
 * input: sorting can make duplicated elements adjacent and skip the duplication
 * output: when scaning in sorted array, feasible solutoin is obtained in the first-element-in-ordered way
 * In addition, in a sorted array, 2-sum can be solved in O(n) with a 2 pointer min-max solution
 */
class Solution {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		if (nums != null && nums.length >= 3) {
			Arrays.sort(nums); // O(nlogn)
			for (int i = 0; i < nums.length; i++) {
				if (i > 0 && nums[i] == nums[i - 1]) {
					continue;
				}
				int target = nums[i];
				List<List<Integer>> twoSumResults = twoSumInSortedArray(nums, i + 1, nums.length - 1, -target);
				for (List<Integer> twoSumResult : twoSumResults) {
					twoSumResult.add(0, target);
				}
				results.addAll(twoSumResults);
			}	
		}
		return results;
	}

	private List<List<Integer>> twoSumInSortedArray(int[] nums, int start, int end, int target) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		int low = start;
		int high = end;
		while (low < high) {
			if (low != start && nums[low] == nums[low - 1]) {
				low++;
				continue;
			}
			if (high != end && nums[high] == nums[high + 1]) {
				high--;
				continue;
			}
			if (target == (nums[low] + nums[high])) {
				List<Integer> result = new ArrayList<Integer>();
				result.add(nums[low]);
				result.add(nums[high]);
				results.add(result);
				low++;
				high--;
			} else if (target > (nums[low] + nums[high])) {
				low++;
			} else {
				high--;
			}
		}
		return results;
	}

}
