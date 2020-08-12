/*
 * @lc app=leetcode id=1539 lang=java
 *
 * [1539] Kth Missing Positive Number
 *
 * https://leetcode.com/problems/kth-missing-positive-number/description/
 *
 * algorithms
 * Easy (50.13%)
 * Likes:    49
 * Dislikes: 1
 * Total Accepted:    6.6K
 * Total Submissions: 13.3K
 * Testcase Example:  '[2,3,4,7,11]\n5'
 *
 * Given an array arr of positive integers sorted in a strictly increasing
 * order, and an integer k.
 * 
 * Find the k^th positive integer that is missing from this array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The
 * 5^th missing positive integer is 9.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2^nd missing
 * positive integer is 6.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 * 1 <= k <= 1000
 * arr[i] < arr[j] for 1 <= i < j <= arr.length
 * 
 */

// @lc code=start
class Solution {
    public int findKthPositive(int[] arr, int k) {
    	Set<Integer> s = new HashSet<>();
        for(int x : arr){
        	s.add(x);
        }
        int ret = 1;
        while(true){
        	if(!s.contains(ret)){
        		k--;	
        	}
        	if(k == 0){
        		return ret;
        	}
        	ret++;
        }
    }
}
// @lc code=end
