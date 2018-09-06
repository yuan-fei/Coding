package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import math.SamplingAndShuffling;

/** Left lean Red Black Tree */
public class LLRBT<K extends Comparable<K>> {

	public static void main(String[] args) {
		test();
	}

	private static void test() {
		LLRBT<Integer> rbt = new LLRBT<Integer>();
		int[] k = new int[] { 5, 2, 0, 6, 1, 4, 3 };
		System.out.println("Insert");
		System.out.println(Arrays.toString(k));
		for (int i : k) {
			rbt.insert(i);
			rbt.print();
		}
		System.out.println("Remove");
		k = new int[] { 1, 0, 2, 3, 5, 6, 4 };
		System.out.println(Arrays.toString(k));
		for (int i : k) {
			rbt.delete(i);
			rbt.print();
		}
	}

	private static void testRandom() {
		LLRBT<Integer> rbt = new LLRBT<Integer>();
		int[] k = SamplingAndShuffling.shuffle(7);
		System.out.println("Insert");
		System.out.println(Arrays.toString(k));
		for (int i : k) {
			rbt.insert(i);
			rbt.print();
		}
		System.out.println("Remove");
		k = SamplingAndShuffling.shuffle(7);
		System.out.println(Arrays.toString(k));
		for (int i : k) {
			rbt.delete(i);
			rbt.print();
		}
	}

	LeftLeanRedBlackTreeNode<K> root;

	public void print() {
		Queue<LeftLeanRedBlackTreeNode<K>> q = new LinkedList<LeftLeanRedBlackTreeNode<K>>();
		q.offer(root);
		while (!q.isEmpty()) {
			int cnt = q.size();
			String s = "";
			for (int i = 0; i < cnt; i++) {
				LeftLeanRedBlackTreeNode<K> n = q.poll();
				s += (n + ",");
				if (n != null) {
					q.offer(n.leftChild);
					q.offer(n.rightChild);
				}
			}
			System.out.println(s);
		}
		System.out.println();
	}

	private boolean isRed(LeftLeanRedBlackTreeNode<K> n) {
		return n != null && n.color == Color.R;
	}

	private void swapColor(LeftLeanRedBlackTreeNode<K> a, LeftLeanRedBlackTreeNode<K> b) {
		Color c = a.color;
		a.color = b.color;
		b.color = c;
	}

	private void flipColor(LeftLeanRedBlackTreeNode<K> r) {
		Color c = r.color;
		if (r.leftChild != null) {
			r.color = r.leftChild.color;
			r.leftChild.color = c;
		}
		if (r.rightChild != null) {
			r.color = r.rightChild.color;
			r.rightChild.color = c;
		}

	}

	private LeftLeanRedBlackTreeNode<K> min(LeftLeanRedBlackTreeNode<K> r) {
		if (r.leftChild == null) {
			return r;
		} else {
			return min(r.leftChild);
		}
	}

	private LeftLeanRedBlackTreeNode<K> max(LeftLeanRedBlackTreeNode<K> r) {
		if (r.rightChild == null) {
			return r;
		} else {
			return min(r.rightChild);
		}
	}

	private LeftLeanRedBlackTreeNode<K> search(LeftLeanRedBlackTreeNode<K> n, K k) {
		if (n == null) {
			return null;
		}
		int result = k.compareTo(n.key);
		if (result == 0) {
			return n;
		} else if (result < 0) {
			return search(n.leftChild, k);
		} else {
			return search(n.rightChild, k);
		}
	}

	private LeftLeanRedBlackTreeNode<K> leftRotate(LeftLeanRedBlackTreeNode<K> r) {
		LeftLeanRedBlackTreeNode<K> y = r.rightChild;
		swapColor(r, y);
		r.setRightChild(y.leftChild);
		y.setLeftChild(r);
		return y;
	}

	private LeftLeanRedBlackTreeNode<K> rightRotate(LeftLeanRedBlackTreeNode<K> r) {
		LeftLeanRedBlackTreeNode<K> y = r.leftChild;
		swapColor(r, y);
		r.setLeftChild(y.rightChild);
		y.setRightChild(r);
		return y;
	}

	private LeftLeanRedBlackTreeNode<K> fix(LeftLeanRedBlackTreeNode<K> n) {
		if (!isRed(n.leftChild) && isRed(n.rightChild)) {
			n = leftRotate(n);
		}
		if (isRed(n.leftChild) && n.leftChild.leftChild != null && isRed(n.leftChild.leftChild)) {
			n = rightRotate(n);
		}
		if (isRed(n.leftChild) && isRed(n.rightChild)) {
			flipColor(n);
		}
		return n;
	}

	public void insert(K k) {
		root = insert(root, k);
		root.color = Color.B;
	}

	private LeftLeanRedBlackTreeNode<K> insert(LeftLeanRedBlackTreeNode<K> n, K k) {
		if (n == null) {
			n = new LeftLeanRedBlackTreeNode<K>(k, Color.R);
		} else {
			int result = k.compareTo(n.key);
			if (result == 0) {
				throw new IllegalArgumentException("duplicate key: " + k);
			} else if (result < 0) {
				n.setLeftChild(insert(n.leftChild, k));
			} else {
				n.setRightChild(insert(n.rightChild, k));
			}
		}
		return fix(n);
	}

	public void delete(K k) {
		if (search(root, k) != null) {
			if (!isRed(root.leftChild) && !isRed(root.rightChild)) {
				root.color = Color.R;
			}
			root = delete(root, k);
		}
	}

	/** Assume k exists */
	private LeftLeanRedBlackTreeNode<K> delete(LeftLeanRedBlackTreeNode<K> n, K k) {

		if (k.compareTo(n.key) < 0) {
			if (!isRed(n.leftChild) && n.leftChild != null && !isRed(n.leftChild.leftChild)) {
				n = moveLeft(n);
			}
			n.setLeftChild(delete(n.leftChild, k));
		} else {
			if (isRed(n.leftChild)) {
				n = rightRotate(n);
			}
			if (k.compareTo(n.key) == 0 && n.rightChild == null) { // delete n
				return null;
			}
			if (!isRed(n.rightChild) && n.rightChild != null && !isRed(n.rightChild.leftChild)) {
				// 2-node, make it 3
				n = moveRight(n);
			}
			if (k.compareTo(n.key) > 0) {
				n.setRightChild(delete(n.rightChild, k));
			} else {
				LeftLeanRedBlackTreeNode<K> d = min(n.rightChild);
				n.key = d.key;
				n.setRightChild(deleteMin(n.rightChild));
			}
		}
		return fix(n);
	}

	private LeftLeanRedBlackTreeNode<K> moveRight(LeftLeanRedBlackTreeNode<K> n) {
		flipColor(n);
		if (n.leftChild != null && isRed(n.leftChild.leftChild)) {
			// it's a 5-node break it into 2 3-node
			n = rightRotate(n);
			flipColor(n);
		}
		return n;
	}

	private LeftLeanRedBlackTreeNode<K> deleteMax(LeftLeanRedBlackTreeNode<K> n) {
		if (isRed(n.leftChild)) {
			n = rightRotate(n);
		}
		if (n.rightChild == null) { // delete n
			return null;
		}
		if (!isRed(n.rightChild) && n.rightChild != null && !isRed(n.rightChild.leftChild)) {
			// 2-node, make it 3
			n = moveRight(n);
		}
		n.setRightChild(deleteMax(n.rightChild));
		return fix(n);
	}

	private LeftLeanRedBlackTreeNode<K> moveLeft(LeftLeanRedBlackTreeNode<K> n) {
		flipColor(n);
		if (n.rightChild != null && isRed(n.rightChild.leftChild)) {
			// it's a 5-node break it into 2 3-node
			n.setRightChild(rightRotate(n.rightChild));
			n = leftRotate(n);
			flipColor(n);
		}
		return n;
	}

	private LeftLeanRedBlackTreeNode<K> deleteMin(LeftLeanRedBlackTreeNode<K> n) {
		if (n.leftChild == null) {
			return null;
		}
		if (!isRed(n.leftChild) && n.leftChild != null && !isRed(n.leftChild.leftChild)) {
			n = moveLeft(n);
		}
		n.setLeftChild(deleteMin(n.leftChild));
		return fix(n);
	}
}
