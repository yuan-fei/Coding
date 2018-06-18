package basic;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/** Sort Map by value: first O(logN), put O(logN) */
public class SortedMapByValue<K, V> implements Map<K, V> {

	public static void main(String[] args) {
		SortedMapByValue<String, Integer> map = new SortedMapByValue<String, Integer>();
		map.put("3", 3);
		map.put("4", 4);
		map.put("2", 2);
		map.put("5", 5);
		System.out.println(map.keySet());
		System.out.println(map.values());
		while (!map.isEmpty()) {
			System.out.println(map.removeFirst());
		}
	}

	Map<K, Entry<K, V>> map;
	PriorityQueue<Entry<K, V>> queue;

	public SortedMapByValue(Comparator<V> valueComparator) {
		map = new HashMap<K, Entry<K, V>>();
		queue = new PriorityQueue<Entry<K, V>>(new ValueComparatorWrapper<K, V>(valueComparator));
	}

	public SortedMapByValue() {
		this(null);
	}

	public Entry<K, V> firstEntry() {
		return queue.peek();
	}

	public Entry<K, V> removeFirst() {
		Entry<K, V> first = queue.poll();
		map.remove(first.getKey());
		return first;
	}

	private static class ValueComparatorWrapper<K, V> implements Comparator<Entry<K, V>> {

		private Comparator<V> comparator;

		public ValueComparatorWrapper(Comparator<V> comparator) {
			this.comparator = comparator;
		}

		@SuppressWarnings("unchecked")
		@Override
		public int compare(Entry<K, V> a, Entry<K, V> b) {
			if (comparator != null) {
				return comparator.compare(a.getValue(), b.getValue());
			} else {
				Comparable<? super V> key = (Comparable<? super V>) (a.getValue());
				return key.compareTo(b.getValue());
			}
		}

	}

	@Override
	public void clear() {
		map.clear();
		queue.clear();
	}

	@Override
	public boolean containsKey(Object arg0) {
		return map.containsKey(arg0);
	}

	@Override
	public boolean containsValue(Object arg0) {
		for (Entry<K, V> entry : queue) {
			if (entry.getValue().equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> set = new TreeSet<Entry<K, V>>(queue.comparator());
		set.addAll(queue);
		return set;
	}

	@Override
	public V get(Object arg0) {
		Entry<K, V> v = map.get(arg0);
		return v == null ? null : v.getValue();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		return queue.stream().map(e -> e.getKey()).collect(Collectors.toSet());
	}

	@Override
	public V put(K arg0, V arg1) {
		V ret = remove(arg0);
		map.put(arg0, new SimpleEntry<K, V>(arg0, arg1));
		queue.offer(map.get(arg0));
		return ret;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> arg0) {
		for (Entry<? extends K, ? extends V> entry : arg0.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public V remove(Object arg0) {
		Entry<K, V> v = map.get(arg0);
		if (v != null) {
			queue.remove(v);
		}
		return v == null ? null : v.getValue();
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Collection<V> values() {
		return queue.stream().sorted(queue.comparator()).map(e -> e.getValue()).collect(Collectors.toList());
	}
}
