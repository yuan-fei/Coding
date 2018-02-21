/*
 * [46] Permutations
 *
 * https://leetcode.com/problems/permutations/description/
 *
 * algorithms
 * Medium (46.46%)
 * Total Accepted:    218.2K
 * Total Submissions: 468.6K
 * Testcase Example:  '[1,2,3]'
 *
 * 
 * Given a collection of distinct numbers, return all possible permutations.
 * 
 * 
 * 
 * For example,
 * [1,2,3] have the following permutations:
 * 
 * [
 * ⁠ [1,2,3],
 * ⁠ [1,3,2],
 * ⁠ [2,1,3],
 * ⁠ [2,3,1],
 * ⁠ [3,1,2],
 * ⁠ [3,2,1]
 * ]
 * 
 * 
 */
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> resultSet = new ArrayList<List<Integer>>();
        permute(nums, 0, resultSet, new ArrayList<Integer>());
        return resultSet;
        
    }

    public void permute(int[] nums, int k, List<List<Integer>> resultSet, List<Integer> currentSet){
    	if(k == nums.length){
    		resultSet.add(new ArrayList(currentSet));
    	}
    	else{
    		for (int i = k ; i < nums.length; i++) {
    			swap(nums, k, i);
    			print(nums);
    			currentSet.add(nums[k]);
    			permute(nums, k + 1, resultSet, currentSet);
    			currentSet.remove(currentSet.size() - 1);
    			swap(nums, i, k);
    			print(nums);
    		}
    	}
    }

    private void swap(int[] nums, int i, int j){
    	int num = nums[i];
    	nums[i] = nums[j];
    	nums[j] = num;
    }

    private void print(int[] nums){
    	for (int num : nums) {
    		System.out.print(num);
    	}
    		System.out.println();
    }
}
