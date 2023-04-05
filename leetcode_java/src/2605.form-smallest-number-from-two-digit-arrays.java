/*
 * @lc app=leetcode id=2605 lang=java
 *
 * [2605] Form Smallest Number From Two Digit Arrays
 *
 * https://leetcode.com/problems/form-smallest-number-from-two-digit-arrays/description/
 *
 * algorithms
 * Easy (51.28%)
 * Likes:    67
 * Dislikes: 3
 * Total Accepted:    15.7K
 * Total Submissions: 30.6K
 * Testcase Example:  '[4,1,3]\n[5,7]'
 *
 * Given two arrays of unique digits nums1 and nums2, return the smallest
 * number that contains at least one digit from each array.
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [4,1,3], nums2 = [5,7]
 * Output: 15
 * Explanation: The number 15 contains the digit 1 from nums1 and the digit 5
 * from nums2. It can be proven that 15 is the smallest number we can have.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [3,5,2,6], nums2 = [3,1,7]
 * Output: 3
 * Explanation: The number 3 contains the digit 3 which exists in both
 * arrays.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums1.length, nums2.length <= 9
 * 1 <= nums1[i], nums2[i] <= 9
 * All digits in each array are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minNumber(int[] nums1, int[] nums2) {
        Set<Integer> ts1 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            ts1.add(nums1[i]);
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // System.out.println(ts1);
        for (int i = 0; i < nums2.length; i++) {
            if(ts1.contains(nums2[i])){
                return nums2[i];
            }
        }
        return Math.min(nums2[0] * 10 + nums1[0], nums1[0] * 10 + nums2[0]);
    }
}
// @lc code=end
