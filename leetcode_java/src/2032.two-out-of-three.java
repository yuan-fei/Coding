/*
 * @lc app=leetcode id=2032 lang=java
 *
 * [2032] Two Out of Three
 *
 * https://leetcode.com/problems/two-out-of-three/description/
 *
 * algorithms
 * Easy (71.71%)
 * Likes:    46
 * Dislikes: 15
 * Total Accepted:    9K
 * Total Submissions: 12.5K
 * Testcase Example:  '[1,1,3,2]\n[2,3]\n[3]'
 *
 * Given three integer arrays nums1, nums2, and nums3, return a distinct array
 * containing all the values that are present in at least two out of the three
 * arrays. You may return the values in any order.
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
 * Output: [3,2]
 * Explanation: The values that are present in at least two arrays are:
 * - 3, in all three arrays.
 * - 2, in nums1 and nums2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
 * Output: [2,3,1]
 * Explanation: The values that are present in at least two arrays are:
 * - 2, in nums2 and nums3.
 * - 3, in nums1 and nums2.
 * - 1, in nums1 and nums3.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
 * Output: []
 * Explanation: No value is present in at least two arrays.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums1.length, nums2.length, nums3.length <= 100
 * 1 <= nums1[i], nums2[j], nums3[k] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        int[][] cnt = new int[101][3];
        for(int x : nums1){
            cnt[x][0] = 1;
        }
        for(int x : nums2){
            cnt[x][1] = 1;
        }
        for(int x : nums3){
            cnt[x][2] = 1;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < cnt.length; i++) {
            if(cnt[i][0] + cnt[i][1] + cnt[i][2] > 1){
                res.add(i);
            }
        }
        return res;
    }
}
// @lc code=end
