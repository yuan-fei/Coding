/*
 * @lc app=leetcode id=2856 lang=java
 *
 * [2856] Minimum Array Length After Pair Removals
 *
 * https://leetcode.com/problems/minimum-array-length-after-pair-removals/description/
 *
 * algorithms
 * Medium (20.81%)
 * Likes:    374
 * Dislikes: 98
 * Total Accepted:    24K
 * Total Submissions: 103.7K
 * Testcase Example:  '[1,2,3,4]'
 *
 * Given an integer array num sorted in non-decreasing order.
 * 
 * You can perform the following operation any number of times:
 * 
 * 
 * Choose two indices, i and j, where nums[i] < nums[j].
 * Then, remove the elements at indices i and j from nums. The remaining
 * elements retain their original order, and the array is re-indexed.
 * 
 * 
 * Return the minimum length of nums after applying the operation zero or more
 * times.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,4]
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,1,2,2,3,3]
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1000000000,1000000000]
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * Since both numbers are equal, they cannot be removed.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: nums = [2,3,4,4,4]
 * 
 * Output: 1
 * 
 * Explanation:
 * 
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * nums is sorted in non-decreasing order.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minLengthAfterRemovals(List<Integer> nums) {
        int low = 0;
        int high = nums.size() / 2;
        while(low + 1 < high){
            int mid = (low + high) / 2;
            if(feasible(nums, mid)){
                low = mid;
            }
            else{
                high = mid;
            }
        }
        if(feasible(nums, high)){
            return nums.size() - 2 * high;
        }
        return nums.size() - 2 * low;
    }

    boolean feasible(List<Integer> nums, int cnt){
        for(int i = 0; i < cnt; i++){
            if(nums.get(i) >= nums.get(nums.size() - cnt + i)){
                return false;
            }
        }
        return true;
    }
}
// @lc code=end
