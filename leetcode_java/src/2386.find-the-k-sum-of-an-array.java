/*
 * @lc app=leetcode id=2386 lang=java
 *
 * [2386] Find the K-Sum of an Array
 *
 * https://leetcode.com/problems/find-the-k-sum-of-an-array/description/
 *
 * algorithms
 * Hard (27.85%)
 * Likes:    188
 * Dislikes: 6
 * Total Accepted:    2.3K
 * Total Submissions: 8.4K
 * Testcase Example:  '[2,4,-2]\n5'
 *
 * You are given an integer array nums and a positive integer k. You can choose
 * any subsequence of the array and sum all of its elements together.
 * 
 * We define the K-Sum of the array as the k^th largest subsequence sum that
 * can be obtained (not necessarily distinct).
 * 
 * Return the K-Sum of the array.
 * 
 * A subsequence is an array that can be derived from another array by deleting
 * some or no elements without changing the order of the remaining elements.
 * 
 * Note that the empty subsequence is considered to have a sum of 0.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,4,-2], k = 5
 * Output: 2
 * Explanation: All the possible subsequence sums that we can obtain are the
 * following sorted in decreasing order:
 * - 6, 4, 4, 2, 2, 0, 0, -2.
 * The 5-Sum of the array is 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,-2,3,4,-10,12], k = 16
 * Output: 10
 * Explanation: The 16-Sum of the array is 10.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length
 * 1 <= n <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 1 <= k <= min(2000, 2^n)
 * 
 * 
 */

// @lc code=start
class Solution {
    public long kSum(int[] nums, int k) {
        int n = nums.length;
        long sum = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] > 0){
                sum += nums[i];    
            }
            else{
                nums[i] = -nums[i];
            }
        }
        k--;
        if(k == 0){
            return sum;
        }
        Arrays.sort(nums);
        for(int i = 0; i < n; i++){
            nums[i] = -nums[i];
        }
        PriorityQueue<long[]> q = new PriorityQueue<>((a, b) -> Long.compare(-a[0], -b[0]));
        q.offer(new long[]{nums[0], 0});
        while(!q.isEmpty()){
            long[] cur = q.poll();
            k--;
            // System.out.println(Arrays.toString(cur));
            if(k == 0){
                return sum + cur[0];
            }
            if(cur[1] + 1 < n){
                q.offer(new long[]{cur[0] - nums[(int)cur[1]] + nums[(int)cur[1] + 1], cur[1] + 1});    
                q.offer(new long[]{cur[0] + nums[(int)cur[1] + 1], cur[1] + 1});
            }
            
        }
        return -1;
    }
}
// @lc code=end
