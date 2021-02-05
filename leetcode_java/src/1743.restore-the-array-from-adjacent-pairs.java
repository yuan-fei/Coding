/*
 * @lc app=leetcode id=1743 lang=java
 *
 * [1743] Restore the Array From Adjacent Pairs
 *
 * https://leetcode.com/problems/restore-the-array-from-adjacent-pairs/description/
 *
 * algorithms
 * Medium (62.11%)
 * Likes:    162
 * Dislikes: 4
 * Total Accepted:    7.2K
 * Total Submissions: 11.6K
 * Testcase Example:  '[[2,1],[3,4],[3,2]]'
 *
 * There is an integer array nums that consists of n unique elements, but you
 * have forgotten it. However, you do remember every pair of adjacent elements
 * in nums.
 * 
 * You are given a 2D integer array adjacentPairs of size n - 1 where each
 * adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are
 * adjacent in nums.
 * 
 * It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1]
 * will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1],
 * nums[i]]. The pairs can appear in any order.
 * 
 * Return the original array nums. If there are multiple solutions, return any
 * of them.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: adjacentPairs = [[2,1],[3,4],[3,2]]
 * Output: [1,2,3,4]
 * Explanation: This array has all its adjacent pairs in adjacentPairs.
 * Notice that adjacentPairs[i] may not be in left-to-right order.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
 * Output: [-2,4,1,-3]
 * Explanation: There can be negative numbers.
 * Another solution is [-3,1,4,-2], which would also be accepted.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: adjacentPairs = [[100000,-100000]]
 * Output: [100000,-100000]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * nums.length == n
 * adjacentPairs.length == n - 1
 * adjacentPairs[i].length == 2
 * 2 <= n <= 10^5
 * -10^5 <= nums[i], ui, vi <= 10^5
 * There exists some nums that has adjacentPairs as its pairs.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
    	Map<Integer, List<Integer>> m = new HashMap<>();
    	for(int[] p : adjacentPairs){
    		if(!m.containsKey(p[0])){
				m.put(p[0], new ArrayList<>());
    		}
    		m.get(p[0]).add(p[1]);
    		if(!m.containsKey(p[1])){
				m.put(p[1], new ArrayList<>());
    		}
    		m.get(p[1]).add(p[0]);
    	}
    	int cur = 0;
    	for(int k : m.keySet()){
    		if(m.get(k).size() == 1){
    			cur = k;
    			break;
    		}
    	}
    	int[] ans = new int[adjacentPairs.length + 1];
    	boolean hasNext = true;
    	int i = 0;
    	int prev = cur;
    	while(hasNext){
    		ans[i++] = cur;
    		hasNext = false;
    		for(int n : m.get(cur)){
    			if(n != prev){
    				prev = cur;
    				cur = n;
    				hasNext = true;
    				break;
    			}
    		}
    		// System.out.println(Arrays.asList(prev, cur));
    	}
    	return ans;
    }


}
// @lc code=end
