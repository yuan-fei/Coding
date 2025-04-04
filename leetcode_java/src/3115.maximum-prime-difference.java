/*
 * @lc app=leetcode id=3115 lang=java
 *
 * [3115] Maximum Prime Difference
 *
 * https://leetcode.com/problems/maximum-prime-difference/description/
 *
 * algorithms
 * Medium (57.69%)
 * Likes:    109
 * Dislikes: 15
 * Total Accepted:    42.8K
 * Total Submissions: 74.9K
 * Testcase Example:  '[4,2,9,5,3]'
 *
 * You are given an integer array nums.
 * 
 * Return an integer that is the maximum distance between the indices of two
 * (not necessarily different) prime numbers in nums.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [4,2,9,5,3]
 * 
 * Output: 3
 * 
 * Explanation: nums[1], nums[3], and nums[4] are prime. So the answer is |4 -
 * 1| = 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [4,8,2,8]
 * 
 * Output: 0
 * 
 * Explanation: nums[2] is prime. Because there is just one prime number, the
 * answer is |2 - 2| = 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 3 * 10^5
 * 1 <= nums[i] <= 100
 * The input is generated such that the number of prime numbers in the nums is
 * at least one.
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    public int maximumPrimeDifference(int[] nums) {
        int n = nums.length;
        int[] primeIndexes = IntStream.range(0, n).filter(x -> isPrime(nums[x])).toArray();
        System.out.println(Arrays.toString(primeIndexes));
        return primeIndexes[primeIndexes.length - 1] - primeIndexes[0];
    }

    boolean isPrime(int x) {
        for (int f = 2; f * f <= x; f++) {
            if (x % f == 0) {
                return false;
            }
        }
        return x > 1;
    }
}
// @lc code=end
