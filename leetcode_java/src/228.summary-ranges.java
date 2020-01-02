/*
 * @lc app=leetcode id=228 lang=java
 *
 * [228] Summary Ranges
 *
 * https://leetcode.com/problems/summary-ranges/description/
 *
 * algorithms
 * Medium (37.62%)
 * Likes:    482
 * Dislikes: 459
 * Total Accepted:    148.8K
 * Total Submissions: 395.2K
 * Testcase Example:  '[0,1,2,4,5,7]'
 *
 * Given a sorted integer array without duplicates, return the summary of its
 * ranges.
 * 
 * Example 1:
 * 
 * 
 * Input:  [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:  [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> summaryRanges(int[] nums) {
    	if(nums.length == 0){
    		return new ArrayList<>();
    	}
    	List<String> ans = new ArrayList<>();
    	int left = nums[0];
    	int right = left;
        for(int i = 1; i < nums.length; i++){
        	if(nums[i] != right + 1){
        		ans.add(toString(left, right));
        		left = right = nums[i];
        	}
        	else{
        		right = nums[i];
        	}
        }
        ans.add(toString(left, right));
        return ans;
    }

    String toString(int left, int right){
    	if(left == right){
    		return "" + left;
    	}
    	else{
    		return left + "->" + right;
    	}
    }
}
// @lc code=end
