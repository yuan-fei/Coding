
public class BinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int result = binarySerach(new int[] {}, 3);
		result = binarySerach(new int[] { 1, 2, 3 }, 3);
		result = binarySerach(new int[] { 3, 5 }, 3);
		System.out.println(result);
	}

	/** binary search and return the index of the first occurrence of target */
	public static int binarySerach(int[] nums, int target) {
		if (nums.length == 0) {
			return -1;
		}
		int start = 0;
		int end = nums.length;
		/** Jump out of the loop when there are less than 3 elements left */
		while (start + 1 < end) {
			/** avoid numeric overflow */
			int mid = start + (end - start) / 2;
			if (nums[mid] == target) {
				/**
				 * move end to mid in order to find the first target; if last
				 * target is expected, move start to mid
				 */
				end = mid;
			} else if (nums[mid] > target) {
				/**
				 * No need to be 'end = mid - 1' because we make sure mid will
				 * never equals start or end! (because there are at least 3
				 * elements according to the while loop condition 'start + 1 <
				 * end')
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
}
