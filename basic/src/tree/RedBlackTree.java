package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import math.SamplingAndShuffling;

public class RedBlackTree<K extends Comparable<K>> {

	public static void main(String[] args) {
		testRandom();
	}

	private static void test() {
		RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
		int[] k = new int[] { 1, 2, 4, 0, 6, 5, 3 };
		System.out.println("Insert");
		System.out.println(Arrays.toString(k));
		for (int i : k) {
			rbt.insert(i);
			rbt.print();
		}
		System.out.println("Remove");
		k = new int[] { 5, 6, 1, 2, 4, 3, 0 };
		System.out.println(Arrays.toString(k));
		for (int i : k) {
			rbt.delete(i);
			rbt.print();
		}
	}

	private static void testRandom() {
		RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
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

	RedBlackTreeNode<K> root;

	public void print() {
		Queue<RedBlackTreeNode<K>> q = new LinkedList<RedBlackTreeNode<K>>();
		q.offer(root);
		while (!q.isEmpty()) {
			int cnt = q.size();
			String s = "";
			for (int i = 0; i < cnt; i++) {
				RedBlackTreeNode<K> n = q.poll();
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

	public RedBlackTreeNode<K> search(K key) {
		return searchRecursive(key, root);
	}

	public RedBlackTreeNode<K> searchRecursive(K key, RedBlackTreeNode<K> root) {
		if (root == null) {
			return null;
		}
		int result = root.key.compareTo(key);
		if (result == 0) {
			return root;
		} else if (result < 0) {
			return searchRecursive(key, root.leftChild);
		} else {
			return searchRecursive(key, root.rightChild);
		}
	}

	private boolean isNullOrBlack(RedBlackTreeNode<K> r) {
		return r == null || r.color == Color.B;
	}

	private boolean isRed(RedBlackTreeNode<K> r) {
		return r != null && r.color == Color.R;
	}

	public RedBlackTree<K> insert(K k) {
		RedBlackTreeNode<K> insertedNode;
		if (root == null) {
			root = new RedBlackTreeNode<K>(k, Color.B);
			insertedNode = root;
		} else {
			insertedNode = insert(k, root);
		}
		// print();
		// System.out.println("after fix: ");
		fixUpInsert(insertedNode);
		return this;
	}

	private RedBlackTreeNode<K> insert(K k, RedBlackTreeNode<K> r) {
		if (r != null) {
			int result = k.compareTo(r.key);
			if (result < 0) {
				if (r.leftChild == null) {
					r.setLChild(new RedBlackTreeNode<K>(k, Color.R));
					return r.leftChild;
				} else {
					return insert(k, r.leftChild);
				}
			} else if (result > 0) {
				if (r.rightChild == null) {
					r.setRChild(new RedBlackTreeNode<K>(k, Color.R));
					return r.rightChild;
				} else {
					return insert(k, r.rightChild);
				}
			} else {
				throw new IllegalArgumentException("duplicate key: " + k);
			}
		} else {
			return null;
		}
	}

	private void fixUpInsert(RedBlackTreeNode<K> r) {
		while (true) {
			if (r.parent == null) { // root
				r.color = Color.B;
				return;
			} else if (r.parent.color == Color.B) {
				return;
			} else { // red parent, need to be fixed
				if (r.parent.parent == null) { // height = 2
					r.parent.color = Color.B;
				} else {
					// red r with no sibling, red parent, black grandfather,
					if (r.parent.parent.leftChild == r.parent) { // parent is left child of grandfather
						if (isRed(r.parent.parent.rightChild)) {
							// case 1: red uncle
							flipColor(r.parent.parent);
							r = r.parent.parent;
						} else {
							// black uncle
							if (r == r.parent.rightChild) { // case 2
								r = r.parent;
								leftRotate(r);
							}
							// case 3
							r.parent.color = Color.B;
							r.parent.parent.color = Color.R;
							rightRotate(r.parent.parent);
						}
					} else {
						if (isRed(r.parent.parent.leftChild)) {
							flipColor(r.parent.parent);
							r = r.parent.parent;
						} else {
							if (r == r.parent.leftChild) {
								r = r.parent;
								rightRotate(r);
							}
							r.parent.color = Color.B;
							r.parent.parent.color = Color.R;
							leftRotate(r.parent.parent);
						}
					}
				}
			}
		}
	}

	/** Assume r.rightChild!=null */
	private void leftRotate(RedBlackTreeNode<K> r) {
		RedBlackTreeNode<K> y = r.rightChild;
		r.setRChild(y.leftChild);
		if (r.parent == null) {
			root = y;
			y.parent = null;
		} else {
			if (r.parent.leftChild == r) {
				r.parent.setLChild(y);
			} else {
				r.parent.setRChild(y);
			}
		}
		y.setLChild(r);
	}

	/** Assume r.leftChild!=null */
	private void rightRotate(RedBlackTreeNode<K> r) {
		RedBlackTreeNode<K> y = r.leftChild;
		r.setLChild(y.rightChild);
		if (r.parent == null) {
			root = y;
			y.parent = null;
		} else {
			if (r.parent.leftChild == r) {
				r.parent.setLChild(y);
			} else {
				r.parent.setRChild(y);
			}
		}
		y.setRChild(r);
	}

	private void flipColor(RedBlackTreeNode<K> r) {
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

	public void delete(K k) {
		List<RedBlackTreeNode<K>> nodes = deleteRecursive(k, root);
		print();
		System.out.println("fix");
		RedBlackTreeNode<K> deleteNode = nodes.get(0);
		RedBlackTreeNode<K> fixNode = nodes.get(1);
		if (deleteNode == root) {
			root = null;
		} else if (deleteNode.color == Color.B) {
			fixUpDelete(deleteNode.parent, fixNode);
		}
	}

	/** Assume r is p's child, and p is imbalanced with left and right child */
	private void fixUpDelete(RedBlackTreeNode<K> p, RedBlackTreeNode<K> x) {
		while (true) {
			if (p == null) { // x is root
				x.color = Color.B;
				return;
			} else if (isRed(x)) { // increase bh by 1
				x.color = Color.B;
				return;
			} else { // p is not null, p.rightChild is not null, and r is black or null
				if (p.leftChild == x) { // left child case
					if (isRed(p.rightChild)) { // p must be black
						p.rightChild.color = Color.B;
						p.color = Color.R;
						leftRotate(p);
					}
					// p's right child is black
					if (isNullOrBlack(p.rightChild.leftChild) && isNullOrBlack(p.rightChild.rightChild)) {
						// p.rightChild has both child black
						p.rightChild.color = Color.R;
						x = p;
						p = p.parent;
					} else {
						// p.rightChild has at least 1 red child
						if (isRed(p.rightChild.leftChild)) {
							// p.rightChild has red left child
							p.rightChild.leftChild.color = Color.B;
							p.rightChild.color = Color.R;
							rightRotate(p.rightChild);
						}
						// p.rightChild has red right child
						p.rightChild.color = p.color;
						p.rightChild.rightChild.color = Color.B;
						p.color = Color.B;
						leftRotate(p);
						p = null;
						x = root;
					}
				} else { // right child case
					if (isRed(p.leftChild)) {
						p.leftChild.color = Color.B;
						p.color = Color.R;
						rightRotate(p);
					}
					if (isNullOrBlack(p.leftChild.leftChild) && isNullOrBlack(p.leftChild.rightChild)) {
						p.leftChild.color = Color.R;
						x = p;
						p = p.parent;
					} else {
						if (isRed(p.leftChild.rightChild)) {
							p.leftChild.rightChild.color = Color.B;
							p.leftChild.color = Color.R;
							leftRotate(p.leftChild);
						}
						p.leftChild.color = p.color;
						p.leftChild.leftChild.color = Color.B;
						p.color = Color.B;
						rightRotate(p);
						p = null;
						x = root;
					}
				}
			}
		}
	}

	/** Assume r is not null */
	private List<RedBlackTreeNode<K>> deleteRecursive(K k, RedBlackTreeNode<K> r) {

		RedBlackTreeNode<K> deleteNode = null;
		RedBlackTreeNode<K> fixNode = null;
		int result = k.compareTo(r.key);
		if (result < 0) {
			if (r.leftChild != null) {
				return deleteRecursive(k, r.leftChild);
			}
		} else if (result > 0) {
			if (r.rightChild != null) {
				return deleteRecursive(k, r.rightChild);
			}
		} else {
			if (r.leftChild == null && r.rightChild == null) {
				if (r.parent != null) {
					if (r.parent.leftChild == r) {
						r.parent.setLChild(null);
					} else {
						r.parent.setRChild(null);
					}
				}
				deleteNode = r;
			} else {
				if (r.leftChild != null) {
					deleteNode = findMax(r.leftChild);
					if (deleteNode.parent.leftChild == deleteNode) {
						deleteNode.parent.setLChild(deleteNode.leftChild);
						fixNode = deleteNode.leftChild;
					} else {
						deleteNode.parent.setRChild(deleteNode.leftChild);
						fixNode = deleteNode.leftChild;
					}

				} else {
					deleteNode = findMin(r.rightChild);
					if (deleteNode.parent.leftChild == deleteNode) {
						deleteNode.parent.setLChild(deleteNode.rightChild);
						fixNode = deleteNode.rightChild;
					} else {
						deleteNode.parent.setRChild(deleteNode.rightChild);
						fixNode = deleteNode.rightChild;
					}
				}
				r.key = deleteNode.key;
			}
		}
		return Arrays.asList(deleteNode, fixNode);
	}

	private RedBlackTreeNode<K> findMin(RedBlackTreeNode<K> r) {
		if (r.leftChild != null) {
			return findMin(r.leftChild);
		}
		return r;
	}

	private RedBlackTreeNode<K> findMax(RedBlackTreeNode<K> r) {
		if (r.rightChild != null) {
			return findMax(r.rightChild);
		}
		return r;
	}
}
