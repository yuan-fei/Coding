/*
 * @lc app=leetcode id=1775 lang=java
 *
 * [1775] Equal Sum Arrays With Minimum Number of Operations
 *
 * https://leetcode.com/problems/equal-sum-arrays-with-minimum-number-of-operations/description/
 *
 * algorithms
 * Medium (50.34%)
 * Likes:    163
 * Dislikes: 3
 * Total Accepted:    5.6K
 * Total Submissions: 11.2K
 * Testcase Example:  '[1,2,3,4,5,6]\n[1,1,2,2,2,2]'
 *
 * You are given two arrays of integers nums1 and nums2, possibly of different
 * lengths. The values in the arrays are between 1 and 6, inclusive.
 * 
 * In one operation, you can change any integer's value in any of the arrays to
 * any value between 1 and 6, inclusive.
 * 
 * Return the minimum number of operations required to make the sum of values
 * in nums1 equal to the sum of values in nums2. Return -1​​​​​ if it is not
 * possible to make the sum of the two arrays equal.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
 * Output: 3
 * Explanation: You can make the sums of nums1 and nums2 equal with 3
 * operations. All indices are 0-indexed.
 * - Change nums2[0] to 6. nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2].
 * - Change nums1[5] to 1. nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2].
 * - Change nums1[2] to 2. nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [1,1,1,1,1,1,1], nums2 = [6]
 * Output: -1
 * Explanation: There is no way to decrease the sum of nums1 or to increase the
 * sum of nums2 to make them equal.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums1 = [6,6], nums2 = [1]
 * Output: 3
 * Explanation: You can make the sums of nums1 and nums2 equal with 3
 * operations. All indices are 0-indexed. 
 * - Change nums1[0] to 2. nums1 = [2,6], nums2 = [1].
 * - Change nums1[1] to 2. nums1 = [2,2], nums2 = [1].
 * - Change nums2[0] to 4. nums1 = [2,2], nums2 = [4].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums1.length, nums2.length <= 10^5
 * 1 <= nums1[i], nums2[i] <= 6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        // if(Math.max(n1, n2) > 6 * Math.min(n1, n2)){
        // 	return -1;
        // }
        int sum1 = sum(nums1);
        int sum2 = sum(nums2);
        PriorityQueue<Integer> q = new PriorityQueue<>();
        int[] large = (sum1 > sum2) ? nums1 : nums2;
        int[] small = (sum1 <= sum2) ? nums1 : nums2;
        for(int x : large){
    		if(x > 1){
    			q.offer(-(x - 1));
    		}
    	}
    	for(int x : small){
    		if(x < 6){
    			q.offer(-(6 - x));
    		}
    	}
    	int cnt = 0;
    	int diff = Math.abs(sum1 - sum2);
    	while(!q.isEmpty() && diff > 0){
    		int d = q.poll();
    		diff += d;
    		cnt++;
    	}
    	if(diff > 0){
    		return -1;
    	}
    	return cnt;
    }

    int sum(int[] a){
    	int ret = 0;
    	for(int x : a){
    		ret += x;
    	}
    	return ret;
    }
}
// @lc code=end
