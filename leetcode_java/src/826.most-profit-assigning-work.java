/*
 * @lc app=leetcode id=826 lang=java
 *
 * [826] Most Profit Assigning Work
 *
 * https://leetcode.com/problems/most-profit-assigning-work/description/
 *
 * algorithms
 * Medium (35.50%)
 * Total Accepted:    12.1K
 * Total Submissions: 33.3K
 * Testcase Example:  '[2,4,6,8,10]\n[10,20,30,40,50]\n[4,5,6,7]'
 *
 * We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i]
 * is the profit of the ith job. 
 * 
 * Now we have some workers. worker[i] is the ability of the ith worker, which
 * means that this worker can only complete a job with difficulty at most
 * worker[i]. 
 * 
 * Every worker can be assigned at most one job, but one job can be completed
 * multiple times.
 * 
 * For example, if 3 people attempt the same job that pays $1, then the total
 * profit will be $3.  If a worker cannot complete any job, his profit is $0.
 * 
 * What is the most profit we can make?
 * 
 * Example 1:
 * 
 * 
 * Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker =
 * [4,5,6,7]
 * Output: 100 
 * Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get
 * profit of [20,20,30,30] seperately.
 * 
 * Notes:
 * 
 * 
 * 1 <= difficulty.length = profit.length <= 10000
 * 1 <= worker.length <= 10000
 * difficulty[i], profit[i], worker[i]  are in range [1, 10^5]
 * 
 * 
 */
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
    	int n = difficulty.length;
    	int m  =worker.length;
        Job[] jobs = new Job[n];
        for(int i = 0; i < n; i++){
        	jobs[i] = new Job(difficulty[i], profit[i]);
        }
        Arrays.sort(jobs, (a, b)->Integer.compare(a.d, b.d));
        TreeMap<Integer, Integer> s = new TreeMap<>();
        s.put(jobs[0].d, jobs[0].p);
        for(int i = 1; i < n; i++){
        	s.put(jobs[i].d, Math.max(jobs[i].p, s.lastEntry().getValue()));
        }

        int total = 0;
        for (int i = 0; i < m; i++) {
        	Map.Entry<Integer, Integer> e = s.floorEntry(worker[i]);
        	total += (e==null)?0:e.getValue();
        }
        return total;
    }

    static class Job{
    	int d;
    	int p;

    	Job(int difficulty, int profit){
    		d = difficulty;
    		p = profit;
    	}
    }
}
