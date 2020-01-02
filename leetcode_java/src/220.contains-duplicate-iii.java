/*
 * @lc app=leetcode id=220 lang=java
 *
 * [220] Contains Duplicate III
 *
 * https://leetcode.com/problems/contains-duplicate-iii/description/
 *
 * algorithms
 * Medium (20.35%)
 * Likes:    851
 * Dislikes: 863
 * Total Accepted:    107.8K
 * Total Submissions: 529.5K
 * Testcase Example:  '[1,2,3,1]\n3\n0'
 *
 * Given an array of integers, find out whether there are two distinct indices
 * i and j in the array such that the absolute difference between nums[i] and
 * nums[j] is at most t and the absolute difference between i and j is at most
 * k.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> s = new TreeSet<>();
        for(int i = 0; i < nums.length; i++){
        	long lb = (long)nums[i] - t;
        	long ub = (long)nums[i] + t;
        	Long ceiling = s.ceiling(lb);
        	if(ceiling != null && ceiling <= ub){
        		return true;
        	}
        	Long floor = s.floor(ub);
        	if(floor != null && floor >= lb){
        		return true;
        	}
        	s.add((long)nums[i]);
        	if(s.size() > k){
        		s.remove((long)nums[i - k]);
        	}
        	
        }
        return false;
    }
}
// @lc code=end
