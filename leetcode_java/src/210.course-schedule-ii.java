/*
 * @lc app=leetcode id=210 lang=java
 *
 * [210] Course Schedule II
 *
 * https://leetcode.com/problems/course-schedule-ii/description/
 *
 * algorithms
 * Medium (37.40%)
 * Likes:    1427
 * Dislikes: 98
 * Total Accepted:    197.3K
 * Total Submissions: 527.3K
 * Testcase Example:  '2\n[[1,0]]'
 *
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have
 * to first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, return
 * the ordering of courses you should take to finish all courses.
 * 
 * There may be multiple correct orders, you just need to return one of them.
 * If it is impossible to finish all courses, return an empty array.
 * 
 * Example 1:
 * 
 * 
 * Input: 2, [[1,0]] 
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you
 * should have finished   
 * course 0. So the correct course order is [0,1] .
 * 
 * Example 2:
 * 
 * 
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you
 * should have finished both     
 * ⁠            courses 1 and 2. Both courses 1 and 2 should be taken after you
 * finished course 0. 
 * So one correct course order is [0,1,2,3]. Another correct ordering is
 * [0,2,1,3] .
 * 
 * Note:
 * 
 * 
 * The input prerequisites is a graph represented by a list of edges, not
 * adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input
 * prerequisites.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
    	Map<Integer, List<Integer>> adj = new HashMap<>();
    	for(int[] p: prerequisites){
    		if(!adj.containsKey(p[1])){
    			adj.put(p[1], new ArrayList<>());
    		}
    		adj.get(p[1]).add(p[0]);
    	}
    	int[] visited = new int[numCourses];
    	List<Integer> ans = new ArrayList<>();
    	for(int i = 0; i < numCourses; i++){
    		if(visited[i] == 0 && !dfs(adj, i, visited, ans)){
    			return new int[0];
    		}
    	}
    	// System.out.println(ans);
    	int n = ans.size();
    	int[] arr = new int[n];
    	for(int i = 0; i < n; i++){
    		arr[n - i - 1] = ans.get(i);
    	}
    	return arr;
    }

    boolean dfs(Map<Integer, List<Integer>> adj, int u, int[] visited, List<Integer> orders){
    	visited[u] = 1;
    	for(int v: adj.getOrDefault(u, new ArrayList<>())){
    		if(visited[v] == 1){
    			return false;
    		}
    		else{
    			if(visited[v] == 0 && !dfs(adj, v, visited, orders)){
    				return false;
    			}
    		}
    	}
    	orders.add(u);
    	visited[u] = 2;
    	return true;
    }
}
// @lc code=end
