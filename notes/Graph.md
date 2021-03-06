# Notes

## <a name='Graph'></a>Graph
* construction

	~~~
	List<Integer>[] adj = new List[nV];
	int[] from = new int[nE];
	int[] to = new int[nE];
	int[] weight = new int[nE];
	for(int i = 0; i < nE; i++){
		int u, v, w;	//read fro input
		adj[u].add(i);
		// undirected
		// adj[v].add(i);
		from[i] = u;
		to[i] = v;
		weight[i] = w;
	}
	~~~
* iterate adjacent vertices

	~~~
	//given vertex u, how to find all its adjacent vertices
	for(int e: adj[u]){
		int v = from[e] + to[e] - u;
		//or int v = from[e] ^ to[e] ^ u;
	}
	~~~	
## <a name='Graph_Search'></a>Graph Search
* DFS and BFS
	* Node state:
		* white = unvisited
		* gray = being queued in BFS, or being visited in DFS
		* black = visited
	* Graph search generates a spanning tree
* Multiple Source BFS
	* find the shortest distance for a target from any sources
	* Related Problems: [as-far-from-land-as-possible](https://leetcode.com/contest/weekly-contest-150/problems/as-far-from-land-as-possible/)

## <a name='Topological_Sort'></a>Topological Sort
* DFS: O(V+E)
	* List vertices by finish time desc
* BFS: O(V+E)
	* Constantly remove vertices whose indegree 0 and their out edges

## <a name='Strongly_Connected_Components'></a>Strongly Connected Components
* Directed Graph: 
	* 2-pass DFS: O(V+E)
		* DFS1: obtain topological order
		* DFS2: find each component in topological order on reverse graph
	* Application:
		* 2-SAT: Find boolean assignment for each variable to make 2-CNF true
			* i.e. (a,b,c) =(false,false,fasle) for (a∨¬b)∧(¬a∨b)∧(¬a∨¬b)∧(a∨¬c)
			* equavalence between implication and disjunction:
				* a∨b <=> (¬b=>a)∧(¬a=>b)
			* implication graph: directed
				* vertices: for each a add a,¬a
				* edges: for each a∨b add (¬b, a), (¬a, b)
			* solution: O(v+e)
				1. transform a expression to implication graph, and obtain SCC
				2. each component id is assigned in topological order 
				4. for each vertices a,¬a, 
					* if a,¬a in the same scc, then there is no solution (¬a=>a)∧(¬a=>a)
					* else, assign the variable which has a bigger component id to true
			* Reference:
				* 《挑战程序设计竞赛》 p324
				* [cp-algorithms](https://cp-algorithms.com/graph/2SAT.html)
* Undirected graph
	* Union Find Set: O(VlgV+E), O(V+E)
	* DFS, BFS: O(V+E)

## <a name='Bridge'></a>Bridge and Articulation Point
* Bridge: An edge in an undirected connected graph is a bridge iff removing it disconnects the graph
	* O(V+E) with dfs
		* maintain inTime[u]: dfs in time for vertex u; lowestInTimeReached[u]: lowest inTime that u or its subtree can reach
		* for edge u-v, if v can not reach a vertex with lower inTime than u: `lowestInTimeReached[v] > inTime[u]`, then, u-v is bridge

	~~~
	void dfs(int u, int p, List<int[]> ans) {
		lowestInTimeReached[u] = inTime[u] = counter++;
		for (int v : adj[u]) {
			if (v != p) {
				if (inTime[v] == -1) {
					dfs(v, u, ans);
					if (inTime[u] < lowestInTimeReached[v]) {
						ans.add(new int[] { u, v });
					}
				}
				lowestInTimeReached[u] = Math.min(lowestInTimeReached[u], lowestInTimeReached[v]);
			}
		}
	}
	~~~
* Articulation point: A vertex in an undirected connected graph is an articulation point (or cut vertex) iff removing it (and edges through it) disconnects the graph
	* O(V+E) with dfs
		* for each 'bridge' u-v:
			* u is non-root, then u is AP
			* u is root, then u is AP only when u has to visit more than 1 children directly in DFS
			
	~~~
	void dfs(int u, int p, List<Integer> ans) {
		inTime[u] = counter++;
		lowestInTimeReached[u] = inTime[u];
		int unVisitedChildren = 0;
		for (int v : adj[u]) {
			if (v != p) {
				if (inTime[v] == -1) {
					unVisitedChildren++;
					dfs(v, u, ans);
					// non-root case
					if (inTime[u] > 0 && inTime[u] < lowestInTimeReached[v]) {
						ans.add(u);
					}
				}
				lowestInTimeReached[u] = Math.min(lowestInTimeReached[u], lowestInTimeReached[v]);
			}
		}
		// root case
		if (inTime[u] == 0 && unVisitedChildren > 1) {
			ans.add(u);
		}
	}
	~~~
* Reference:
	* [geeksforgeeks: bridge-in-a-graph](https://www.geeksforgeeks.org/bridge-in-a-graph/)
	* [geeksforgeeks: articulation-points-or-cut-vertices-in-a-graph](https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/)

## <a name='Euler_Path'></a>Euler Path: A Path visit each edge once
* Hierholzer's algorithm: O(V+E)
	* This algorithm works for both directed graph and undirected graph
	* The graph will still be euler graph after removing a cycle in graph.
		* for a node with multiple out edges, each of its out edge belongs to a separate circle
		
		~~~
		dfs(u){
			for all edges of u->v
				dfs(v)	//add other circle first
				add u->v to result path	  //add current circle
		}
		~~~
	* [notebook](https://cs.stanford.edu/group/acm/SLPC/notebook.pdf)
	* [geeksforgeeks](https://www.geeksforgeeks.org/hierholzers-algorithm-directed-graph/)

## <a name='Hamiltonion_Path'></a>Hamiltonion Path: A Path visit each vertex once
* NP-complete, but solvable for small n with bitmask DP in O(n2^n)
* Sufficient condition for Hamiltonian cycle
	* Diac: deg(v) >= n/2 for each vertex in G, then G is guaranteed to have a H cycle
* Reference: 
	* [A little bit of classics: dynamic programming over subsets and paths in graphs](https://codeforces.com/blog/entry/337)
	* [Diac](https://en.wikipedia.org/wiki/Hamiltonian_path)

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
		* Implementation for sparse graph `O((E+V)logE)`: 
			* use priorityqueue and add distance array
			* offer item to pQueue without delete/update obsolte items
		* Implementation for dense graph `O(V^2)`: 
			* no priority queue
			* loop all verteices to find the min dist vertex `minV`
			* relax paths through `minV`
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
* Max flow - Min cut
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
	* Related Problems: 
		* poj-3281: Dining
		
* Min cost flow: given unit cost and max capacity of each edge, find the minimum total cost that a given flow can be consumed.
	* O（max-flow * V * E）: Ford-Fulkerson + augment path with Bellman-Ford (挑战程序设计竞赛)
	* Related Problems:
		* poj-2135: each edge has unit capacity, the total cost of a edge becomes the unit cost, min-cost flow can be used for solution
		* poj-3686
* Bipartite max matching
	* Max-size matching
		* Hungarian: `O(VE)`
			* DFS implemention: [Stanford ACM-ICPC: max bipartite matching](https://cs.stanford.edu/group/acm/SLPC/notebook.pdf)
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
			* DFS implementation 
				* [Good explanation, O(n^4)](http://www.cnblogs.com/wenruo/p/5264235.html)
				* [Cache slack, O(n^3)](https://www.tianmaying.com/snippet/1325)
	* <a name='Bipartite Min-vertex cover'></a>Bipartite Min-vertex cover problem
		* In **bipartite**, #(minimum vertex cover) = #(max bipartite matching): [Kőnig's theorem](https://en.wikipedia.org/wiki/Kőnig%27s_theorem_(graph_theory))
		* Implementation:
			* For bipartite [L, R], get matchings with max-size matching
			* For each unmatched vertex u in L, add the following vertices to set Z
				* u
				* all the vertices which can be reached by alternating paths starting with u
			* MCV = (L ∖ Z) ∪ (R ∩ Z).
* Problems can be converted to max-flow problems
	* Capacity on vertices: for each vertex A, add a new vertex A' and an edge between them, specify the max capacity of the edge is the A's vertex capacity.
	* Order matters: N tasks can be scheduled on M processors, for each processor it costs differently for different task scheduling order. The cost of a task differs among processors, but on the same processor, it increases when it  is scheduled later. (poj-3686)
		1. Model the flow graph as 
			* 1 source vertex S, 1 target vertex E
			* N task vertices: T<sub>i</sub> where i in [1 ~ N]
			* N * M processor vertices: P<sub>j, k</sub> where j in [1 ~ N], k in [1 ~ M] means the order j of processor k.
			* N edges from source to task vertices: e(S, T<sub>i</sub>, 1, 0) S to   T<sub>i</sub> with unit capacity and cost 0
			* N\*M Edges from processor vertices to target vertex: e(P<sub>j, k</sub>, E, 1, 0) S to   T<sub>i</sub> with unit capacity and cost 0
			* N\*N\*M Edges between N task vertices and N*M processor vertices each with capacity 1 and different costs: e(T<sub>i</sub>, P<sub>j, k</sub>, 1, c<sub>j</sub>) represents task i scheduled at order j of processor k with cost c<sub>j</sub>
		2. Solve the min-cost flow problem min-cost(S, E, N)
		
	* [Problems can be converted to flow](https://en.wikipedia.org/wiki/Maximum_flow_problem#Application)
		* <a name='DAG_MPC'></a>DAG minimum path cover: find the minimum number of vertex-disjoint paths to cover each vertex in DAG
			* Solution:
				1. Construct bipartite with vertices (V<sub>has-out-edge</sub>, V<sub>has-in-edge</sub>), and edges from DAG
				2. MPC of DAG = #(vertex in DAG) - #(max bipartite matching)
			* Reference
				* GCJ 2009 round2 C《挑战p272》
* Problems can be convereted to min-cut problem: 
	* Vertices must be splitted into 2 disjoint sets, and we only care about the total minimum capacity of edges between 2 sets 
		* Dual core CPU (poj-3469)
		* wifi tower (gcj 2009 final D)
	* Max weight closure of a directed graph (最大闭合权图): given weights on vertices, find a closure with max total weight
		* Assume we include all positive weight vertices in the closure first, and next, we want to **exclude as few positive weight vertices and include as few negative weight vertices as possible by the definition of closure**.
		* [《最小割模型在信息学竞赛中的应用》](https://files-cdn.cnblogs.com/files/gjc1124646822/《最小割模型在信息学竞赛中的应用》.pdf)
		* GCJ 2009 world final D wifi tower(挑战280)
	* Min weight vertex covering set in bipartite (MinWVCS)
		* solution
			1. add edges s->X and Y->t with vertex weight as edge capacity
			2. add edges X->Y with infinite edge capacity
			3. MinWVCS is the min cut on the graph
		* [《最小割模型在信息学竞赛中的应用》](https://files-cdn.cnblogs.com/files/gjc1124646822/《最小割模型在信息学竞赛中的应用》.pdf)
	* Max weight vertex independent set in bipartite (MaxWVIS)
		* MaxWVIS = The complement vertices of MinWVCS
* Problems can be converted to bipartite problems
	*  Find Minimum Clique Cover for DAG
		* MCC for general undirected graph is NP-hard
		* If vertex is ordered by some ordering scheme, then a clique can be reduced to a DAG
		* MCC can be solved by [DAG minimum path cover](#DAG_MPC)

## Graph Theory
* Minimum Vertex Cover (MVC) problem:
 	* The MVC problem in a general graph is NP hard, there is polynomial approximation algorithm which guarantees to be no more than twice the size of an optimal vertex cover (CLRS: Approximation algorithms)
	* In **bipartite**, #(minimum vertex cover) = #(max bipartite matching): [Kőnig's theorem](https://en.wikipedia.org/wiki/Kőnig%27s_theorem_(graph_theory)), see [Bipartite Min-vertex cover](#Bipartite Min-vertex cover)
* Maximum Independent Set
	* Independent set: a set of vertices in a graph, in which, no two of which are adjacent
	* MIS problem in general graph is NP-hard
		* \#(maximum independent set) + #(minimum vertex cover) = |V|
	* In **bipartite**, #(maximum independent set) + #(max bipartite matching) = |V|
* Minimum edge cover
	* In any graph with no isolate vertex, #(minimum edge cover) + #(max bipartite matching) = |V|
	* In **bipartite**, #(minimum edge cover) = #(maximum independent set)
* Minimum Clique Cover
	* A clique of G is a subset of vertices which make a complete subgraph
	* A clique cover is a vertex partition of graph by disjoint cliques
	* MCC problem in general graph is NP-hard

## Reference
* [CLRS solutions I](https://walkccc.github.io/CLRS)
* [CLRS solutions II](http://sites.math.rutgers.edu/~ajl213/CLRS)