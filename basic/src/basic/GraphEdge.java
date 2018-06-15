package basic;

import java.util.Comparator;

public class GraphEdge<T> {
	public GraphNode<T> source;
	public GraphNode<T> target;
	public double weight;

	public GraphEdge(GraphNode<T> source, GraphNode<T> target, double weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}

	public GraphEdge(GraphNode<T> source, GraphNode<T> target) {
		this(source, target, 0);
	}

	public GraphEdge<T> reverse() {
		return new GraphEdge<T>(target, source, weight);
	}

	public static class ComparatorByWeight<T> implements Comparator<GraphEdge<T>> {

		int ascending = 1;

		public ComparatorByWeight(boolean ascending) {
			this.ascending = ascending ? 1 : -1;
		}

		@Override
		public int compare(GraphEdge<T> arg0, GraphEdge<T> arg1) {
			// TODO Auto-generated method stub
			return (int) (arg0.weight - arg1.weight) * ascending;
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + source + "->" + target + ": " + weight + ")";
	}
}
