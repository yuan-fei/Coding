/*
 * @lc app=leetcode id=2826 lang=java
 *
 * [2826] Sorting Three Groups
 *
 * https://leetcode.com/problems/sorting-three-groups/description/
 *
 * algorithms
 * Medium (41.64%)
 * Likes:    484
 * Dislikes: 90
 * Total Accepted:    18.6K
 * Total Submissions: 44.2K
 * Testcase Example:  '[2,1,3,2,1]'
 *
 * You are given an integer array nums. Each element in nums is 1, 2 or 3. In
 * each operation, you can remove an element from nums. Return the minimum
 * number of operations to make nums non-decreasing.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,1,3,2,1]
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * One of the optimal solutions is to remove nums[0], nums[2] and nums[3].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,3,2,1,3,3]
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * One of the optimal solutions is to remove nums[1] and nums[2].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [2,2,2,2,3,3]
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * nums is already non-decreasing.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 3
 * 
 * 
 * 
 * Follow-up: Can you come up with an algorithm that runs in O(n) time
 * complexity?
 */

// @lc code=start
class Solution {
    int MAX = 500;
    public int minimumOperations(List<Integer> nums) {
        nums.add(3);
        int[] positions = new int[4];
        Arrays.fill(positions, -1);
        // positions[nums.get(0)] = 0;
        int[] dp = new int[4];
        // Arrays.fill(dp, MAX);
        // dp[nums.get(0)] = 0;
        for(int i = 0; i < nums.size(); i++){
            int x = nums.get(i);
            int v = 0;
            switch(x){
                case 3:
                    v = Math.min(dp[1] + i - positions[1] - 1, dp[2] + i - positions[2] - 1);
                    v = Math.min(v, dp[3] + i - positions[3] - 1);
                    break;
                case 2:
                    v = Math.min(dp[1] + i - positions[1] - 1, dp[2] + i - positions[2] - 1);
                    break;
                case 1:
                    v = dp[1] + i - positions[1] - 1;
                    break;
            }
            dp[x] = v;
            positions[x] = i;
        }
        // System.out.println(Arrays.toString(positions));
        // System.out.println(Arrays.toString(dp));
        return dp[3];
    }
}
// @lc code=end
