/*
 * @lc app=leetcode id=2659 lang=java
 *
 * [2659] Make Array Empty
 *
 * https://leetcode.com/problems/make-array-empty/description/
 *
 * algorithms
 * Hard (14.02%)
 * Likes:    180
 * Dislikes: 9
 * Total Accepted:    2.4K
 * Total Submissions: 16.5K
 * Testcase Example:  '[3,4,-1]'
 *
 * You are given an integer array nums containing distinct numbers, and you can
 * perform the following operations until the array is empty:
 * 
 * 
 * If the first element has the smallest value, remove it
 * Otherwise, put the first element at the end of the array.
 * 
 * 
 * Return an integer denoting the number of operations it takes to make nums
 * empty.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,4,-1]
 * Output: 5
 * 
 * 
 * 
 * 
 * 
 * Operation
 * Array
 * 
 * 
 * 
 * 
 * 1
 * [4, -1, 3]
 * 
 * 
 * 2
 * [-1, 3, 4]
 * 
 * 
 * 3
 * [3, 4]
 * 
 * 
 * 4
 * [4]
 * 
 * 
 * 5
 * []
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,4,3]
 * Output: 5
 * 
 * 
 * 
 * 
 * 
 * Operation
 * Array
 * 
 * 
 * 
 * 
 * 1
 * [2, 4, 3]
 * 
 * 
 * 2
 * [4, 3]
 * 
 * 
 * 3
 * [3, 4]
 * 
 * 
 * 4
 * [4]
 * 
 * 
 * 5
 * []
 * 
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,2,3]
 * Output: 3
 * 
 * 
 * 
 * 
 * 
 * Operation
 * Array
 * 
 * 
 * 
 * 
 * 1
 * [2, 3]
 * 
 * 
 * 2
 * [3]
 * 
 * 
 * 3
 * []
 * 
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * All values in nums are distinct.
 * 
 * 
 */

// @lc code=start
class Solution {
    public long countOperationsToEmptyArray(int[] nums) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for(int i = 0;  i< nums.length; i++){
            tm.put(nums[i], i);
        }
        int cnt = 0;
        int prePos = -1;
        long total = nums.length;
        long ans = 0;
        for(int k : tm.keySet()){
            int pos = tm.get(k);
            if(pos > prePos){
                cnt++;
            }
            else{
                ans += total;
                total -= cnt;
                cnt = 1;
            }
            prePos = pos;
        }
        ans += total;
        return ans;
    }
}
// @lc code=end
