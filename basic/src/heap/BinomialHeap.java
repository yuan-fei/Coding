package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import math.SamplingAndShuffling;

/** Heap Operations with O(logn) */
public class BinomialHeap<K extends Comparable<K>> implements IMergeableMinHeap<BinomialHeapNode<K>, K> {

	public static void main(String[] args) {
		BinomialHeap<Integer> h = new BinomialHeap<Integer>();
		int[] keys = new int[] { 2, 4, 5, 1, 3 };
		keys = SamplingAndShuffling.shuffle(8);
		System.out.println(Arrays.toString(keys));
		List<BinomialHeapNode<Integer>> nodes = new ArrayList<BinomialHeapNode<Integer>>();
		System.out.println("Insertion");
		for (int i = 0; i < keys.length; i++) {
			nodes.add(new BinomialHeapNode<Integer>(keys[i]));
			h.insert(nodes.get(i));
			h.print();
			System.out.println("Min: " + h.minimum());
		}
		System.out.println("DecreaseKey");
		h.decreaseKey(nodes.get(1), 0);
		h.print();
		System.out.println("Delete");
		h.delete(nodes.get(2));
		h.print();
		System.out.println("ExtractMin");
		while (!h.isEmpty()) {
			System.out.println("Extracted min: " + h.extractMin());
			h.print();
		}
	}

	BinomialHeapNode<K> head;

	public void print() {
		BinomialHeapNode<K> cur = head;
		while (cur != null) {
			print(cur);
			cur = cur.sibling;
		}
	}

	private void print(BinomialHeapNode<K> x) {
		Queue<BinomialHeapNode<K>> q = new LinkedList<BinomialHeapNode<K>>();
		q.offer(x);
		while (!q.isEmpty()) {
			int cnt = q.size();
			String s = "";
			for (int i = 0; i < cnt; i++) {
				BinomialHeapNode<K> n = q.poll();
				s += (n + ",");
				if (n != null) {
					BinomialHeapNode<K> child = n.child;
					while (child != null) {
						q.offer(child);
						child = child.sibling;
					}
				}
			}
			System.out.println(s);
		}
		System.out.println();
	}

	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public void insert(BinomialHeapNode<K> x) {
		BinomialHeap<K> h = new BinomialHeap<K>();
		h.head = x;
		unionWith(h);
	}

	@Override
	public BinomialHeapNode<K> minimum() {
		BinomialHeapNode<K> x = head;
		BinomialHeapNode<K> min = x;
		while (x != null) {
			if (min == null || x.key.compareTo(min.key) < 0) {
				min = x;
			}
			x = x.sibling;
		}
		return min;
	}

	@Override
	public BinomialHeapNode<K> extractMin() {
		BinomialHeapNode<K> min = minimum();
		if (min == null) {
			return null;
		}
		BinomialHeapNode<K> dummy = new BinomialHeapNode<K>(null);
		dummy.sibling = head;
		BinomialHeapNode<K> pre = dummy;
		BinomialHeapNode<K> cur = pre.sibling;
		while (cur != min) {
			pre = cur;
			cur = cur.sibling;
		}
		pre.sibling = cur.sibling;
		head = dummy.sibling;
		BinomialHeap<K> subHeap = new BinomialHeap<>();
		subHeap.head = rearrange(cur.child);
		unionWith(subHeap);
		return min;
	}

	private BinomialHeapNode<K> rearrange(BinomialHeapNode<K> hd) {

		BinomialHeapNode<K> pre = null;
		BinomialHeapNode<K> cur = hd;
		while (cur != null) {
			BinomialHeapNode<K> tmp = cur.sibling;
			cur.sibling = pre;
			pre = cur;
			cur = tmp;
			pre.parent = null;
		}
		return pre;
	}

	private void mergeWith(BinomialHeap<K> h) {
		BinomialHeapNode<K> a = this.head;
		BinomialHeapNode<K> b = h.head;
		BinomialHeapNode<K> dummy = new BinomialHeapNode<K>(null);
		BinomialHeapNode<K> cur = dummy;
		while (true) {
			if (a == null) {
				cur.sibling = b;
				break;
			} else if (b == null) {
				cur.sibling = a;
				break;
			}
			if (a.degree < b.degree) {
				cur.sibling = a;
				a = a.sibling;
			} else {
				cur.sibling = b;
				b = b.sibling;
			}
			cur = cur.sibling;
		}
		head = dummy.sibling;
	}

	@Override
	public void unionWith(IMergeableMinHeap<BinomialHeapNode<K>, K> h) {
		mergeWith((BinomialHeap<K>) h);
		if (head == null) {
			return;
		}
		BinomialHeapNode<K> dummy = new BinomialHeapNode<K>(null);
		dummy.sibling = head;
		BinomialHeapNode<K> pre = dummy;
		BinomialHeapNode<K> cur = dummy.sibling;
		BinomialHeapNode<K> next = cur.sibling;
		while (next != null) {
			if (cur.degree != next.degree
					|| (cur.degree == next.degree && next.sibling != null && next.degree == next.sibling.degree)) {
				pre = cur;
				cur = next;
				next = next.sibling;
			} else {
				BinomialHeapNode<K> nextSibling = next.sibling;
				if (cur.key.compareTo(next.key) < 0) {
					pre.sibling = cur.link(next);

				} else {
					pre.sibling = next.link(cur);
				}
				pre.sibling.sibling = nextSibling;
				cur = pre.sibling;
				next = cur.sibling;
			}
		}
		head = dummy.sibling;
	}

	@Override
	public void decreaseKey(BinomialHeapNode<K> x, K k) {
		if (x.key.compareTo(k) < 0) {
			throw new IllegalArgumentException("decreaseKey with larger key: " + k);
		}
		x.key = k;
		BinomialHeapNode<K> p = x.parent;
		while (p != null && p.key.compareTo(x.key) > 0) {
			K tmp = x.key;
			x.key = p.key;
			p.key = tmp;
			x = p;
			p = p.parent;
		}
	}

	@Override
	public void delete(BinomialHeapNode<K> x) {
		decreaseKey(x, minimum().key);
		extractMin();
	}

}

class BinomialHeapNode<K extends Comparable<K>> {
	BinomialHeapNode<K> parent, child, sibling;
	K key;
	int degree = 0;

	public BinomialHeapNode(K key) {
		super();
		this.key = key;
	}

	BinomialHeapNode<K> link(BinomialHeapNode<K> x) {
		x.parent = this;
		x.sibling = this.child;
		this.child = x;
		degree++;
		return this;
	}

	@Override
	public String toString() {
		return "[" + key + ", " + degree + "]";
	}
}
