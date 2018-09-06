package tree;

public class LeftLeanRedBlackTreeNode<K extends Comparable<K>> {

	protected K key;
	protected LeftLeanRedBlackTreeNode<K> leftChild;
	protected LeftLeanRedBlackTreeNode<K> rightChild;
	protected Color color;

	public LeftLeanRedBlackTreeNode(K key) {
		this.key = key;
	}

	public LeftLeanRedBlackTreeNode(K key, Color color) {
		this(key);
		this.color = color;
	}

	public void setLeftChild(LeftLeanRedBlackTreeNode<K> n) {
		this.leftChild = n;
	}

	public void setRightChild(LeftLeanRedBlackTreeNode<K> n) {
		this.rightChild = n;
	}

	@Override
	public String toString() {
		return "[" + key + ", " + color + "]";
	}
}
