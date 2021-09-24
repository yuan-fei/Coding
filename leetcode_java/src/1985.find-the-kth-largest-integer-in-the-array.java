/*
 * @lc app=leetcode id=1985 lang=java
 *
 * [1985] Find the Kth Largest Integer in the Array
 *
 * https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/description/
 *
 * algorithms
 * Medium (42.49%)
 * Likes:    102
 * Dislikes: 25
 * Total Accepted:    10.8K
 * Total Submissions: 25.5K
 * Testcase Example:  '["3","6","7","10"]\n4'
 *
 * You are given an array of strings nums and an integer k. Each string in nums
 * represents an integer without leading zeros.
 * 
 * Return the string that represents the k^th largest integer in nums.
 * 
 * Note: Duplicate numbers should be counted distinctly. For example, if nums
 * is ["1","2","2"], "2" is the first largest integer, "2" is the
 * second-largest integer, and "1" is the third-largest integer.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = ["3","6","7","10"], k = 4
 * Output: "3"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["3","6","7","10"].
 * The 4^th largest integer in nums is "3".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = ["2","21","12","1"], k = 3
 * Output: "2"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["1","2","12","21"].
 * The 3^rd largest integer in nums is "2".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = ["0","0"], k = 2
 * Output: "0"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["0","0"].
 * The 2^nd largest integer in nums is "0".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= nums.length <= 10^4
 * 1 <= nums[i].length <= 100
 * nums[i] consists of only digits.
 * nums[i] will not have any leading zeros.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String kthLargestNumber(String[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums, (a, b) -> Integer.compare(b.length(), a.length()) != 0 ? Integer.compare(b.length(), a.length()) : b.compareTo(a));
        return nums[k - 1];
    }
}
// @lc code=end