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
	* Bellman-Ford: `O(VE)`
		* support SSSP with negiative weights, can be used to check existance of a negative weight circle
* SSSP for DAG
	* Topological sort + relax edges in vertex order: O(V+E)
		* Diameter/Longest path/Critical path of DAG 
* SSSP for DG with non-negative weights
	* Dijkstra: `O((V+E)lgV)`
		* Can be used only for non-negative weight graph for the triangle inequity: D(u, v)+D(v, w) >= D(u, w)
* Application: 
	* System of difference constraint

## <a name='All_Pairs_Shortest_Paths'></a>All-Pairs Shortest Paths
* Adjacent Matrix based
	* Negative weights circle check: digonal elements (`d[i, i] < 0`).
	* Pseudo Matrix Mulplication: `O(V^3*lgV)`
		* 'Repeated square' optimization for speed up
	* Floyd-Warshall: `O(v^3)`
		* **Online/In-place update with O(V^2) space complexcity**
		* Dynamic Prigramming: 
			* State `d[i,j]` in the `kth` loop: path i to j only contains vertices wihch in the first kth vertices (v1~vk) as intermediate
			* Function `d[i,j]=min(d[i,j], d[i,k] + d[k,j])`: take k as a intermediate or not
		* Compared with Pseudo Matrix Mulplication, for each loop over k, FW doesn't recalc the `d[i, l] + d[l, j]` in which `l < k`, which saves time
* Adjacent List based
	* Johnson's algorithm on sparse graph:`O(V^2*lgV+VE)`
		* Combine both Bellman-ford and Dijkstra SSSP algorithm
			* Bellman-Ford: Negative weights circle check and re-weighting
				* Re-wighting: adjust the weights to non-negative values
					1. New super source s: `weight[s, x] = 0`
					2. Calc `d[s, i]` for each v with Bellman-Ford
					3. `new_weight[i, j] = weight[i, j] + d[s, i] - d[s, j]`

			* Dijkstra : SSSP for All vertices with adjusted non-negative weights
		* Time complexity:`O(V^2*lgV+VE)`
			* Bellman-Ford: `O(VE)`
			* Dijkstra: `O(V^2*lgV)`
				* For each v, run Dijikstra with `O((V+E)lgV)`, which is in total `O(V(V+E)lgV)`, for sparse graph: E << V, so `O(V^2*lgV)`
* Application
	* Transitive closure: all reachable pairs