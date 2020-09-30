/*
 * @lc app=leetcode id=532 lang=java
 *
 * [532] K-diff Pairs in an Array
 *
 * https://leetcode.com/problems/k-diff-pairs-in-an-array/description/
 *
 * algorithms
 * Easy (31.89%)
 * Likes:    710
 * Dislikes: 1427
 * Total Accepted:    114.9K
 * Total Submissions: 360.2K
 * Testcase Example:  '[3,1,4,1,5]\n2'
 *
 * Given an array of integers and an integer k, you need to find the number of
 * unique k-diff pairs in the array. Here a k-diff pair is defined as an
 * integer pair (i, j), where i and j are both numbers in the array and their
 * absolute difference is k.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,1,4,1,5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of
 * unique pairs.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3,4,5], k = 1
 * Output: 4
 * Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3,
 * 4) and (4, 5).
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,3,1,5,4], k = 0
 * Output: 1
 * Explanation: There is one 0-diff pair in the array, (1, 1).
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: nums = [1,2,4,4,3,3,0,9,2,3], k = 3
 * Output: 2
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: nums = [-1,-2,-3], k = 1
 * Output: 2
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^4
 * -10^7 <= nums[i] <= 10^7
 * 0 <= k <= 10^7
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findPairs(int[] nums, int k) {
        Map<Integer, Boolean> s = new HashMap<>();
        int cnt = 0;
        for(int n : nums){
        	if(k == 0){
        		if(s.containsKey(n) && !s.get(n)){
        			cnt++;
        		}
        	}
        	else if(!s.containsKey(n)){
        		if(s.containsKey(n - k)){
        			cnt++;	
        		}
        		if(s.containsKey(k + n)){
        			cnt++;	
        		}
        	}
        	if(s.containsKey(n)){
        		s.put(n, true);	
        	}
        	else{
        		s.put(n, false);
        	}
        	// System.out.println(cnt);
        }
        return cnt;
    }
}
// @lc code=end
