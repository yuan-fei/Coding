/*
 * @lc app=leetcode id=1248 lang=java
 *
 * [1248] Count Number of Nice Subarrays
 *
 * https://leetcode.com/problems/count-number-of-nice-subarrays/description/
 *
 * algorithms
 * Medium (47.35%)
 * Likes:    42
 * Dislikes: 0
 * Total Accepted:    2.6K
 * Total Submissions: 5.5K
 * Testcase Example:  '[1,1,2,1,1]\n3'
 *
 * Given an array of integers nums and an integer k. A subarray is called nice
 * if there are k odd numbers on it.
 * 
 * Return the number of nice sub-arrays.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and
 * [1,2,1,1].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There is no odd numbers in the array.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
    	int even = 0;
    	List<Integer> evens = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
        	if(nums[i] % 2 == 1){
        		evens.add(even);
        		even = 0;
        	}
        	else{
        		even++;
        	}
        }
        evens.add(even);
        int total = 0;
        for(int i = 0; i + k < evens.size(); i++){
        	total += (evens.get(i) + 1) * (evens.get(i + k) + 1);
        }
        return total;
    }
}
// @lc code=end
