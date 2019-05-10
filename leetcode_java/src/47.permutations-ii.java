/*
 * [47] Permutations II
 *
 * https://leetcode.com/problems/permutations-ii/description/
 *
 * algorithms
 * Medium (35.63%)
 * Total Accepted:    231.3K
 * Total Submissions: 581.6K
 * Testcase Example:  '[1,1,2]'
 *
 * Given a collection of numbers that might contain duplicates, return all
 * possible unique permutations.
 * 
 * Example:
 * 
 * 
 * Input: [1,1,2]
 * Output:
 * [
 * ⁠ [1,1,2],
 * ⁠ [1,2,1],
 * ⁠ [2,1,1]
 * ]
 * 
 * 
 */
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
    	List<List<Integer>> res = new ArrayList<>();
        permute(nums, 0, res, new ArrayList<Integer>());
        return res;
    }

    private void permute(int[] nums, int pos, List<List<Integer>> res, List<Integer> cur){
    	if(pos == nums.length){
    		res.add(new ArrayList<Integer>(cur));
    		return ;
    	}
    	else{
    		Set<Integer> set = new HashSet<>();
    		for(int i = pos; i < nums.length; i++){
    			if(!set.contains(nums[i])){
    				set.add(nums[i]);
	    			swap(nums, pos, i);
	    			cur.add(nums[pos]);
	    			permute(nums, pos+1, res, cur);
	    			cur.remove(cur.size() - 1);
	    			swap(nums, pos, i);	
    			}
    		}
    		
    	}
    }

    void swap(int[] nums, int i, int j){
    	int t = nums[i];
    	nums[i] = nums[j];
    	nums[j] = t;
    }
}
