package tree;

public abstract class AbstractRedBlackTreeNode<K extends Comparable<K>, N extends AbstractRedBlackTreeNode<K, N>> {

	protected K key;
	protected N leftChild;
	protected N rightChild;
	protected N parent;
	protected Color color;

	public AbstractRedBlackTreeNode(K key, Color color) {
		this.key = key;
		this.color = color;
	}

	public void setLeftChild(N n) {
		this.leftChild = n;
		if (n != null) {
			n.parent = (N) this;
		}
	}

	public void setRightChild(N n) {
		this.rightChild = n;
		if (n != null) {
			n.parent = (N) this;
		}
	}

	@Override
	public String toString() {
		return "[" + key + ", " + color + "]";
	}
}