package tree;

import java.util.Arrays;
import java.util.List;

import math.SamplingAndShuffling;

public class IntervalTree extends RedBlackTree<Interval, IntervalTreeNode> {

	public static void main(String[] args) {
		test();
	}

	private static void test() {
		IntervalTree it = new IntervalTree();
		int[] k1 = new int[] { 5, 6, 4, 3, 2, 0, 1 };
		int[] k2 = new int[] { 0, 3, 2, 1, 5, 6, 4 };
		Interval[] intv = new Interval[7];
		for (int i = 0; i < intv.length; i++) {
			intv[i] = new Interval(Math.min(k1[i], k2[i]), Math.max(k1[i], k2[i]));
		}
		System.out.println("Insert");
		System.out.println(Arrays.toString(intv));
		for (Interval i : intv) {
			it.insert(i);
			it.print();
		}

		System.out.println("SearchOverlap");
		Interval[] si = new Interval[] { new Interval(1, 2), new Interval(-1, -1), new Interval(7, 8),
				new Interval(1, 1), new Interval(-1, 8) };
		for (int i = 0; i < si.length; i++) {
			System.out.println(si[i] + ":" + it.searchOverlap(si[i]));
		}

		System.out.println("Remove");
		System.out.println(Arrays.toString(intv));
		for (Interval i : intv) {
			it.delete(i);
			it.print();
		}
	}

	private static void testRandom() {
		IntervalTree it = new IntervalTree();
		int[] k1 = SamplingAndShuffling.shuffle(7);
		int[] k2 = SamplingAndShuffling.shuffle(7);
		System.out.println(Arrays.toString(k1));
		System.out.println(Arrays.toString(k2));
		Interval[] intv = new Interval[7];
		for (int i = 0; i < intv.length; i++) {
			intv[i] = new Interval(Math.min(k1[i], k2[i]), Math.max(k1[i], k2[i]));
		}
		System.out.println("Insert");
		System.out.println(Arrays.toString(intv));
		for (Interval i : intv) {
			it.insert(i);
			it.print();
		}
		System.out.println("Remove");
		System.out.println(Arrays.toString(intv));
		for (Interval i : intv) {
			it.delete(i);
			it.print();
		}
	}

	/**
	 * Find a interval that is overlap with the give interval or return null if
	 * there is not any overlap
	 */
	public IntervalTreeNode searchOverlap(Interval i) {
		return searchOverlap(root, i);
	}

	private IntervalTreeNode searchOverlap(IntervalTreeNode r, Interval i) {
		if (r == null) {
			return null;
		}
		boolean result = Interval.isOverlap(r.key, i);
		if (result) {
			return r;
		} else if (r.leftChild != null && r.leftChild.maxRight >= i.left) {
			return searchOverlap(r.leftChild, i);
		} else {
			return searchOverlap(r.rightChild, i);
		}

	}

	private void fixMaxRight(IntervalTreeNode r) {
		r.maxRight = Math.max(r.key.right, r.leftChild == null ? Double.NEGATIVE_INFINITY : r.leftChild.maxRight);
		r.maxRight = Math.max(r.maxRight, r.rightChild == null ? Double.NEGATIVE_INFINITY : r.rightChild.maxRight);
	}

	@Override
	protected IntervalTreeNode leftRotate(IntervalTreeNode r) {
		IntervalTreeNode rt = super.leftRotate(r);
		fixMaxRight(rt.leftChild);
		fixMaxRight(rt);
		return rt;
	}

	@Override
	protected IntervalTreeNode rightRotate(IntervalTreeNode r) {
		IntervalTreeNode rt = super.rightRotate(r);
		fixMaxRight(rt.rightChild);
		fixMaxRight(rt);
		return rt;
	}

	@Override
	protected IntervalTreeNode insert(Interval k, IntervalTreeNode r) {
		IntervalTreeNode rt = super.insert(k, r);
		fixMaxRight(r);
		return rt;
	}

	@Override
	protected List<IntervalTreeNode> delete(Interval k, IntervalTreeNode r) {
		List<IntervalTreeNode> l = super.delete(k, r);
		IntervalTreeNode deleteNode = l.get(0);
		if (r.key.compareTo(deleteNode.key) == 0) {
			IntervalTreeNode n = deleteNode;
			while (n != r && n.parent != null) {
				n = n.parent;
				fixMaxRight(n);
			}
		} else {
			fixMaxRight(r);
		}
		return l;
	}

	@Override
	protected IntervalTreeNode newNode(Interval k, Color c) {
		return new IntervalTreeNode(k, c);
	}
}

class IntervalTreeNode extends AbstractRedBlackTreeNode<Interval, IntervalTreeNode> {

	double maxRight;

	public IntervalTreeNode(Interval key, Color color) {
		super(key, color);
		maxRight = key.right;
	}

	@Override
	public String toString() {
		return "(" + key + ", " + maxRight + ")";
	}

}

class Interval implements Comparable<Interval> {
	double left;
	double right;

	public Interval(double left, double right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int compareTo(Interval that) {
		int result = Double.compare(left, that.left);
		if (result != 0) {
			return result;
		} else {
			return Double.compare(right, that.right);
		}
	}

	@Override
	public String toString() {
		return "[" + left + ", " + right + "]";
	}

	public static boolean isOverlap(Interval a, Interval b) {
		if (a.left > b.right || b.left > a.right) {
			return false;
		}
		return true;
	}
}