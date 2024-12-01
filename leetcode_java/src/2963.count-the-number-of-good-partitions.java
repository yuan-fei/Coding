/*
 * @lc app=leetcode id=2963 lang=java
 *
 * [2963] Count the Number of Good Partitions
 *
 * https://leetcode.com/problems/count-the-number-of-good-partitions/description/
 *
 * algorithms
 * Hard (48.97%)
 * Likes:    251
 * Dislikes: 3
 * Total Accepted:    11.4K
 * Total Submissions: 24.1K
 * Testcase Example:  '[1,2,3,4]'
 *
 * You are given a 0-indexed array nums consisting of positive integers.
 * 
 * A partition of an array into one or more contiguous subarrays is called good
 * if no two subarrays contain the same number.
 * 
 * Return the total number of good partitions of nums.
 * 
 * Since the answer may be large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,4]
 * Output: 8
 * Explanation: The 8 possible good partitions are: ([1], [2], [3], [4]), ([1],
 * [2], [3,4]), ([1], [2,3], [4]), ([1], [2,3,4]), ([1,2], [3], [4]), ([1,2],
 * [3,4]), ([1,2,3], [4]), and ([1,2,3,4]).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,1,1,1]
 * Output: 1
 * Explanation: The only possible good partition is: ([1,1,1,1]).
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,2,1,3]
 * Output: 2
 * Explanation: The 2 possible good partitions are: ([1,2,1], [3]) and
 * ([1,2,1,3]).
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
    long MOD = (long)1e9 + 7;
    public int numberOfGoodPartitions(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> numToPos = new HashMap<>();
        for(int i = 0; i < n; i++){
            numToPos.put(nums[i], i);
        }
        int cnt = 0;
        int ub = 0;
        for(int i = 0; i < n; i++){
            int lastPos = numToPos.get(nums[i]);
            ub = Math.max(ub, lastPos);
            if(ub == i){
                cnt++;
            }
        }
        return (int)partition(cnt);
    }

    long partition(int n){
        return modularExpRecursive(2, n - 1, MOD);
    }

    long modularExpRecursive(long base, long exp, long m) {
        if (exp == 0) {
            return 1;
        } else if (exp % 2 == 1) {
            long l = base * modularExpRecursive(base, exp - 1, m);
            return l % m;
        } else {
            long t = modularExpRecursive(base, exp / 2, m);
            return (t * t) % m;
        }
    }
}
// @lc code=end
