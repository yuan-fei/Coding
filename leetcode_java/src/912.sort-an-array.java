/*
 * @lc app=leetcode id=912 lang=java
 *
 * [912] Sort an Array
 *
 * https://leetcode.com/problems/sort-an-array/description/
 *
 * algorithms
 * Medium (58.23%)
 * Likes:    5530
 * Dislikes: 719
 * Total Accepted:    511.3K
 * Total Submissions: 882K
 * Testcase Example:  '[5,2,3,1]'
 *
 * Given an array of integers nums, sort the array in ascending order and
 * return it.
 * 
 * You must solve the problem without using any built-in functions in
 * O(nlog(n)) time complexity and with the smallest space complexity
 * possible.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [5,2,3,1]
 * Output: [1,2,3,5]
 * Explanation: After sorting the array, the positions of some numbers are not
 * changed (for example, 2 and 3), while the positions of other numbers are
 * changed (for example, 1 and 5).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 * Explanation: Note that the values of nums are not necessairly unique.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 5 * 10^4
 * -5 * 10^4 <= nums[i] <= 5 * 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] sortArray(int[] nums) {
        int[] cnt = new int[100005];
        int OFS = 50000;
        for(int x : nums){
            cnt[x + OFS]++;
        }
        int j = 0;
        int[] ret = new int[nums.length];
        for(int i = 0; i < cnt.length; i++){
            while(cnt[i] > 0){
                ret[j++] = i - OFS;
                cnt[i]--;
            }
        }
        return ret;
    }
}
// @lc code=end
