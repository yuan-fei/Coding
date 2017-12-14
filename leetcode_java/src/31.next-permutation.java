/*
 * [31] Next Permutation
 *
 * https://leetcode.com/problems/next-permutation
 *
 * algorithms
 * Medium (28.88%)
 * Total Accepted:    132.4K
 * Total Submissions: 458.4K
 * Testcase Example:  '[1,2,3]'
 *
 * 
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 * 
 * 
 * The replacement must be in-place, do not allocate extra memory.
 * 
 * 
 * Here are some examples. Inputs are in the left-hand column and its
 * corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * 
 */


class Solution {
	public void nextPermutation(int[] nums) {
		int last = nums.length - 1;
		//1. Find backward for first decreased element e
		while (last > 0 && nums[last] <= nums[last - 1]) {
			last--;
		}

		reverseTail(nums, last);
		if (last > 0) {
			// nums[nums.length - 1] > nums[last - 1]
			int index = findFirstGreater(nums, last, nums[last - 1]);
			if (index != -1) {
				swap(nums, index, last - 1);
			}
		}
	}


	private void reverseTail(int[] nums, int start) {
		for (int i = start, j = nums.length - 1; i < j; i++, j--) {
			swap(nums, i, j);
		}
	}

	/*Find the index of 1st element starting from the index that is greater than the target*/
	private int findFirstGreater(int[] nums, int index, int target) {
		if (nums.length <= index) {
			return -1;
		}
		int low = index;
		int high = nums.length - 1;
		while (low + 1 < high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] <= target) {
				low = mid;
			} else {
				high = mid;
			}
		}
		if (nums[low] > target) {
			return low;
		}else if (nums[high] > target) {
			return high;
		} else if (nums.length > high + 1 && nums[high + 1] > target) {
			return high + 1;
		} else {
			return -1;
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
