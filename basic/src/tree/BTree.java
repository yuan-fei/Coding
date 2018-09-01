package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BTree<K extends Comparable<K>> {

	public static void main(String[] args) {
		BTree<String> bt = new BTree<String>(2);
		bt.insert("G").insert("M");
		bt.print();
		bt.insert("P");
		bt.print();
		bt.insert("X");
		bt.print();
		bt.insert("A").insert("C");
		bt.print();
		bt.insert("D").insert("E");
		bt.print();
		bt.insert("J");
		bt.print();
		bt.insert("K");
		bt.print();
		bt.insert("N").insert("O");
		bt.print();
		bt.insert("R").insert("S");
		bt.print();
		bt.insert("T").insert("U").insert("V");
		bt.print();
		bt.insert("Y").insert("Z");
		bt.print();
		bt.insert("W");
		bt.print();
		bt.delete("W");
		bt.print();
		bt.delete("M");
		bt.print();
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		String i = in.next();
		while (i != " ") {
			bt.delete(i);
			bt.print();
			i = in.next();
		}
		in.close();
	}

	int minDegree;
	BTreeNode<K> root;

	public BTree(int minDegree) {
		this.minDegree = minDegree;
		newRoot();
	}

	private void newRoot() {
		root = new BTreeNode<K>(minDegree, true);
	}

	public SearchResult<K> search(K key) {
		return searchRecursive(root, key);
	}

	public SearchResult<K> searchRecursive(BTreeNode<K> root, K key) {
		int index = Collections.binarySearch(root.keys, key);
		if (index >= 0) {
			return new SearchResult<K>(root, index);
		} else {
			if (root.isLeaf) {
				return null;
			} else {
				return searchRecursive(root.children.get(-index - 1), key);
			}
		}
	}

	/**
	 * split parent's ith child who is a full node which has 2 * minDegree-1 keys
	 * and 2 * minDegree children into 2 node
	 */
	private void splitFullChild(BTreeNode<K> parent, int i, BTreeNode<K> child) {
		BTreeNode<K> newChild = new BTreeNode<K>(minDegree, child.isLeaf);
		newChild.keys.addAll(child.keys.subList(minDegree, 2 * minDegree - 1));
		for (int j = 2 * minDegree - 2; j > minDegree - 1; j--) {
			child.keys.remove(j);
		}
		K elevateKey = child.keys.remove(child.keys.size() - 1);
		if (!child.isLeaf) {
			newChild.children.addAll(child.children.subList(minDegree, 2 * minDegree));
			for (int j = 2 * minDegree - 1; j > minDegree - 1; j--) {
				child.children.remove(j);
			}
		}
		parent.keys.add(i, elevateKey);
		parent.children.add(i + 1, newChild);
	}

	/** Forward only insertion (no-backtrack) */
	public BTree<K> insert(K key) {
		BTreeNode<K> node = root;
		if (node.isFull()) {
			newRoot();
			root.isLeaf = false;
			root.children.add(node);
			splitFullChild(root, 0, node);
		}
		insertNonFull(root, key);
		return this;
	}

	private void insertNonFull(BTreeNode<K> node, K key) {
		int index = Collections.binarySearch(node.keys, key);
		if (index >= 0) {
			throw new IllegalArgumentException("Duplicated element!");
		}
		if (node.isLeaf) {
			node.keys.add(-index - 1, key);
		} else {
			int target = -index - 1;
			BTreeNode<K> child = node.children.get(target);
			if (child.isFull()) {
				splitFullChild(node, target, child);
				if (node.keys.get(target).compareTo(key) < 0) {
					target++;
				}
			}
			insertNonFull(node.children.get(target), key);
		}
	}

	private K subMax(BTreeNode<K> node) {
		if (node.isLeaf) {
			return node.keys.get(node.keys.size() - 1);
		} else {
			return subMax(node.children.get(node.children.size() - 1));
		}
	}

	private K subMin(BTreeNode<K> node) {
		if (node.isLeaf) {
			return node.keys.get(0);
		} else {
			return subMin(node.children.get(0));
		}
	}

	public void delete(K key) {
		deleteRecursive(root, key);
	}

	private void deleteRecursive(BTreeNode<K> node, K key) {
		if (node.isLeaf) {
			node.keys.remove(key);
		} else {
			int index = Collections.binarySearch(node.keys, key);
			if (index >= 0) { // found key
				BTreeNode<K> leftChild = node.children.get(index);
				BTreeNode<K> rightChild = node.children.get(index + 1);
				if (leftChild.keys.size() >= minDegree) { // left child has enough keys
					K predecessor = subMax(leftChild);
					deleteRecursive(leftChild, predecessor);
					node.keys.set(index, predecessor);
				} else if (rightChild.keys.size() >= minDegree) {// right child has enough keys
					K successor = subMin(rightChild);
					deleteRecursive(rightChild, successor);
					node.keys.set(index, successor);
				} else { // left child + right child = 2 * minDegree-2: merge left and right
					node.keys.remove(index);
					node.children.remove(index + 1);
					leftChild.keys.add(key);
					leftChild.keys.addAll(rightChild.keys);
					leftChild.children.addAll(rightChild.children);
					deleteRecursive(leftChild, key);
				}
			} else {
				int target = -index - 1;
				BTreeNode<K> child = node.children.get(target);
				if (child.keys.size() == minDegree - 1) {// child has left sibling
					if (target > 0 && node.children.get(target - 1).keys.size() > minDegree - 1) {
						BTreeNode<K> leftSibling = node.children.get(target - 1);
						// borrow from left sibling
						K keyBorrowedFromParent = node.keys.get(target - 1);
						K keyBorrowedFromLeftSibling = leftSibling.keys.remove(leftSibling.keys.size() - 1);
						node.keys.set(target - 1, keyBorrowedFromLeftSibling);
						child.keys.add(0, keyBorrowedFromParent);
						if (!child.isLeaf) {
							BTreeNode<K> childBorrowedFromLeftSibling = leftSibling.children
									.remove(leftSibling.children.size() - 1);
							child.children.add(0, childBorrowedFromLeftSibling);
						}

					} else if (target < node.children.size() - 1
							&& node.children.get(target + 1).keys.size() > minDegree - 1) {// child has right sibling
						BTreeNode<K> rightSibling = node.children.get(target + 1);
						K keyBorrowedFromParent = node.keys.get(target);
						K keyBorrowedFromRightSibling = rightSibling.keys.remove(0);
						node.keys.set(target, keyBorrowedFromRightSibling);
						child.keys.add(keyBorrowedFromParent);
						if (!child.isLeaf) {
							BTreeNode<K> childBorrowedFromRightSibling = rightSibling.children.remove(0);
							child.children.add(childBorrowedFromRightSibling);
						}
					} else {
						if (target > 0) { // child has left sibling
							BTreeNode<K> leftSibling = node.children.get(target - 1);
							K keyBorrowedFromParent = node.keys.remove(target - 1);
							node.children.remove(target);
							leftSibling.keys.add(keyBorrowedFromParent);
							leftSibling.keys.addAll(child.keys);
							leftSibling.children.addAll(child.children);
							child = leftSibling;
						} else if (target < node.children.size() - 1) { // child has left sibling
							BTreeNode<K> rightSibling = node.children.get(target + 1);
							K keyBorrowedFromParent = node.keys.remove(target + 1);
							node.children.remove(target + 1);
							child.keys.add(keyBorrowedFromParent);
							child.keys.addAll(rightSibling.keys);
							child.children.addAll(rightSibling.children);
						} else {
							// This is weird!
						}
					}
				}
				deleteRecursive(child, key);
			}
		}
	}

	public void print() {
		Queue<BTreeNode<K>> q = new LinkedList<BTreeNode<K>>();
		q.offer(root);
		while (!q.isEmpty()) {
			int cnt = q.size();
			String s = "";
			for (int i = 0; i < cnt; i++) {
				BTreeNode<K> n = q.poll();
				s += ("[" + n + "], ");
				if (!n.isLeaf) {
					q.addAll(n.children);
				}
			}
			System.out.println(s);
		}
		System.out.println();
	}
}

class SearchResult<K extends Comparable<K>> {
	BTreeNode<K> n;
	int index;

	public SearchResult(BTreeNode<K> n, int index) {
		super();
		this.n = n;
		this.index = index;
	}

}