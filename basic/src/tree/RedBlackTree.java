package tree;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import math.SamplingAndShuffling;

public class RedBlackTree<K extends Comparable<K>, N extends AbstractRedBlackTreeNode<K, N>> implements ISearchable<K> {

	public static void main(String[] args) {
		testRandom();
	}

	private static void test() {
		RedBlackTree<Integer, RedBlackTreeNode<Integer>> rbt = new RedBlackTree<Integer, RedBlackTreeNode<Integer>>();
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
		RedBlackTree<Integer, RedBlackTreeNode<Integer>> rbt = new RedBlackTree<Integer, RedBlackTreeNode<Integer>>();
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

	N root;

	public void print() {
		Queue<N> q = new LinkedList<N>();
		q.offer(root);
		while (!q.isEmpty()) {
			int cnt = q.size();
			String s = "";
			for (int i = 0; i < cnt; i++) {
				N n = q.poll();
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

	public N search(K key) {
		return search(key, root);
	}

	public N search(K key, N root) {
		if (root == null) {
			return null;
		}
		int result = root.key.compareTo(key);
		if (result == 0) {
			return root;
		} else if (result < 0) {
			return search(key, root.leftChild);
		} else {
			return search(key, root.rightChild);
		}
	}

	private boolean isNullOrBlack(N r) {
		return r == null || r.color == Color.B;
	}

	private boolean isRed(N r) {
		return r != null && r.color == Color.R;
	}

	public void insert(K k) {
		N insertedNode;
		if (root == null) {
			root = newNode(k, Color.B);
			insertedNode = root;
		} else {
			insertedNode = insert(k, root);
		}
		// print();
		// System.out.println("after fix: ");
		fixUpInsert(insertedNode);
	}

	protected N newNode(K k, Color c) {
		Class<N> claz = (Class<N>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		try {
			return (N) claz.getConstructor(k.getClass(), c.getClass()).newInstance(k, c);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	protected N insert(K k, N r) {
		if (r != null) {
			int result = k.compareTo(r.key);
			if (result < 0) {
				if (r.leftChild == null) {
					r.setLeftChild(newNode(k, Color.R));
					return r.leftChild;
				} else {
					return insert(k, r.leftChild);
				}
			} else if (result > 0) {
				if (r.rightChild == null) {
					r.setRightChild(newNode(k, Color.R));
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

	private void fixUpInsert(N r) {
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
	protected N leftRotate(N r) {
		N y = r.rightChild;
		r.setRightChild(y.leftChild);
		if (r.parent == null) {
			root = y;
			y.parent = null;
		} else {
			if (r.parent.leftChild == r) {
				r.parent.setLeftChild(y);
			} else {
				r.parent.setRightChild(y);
			}
		}
		y.setLeftChild(r);
		return y;
	}

	/** Assume r.leftChild!=null */
	protected N rightRotate(N r) {
		N y = r.leftChild;
		r.setLeftChild(y.rightChild);
		if (r.parent == null) {
			root = y;
			y.parent = null;
		} else {
			if (r.parent.leftChild == r) {
				r.parent.setLeftChild(y);
			} else {
				r.parent.setRightChild(y);
			}
		}
		y.setRightChild(r);
		return y;
	}

	private void flipColor(N r) {
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
		List<N> nodes = delete(k, root);
		print();
		System.out.println("fix");
		N deleteNode = nodes.get(0);
		N fixNode = nodes.get(1);
		if (deleteNode == root) {
			root = null;
		} else if (deleteNode.color == Color.B) {
			fixUpDelete(deleteNode.parent, fixNode);
		}
	}

	/** Assume r is p's child, and p is imbalanced with left and right child */
	private void fixUpDelete(N p, N x) {
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
	protected List<N> delete(K k, N r) {

		N deleteNode = null;
		N fixNode = null;
		int result = k.compareTo(r.key);
		if (result < 0) {
			if (r.leftChild != null) {
				return delete(k, r.leftChild);
			}
		} else if (result > 0) {
			if (r.rightChild != null) {
				return delete(k, r.rightChild);
			}
		} else {
			if (r.leftChild == null && r.rightChild == null) {
				if (r.parent != null) {
					if (r.parent.leftChild == r) {
						r.parent.setLeftChild(null);
					} else {
						r.parent.setRightChild(null);
					}
				}
				deleteNode = r;
			} else {
				if (r.leftChild != null) {
					deleteNode = findMax(r.leftChild);
					if (deleteNode.parent.leftChild == deleteNode) {
						deleteNode.parent.setLeftChild(deleteNode.leftChild);
						fixNode = deleteNode.leftChild;
					} else {
						deleteNode.parent.setRightChild(deleteNode.leftChild);
						fixNode = deleteNode.leftChild;
					}
				} else {
					deleteNode = findMin(r.rightChild);
					if (deleteNode.parent.leftChild == deleteNode) {
						deleteNode.parent.setLeftChild(deleteNode.rightChild);
						fixNode = deleteNode.rightChild;
					} else {
						deleteNode.parent.setRightChild(deleteNode.rightChild);
						fixNode = deleteNode.rightChild;
					}
				}
				r.key = deleteNode.key;
			}
		}
		return Arrays.asList(deleteNode, fixNode);
	}

	private N findMin(N r) {
		if (r.leftChild != null) {
			return findMin(r.leftChild);
		}
		return r;
	}

	private N findMax(N r) {
		if (r.rightChild != null) {
			return findMax(r.rightChild);
		}
		return r;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public boolean member(K k) {
		return search(k) != null;
	}

	@Override
	public Optional<K> minimum() {
		N min = findMin(root);
		if (min != null) {
			return Optional.of(min.key);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<K> maximum() {
		N max = findMax(root);
		if (max != null) {
			return Optional.of(max.key);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<K> predecessor(K k) {
		if (member(k)) {
			N pre = predecessor(root, k);
			if (pre != null) {
				return Optional.of(pre.key);
			}
		}
		return Optional.empty();
	}

	private N predecessor(N r, K k) {
		if (r == null) {
			return null;
		} else {
			int cmp = r.key.compareTo(k);
			if (cmp == 0) {
				return findMax(r.leftChild);
			} else if (cmp > 0) {
				return predecessor(r.leftChild, k);
			} else {
				N pre = predecessor(r.rightChild, k);
				if (pre == null) {
					return r;
				} else {
					return pre;
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.ISearchable#successor(K)
	 */
	@Override
	public Optional<K> successor(K k) {
		if (member(k)) {
			N succ = successor(root, k);
			if (succ != null) {
				return Optional.of(succ.key);
			}
		}
		return Optional.empty();
	}

	private N successor(N r, K k) {
		if (r == null) {
			return null;
		} else {
			int cmp = r.key.compareTo(k);
			if (cmp == 0) {
				return findMin(r.rightChild);
			} else if (cmp < 0) {
				return successor(r.rightChild, k);
			} else {
				N succ = successor(r.leftChild, k);
				if (succ == null) {
					return r;
				} else {
					return succ;
				}
			}
		}

	}
}
