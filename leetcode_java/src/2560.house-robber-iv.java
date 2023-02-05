/*
 * @lc app=leetcode id=2560 lang=java
 *
 * [2560] House Robber IV
 *
 * https://leetcode.com/problems/house-robber-iv/description/
 *
 * algorithms
 * Medium (24.80%)
 * Likes:    130
 * Dislikes: 7
 * Total Accepted:    3.1K
 * Total Submissions: 12.7K
 * Testcase Example:  '[2,3,5,9]\n2'
 *
 * There are several consecutive houses along a street, each of which has some
 * money inside. There is also a robber, who wants to steal money from the
 * homes, but he refuses to steal from adjacent homes.
 * 
 * The capability of the robber is the maximum amount of money he steals from
 * one house of all the houses he robbed.
 * 
 * You are given an integer array nums representing how much money is stashed
 * in each house. More formally, the i^th house from the left has nums[i]
 * dollars.
 * 
 * You are also given an integer k, representing the minimum number of houses
 * the robber will steal from. It is always possible to steal at least k
 * houses.
 * 
 * Return the minimum capability of the robber out of all the possible ways to
 * steal at least k houses.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,3,5,9], k = 2
 * Output: 5
 * Explanation: 
 * There are three ways to rob at least 2 houses:
 * - Rob the houses at indices 0 and 2. Capability is max(nums[0], nums[2]) =
 * 5.
 * - Rob the houses at indices 0 and 3. Capability is max(nums[0], nums[3]) =
 * 9.
 * - Rob the houses at indices 1 and 3. Capability is max(nums[1], nums[3]) =
 * 9.
 * Therefore, we return min(5, 9, 9) = 5.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,7,9,3,1], k = 2
 * Output: 2
 * Explanation: There are 7 ways to rob the houses. The way which leads to
 * minimum capability is to rob the house at index 0 and 4. Return max(nums[0],
 * nums[4]) = 2.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= (nums.length + 1)/2
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minCapability(int[] nums, int k) {
        int low = 1;
        int high = (int)1e9;
        while(low + 1 < high){
            int mid = low + (high - low) / 2;
            if(feasible(nums, k, mid)){
                high = mid;
            }
            else{
                low = mid;
            }
        }
        if(feasible(nums, k, low)){
            return low;
        }
        return high;
    }

    boolean feasible(int[] nums, int k, int cap){
        int cnt = 0;
        int left = -1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > cap){
                if(left != -1){
                    cnt += (i - left + 1) / 2;
                    left = -1;    
                }
            }
            else{
                if(left == -1){
                    left = i;
                }
            }
        }
        if(left != -1){
            cnt += (nums.length - left + 1) / 2;
        }
        return cnt >= k;
    }
}
// @lc code=end
