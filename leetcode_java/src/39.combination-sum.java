/*
 * [39] Combination Sum
 *
 * https://leetcode.com/problems/combination-sum
 *
 * algorithms
 * Medium (40.07%)
 * Total Accepted:    191.5K
 * Total Submissions: 477.4K
 * Testcase Example:  '[2,3,6,7]\n7'
 *
 * 
 * Given a set of candidate numbers (C) (without duplicates) and a target
 * number (T), find all unique combinations in C where the candidate numbers
 * sums to T. 
 * 
 * 
 * The same repeated number may be chosen from C unlimited number of times.
 * 
 * 
 * Note:
 * 
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * 
 * 
 * 
 * 
 * For example, given candidate set [2, 3, 6, 7] and target 7, 
 * A solution set is: 
 * 
 * [
 * ⁠ [7],
 * ⁠ [2, 2, 3]
 * ]
 * 
 * 
 */

/*
Analysis: 
1. Backtrack: generate element-repeatable subset and check sum. O(2^n)
2. DP: cs[target] = each candiates + cs[target-candidate]
*/
import java.util.*;

class Solution {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		 List<List<Integer>> result = new ArrayList<List<Integer>>();
		 List<Integer> currentSet = new ArrayList<Integer>();
		 elementRepeatableSubset(candidates, target, 0, result, currentSet);
		 return result;
	}

	private void elementRepeatableSubset(int[] candidates, int target, int pos, List<List<Integer>> result, List<Integer> currentSet){
		if(target == 0){
			result.add(new ArrayList<Integer>(currentSet));
			return;
		}
		
		if(target < 0){
			return;
		}

		for (int i = pos; i < candidates.length; i++) {
			currentSet.add(candidates[i]);
			elementRepeatableSubset(candidates, target - candidates[i], i, result, currentSet);
			currentSet.remove(currentSet.size() - 1);
		}
	}
}
