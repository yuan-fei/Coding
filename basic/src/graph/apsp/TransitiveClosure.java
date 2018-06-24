package graph.apsp;

import java.util.Arrays;

import graph.Graph;
import graph.GraphEdge;
import graph.GraphNode;

public class TransitiveClosure {

	public static void main(String[] args) {
		Graph<String> g = new Graph<String>();
		GraphNode<String> v1 = new GraphNode<String>("1");
		GraphNode<String> v2 = new GraphNode<String>("2");
		GraphNode<String> v3 = new GraphNode<String>("3");
		GraphNode<String> v4 = new GraphNode<String>("4");
		g.addVertex(v1).addVertex(v2).addVertex(v3).addVertex(v4);
		g.addEdge(new GraphEdge<String>(v2, v3)).addEdge(new GraphEdge<String>(v2, v4))
				.addEdge(new GraphEdge<String>(v4, v1)).addEdge(new GraphEdge<String>(v4, v3))
				.addEdge(new GraphEdge<String>(v3, v2));
		double[][] weights = g.getAdjacentMatrix();
		int n = weights.length;
		System.out.println(Arrays.deepToString(weights));
		System.out.println(FloydWarshall.getShortestPaths(n, weights).asTransitiveClosure());
	}

}
