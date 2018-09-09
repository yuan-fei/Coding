package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FibnacciHeap<K extends Comparable<K>> implements IMergeableMinHeap<FibnacciHeapNode<K>, K> {

	public static void main(String[] args) {
		FibnacciHeap<Integer> h = new FibnacciHeap<Integer>();
		int[] keys = new int[] { 1, 7, 4, 5, 0, 6, 2, 3 };
		// keys = SamplingAndShuffling.shuffle(8);
		System.out.println(Arrays.toString(keys));
		List<FibnacciHeapNode<Integer>> nodes = new ArrayList<FibnacciHeapNode<Integer>>();
		System.out.println("Insertion");
		for (int i = 0; i < keys.length; i++) {
			nodes.add(new FibnacciHeapNode<Integer>(keys[i]));
			h.insert(nodes.get(i));
			h.print();
			System.out.println("Min: " + h.minimum());
		}
		System.out.println("DecreaseKey" + nodes.get(1));
		h.decreaseKey(nodes.get(1), 0);
		h.print();
		System.out.println("Extracted min: " + h.extractMin());
		h.print();
		System.out.println("Delete" + nodes.get(2));
		h.delete(nodes.get(2));
		h.print();
		System.out.println("ExtractMin");
		while (!h.isEmpty()) {
			System.out.println("Extracted min: " + h.extractMin());
			h.print();
		}

	}

	private boolean isEmpty() {
		return min == null;
	}

	private void print() {
		FibnacciHeapNode<K> n = min;
		print(n);
		while (n != null && n.right != null && n.right != min) {
			n = n.right;
			print(n);
		}
	}

	private void print(FibnacciHeapNode<K> x) {
		Queue<FibnacciHeapNode<K>> q = new LinkedList<FibnacciHeapNode<K>>();
		q.offer(x);
		while (!q.isEmpty()) {
			int cnt = q.size();
			String s = "";
			for (int i = 0; i < cnt; i++) {
				FibnacciHeapNode<K> n = q.poll();
				s += (n + ",");
				if (n != null) {
					FibnacciHeapNode<K> child = n.child;
					for (int j = 0; j < n.degree; j++) {
						q.offer(child);
						child = child.right;
					}
				}
			}
			System.out.println(s);
		}
		System.out.println();
	}

	FibnacciHeapNode<K> min;
	int rootCount;

	@Override
	public void insert(FibnacciHeapNode<K> x) {
		if (min == null) {
			min = x;
		} else {
			x.setRight(min.right);
			min.setRight(x);
			if (min.key.compareTo(x.key) > 0) {
				min = x;
			}
		}
		rootCount++;
	}

	@Override
	public FibnacciHeapNode<K> minimum() {
		return min;
	}

	@Override
	public FibnacciHeapNode<K> extractMin() {
		FibnacciHeapNode<K> rt = min;
		if (min != null) {
			if (min.child != null) {
				FibnacciHeapNode<K> child = min.child;
				FibnacciHeapNode<K> rightChild = child.right;
				for (int i = 0; i < min.degree; i++) {
					child.parent = null;
					child.firstChildLost = false;
					insert(child);
					child = rightChild;
					rightChild = rightChild.right;
				}
			}
			if (min.right == min) {
				min = null;
			} else {
				FibnacciHeapNode<K> tmp = min.right;
				min.left.setRight(min.right);
				min = tmp;
			}
		}
		rootCount--;
		adjustHeap();
		return rt;
	}

	private void adjustHeap() {
		if (min == null) {
			return;
		}
		Map<Integer, FibnacciHeapNode<K>> degree = new HashMap<Integer, FibnacciHeapNode<K>>();
		FibnacciHeapNode<K> n = min;
		FibnacciHeapNode<K> next = n.right;
		for (int i = 0; i < rootCount; i++) {
			n.setLeft(n);
			while (degree.containsKey(n.degree)) {
				FibnacciHeapNode<K> m = degree.remove(n.degree);
				if (m.key.compareTo(n.key) < 0) {
					n = m.link(n);
				} else {
					n = n.link(m);
				}
			}
			degree.put(n.degree, n);
			n = next;
			next = next.right;
		}
		rootCount = 0;
		min = null;
		for (FibnacciHeapNode<K> node : degree.values()) {
			insert(node);
		}
	}

	@Override
	public void unionWith(IMergeableMinHeap<FibnacciHeapNode<K>, K> h) {
		FibnacciHeapNode<K> thatMin = ((FibnacciHeap<K>) h).min;
		if (min == null) {
			min = thatMin;
			return;
		}
		if (thatMin == null) {
			return;
		}
		min.left.setRight(thatMin);
		thatMin.left.setRight(min);
		if (min.key.compareTo(thatMin.key) > 0) {
			min = thatMin;
		}
		rootCount += ((FibnacciHeap<K>) h).rootCount;
	}

	@Override
	public void decreaseKey(FibnacciHeapNode<K> x, K k) {
		if (x.key.compareTo(k) < 0) {
			throw new IllegalArgumentException("decreaseKey with larger key: " + k);
		}
		x.key = k;
		cut(x.parent, x);
		cutCascade(x.parent);
	}

	private void cut(FibnacciHeapNode<K> p, FibnacciHeapNode<K> x) {
		if (p != null) {
			if (p.degree == 1) {
				p.setChild(null);
			} else {
				p.setChild(x.left);
			}
			p.degree--;
			p.firstChildLost = true;
		}
		if (min != x) {
			x.left.setRight(x.right);
			x.setLeft(x);
			if (x.parent == null) {
				rootCount--;
			} else {
				x.parent = null;
			}
			insert(x);
		}
	}

	private void cutCascade(FibnacciHeapNode<K> p) {
		if (p != null && p.firstChildLost) {
			cut(p.parent, p);
			cutCascade(p.parent);
		}
	}

	@Override
	public void delete(FibnacciHeapNode<K> x) {
		x.key = minimum().key;
		extractMin();
	}

}

class FibnacciHeapNode<K extends Comparable<K>> {
	FibnacciHeapNode<K> parent, child, left, right;
	K key;
	int degree;
	boolean firstChildLost;

	public FibnacciHeapNode(K key) {
		super();
		this.key = key;
		left = this;
		right = this;
	}

	public void setChild(FibnacciHeapNode<K> n) {
		child = n;
		if (n != null) {
			n.parent = this;
		}
	}

	public void setRight(FibnacciHeapNode<K> n) {
		right = n;
		if (n != null) {
			n.left = this;
		}
	}

	public void setLeft(FibnacciHeapNode<K> n) {
		left = n;
		if (n != null) {
			n.right = this;
		}
	}

	FibnacciHeapNode<K> link(FibnacciHeapNode<K> x) {
		if (x != null) {
			x.parent = this;
			x.firstChildLost = false;
			if (child == null) {
				this.child = x;
			} else {
				x.setRight(this.child.right);
				this.child.setRight(x);
			}
			degree++;
		}
		return this;
	}

	@Override
	public String toString() {
		return "[" + key + ", " + degree + ", " + firstChildLost + "]";
	}
}
