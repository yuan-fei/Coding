/*
 * @lc app=leetcode id=1879 lang=java
 *
 * [1879] Minimum XOR Sum of Two Arrays
 *
 * https://leetcode.com/problems/minimum-xor-sum-of-two-arrays/description/
 *
 * algorithms
 * Hard (27.94%)
 * Likes:    132
 * Dislikes: 5
 * Total Accepted:    2.4K
 * Total Submissions: 8.7K
 * Testcase Example:  '[1,2]\n[2,3]'
 *
 * You are given two integer arrays nums1 and nums2 of length n.
 * 
 * The XOR sum of the two integer arrays is (nums1[0] XOR nums2[0]) + (nums1[1]
 * XOR nums2[1]) + ... + (nums1[n - 1] XOR nums2[n - 1]) (0-indexed).
 * 
 * 
 * For example, the XOR sum of [1,2,3] and [3,2,1] is equal to (1 XOR 3) + (2
 * XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4.
 * 
 * 
 * Rearrange the elements of nums2 such that the resulting XOR sum is
 * minimized.
 * 
 * Return the XOR sum after the rearrangement.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [1,2], nums2 = [2,3]
 * Output: 2
 * Explanation: Rearrange nums2 so that it becomes [3,2].
 * The XOR sum is (1 XOR 3) + (2 XOR 2) = 2 + 0 = 2.
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [1,0,3], nums2 = [5,3,4]
 * Output: 8
 * Explanation: Rearrange nums2 so that it becomes [5,4,3]. 
 * The XOR sum is (1 XOR 5) + (0 XOR 4) + (3 XOR 3) = 4 + 4 + 0 = 8.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums1.length
 * n == nums2.length
 * 1 <= n <= 14
 * 0 <= nums1[i], nums2[i] <= 10^7
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumXORSum(int[] nums1, int[] nums2) {

    	int n = nums1.length;
    	int[] dp = new int[1 << n];
    	int MAX = (int)1e9;
    	Arrays.fill(dp, MAX);
    	dp[0] = 0;
        for(int i = 0; i < n; i++){
        	for(int m = 1; m < (1 << n); m++){
        		if(Integer.bitCount(m) == i + 1){
        			for(int j = 0; j < n; j++){
        				if(((m >> j) & 1) != 0){
        					dp[m] = Math.min(dp[m], dp[m ^ (1 << j)] + (nums1[i] ^ nums2[j]));
        				}
        			}
        		}
        	}
        	// System.out.println(Arrays.toString(dp));
        }
        return dp[(1 << n) - 1];
    }
}
// @lc code=end
