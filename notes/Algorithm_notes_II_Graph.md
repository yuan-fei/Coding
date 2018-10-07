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

## <a name='Maximum_Flow'></a>Maximum Flow
* Flow network: DAG with capacity on edge >= 0
	* flow conservation: total flow-in of a vertex = total flow-out of a vertex
	* maximum flow = minimum cut
* Ford-Fulkerson method: `O(E * max-flow)`
	* Find augment path in a residual network, consume it
* Edmonds-Karp algorithm: `O(VE^2)`
	* Ford-Fulkerson method implementation with BFS for augment path searching
* Push-Relabel algorithm: `O(EV^2)`
	* Only flow from 'high' to 'low'
		* preflow(v): flow-in of v
		* push: flow from one overflow (preflow(v)>0) vertex to another vertex
		* relabel: adjust height of overflow vertex in order to flow out
* Relabel to front algorithm:`O(V^3)`
	* Admissible network: `h(u) = h(v) + 1`
	* Keep topological order
* Bipartite max matching
	* Max-size matching
		* Max flow: `O(VE)`
			* Convert to a flow network
				* Add a super source s connect to every vertice in part X each with unit capacity.
				* Add a super target t connect to every vertice in part Y each with unit capacity.
				* Add all edges between X and Y with unit capacity
			* With Ford-Fulkerson method the max-flow of converted flow network is at most V/2, thus O(V * E)
		* Hopcroft-Karp: `O(EV^0.5)` (CLRS 26.6)
			* Alternative/Augmenting path: start with unmatched vertex in U, end with unmatched vertex in V, and the edges between belongs alternatively to Matched (M) and Unmatched (E-M) groups.
			* Intuitives: Find vertex disjoint shortest alternative path, augment with them (XOR) until no such path exists.
			* Implementation: BFS + DFS
				* BFS: mark all possible shortest alternative paths
				* DFS: check and update matching with the alternative paths
	* Max-weight matching (assignment problem)
		* Kuhn-Munkres (Hungarian algorithm): `O(V^3)` ([http://www.cse.ust.hk/%7Egolin/COMP572/Notes/Matching.pdf](http://www.cse.ust.hk/%7Egolin/COMP572/Notes/Matching.pdf))
			* Assume there is a perfect matching. This can be done by adding zero-weight edges to all missing pair.
			* Intuitive: find perfect matching in equality graph
				* Labeling vertices: `L(u) + L(v) >= Weight(u,v)`
				* Equality Graph: verteices and edges with  `L(u) + L(v) = Weight(u,v)`
				* Kuhn-Munkres Theorem: A perfect matching in equality graph is a max weight matching
				* Extend equality graph with relabeling, always pick a new vertex with minimum slack into euqality graph which will bring the total labeling of euqaity graph least decrease

## Reference
* [CLRS solutions I](https://walkccc.github.io/CLRS)
* [CLRS solutions II](http://sites.math.rutgers.edu/~ajl213/CLRS)