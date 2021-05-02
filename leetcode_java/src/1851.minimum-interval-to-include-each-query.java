/*
 * @lc app=leetcode id=1851 lang=java
 *
 * [1851] Minimum Interval to Include Each Query
 *
 * https://leetcode.com/problems/minimum-interval-to-include-each-query/description/
 *
 * algorithms
 * Hard (32.44%)
 * Likes:    83
 * Dislikes: 0
 * Total Accepted:    1.5K
 * Total Submissions: 4.7K
 * Testcase Example:  '[[1,4],[2,4],[3,6],[4,4]]\n[2,3,4,5]'
 *
 * You are given a 2D integer array intervals, where intervals[i] = [lefti,
 * righti] describes the i^th interval starting at lefti and ending at righti
 * (inclusive). The size of an interval is defined as the number of integers it
 * contains, or more formally righti - lefti + 1.
 * 
 * You are also given an integer array queries. The answer to the j^th query is
 * the size of the smallest interval i such that lefti <= queries[j] <= righti.
 * If no such interval exists, the answer is -1.
 * 
 * Return an array containing the answers to the queries.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
 * Output: [3,3,1,4]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,4] is the smallest interval containing 2. The
 * answer is 4 - 2 + 1 = 3.
 * - Query = 3: The interval [2,4] is the smallest interval containing 3. The
 * answer is 4 - 2 + 1 = 3.
 * - Query = 4: The interval [4,4] is the smallest interval containing 4. The
 * answer is 4 - 4 + 1 = 1.
 * - Query = 5: The interval [3,6] is the smallest interval containing 5. The
 * answer is 6 - 3 + 1 = 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
 * Output: [2,-1,4,6]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,3] is the smallest interval containing 2. The
 * answer is 3 - 2 + 1 = 2.
 * - Query = 19: None of the intervals contain 19. The answer is -1.
 * - Query = 5: The interval [2,5] is the smallest interval containing 5. The
 * answer is 5 - 2 + 1 = 4.
 * - Query = 22: The interval [20,25] is the smallest interval containing 22.
 * The answer is 25 - 20 + 1 = 6.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= intervals.length <= 10^5
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * 1 <= lefti <= righti <= 10^7
 * 1 <= queries[j] <= 10^7
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int n = intervals.length;
    	int m = queries.length;
        Integer[] ids = new Integer[m];
        for(int i = 0; i < m; i++){
        	ids[i] = i;
        }
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(ids, (a, b) -> Integer.compare(queries[a], queries[b]));
        PriorityQueue<Integer> endTimeQueue = new PriorityQueue<>((a, b) -> Integer.compare(intervals[a][1], intervals[b][1]));
        TreeMap<Integer, Integer> span = new TreeMap<>();
        int[] ans = new int[m];
        Arrays.fill(ans, -1);
        int j = 0;
        for(int i = 0; i < m; i++){
        	int id = ids[i];
        	while(j < n && intervals[j][0] <= queries[id]){
        		endTimeQueue.offer(j);
        		add(span, intervals[j][1] - intervals[j][0] + 1);
        		j++;
        	}
        	while(!endTimeQueue.isEmpty() && intervals[endTimeQueue.peek()][1] < queries[id]){
        		int oid = endTimeQueue.poll();
        		remove(span, intervals[oid][1] - intervals[oid][0] + 1);
        	}
        	// System.out.println("" + queries[id]+ endTimeQueue + span);
        	if(!span.isEmpty()){
				ans[id] = span.firstKey();
        	}
        }
        return ans;
    }

    void add(TreeMap<Integer, Integer> tm, int x){
    	tm.put(x, tm.getOrDefault(x, 0) + 1);
    }

    void remove(TreeMap<Integer, Integer> tm, int x){
    	int cnt = tm.get(x) - 1;
    	if(cnt > 0){
    		tm.put(x, cnt);
    	}
    	else{
    		tm.remove(x);
    	}
    }
}
// @lc code=end
