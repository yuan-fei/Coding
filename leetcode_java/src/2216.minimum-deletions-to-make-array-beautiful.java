/*
 * @lc app=leetcode id=2216 lang=java
 *
 * [2216] Minimum Deletions to Make Array Beautiful
 *
 * https://leetcode.com/problems/minimum-deletions-to-make-array-beautiful/description/
 *
 * algorithms
 * Medium (38.83%)
 * Likes:    94
 * Dislikes: 50
 * Total Accepted:    9.1K
 * Total Submissions: 23.4K
 * Testcase Example:  '[1,1,2,3,5]'
 *
 * You are given a 0-indexed integer array nums. The array nums is beautiful
 * if:
 * 
 * 
 * nums.length is even.
 * nums[i] != nums[i + 1] for all i % 2 == 0.
 * 
 * 
 * Note that an empty array is considered beautiful.
 * 
 * You can delete any number of elements from nums. When you delete an element,
 * all the elements to the right of the deleted element will be shifted one
 * unit to the left to fill the gap created and all the elements to the left of
 * the deleted element will remain unchanged.
 * 
 * Return the minimum number of elements to delete from nums to make it
 * beautiful.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,1,2,3,5]
 * Output: 1
 * Explanation: You can delete either nums[0] or nums[1] to make nums =
 * [1,2,3,5] which is beautiful. It can be proven you need at least 1 deletion
 * to make nums beautiful.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,1,2,2,3,3]
 * Output: 2
 * Explanation: You can delete nums[0] and nums[5] to make nums = [1,2,2,3]
 * which is beautiful. It can be proven you need at least 2 deletions to make
 * nums beautiful.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minDeletion(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(ans.size() % 2 == 1){
                if(ans.get(ans.size() - 1) != nums[i]){
                    ans.add(nums[i]);
                }
            }
            else{
                ans.add(nums[i]);
            }
        }
        // System.out.println(ans);
        if(ans.size() % 2 == 1){
            return nums.length - ans.size() + 1;    
        }
        return nums.length - ans.size();
    }
}
// @lc code=end
