/*
 * @lc app=leetcode id=659 lang=java
 *
 * [659] Split Array into Consecutive Subsequences
 *
 * https://leetcode.com/problems/split-array-into-consecutive-subsequences/description/
 *
 * algorithms
 * Medium (41.22%)
 * Total Accepted:    23.1K
 * Total Submissions: 56K
 * Testcase Example:  '[1,2,3,3,4,5]'
 *
 * Given an array nums sorted in ascending order, return true if and only if
 * you can split it into 1 or more subsequences such that each subsequence
 * consists of consecutive integers and has length at least 3.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [1,2,3,3,4,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences : 
 * 1, 2, 3
 * 3, 4, 5
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [1,2,3,3,4,4,5,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences : 
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: [1,2,3,4,4,5]
 * Output: False
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10000
 * 
 * 
 * 
 * 
 */
class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> prefixMap = new HashMap<>();
        for (int n : nums) {
        	int cnt = 1;
        	if(prefixMap.containsKey(n-1)){
        		cnt += prefixMap.get(n-1).poll();	
        		if(prefixMap.get(n-1).isEmpty()){
        			prefixMap.remove(n-1);
        		}
        	}
        	if(!prefixMap.containsKey(n)){
				prefixMap.put(n, new PriorityQueue<>());
    		}
    		prefixMap.get(n).offer(cnt);
        }
        for (int k : prefixMap.keySet()) {
        	Queue<Integer> q = prefixMap.get(k);
        	while(!q.isEmpty()){
        		if(q.poll()<3){
        			return false;
        		}
        	}
        }
        return true;
    }
}
