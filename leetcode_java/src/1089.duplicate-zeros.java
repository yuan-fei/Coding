/*
 * @lc app=leetcode id=1089 lang=java
 *
 * [1089] Duplicate Zeros
 *
 * https://leetcode.com/problems/duplicate-zeros/description/
 *
 * algorithms
 * Easy (51.63%)
 * Likes:    2523
 * Dislikes: 720
 * Total Accepted:    394.1K
 * Total Submissions: 762.1K
 * Testcase Example:  '[1,0,2,3,0,4,5,0]'
 *
 * Given a fixed-length integer array arr, duplicate each occurrence of zero,
 * shifting the remaining elements to the right.
 * 
 * Note that elements beyond the length of the original array are not written.
 * Do the above modifications to the input array in place and do not return
 * anything.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [1,0,2,3,0,4,5,0]
 * Output: [1,0,0,2,3,0,0,4]
 * Explanation: After calling your function, the input array is modified to:
 * [1,0,0,2,3,0,0,4]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [1,2,3]
 * Output: [1,2,3]
 * Explanation: After calling your function, the input array is modified to:
 * [1,2,3]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 9
 * 
 * 
 */

// @lc code=start
class Solution {
    public void duplicateZeros(int[] arr) {
        int[] arrCopy = Arrays.copyOf(arr, arr.length);
        int j = 0;
        for(int i = 0; i < arr.length && j < arr.length; i++, j++){
            arr[j] = arrCopy[i];
            if(arrCopy[i] == 0 && ++j < arr.length){
                arr[j] = arrCopy[i];
            }
        }
    }
}
// @lc code=end
