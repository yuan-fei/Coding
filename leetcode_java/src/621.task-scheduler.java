/*
 * [621] Task Scheduler
 *
 * https://leetcode.com/problems/task-scheduler/description/
 *
 * algorithms
 * Medium (42.79%)
 * Total Accepted:    34.2K
 * Total Submissions: 80.7K
 * Testcase Example:  '["A","A","A","B","B","B"]\n2'
 *
 * Given a char array representing tasks CPU need to do. It contains capital
 * letters A to Z where different letters represent different tasks.Tasks could
 * be done without original order. Each task could be done in one interval. For
 * each interval, CPU could finish one task or just be idle.
 * 
 * However, there is a non-negative cooling interval n that means between two
 * same tasks, there must be at least n intervals that CPU are doing different
 * tasks or just be idle. 
 * 
 * You need to return the least number of intervals the CPU will take to finish
 * all the given tasks.
 * 
 * Example 1:
 * 
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 * 
 * 
 * 
 * Note:
 * 
 * The number of tasks is in the range [1, 10000].
 * The integer n is in the range [0, 100].
 * 
 * 
 */
class Solution {
    public int leastInterval(char[] tasks, int n) {
    	PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>(){
    		public int compare(Integer a, Integer b){
    			return -Integer.compare(a, b);
    		}
    	});
    	int[] tc = new int[26];
    	for(char t: tasks){
    		tc[t-'A']++;
    	}
    	for(int c: tc){
    		if(c > 0){
    			queue.offer(c);
    		}
    	}
    	
    	int tot = 0;
    	while(!queue.isEmpty()){
    		List<Integer> leftTaskCnt = new ArrayList<>();
    		int i = 0;
    		while(i < n + 1){
    			if(!queue.isEmpty()){
    				int cnt = queue.poll();
    				if(--cnt > 0){
    					leftTaskCnt.add(cnt);	
    				}
    				tot++;
    				i++;
    			}
    			else{
    				break;
    			}
    		}
    		if(!leftTaskCnt.isEmpty()){
	    		tot += n + 1 - i;
	    		for(int c: leftTaskCnt){
	    			queue.offer(c);
	    		}	
    		}
    	}
    	return tot;
    }
}
