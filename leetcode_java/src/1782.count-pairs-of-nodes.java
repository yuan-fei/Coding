/*
 * @lc app=leetcode id=1782 lang=java
 *
 * [1782] Count Pairs Of Nodes
 *
 * https://leetcode.com/problems/count-pairs-of-nodes/description/
 *
 * algorithms
 * Hard (13.44%)
 * Likes:    24
 * Dislikes: 15
 * Total Accepted:    338
 * Total Submissions: 2.7K
 * Testcase Example:  '4\n[[1,2],[2,4],[1,3],[2,3],[2,1]]\n[2,3]'
 *
 * You are given an undirected graph represented by an integer n, which is the
 * number of nodes, and edges, where edges[i] = [ui, vi] which indicates that
 * there is an undirected edge between ui and vi. You are also given an integer
 * array queries.
 * 
 * The answer to the j^th query is the number of pairs of nodes (a, b) that
 * satisfy the following conditions:
 * 
 * 
 * a < b
 * cnt is strictly greater than queries[j], where cnt is the number of edges
 * incident to a or b.
 * 
 * 
 * Return an array answers such that answers.length == queries.length and
 * answers[j] is the answer of the j^th query.
 * 
 * Note that there can be repeated edges.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 4, edges = [[1,2],[2,4],[1,3],[2,3],[2,1]], queries = [2,3]
 * Output: [6,5]
 * Explanation: The number of edges incident to at least one of each pair is
 * shown above.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 5, edges = [[1,5],[1,5],[3,4],[2,5],[1,3],[5,1],[2,3],[2,5]],
 * queries = [1,2,3,4,5]
 * Output: [10,10,9,8,6]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 2 * 10^4
 * 1 <= edges.length <= 10^5
 * 1 <= ui, vi <= n
 * ui != vi
 * 1 <= queries.length <= 20
 * 0 <= queries[j] < edges.length
 * 
 * 
 */

// @lc code=start
class Solution {
	int MAX;
    public int[] countPairs(int n, int[][] edges, int[] queries) {
    	MAX = edges.length;
        int[] nodeIncidents = getIncidents(n, edges);
        int[] prefIncidentCnt = getIncidentCnt(n, nodeIncidents);
        HashMap<Integer, Integer>[] m = getSharedCnt(n, edges);
        int[] prefAdjustment = getPrefAdjustment(n, m, nodeIncidents);

        // System.out.println(Arrays.toString(nodeIncidents));
        // System.out.println(Arrays.toString(prefIncidentCnt));
        // System.out.println(Arrays.toString(prefAdjustment));
        
        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
        	int x = queries[i];
        	for(int j = 0; j <= MAX; j++){
        		int lb = Math.max(x - j, j - 1);
        		int incidentCntj = prefIncidentCnt[j + 1] - prefIncidentCnt[j];
        		int incidentCntAboveLB = prefIncidentCnt[prefIncidentCnt.length - 1] - prefIncidentCnt[lb + 1];
        		ans[i] += incidentCntj * incidentCntAboveLB;
        		if(lb < j){
        			ans[i] -= incidentCntj * (incidentCntj + 1) / 2;
        		}
        		// System.out.println(Arrays.asList(i, x, j, incidentCntj, lb + 1, incidentCntAboveLB, ans[i]));
        	}
        	ans[i] += prefAdjustment[prefAdjustment.length - 1] - prefAdjustment[x + 1];
        }
        return ans;
    }

    int[] getIncidents(int n, int[][] edges){
    	int[] incidents = new int[n + 1];
    	for (int[] e : edges) {
    		incidents[e[0]]++;
    		incidents[e[1]]++;
    	}
    	return incidents;
    }

    int[] getIncidentCnt(int n, int[] nodeIncidents){
    	int[] prefCnt = new int[MAX + 2];
    	for (int i = 1; i < nodeIncidents.length; i++) {
    		prefCnt[nodeIncidents[i] + 1]++;
    	}
    	for(int i = 1; i < prefCnt.length; i++){
    		prefCnt[i] += prefCnt[i - 1];
    	}
    	return prefCnt;
    }

    HashMap<Integer, Integer>[] getSharedCnt(int n, int[][] edges){
    	HashMap<Integer, Integer>[] shared = new HashMap[n + 1];
    	for (int[] e : edges) {
    		int n1 = Math.min(e[0], e[1]), n2 = Math.max(e[0], e[1]);
	        shared[n1] = shared[n1] == null ? new HashMap() : shared[n1];
	        shared[n1].put(n2, shared[n1].getOrDefault(n2, 0) + 1);
    	}
    	return shared;
    }

    int[] getPrefAdjustment(int n, HashMap<Integer, Integer>[]  m, int[] nodeIncidents){
    	int[] prefAdjustment = new int[2 * MAX + 2];
    	for (int u = 1; u < m.length; u++) {
    		if(m[u] != null){
	    		for(int v : m[u].keySet()){
		    		int total = nodeIncidents[u] + nodeIncidents[v];
		    		prefAdjustment[total + 1]--;
		    		prefAdjustment[total + 1 - m[u].get(v)]++;	
	    		}	
    		}
    	}
    	for(int i = 1; i < prefAdjustment.length; i++){
    		prefAdjustment[i] += prefAdjustment[i - 1];
    	}
    	return prefAdjustment;
    }
}
// @lc code=end
