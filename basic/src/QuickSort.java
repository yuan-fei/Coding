import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		int[] nums = new int[] { 3, 2, 5, 4, 7, 9, 7 };
		quickSort(nums, 0, nums.length - 1);
		print(nums);
		nums = new int[] { 1, 2, 3, 4, 7, 9 };
		quickSort(nums, 0, nums.length - 1);
		print(nums);
		nums = new int[0];
		quickSort(nums, 0, nums.length - 1);
		print(nums);
		nums = new int[] { 1 };
		quickSort(nums, 0, nums.length - 1);
		print(nums);
	}

	public static void print(int[] nums) {
		System.out.println(Arrays.toString(nums));
	}

	public static void quickSort(int[] nums, int start, int end) {
		if (start >= end) {
			return;
		}
		int pivot = partition(nums, start, end);
		quickSort(nums, start, pivot - 1);
		quickSort(nums, pivot + 1, end);
	}

	private static int partition(int[] nums, int start, int end) {
		int firstGreaterIndex = start;
		int pivot = nums[end];
		for (int i = start; i < end; i++) {
			if (nums[i] < pivot) {
				swap(nums, i, firstGreaterIndex);
				firstGreaterIndex++;
			}
		}
		swap(nums, end, firstGreaterIndex);
		return firstGreaterIndex;
	}

	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}
