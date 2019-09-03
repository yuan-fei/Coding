/*
 * @lc app=leetcode id=743 lang=java
 *
 * [743] Network Delay Time
 *
 * https://leetcode.com/problems/network-delay-time/description/
 *
 * algorithms
 * Medium (43.52%)
 * Total Accepted:    43.4K
 * Total Submissions: 99.6K
 * Testcase Example:  '[[2,1,1],[2,3,1],[3,4,1]]\n4\n2'
 *
 * There are N network nodes, labelled 1 to N.
 * 
 * Given times, a list of travel times as directed edges times[i] = (u, v, w),
 * where u is the source node, v is the target node, and w is the time it takes
 * for a signal to travel from source to target.
 * 
 * Now, we send a signal from a certain node K. How long will it take for all
 * nodes to receive the signal? If it is impossible, return -1.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * Output: 2
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * N will be in the range [1, 100].
 * K will be in the range [1, N].
 * The length of times will be in the range [1, 6000].
 * All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
 * 
 * 
 */
class Solution {
	
    public int networkDelayTime(int[][] times, int N, int K) {
    	int[] dist = new int[N+1];
    	Arrays.fill(dist, -1);
        int MAX = 6000*100+5;
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] time : times) {
        	List<int[]> edges = adj.getOrDefault(time[0], new ArrayList<>());
        	adj.put(time[0], edges);
        	edges.add(new int[]{time[1], time[2]});
        	dist[time[0]] = MAX;
        	dist[time[1]] = MAX;
        }


        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->Integer.compare(a[1], b[1]));
        q.offer(new int[]{K, 0});
        dist[K] = 0;

        while(!q.isEmpty()){
        	int[] cur = q.poll();
        	// System.out.println(adj.get(cur[0]).size());
        	for(int[] e: adj.getOrDefault(cur[0], new ArrayList<>())){
        		// System.out.println((e[1] + cur[1])+", "+dist[e[0]]);
        		if(e[1] + cur[1] < dist[e[0]]){
        			q.offer(new int[]{e[0], e[1] + cur[1] });	
        			// System.out.println((e[1] + cur[1])+", "+e[0]);
        			dist[e[0]] = e[1] + cur[1];
        		}
        	}
        }
        // System.out.println(Arrays.toString(dist));
        int maxMinD = 0;
        // don't know what's going on here
        int cnt = 0;
        for(int i = 1; i <= N; i++){
        	if(dist[i] >= MAX){
        		return -1;
        	}
        	maxMinD = Math.max(dist[i], maxMinD);
        	if(dist[i]>-1){
				cnt++;
        	}
        }

        return cnt==N?maxMinD:-1;
    }
}
