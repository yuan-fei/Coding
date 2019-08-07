/*
 * @lc app=leetcode id=1144 lang=java
 *
 * [1144] Decrease Elements To Make Array Zigzag
 *
 * https://leetcode.com/problems/decrease-elements-to-make-array-zigzag/description/
 *
 * algorithms
 * Medium (39.97%)
 * Total Accepted:    3.6K
 * Total Submissions: 9K
 * Testcase Example:  '[1,2,3]'
 *
 * Given an array nums of integers, a move consists of choosing any element and
 * decreasing it by 1.
 * 
 * An array A is a zigzag array if either:
 * 
 * 
 * Every even-indexed element is greater than adjacent elements, ie. A[0] >
 * A[1] < A[2] > A[3] < A[4] > ...
 * OR, every odd-indexed element is greater than adjacent elements, ie. A[0] <
 * A[1] > A[2] < A[3] > A[4] < ...
 * 
 * 
 * Return the minimum number of moves to transform the given array nums into a
 * zigzag array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3]
 * Output: 2
 * Explanation: We can decrease 2 to 0 or 3 to 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [9,6,1,6,2]
 * Output: 4
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * 
 * 
 */
class Solution {
    public int movesToMakeZigzag(int[] a) {
        int n = a.length;
        int totalOdd = 0;
        int totalEven = 0;
        for(int i = 0; i < n; i++){
            int left, right;
            left = right = Integer.MAX_VALUE;
            if(i-1>=0){
                left = a[i-1];
            }
            if(i+1<n){
                right = a[i + 1];
            }
            // System.out.println(left+" "+right);
            int delta = Math.max(0, a[i] - Math.min(left, right)+1);
            if(i%2==0){
                totalEven+=delta;
            }
            else{
                totalOdd+=delta;
            }
        }
        return Math.min(totalOdd, totalEven);
    }
}
