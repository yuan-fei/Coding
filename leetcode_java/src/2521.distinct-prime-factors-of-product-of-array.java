/*
 * @lc app=leetcode id=2521 lang=java
 *
 * [2521] Distinct Prime Factors of Product of Array
 *
 * https://leetcode.com/problems/distinct-prime-factors-of-product-of-array/description/
 *
 * algorithms
 * Medium (46.50%)
 * Likes:    95
 * Dislikes: 5
 * Total Accepted:    10.4K
 * Total Submissions: 22.3K
 * Testcase Example:  '[2,4,3,7,10,6]'
 *
 * Given an array of positive integers nums, return the number of distinct
 * prime factors in the product of the elements of nums.
 * 
 * Note that:
 * 
 * 
 * A number greater than 1 is called prime if it is divisible by only 1 and
 * itself.
 * An integer val1 is a factor of another integer val2 if val2 / val1 is an
 * integer.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,4,3,7,10,6]
 * Output: 4
 * Explanation:
 * The product of all the elements in nums is: 2 * 4 * 3 * 7 * 10 * 6 = 10080 =
 * 2^5 * 3^2 * 5 * 7.
 * There are 4 distinct prime factors so we return 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,4,8,16]
 * Output: 1
 * Explanation:
 * The product of all the elements in nums is: 2 * 4 * 8 * 16 = 1024 = 2^10.
 * There is 1 distinct prime factor so we return 1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^4
 * 2 <= nums[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int distinctPrimeFactors(int[] nums) {
        Set<Integer> primes = new HashSet<>();
        for(int x : nums){
            primes.addAll(getPrimeFactors(x));
        }
        return primes.size();
    }

    Set<Integer> getPrimeFactors(int n){
        Set<Integer> s = new HashSet<>();
        for(int i = 2; i <= n; i++){
            while(n % i == 0){
                n /= i;
                s.add(i);
            }
        }
        return s;
    }
}
// @lc code=end
