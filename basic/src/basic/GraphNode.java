package basic;

public class GraphNode<T> {
	public T val;

	public GraphNode(T v) {
		val = v;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return val.toString();
	}

}
