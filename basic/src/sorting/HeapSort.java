package sorting;

import java.util.Arrays;

public class HeapSort implements ISorting {

	public static void main(String[] args) {
		ISorting s = new HeapSort();
		int[] keys = new int[] { 1, 7, 4, 5, 0, 6, 2, 3 };
		// keys = new int[] { 1, 0 };
		System.out.println(Arrays.toString(keys));
		s.sort(keys);
		System.out.println(Arrays.toString(keys));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sorting.ISorting#sort(int[])
	 */
	@Override
	public void sort(int[] a) {
		buildMaxHeap(a, a.length);
		for (int heapSize = a.length - 1; heapSize >= 1; heapSize--) {
			swap(a, 0, heapSize);
			maxHeapify(a, 0, heapSize - 1);
		}
	}

	/** O(n) */
	void buildMaxHeap(int[] a, int heapSize) {
		for (int i = heapSize / 2; i >= 0; i--) {
			maxHeapify(a, i, heapSize);
		}
	}

	/** O(logn) */
	void maxHeapify(int[] a, int i, int heapSize) {
		while (i < heapSize) {
			int iLeftChild = leftChild(i);
			int iRightChild = rightChild(i);
			int candidateIndex = -1;
			if (iLeftChild < heapSize && iRightChild < heapSize) {
				if (a[iRightChild] < a[iLeftChild]) {
					candidateIndex = iLeftChild;
				} else {
					candidateIndex = iRightChild;
				}
			} else if (iLeftChild < heapSize) {
				candidateIndex = iLeftChild;
			} else if (iRightChild < heapSize) {
				candidateIndex = iRightChild;
			}
			if (candidateIndex > 0 && a[i] < a[candidateIndex]) {
				swap(a, candidateIndex, i);
				i = candidateIndex;
			} else {
				return;
			}

		}
	}

	int parent(int i) {
		return (i - 1) >> 1;
	}

	int leftChild(int i) {
		return rightChild(i) - 1;
	}

	int rightChild(int i) {
		return (i + 1) << 1;
	}

	void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}
