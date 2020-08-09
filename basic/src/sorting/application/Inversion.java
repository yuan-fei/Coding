package sorting.application;

/**
 * Count reverse pair: count inversion (i < j && a[i] > a[j]) with Merge sort in
 * O(nlogn)
 */
public class Inversion {

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 8, 2, 1, 4, 7, 3, 2, 3, 6 };
		System.out.println(new Inversion().inversion(arr));
		arr = new int[] { 1, 2 };
		System.out.println(new Inversion().inversion(arr));
	}

	public int inversion(int[] arr) {
		int[] aux = new int[arr.length];
		return mergeSort(arr, aux, 0, arr.length);
	}

	/** sort arr[start..end] with help of aux array */
	private static int mergeSort(int[] arr, int[] aux, int start, int end) {
		int sum = 0;
		if (start + 1 < end) {
			int mid = (start + end) / 2;
			sum += mergeSort(arr, aux, start, mid);
			sum += mergeSort(arr, aux, mid, end);
			sum += count(arr, start, mid, end);
			merge(arr, aux, start, mid, end);
			System.arraycopy(aux, start, arr, start, end - start);
		}
		return sum;
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

	/** count inversions between from[start..mid] and from[mid, end] */
	private static int count(int[] from, int start, int mid, int end) {
		int totalInversion = 0;
		int j = mid;
		for (int i = start; i < mid; i++) {
			while (j < end && from[i] > from[j]) {
				j++;
			}
			totalInversion += j - mid;
		}
		return totalInversion;
	}

}
