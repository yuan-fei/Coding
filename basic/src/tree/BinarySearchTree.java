package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class BinarySearchTree<K extends Comparable<K>> implements ISearchable<K> {

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		int[] k = new int[] { 1, 2, 4, 0, 6, 5, 3 };
		System.out.println("Insert");
		System.out.println(Arrays.toString(k));
		for (int i : k) {
			bst.insert(i);
			bst.print();
			System.out.println("min-max" + bst.minimum() + ":" + bst.maximum());
		}

		System.out.println("pre-succ");
		for (int i : k) {
			System.out.println(i + ":" + bst.predecessor(i) + ":" + bst.successor(i));
		}
		System.out.println("Remove");
		k = new int[] { 5, 6, 1, 2, 4, 3, 0 };
		System.out.println(Arrays.toString(k));
		for (int i : k) {
			bst.delete(i);
			bst.print();
			System.out.println("min-max" + bst.minimum() + ":" + bst.maximum());
		}
	}

	BinaryTreeNode<K> root;

	public void print() {
		Queue<BinaryTreeNode<K>> q = new LinkedList<BinaryTreeNode<K>>();
		q.offer(root);
		while (!q.isEmpty()) {
			int cnt = q.size();
			String s = "";
			for (int i = 0; i < cnt; i++) {
				BinaryTreeNode<K> n = q.poll();
				s += (n + ",");
				if (n != null) {
					q.offer(n.left);
					q.offer(n.right);
				}
			}
			System.out.println(s);
		}
		System.out.println();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.ISearchable#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.ISearchable#exists(K)
	 */
	@Override
	public boolean member(K k) {
		return search(root, k) != null;
	}

	private BinaryTreeNode<K> search(BinaryTreeNode<K> r, K k) {
		if (r == null) {
			return null;
		} else {
			int cmp = r.key.compareTo(k);
			if (cmp == 0) {
				return r;
			} else if (cmp > 0) {
				return search(r.left, k);
			} else {
				return search(r.right, k);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.ISearchable#insert(K)
	 */
	@Override
	public void insert(K k) {
		root = insert(root, k);
	}

	private BinaryTreeNode<K> insert(BinaryTreeNode<K> r, K k) {
		if (r != null) {
			int cmp = r.key.compareTo(k);
			if (cmp == 0) {
				throw new IllegalArgumentException("Duplicate key: " + k);
			} else if (cmp > 0) {
				r.left = insert(r.left, k);
			} else {
				r.right = insert(r.right, k);
			}
			return r;
		} else {
			return new BinaryTreeNode<K>(k);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.ISearchable#delete(K)
	 */
	@Override
	public void delete(K k) {
		root = delete(root, k);
	}

	private BinaryTreeNode<K> delete(BinaryTreeNode<K> r, K k) {
		if (r != null) {
			int cmp = r.key.compareTo(k);
			if (cmp == 0) {
				if (r.left == null) {
					return r.right;
				} else if (r.right == null) {
					return r.left;
				} else {
					r.key = minimum(r.right).key;
					r.right = delete(r.right, r.key);
					return r;
				}
			} else if (cmp > 0) {
				r.left = delete(r.left, k);
			} else {
				r.right = delete(r.right, k);
			}
		}
		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.ISearchable#minimum()
	 */
	@Override
	public Optional<K> minimum() {
		BinaryTreeNode<K> min = minimum(root);
		if (min == null) {
			return Optional.empty();
		} else {
			return Optional.of(min.key);
		}
	}

	private BinaryTreeNode<K> minimum(BinaryTreeNode<K> r) {
		if (r == null) {
			return null;
		} else {
			if (r.left != null) {
				return minimum(r.left);
			} else {
				return r;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.ISearchable#maximum()
	 */
	@Override
	public Optional<K> maximum() {
		BinaryTreeNode<K> max = maximum(root);
		if (max == null) {
			return Optional.empty();
		} else {
			return Optional.of(max.key);
		}
	}

	private BinaryTreeNode<K> maximum(BinaryTreeNode<K> r) {
		if (r == null) {
			return null;
		} else {
			if (r.right != null) {
				return maximum(r.right);
			} else {
				return r;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.ISearchable#predecessor(K)
	 */
	@Override
	public Optional<K> predecessor(K k) {
		if (member(k)) {
			BinaryTreeNode<K> pre = predecessor(root, k);
			if (pre != null) {
				return Optional.of(pre.key);
			}
		}
		return Optional.empty();
	}

	private BinaryTreeNode<K> predecessor(BinaryTreeNode<K> r, K k) {
		if (r == null) {
			return null;
		} else {
			int cmp = r.key.compareTo(k);
			if (cmp == 0) {
				return maximum(r.left);
			} else if (cmp > 0) {
				return predecessor(r.left, k);
			} else {
				BinaryTreeNode<K> pre = predecessor(r.right, k);
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
			BinaryTreeNode<K> succ = successor(root, k);
			if (succ != null) {
				return Optional.of(succ.key);
			}
		}
		return Optional.empty();
	}

	private BinaryTreeNode<K> successor(BinaryTreeNode<K> r, K k) {
		if (r == null) {
			return null;
		} else {
			int cmp = r.key.compareTo(k);
			if (cmp == 0) {
				return minimum(r.right);
			} else if (cmp < 0) {
				return successor(r.right, k);
			} else {
				BinaryTreeNode<K> succ = successor(r.left, k);
				if (succ == null) {
					return r;
				} else {
					return succ;
				}
			}
		}

	}
}

class BinaryTreeNode<K extends Comparable<K>> {
	public BinaryTreeNode(K k) {
		this.key = k;
	}

	BinaryTreeNode<K> left, right;
	K key;

	@Override
	public String toString() {
		return key.toString();
	}
}