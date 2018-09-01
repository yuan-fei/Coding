package tree;

public class RedBlackTreeNode<K extends Comparable<K>> {

	K key;
	RedBlackTreeNode<K> parent;
	RedBlackTreeNode<K> leftChild;
	RedBlackTreeNode<K> rightChild;
	Color color;

	public RedBlackTreeNode(K key) {
		this.key = key;
	}

	public RedBlackTreeNode(K key, Color color) {
		this(key);
		this.color = color;
	}

	public void setLChild(RedBlackTreeNode<K> n) {
		this.leftChild = n;
		if (n != null) {
			n.parent = this;
		}
	}

	public void setRChild(RedBlackTreeNode<K> n) {
		this.rightChild = n;
		if (n != null) {
			n.parent = this;
		}
	}

	@Override
	public String toString() {
		return "[" + key + ", " + color + "]";
	}
}

enum Color {
	R, B
}