package heap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryHeap<K extends Comparable<K>> {

	public static void main(String[] args) {
		int[] keys = new int[] { 1, 7, 4, 5, 0, 6, 2, 3 };
		BinaryHeap<Integer> h = new BinaryHeap<Integer>();
		System.out.println("Insert");
		for (int i = 0; i < keys.length; i++) {
			h.insert(keys[i]);
			h.print();
		}
		System.out.println("Delete");
		for (int i = 0; i < keys.length; i++) {
			System.out.println("min: " + h.extractMin());
			h.print();
		}

	}

	List<K> h = new ArrayList<K>();

	void print() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(0);
		while (!q.isEmpty()) {
			int cnt = q.size();
			String s = "";
			for (int i = 0; i < cnt; i++) {
				Integer n = q.poll();
				if (n < h.size()) {
					s += (h.get(n) + ",");
					if (leftChild(n) < h.size()) {
						q.offer(leftChild(n));
					}
					if (rightChild(n) < h.size()) {
						q.offer(rightChild(n));
					}
				}
			}
			System.out.println(s);
		}
		System.out.println();
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

	void swap(int i, int j) {
		K t = h.get(i);
		h.set(i, h.get(j));
		h.set(j, t);
	}

	void minHeapify(int i) {
		while (i < h.size()) {
			int iLeftChild = leftChild(i);
			int iRightChild = rightChild(i);
			int candidateIndex = -1;
			if (iLeftChild < h.size() && iRightChild < h.size()) {
				if (h.get(iRightChild).compareTo(h.get(iLeftChild)) > 0) {
					candidateIndex = iLeftChild;
				} else {
					candidateIndex = iRightChild;
				}
			} else if (iLeftChild < h.size()) {
				candidateIndex = iLeftChild;
			} else if (iRightChild < h.size()) {
				candidateIndex = iRightChild;
			}
			if (candidateIndex > 0 && h.get(i).compareTo(h.get(candidateIndex)) > 0) {
				swap(candidateIndex, i);
				i = candidateIndex;
			} else {
				return;
			}

		}
	}

	void buildMinHeap() {
		for (int i = h.size() / 2; i >= 0; i--) {
			minHeapify(i);
		}
	}

	public void decreaseKey(int i, K k) {
		h.set(i, k);
		while (i >= 0) {
			int p = parent(i);
			if (p >= 0 && h.get(p).compareTo(h.get(i)) > 0) {
				swap(i, p);
				i = p;
			} else {
				return;
			}
		}
	}

	public K extractMin() {
		swap(0, h.size() - 1);
		K k = h.remove(h.size() - 1);
		minHeapify(0);
		return k;
	}

	public void insert(K k) {
		h.add(k);
		decreaseKey(h.size() - 1, k);
	}

	public void delete(K k) {
		int i = h.indexOf(k);
		if (i >= 0) {
			swap(i, h.size() - 1);
			minHeapify(i);
			h.remove(h.size() - 1);
		}
	}

}
