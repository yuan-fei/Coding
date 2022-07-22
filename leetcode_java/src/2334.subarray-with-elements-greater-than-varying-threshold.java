/*
 * @lc app=leetcode id=2334 lang=java
 *
 * [2334] Subarray With Elements Greater Than Varying Threshold
 *
 * https://leetcode.com/problems/subarray-with-elements-greater-than-varying-threshold/description/
 *
 * algorithms
 * Hard (36.00%)
 * Likes:    198
 * Dislikes: 2
 * Total Accepted:    2.5K
 * Total Submissions: 6.8K
 * Testcase Example:  '[1,3,4,3,1]\n6'
 *
 * You are given an integer array nums and an integer threshold.
 * 
 * Find any subarray of nums of length k such that every element in the
 * subarray is greater than threshold / k.
 * 
 * Return the size of any such subarray. If there is no such subarray, return
 * -1.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an
 * array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,3,4,3,1], threshold = 6
 * Output: 3
 * Explanation: The subarray [3,4,3] has a size of 3, and every element is
 * greater than 6 / 3 = 2.
 * Note that this is the only valid subarray.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [6,5,6,5,8], threshold = 7
 * Output: 1
 * Explanation: The subarray [8] has a size of 1, and 8 > 7 / 1 = 7. So 1 is
 * returned.
 * Note that the subarray [6,5] has a size of 2, and every element is greater
 * than 7 / 2 = 3.5. 
 * Similarly, the subarrays [6,5,6], [6,5,6,5], [6,5,6,5,8] also satisfy the
 * given conditions.
 * Therefore, 2, 3, 4, or 5 may also be returned.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i], threshold <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        int[] next_small = new int[n];
        int[] prev_small = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        Arrays.fill(next_small, n);
        Arrays.fill(prev_small, -1);
        for(int i=1;i<n;i++){
            while(!stack.isEmpty() && nums[stack.peek()] >= nums[i]){
                stack.pop();
            }    
            if(stack.size()!=0){
                prev_small[i] = stack.peek();
            }
            stack.push(i);
        }
        stack = new Stack<>();
        stack.push(n-1);
        for(int i=n-2;i>=0;i--){
            while(!stack.isEmpty() && nums[stack.peek()] >= nums[i]){
                stack.pop();
            }    
            if(stack.size()!=0){
                next_small[i] = stack.peek();
            }
            stack.push(i);
        }
        for(int i=0;i<n;i++){
            int len = next_small[i] - prev_small[i] - 1;
            if(threshold/(double)len < nums[i]){
                return len;
            }
        }
        return -1;
    }
}
// @lc code=end
