/*
 * @lc app=leetcode id=491 lang=java
 *
 * [491] Increasing Subsequences
 *
 * https://leetcode.com/problems/increasing-subsequences/description/
 *
 * algorithms
 * Medium (45.13%)
 * Likes:    658
 * Dislikes: 107
 * Total Accepted:    44.7K
 * Total Submissions: 98.8K
 * Testcase Example:  '[4,6,7,7]'
 *
 * Given an integer array, your task is to find all the different possible
 * increasing subsequences of the given array, and the length of an increasing
 * subsequence should be at least 2.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7],
 * [4,7,7]]
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The length of the given array will not exceed 15.
 * The range of integer in the given array is [-100,100].
 * The given array may contain duplicates, and two equal integers should also
 * be considered as a special case of increasing sequence.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, res, new ArrayList<>());
        return res;
    }

    void dfs(int[] nums, int pos, List<List<Integer>> result, List<Integer> cur){
    	if(cur.size() > 1){
    		result.add(new ArrayList<>(cur));	
    	}
    	if(pos >= nums.length){
    		return;
    	}
    	Set<Integer> seen = new HashSet<>();
    	for(int i = pos; i < nums.length; i++){
    		if(cur.isEmpty() && !seen.contains(nums[i])){
    			cur.add(nums[i]);
    			seen.add(nums[i]);
    			dfs(nums, i + 1, result, cur);
    			cur.remove(cur.size() - 1);
    		}
    		else if((i == pos || !seen.contains(nums[i])) && cur.get(cur.size() - 1) <= nums[i]){
    			cur.add(nums[i]);
    			seen.add(nums[i]);
    			dfs(nums, i + 1, result, cur);
    			cur.remove(cur.size() - 1);
    		}
    	}
    }
}
// @lc code=end
