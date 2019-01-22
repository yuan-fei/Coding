import java.util.Arrays;

public class BinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(binarySerach(new int[] {}, 3));
		System.out.println(binarySerach(new int[] { 2 }, 3));
		System.out.println(binarySerach(new int[] { 3 }, 3));
		System.out.println(binarySerach(new int[] { 1, 2, 3 }, 3));
		System.out.println(binarySerach(new int[] { 3, 5 }, 3));
		System.out.println();
		System.out.println(lowerBound(new int[] {}, 3));
		System.out.println(lowerBound(new int[] { 2 }, 3));
		System.out.println(lowerBound(new int[] { 3 }, 3));
		System.out.println(lowerBound(new int[] { 1, 2 }, 3));
		System.out.println(lowerBound(new int[] { 1, 2, 5 }, 3));
		System.out.println(lowerBound(new int[] { 4, 5 }, 3));
		System.out.println();
		System.out.println(upperBound(new int[] {}, 3));
		System.out.println(upperBound(new int[] { 2 }, 3));
		System.out.println(upperBound(new int[] { 3 }, 3));
		System.out.println(upperBound(new int[] { 1, 2 }, 3));
		System.out.println(upperBound(new int[] { 1, 2, 5 }, 3));
		System.out.println(upperBound(new int[] { 4, 5 }, 3));
		System.out.println();
		System.out.println(lowerBoundWithLib(new int[] {}, 3));
		System.out.println(lowerBoundWithLib(new int[] { 2 }, 3));
		System.out.println(lowerBoundWithLib(new int[] { 3 }, 3));
		System.out.println(lowerBoundWithLib(new int[] { 1, 2 }, 3));
		System.out.println(lowerBoundWithLib(new int[] { 1, 2, 5 }, 3));
		System.out.println(lowerBoundWithLib(new int[] { 4, 5 }, 3));
		System.out.println();
		System.out.println(upperBoundWithLib(new int[] {}, 3));
		System.out.println(upperBoundWithLib(new int[] { 2 }, 3));
		System.out.println(upperBoundWithLib(new int[] { 3 }, 3));
		System.out.println(upperBoundWithLib(new int[] { 1, 2 }, 3));
		System.out.println(upperBoundWithLib(new int[] { 1, 2, 5 }, 3));
		System.out.println(upperBoundWithLib(new int[] { 4, 5 }, 3));
	}

	/** binary search and return the index of the first occurrence of target */
	public static int binarySerach(int[] nums, int target) {
		if (nums.length == 0) {
			return -1;
		}
		int start = 0;
		int end = nums.length - 1;
		/** Jump out of the loop when there are less than 3 elements left */
		while (start + 1 < end) {
			/** avoid numeric overflow */
			int mid = start + (end - start) / 2;
			if (nums[mid] == target) {
				/**
				 * move end to mid in order to find the first target; if last target is
				 * expected, move start to mid
				 */
				end = mid;
			} else if (nums[mid] > target) {
				/**
				 * No need to be 'end = mid - 1' because we make sure mid will never equals
				 * start or end! (because there are at least 3 elements according to the while
				 * loop condition 'start + 1 < end')
				 */
				end = mid;
			} else if (nums[mid] < target) {
				start = mid;
			}
		}

		/** Only 2 or 1 elements left between start and end */
		if (nums[start] == target) {
			return start;
		} else if (nums[end] == target) {
			return end;
		}
		return -1;
	}

	public static int lowerBound(int[] nums, int target) {
		if (nums.length == 0) {
			return -1;
		}
		int start = 0;
		int end = nums.length - 1;
		/** Jump out of the loop when there are less than 3 elements left */
		while (start + 1 < end) {
			/** avoid numeric overflow */
			int mid = start + (end - start) / 2;
			if (nums[mid] == target) {
				/**
				 * move end to mid in order to find the first target; if last target is
				 * expected, move start to mid
				 */
				end = mid;
			} else if (nums[mid] > target) {
				/**
				 * No need to be 'end = mid - 1' because we make sure mid will never equals
				 * start or end! (because there are at least 3 elements according to the while
				 * loop condition 'start + 1 < end')
				 */
				end = mid;
			} else if (nums[mid] < target) {
				start = mid;
			}
		}

		/** Only 2 or 1 elements left between start and end */
		if (nums[end] < target || (nums[end] == target && nums[start] < target)) {
			return end;
		} else if (nums[start] <= target) {
			return start;
		} else {
			return -1;
		}
	}

	public static int upperBound(int[] nums, int target) {
		if (nums.length == 0) {
			return -1;
		}
		int start = 0;
		int end = nums.length - 1;
		/** Jump out of the loop when there are less than 3 elements left */
		while (start + 1 < end) {
			/** avoid numeric overflow */
			int mid = start + (end - start) / 2;
			if (nums[mid] == target) {
				/**
				 * move end to mid in order to find the first target; if last target is
				 * expected, move start to mid
				 */
				start = mid;
			} else if (nums[mid] > target) {
				/**
				 * No need to be 'end = mid - 1' because we make sure mid will never equals
				 * start or end! (because there are at least 3 elements according to the while
				 * loop condition 'start + 1 < end')
				 */
				end = mid;
			} else if (nums[mid] < target) {
				start = mid;
			}
		}

		/** Only 2 or 1 elements left between start and end */
		if (nums[start] > target || (nums[start] == target && nums[end] > target)) {
			return start;
		} else if (nums[end] >= target) {
			return end;
		} else {
			return -1;
		}
	}

	public static int lowerBoundWithLib(int[] nums, int target) {
		int index = Arrays.binarySearch(nums, target);
		if (index == -1) {
			return -1;
		}
		if (index < 0) {
			index = -index - 2;
		}

		while (index - 1 >= 0 && nums[index - 1] >= target) {
			index--;
		}
		return index;
	}

	public static int upperBoundWithLib(int[] nums, int target) {
		int index = Arrays.binarySearch(nums, target);
		if (index < 0) {
			index = -index - 1;
		}
		if (index == nums.length) {
			return -1;
		}
		while (index + 1 < nums.length && nums[index + 1] <= target) {
			index++;
		}
		return index;
	}
}
