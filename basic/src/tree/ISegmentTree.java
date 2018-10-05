package tree;

/**
 * Tree used for range query. Update and range query can be done in O(logn)
 */
public interface ISegmentTree<R> {

	public void build(int[] nums);

	public R query(int start, int end);

	public void update(int index, int value);

}