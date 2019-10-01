package sorting.application;

/** count inversion with Merge sort in O(nlogn) */
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
			sum += merge(arr, aux, start, mid, end);
			System.arraycopy(aux, start, arr, start, end - start);
		}
		return sum;
	}

	/** merge from[start..mid] and from[mid, end] to to[start..end] */
	private static int merge(int[] from, int[] to, int start, int mid, int end) {
		int totalInversion = 0;
		int curInversion = 0;
		int i = start;
		int j = mid;
		int k = start;
		while (i < mid && j < end) {
			if (from[i] <= from[j]) {
				totalInversion += curInversion;
				to[k] = from[i];
				i++;
			} else {
				curInversion++;
				to[k] = from[j];
				j++;
			}
			k++;
		}
		while (i < mid) {
			totalInversion += curInversion;
			to[k] = from[i];
			i++;
			k++;
		}
		while (j < end) {
			to[k] = from[j];
			j++;
			k++;
		}
		return totalInversion;
	}
}
