/*
 * @lc app=leetcode id=2442 lang=java
 *
 * [2442] Count Number of Distinct Integers After Reverse Operations
 *
 * https://leetcode.com/problems/count-number-of-distinct-integers-after-reverse-operations/description/
 *
 * algorithms
 * Medium (77.92%)
 * Likes:    56
 * Dislikes: 3
 * Total Accepted:    14.2K
 * Total Submissions: 18.2K
 * Testcase Example:  '[1,13,10,12,31]'
 *
 * You are given an array nums consisting of positive integers.
 * 
 * You have to take each integer in the array, reverse its digits, and add it
 * to the end of the array. You should apply this operation to the original
 * integers in nums.
 * 
 * Return the number of distinct integers in the final array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,13,10,12,31]
 * Output: 6
 * Explanation: After including the reverse of each number, the resulting array
 * is [1,13,10,12,31,1,31,1,21,13].
 * The reversed integers that were added to the end of the array are
 * underlined. Note that for the integer 10, after reversing it, it becomes 01
 * which is just 1.
 * The number of distinct integers in this array is 6 (The numbers 1, 10, 12,
 * 13, 21, and 31).
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,2,2]
 * Output: 1
 * Explanation: After including the reverse of each number, the resulting array
 * is [2,2,2,2,2,2].
 * The number of distinct integers in this array is 1 (The number 2).
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countDistinctIntegers(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for(int x : nums){
            s.add(x);
            s.add(reverse(x));
        }
        return s.size();
    }

    int reverse(int x){
        int ret = 0;
        while(x != 0){
            int d = x % 10;
            x /= 10;
            ret *= 10;
            ret += d;
        }
        return ret;
    }
}
// @lc code=end
