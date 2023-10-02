/*
 * @lc app=leetcode id=945 lang=java
 *
 * [945] Minimum Increment to Make Array Unique
 *
 * https://leetcode.com/problems/minimum-increment-to-make-array-unique/description/
 *
 * algorithms
 * Medium (51.74%)
 * Likes:    1715
 * Dislikes: 53
 * Total Accepted:    81.7K
 * Total Submissions: 157.5K
 * Testcase Example:  '[1,2,2]'
 *
 * You are given an integer array nums. In one move, you can pick an index i
 * where 0 <= i < nums.length and increment nums[i] by 1.
 * 
 * Return the minimum number of moves to make every value in nums unique.
 * 
 * The test cases are generated so that the answer fits in a 32-bit integer.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,2]
 * Output: 1
 * Explanation: After 1 move, the array could be [1, 2, 3].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,2,1,2,1,7]
 * Output: 6
 * Explanation: After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
 * It can be shown with 5 or less moves that it is impossible for the array to
 * have all unique values.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minIncrementForUnique(int[] nums) {
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        for(int x : nums){
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }
        int ans = 0;
        while(!freq.isEmpty()){
            int k = freq.firstKey();
            int cnt = freq.remove(k);
            ans += cnt - 1;
            if(cnt > 1){
                freq.put(k + 1, freq.getOrDefault(k + 1, 0) + cnt - 1);
            }
        }
        return ans;
    }
}
// @lc code=end
