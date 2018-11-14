/** search for max value of a uni-modal array in O(logn) */
public class TernarySearch {

	public static void main(String[] args) {
		System.out.println(find(new int[] { 1, 2, 3, 4, 5, 3, 2 }));
		System.out.println(find(new int[] { 1, 2, 3, 4, 5 }));
		System.out.println(find(new int[] { 4, 3, 2, 1 }));
		System.out.println(find(new int[] { 1, 1, 1, 1 }));
	}

	public static int find(int[] nums) {
		int start = 0;
		int end = nums.length - 1;
		while (start + 2 < end) {
			int left = start + (end - start) / 3;
			int right = start + 2 * (end - start) / 3;
			if (nums[left] > nums[right]) {
				end = right;
			} else {
				start = left;
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = start; i <= end; i++) {
			max = Math.max(max, nums[i]);
		}
		return max;
	}
}
