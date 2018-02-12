/*
 * [40] Combination Sum II
 *
 * https://leetcode.com/problems/combination-sum-ii/description/
 *
 * algorithms
 * Medium (35.47%)
 * Total Accepted:    142.4K
 * Total Submissions: 401.5K
 * Testcase Example:  '[10,1,2,7,6,1,5]\n8'
 *
 * 
 * Given a collection of candidate numbers (C) and a target number (T), find
 * all unique combinations in C where the candidate numbers sums to T.
 * 
 * 
 * Each number in C may only be used once in the combination.
 * 
 * Note:
 * 
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * 
 * 
 * 
 * 
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
 * A solution set is: 
 * 
 * [
 * ⁠ [1, 7],
 * ⁠ [1, 2, 5],
 * ⁠ [2, 6],
 * ⁠ [1, 1, 6]
 * ]
 * 
 * 
 */

/*
* Analysis:
* 1. Backtrack: all unique combinations
* 2. DP: hard to implement duplicate removal
*/
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> currentSet = new ArrayList<Integer>();
        Arrays.sort(candidates);
        combinationSum2(candidates, target, 0, result, currentSet);
        return result;
    }

    private void combinationSum2(int[] candidates, int target, int pos, List<List<Integer>> result, List<Integer> currentSet){
    	if(target == 0){
    		result.add(new ArrayList<Integer>(currentSet));
    		return;
    	}
    	if(target < 0){
    		return;
    	}
    	for (int i = pos; i < candidates.length; i++) {
    		if(i > pos && candidates[i] == candidates[i - 1]){
    			continue;
    		}
    		currentSet.add(candidates[i]);
    		combinationSum2(candidates, target - candidates[i], i + 1, result, currentSet);
    		currentSet.remove(currentSet.size() - 1);
    	}
    }

    private void combinationSum2_dp(int[] candidates, int target){
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> currentSet = new ArrayList<Integer>();

    }
}
