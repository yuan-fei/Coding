/*
 * @lc app=leetcode id=1505 lang=java
 *
 * [1505] Minimum Possible Integer After at Most K Adjacent Swaps On Digits
 *
 * https://leetcode.com/problems/minimum-possible-integer-after-at-most-k-adjacent-swaps-on-digits/description/
 *
 * algorithms
 * Hard (36.43%)
 * Likes:    102
 * Dislikes: 10
 * Total Accepted:    2.9K
 * Total Submissions: 7.9K
 * Testcase Example:  '"4321"\n4'
 *
 * Given a string num representing the digits of a very large integer and an
 * integer k.
 * 
 * You are allowed to swap any two adjacent digits of the integer at most k
 * times.
 * 
 * Return the minimum integer you can obtain also as a string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = "4321", k = 4
 * Output: "1342"
 * Explanation: The steps to obtain the minimum integer from 4321 with 4
 * adjacent swaps are shown.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = "100", k = 1
 * Output: "010"
 * Explanation: It's ok for the output to have leading zeros, but the input is
 * guaranteed not to have any leading zeros.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: num = "36789", k = 1000
 * Output: "36789"
 * Explanation: We can keep the number without any swaps.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: num = "22", k = 22
 * Output: "22"
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: num = "9438957234785635408", k = 23
 * Output: "0345989723478563548"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= num.length <= 30000
 * num contains digits only and doesn't have leading zeros.
 * 1 <= k <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public String minInteger(String num, int k) {
        int n = num.length();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) nums[i] = Character.getNumericValue(num.charAt(i));
        helper(nums, k);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(nums[i] + "");
        }
        return sb.toString();
    }
    
    private void helper(int[] nums, int k) {
        int n = nums.length;
        for(int i = 0; i < n && k > 0; i++) {
            int index = i;
            for(int j = i + 1; j < n; j++) {
                if(k < j - i) break;
                if(nums[j] < nums[index]) {
                    index = j;
                }
            }
            for(int j = index; j > i; j--) {
                swap(nums, j, j - 1);
            }
            k -= (index - i);
        }
    }
    
    private void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
// @lc code=end
