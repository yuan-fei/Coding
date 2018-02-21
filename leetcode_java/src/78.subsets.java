/*
 * [78] Subsets
 *
 * https://leetcode.com/problems/subsets/description/
 *
 * algorithms
 * Medium (43.82%)
 * Total Accepted:    217.5K
 * Total Submissions: 494.7K
 * Testcase Example:  '[1,2,3]'
 *
 * 
 * Given a set of distinct integers, nums, return all possible subsets (the
 * power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * 
 * For example,
 * If nums = [1,2,3], a solution is:
 * 
 * 
 * 
 * [
 * ⁠ [3],
 * ⁠ [1],
 * ⁠ [2],
 * ⁠ [1,2,3],
 * ⁠ [1,3],
 * ⁠ [2,3],
 * ⁠ [1,2],
 * ⁠ []
 * ]
 * 
 */
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> resultSet = new ArrayList<List<Integer>>();
        subsetsHelper(nums, 0 ,resultSet, new ArrayList<Integer>());
        return resultSet;
    }

    public void subsetsHelper(int[] nums, int k, List<List<Integer>> resultSet, List<Integer> current) {
    	resultSet.add(new ArrayList<Integer>(current));
    	if(k == nums.length){
    		return;
    	}
    	for (int i = k; i < nums.length; i++) {
    		current.add(nums[i]);
    		subsetsHelper(nums, i + 1, resultSet, current);
    		current.remove(current.size() - 1);
    	}
    }
}
