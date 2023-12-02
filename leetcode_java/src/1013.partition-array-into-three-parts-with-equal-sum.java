/*
 * @lc app=leetcode id=1013 lang=java
 *
 * [1013] Partition Array Into Three Parts With Equal Sum
 *
 * https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/description/
 *
 * algorithms
 * Easy (42.16%)
 * Likes:    1649
 * Dislikes: 150
 * Total Accepted:    86.5K
 * Total Submissions: 205.3K
 * Testcase Example:  '[0,2,1,-6,6,-7,9,1,2,0,1]'
 *
 * Given an array of integers arr, return true if we can partition the array
 * into three non-empty parts with equal sums.
 * 
 * Formally, we can partition the array if we can find indexes i + 1 < j with
 * (arr[0] + arr[1] + ... + arr[i] == arr[i + 1] + arr[i + 2] + ... + arr[j -
 * 1] == arr[j] + arr[j + 1] + ... + arr[arr.length - 1])
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [0,2,1,-6,6,-7,9,1,2,0,1]
 * Output: true
 * Explanation: 0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [0,2,1,-6,6,7,9,-1,2,0,1]
 * Output: false
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr = [3,3,6,5,-2,2,5,1,-9,4]
 * Output: true
 * Explanation: 3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= arr.length <= 5 * 10^4
 * -10^4 <= arr[i] <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean canThreePartsEqualSum(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        int sum = Arrays.stream(arr).sum();
        if(sum % 3 != 0){
            return false;
        }
        int target = sum / 3;
        int targetMetCnt = 0;
        int prefSum = 0;
        for(int x : arr){
            prefSum += x;
            if(prefSum == target){
                targetMetCnt++;
                target += sum / 3;
            }
        }
        return targetMetCnt >= 3;
    }
}
// @lc code=end
