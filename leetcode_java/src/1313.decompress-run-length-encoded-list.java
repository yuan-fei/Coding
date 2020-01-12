/*
 * @lc app=leetcode id=1313 lang=java
 *
 * [1313] Decompress Run-Length Encoded List
 *
 * https://leetcode.com/problems/decompress-run-length-encoded-list/description/
 *
 * algorithms
 * Easy (81.31%)
 * Likes:    8
 * Dislikes: 49
 * Total Accepted:    2.6K
 * Total Submissions: 3.1K
 * Testcase Example:  '[1,2,3,4]'
 *
 * We are given a list nums of integers representing a list compressed with
 * run-length encoding.
 * 
 * Consider each adjacent pair of elements [a, b] = [nums[2*i], nums[2*i+1]]
 * (with i >= 0).  For each such pair, there are a elements with value b in the
 * decompressed list.
 * 
 * Return the decompressed list.
 * 
 * 
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [2,4,4,4]
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 100
 * nums.length % 2 == 0
 * 1 <= nums[i] <= 100
 * 
 */

// @lc code=start
class Solution {
    public int[] decompressRLElist(int[] nums) {
    	int n = 0;
        for (int i = 0; i < nums.length; i += 2) {
        	n +=nums[i];
        }
        int[] ans = new int[n];
        int last = 0;
        for (int i = 1; i < nums.length; i += 2) {
        	for(int j = 0; j < nums[i - 1]; j++){
        		ans[last++] = nums[i];	
        	}
        }
        return ans;
    }
}
// @lc code=end
