/*
 * @lc app=leetcode id=624 lang=java
 *
 * [624] Maximum Distance in Arrays
 *
 * https://leetcode.com/problems/maximum-distance-in-arrays/description/
 *
 * algorithms
 * Medium (45.63%)
 * Likes:    1453
 * Dislikes: 116
 * Total Accepted:    188.1K
 * Total Submissions: 412.5K
 * Testcase Example:  '[[1,2,3],[4,5],[1,2,3]]'
 *
 * You are given m arrays, where each array is sorted in ascending order.
 * 
 * You can pick up two integers from two different arrays (each array picks
 * one) and calculate the distance. We define the distance between two integers
 * a and b to be their absolute difference |a - b|.
 * 
 * Return the maximum distance.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arrays = [[1,2,3],[4,5],[1,2,3]]
 * Output: 4
 * Explanation: One way to reach the maximum distance 4 is to pick 1 in the
 * first or third array and pick 5 in the second array.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arrays = [[1],[1]]
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == arrays.length
 * 2 <= m <= 10^5
 * 1 <= arrays[i].length <= 500
 * -10^4 <= arrays[i][j] <= 10^4
 * arrays[i] is sorted in ascending order.
 * There will be at most 10^5 integers in all the arrays.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        PriorityQueue<int[]> minQueue = new PriorityQueue<>(Comparator.comparing(x -> x[0]));
        PriorityQueue<int[]> maxQueue = new PriorityQueue<>(Comparator.comparing(x -> -x[0]));
        for(int i = 0; i < arrays.size(); i++){
            List<Integer> arr = arrays.get(i);
            minQueue.offer(new int[]{arr.get(0), i});
            maxQueue.offer(new int[]{arr.get(arr.size() - 1), i});
        }
        int[][] minimums = {minQueue.poll(), minQueue.poll()};
        int[][] maximums = {maxQueue.poll(), maxQueue.poll()};
        int ans = 0;
        for(int[] min : minimums){
            for (int[] max : maximums) {
                if(min[1] != max[1]){
                    ans = Math.max(ans, max[0] - min[0]);
                }
            }
        }
        return ans;
    }
}
// @lc code=end
