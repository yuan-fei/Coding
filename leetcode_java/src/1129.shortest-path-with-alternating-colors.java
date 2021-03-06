/*
 * @lc app=leetcode id=1129 lang=java
 *
 * [1129] Shortest Path with Alternating Colors
 *
 * https://leetcode.com/problems/shortest-path-with-alternating-colors/description/
 *
 * algorithms
 * Medium (35.94%)
 * Total Accepted:    3.8K
 * Total Submissions: 10.6K
 * Testcase Example:  '3\n[[0,1],[1,2]]\n[]'
 *
 * Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this
 * graph, each edge is either red or blue, and there could be self-edges or
 * parallel edges.
 * 
 * Each [i, j] in red_edges denotes a red directed edge from node i to node j.
 * Similarly, each [i, j] in blue_edges denotes a blue directed edge from node
 * i to node j.
 * 
 * Return an array answer of length n, where each answer[X] is the length of
 * the shortest path from node 0 to node X such that the edge colors alternate
 * along the path (or -1 if such a path doesn't exist).
 * 
 * 
 * Example 1:
 * Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
 * Output: [0,1,-1]
 * Example 2:
 * Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
 * Output: [0,1,-1]
 * Example 3:
 * Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
 * Output: [0,-1,-1]
 * Example 4:
 * Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
 * Output: [0,1,2]
 * Example 5:
 * Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
 * Output: [0,1,1]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 100
 * red_edges.length <= 400
 * blue_edges.length <= 400
 * red_edges[i].length == blue_edges[i].length == 2
 * 0 <= red_edges[i][j], blue_edges[i][j] < n
 * 
 */
class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        Map<Integer, List<Integer>> redEdgeList = getAdjList(red_edges);
        Map<Integer, List<Integer>> blueEdgeList = getAdjList(blue_edges);
        Queue<int[]> q = new LinkedList<>();
        int[] ansRed = new int[n];
        int[] ansBlue = new int[n];
        Arrays.fill(ansRed, -1);
        Arrays.fill(ansBlue, -1);
        int len = 0;
        // System.out.println(redEdgeList);
        // for(int to: redEdgeList.getOrDefault(0, new ArrayList<>())){
        //     q.offer(new int[]{to, 0});
        //     ansRed[to] = len;
        // }
        // System.out.println(Arrays.toString(ansRed));
        // for(int to: blueEdgeList.getOrDefault(0, new ArrayList<>())){
        //     q.offer(new int[]{to, 1});
        //     ansBlue[to] = len;
        // }
        q.offer(new int[]{0,0});
        q.offer(new int[]{0,1});
        ansRed[0] = 0;
        ansBlue[0] = 0;
        while(!q.isEmpty()){
            len++;
            int s = q.size();
            for(int i = 0; i < s; i++){
                int[] from = q.poll();
                // System.out.println(Arrays.toString(from));
                if(from[1]==1){
                    for(int to: redEdgeList.getOrDefault(from[0], new ArrayList<>())){
                        if(ansRed[to]==-1){
                            q.offer(new int[]{to, 0});
                            ansRed[to] = len;    
                        }
                    }    
                }
                else{
                    for(int to: blueEdgeList.getOrDefault(from[0], new ArrayList<>())){
                        if(ansBlue[to]==-1){
                            q.offer(new int[]{to, 1});
                            ansBlue[to] = len;    
                        }
                    }        
                }
            }
        }
        // System.out.println(Arrays.toString(ansRed));
        // System.out.println(Arrays.toString(ansBlue));
        int[] ans = new int[n];
        for(int i = 0; i<n; i++){
            ans[i] = Math.min(ansRed[i], ansBlue[i]);
            if(ans[i]==-1){
                ans[i] = Math.max(ansRed[i], ansBlue[i]);
            }
        }
        return ans;
    }
    
    Map<Integer, List<Integer>> getAdjList(int[][] edges){
        Map<Integer, List<Integer>> m = new HashMap<>();
        for(int[] edge: edges){
                List<Integer> l= m.getOrDefault(edge[0], new ArrayList<>());
                l.add(edge[1]);
                m.put(edge[0],l);    
        }
        return m;
    }
}
