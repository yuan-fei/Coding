/*
 * @lc app=leetcode id=2420 lang=java
 *
 * [2420] Find All Good Indices
 *
 * https://leetcode.com/problems/find-all-good-indices/description/
 *
 * algorithms
 * Medium (35.31%)
 * Likes:    304
 * Dislikes: 19
 * Total Accepted:    12.5K
 * Total Submissions: 35.5K
 * Testcase Example:  '[2,1,1,1,3,4,1]\n2'
 *
 * You are given a 0-indexed integer array nums of size n and a positive
 * integer k.
 * 
 * We call an index i in the range k <= i < n - k good if the following
 * conditions are satisfied:
 * 
 * 
 * The k elements that are just before the index i are in non-increasing
 * order.
 * The k elements that are just after the index i are in non-decreasing
 * order.
 * 
 * 
 * Return an array of all good indices sorted in increasing order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,1,1,1,3,4,1], k = 2
 * Output: [2,3]
 * Explanation: There are two good indices in the array:
 * - Index 2. The subarray [2,1] is in non-increasing order, and the subarray
 * [1,3] is in non-decreasing order.
 * - Index 3. The subarray [1,1] is in non-increasing order, and the subarray
 * [3,4] is in non-decreasing order.
 * Note that the index 4 is not good because [4,1] is not non-decreasing.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,1,1,2], k = 2
 * Output: []
 * Explanation: There are no good indices in this array.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length
 * 3 <= n <= 10^5
 * 1 <= nums[i] <= 10^6
 * 1 <= k <= n / 2
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> goodIndices(int[] nums, int k) {
        TreeMap<Integer, Integer> increasing = buildIndex(nums, true);
        TreeMap<Integer, Integer> decreasing = buildIndex(nums, false);
        // System.out.println(increasing);
        // System.out.println(decreasing);
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        for(int i = k; i < n - k; i++){
            int l = decreasing.floorKey(i - k);
            if(decreasing.get(l) < i - 1){
                continue;
            }
            l = increasing.floorKey(i + 1);
            if(increasing.get(l) >= i + k){
                ans.add(i);
            }
        }
        return ans;
    }

    TreeMap<Integer, Integer> buildIndex(int[] nums, boolean increasing){
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int n = nums.length;
        int l = 0;
        for(int i = 1; i < nums.length; i++){
            if(increasing && nums[i] < nums[i - 1]){
                tm.put(l, i - 1);
                l = i;
            }
            else if(!increasing && nums[i] > nums[i - 1]){
                tm.put(l, i - 1);
                l = i;
            }
        }
        tm.put(l, n - 1);
        return tm;
    }
}
// @lc code=end
