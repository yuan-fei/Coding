/*
 * @lc app=leetcode id=2488 lang=java
 *
 * [2488] Count Subarrays With Median K
 *
 * https://leetcode.com/problems/count-subarrays-with-median-k/description/
 *
 * algorithms
 * Hard (32.89%)
 * Likes:    120
 * Dislikes: 2
 * Total Accepted:    3K
 * Total Submissions: 9.2K
 * Testcase Example:  '[3,2,1,4,5]\n4'
 *
 * You are given an array nums of size n consisting of distinct integers from 1
 * to n and a positive integer k.
 * 
 * Return the number of non-empty subarrays in nums that have a median equal to
 * k.
 * 
 * Note:
 * 
 * 
 * The median of an array is the middle element after sorting the array in
 * ascending order. If the array is of even length, the median is the left
 * middle element.
 * 
 * 
 * For example, the median of [2,3,1,4] is 2, and the median of [8,4,3,5,1] is
 * 4.
 * 
 * 
 * A subarray is a contiguous part of an array.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,2,1,4,5], k = 4
 * Output: 3
 * Explanation: The subarrays that have a median equal to 4 are: [4], [4,5] and
 * [1,4,5].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,3,1], k = 3
 * Output: 1
 * Explanation: [3] is the only subarray that has a median equal to 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i], k <= n
 * The integers in nums are distinct.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int pos = IntStream.range(0, n).filter(i -> nums[i] == k).findFirst().getAsInt();
        Map<Integer, Integer> m = new HashMap<>();
        int curDiff = 0;
        for(int i = pos; i < n; i++){
            if(nums[i] > k){
                curDiff++;
            }
            else if(nums[i] < k){
                curDiff--;
            }
            m.put(curDiff, m.getOrDefault(curDiff, 0) + 1);
        }
        curDiff = 0;
        int ans = 0;
        for(int i = pos; i >= 0; i--){
            if(nums[i] > k){
                curDiff++;
            }
            else if(nums[i] < k){
                curDiff--;
            }
            ans += m.getOrDefault(-curDiff, 0);
            ans += m.getOrDefault(-curDiff + 1, 0);
        }
        return ans;
    }
}
// @lc code=end
