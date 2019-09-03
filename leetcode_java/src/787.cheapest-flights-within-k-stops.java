/*
 * @lc app=leetcode id=787 lang=java
 *
 * [787] Cheapest Flights Within K Stops
 *
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
 *
 * algorithms
 * Medium (35.85%)
 * Total Accepted:    49.4K
 * Total Submissions: 137.6K
 * Testcase Example:  '3\n[[0,1,100],[1,2,100],[0,2,500]]\n0\n2\n1'
 *
 * There are n cities connected by m flights. Each fight starts from city u and
 * arrives at v with a price w.
 * 
 * Now given all the cities and flights, together with starting city src and
 * the destination dst, your task is to find the cheapest price from src to dst
 * with up to k stops. If there is no such route, output -1.
 * 
 * 
 * Example 1:
 * Input: 
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation: 
 * The graph looks like this:
 * 
 * 
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as
 * marked red in the picture.
 * 
 * 
 * Example 2:
 * Input: 
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation: 
 * The graph looks like this:
 * 
 * 
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as
 * marked blue in the picture.
 * 
 * Note:
 * 
 * 
 * The number of nodes n will be in range [1, 100], with nodes labeled from 0
 * to n - 1.
 * The size of flights will be in range [0, n * (n - 1) / 2].
 * The format of each flight will be (src, dst, price).
 * The price of each flight will be in the range [1, 10000].
 * k is in the range of [0, n - 1].
 * There will not be any duplicated flights or self cycles.
 * 
 * 
 */
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] flight : flights) {
        	List<int[]> edges = adj.getOrDefault(flight[0], new ArrayList<>());
        	adj.put(flight[0], edges);
        	edges.add(new int[]{flight[1], flight[2]});
        }

        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->Integer.compare(a[1], b[1]));
        q.offer(new int[]{src, 0, K+1});
        while(!q.isEmpty()){
        	int[] cur = q.poll();
        	// System.out.println(Arrays.toString(cur));
        	if(cur[0] == dst){
        		return cur[1];
        	}
        	if(cur[2]-1>=0){
	        	for(int[] e: adj.getOrDefault(cur[0], new ArrayList<>())){
	        		q.offer(new int[]{e[0], cur[1]+e[1], cur[2]-1});
	        	}	
        	}
        }
        return -1;
    }
}
