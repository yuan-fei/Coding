/*
 * @lc app=leetcode id=2009 lang=java
 *
 * [2009] Minimum Number of Operations to Make Array Continuous
 *
 * https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/description/
 *
 * algorithms
 * Hard (41.91%)
 * Likes:    105
 * Dislikes: 1
 * Total Accepted:    1.9K
 * Total Submissions: 4.6K
 * Testcase Example:  '[4,2,5,3]'
 *
 * You are given an integer array nums. In one operation, you can replace any
 * element in nums with any integer.
 * 
 * nums is considered continuous if both of the following conditions are
 * fulfilled:
 * 
 * 
 * All elements in nums are unique.
 * The difference between the maximum element and the minimum element in nums
 * equals nums.length - 1.
 * 
 * 
 * For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6]
 * is not continuous.
 * 
 * Return the minimum number of operations to make nums continuous.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [4,2,5,3]
 * Output: 0
 * Explanation: nums is already continuous.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3,5,6]
 * Output: 1
 * Explanation: One possible solution is to change the last element to 4.
 * The resulting array is [1,2,3,5,4], which is continuous.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,10,100,1000]
 * Output: 3
 * Explanation: One possible solution is to:
 * - Change the second element to 2.
 * - Change the third element to 3.
 * - Change the fourth element to 4.
 * The resulting array is [1,2,3,4], which is continuous.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        TreeSet<Integer> ts = new TreeSet<>();
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for(int x : nums){
            ts.add(x);
        }
        int i = 0;
        for(int x : ts){
            tm.put(x, i++);
        }
        i = 0;
        int ret = n;
        for(int k : tm.keySet()){
            // System.out.println(k +", "+ tm);
            int ubk = tm.floorKey(k + n - 1);
            int ubv = tm.get(ubk);
            ret = Math.min(ret, n - (ubv - i + 1));
            // System.out.println(k + ", " + ubk +", "+ ubv + ", " + ret);
            i++;
        }
        return ret;
    }
}
// @lc code=end
