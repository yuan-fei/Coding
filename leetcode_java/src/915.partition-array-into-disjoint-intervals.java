/*
 * @lc app=leetcode id=915 lang=java
 *
 * [915] Partition Array into Disjoint Intervals
 *
 * https://leetcode.com/problems/partition-array-into-disjoint-intervals/description/
 *
 * algorithms
 * Medium (48.51%)
 * Likes:    1590
 * Dislikes: 74
 * Total Accepted:    77.9K
 * Total Submissions: 160.1K
 * Testcase Example:  '[5,0,3,8,6]'
 *
 * Given an integer array nums, partition it into two (contiguous) subarrays
 * left and right so that:
 * 
 * 
 * Every element in left is less than or equal to every element in right.
 * left and right are non-empty.
 * left has the smallest possible size.
 * 
 * 
 * Return the length of left after such a partitioning.
 * 
 * Test cases are generated such that partitioning exists.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [5,0,3,8,6]
 * Output: 3
 * Explanation: left = [5,0,3], right = [8,6]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,1,1,0,6,12]
 * Output: 4
 * Explanation: left = [1,1,1,0], right = [6,12]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^6
 * There is at least one valid answer for the given input.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        int[][] numsWithPos = new int[n][2];
        for(int i = 0; i < nums.length; i++){
            numsWithPos[i][0] = nums[i];
            numsWithPos[i][1] = i;
        }
        Arrays.sort(numsWithPos, Comparator.comparing(nwp -> nwp[0]));
        int[] rank = new int[n];
        for(int i = 0; i < n; i++){
            rank[numsWithPos[i][1]] = i;
        }
        // System.out.println(Arrays.toString(rank));
        int maxRight = -1;
        for(int i = 0; i < n; i++){
            maxRight = Math.max(maxRight, rank[i]);
            if(maxRight == i){
                return maxRight + 1;
            }
        }
        return 0;
    }
}
// @lc code=end
