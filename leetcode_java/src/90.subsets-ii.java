/*
 * @lc app=leetcode id=90 lang=java
 *
 * [90] Subsets II
 *
 * https://leetcode.com/problems/subsets-ii/description/
 *
 * algorithms
 * Medium (43.31%)
 * Total Accepted:    217.4K
 * Total Submissions: 501.9K
 * Testcase Example:  '[1,2,2]'
 *
 * Given a collection of integers that might contain duplicates, nums, return
 * all possible subsets (the power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * Example:
 * 
 * 
 * Input: [1,2,2]
 * Output:
 * [
 * ⁠ [2],
 * ⁠ [1],
 * ⁠ [1,2,2],
 * ⁠ [2,2],
 * ⁠ [1,2],
 * ⁠ []
 * ]
 * 
 * 
 */
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        subsetsWithDupHelper(nums, new ArrayList<>(), ans, 0);
        return ans;
    }

    void subsetsWithDupHelper(int[] nums, List<Integer> cur, List<List<Integer>> ans, int pos){
    	ans.add(new ArrayList<>(cur));
    	if(pos == nums.length){
    		return;
    	}
    	for(int i = pos; i < nums.length; i++){
    		if(i==pos || nums[i]!=nums[i-1]){
	    		cur.add(nums[i]);
	    		subsetsWithDupHelper(nums, cur, ans, i+1);
	    		cur.remove(cur.size() - 1);	
    		}
    	}
    }
}
