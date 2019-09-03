/*
 * @lc app=leetcode id=786 lang=java
 *
 * [786] K-th Smallest Prime Fraction
 *
 * https://leetcode.com/problems/k-th-smallest-prime-fraction/description/
 *
 * algorithms
 * Hard (40.52%)
 * Total Accepted:    8.9K
 * Total Submissions: 22K
 * Testcase Example:  '[1,2,3,5]\n3'
 *
 * A sorted list A contains 1, plus some number of primes.  Then, for every p <
 * q in the list, we consider the fraction p/q.
 * 
 * What is the K-th smallest fraction considered?  Return your answer as an
 * array of ints, where answer[0] = p and answer[1] = q.
 * 
 * 
 * Examples:
 * Input: A = [1, 2, 3, 5], K = 3
 * Output: [2, 5]
 * Explanation:
 * The fractions to be considered in sorted order are:
 * 1/5, 1/3, 2/5, 1/2, 3/5, 2/3.
 * The third fraction is 2/5.
 * 
 * Input: A = [1, 7], K = 1
 * Output: [1, 7]
 * 
 * 
 * Note:
 * 
 * 
 * A will have length between 2 and 2000.
 * Each A[i] will be between 1 and 30000.
 * K will be between 1 and A.length * (A.length - 1) / 2.
 * 
 */
class Solution {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
    	int N = A.length;
        Arrays.sort(A);
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->Integer.compare(A[a[0]]*A[b[1]], A[a[1]]*A[b[0]]));
        boolean[][] visited = new boolean[N][N];
        q.offer(new int[]{0, N-1});
        visited[0][N-1] = true;
        while(!q.isEmpty()){
        	int[] cur = q.poll();
        	// System.out.println(Arrays.toString(cur));
        	K--;
        	if(K==0){
        		return new int[]{A[cur[0]], A[cur[1]]};
        	}
        	else{
        		if(cur[0]+1 < cur[1]){
        			if(!visited[cur[0]+1][cur[1]]){
        				q.offer(new int[]{cur[0]+1, cur[1]});	
        				visited[cur[0]+1][cur[1]] = true;
        			}
        			if(!visited[cur[0]][cur[1]-1]){
        				q.offer(new int[]{cur[0], cur[1]-1});	
        				visited[cur[0]][cur[1]-1] = true;
        			}
        		}
        	}
        }
        return new int[0];
    }
}
