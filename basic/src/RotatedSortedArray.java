/**
 * Find min or search in rotated sorted array
 * 
 * Note: no duplicate element!
 */
public class RotatedSortedArray {
	public static void main(String[] args) {
		System.out.println(findMin(new int[] { 4, 5, 6, 7, 1, 2, 3 }));
		System.out.println(findMin(new int[] { 1, 2, 3, 4, 5, 6 }));
		System.out.println(search(new int[] { 4, 5, 6, 7, 1, 2, 3 }, 3));
		System.out.println(search(new int[] { 1, 2, 3, 4, 5, 6 }, 3));
	}

	public static int findMin(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int low = 0;
		int high = nums.length - 1;
		while (low + 1 < high) {
			int mid = low + (high - low) / 2;
			/** must choose high end for comparison in case of a fully sorted array */
			if (nums[high] < nums[mid]) {
				low = mid;
			} else {
				high = mid;
			}
		}
		return Math.min(nums[high], nums[low]);
	}

	public static int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int low = 0;
		int high = nums.length - 1;
		while (low + 1 < high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[low] < nums[mid]) {
				if (nums[low] <= target && target < nums[mid]) {
					high = mid;
				} else {
					low = mid;
				}
			} else {
				if (nums[mid] < target && target <= nums[high]) {
					low = mid;
				} else {
					high = mid;
				}
			}
		}
		if (nums[low] == target) {
			return low;
		} else if (nums[high] == target) {
			return high;
		}
		return -1;
	}
}
