/*
 * @lc app=leetcode id=2584 lang=java
 *
 * [2584] Split the Array to Make Coprime Products
 *
 * https://leetcode.com/problems/split-the-array-to-make-coprime-products/description/
 *
 * algorithms
 * Medium (12.30%)
 * Likes:    25
 * Dislikes: 64
 * Total Accepted:    2.4K
 * Total Submissions: 20.4K
 * Testcase Example:  '[4,7,8,15,3,5]'
 *
 * You are given a 0-indexed integer array nums of length n.
 * 
 * A split at an index i where 0 <= i <= n - 2 is called valid if the product
 * of the first i + 1 elements and the product of the remaining elements are
 * coprime.
 * 
 * 
 * For example, if nums = [2, 3, 3], then a split at the index i = 0 is valid
 * because 2 and 9 are coprime, while a split at the index i = 1 is not valid
 * because 6 and 3 are not coprime. A split at the index i = 2 is not valid
 * because i == n - 1.
 * 
 * 
 * Return the smallest index i at which the array can be split validly or -1 if
 * there is no such split.
 * 
 * Two values val1 and val2 are coprime if gcd(val1, val2) == 1 where gcd(val1,
 * val2) is the greatest common divisor of val1 and val2.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [4,7,8,15,3,5]
 * Output: 2
 * Explanation: The table above shows the values of the product of the first i
 * + 1 elements, the remaining elements, and their gcd at each index i.
 * The only valid split is at index 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [4,7,15,8,3,5]
 * Output: -1
 * Explanation: The table above shows the values of the product of the first i
 * + 1 elements, the remaining elements, and their gcd at each index i.
 * There is no valid split.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length
 * 1 <= n <= 10^4
 * 1 <= nums[i] <= 10^6
 * 
 * 
 */

// @lc code=start

class Solution {
    public int findValidSplit(int[] nums) {
        if (nums.length == 1) {
            return -1;
        }

        // Get factors of each number, and the factor ranges
        List<Integer>[] factors = new List[nums.length];
        Map<Integer, Integer> factorEnds = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            factors[i] = new ArrayList<>();
            for (int j = 2; j * j <= nums[i]; j++) {
                if (nums[i] % j == 0) {
                    factors[i].add(j);
                    factors[i].add(nums[i] / j);
                    factorEnds.put(j, i);
                    factorEnds.put(nums[i] / j, i);
                }
            }
            factors[i].add(nums[i]);
            factorEnds.put(nums[i], i);
        }

        // Sweep line
        int end = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int factor : factors[i]) {
                end = Math.max(end, factorEnds.get(factor));
            }
            if (end == nums.length - 1) {
                break;
            }
            if (end == i) {
                return end;
            }
        }

        return -1;
    }
}
// @lc code=end
