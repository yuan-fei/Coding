package tree;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class OrderStatisticsTree<K extends Comparable<K>> extends RedBlackTree<K, OrderStatisticsTreeNode<K>> {

	public static void main(String[] args) {
		test();

	}

	private static void test() {
		OrderStatisticsTree<Integer> ost = new OrderStatisticsTree<Integer>();
		int[] k = new int[] { 1, 2, 4, 0, 6, 5, 3 };
		System.out.println("Insert");
		System.out.println(Arrays.toString(k));
		for (int i : k) {
			ost.insert(i);
			ost.print();
		}
		System.out.println("Select");
		for (int i : IntStream.range(1, 8).toArray()) {
			System.out.println(i);
			System.out.println(ost.select(i));
		}

		System.out.println("Rank");
		for (int i : IntStream.range(0, 7).toArray()) {
			System.out.println(i);
			System.out.println(ost.rank(i));
		}
		System.out.println("Remove");
		k = new int[] { 5, 6, 1, 2, 4, 3, 0 };
		System.out.println(Arrays.toString(k));
		for (int i : k) {
			ost.delete(i);
			ost.print();
		}

	}

	public OrderStatisticsTreeNode<K> select(int k) {
		return select(root, k);
	}

	private OrderStatisticsTreeNode<K> select(OrderStatisticsTreeNode<K> r, int k) {
		if (r == null || r.size < k) {
			return null;
		} else {
			int lSize = (r.leftChild == null ? 0 : r.leftChild.size) + 1;
			if (lSize == k) {
				return r;
			} else if (lSize > k) {
				return select(r.leftChild, k);
			} else {
				return select(r.rightChild, k - lSize);
			}
		}
	}

	public int rank(K x) {
		return rank(root, x);
	}

	private int rank(OrderStatisticsTreeNode<K> r, K x) {
		if (r == null) {
			return -1;
		}
		int result = r.key.compareTo(x);
		int lSize = (r.leftChild == null ? 0 : r.leftChild.size) + 1;
		if (result == 0) {
			return lSize;
		} else if (result > 0) {
			return rank(r.leftChild, x);
		} else {
			int rRank = rank(r.rightChild, x);
			return rRank > 0 ? rRank + lSize : rRank;
		}
	}

	@Override
	protected OrderStatisticsTreeNode<K> leftRotate(OrderStatisticsTreeNode<K> r) {
		OrderStatisticsTreeNode<K> newRoot = super.leftRotate(r);
		OrderStatisticsTreeNode<K> oldRoot = r;
		newRoot.size = oldRoot.size;
		oldRoot.size = (oldRoot.leftChild == null ? 0 : oldRoot.leftChild.size)
				+ (oldRoot.rightChild == null ? 0 : oldRoot.rightChild.size) + 1;
		return newRoot;
	}

	@Override
	protected OrderStatisticsTreeNode<K> rightRotate(OrderStatisticsTreeNode<K> r) {
		OrderStatisticsTreeNode<K> newRoot = super.rightRotate(r);
		OrderStatisticsTreeNode<K> oldRoot = r;
		newRoot.size = oldRoot.size;
		oldRoot.size = (oldRoot.leftChild == null ? 0 : oldRoot.leftChild.size)
				+ (oldRoot.rightChild == null ? 0 : oldRoot.rightChild.size) + 1;
		return newRoot;
	}

	@Override
	protected OrderStatisticsTreeNode<K> insert(K k, OrderStatisticsTreeNode<K> r) {
		OrderStatisticsTreeNode<K> n = super.insert(k, r);
		if (n != null) {
			r.size++;
		}
		return n;
	}

	@Override
	protected List<OrderStatisticsTreeNode<K>> delete(K k, OrderStatisticsTreeNode<K> r) {
		List<OrderStatisticsTreeNode<K>> l = super.delete(k, r);
		OrderStatisticsTreeNode<K> deleteNode = l.get(0);
		if (r.key.compareTo(deleteNode.key) == 0) {
			OrderStatisticsTreeNode<K> n = deleteNode;
			while (n != r && n.parent != null) {
				n = n.parent;
				n.size--;
			}
		} else {
			r.size--;
		}
		return l;
	}

	@Override
	protected OrderStatisticsTreeNode<K> newNode(K k, Color c) {
		return new OrderStatisticsTreeNode<K>(k, c);
	}
}

class OrderStatisticsTreeNode<K extends Comparable<K>> extends AbstractRedBlackTreeNode<K, OrderStatisticsTreeNode<K>> {

	int size;

	public OrderStatisticsTreeNode(K key, Color c) {
		super(key, c);
		size = 1;
	}

	@Override
	public String toString() {
		return "[" + key + ", " + color + ", " + size + "]";
	}
}