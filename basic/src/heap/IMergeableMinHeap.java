package heap;

public interface IMergeableMinHeap<N, K extends Comparable<K>> {

	void insert(N x);

	N minimum();

	N extractMin();

	void unionWith(IMergeableMinHeap<N, K> h);

	void decreaseKey(N x, K k);

	void delete(N x);

}
