/*
 * @lc app=leetcode id=941 lang=java
 *
 * [941] Valid Mountain Array
 *
 * https://leetcode.com/problems/valid-mountain-array/description/
 *
 * algorithms
 * Easy (33.34%)
 * Likes:    2788
 * Dislikes: 175
 * Total Accepted:    385.9K
 * Total Submissions: 1.2M
 * Testcase Example:  '[2,1]'
 *
 * Given an array of integers arr, return true if and only if it is a valid
 * mountain array.
 * 
 * Recall that arr is a mountain array if and only if:
 * 
 * 
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * 
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i] 
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * Input: arr = [2,1]
 * Output: false
 * Example 2:
 * Input: arr = [3,5,5]
 * Output: false
 * Example 3:
 * Input: arr = [0,3,2,1]
 * Output: true
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean validMountainArray(int[] arr) {
        int max = 0;
        int maxId = -1;
        for(int i = 0; i < arr.length; i++){
            if(max < arr[i]){
                max = arr[i];
                maxId = i;
            }
        }
        if(maxId == 0 || maxId == arr.length - 1){
            return false;
        }
        for(int i = 1; i < maxId; i++){
            if(arr[i] <= arr[i - 1]){
                return false;
            }
        }
        for(int i = maxId + 1; i < arr.length; i++){
            if(arr[i] >= arr[i - 1]){
                return false;
            }
        }
        return true;
    }
}
// @lc code=end
