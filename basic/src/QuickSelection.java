
public class QuickSelection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int e = findKthLargest(new int[] { 0, 1, 2, 3, 4 }, 0);
		System.out.println(e);
		e = findKthLargest(new int[] { 0, 1, 2, 3, 4 }, 1);
		System.out.println(e);
		e = findKthLargest(new int[] { 0, 1, 2, 3, 4 }, 4);
		System.out.println(e);
		e = findKthLargest(new int[] { 0, 0, 2, 4, 3 }, 4);
		System.out.println(e);
		e = findKthLargest(new int[] { 0, 0, 2, 4, 3 }, 3);
		System.out.println(e);
		e = findKthLargest(new int[] { 0, 0, 0, 0, 0 }, 3);
		System.out.println(e);
	}

	public static int findKthLargest(int[] arr, int k) {
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
			return findKthLargestHelper(arr, index + 1, end, k - index - 1);
		} else {
			return findKthLargestHelper(arr, start, index - 1, k);
		}
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

	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}
