package sorting;

import java.util.Arrays;

/** Merge sort in O(nlogn), stable */
public class MergeSort implements ISorting {

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 8, 2, 1, 4, 7, 3, 2, 3, 6 };
		new MergeSort().sort(arr);
		System.out.println(Arrays.toString(arr));
		arr = new int[] { 1, 2 };
		new MergeSort().sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void sort(int[] arr) {
		int[] aux = new int[arr.length];
		mergeSort(arr, aux, 0, arr.length);
	}

	/** sort arr[start..end] with help of aux array */
	private static void mergeSort(int[] arr, int[] aux, int start, int end) {
		if (start + 1 < end) {
			int mid = (start + end) / 2;
			mergeSort(arr, aux, start, mid);
			mergeSort(arr, aux, mid, end);
			merge(arr, aux, start, mid, end);
			System.arraycopy(aux, start, arr, start, end - start);
		}
	}

	/** merge from[start..mid] and from[mid, end] to to[start..end] */
	private static void merge(int[] from, int[] to, int start, int mid, int end) {

		int i = start;
		int j = mid;
		int k = start;
		while (i < mid && j < end) {
			if (from[i] <= from[j]) {
				to[k] = from[i];
				i++;
			} else {
				to[k] = from[j];
				j++;
			}
			k++;
		}
		while (i < mid) {
			to[k] = from[i];
			i++;
			k++;
		}
		while (j < end) {
			to[k] = from[j];
			j++;
			k++;
		}

	}
}
