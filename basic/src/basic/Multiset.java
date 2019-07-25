package basic;

import java.util.Comparator;
import java.util.TreeMap;

public class Multiset<K> {

	public static void main(String[] args) {
		Multiset<Integer> bst = new Multiset<>();
		bst.add(1);
		bst.add(1);
		bst.add(2);
		bst.add(3);
		System.out.println(bst.first());
		bst.remove(1);
		System.out.println(bst.first());

	}

	TreeMap<K, Integer> bst;
	private int size;

	public Multiset(Comparator<? super K> comparator) {
		bst = new TreeMap<>(comparator);
	}

	public Multiset() {
		this(null);
	}

	public void add(K v) {
		if (bst.containsKey(v)) {
			bst.put(v, bst.get(v) + 1);
		} else {
			bst.put(v, 1);
		}
		size++;
	}

	public void remove(K v) {
		if (bst.get(v) == 1) {
			bst.remove(v);
		} else {
			bst.put(v, bst.get(v) - 1);
		}
		size--;
	}

	public int size() {
		return size;
	}

	public K first() {
		return bst.firstKey();
	}

	public K last() {
		return bst.lastKey();
	}
}
