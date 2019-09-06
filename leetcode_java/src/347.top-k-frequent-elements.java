/*
 * @lc app=leetcode id=347 lang=java
 *
 * [347] Top K Frequent Elements
 *
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 *
 * algorithms
 * Medium (56.29%)
 * Total Accepted:    237.9K
 * Total Submissions: 422.4K
 * Testcase Example:  '[1,1,1,2,2,3]\n2'
 *
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1], k = 1
 * Output: [1]
 * 
 * 
 * Note: 
 * 
 * 
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is
 * the array's size.
 * 
 * 
 */
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
    	List<Integer> ans = new ArrayList<>();
    	if(nums.length > 0){
			Map<Integer, Integer> cntMap = new HashMap<>();
	        for (int n : nums) {
	        	int cnt = 0;
	        	if(cntMap.containsKey(n)){
	        		cnt = cntMap.get(n);
	        	}
	        	cntMap.put(n, cnt+1);
	        }

	        PriorityQueue<int[]> q = new PriorityQueue<>((a, b)->Integer.compare(a[1], b[1]));

	        for(int e : cntMap.keySet()){
	        	q.offer(new int[]{e, cntMap.get(e)});
	        	if(q.size() > k){
	        		q.poll();
	        	}
	        }

	        while(!q.isEmpty()){
	        	ans.add(q.poll()[0]);
	        }
	        Collections.reverse(ans);
    	}
        return ans;
    }
}
