/*
 * @lc app=leetcode id=2972 lang=java
 *
 * [2972] Count the Number of Incremovable Subarrays II
 *
 * https://leetcode.com/problems/count-the-number-of-incremovable-subarrays-ii/description/
 *
 * algorithms
 * Hard (37.25%)
 * Likes:    220
 * Dislikes: 19
 * Total Accepted:    8.7K
 * Total Submissions: 22K
 * Testcase Example:  '[1,2,3,4]'
 *
 * You are given a 0-indexed array of positive integers nums.
 * 
 * A subarray of nums is called incremovable if nums becomes strictly
 * increasing on removing the subarray. For example, the subarray [3, 4] is an
 * incremovable subarray of [5, 3, 4, 6, 7] because removing this subarray
 * changes the array [5, 3, 4, 6, 7] to [5, 6, 7] which is strictly
 * increasing.
 * 
 * Return the total number of incremovable subarrays of nums.
 * 
 * Note that an empty array is considered strictly increasing.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an
 * array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,4]
 * Output: 10
 * Explanation: The 10 incremovable subarrays are: [1], [2], [3], [4], [1,2],
 * [2,3], [3,4], [1,2,3], [2,3,4], and [1,2,3,4], because on removing any one
 * of these subarrays nums becomes strictly increasing. Note that you cannot
 * select an empty subarray.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [6,5,7,8]
 * Output: 7
 * Explanation: The 7 incremovable subarrays are: [5], [6], [5,7], [6,5],
 * [5,7,8], [6,5,7] and [6,5,7,8].
 * It can be shown that there are only 7 incremovable subarrays in nums.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [8,7,6,6]
 * Output: 3
 * Explanation: The 3 incremovable subarrays are: [8,7,6], [7,6,6], and
 * [8,7,6,6]. Note that [8,7] is not an incremovable subarray because after
 * removing [8,7] nums becomes [6,6], which is sorted in ascending order but
 * not strictly increasing.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public long incremovableSubarrayCount(int[] nums) {
        int[] numsRefined = new int[nums.length + 2];
        int n = numsRefined.length;
        System.arraycopy(nums, 0, numsRefined, 1, nums.length);
        numsRefined[0] = 0;
        numsRefined[n - 1] = (int)1e9 + 7;
        int l = 0;
        int r = n - 1;
        long ans = 0;
        for(; l < n; l++){
            if(l == n - 1 || numsRefined[l] >= numsRefined[l + 1]){
                break;
            }
        }
        // System.out.println(l);
        for(; r > 0; r--){
            if(r < n - 1 && numsRefined[r] >= numsRefined[r + 1]){
                break;
            }
            while(numsRefined[l] >= numsRefined[r]){
                l--;
            }
            if(l + 1 == r){
                l--;
            }
            ans += l + 1;
            // System.out.println(Arrays.asList(r, l, ans));
        }
        return ans;
    }
}
// @lc code=end
