/** O(n) */
public class QuickSelection {

	public static void main(String[] args) {
		int e = findKthSmallestRec(new int[] { 0, 1, 2, 3, 4 }, 0);
		System.out.println(e);
		e = findKthSmallestIter(new int[] { 0, 1, 2, 3, 4 }, 0);
		System.out.println(e);
		e = findKthSmallestRec(new int[] { 0, 1, 2, 3, 4 }, 1);
		System.out.println(e);
		e = findKthSmallestIter(new int[] { 0, 1, 2, 3, 4 }, 1);
		System.out.println(e);
		e = findKthSmallestRec(new int[] { 0, 1, 2, 3, 4 }, 4);
		System.out.println(e);
		e = findKthSmallestIter(new int[] { 0, 1, 2, 3, 4 }, 4);
		System.out.println(e);
		e = findKthSmallestRec(new int[] { 0, 0, 2, 4, 3 }, 4);
		System.out.println(e);
		e = findKthSmallestIter(new int[] { 0, 0, 2, 4, 3 }, 4);
		System.out.println(e);
		e = findKthSmallestRec(new int[] { 0, 0, 2, 4, 3 }, 3);
		System.out.println(e);
		e = findKthSmallestIter(new int[] { 0, 0, 2, 4, 3 }, 3);
		System.out.println(e);
		e = findKthSmallestRec(new int[] { 0, 0, 0, 0, 0 }, 3);
		System.out.println(e);
		e = findKthSmallestIter(new int[] { 0, 0, 0, 0, 0 }, 3);
		System.out.println(e);
		e = findKthSmallestRec(new int[] { 1, 2, 800005, -516268571, 1331571109 }, 2);
		System.out.println(e);
		e = findKthSmallestIter(new int[] { 1, 2, 800005, -516268571, 1331571109 }, 2);
		System.out.println(e);
	}

	public static int findKthSmallestRec(int[] arr, int k) {
		if (k < arr.length) {
			return findKthLargestHelper(arr, 0, arr.length - 1, k);
		}
		throw new IllegalArgumentException();
	}

	public static int findKthLargestHelper(int[] arr, int start, int end, int k) {
		int index = partition(arr, start, end);
		if (index == start + k) {
			return arr[index];
		} else if (index < start + k) {
			return findKthLargestHelper(arr, index + 1, end, k - (index - start + 1));
		} else {
			return findKthLargestHelper(arr, start, index - 1, k);
		}
	}

	public static int findKthSmallestIter(int[] arr, int k) {
		if (k < arr.length) {
			int start = 0;
			int end = arr.length - 1;
			while (start < end) {
				int index = dualPivotPartition(arr, start, end);
				if (index < start + k) {
					k -= (index - start + 1);
					start = index + 1;
				} else {
					end = index;
				}
			}
			return arr[start];
		}
		throw new IllegalArgumentException();
	}

	private static int partition(int[] arr, int start, int end) {
		int j = start;
		int pivot = arr[end];
		for (int i = start; i < end; i++) {
			if (arr[i] < pivot) {
				swap(arr, i, j);
				j++;
			}
		}
		swap(arr, end, j);
		return j;
	}

	/**
	 * dual pivot partition will cut in half when all elements in array are the same
	 */
	private static int dualPivotPartition(int[] arr, int start, int end) {
		int mid = (end + start) / 2;
		int pivot = arr[mid];

		while (true) {
			while (arr[start] < pivot) {
				start++;
			}
			while (arr[end] > pivot) {
				end--;
			}
			if (start >= end) {
				return end;
			} else {
				swap(arr, start, end);
				start++;
				end--;
			}
		}
	}

	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}
