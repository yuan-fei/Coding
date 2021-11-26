/*
 * @lc app=leetcode id=697 lang=java
 *
 * [697] Degree of an Array
 *
 * https://leetcode.com/problems/degree-of-an-array/description/
 *
 * algorithms
 * Easy (55.21%)
 * Likes:    1691
 * Dislikes: 1123
 * Total Accepted:    131.1K
 * Total Submissions: 237.3K
 * Testcase Example:  '[1,2,2,3,1]'
 *
 * Given a non-empty array of non-negative integers nums, the degree of this
 * array is defined as the maximum frequency of any one of its elements.
 * 
 * Your task is to find the smallest possible length of a (contiguous) subarray
 * of nums, that has the same degree as nums.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,2,3,1]
 * Output: 2
 * Explanation: 
 * The input array has a degree of 2 because both elements 1 and 2 appear
 * twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,2,3,1,4,2]
 * Output: 6
 * Explanation: 
 * The degree is 3 because the element 2 is repeated 3 times.
 * So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * nums.length will be between 1 and 50,000.
 * nums[i] will be an integer between 0 and 49,999.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findShortestSubArray(int[] nums) {
        int n = 50000;
        // n = 4;
        int[] cnt = new int[n];
        int[] start = new int[n];
        int[] end = new int[n];
        Arrays.fill(start, nums.length);
        for(int i = 0; i < nums.length; i++){
            cnt[nums[i]]++;
            start[nums[i]] = Math.min(start[nums[i]], i);
            end[nums[i]] = Math.max(end[nums[i]], i);
        }
        int max = 1;
        int minDist = nums.length;
        for(int i = 0; i < cnt.length; i++){
            if(cnt[i] > max){
                minDist = end[i] - start[i] + 1;
                max = cnt[i];
            }
            if(cnt[i] >= max){
                minDist = Math.min(minDist, end[i] - start[i] + 1);
            }
        }
        // System.out.println(Arrays.toString(cnt));
        // System.out.println(Arrays.toString(start));
        // System.out.println(Arrays.toString(end));
        return minDist;
    }
}
// @lc code=end
