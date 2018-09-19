package sorting;

import java.util.Arrays;

public class QuickSort implements ISorting {

	public static void main(String[] args) {
		int[] nums = new int[] { 3, 2, 5, 4, 7, 9, 7 };
		QuickSort q = new QuickSort();
		q.sort(nums);
		print(nums);
		nums = new int[] { 1, 2, 3, 4, 7, 9 };
		q.sort(nums);
		print(nums);
		nums = new int[0];
		q.sort(nums);
		print(nums);
		nums = new int[] { 1 };
		q.sort(nums);
		print(nums);
	}

	public static void print(int[] nums) {
		System.out.println(Arrays.toString(nums));
	}

	public void sort(int[] nums) {
		quickSort(nums, 0, nums.length - 1);
	}

	public void quickSort(int[] nums, int start, int end) {
		if (start >= end) {
			return;
		}
		int pivot = partition(nums, start, end);
		quickSort(nums, start, pivot - 1);
		quickSort(nums, pivot + 1, end);
	}

	private int partition(int[] nums, int start, int end) {
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

	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}
