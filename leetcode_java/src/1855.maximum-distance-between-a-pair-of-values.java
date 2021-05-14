/*
 * @lc app=leetcode id=1855 lang=java
 *
 * [1855] Maximum Distance Between a Pair of Values
 *
 * https://leetcode.com/problems/maximum-distance-between-a-pair-of-values/description/
 *
 * algorithms
 * Medium (44.74%)
 * Likes:    150
 * Dislikes: 4
 * Total Accepted:    7.4K
 * Total Submissions: 16.6K
 * Testcase Example:  '[55,30,5,4,2]\n[100,20,10,10,5]'
 *
 * You are given two non-increasing 0-indexed integer arrays nums1​​​​​​ and
 * nums2​​​​​​.
 * 
 * A pair of indices (i, j), where 0 <= i < nums1.length and 0 <= j <
 * nums2.length, is valid if both i <= j and nums1[i] <= nums2[j]. The distance
 * of the pair is j - i​​​​.
 * 
 * Return the maximum distance of any valid pair (i, j). If there are no valid
 * pairs, return 0.
 * 
 * An array arr is non-increasing if arr[i-1] >= arr[i] for every 1 <= i <
 * arr.length.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
 * Output: 2
 * Explanation: The valid pairs are (0,0), (2,2), (2,3), (2,4), (3,3), (3,4),
 * and (4,4).
 * The maximum distance is 2 with pair (2,4).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [2,2,2], nums2 = [10,10,1]
 * Output: 1
 * Explanation: The valid pairs are (0,0), (0,1), and (1,1).
 * The maximum distance is 1 with pair (0,1).
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums1 = [30,29,19,5], nums2 = [25,25,25,25,25]
 * Output: 2
 * Explanation: The valid pairs are (2,2), (2,3), (2,4), (3,3), and (3,4).
 * The maximum distance is 2 with pair (2,4).
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: nums1 = [5,4], nums2 = [3,2]
 * Output: 0
 * Explanation: There are no valid pairs, so return 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums1.length <= 10^5
 * 1 <= nums2.length <= 10^5
 * 1 <= nums1[i], nums2[j] <= 10^5
 * Both nums1 and nums2 are non-increasing.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
    	int n = nums1.length;
    	int m = nums2.length;
    	int i = 0;
    	int j = 0;
    	int max = 0;
        while(i < n && j < m){
        	if(nums1[i] <= nums2[j]){
        		max = Math.max(max, j - i);
        		j++;
        	}
        	else{
        		i++;
        	}
        }
        return max;
    }
}
// @lc code=end
