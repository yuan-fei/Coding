package tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import math.SamplingAndShuffling;
import utils.Util;

/**
 * Van Emde Boas Tree is a tree whose keys are integers in range 0~size, and all
 * BST operations can be done in O(loglogn)
 */
public class VanEmdeBoasTree {

	public static void main(String[] args) {
		VanEmdeBoasTree t = new VanEmdeBoasTree(16);
		int[] n = new int[] { 2, 3, 4, 5, 7, 14, 15 };
		n = new int[] { 0, 1, 2, 3, 2 };
		int[] r = SamplingAndShuffling.shuffle(n.length);
		System.out.println("insert");
		for (int i = 0; i < r.length; i++) {
			t.insert(n[r[i]]);
		}
		t.print();
		System.out.println("pre-succ");
		for (int i = 0; i < r.length; i++) {
			System.out.println("pre " + n[r[i]] + ":" + t.predecessor(n[i]));
			System.out.println("succ " + n[r[i]] + ":" + t.successor(n[i]));
			System.out.println("member: " + n[r[i]] + "=" + t.member(n[r[i]]));
		}
		System.out.println("member: " + 13 + "=" + t.member(13));
		System.out.println("member: " + 16 + "=" + t.member(16));
		t.print();

		System.out.println("delete");
		r = SamplingAndShuffling.shuffle(n.length);
		for (int i = 0; i < r.length; i++) {
			System.out.println("delete: " + n[r[i]]);
			t.delete(n[r[i]]);
			t.print();
			System.out.println("member: " + n[r[i]] + "=" + t.member(n[r[i]]));
		}
	}

	int size;
	VanEmdeBoasTreeNode root;

	public VanEmdeBoasTree(int size) {
		if (!Util.isPowerOfTwo(size)) {
			throw new IllegalArgumentException("size must be power of 2!");
		}
		this.size = size;
		root = new VanEmdeBoasTreeNode(size, "0");
	}

	public void print() {
		Queue<VanEmdeBoasTreeNode> q = new LinkedList<VanEmdeBoasTreeNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			int cnt = q.size();
			String s = "";
			for (int i = 0; i < cnt; i++) {
				VanEmdeBoasTreeNode n = q.poll();
				s += (n + ",");
				if (n != null && n.size > 2) {
					q.offer(n.summary);
					for (int j = 0; j < n.cluster.length; j++) {
						q.offer(n.cluster[j]);
					}
				}
			}
			System.out.println(s);
		}
		System.out.println();

	}

	public boolean member(int x) {
		if (isValid(x)) {
			return member(root, x);
		} else {
			return false;
		}
	}

	private boolean isValid(int x) {
		return x < root.size && x >= 0;
	}

	private boolean member(VanEmdeBoasTreeNode r, int x) {
		if ((r.min.isPresent() && r.min.get().equals(x)) || (r.max.isPresent() && r.max.get().equals(x))) {
			return true;
		} else if (r.size == 2) {
			return false;
		} else {
			return member(r.cluster[r.getCluster(x)], r.getOffset(x));
		}
	}

	public Optional<Integer> minimum() {
		return minimum(root);
	}

	private Optional<Integer> minimum(VanEmdeBoasTreeNode r) {
		return r.min;
	}

	public Optional<Integer> maximum() {
		return maximum(root);
	}

	private Optional<Integer> maximum(VanEmdeBoasTreeNode r) {
		return r.max;
	}

	public Optional<Integer> predecessor(int x) {
		if (isValid(x)) {
			return predecessor(root, x);
		} else {
			return Optional.empty();
		}
	}

	private Optional<Integer> predecessor(VanEmdeBoasTreeNode r, int x) {
		if (r.size == 2) {
			if (r.min.isPresent() && r.min.get() < x) {
				return r.min;
			} else {
				return Optional.empty();
			}
		} else if (r.max.isPresent() && x > r.max.get()) {
			return r.max;
		} else {
			Optional<Integer> minOffset = minimum(r.cluster[r.getCluster(x)]);
			if (minOffset.isPresent() && minOffset.get() < r.getOffset(x)) {
				int offset = predecessor(r.cluster[r.getCluster(x)], r.getOffset(x)).get();
				return Optional.of(r.getIndex(r.getCluster(x), offset));
			} else {
				Optional<Integer> predecessorCluster = predecessor(r.summary, r.getCluster(x));
				if (predecessorCluster.isPresent()) {
					int offset = maximum(r.cluster[predecessorCluster.get()]).get();
					return Optional.of(r.getIndex(predecessorCluster.get(), offset));
				} else {
					if (r.min.isPresent() && x > r.min.get()) {
						return r.min;
					} else {
						return Optional.empty();
					}
				}
			}
		}
	}

	public Optional<Integer> successor(int x) {
		if (isValid(x)) {
			return successor(root, x);
		} else {
			return Optional.empty();
		}

	}

	private Optional<Integer> successor(VanEmdeBoasTreeNode r, int x) {
		if (r.size == 2) {
			if (r.max.isPresent() && r.max.get() > x) {
				return r.max;
			} else {
				return Optional.empty();
			}
		} else if (r.min.isPresent() && x < r.min.get()) {
			return r.min;
		} else {
			Optional<Integer> maxOffset = maximum(r.cluster[r.getCluster(x)]);
			if (maxOffset.isPresent() && maxOffset.get() > r.getOffset(x)) {
				int offset = successor(r.cluster[r.getCluster(x)], r.getOffset(x)).get();
				return Optional.of(r.getIndex(r.getCluster(x), offset));
			} else {
				Optional<Integer> successorCluster = successor(r.summary, r.getCluster(x));
				if (successorCluster.isPresent()) {
					int offset = minimum(r.cluster[successorCluster.get()]).get();
					return Optional.of(r.getIndex(successorCluster.get(), offset));
				} else {
					return Optional.empty();
				}
			}
		}
	}

	public void insert(int x) {
		if (isValid(x)) {
			insert(root, x);
		} else {
			throw new IllegalArgumentException("out of range: " + x);
		}

	}

	private void insertEmptyTree(VanEmdeBoasTreeNode r, int x) {
		r.min = Optional.of(x);
		r.max = Optional.of(x);
	}

	private void insert(VanEmdeBoasTreeNode r, int x) {
		if (!r.min.isPresent()) {
			insertEmptyTree(r, x);
		} else {
			if (x < r.min.get()) {
				int t = r.min.get();
				r.min = Optional.of(x);
				x = t;
				insert(r, x);
			} else {
				if (r.size > 2) {
					if (!minimum(r.cluster[r.getCluster(x)]).isPresent()) {
						insert(r.summary, r.getCluster(x));
						insertEmptyTree(r.cluster[r.getCluster(x)], r.getOffset(x));
					} else {
						insert(r.cluster[r.getCluster(x)], r.getOffset(x));
					}
				}

				if (x > r.max.get()) {
					r.max = Optional.of(x);
				}
			}
		}

	}

	public void delete(int x) {
		if (isValid(x)) {
			delete(root, x);
		} else {
			throw new IllegalArgumentException("out of range: " + x);
		}
	}

	private void delete(VanEmdeBoasTreeNode r, int x) {
		if (!r.min.isPresent()) {
			return;
		} else {
			if (r.size == 2) {
				if (r.min.equals(r.max) && r.min.get().equals(x)) {
					r.min = Optional.empty();
					r.max = Optional.empty();
				} else if (r.min.get().equals(x)) {
					r.min = r.max;
				} else if (r.max.get().equals(x)) {
					r.max = r.min;
				}
			} else {
				if (r.min.get().equals(x)) {
					Optional<Integer> successor = successor(r, r.min.get());
					if (!successor.isPresent()) {
						r.min = Optional.empty();
						r.max = Optional.empty();
						return;
					} else {
						delete(r, successor.get());
						r.min = Optional.of(successor.get());
					}
				} else {
					delete(r.cluster[r.getCluster(x)], r.getOffset(x));
				}

				if (!r.cluster[r.getCluster(x)].min.isPresent()) {
					delete(r.summary, r.getCluster(x));
				}
				if (x == r.max.get()) {
					// adjust r.max
					Optional<Integer> maxCluster = maximum(r.summary);
					if (maxCluster.isPresent()) {
						r.max = Optional.of(r.getIndex(maxCluster.get(), maximum(r.cluster[maxCluster.get()]).get()));
					} else {
						r.max = r.min;
					}
				}
			}
		}

	}

}

/** min and max can only be both null or non-null */
class VanEmdeBoasTreeNode {
	String id;
	int size;
	Optional<Integer> min;
	Optional<Integer> max;
	VanEmdeBoasTreeNode summary;
	VanEmdeBoasTreeNode[] cluster;

	public VanEmdeBoasTreeNode(int size, String id) {
		this.size = size;
		this.id = id;
		min = Optional.empty();
		max = Optional.empty();
		if (size > 2) {
			cluster = new VanEmdeBoasTreeNode[Util.upperSquareRoot(size)];
			for (int i = 0; i < cluster.length; i++) {
				cluster[i] = new VanEmdeBoasTreeNode(Util.lowerSquareRoot(size), id + "-c" + i);
			}
			summary = new VanEmdeBoasTreeNode(Util.lowerSquareRoot(size), id + "-s");
		}
	}

	private boolean isValid(int x) {
		return x < size && x >= 0;
	}

	public int getCluster(int x) {
		if (isValid(x)) {
			return getCluster(size, x);
		} else {
			return -1;
		}
	}

	public int getOffset(int x) {
		if (isValid(x)) {
			return getOffset(size, x);
		} else {
			return -1;
		}
	}

	public int getIndex(int cluster, int offset) {
		return getIndex(size, cluster, offset);
	}

	private int getCluster(int size, int x) {
		return (int) Math.floor(x / Util.lowerSquareRoot(size));
	}

	private int getOffset(int size, int x) {
		return x % Util.lowerSquareRoot(size);
	}

	private int getIndex(int size, int cluster, int offset) {
		return cluster * Util.lowerSquareRoot(size) + offset;
	}

	@Override
	public String toString() {
		return "[" + id + ", " + size + ", " + min + ", " + max + "]";
	}
}