/*
 * @lc app=leetcode id=1537 lang=java
 *
 * [1537] Get the Maximum Score
 *
 * https://leetcode.com/problems/get-the-maximum-score/description/
 *
 * algorithms
 * Hard (35.60%)
 * Likes:    170
 * Dislikes: 13
 * Total Accepted:    5.4K
 * Total Submissions: 15.2K
 * Testcase Example:  '[2,4,5,8,10]\n[4,6,8,9]'
 *
 * You are given two sorted arrays of distinct integers nums1 and nums2.
 * 
 * A valid path is defined as follows:
 * 
 * 
 * Choose array nums1 or nums2 to traverse (from index-0).
 * Traverse the current array from left to right.
 * If you are reading any value that is present in nums1 and nums2 you are
 * allowed to change your path to the other array. (Only one repeated value is
 * considered in the valid path).
 * 
 * 
 * Score is defined as the sum of uniques values in a valid path.
 * 
 * Return the maximum score you can obtain of all possible valid paths.
 * 
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: nums1 = [2,4,5,8,10], nums2 = [4,6,8,9]
 * Output: 30
 * Explanation: Valid paths:
 * [2,4,5,8,10], [2,4,5,8,9], [2,4,6,8,9], [2,4,6,8,10],  (starting from nums1)
 * [4,6,8,9], [4,5,8,10], [4,5,8,9], [4,6,8,10]    (starting from nums2)
 * The maximum is obtained with the path in green [2,4,6,8,10].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [1,3,5,7,9], nums2 = [3,5,100]
 * Output: 109
 * Explanation: Maximum sum is obtained with the path [1,3,5,100].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums1 = [1,2,3,4,5], nums2 = [6,7,8,9,10]
 * Output: 40
 * Explanation: There are no common elements between nums1 and nums2.
 * Maximum sum is obtained with the path [6,7,8,9,10].
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: nums1 = [1,4,5,8,9,11,19], nums2 = [2,3,4,11,12]
 * Output: 61
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums1.length <= 10^5
 * 1 <= nums2.length <= 10^5
 * 1 <= nums1[i], nums2[i] <= 10^7
 * nums1 and nums2 are strictly increasing.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxSum(int[] nums1, int[] nums2) {
    	long MOD = 1000000007;
    	Map<Integer, Integer> m = new HashMap<>();
    	long[] prefSum1 = new long[nums1.length + 1];
    	long[] prefSum2 = new long[nums2.length + 1];
    	for(int i =  0; i < nums1.length; i++){
    		m.put(nums1[i], i + 1);
    		prefSum1[i + 1] = prefSum1[i] + nums1[i];
    	}

    	for(int i = 0; i < nums2.length; i++){
    		prefSum2[i + 1] = prefSum2[i] + nums2[i];
    	}
    	long max = 0;
    	int left1 = 0;
    	int left2 = 0;
    	for(int i = 1; i <= nums2.length; i++){
    		if(m.containsKey(nums2[i - 1])){
    			int j = m.get(nums2[i - 1]);
    			max += Math.max(prefSum2[i] - prefSum2[left2], prefSum1[j] - prefSum1[left1]);
    			// System.out.println(max);
    			left2 = i;
    			left1 = j;
    		}
    	}
    	// System.out.println(max);
    	max += Math.max(prefSum2[nums2.length] - prefSum2[left2], prefSum1[nums1.length] - prefSum1[left1]);
    	return (int)(max % MOD);
    }


}
// @lc code=end
