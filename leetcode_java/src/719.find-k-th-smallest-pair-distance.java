/*
 * @lc app=leetcode id=719 lang=java
 *
 * [719] Find K-th Smallest Pair Distance
 *
 * https://leetcode.com/problems/find-k-th-smallest-pair-distance/description/
 *
 * algorithms
 * Hard (29.59%)
 * Total Accepted:    21.6K
 * Total Submissions: 73.1K
 * Testcase Example:  '[1,3,1]\n1'
 *
 * Given an integer array, return the k-th smallest distance among all the
 * pairs. The distance of a pair (A, B) is defined as the absolute difference
 * between A and B. 
 * 
 * Example 1:
 * 
 * Input:
 * nums = [1,3,1]
 * k = 1
 * Output: 0 
 * Explanation:
 * Here are all the pairs:
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * Then the 1st smallest distance pair is (1,1), and its distance is 0.
 * 
 * 
 * 
 * Note:
 * 
 * 2 .
 * 0 .
 * 1 .
 * 
 * 
 */
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int low = 0;
        int high = nums[nums.length - 1] - nums[0];
        while(low + 1 < high){
        	int mid = (low + high) /2;
        	if(hasNoLessThanThanKPairs(nums, k, mid)){
        		high = mid;
        	}
        	else{
        		low = mid;
        	}
        	// System.out.println(low+", "+high);
        }
        if(hasNoLessThanThanKPairs(nums, k, low)){
        	return low;
        }
        else{
        	return high;
        }
    }

    boolean hasNoLessThanThanKPairs(int[] nums, int k, int target){
    	int cnt = 0;
    	int left = 0;
    	for (int i = 0; i < nums.length; i++) {
    		while(nums[i] - nums[left] > target){
    			left++;
    		}
    		cnt += i - left;
    	}
    	if(cnt >= k){
    		return true;
    	}
    	return false;
    }
}
