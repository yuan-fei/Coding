/*
 * @lc app=leetcode id=2736 lang=java
 *
 * [2736] Maximum Sum Queries
 *
 * https://leetcode.com/problems/maximum-sum-queries/description/
 *
 * algorithms
 * Hard (27.89%)
 * Likes:    304
 * Dislikes: 15
 * Total Accepted:    4.9K
 * Total Submissions: 17.5K
 * Testcase Example:  '[4,3,1,2]\n[2,4,9,5]\n[[4,1],[1,3],[2,5]]'
 *
 * You are given two 0-indexed integer arrays nums1 and nums2, each of length
 * n, and a 1-indexed 2D array queries where queries[i] = [xi, yi].
 * 
 * For the i^th query, find the maximum value of nums1[j] + nums2[j] among all
 * indices j (0 <= j < n), where nums1[j] >= xi and nums2[j] >= yi, or -1 if
 * there is no j satisfying the constraints.
 * 
 * Return an array answer where answer[i] is the answer to the i^th query.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [4,3,1,2], nums2 = [2,4,9,5], queries = [[4,1],[1,3],[2,5]]
 * Output: [6,10,7]
 * Explanation: 
 * For the 1st query xi = 4 and yi = 1, we can select index j = 0 since
 * nums1[j] >= 4 and nums2[j] >= 1. The sum nums1[j] + nums2[j] is 6, and we
 * can show that 6 is the maximum we can obtain.
 * 
 * For the 2nd query xi = 1 and yi = 3, we can select index j = 2 since
 * nums1[j] >= 1 and nums2[j] >= 3. The sum nums1[j] + nums2[j] is 10, and we
 * can show that 10 is the maximum we can obtain. 
 * 
 * For the 3rd query xi = 2 and yi = 5, we can select index j = 3 since
 * nums1[j] >= 2 and nums2[j] >= 5. The sum nums1[j] + nums2[j] is 7, and we
 * can show that 7 is the maximum we can obtain.
 * 
 * Therefore, we return [6,10,7].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [3,2,5], nums2 = [2,3,4], queries = [[4,4],[3,2],[1,1]]
 * Output: [9,9,9]
 * Explanation: For this example, we can use index j = 2 for all the queries
 * since it satisfies the constraints for each query.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums1 = [2,1], nums2 = [2,3], queries = [[3,3]]
 * Output: [-1]
 * Explanation: There is one query in this example with xi = 3 and yi = 3. For
 * every index, j, either nums1[j] < xi or nums2[j] < yi. Hence, there is no
 * solution. 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * nums1.length == nums2.length 
 * n == nums1.length 
 * 1 <= n <= 10^5
 * 1 <= nums1[i], nums2[i] <= 10^9 
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * xi == queries[i][1]
 * yi == queries[i][2]
 * 1 <= xi, yi <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int m = queries.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int[][] nums = new int[n][2];
        for(int i = 0; i < n; i++){
            nums[i][0] = nums1[i];
            nums[i][1] = nums2[i];
        }
        Arrays.sort(nums, Comparator.comparing(x -> -x[0]));
        int[][] queriesWithIdx = new int[m][3];
        for(int i = 0; i < m; i++){
            queriesWithIdx[i][0] = queries[i][0];
            queriesWithIdx[i][1] = queries[i][1];
            queriesWithIdx[i][2] = i;
        }
        Arrays.sort(queriesWithIdx, Comparator.comparing(x -> -x[0]));
        int[] ans = new int[m];
        int j = 0;
        for(int i = 0; i < m; i++){
            while(j < n && queriesWithIdx[i][0] <= nums[j][0]){
                Integer ceilingKey = tm.ceilingKey(nums[j][1]);
                if(ceilingKey != null && tm.get(ceilingKey) >= nums[j][1] + nums[j][0]){
                    // nums[j][1] is not optimal, ignore it
                }
                else{
                    Integer floorKey = tm.floorKey(nums[j][1]);
                    while(floorKey != null && tm.get(floorKey) <= nums[j][1] + nums[j][0]){
                        tm.remove(floorKey);
                        floorKey = tm.floorKey(nums[j][1]);
                    }
                    tm.put(nums[j][1], nums[j][1] + nums[j][0]);
                }
                j++;
            }
            // System.out.println(tm);
            Integer ceilingKey = tm.ceilingKey(queriesWithIdx[i][1]);
            ans[queriesWithIdx[i][2]] = (ceilingKey == null) ? -1 : tm.get(ceilingKey);
        }
        return ans;
    }
}
// @lc code=end
