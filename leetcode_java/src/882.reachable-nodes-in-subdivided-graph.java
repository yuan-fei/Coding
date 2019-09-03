/*
 * @lc app=leetcode id=882 lang=java
 *
 * [882] Reachable Nodes In Subdivided Graph
 *
 * https://leetcode.com/problems/reachable-nodes-in-subdivided-graph/description/
 *
 * algorithms
 * Hard (38.54%)
 * Total Accepted:    3.8K
 * Total Submissions: 9.7K
 * Testcase Example:  '[[0,1,10],[0,2,1],[1,2,2]]\n6\n3'
 *
 * Starting with an undirected graph (the "original graph") with nodes from 0
 * to N-1, subdivisions are made to some of the edges.
 * 
 * The graph is given as follows: edges[k] is a list of integer pairs (i, j, n)
 * such that (i, j) is an edge of the original graph,
 * 
 * and n is the total number of new nodes on that edge. 
 * 
 * Then, the edge (i, j) is deleted from the original graph, n new nodes (x_1,
 * x_2, ..., x_n) are added to the original graph,
 * 
 * and n+1 new edges (i, x_1), (x_1, x_2), (x_2, x_3), ..., (x_{n-1}, x_n),
 * (x_n, j) are added to the original graph.
 * 
 * Now, you start at node 0 from the original graph, and in each move, you
 * travel along one edge. 
 * 
 * Return how many nodes you can reach in at most M moves.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: edges = [[0,1,10],[0,2,1],[1,2,2]], M = 6, N = 3
 * Output: 13
 * Explanation: 
 * The nodes that are reachable in the final graph after M = 6 moves are
 * indicated below.
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], M = 10, N = 4
 * Output: 23
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 0 <= edges.length <= 10000
 * 0 <= edges[i][0] < edges[i][1] < N
 * There does not exist any i != j for which edges[i][0] == edges[j][0] and
 * edges[i][1] == edges[j][1].
 * The original graph has no parallel edges.
 * 0 <= edges[i][2] <= 10000
 * 0 <= M <= 10^9
 * 1 <= N <= 3000
 * A reachable node is a node that can be travelled to using at most M moves
 * starting from node 0.
 * 
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    public int reachableNodes(int[][] edges, int M, int N) {
    	int[] minD = dijkstra(edges, M, N);
    	int cnt = 0;
    	for (int d: minD) {
    		if(d!=-1){
    			cnt++;
    		}
    	}
    	for(int[] edge: edges){
    		int left = 0;
    		if(minD[edge[0]]!=-1){
    			left = Math.min(M-minD[edge[0]], edge[2]);
    		}
    		int right= 0;
    		if(minD[edge[1]]!=-1){
    			right = Math.min(M-minD[edge[1]], edge[2]);
    		}
    		cnt+=Math.min(edge[2], left+right);
    	}
        return cnt;
    }

    int[] dijkstra(int[][] edges, int M, int N){
		List<int[]>[] neighbor = getNeighborTable(edges, N);
		int[] minD = new int[N];
		int[] d = new int[N];
		Arrays.fill(minD, -1);
		Arrays.fill(d, M+1);
		TreeMap<Integer, Set<Integer>> mSet= new TreeMap<>();
		d[0] = 0;
		for (int i = 0; i<N; i++) {
			Set<Integer> s = mSet.getOrDefault(d[i], new HashSet<Integer>());
			mSet.put(d[i], s);
			s.add(i);
		}
		while(!mSet.isEmpty()){
			// System.out.println(mSet);
			Map.Entry<Integer, Set<Integer>> e = mSet.firstEntry();
			int from = e.getValue().iterator().next();
			int dd = e.getKey();
			if(dd>M){
				break;
			}
			
			minD[from] = dd;
			e.getValue().remove(from);
			if(e.getValue().isEmpty()){
				mSet.remove(dd);
			}
			if(neighbor[from]!=null){
				for(int[] to: neighbor[from])	{
					if(dd+to[1]<d[to[0]]){
						Set<Integer> s = mSet.get(d[to[0]]);
						s.remove(to[0]);
						if(s.isEmpty()){
							mSet.remove(d[to[0]]);
						}
						d[to[0]] = dd+to[1];
						s = mSet.getOrDefault(d[to[0]], new HashSet<Integer>());
						mSet.put(d[to[0]], s);
						s.add(to[0]);
					}
				}
			}
		}
		return minD;
    }

    List<int[]>[] getNeighborTable(int[][] edges, int N){
    	List<int[]>[] neighbor = new List[N];
        for(int[] edge: edges){
        	if(neighbor[edge[0]] == null){
        		neighbor[edge[0]] = new ArrayList<int[]>();
        	}
        	neighbor[edge[0]].add(new int[]{edge[1], edge[2]+1});
        	if(neighbor[edge[1]] == null){
        		neighbor[edge[1]] = new ArrayList<int[]>();
        	}
        	neighbor[edge[1]].add(new int[]{edge[0], edge[2]+1});
        }
        return neighbor;
    }
}
