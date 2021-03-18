/*
 * @lc app=leetcode id=1793 lang=java
 *
 * [1793] Maximum Score of a Good Subarray
 *
 * https://leetcode.com/problems/maximum-score-of-a-good-subarray/description/
 *
 * algorithms
 * Hard (43.93%)
 * Likes:    174
 * Dislikes: 12
 * Total Accepted:    4.2K
 * Total Submissions: 9.7K
 * Testcase Example:  '[1,4,3,7,4,5]\n3'
 *
 * You are given an array of integers nums (0-indexed) and an integer k.
 * 
 * The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ...,
 * nums[j]) * (j - i + 1). A good subarray is a subarray where i <= k <= j.
 * 
 * Return the maximum possible score of a good subarray.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,4,3,7,4,5], k = 3
 * Output: 15
 * Explanation: The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) *
 * (5-1+1) = 3 * 5 = 15. 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [5,5,4,5,4,1,1,1], k = 0
 * Output: 20
 * Explanation: The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) *
 * (4-0+1) = 4 * 5 = 20.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 2 * 10^4
 * 0 <= k < nums.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximumScore(int[] nums, int k) {
    	int n = nums.length;
    	int[] numsPadding = new int[n + 2];
        System.arraycopy(nums, 0, numsPadding, 1, n);
        numsPadding[0] = -1;
        numsPadding[n + 1] = -1;
        nums = numsPadding;
        Stack<Integer> s = new Stack<>();
        s.push(0);
        int max = 0;
        int[] left = new int[n + 2];
        int[] right = new int[n + 2];
        for(int i = 1; i <= n + 1; i++){
        	while(nums[s.peek()] > nums[i]){
        		int j = s.pop();
        		right[j] = i;
        	}
        	s.push(i);
        }
        s.clear();
        s.push(n + 1);
        for(int i = n + 1; i >= 1; i--){
        	while(nums[s.peek()] > nums[i]){
        		int j = s.pop();
        		left[j] = i;
        	}
        	s.push(i);
        }
        for(int i = 1; i <= n + 1; i++){
        	if(left[i] < k + 1 && k + 1 < right[i]){
        		max = Math.max(max, (right[i] - left[i] - 1) * nums[i]);
        	}
        }
        
        return max;
    }
}
// @lc code=end
