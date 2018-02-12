/*
 * [216] Combination Sum III
 *
 * https://leetcode.com/problems/combination-sum-iii/description/
 *
 * algorithms
 * Medium (46.72%)
 * Total Accepted:    83.9K
 * Total Submissions: 179.5K
 * Testcase Example:  '3\n7'
 *
 * 
 * Find all possible combinations of k numbers that add up to a number n, given
 * that only numbers from 1 to 9 can be used and each combination should be a
 * unique set of numbers.
 * 
 * 
 * 
 * ⁠Example 1:
 * Input:  k = 3,  n = 7
 * Output: 
 * 
 * [[1,2,4]]
 * 
 * 
 * ⁠Example 2:
 * Input:  k = 3,  n = 9
 * Output: 
 * 
 * [[1,2,6], [1,3,5], [2,3,4]]
 * 
 * 
 * 
 * Credits:Special thanks to @mithmatt for adding this problem and creating all
 * test cases.
 */

/*
Analysis:

*/
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> currentSet = new ArrayList<Integer>();
		combinationSum3(k, n, 1, result, currentSet);
		return result;
    }

    private void combinationSum3(int k, int n, int pos, List<List<Integer>> result, List<Integer> currentSet){
    	if(k == 0 ){
    		if(n == 0){
    			result.add(new ArrayList<Integer>(currentSet));	
    		}
    		return;
    	}

    	if(pos <= Math.min(n,9)) {
    		combinationSum3(k, n, pos + 1, result, currentSet);
    		currentSet.add(pos);
    		combinationSum3(k - 1, n - pos, pos + 1, result, currentSet);
    		currentSet.remove(currentSet.size() - 1);
    	}
    }
}
