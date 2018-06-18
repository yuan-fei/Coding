# Notes

## <a name='Graph'></a>Graph Search
* DFS and BFS
	* Node state:
		* white = unvisited
		* gray = being queued in BFS, or being visited in DFS
		* black = visited
	* Graph search generates a spanning tree

## <a name='Topological_Sort'></a>Topological Sort
* DFS: O(V+E)
	* List vertices by finish time desc
* BFS: O(V+E)
	* Constantly remove vertices whose indegree 0 and their out edges

## <a name='Strongly_Connected_Components'></a>Strongly Connected Components
* Directed Graph: 
	* 2-pass DFS: O(V+E)
		* 1st pass find all parent -> child
		* 2nd pass find all child -> parent
			* transposed graph: find reverse connectivity
			* finish time descending order: find parent->child 
* Undirected graph
	* Union Find Set: O(VlgV+E), O(V+E)
	* DFS, BFS: O(V+E)

## <a name='Minimum_Spanning_Tree'></a>Minimum Spanning Tree
* Kruskal: O((V+E)lgV)
	* merge disjoint trees with min edge
* Prim:O((V+E)lgV)
	* grow the only tree with min relaxed edges

## <a name='Single_Source_Shortest_Paths'></a>Single-Source Shortest Paths
* Negative weights circle: a circle whose sum of weights is negative
	* A graph with negative weights circle has no shortest paths
	* A graph with both negative weights and circle, but not negative weight circle can have shortest paths
* SSSP for general directed graph
	* Bellman-Ford: O(VE)
		* support SSSP with negiative weights, can be used to check existance of a negative weight circle
* SSSP for DAG
	* Topological sort + relax edges in vertex order: O(V+E)
		* Diameter/Longest path/Critical path of DAG 
* SSSP for DG with non-negative weights
	* Dijkstra: O((V+E)lgV)
		* Can be used only for non-negative weight graph for the triangle inequity: D(u, v)+D(v, w) >= D(u, w)

## <a name='All_Pairs_Shortest_Paths'></a>All-Pairs Shortest Paths