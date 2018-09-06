package tree;

class RedBlackTreeNode<K extends Comparable<K>> extends AbstractRedBlackTreeNode<K, RedBlackTreeNode<K>> {

	public RedBlackTreeNode(K key, Color c) {
		super(key, c);
	}

}