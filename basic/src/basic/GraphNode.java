package basic;

import java.util.ArrayList;
import java.util.List;

public class GraphNode<T> {
	public T val;
	public List<GraphNode<T>> children;

	public GraphNode(T v) {
		val = v;
		children = new ArrayList<GraphNode<T>>();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return val.toString();
	}
}
