/*
 * @lc app=leetcode id=1296 lang=java
 *
 * [1296] Divide Array in Sets of K Consecutive Numbers
 *
 * https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/description/
 *
 * algorithms
 * Medium (47.26%)
 * Likes:    91
 * Dislikes: 4
 * Total Accepted:    5.9K
 * Total Submissions: 12.4K
 * Testcase Example:  '[1,2,3,3,4,4,5,6]\n4'
 *
 * Given an array of integers nums and a positive integer k, find whether it's
 * possible to divide this array into sets of k consecutive numbers
 * Return True if its possible otherwise return False.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,3,4,4,5,6], k = 4
 * Output: true
 * Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
 * Output: true
 * Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and
 * [9,10,11].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [3,3,2,2,1,1], k = 3
 * Output: true
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 * Explanation: Each array should be divided in subarrays of size 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= nums.length
 * 
 */

// @lc code=start
class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if(nums.length % k != 0){
        	return false;
        }
        TreeMap<Integer, Integer> cnt = new TreeMap<>();
        for(int n: nums){
        	cnt.put(n, cnt.getOrDefault(n, 0) + 1);
        }
        
        while(!cnt.isEmpty()){
        	// System.out.println(cnt);
        	Integer lowest = cnt.firstKey();
        	for(int i = lowest; i < lowest + k; i++){
        		if(!cnt.containsKey(i)){
        			return false;
        		}
        		else{
        			int c = cnt.get(i) - 1;
        			if(c == 0){
        				cnt.remove(i);
        			}
        			else{
        				cnt.put(i, c);
        			}
        		}
        	}
        }
        return true;
    }
}
// @lc code=end
