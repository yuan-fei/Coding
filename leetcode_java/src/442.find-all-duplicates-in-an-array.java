/*
 * @lc app=leetcode id=442 lang=java
 *
 * [442] Find All Duplicates in an Array
 *
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/description/
 *
 * algorithms
 * Medium (64.85%)
 * Likes:    1849
 * Dislikes: 143
 * Total Accepted:    150.7K
 * Total Submissions: 232.2K
 * Testcase Example:  '[4,3,2,7,8,2,3,1]'
 *
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements
 * appear twice and others appear once.
 * 
 * Find all the elements that appear twice in this array.
 * 
 * Could you do it without extra space and in O(n) runtime?
 * 
 * Example:
 * 
 * Input:
 * [4,3,2,7,8,2,3,1]
 * 
 * Output:
 * [2,3]
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
    	int n = nums.length;
    	List<Integer> res = new ArrayList<>();
        for(int i = 0; i < n; i++){
        	int index = Math.abs(nums[i]) - 1;
        	if(nums[index] > 0){
        		nums[index] *= -1;
        	}
        	else{
        		res.add(Math.abs(nums[i]));
        	}
        }
        return res;
    }
}
// @lc code=end
