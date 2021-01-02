/*
 * @lc app=leetcode id=658 lang=java
 *
 * [658] Find K Closest Elements
 *
 * https://leetcode.com/problems/find-k-closest-elements/description/
 *
 * algorithms
 * Medium (41.59%)
 * Likes:    1775
 * Dislikes: 297
 * Total Accepted:    131.3K
 * Total Submissions: 315.3K
 * Testcase Example:  '[1,2,3,4,5]\n4\n3'
 *
 * Given a sorted integer array arr, two integers k and x, return the k closest
 * integers to x in the array. The result should also be sorted in ascending
 * order.
 * 
 * An integer a is closer to x than an integer b if:
 * 
 * 
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 * 
 * 
 * 
 * Example 1:
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * Example 2:
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 10^4
 * Absolute value of elements in the array and x will not exceed 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> findClosestElements(int[] A, int k, int x) {
        int left = 0, right = A.length - k;
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - A[mid] > A[mid + k] - x)
                left = mid + 1;
            else
                right = mid;
        }
        return Arrays.stream(A, left, left + k).boxed().collect(Collectors.toList());
    }
}
// @lc code=end
