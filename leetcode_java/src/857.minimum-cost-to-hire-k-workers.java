/*
 * @lc app=leetcode id=857 lang=java
 *
 * [857] Minimum Cost to Hire K Workers
 *
 * https://leetcode.com/problems/minimum-cost-to-hire-k-workers/description/
 *
 * algorithms
 * Hard (48.06%)
 * Total Accepted:    20K
 * Total Submissions: 41.5K
 * Testcase Example:  '[10,20,5]\n[70,50,30]\n2'
 *
 * There are N workers.  The i-th worker has a quality[i] and a minimum wage
 * expectation wage[i].
 * 
 * Now we want to hire exactly K workers to form a paid group.  When hiring a
 * group of K workers, we must pay them according to the following rules:
 * 
 * 
 * Every worker in the paid group should be paid in the ratio of their quality
 * compared to other workers in the paid group.
 * Every worker in the paid group must be paid at least their minimum wage
 * expectation.
 * 
 * 
 * Return the least amount of money needed to form a paid group satisfying the
 * above conditions.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: quality = [10,20,5], wage = [70,50,30], K = 2
 * Output: 105.00000
 * Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
 * Output: 30.66667
 * Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers
 * seperately. 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= K <= N <= 10000, where N = quality.length = wage.length
 * 1 <= quality[i] <= 10000
 * 1 <= wage[i] <= 10000
 * Answers within 10^-5 of the correct answer will be considered correct.
 * 
 * 
 * 
 * 
 */
class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
    	int N = quality.length;
    	Worker[] workers  = new Worker[N];
        for(int i = 0; i < N; i++){
        	workers[i] = new Worker(quality[i], wage[i]);
        }
        Arrays.sort(workers, (a,b)->Integer.compare(a.w*b.q, b.w*a.q));
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b)->-Integer.compare(a, b));
        Worker minCostWorker = new Worker(0, 1);
        long minCostTotal = 1;
        long curTotal = 0;
        for(Worker worker: workers){
        	q.offer(worker.q);
        	curTotal+=worker.q;
        	if(q.size() > K){
        		curTotal-=q.poll();
        	}
        	if(q.size() == K){
	        	if(minCostTotal*minCostWorker.w*worker.q > curTotal*worker.w*minCostWorker.q){
	        		minCostWorker = worker;
	        		minCostTotal = curTotal;
	        	}	
        	}
        }
        return (double)minCostTotal*minCostWorker.w/minCostWorker.q;
    }

    static class Worker{
    	int q;
    	int w;
    	Worker(int qq, int ww){
    		q=qq;
    		w=ww;
    	}
    }
}
