/*
 * [207] Course Schedule
 *
 * https://leetcode.com/problems/course-schedule/description/
 *
 * algorithms
 * Medium (33.39%)
 * Total Accepted:    113.4K
 * Total Submissions: 339.2K
 * Testcase Example:  '2\n[[1,0]]'
 *
 * 
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have
 * to first take course 1, which is expressed as a pair: [0,1]
 * 
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it
 * possible for you to finish all courses?
 * 
 * 
 * For example:
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have
 * finished course 0. So it is possible.
 * 
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have
 * finished course 0, and to take course 0 you should also have finished course
 * 1. So it is impossible.
 * 
 * Note:
 * 
 * The input prerequisites is a graph represented by a list of edges, not
 * adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input
 * prerequisites.
 * 
 * 
 * 
 * click to show more hints.
 * 
 * Hints:
 * 
 * This problem is equivalent to finding if a cycle exists in a directed graph.
 * If a cycle exists, no topological ordering exists and therefore it will be
 * impossible to take all courses.
 * Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera
 * explaining the basic concepts of Topological Sort.
 * Topological sort could also be done via BFS.
 * 
 * 
 */
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] adjMatrix = new int[numCourses][numCourses];
        int[] indegree = new int[numCourses];
        for (int[] edge: prerequisites) {
        	if(adjMatrix[edge[0]][edge[1]] == 0){
        		adjMatrix[edge[0]][edge[1]] = 1;	
        	}
        	indegree[edge[0]] += 1;
        }

        int count = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
        	if(indegree[i] == 0){
        		queue.offer(i);	
        	}
        }

        while(!queue.isEmpty()){
        	int vertex = queue.poll();
        	count++;
        	for (int i = 0; i < numCourses; i++) {
        		if(adjMatrix[i][vertex] != 0){
        			if(--indegree[i] == 0){
        				queue.offer(i);
        			}	
        		}
        	}
        }
        return count == numCourses;
        
    }

}
