/*
 * @lc app=leetcode id=2610 lang=java
 *
 * [2610] Convert an Array Into a 2D Array With Conditions
 *
 * https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions/description/
 *
 * algorithms
 * Medium (85.33%)
 * Likes:    35
 * Dislikes: 0
 * Total Accepted:    10.6K
 * Total Submissions: 12.4K
 * Testcase Example:  '[1,3,4,1,2,3,1]'
 *
 * You are given an integer array nums. You need to create a 2D array from nums
 * satisfying the following conditions:
 * 
 * 
 * The 2D array should contain only the elements of the array nums.
 * Each row in the 2D array contains distinct integers.
 * The number of rows in the 2D array should be minimal.
 * 
 * 
 * Return the resulting array. If there are multiple answers, return any of
 * them.
 * 
 * Note that the 2D array can have a different number of elements on each
 * row.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,3,4,1,2,3,1]
 * Output: [[1,3,4,2],[1,3],[1]]
 * Explanation: We can create a 2D array that contains the following rows:
 * - 1,3,4,2
 * - 1,3
 * - 1
 * All elements of nums were used, and each row of the 2D array contains
 * distinct integers, so it is a valid answer.
 * It can be shown that we cannot have less than 3 rows in a valid array.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3,4]
 * Output: [[4,3,2,1]]
 * Explanation: All elements of the array are distinct, so we can keep all of
 * them in the first row of the 2D array.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= nums.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> findMatrix(int[] nums) {
        int maxRow = 0;
        int[] cnt = new int[201];
        for(int x : nums){
            cnt[x]++;
            maxRow = Math.max(maxRow, cnt[x]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for(int i =  0; i < maxRow; i++){
            ans.add(new ArrayList<>());
        }
        for(int i = 1; i < cnt.length; i++){
            for(int j = 0; j < cnt[i]; j++){
                ans.get(j).add(i);
            }
        }
        return ans;
    }
}
// @lc code=end
