/*
 * @lc app=leetcode id=1494 lang=java
 *
 * [1494] Parallel Courses II
 *
 * https://leetcode.com/problems/parallel-courses-ii/description/
 *
 * algorithms
 * Hard (27.97%)
 * Likes:    95
 * Dislikes: 8
 * Total Accepted:    2K
 * Total Submissions: 7.1K
 * Testcase Example:  '4\n[[2,1],[3,1],[1,4]]\n2'
 *
 * Given the integer n representing the number of courses at some university
 * labeled from 1 to n, and the array dependencies where dependencies[i] = [xi,
 * yi]  represents a prerequisite relationship, that is, the course xi must be
 * taken before the course yi.  Also, you are given the integer k.
 * 
 * In one semester you can take at most k courses as long as you have taken all
 * the prerequisites for the courses you are taking.
 * 
 * Return the minimum number of semesters to take all courses. It is guaranteed
 * that you can take all courses in some way.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: n = 4, dependencies = [[2,1],[3,1],[1,4]], k = 2
 * Output: 3 
 * Explanation: The figure above represents the given graph. In this case we
 * can take courses 2 and 3 in the first semester, then take course 1 in the
 * second semester and finally take course 4 in the third semester.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: n = 5, dependencies = [[2,1],[3,1],[4,1],[1,5]], k = 2
 * Output: 4 
 * Explanation: The figure above represents the given graph. In this case one
 * optimal way to take all courses is: take courses 2 and 3 in the first
 * semester and take course 4 in the second semester, then take course 1 in the
 * third semester and finally take course 5 in the fourth semester.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 11, dependencies = [], k = 2
 * Output: 6
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 15
 * 1 <= k <= n
 * 0 <= dependencies.length <= n * (n-1) / 2
 * dependencies[i].length == 2
 * 1 <= xi, yi <= n
 * xi != yi
 * All prerequisite relationships are distinct, that is, dependencies[i] !=
 * dependencies[j].
 * The given graph is a directed acyclic graph.
 * 
 */

// @lc code=start
class Solution {
    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        int[] dep = new int[n];
        for(int[] d : dependencies){
        	dep[d[1] - 1] |= (1 << (d[0] - 1));
        }
        // candidates for next semester
        int[] bitmap = new int[1 << n];
        for(int i = 0; i < (1 << n); i++){
        	for (int j = 0; j < n; j++) {
        		if((i & dep[j]) == dep[j] && ((i >> j) & 1) == 0){
        			bitmap[i] |= 1 << j;
        		}
        	}
        }
		// System.out.println(Arrays.toString(bitmap));        
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean [1 << n];
        q.offer(0);
        int cnt = 0;
        while(!q.isEmpty()){
        	// System.out.println(q);
        	for(int l = q.size(); l > 0; l--){
        		int cur = q.poll();
        		if(cur + 1 == (1 << n)){
        			return cnt;
        		}
        		if(Integer.bitCount(bitmap[cur]) <= k){
        			if(!visited[cur | bitmap[cur]]){
						q.offer(cur | bitmap[cur]);
						visited[cur | bitmap[cur]] = true;
					}
        		}
        		else{
        			int delta = bitmap[cur];
        			while(delta != 0){
        				delta = bitmap[cur] & (delta - 1);
        				if(Integer.bitCount(delta) == k){
        					if(!visited[cur | delta]){
								q.offer(cur | delta);
								visited[cur | delta] = true;
							}
        				}
        			}
        		}
        	}
        	cnt++;
        }

        return cnt;
    }
}
// @lc code=end
