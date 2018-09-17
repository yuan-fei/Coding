package tree;

import java.util.Optional;

public interface ISearchable<K extends Comparable<K>> {

	boolean isEmpty();

	boolean member(K k);

	void insert(K k);

	void delete(K k);

	Optional<K> minimum();

	Optional<K> maximum();

	Optional<K> predecessor(K k);

	Optional<K> successor(K k);

}